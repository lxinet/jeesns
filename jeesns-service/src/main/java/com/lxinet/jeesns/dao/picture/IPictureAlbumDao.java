package com.lxinet.jeesns.dao.picture;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.picture.Picture;
import com.lxinet.jeesns.model.picture.PictureAlbum;
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