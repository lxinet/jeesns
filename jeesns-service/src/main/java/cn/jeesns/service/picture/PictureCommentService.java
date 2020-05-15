package cn.jeesns.service.picture;

import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.core.consts.AppTag;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.enums.MessageType;
import cn.jeesns.core.enums.Messages;
import cn.jeesns.core.model.Page;
import cn.jeesns.dao.picture.IPictureCommentDao;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.picture.Picture;
import cn.jeesns.model.picture.PictureComment;
import cn.jeesns.service.member.MemberService;
import cn.jeesns.service.member.MessageService;
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
