package com.lxinet.jeesns.dao.picture;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.picture.PictureFavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPictureFavorDao extends BaseMapper<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}