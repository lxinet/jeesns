package com.lxinet.jeesns.modules.cms.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.cms.dao.IArticleCateDao;
import com.lxinet.jeesns.modules.cms.dao.IArticleDao;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import com.lxinet.jeesns.modules.cms.service.IArticleCateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Service("articleCateService")
public class ArticleCateServiceImpl implements IArticleCateService {

    @Resource
    private IArticleCateDao articleCateDao;
    @Resource
    private IArticleDao articleDao;

    @Override
    public ArticleCate findById(int id) {
        ArticleCate articleCate = articleCateDao.findById(id);
        return articleCate;
    }

    @Override
    public int save(ArticleCate articleCate) {
        return articleCateDao.save(articleCate);
    }

    @Override
    public int update(ArticleCate articleCate) {
        return articleCateDao.update(articleCate);
    }

    @Override
    @Transactional
    public ResponseModel delete(int id) {
        List sonList = this.findListByFid(id);
        if(sonList.size() > 0){
            return new ResponseModel(-1,"请先删除子栏目");
        }
//        articleDao.setArticleAsNoneCate(id);
        int result = articleCateDao.delete(id);

        if(result == 1){
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

    @Override
    public List<ArticleCate> list() {
        return articleCateDao.list();
    }

    public List<ArticleCate> findListByFid(int fid) {
        return articleCateDao.findListByFid(fid);
    }

}
