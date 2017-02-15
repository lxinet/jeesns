package com.lxinet.jeesns.modules.weibo.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.weibo.entity.WeiboComment;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IWeiboCommentService {

    WeiboComment findById(int id);

    ResponseModel save(Member loginMember,String content,Integer articleId);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel listByWeibo(Page page, int articleId);

    void deleteByWeibo(Integer weiboId);
}
