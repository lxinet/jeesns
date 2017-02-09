package com.lxinet.jeesns.modules.weibo.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.weibo.entity.WeiboFavor;
import org.apache.ibatis.annotations.Param;

/**
 * 微博点赞DAO接口
 * Created by zchuanzhao on 2017/2/8.
 */
public interface IWeiboFavorDao extends IBaseDao<WeiboFavor> {

    WeiboFavor find(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer save(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer delete(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);
}