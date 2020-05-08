package com.lxinet.jeesns.interceptor;

import com.alibaba.fastjson.JSON;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ApiTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = request.getHeader("Authorization");
            JwtUtil jwtUtil = SpringContextUtil.getBean(JwtUtil.class);
            Member member = jwtUtil.getMember(token);
            if (member == null){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                Result result = new Result(-99);
                out.print(JSON.toJSONString(result));
                out.flush();
                out.close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
