package com.lxinet.jeesns.service.weibo;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.WeiboComment;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IWeiboCommentService {

    WeiboComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel listByWeibo(Page page, int articleId);

    void deleteByWeibo(Integer weiboId);
}
