package com.lxinet.jeesns.common.dao;

import com.lxinet.jeesns.common.entity.ArchiveFavor;
import com.lxinet.jeesns.core.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * 文章点赞DAO接口
 * Created by zchuanzhao on 2017/2/9.
 */
public interface IArchiveFavorDao extends IBaseDao<ArchiveFavor> {

    ArchiveFavor find(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer save(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer delete(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);
}