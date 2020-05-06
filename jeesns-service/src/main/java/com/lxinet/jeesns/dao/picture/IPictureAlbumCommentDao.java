package com.lxinet.jeesns.dao.picture;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.picture.PictureAlbum;
import com.lxinet.jeesns.model.picture.PictureAlbumComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPictureAlbumCommentDao extends BaseMapper<PictureAlbumComment> {
    List<PictureAlbumComment> listByPictureAlbum(@Param("page") Page page, @Param("pictureAlbumId") Integer pictureAlbumId);

    int deleteByPictureAlbum(@Param("pictureAlbumId") Integer pictureAlbumId);

    PictureAlbumComment findById(@Param("id") Integer id);
}