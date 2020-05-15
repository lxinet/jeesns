package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureAlbum;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface IPictureAlbumDao extends BaseMapper<PictureAlbum> {

    List<PictureAlbum> listByMember(@Param("memberId") Integer memberId);

    List<PictureAlbum> list(@Param("page") Page page);

    PictureAlbum findWeiboAlbum(@Param("memberId") Integer memberId);

    PictureAlbum findById(@Param("id") Integer id);

    int delete(@Param("id") Integer id);
}