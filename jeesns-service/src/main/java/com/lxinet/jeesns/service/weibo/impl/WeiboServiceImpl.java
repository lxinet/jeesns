package com.lxinet.jeesns.service.weibo.impl;

import com.lxinet.jeesns.common.utils.*;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.Weibo;
import com.lxinet.jeesns.service.common.IPictureService;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IMessageService;
import com.lxinet.jeesns.service.member.IScoreDetailService;
import com.lxinet.jeesns.service.system.IActionLogService;
import com.lxinet.jeesns.dao.weibo.IWeiboDao;
import com.lxinet.jeesns.service.weibo.IWeiboFavorService;
import com.lxinet.jeesns.service.weibo.IWeiboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Service("weiboService")
public class WeiboServiceImpl implements IWeiboService {
    @Resource
    private IWeiboDao weiboDao;
    @Resource
    private IWeiboFavorService weiboFavorService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public Weibo findById(int id, int memberId) {
        Weibo weibo = weiboDao.findById(id,memberId);
//        weibo = this.atFormat(weibo);
        return weibo;
    }

    @Override
    @Transactional
    public ResponseModel save(HttpServletRequest request, Member loginMember, String content, String pictures) {
        if("0".equals(request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST.toUpperCase()))){
            return new ResponseModel(-1,"微博已关闭");
        }
        if(StringUtils.isEmpty(content)){
            return new ResponseModel(-1,"内容不能为空");
        }
        if(content.length() > Integer.parseInt((String) request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase()))){
            return new ResponseModel(-1,"内容不能超过"+request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase())+"字");
        }
        Weibo weibo = new Weibo();
        weibo.setMemberId(loginMember.getId());
        weibo.setContent(content);
        weibo.setStatus(1);
        if(StringUtils.isEmpty(pictures)){
            //普通文本
            weibo.setType(0);
        }else {
            //图片
            weibo.setType(1);
        }
        if(weiboDao.save(weibo) == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.WEIBO, MessageType.WEIBO_REFER,weibo.getId());
            pictureService.update(weibo.getId(),pictures);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.POST_WEIBO,"", ActionLogType.WEIBO.getValue(),weibo.getId());
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.RELEASE_WEIBO, weibo.getId());
            return new ResponseModel(1,"发布成功");
        }
        return new ResponseModel(-1,"发布失败");
    }

    @Override
    public ResponseModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Weibo> list = weiboDao.listByPage(page, memberId,loginMemberId,key);
        list = this.atFormat(list);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Transactional
    @Override
    public ResponseModel delete(HttpServletRequest request, Member loginMember, int id) {
        Weibo weibo = this.findById(id,loginMember.getId());
        if(weibo == null){
            return new ResponseModel(-1,"微博不存在");
        }
        weiboDao.delete(id);
        //扣除积分
        scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.RELEASE_WEIBO,id);
        pictureService.delete(request, id);
        actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO, "ID："+weibo.getId()+"，内容："+weibo.getContent());
        return new ResponseModel(1,"操作成功");
    }

    @Transactional
    @Override
    public ResponseModel userDelete(HttpServletRequest request, Member loginMember, int id) {
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        Weibo weibo = this.findById(id,loginMember.getId());
        if(weibo == null){
            return new ResponseModel(-1,"微博不存在");
        }
        if(loginMember.getIsAdmin() == 0 && (loginMember.getId().intValue() != weibo.getMember().getId().intValue())){
            return new ResponseModel(-1,"没有权限");
        }
        return this.delete(request, loginMember,id);
    }

    @Override
    public List<Weibo> hotList(int loginMemberId) {
        List<Weibo> hotList = weiboDao.hotList(loginMemberId);
        return hotList;
    }

    @Transactional
    @Override
    public ResponseModel favor(Member loginMember, int weiboId) {
        String message;
        ResponseModel<Integer> responseModel;
        Weibo weibo = this.findById(weiboId,loginMember.getId());
        if(weiboFavorService.find(weiboId,loginMember.getId()) == null){
            //增加
            weiboDao.favor(weiboId,1);
            weiboFavorService.save(weiboId,loginMember.getId());
            message = "点赞成功";
            responseModel = new ResponseModel(0,message);
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP, weiboId);
            //点赞之后发送系统信息
            messageService.diggDeal(loginMember.getId(),weibo.getMemberId(),AppTag.WEIBO,MessageType.WEIBO_ZAN,weibo.getId());
        }else {
            //减少
            int num = weiboDao.favor(weiboId,-1);
            weiboFavorService.delete(weiboId,loginMember.getId());
            message = "取消赞成功";
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP,weiboId);
            responseModel = new ResponseModel(1,message);
        }
        responseModel.setData(weibo.getFavor());
        return responseModel;
    }

    @Override
    public List<Weibo> listByCustom(int loginMemberId, String sort, int num, int day) {
        return weiboDao.listByCustom(loginMemberId,sort,num,day);
    }

    public Weibo atFormat(Weibo weibo){
        weibo.setContent(memberService.atFormat(weibo.getContent()));
        return weibo;
    }

    public List<Weibo> atFormat(List<Weibo> weiboList){
        for (Weibo weibo : weiboList){
            atFormat(weibo);
        }
        return weiboList;
    }
}
