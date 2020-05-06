package com.lxinet.jeesns.interceptor;

import com.lxinet.jeesns.core.annotation.*;
import com.lxinet.jeesns.core.interceptor.JeesnsInterceptor;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.service.member.MessageService;
import com.lxinet.jeesns.utils.ConfigUtil;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化监听器
 * Created by zchuanzhao on 16/9/26.
 */
public class InitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MessageService messageService = SpringContextUtil.getBean(MessageService.class);
        Member loginUser = MemberUtil.getLoginMember(request);
        request.setAttribute("loginUser", loginUser);
        //会员未读私信数量
        Integer unReadMessageNum = 0;
        //系统未读信息数量
        Integer systemUnReadMessageNum = 0;
        if (loginUser != null) {
            if (loginUser.getIsActive() == 0) {
                String memberEmailValid = (String) request.getServletContext().getAttribute(ConfigUtil.MEMBER_EMAIL_VALID.toUpperCase());
                if (Integer.parseInt(memberEmailValid) == 1) {
                    if (!(request.getServletPath().indexOf("member/active") != -1 || request.getServletPath().indexOf("member/logout") != -1 ||
                            request.getServletPath().indexOf("member/sendEmailActiveValidCode") != -1 || request.getServletPath().indexOf("/res/") != -1 ||
                            request.getServletPath().indexOf("/upload/") != -1)) {
                        response.sendRedirect(request.getContextPath() + "/member/active");
                        return false;
                    }
                }
            }
            //会员未读信息
            unReadMessageNum = messageService.countUnreadNum(loginUser.getId());
            systemUnReadMessageNum = messageService.countSystemUnreadNum(loginUser.getId());
        }
        request.setAttribute("unReadMessageNum", unReadMessageNum);
        request.setAttribute("systemUnReadMessageNum", systemUnReadMessageNum);

        if (handler != null) {
            List<Annotation> annotationList = new ArrayList<>();
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                Class clazz = ((HandlerMethod) handler).getMethod().getDeclaringClass();
                Annotation userLogin = ((HandlerMethod) handler).getMethod().getAnnotation(UserLogin.class);
                if (userLogin != null){
                    boolean result = this.invoke(UserLoginInterceptor.class, request, response, handler);
                    if (!result){
                        return result;
                    }
                }
                Annotation adminLogin = ((HandlerMethod) handler).getMethod().getAnnotation(AdminLogin.class);
                if (adminLogin != null){
                    boolean result = this.invoke(AdminLoginInterceptor.class, request, response, handler);
                    if (!result){
                        return result;
                    }
                }
                Annotation[] classAnnotations = clazz.getAnnotations();
                for (Annotation annotation : classAnnotations) {
                    annotationList.add(annotation);
                }
                Annotation[] methodAnnotations = ((HandlerMethod) handler).getMethod().getAnnotations();
                for (Annotation annotation : methodAnnotations) {
                    annotationList.add(annotation);
                }
                for (int i = 0; i < annotationList.size(); i++) {
                    boolean hasClear = false;
                    Annotation annotation = annotationList.get(i);
                    //获取Before注解
                    Before before = null;
                    try {
                        before = (Before) annotation;
                    } catch (Exception e) {

                    }
                    if (before != null) {
                        for (int j = i + 1; j < annotationList.size(); j++) {
                            Annotation annotation1 = annotationList.get(j);
                            Clear clear = null;
                            try {
                                clear = (Clear) annotation1;
                            } catch (Exception e) {

                            }
                            if (clear != null) {
                                hasClear = true;
                                break;
                            }
                        }
                        //在@Before注解后面如果有@Clear注解，该注解就无效
                        if (!hasClear) {
                            Class<? extends JeesnsInterceptor> interceptorlll = before.value();
                            boolean result = this.invoke(interceptorlll, request, response, handler);
                            return result;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        if (handler != null) {
            List<Annotation> annotationList = new ArrayList<>();
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                Class clazz = ((HandlerMethod) handler).getMethod().getDeclaringClass();
                Annotation[] classAnnotations = clazz.getAnnotations();
                for (Annotation annotation : classAnnotations) {
                    annotationList.add(annotation);
                }
                Annotation[] methodAnnotations = ((HandlerMethod) handler).getMethod().getAnnotations();
                for (Annotation annotation : methodAnnotations) {
                    annotationList.add(annotation);
                }
                for (int i = 0; i < annotationList.size(); i++) {
                    boolean hasClear = false;
                    Annotation annotation = annotationList.get(i);
                    //获取After注解
                    After after = null;
                    try {
                        after = (After) annotation;
                    } catch (Exception e1) {

                    }
                    if (after != null) {
                        for (int j = i + 1; j < annotationList.size(); j++) {
                            Annotation annotation1 = annotationList.get(j);
                            Clear clear = null;
                            try {
                                clear = (Clear) annotation1;
                            } catch (Exception e1) {

                            }
                            if (clear != null) {
                                hasClear = true;
                                break;
                            }
                        }
                        //在@After注解后面如果有@Clear注解，该注解就无效
                        if (!hasClear) {
                            Class<? extends JeesnsInterceptor> interceptorlll = after.value();
                            this.invoke(interceptorlll, request, response, handler);
                        }
                    }
                }
            }
        }
    }

    private boolean invoke(Class<?> interceptorlll, HttpServletRequest request, HttpServletResponse response, Object handler) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object object = Class.forName(interceptorlll.getCanonicalName()).newInstance();
        Class[] clazzs = new Class[]{HttpServletRequest.class, HttpServletResponse.class, Object.class};
        Method method = object.getClass().getMethod("interceptor", clazzs);
        Object[] params = new Object[]{request, response, handler};
        boolean result = (boolean) method.invoke(object, params);
        return result;
    }
}
