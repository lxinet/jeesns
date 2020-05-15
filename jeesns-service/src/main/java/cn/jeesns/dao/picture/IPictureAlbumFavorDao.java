package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureAlbumFavor;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPictureAlbumFavorDao extends BaseMapper<PictureAlbumFavor> {
    PictureAlbumFavor find(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);
}