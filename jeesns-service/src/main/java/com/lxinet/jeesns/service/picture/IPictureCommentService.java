package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.PictureComment;


/**
 * Created by zchuanzhao on 2017/11/14.
 */
public interface IPictureCommentService {

    PictureComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer pictureId);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByPicture(Page page, int pictureId);

    void deleteByPicture(Integer pictureId);
}
