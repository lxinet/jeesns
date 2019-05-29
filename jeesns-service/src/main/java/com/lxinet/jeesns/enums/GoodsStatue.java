package com.lxinet.jeesns.enums;


/**
 * 商城状态
 * Created by zchuanzhao on 2019/5/29.
 */
public enum  GoodsStatue {

    /**
     * 上架
     */
    ENABLED(1),

    /**
     * 下架
     */
    DISABLED(0);

    private int status;

    GoodsStatue(int status){
        this.status = status;
    }

    public int value(){
        return status;
    }

}
