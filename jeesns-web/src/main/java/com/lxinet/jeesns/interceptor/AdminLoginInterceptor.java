package com.lxinet.jeesns.interceptor;

import com.lxinet.jeesns.core.interceptor.JeesnsInterceptor;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.member.Member;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zchuanzhao on 16/11/25.
 */
public class AdminLoginInterceptor implements JeesnsInterceptor {

    @Override
    public boolean interceptor(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Member loginAdmin = MemberUtil.getLoginMember(request);
        if (loginAdmin == null || loginAdmin.getIsAdmin() == 0) {
            if (isAjaxRequest(request)){
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("code", -99);
                json.put("message", "登录信息已失效，请重新登录");
                out.write(json.toString());
                out.flush();
                out.close();
            }else {
                JeesnsConfig jeesnsConfig = SpringContextUtil.getBean(JeesnsConfig.class);
                String redirectUrl = request.getRequestURI();
                response.sendRedirect(request.getContextPath() + "/" + jeesnsConfig.getManagePath() + "/login?redirectUrl=" + redirectUrl);
            }
            return false;
        }
        request.setAttribute("loginUser", loginAdmin);
        return true;
    }

    /**
     * 判断是否是AJAX请求
     * @return true ajax
     */
    private boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(header) ? true : false;
    }
}
