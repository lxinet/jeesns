package com.lxinet.jeesns.interceptor;

import com.lxinet.jeesns.common.utils.MemberUtil;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.SpringContextHolder;
import com.lxinet.jeesns.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zchuanzhao on 16/11/25.
 */
public class AdminLoginInterceptor implements JeesnsInterceptor {

    @Override
    public boolean interceptor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        Member loginAdmin = MemberUtil.getLoginMember(httpServletRequest);
        if (loginAdmin == null || loginAdmin.getIsAdmin() == 0) {
            JeesnsConfig jeesnsConfig = SpringContextHolder.getBean("jeesnsConfig");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + jeesnsConfig.getManagePath() + "/login");
            return false;
        }
        httpServletRequest.setAttribute("loginUser", loginAdmin);
        return true;
    }
}
