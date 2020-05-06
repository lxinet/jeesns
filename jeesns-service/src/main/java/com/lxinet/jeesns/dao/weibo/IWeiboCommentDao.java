package com.lxinet.jeesns.dao.weibo;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.weibo.WeiboComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微博评论DAO接口
 * Created by zchuanzhao on 16/12/22.
 */
@Mapper
public interface IWeiboCommentDao extends BaseMapper<WeiboComment> {

    WeiboComment findById(@Param("id") Integer id);

    List<WeiboComment> listByWeibo(@Param("page") Page page, @Param("weiboId") Integer weiboId);

    /**
     * 根据微博ID删除评论
     * @param weiboId
     * @return
     */
    int deleteByWeibo(@Param("weiboId") Integer weiboId);
}