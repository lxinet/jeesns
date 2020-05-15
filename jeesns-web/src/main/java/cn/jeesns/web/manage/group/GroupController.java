package cn.jeesns.web.manage.group;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.group.Group;
import cn.jeesns.model.member.Member;
import cn.jeesns.service.group.GroupService;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
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

    @RequestMapping(value = "${jeesns.managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        List<Group> list = groupService.list(status,key);
        model.addAttribute("list",list);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${jeesns.managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.delete(loginMember,id));
    }

    @RequestMapping(value = "${jeesns.managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result changeStatus(@PathVariable("id") int id){
        return new Result(groupService.changeStatus(id));
    }



}
