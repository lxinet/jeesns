package com.lxinet.jeesns.modules.weibo.web.index;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.ErrorUtil;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.weibo.entity.Weibo;
import com.lxinet.jeesns.modules.weibo.service.IWeiboCommentService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/20.
 */
@Controller("weiboIndexController")
@RequestMapping("/weibo")
public class WeiboController extends BaseController {
    private static final String MEMBER_FTL_PATH = "/index/weibo/";
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IWeiboCommentService weiboCommentService;

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel publish(String content,String pictures){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return weiboService.save(loginMember,content, pictures);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResponseModel responseModel = weiboService.listByPage(page,0,loginMemberId,key);
        model.addAttribute("model",responseModel);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        return MEMBER_FTL_PATH + "list";
    }

    @RequestMapping(value = "/detail/{weiboId}",method = RequestMethod.GET)
    public String detail(@PathVariable("weiboId") Integer weiboId, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Weibo weibo = weiboService.findById(weiboId,loginMemberId);
        if(weibo == null){
            return ErrorUtil.error(model,1007, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("weibo",weibo);
        return MEMBER_FTL_PATH + "detail";
    }

    @RequestMapping(value="/delete/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = weiboService.userDelete(request, loginMember,weiboId);
        if(responseModel.getCode() >= 0){
            responseModel.setCode(2);
            responseModel.setUrl(request.getContextPath() + "/weibo/list");
        }
        return responseModel;
    }


    @RequestMapping(value="/comment/{weiboId}",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@PathVariable("weiboId") Integer weiboId, String content){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return weiboCommentService.save(loginMember,content,weiboId);
    }

    @RequestMapping(value="/commentList/{weiboId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Object commentList(@PathVariable("weiboId") Integer weiboId){
        Page page = new Page(request);
        if(weiboId == null){
            weiboId = 0;
        }
        return weiboCommentService.listByWeibo(page,weiboId);
    }

    @RequestMapping(value="/favor/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    public Object favor(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        if(weiboId == null) {
            return new ResponseModel(-1, "非法操作");
        }
        return weiboService.favor(loginMember,weiboId);
    }
}
