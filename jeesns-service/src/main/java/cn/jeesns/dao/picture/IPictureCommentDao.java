package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureComment;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPictureCommentDao extends BaseMapper<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);

    PictureComment findById(@Param("pictureId") Integer pictureId);
}