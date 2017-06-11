package com.lxinet.jeesns.weibo.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.weibo.model.WeiboComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微博评论DAO接口
 * Created by zchuanzhao on 16/12/22.
 */
public interface IWeiboCommentDao extends IBaseDao<WeiboComment> {

    List<WeiboComment> listByWeibo(@Param("page") Page page, @Param("weiboId") Integer weiboId);

    /**
     * 根据微博ID删除评论
     * @param weiboId
     * @return
     */
    int deleteByWeibo(@Param("weiboId") Integer weiboId);
}