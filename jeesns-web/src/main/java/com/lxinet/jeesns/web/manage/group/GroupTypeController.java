package com.lxinet.jeesns.web.manage.group;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.group.GroupType;
import com.lxinet.jeesns.service.group.GroupTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Controller("manageGroupTypeController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupTypeController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/type/";
    @Resource
    private GroupTypeService groupTypeService;

    @RequestMapping(value = "${jeesns.managePath}/group/type/list")
    public String index(Model model) {
        List<GroupType> list = groupTypeService.list();
        model.addAttribute("list", list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "${jeesns.managePath}/group/type/add", method = RequestMethod.GET)
    public Object add() {
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value = "${jeesns.managePath}/group/type/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(GroupType groupType) {
        return new Result(groupTypeService.save(groupType));
    }


    @RequestMapping(value = "${jeesns.managePath}/group/type/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        GroupType groupType = groupTypeService.findById(id);
        model.addAttribute("groupType", groupType);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "${jeesns.managePath}/group/type/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(GroupType groupType) {
        return new Result(groupTypeService.update(groupType));
    }


    @RequestMapping(value = "${jeesns.managePath}/group/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") int id) {
        return new Result(groupTypeService.delete(id));
    }


}
