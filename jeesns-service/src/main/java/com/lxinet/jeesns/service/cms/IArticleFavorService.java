package com.lxinet.jeesns.service.cms;


import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.cms.ArticleFavor;

/**
 * 文章点赞Service接口
 * Created by zchuanzhao on 2018/11/21.
 */
public interface IArticleFavorService extends IBaseService<ArticleFavor> {

    ArticleFavor find(Integer articleId, Integer memberId);

    void save(Integer articleId, Integer memberId);

    void delete(Integer articleId, Integer memberId);
}