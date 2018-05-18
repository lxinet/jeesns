package com.lxinet.jeesns.service.picture.impl;

import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.picture.IPictureCommentDao;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.Picture;
import com.lxinet.jeesns.model.picture.PictureComment;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IMessageService;
import com.lxinet.jeesns.service.picture.IPictureCommentService;
import com.lxinet.jeesns.service.picture.IPictureService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/11/14.
 */
@Service("pictureCommentService")
public class PictureCommentServiceImpl implements IPictureCommentService {
    @Resource
    private IPictureCommentDao pictureCommentDao;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public PictureComment findById(int id) {
        PictureComment PictureComment = pictureCommentDao.findById(id);
        atFormat(PictureComment);
        return PictureComment;
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer pictureId) {
        Picture picture = pictureService.findById(pictureId,loginMember.getId());
        if(picture == null){
            return new ResultModel(-1,"图片不存在");
        }
        PictureComment pictureComment = new PictureComment();
        pictureComment.setMemberId(loginMember.getId());
        pictureComment.setPictureId(pictureId);
        pictureComment.setContent(content);
        int result = pictureCommentDao.save(pictureComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.PICTURE, MessageType.PICTURE_COMMENT_REFER,picture.getPictureId());
            //回复微博发送系统信息
            messageService.diggDeal(loginMember.getId(), picture.getMemberId(), content,AppTag.PICTURE, MessageType.PICTURE_REPLY, picture.getPictureId());
            return new ResultModel(0,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByPicture(Page page, int pictureId) {
        List<PictureComment> list = pictureCommentDao.listByPicture(page, pictureId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByPicture(Integer pictureId) {
        pictureCommentDao.deleteByPicture(pictureId);
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        PictureComment pictureComment = this.findById(id);
        if(pictureComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = pictureCommentDao.delete(id);
        if(result == 1){
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public PictureComment atFormat(PictureComment pictureComment){
        pictureComment.setContent(memberService.atFormat(pictureComment.getContent()));
        return pictureComment;
    }

    public List<PictureComment> atFormat(List<PictureComment> pictureCommentList){
        for (PictureComment pictureComment : pictureCommentList){
            atFormat(pictureComment);
        }
        return pictureCommentList;
    }
}
