package com.lxinet.jeesns.cms.service;

import com.lxinet.jeesns.cms.model.Article;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.member.model.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleService {

    Article findById(int id);

    Article findById(int id,Member loginMember);

    ResponseModel save(Member member,Article article);

    ResponseModel update(Member member,Article article);

    ResponseModel delete(Member member,int id);

    ResponseModel listByPage(Page page, String key, int cateid, int status, int memberId);

    void updateViewCount(int id);

    ResponseModel audit(int id);

    ResponseModel favor(Member loginMember, int articleId);

    List<Article> listByCustom(int cid,String sort,int num,int day);
}
