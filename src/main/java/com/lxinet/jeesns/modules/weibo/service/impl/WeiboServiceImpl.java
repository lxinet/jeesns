package com.lxinet.jeesns.modules.weibo.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.utils.ConfigUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.entity.Config;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import com.lxinet.jeesns.modules.weibo.dao.IWeiboDao;
import com.lxinet.jeesns.modules.weibo.entity.Weibo;
import com.lxinet.jeesns.modules.weibo.service.IWeiboCommentService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboFavorService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    public Weibo findById(int id,int memberId) {
        return weiboDao.findById(id,memberId);
    }

    @Override
    public ResponseModel save(Member loginMember, String content) {
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
        weibo.setType(0);
        weibo.setContent(content);
        weibo.setStatus(1);
        if(weiboDao.save(weibo) == 1){
            return new ResponseModel(1,"发布成功");
        }
        return new ResponseModel(-1,"发布失败");
    }

    @Override
    public ResponseModel<Weibo> listByPage(Page page, int memberId,int loginMemberId) {
        PageInterceptor.startPage(page);
        List<Weibo> list = weiboDao.listByPage(memberId,loginMemberId);
        ResponseModel model = new ResponseModel(0,PageInterceptor.endPage());
        model.setData(list);
        return model;
    }

    @Transactional
    @Override
    public ResponseModel delete(int id) {
        if(weiboDao.delete(id) > 0){
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

    @Transactional
    @Override
    public ResponseModel userDelete(Member loginMember, int id) {
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        Weibo weibo = this.findById(id,loginMember.getId());
        if(weibo == null){
            return new ResponseModel(-1,"微博不存在");
        }
        if(loginMember.getId() != weibo.getMember().getId()){
            return new ResponseModel(-1,"没有权限");
        }
        if(weiboDao.delete(id) > 0){
            return new ResponseModel(0,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

    @Override
    public List<Weibo> hotList(int loginMemberId) {
        return weiboDao.hotList(loginMemberId);
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
        }else {
            //减少
            int num = weiboDao.favor(weiboId,-1);
            weiboFavorService.delete(weiboId,loginMember.getId());
            message = "取消赞成功";
            responseModel = new ResponseModel(1,message);
        }
        Weibo findWeibo = this.findById(weiboId,loginMember.getId());
        responseModel.setData(findWeibo.getFavor());
        return responseModel;
    }
}
