package com.lxinet.jeesns.common.web;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.Emoji;
import com.lxinet.jeesns.core.utils.ErrorUtil;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.group.service.IGroupService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController extends BaseController{
    private static final String FTL_PATH = "/index/";
    @Resource
    private IArticleService articleService;
    @Resource
    private IGroupTopicService groupTopicService;
    @Resource
    private IGroupService groupService;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IArchiveService archiveService;

    @RequestMapping(value="index",method = RequestMethod.GET)
    public String index(String key,Integer cateid,Model model) {
        Page page = new Page(request);
        if(cateid == null){
            cateid = 0;
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResponseModel articleModel = articleService.listByPage(page,key,cateid,1);
        ResponseModel groupTopicModel = groupTopicService.listByPage(page,key,cateid,1);
        ResponseModel groupModel = groupService.listByPage(1,page,key);
        page.setPageSize(50);
        ResponseModel weiboModel = weiboService.listByPage(page,0,loginMemberId);
        model.addAttribute("articleModel",articleModel);
        model.addAttribute("groupTopicModel",groupTopicModel);
        model.addAttribute("groupModel",groupModel);
        model.addAttribute("weiboModel",weiboModel);
        return FTL_PATH + "index";
    }

    @RequestMapping(value = "u/{id}",method = RequestMethod.GET)
    public String u(@PathVariable("id") Integer id, Model model){
        Page page = new Page(request);
        Member member = memberService.findById(id);
        if(member == null){
            return ErrorUtil.error(model,-1005, Const.INDEX_ERROR_FTL_PATH);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResponseModel weiboModel = weiboService.listByPage(page,id,loginMemberId);
        model.addAttribute("weiboModel",weiboModel);
        model.addAttribute("member",member);
        return FTL_PATH + "u";
    }


    @RequestMapping(value = "newVersion",method = RequestMethod.GET)
    @ResponseBody
    public String newVersion(@RequestParam("callback") String callback){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("LAST_SYSTEM_VERSION",Const.LAST_SYSTEM_VERSION);
        jsonObject.put("LAST_SYSTEM_UPDATE_TIME",Const.LAST_SYSTEM_UPDATE_TIME);
        return callback + "(" + jsonObject.toString() + ")";
    }

    /**
     * 文档、喜欢
     * @param archiveId
     * @return
     */
    @RequestMapping(value="/archive/favor/{archiveId}",method = RequestMethod.GET)
    @ResponseBody
    public Object favor(@PathVariable("archiveId") Integer archiveId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        if(archiveId == null) {
            return new ResponseModel(-1, "非法操作");
        }
        return archiveService.favor(loginMember,archiveId);
    }

    /**
     * 获取Emoji数据
     * @return
     */
    @RequestMapping(value="/emoji/emojiJsonData.json",method = RequestMethod.GET)
    @ResponseBody
    public Object emojiJsonData(){
        return Emoji.emojiJson();
    }

}
