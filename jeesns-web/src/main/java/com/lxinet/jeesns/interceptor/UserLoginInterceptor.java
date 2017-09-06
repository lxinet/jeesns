package com.lxinet.jeesns.interceptor;

import com.lxinet.jeesns.common.utils.MemberUtil;
import com.lxinet.jeesns.core.utils.SpringContextHolder;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.common.utils.ConfigUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public class UserLoginInterceptor implements JeesnsInterceptor {

    @Override
    public boolean interceptor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception{
        try {
            Member loginUser = MemberUtil.getLoginMember(httpServletRequest);
            if (loginUser == null || loginUser.getId() == null) {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/member/login");
                return false;
            }else {
                IMemberService memberService = SpringContextHolder.getBean("memberService");
                Member findMember = memberService.findById(loginUser.getId());
                if(1 == Integer.parseInt((String) httpServletRequest.getServletContext().getAttribute(ConfigUtil.MEMBER_EMAIL_VALID.toUpperCase()))){
                    if(findMember.getIsActive() == 0){
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/member/active");
                        return false;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
