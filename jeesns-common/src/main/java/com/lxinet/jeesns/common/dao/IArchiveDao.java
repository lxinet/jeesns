package com.lxinet.jeesns.common.dao;

import com.lxinet.jeesns.common.entity.Archive;
import com.lxinet.jeesns.core.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * 文章DAO接口
 * Created by zchuanzhao on 2016/9/26.
 */
public interface IArchiveDao extends IBaseDao<Archive> {

    Archive findByArchiveId(@Param("archiveId") Integer archiveId);

    /**
     * 更新阅读数
     * @param archiveId
     * @return
     */
    int updateViewCount(@Param("archiveId") int archiveId);

    int favor(@Param("archiveId") int archiveId, @Param("num") int num);
    
}