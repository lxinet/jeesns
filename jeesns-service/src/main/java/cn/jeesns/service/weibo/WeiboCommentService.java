package cn.jeesns.service.weibo;

import cn.jeesns.service.member.MemberService;
import cn.jeesns.service.member.MessageService;
import cn.jeesns.service.member.ScoreDetailService;
import cn.jeesns.service.system.ActionLogService;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.core.consts.AppTag;
import cn.jeesns.core.enums.MessageType;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.exception.ParamException;
import cn.jeesns.core.model.Page;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.weibo.Weibo;
import cn.jeesns.model.weibo.WeiboComment;
import cn.jeesns.dao.weibo.IWeiboCommentDao;
import cn.jeesns.utils.ActionUtil;
import cn.jeesns.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/22.
 */
@Service("weiboCommentService")
public class WeiboCommentService extends BaseService<WeiboComment> {
    @Resource
    private IWeiboCommentDao weiboCommentDao;
    @Resource
    private WeiboService weiboService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private MessageService messageService;
    @Resource
    private MemberService memberService;

    public WeiboComment findById(int id) {
        WeiboComment weiboComment = weiboCommentDao.findById(id);
        atFormat(weiboComment);
        return weiboComment;
    }

    public boolean save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId) {
        Weibo weibo = weiboService.findById(weiboId,loginMember.getId());
        ValidUtill.checkIsNull(weibo, "微博不存在");
        ValidUtill.checkIsBlank(content, "内容不能为空");
        if(content.length() > 500){
            throw new ParamException("评论内容不能超过500");
        }
        WeiboComment weiboComment = new WeiboComment();
        weiboComment.setMemberId(loginMember.getId());
        weiboComment.setWeiboId(weiboId);
        weiboComment.setContent(content);
        weiboComment.setCommentId(weiboCommentId);
        int result = weiboCommentDao.saveObj(weiboComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.WEIBO, MessageType.WEIBO_COMMENT_REFER,weibo.getId());
            //回复微博发送系统信息
            messageService.diggDeal(loginMember.getId(), weibo.getMemberId(), content,AppTag.WEIBO, MessageType.WEIBO_REPLY, weibo.getId());
            if (weiboCommentId != null){
                WeiboComment replyWeiboComment = this.findById(weiboCommentId);
                if (replyWeiboComment != null){
                    //回复微博发送系统信息
                    messageService.diggDeal(loginMember.getId(), replyWeiboComment.getMemberId(), content, AppTag.WEIBO, MessageType.WEIBO_REPLY_REPLY, replyWeiboComment.getId());
                }
            }
            //微博评论奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.COMMENT_WEIBO, weiboComment.getId());
        }
        return result == 1;
    }

    public Result listByWeibo(Page page, int weiboId) {
        List<WeiboComment> list = weiboCommentDao.listByWeibo(page, weiboId);
        atFormat(list);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public void deleteByWeibo(Integer weiboId) {
        weiboCommentDao.deleteByWeibo(weiboId);
    }

    public boolean delete(Member loginMember, int id) {
        WeiboComment weiboComment = this.findById(id);
        boolean result = super.deleteById(id);
        if(result){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO_COMMENT,"ID："+weiboComment.getId()+"内容："+weiboComment.getContent());
        }
        return result;
    }

    public WeiboComment atFormat(WeiboComment weiboComment){
        weiboComment.setContent(memberService.atFormat(weiboComment.getContent()));
        return weiboComment;
    }

    public List<WeiboComment> atFormat(List<WeiboComment> weiboCommentList){
        for (WeiboComment weiboComment : weiboCommentList){
            atFormat(weiboComment);
        }
        return weiboCommentList;
    }
}
