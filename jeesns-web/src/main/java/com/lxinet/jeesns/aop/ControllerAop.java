package com.lxinet.jeesns.aop;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.exception.JeeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


/**
 * @author zchuanzhao
 */
@Aspect
@Configuration
public class ControllerAop {

    @Pointcut("execution(public com.lxinet.jeesns.core.dto.ResultModel *(..))")
    public void excudeService(){}

    @Around("excudeService()")
    public Object handlerController(ProceedingJoinPoint pjp){
        ResultModel<?> result;
        try {
            result = (ResultModel) pjp.proceed();
        } catch (Throwable e) {
            result = handlerExceotion(pjp, e);
        }
        return result;
    }

    private ResultModel<?> handlerExceotion(ProceedingJoinPoint pjp, Throwable e){
        ResultModel<?> result = new ResultModel();
        if (e instanceof JeeException){
            result.setMessage(((JeeException)e).getJeeMessage());
        }else {
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
