package com.lxinet.jeesns.modules.cms.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.cms.entity.ArticleComment;
import com.lxinet.jeesns.modules.mem.entity.Member;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleCommentService {

    ArticleComment findById(int id);

    ResponseModel save(Member loginMember,String content,Integer articleId);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByArticle(Page page, int articleId);

    void deleteByArticle(Integer articleId);
}
