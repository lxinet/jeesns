package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureFavor;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IPictureFavorDao extends BaseMapper<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}