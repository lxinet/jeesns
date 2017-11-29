package com.lxinet.jeesns.core.exception;

/**
 * 参数异常
 * Created by zchuanzhao on 2017/5/17.
 */
public class ParamException extends Exception {

    public ParamException(String msg){
        super(msg);
    }

    public ParamException(){
        super("参数异常");
    }
}
