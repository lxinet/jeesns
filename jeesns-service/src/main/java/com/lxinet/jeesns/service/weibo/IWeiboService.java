package com.lxinet.jeesns.service.weibo;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.Weibo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public interface IWeiboService {

    Weibo findById(int id, int memberId);

    ResponseModel save(HttpServletRequest request, Member loginMember, String content, String pictures);

    ResponseModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key);

    ResponseModel delete(HttpServletRequest request, Member loginMember, int id);

    ResponseModel userDelete(HttpServletRequest request, Member loginMember, int id);

    List<Weibo> hotList(int loginMemberId);

    ResponseModel favor(Member loginMember, int weiboId);

    List<Weibo> listByCustom(int loginMemberId, String sort,int num,int day);
}
