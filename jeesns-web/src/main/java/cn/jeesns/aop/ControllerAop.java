package cn.jeesns.aop;

import cn.jeesns.core.dto.Result;
import cn.jeesns.core.exception.JeeException;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * @author zchuanzhao
 */
public class ControllerAop {

    public Object handlerController(ProceedingJoinPoint pjp){
        Result<?> result;
        try {
            result = (Result) pjp.proceed();
        } catch (Throwable e) {
            result = handlerExceotion(pjp, e);
        }
        return result;
    }

    private Result<?> handlerExceotion(ProceedingJoinPoint pjp, Throwable e){
        Result<?> result = new Result();
        if (e instanceof JeeException && null != ((JeeException) e).getJeeMessage()){
            result.setMessage(((JeeException)e).getJeeMessage());
        }else {
            result.setCode(-1);
            if (null == e.getMessage()){
                result.setMessage("系统异常：" + e.toString());
            }else {
                result.setMessage(e.getMessage());
            }
            e.printStackTrace();
        }
        return result;
    }
}
