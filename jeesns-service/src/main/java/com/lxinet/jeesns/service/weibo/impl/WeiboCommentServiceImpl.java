package com.lxinet.jeesns.service.weibo.impl;

import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.Weibo;
import com.lxinet.jeesns.model.weibo.WeiboComment;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IMessageService;
import com.lxinet.jeesns.service.member.IScoreDetailService;
import com.lxinet.jeesns.service.system.IActionLogService;
import com.lxinet.jeesns.dao.weibo.IWeiboCommentDao;
import com.lxinet.jeesns.service.weibo.IWeiboCommentService;
import com.lxinet.jeesns.service.weibo.IWeiboService;
import com.lxinet.jeesns.common.utils.ActionUtil;
import com.lxinet.jeesns.common.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/22.
 */
@Service("weiboCommentService")
public class WeiboCommentServiceImpl implements IWeiboCommentService {
    @Resource
    private IWeiboCommentDao weiboCommentDao;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public WeiboComment findById(int id) {
        WeiboComment weiboComment = weiboCommentDao.findById(id);
        atFormat(weiboComment);
        return weiboComment;
    }

    @Override
    public ResponseModel save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId) {
        Weibo weibo = weiboService.findById(weiboId,loginMember.getId());
        if(weibo == null){
            return new ResponseModel(-1,"微博不存在");
        }
        if(StringUtils.isEmpty(content)){
            return new ResponseModel(-1,"内容不能为空");
        }
        if(content.length() > 500){
            return new ResponseModel(-1,"评论内容不能超过500长度");
        }
        WeiboComment weiboComment = new WeiboComment();
        weiboComment.setMemberId(loginMember.getId());
        weiboComment.setWeiboId(weiboId);
        weiboComment.setContent(content);
        weiboComment.setCommentId(weiboCommentId);
        int result = weiboCommentDao.save(weiboComment);
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
            return new ResponseModel(1,"评论成功");
        }else {
            return new ResponseModel(-1,"评论失败");
        }
    }

    @Override
    public ResponseModel listByWeibo(Page page, int weiboId) {
        List<WeiboComment> list = weiboCommentDao.listByWeibo(page, weiboId);
        atFormat(list);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByWeibo(Integer weiboId) {
        weiboCommentDao.deleteByWeibo(weiboId);
    }

    @Override
    public ResponseModel delete(Member loginMember, int id) {
        WeiboComment weiboComment = this.findById(id);
        if(weiboComment == null){
            return new ResponseModel(-1,"评论不存在");
        }
        int result = weiboCommentDao.delete(id);
        if(result == 1){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO_COMMENT,"ID："+weiboComment.getId()+"内容："+weiboComment.getContent());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
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
