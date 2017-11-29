package com.lxinet.jeesns.core.exception;

/**
 * 未登录异常
 * Created by zchuanzhao on 2017/11/15.
 */
public class NotLoginException extends Exception {

    public NotLoginException(){
        super("请先登录");
    }
}
