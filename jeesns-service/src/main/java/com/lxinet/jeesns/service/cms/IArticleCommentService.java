package com.lxinet.jeesns.service.cms;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.model.member.Member;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleCommentService {

    ArticleComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer articleId);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByArticle(Page page, int articleId);

    void deleteByArticle(Integer articleId);
}
