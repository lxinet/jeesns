package com.lxinet.jeesns.dao.picture;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.picture.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
@Mapper
public interface IPictureDao extends BaseMapper<Picture> {

    List<Picture> find(@Param("foreignId") Integer foreignId);

    Picture findById(@Param("id") Integer id,@Param("loginMemberId") Integer loginMemberId);

    List<Picture> list(@Param("page") Page page,@Param("loginMemberId") Integer loginMemberId);

    List<Picture> listByAlbum(@Param("page") Page page, @Param("albumId") Integer albumId,@Param("loginMemberId") Integer loginMemberId);

    int deleteByForeignId(@Param("id") Integer foreignId);

    int update(@Param("foreignId") Integer foreignId, @Param("ids") String[] ids,@Param("description") String description);

    int favor(@Param("id") Integer id,@Param("num") Integer num);
}