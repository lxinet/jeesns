package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.ErrorUtil;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.system.ActionLog;
import com.lxinet.jeesns.service.cms.ArticleService;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.service.common.LinkService;
import com.lxinet.jeesns.service.group.GroupFansService;
import com.lxinet.jeesns.service.group.GroupService;
import com.lxinet.jeesns.service.group.GroupTopicService;
import com.lxinet.jeesns.service.member.MemberFansService;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.service.weibo.WeiboService;
import com.lxinet.jeesns.utils.EmojiUtil;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiIndexController")
@RequestMapping("/api/")
public class IndexController extends BaseController {
    @Resource
    private ArticleService articleService;
    @Resource
    private GroupTopicService groupTopicService;
    @Resource
    private GroupService groupService;
    @Resource
    private WeiboService weiboService;
    @Resource
    private MemberService memberService;
    @Resource
    private ArchiveService archiveService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private GroupFansService groupFansService;
    @Resource
    private MemberFansService memberFansService;
    @Resource
    private LinkService linkService;
    @Resource
    private JwtUtil jwtUtil;


    @GetMapping(value = "u/{id}")
    public String u(@PathVariable("id") Integer id, Model model){
        Page page = new Page(request);
        Member member = memberService.findById(id);
        if(member == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1005, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("member",member);
        Member loginMember = jwtUtil.getMember(request);
        model.addAttribute("loginMember", loginMember);
        Result<ActionLog> list = actionLogService.memberActionLog(page,id);
        model.addAttribute("actionLogModel",list);
        return jeesnsConfig.getFrontTemplate() + "/u";
    }

    @GetMapping(value = "u/{id}/home/{type}")
    public String home(@PathVariable("id") Integer id, @PathVariable("type") String type, Model model){
        Page page = new Page(request);
        Member member = memberService.findById(id);
        if(member == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1005, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("member",member);

        Member loginMember = jwtUtil.getMember(request);
        model.addAttribute("loginMember", loginMember);
        int loginMemberId = 0;
        if(loginMember != null){
            loginMemberId = loginMember.getId().intValue();
        }
        if("article".equals(type)){
            model.addAttribute("model", articleService.listByPage(page,"",0,1, id));
        } else if("groupTopic".equals(type)){
            model.addAttribute("model", groupTopicService.listByPage(page,"",0,1, id,0));
        } else if("group".equals(type)){
            model.addAttribute("model", groupFansService.listByMember(page, id));
        } else if("weibo".equals(type)){
            model.addAttribute("model", weiboService.listByPage(page,id,loginMemberId,""));
        } else if("follows".equals(type)){
            model.addAttribute("model", memberFansService.followsList(page,id));
        } else if("fans".equals(type)){
            model.addAttribute("model", memberFansService.fansList(page,id));
        }
        model.addAttribute("type",type);
        return jeesnsConfig.getFrontTemplate() + "/home";
    }


    /**
     * 获取Emoji数据
     * @return
     */
    @GetMapping(value="/emoji/emojiJsonData.json")
    public Object emojiJsonData(){
        return EmojiUtil.emojiJson();
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @GetMapping(value={"/link"})
    public String link(Model model) {
        Result linkModel = linkService.allList();
        model.addAttribute("linkModel",linkModel);
        return jeesnsConfig.getFrontTemplate() + "/link";
    }
}
