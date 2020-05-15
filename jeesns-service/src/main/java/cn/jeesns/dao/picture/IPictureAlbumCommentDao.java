package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureAlbumComment;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPictureAlbumCommentDao extends BaseMapper<PictureAlbumComment> {
    List<PictureAlbumComment> listByPictureAlbum(@Param("page") Page page, @Param("pictureAlbumId") Integer pictureAlbumId);

    int deleteByPictureAlbum(@Param("pictureAlbumId") Integer pictureAlbumId);

    PictureAlbumComment findById(@Param("id") Integer id);
}