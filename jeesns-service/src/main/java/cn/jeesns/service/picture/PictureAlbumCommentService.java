package cn.jeesns.service.picture;

import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.enums.Messages;
import cn.jeesns.core.model.Page;
import cn.jeesns.dao.picture.IPictureAlbumCommentDao;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.picture.PictureAlbum;
import cn.jeesns.model.picture.PictureAlbumComment;
import cn.jeesns.service.member.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
@Service("pictureAlbumCommentService")
public class PictureAlbumCommentService {
    @Resource
    private IPictureAlbumCommentDao pictureAlbumCommentDao;
    @Resource
    private PictureAlbumService pictureAlbumService;
    @Resource
    private MemberService memberService;

    public PictureAlbumComment findById(int id) {
        PictureAlbumComment pictureAlbumComment = pictureAlbumCommentDao.findById(id);
        atFormat(pictureAlbumComment);
        return pictureAlbumComment;
    }

    public boolean save(Member loginMember, String content, Integer pictureAlbumId) {
        PictureAlbum pictureAlbum = pictureAlbumService.findById(pictureAlbumId);
        ValidUtill.checkIsNull(pictureAlbum, Messages.PICTURE_ALBUM_NOT_EXISTS);
        PictureAlbumComment pictureAlbumComment = new PictureAlbumComment();
        pictureAlbumComment.setMemberId(loginMember.getId());
        pictureAlbumComment.setPictureAlbumId(pictureAlbumId);
        pictureAlbumComment.setContent(content);
        int result = pictureAlbumCommentDao.saveObj(pictureAlbumComment);
        return result == 1;
    }

    public Result listByPictureAlbum(Page page, int pictureAlbumId) {
        List<PictureAlbumComment> list = pictureAlbumCommentDao.listByPictureAlbum(page, pictureAlbumId);
        atFormat(list);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public void deleteByPictureAlbum(Integer pictureAlbumId) {
        pictureAlbumCommentDao.deleteByPictureAlbum(pictureAlbumId);
    }

    public boolean delete(Member loginMember, int id) {
        PictureAlbumComment pictureAlbumComment = this.findById(id);
        ValidUtill.checkIsNull(pictureAlbumComment, "评论不存在");
        int result = pictureAlbumCommentDao.deleteById(id, PictureAlbumComment.class);
        return result == 1;
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
