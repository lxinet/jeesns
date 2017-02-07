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
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Service;

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

    @Override
    public Weibo findById(int id) {
        return weiboDao.findById(id);
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
    public ResponseModel<Weibo> listByPage(Page page, int memberId) {
        PageInterceptor.startPage(page);
        List<Weibo> list = weiboDao.listByPage(memberId);
        ResponseModel model = new ResponseModel(0,PageInterceptor.endPage());
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel delete(int id) {
        if(weiboDao.delete(id) == 1){
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

    @Override
    public ResponseModel userDelete(Member loginMember, int id) {
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        Weibo weibo = this.findById(id);
        if(weibo == null){
            return new ResponseModel(-1,"微博不存在");
        }
        if(loginMember.getId() != weibo.getMember().getId()){
            return new ResponseModel(-1,"没有权限");
        }
        if(weiboDao.delete(id) == 1){
            return new ResponseModel(0,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

    @Override
    public List<Weibo> hotList() {
        return weiboDao.hotList();
    }
}
