package com.lxinet.jeesns.core.dao;

import com.lxinet.jeesns.core.entity.ArchiveFavor;
import com.lxinet.jeesns.core.entity.Picture;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
public interface IPictureDao extends IBaseDao<Picture> {

    Picture find(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer save(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer delete(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);
}