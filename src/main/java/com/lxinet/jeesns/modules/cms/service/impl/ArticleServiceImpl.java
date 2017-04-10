package com.lxinet.jeesns.modules.cms.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Archive;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.modules.cms.dao.IArticleDao;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.cms.service.IArticleCommentService;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
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
    @Resource
    private IArticleCommentService articleCommentService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreRuleService scoreRuleService;

    @Override
    public Article findById(int id) {
        return articleDao.findById(id,0);
    }

    @Override
    public Article findById(int id,Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        return articleDao.findById(id,loginMemberId);
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
                if(article.getStatus() == 1){
                    //投稿审核通过奖励
                    scoreRuleService.scoreRuleBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
                }
                actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_ARTICLE,"", ActionLogType.ARTICLE.getValue(),article.getId());
                return new ResponseModel(0,"文章发布成功");
            }
        }
        return new ResponseModel(-1,"文章发布失败");
    }

    @Override
    public ResponseModel listByPage(Page page, String key, int cateid,int status,int memberId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<Article> list = articleDao.listByPage(page, key,cateid,status,memberId);
        ResponseModel model = new ResponseModel(0,page);
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
            Article article = this.findById(id);
            if(article != null){
                //说明此次操作是审核通过
                if(article.getStatus() == 1){
                    //投稿审核通过奖励
                    scoreRuleService.scoreRuleBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
                }
            }
            return new ResponseModel(1,"操作成功");
        }else {
            return new ResponseModel(-1,"操作时候");
        }
    }

    @Override
    public ResponseModel favor(Member loginMember, int articleId) {
        Article article = this.findById(articleId);
        if(article != null){
            ResponseModel responseModel = archiveService.favor(loginMember,article.getArchiveId());
            if(responseModel.getCode() == 0){
                //文章收到喜欢
                scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
            }else if(responseModel.getCode() == 1){
                //取消喜欢，扣除积分
                scoreRuleService.scoreRuleCancelBonus(loginMember.getId(),ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
            }
            return responseModel;
        }
        return new ResponseModel(-1,"文章不存在");
    }

    @Override
    @Transactional
    public ResponseModel update(Member member,Article article) {
        Article findArticle = this.findById(article.getId(),member);
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
            findArticle.setCateId(article.getCateId());
            articleDao.update(findArticle);
            return new ResponseModel(0,"更新成功");
        }
        return new ResponseModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResponseModel delete(Member member,int id) {
        Article article = this.findById(id);
        if (article == null){
            return new ResponseModel(-1,"文章不存在");
        }
        int result = articleDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreRuleService.scoreRuleCancelBonus(member.getId(),ScoreRuleConsts.ARTICLE_SUBMISSIONS,id);
            archiveService.delete(article.getArchiveId());
            articleCommentService.deleteByArticle(id);
            actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.DELETE_ARTICLE,"ID："+article.getId()+"，标题："+article.getTitle());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

}
