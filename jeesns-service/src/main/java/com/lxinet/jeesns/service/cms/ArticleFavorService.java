package com.lxinet.jeesns.service.cms;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.cms.IArticleFavorDao;
import com.lxinet.jeesns.model.cms.ArticleFavor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("articleFavorService")
public class ArticleFavorService extends BaseServiceImpl<ArticleFavor> {
    @Resource
    private IArticleFavorDao articleFavorDao;

    public ArticleFavor find(Integer articleId, Integer memberId) {
        return articleFavorDao.find(articleId,memberId);
    }

    public void save(Integer articleId, Integer memberId) {
        articleFavorDao.save(articleId,memberId);
    }

    public void delete(Integer articleId, Integer memberId) {
        articleFavorDao.delete(articleId,memberId);
    }
}
