package com.lxinet.jeesns.cms.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.cms.model.ArticleComment;
import com.lxinet.jeesns.member.model.Member;


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
