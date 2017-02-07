package com.lxinet.jeesns.modules.weibo.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.weibo.entity.Weibo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public interface IWeiboDao extends IBaseDao<Weibo> {

    List<Weibo> listByPage(@Param("memberId") int memberId);

    int save(Weibo weibo);

    int delete(int id);

    List<Weibo> hotList();
}
