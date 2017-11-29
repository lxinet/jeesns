package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.PictureAlbumComment;
import com.lxinet.jeesns.model.picture.PictureComment;


/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
public interface IPictureAlbumCommentService {

    PictureAlbumComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer pictureAlbumId);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByPictureAlbum(Page page, int pictureAlbumId);

    void deleteByPictureAlbum(Integer pictureAlbumId);
}
