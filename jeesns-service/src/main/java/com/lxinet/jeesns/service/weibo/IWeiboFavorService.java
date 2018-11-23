package com.lxinet.jeesns.service.weibo;


import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.weibo.WeiboFavor;

/**
 * 微博点赞Service接口
 * Created by zchuanzhao on 2017/2/8.
 */
public interface IWeiboFavorService extends IBaseService<WeiboFavor> {

    WeiboFavor find(Integer weiboId, Integer memberId);

    void save(Integer weiboId, Integer memberId);

    void delete(Integer weiboId, Integer memberId);
}