package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.picture.IPictureCommentDao;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.picture.Picture;
import com.lxinet.jeesns.model.picture.PictureComment;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.MessageService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/11/14.
 */
@Service("pictureCommentService")
public class PictureCommentService {
    @Resource
    private IPictureCommentDao pictureCommentDao;
    @Resource
    private PictureService pictureService;
    @Resource
    private MessageService messageService;
    @Resource
    private MemberService memberService;

    public PictureComment findById(int id) {
        PictureComment PictureComment = pictureCommentDao.findById(id);
        atFormat(PictureComment);
        return PictureComment;
    }

    public boolean save(Member loginMember, String content, Integer pictureId) {
        Picture picture = pictureService.findById(pictureId,loginMember.getId());
        ValidUtill.checkIsNull(picture, Messages.PICTURE_NOT_EXISTS);
        PictureComment pictureComment = new PictureComment();
        pictureComment.setMemberId(loginMember.getId());
        pictureComment.setPictureId(pictureId);
        pictureComment.setContent(content);
        int result = pictureCommentDao.saveObj(pictureComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.PICTURE, MessageType.PICTURE_COMMENT_REFER,picture.getId());
            //回复微博发送系统信息
            messageService.diggDeal(loginMember.getId(), picture.getMemberId(), content,AppTag.PICTURE, MessageType.PICTURE_REPLY, picture.getId());
        }
        return result == 1;
    }

    public Result listByPicture(Page page, int pictureId) {
        List<PictureComment> list = pictureCommentDao.listByPicture(page, pictureId);
        atFormat(list);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public void deleteByPicture(Integer pictureId) {
        pictureCommentDao.deleteByPicture(pictureId);
    }

    public boolean delete(Member loginMember, int id) {
        PictureComment pictureComment = this.findById(id);
        ValidUtill.checkIsNull(pictureComment, "评论不存在");
        int result = pictureCommentDao.deleteById(id, PictureComment.class);
        return result == 1;
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
