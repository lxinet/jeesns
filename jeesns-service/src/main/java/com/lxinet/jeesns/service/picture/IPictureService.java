package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.Picture;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
public interface IPictureService {

    List<Picture> find(Integer foreignId);

    Picture findById(Integer pictureId,int loginMemberId);

    ResponseModel<Picture> listByPage(Page page,int loginMemberId);

    ResponseModel<Picture> listByAlbum(Page page, Integer pictureAlbumId,int loginMemberId);

    int delete(HttpServletRequest request, Integer foreignId);

    int save(Picture picture);

    int update(Integer foreignId, String ids,String description);

    ResponseModel favor(Member loginMember, int pictureId);
}