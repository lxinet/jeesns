package com.lxinet.jeesns.dao.picture;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.picture.PictureComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPictureCommentDao extends BaseMapper<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);

    PictureComment findById(@Param("pictureId") Integer pictureId);
}