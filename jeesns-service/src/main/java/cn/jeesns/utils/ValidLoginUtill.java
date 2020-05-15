package cn.jeesns.utils;

import cn.jeesns.core.enums.Messages;
import cn.jeesns.core.exception.NotLoginException;
import cn.jeesns.core.exception.ParamException;
import cn.jeesns.core.utils.StringUtils;
import cn.jeesns.model.member.Member;

/**
 * @author zchuanzhao@linewell.com
 * 2018/8/2
 */
public class ValidLoginUtill {

    public static void checkLogin(Member member){
        if (null == member){
            throw new NotLoginException();
        }
    }

    public static void checkParam(Boolean... boos){
        for (boolean boo :boos){
            if (!boo){
                throw new ParamException();
            }
        }
    }

    public static void checkIsNull(Object obj){
        if (null == obj){
            throw new ParamException();
        }
    }

    public static void checkIsNull(Object obj, Messages messages){
        if (null == obj){
            throw new ParamException(messages);
        }
    }
    public static void checkIsBlank(String val, Messages messages){
        if (StringUtils.isBlank(val)){
            throw new ParamException(messages);
        }
    }
}
