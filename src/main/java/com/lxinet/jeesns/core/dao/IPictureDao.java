package com.lxinet.jeesns.core.dao;

import com.lxinet.jeesns.core.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
public interface IPictureDao extends IBaseDao<Picture> {

    List<Picture> find(@Param("id") Integer foreignId);

    int delete(@Param("id") Integer foreignId);

    int update(@Param("foreignId") Integer foreignId,@Param("ids") String[] ids);
}