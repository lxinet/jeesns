package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.picture.PictureAlbum;

/**
 * Created by zchuanzhao on 2017/11/03.
 */
public interface IPictureAlbumService {

    ResponseModel<PictureAlbum> listByMember(Integer memberId);

    ResponseModel<PictureAlbum> listByPage(Page page);

    ResponseModel delete(Integer id);

    ResponseModel save(PictureAlbum pictureAlbum);

    ResponseModel update(PictureAlbum pictureAlbum);

    PictureAlbum findWeiboAlbum(Integer memberId);

    PictureAlbum findById(Integer id);
}