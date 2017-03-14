package com.lxinet.jeesns.modules.group.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.group.service.IGroupService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/";
    @Resource
    private IGroupService groupService;

    @RequestMapping(value = "${managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = groupService.listByPage(status,page,key);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel response = groupService.delete(loginMember,id);
        return response;
    }

    @RequestMapping(value = "${managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object changeStatus(@PathVariable("id") int id){
        ResponseModel response = groupService.changeStatus(id);
        return response;
    }



}
