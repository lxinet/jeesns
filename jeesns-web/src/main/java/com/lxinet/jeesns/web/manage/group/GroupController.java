package com.lxinet.jeesns.web.manage.group;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.group.GroupService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/";
    @Resource
    private GroupService groupService;

    @RequestMapping(value = "${managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        List<Group> list = groupService.list(status,key);
        model.addAttribute("list",list);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(groupService.delete(loginMember,id));
    }

    @RequestMapping(value = "${managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel changeStatus(@PathVariable("id") int id){
        return new ResultModel(groupService.changeStatus(id));
    }



}
