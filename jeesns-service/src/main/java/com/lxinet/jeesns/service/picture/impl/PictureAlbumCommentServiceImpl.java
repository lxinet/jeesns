package com.lxinet.jeesns.service.picture.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.picture.IPictureAlbumCommentDao;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.PictureAlbum;
import com.lxinet.jeesns.model.picture.PictureAlbumComment;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.picture.IPictureAlbumCommentService;
import com.lxinet.jeesns.service.picture.IPictureAlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
@Service("pictureAlbumCommentService")
public class PictureAlbumCommentServiceImpl implements IPictureAlbumCommentService {
    @Resource
    private IPictureAlbumCommentDao pictureAlbumCommentDao;
    @Resource
    private IPictureAlbumService pictureAlbumService;
    @Resource
    private IMemberService memberService;

    @Override
    public PictureAlbumComment findById(int id) {
        PictureAlbumComment pictureAlbumComment = pictureAlbumCommentDao.findById(id);
        atFormat(pictureAlbumComment);
        return pictureAlbumComment;
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer pictureAlbumId) {
        PictureAlbum pictureAlbum = pictureAlbumService.findById(pictureAlbumId);
        if(pictureAlbum == null){
            return new ResultModel(-1,"相册不存在");
        }
        PictureAlbumComment pictureAlbumComment = new PictureAlbumComment();
        pictureAlbumComment.setMemberId(loginMember.getId());
        pictureAlbumComment.setPictureAlbumId(pictureAlbumId);
        pictureAlbumComment.setContent(content);
        int result = pictureAlbumCommentDao.save(pictureAlbumComment);
        if(result == 1){
            return new ResultModel(0,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByPictureAlbum(Page page, int pictureAlbumId) {
        List<PictureAlbumComment> list = pictureAlbumCommentDao.listByPictureAlbum(page, pictureAlbumId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByPictureAlbum(Integer pictureAlbumId) {
        pictureAlbumCommentDao.deleteByPictureAlbum(pictureAlbumId);
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        PictureAlbumComment pictureAlbumComment = this.findById(id);
        if(pictureAlbumComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = pictureAlbumCommentDao.delete(id);
        if(result == 1){
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public PictureAlbumComment atFormat(PictureAlbumComment pictureAlbumComment){
        pictureAlbumComment.setContent(memberService.atFormat(pictureAlbumComment.getContent()));
        return pictureAlbumComment;
    }

    public List<PictureAlbumComment> atFormat(List<PictureAlbumComment> pictureAlbumCommentList){
        for (PictureAlbumComment pictureAlbumComment : pictureAlbumCommentList){
            atFormat(pictureAlbumComment);
        }
        return pictureAlbumCommentList;
    }
}
