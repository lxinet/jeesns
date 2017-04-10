package com.lxinet.jeesns.modules.weibo.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.service.IPictureService;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.entity.Config;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
import com.lxinet.jeesns.modules.weibo.dao.IWeiboDao;
import com.lxinet.jeesns.modules.weibo.entity.Weibo;
import com.lxinet.jeesns.modules.weibo.service.IWeiboCommentService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboFavorService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Service("weiboService")
public class WeiboServiceImpl implements IWeiboService {
    @Resource
    private IWeiboDao weiboDao;
    @Resource
    private IConfigService configService;
    @Resource
    private IWeiboCommentService weiboCommentService;
    @Resource
    private IWeiboFavorService weiboFavorService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IScoreRuleService scoreRuleService;

    @Override
    public Weibo findById(int id,int memberId) {
        Weibo weibo = weiboDao.findById(id,memberId);
        WeiboUtil.format(weibo);
        return weibo;
    }

    @Override
    @Transactional
    public ResponseModel save(Member loginMember, String content, String pictures) {
        Map<String,String> config = configService.getConfigToMap();
        if("0".equals(config.get(ConfigUtil.WEIBO_POST))){
            return new ResponseModel(-1,"微博已关闭");
        }
        if(StringUtils.isEmpty(content)){
            return new ResponseModel(-1,"内容不能为空");
        }
        if(content.length() > Integer.parseInt(config.get(ConfigUtil.WEIBO_POST_MAXCONTENT))){
            return new ResponseModel(-1,"内容不能超过"+config.get(ConfigUtil.WEIBO_POST_MAXCONTENT)+"字");
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
            pictureService.update(weibo.getId(),pictures);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.POST_WEIBO,"", ActionLogType.WEIBO.getValue(),weibo.getId());
            //发布微博奖励
            scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.RELEASE_WEIBO, weibo.getId());
            return new ResponseModel(1,"发布成功");
        }
        return new ResponseModel(-1,"发布失败");
    }

    @Override
    public ResponseModel<Weibo> listByPage(Page page, int memberId,int loginMemberId,String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Weibo> list = weiboDao.listByPage(page, memberId,loginMemberId,key);
        for (Weibo weibo : list){
            WeiboUtil.format(weibo);
        }
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
        scoreRuleService.scoreRuleCancelBonus(loginMember.getId(),ScoreRuleConsts.RELEASE_WEIBO,id);
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
        for (Weibo weibo : hotList){
            WeiboUtil.format(weibo);
        }
        return hotList;
    }

    @Transactional
    @Override
    public ResponseModel favor(Member loginMember, int weiboId) {
        String message;
        ResponseModel<Integer> responseModel;
        if(weiboFavorService.find(weiboId,loginMember.getId()) == null){
            //增加
            weiboDao.favor(weiboId,1);
            weiboFavorService.save(weiboId,loginMember.getId());
            message = "点赞成功";
            responseModel = new ResponseModel(0,message);
            //发布微博奖励
            scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP, weiboId);
        }else {
            //减少
            int num = weiboDao.favor(weiboId,-1);
            weiboFavorService.delete(weiboId,loginMember.getId());
            message = "取消赞成功";
            //扣除积分
            scoreRuleService.scoreRuleCancelBonus(loginMember.getId(),ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP,weiboId);
            responseModel = new ResponseModel(1,message);
        }
        Weibo findWeibo = this.findById(weiboId,loginMember.getId());
        responseModel.setData(findWeibo.getFavor());
        return responseModel;
    }
}
