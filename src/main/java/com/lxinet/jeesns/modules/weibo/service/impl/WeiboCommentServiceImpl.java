package com.lxinet.jeesns.modules.weibo.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.modules.cms.dao.IArticleCommentDao;
import com.lxinet.jeesns.modules.cms.dao.IArticleDao;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.cms.entity.ArticleComment;
import com.lxinet.jeesns.modules.cms.service.IArticleCommentService;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
import com.lxinet.jeesns.modules.weibo.dao.IWeiboCommentDao;
import com.lxinet.jeesns.modules.weibo.entity.Weibo;
import com.lxinet.jeesns.modules.weibo.entity.WeiboComment;
import com.lxinet.jeesns.modules.weibo.service.IWeiboCommentService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
    private IScoreRuleService scoreRuleService;

    @Override
    public WeiboComment findById(int id) {
        WeiboComment weiboComment = weiboCommentDao.findById(id);
        WeiboCommentUtil.format(weiboComment);
        return weiboComment;
    }

    @Override
    public ResponseModel save(Member loginMember, String content, Integer weiboId) {
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
        int result = weiboCommentDao.save(weiboComment);
        if(result == 1){
            //微博评论奖励
            scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.COMMENT_WEIBO, weiboComment.getId());
            return new ResponseModel(1,"评论成功");
        }else {
            return new ResponseModel(-1,"评论失败");
        }
    }

    @Override
    public ResponseModel listByWeibo(Page page, int weiboId) {
        List<WeiboComment> list = weiboCommentDao.listByWeibo(page, weiboId);
        for (WeiboComment weiboComment : list){
            WeiboCommentUtil.format(weiboComment);
        }
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByWeibo(Integer weiboId) {
        weiboCommentDao.deleteByWeibo(weiboId);
    }

    @Override
    public ResponseModel delete(Member loginMember,int id) {
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

}
