package com.lxinet.jeesns.core.enums;


import com.lxinet.jeesns.core.consts.MessageField;
import com.lxinet.jeesns.core.utils.LocaleUtil;
import com.lxinet.jeesns.core.utils.StringUtils;

/**
 * @author zchuanzhao
 */
public enum Messages {

    //参数错误
    PARAM_ERROR(-1001, MessageField.PARAM_ERROR),
    USERNAME_NOT_EMPTY(-1001, MessageField.USERNAME_NOT_EMPTY),
    PASSWORD_NOT_EMPTY(-1001, MessageField.PASSWORD_NOT_EMPTY),
    LOGIN_NAME_NOT_EMPTY(-1001, MessageField.LOGIN_NAME_NOT_EMPTY),
    TOKEN_NOT_EMPTY(-1001, MessageField.LOGIN_NAME_NOT_EMPTY),
    NAME_NOT_EMPTY(-1001, MessageField.NAME_NOT_EMPTY),
    CATEGORY_MUST_BE_SELECT(-1001, MessageField.CATEGORY_MUST_BE_SELECT),
    PRICE_IS_ERROR(-1001, MessageField.PRICE_IS_ERROR),
    STOCK_MUST_BE_INTEGER(-1001, MessageField.STOCK_MUST_BE_INTEGER),
    VIRTUALSELLNUM_MUST_BE_INTEGER(-1001, MessageField.VIRTUALSELLNUM_MUST_BE_INTEGER),

    //不存在
    ADMIN_NOT_EXISTS(-1002, MessageField.ADMIN_NOT_EXISTS),
    USER_NOT_EXISTS(-1002, MessageField.USER_NOT_EXISTS),
    GOODS_CATEGORY_NOT_EXISTS(-1002, MessageField.GOODS_CATEGORY_NOT_EXISTS),
    GOODS_NOT_EXISTS(-1002, MessageField.GOODS_NOT_EXISTS),
    AD_NOT_EXISTS(-1002, MessageField.AD_NOT_EXISTS),
    PARENT_CATE_NOT_EXISTS(-1002, MessageField.PARENT_CATE_NOT_EXISTS),

    //已存在
    EXISTS_LOWER_CATEGORY(-1003, MessageField.EXISTS_LOWER_CATEGORY),

    //具体业务错误
    UN_LOGIN(-1002, MessageField.UN_LOGIN),
    LOGIN_INFO_WRONG(-1003, MessageField.LOGIN_INFO_WRONG),
    ACCOUNT_IS_DISABLED(-1004, MessageField.ACCOUNT_IS_DISABLED),
    USERNAME_EXISTS(-1005, MessageField.USERNAME_EXISTS),
    USERNAME_LENGTH_ONLY_BE(-1006, MessageField.USERNAME_LENGTH_ONLY_BE, 5, 32),
    PARENT_CONNOT_BE_SELF(-1007, MessageField.PARENT_CONNOT_BE_SELF),
    ONLY_TOP_CATE_CAN_ADD(-1007, MessageField.ONLY_TOP_CATE_CAN_ADD),
    DELETE_SUB_CATE_FIRST(-1007, MessageField.DELETE_SUB_CATE_FIRST),
    CATE_NOT_EXISTS(-1007, MessageField.CATE_NOT_EXISTS),




    //其他未知错误
    ERROR(-1, MessageField.OPERATE_ERROR),
    //成功
    SUCCESS(0,MessageField.OPERATE_SUCCESS);

    private int code;
    private String message;

    Messages(int code, String messageKey, Object... args) {
        this.code = code;
        if (StringUtils.isNotEmpty(messageKey)) {
            this.message = LocaleUtil.getMessageSource().getMessage(messageKey, args, LocaleUtil.getLocale());
        } else {
            this.message = messageKey;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
