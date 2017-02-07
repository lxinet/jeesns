package com.lxinet.jeesns.modules.cms.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Archive;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.ConfigUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.cms.dao.IArticleDao;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/10/14.
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private IArticleDao articleDao;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IConfigService configService;

    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }

    @Override
    @Transactional
    public ResponseModel save(Member member, Article article) {
        Map<String,String> config = configService.getConfigToMap();
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST))){
            return new ResponseModel(-1,"投稿功能已关闭");
        }
        if(article.getCateId() == null || article.getCateId() == 0){
            return new ResponseModel(-1,"栏目不能为空");
        }
        article.setMemberId(member.getId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        archive.setPostType(1);
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
            article.setStatus(0);
        }else {
            article.setStatus(1);
        }
        //保存文档
        if(archiveService.save(member,archive)){
            //保存文章
            article.setArchiveId(archive.getArchiveId());
            int result = articleDao.save(article);
            if(result == 1){
                return new ResponseModel(0,"文章发布成功");
            }
        }
        return new ResponseModel(-1,"文章发布失败");
    }

    @Override
    public ResponseModel listByPage(Page page, String key, int cateid,int status) {
        PageInterceptor.startPage(page);
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<Article> list = articleDao.listByPage(key,cateid,status);
        ResponseModel model = new ResponseModel(0,PageInterceptor.endPage());
        model.setData(list);
        return model;
    }

    @Override
    public void updateViewCount(int id) {
        articleDao.updateViewCount(id);
    }

    @Override
    public ResponseModel audit(int id) {
        if(articleDao.audit(id) == 1){
            return new ResponseModel(1,"操作成功");
        }else {
            return new ResponseModel(-1,"操作时候");
        }
    }

    @Override
    @Transactional
    public ResponseModel update(Member member,Article article) {
        Article findArticle = this.findById(article.getId());
        if(findArticle == null){
            return new ResponseModel(-2);
        }
        article.setArchiveId(findArticle.getArchiveId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(archiveService.update(member,archive)){
            Map<String,String> config = configService.getConfigToMap();
            if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
                findArticle.setStatus(0);
            }else {
                findArticle.setStatus(1);
            }
            //更新栏目
            if(findArticle.getCateId() != article.getCateId()){
                findArticle.setCateId(article.getCateId());
            }
            articleDao.update(findArticle);
            return new ResponseModel(0,"更新成功");
        }
        return new ResponseModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResponseModel delete(int id) {
        Article article = articleDao.findById(id);
        int result = articleDao.delete(id);
        if(result == 1){
            archiveService.delete(article.getArchiveId());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

}
