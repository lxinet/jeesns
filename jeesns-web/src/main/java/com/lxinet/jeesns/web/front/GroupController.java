package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.service.group.*;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.group.*;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/26.
 */
@Controller("frontGroupController")
@RequestMapping("/${jeesns.groupPath}")
public class GroupController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private GroupService groupService;
    @Resource
    private GroupTopicService groupTopicService;
    @Resource
    private GroupFansService groupFansService;
    @Resource
    private GroupTopicCommentService groupTopicCommentService;
    @Resource
    private GroupTopicTypeService groupTopicTypeService;
    @Resource
    private ArchiveService archiveService;
    @Resource
    private MemberService memberService;
    @Resource
    private GroupTypeService groupTypeService;

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(String key, Model model) {
        List<Group> list = groupService.list(1, key);
        model.addAttribute("list", list);
        model.addAttribute("key", key);
        return jeesnsConfig.getFrontTemplate() + "/group/index";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String apply(Model model) {
        List<GroupType> groupTypeList = groupTypeService.list();
        model.addAttribute("groupTypeList",groupTypeList);
        return jeesnsConfig.getFrontTemplate() + "/group/apply";
    }

    /**
     * 群组详情页面
     *
     * @param groupId
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{groupId}", method = RequestMethod.GET)
    public String detail(@PathVariable("groupId") Integer groupId, @RequestParam(value = "typeId", defaultValue = "0") Integer typeId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        group.setCreatorMember(memberService.findById(group.getCreator()));
        model.addAttribute("group", group);
        Member loginMember = MemberUtil.getLoginMember(request);
        int memberId = 0;
        if (loginMember != null) {
            memberId = loginMember.getId();
        }
        //判断是否已关注该群组
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId, memberId);
        if (groupFans == null) {
            model.addAttribute("isfollow", false);
        } else {
            model.addAttribute("isfollow", true);
        }
        //获取群组帖子列表
        Result result = groupTopicService.listByPage(page, null, groupId, 1, 0, typeId);
        model.addAttribute("model", result);
        String managerIds = group.getManagers();
        List<Member> managerList = new ArrayList<>();
        if (StringUtils.isNotEmpty(managerIds)) {
            String[] idArr = managerIds.split(",");
            for (String id : idArr) {
                Member member = memberService.findById(Integer.parseInt(id));
                if (member != null) {
                    managerList.add(member);
                }
            }
        }
        model.addAttribute("managerList", managerList);
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        int isManager = 0;
        boolean isManagerFlag = false;
        for (String manager : groupManagerArr) {
            if (loginMember.getId() == Integer.parseInt(manager)) {
                isManagerFlag = true;
                break;
            }
        }
        if (isManagerFlag || loginMember.getId().intValue() == group.getCreator().intValue()) {
            isManager = 1;
        }
        model.addAttribute("isManager", isManager);
        //获取群组粉丝列表,第一页，20条数据
        Page groupFansPage = new Page(1, 20);
        List<GroupFans> groupFansList = (List<GroupFans>) groupFansService.listByPage(groupFansPage, groupId).getData();
        List<GroupTopicType> groupTopicTypeList = groupTopicTypeService.list(groupId);
        model.addAttribute("groupFansList", groupFansList);
        model.addAttribute("groupTopicTypeList", groupTopicTypeList);
        model.addAttribute("loginUser", loginMember);
        model.addAttribute("typeId", typeId);
        return jeesnsConfig.getFrontTemplate() + "/group/detail";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result apply(Group group) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.save(loginMember, group));
    }


    @RequestMapping(value = "/edit/{groupId}", method = RequestMethod.GET)
    public String edit(@PathVariable("groupId") Integer groupId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        if (group.getCreator().intValue() != loginMember.getId().intValue()) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1001, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group", group);
        String managerIds = group.getManagers();
        String newManagerNames = "";
        if (StringUtils.isNotEmpty(managerIds)) {
            String[] idArr = managerIds.split(",");
            for (String id : idArr) {
                Member member = memberService.findById(Integer.parseInt(id));
                if (member != null) {
                    newManagerNames += member.getName() + ",";
                }
            }
            if (newManagerNames.length() > 0) {
                newManagerNames = newManagerNames.substring(0, newManagerNames.length() - 1);
            }
        }

        List<GroupType> groupTypeList = groupTypeService.list();
        model.addAttribute("groupTypeList",groupTypeList);
        model.addAttribute("managerNames", newManagerNames);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result update(Group group) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.update(loginMember, group));
    }

    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String topic(@PathVariable("topicId") Integer topicId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopic groupTopic = groupTopicService.findById(topicId, loginMember);
        if (groupTopic == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1004, Const.INDEX_ERROR_FTL_PATH);
        }
        groupTopic.setGroup(groupService.findById(groupTopic.getGroupId()));
        groupTopic.setMember(memberService.findById(groupTopic.getMemberId()));
        groupTopicService.updateViewCount(groupTopic.getId());
        model.addAttribute("groupTopic", groupTopic);

        Group group = groupService.findById(groupTopic.getGroup().getId());
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1000, Const.INDEX_ERROR_FTL_PATH);
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isfollow = false;
        int isManager = 0;
        boolean isManagerFlag = false;
        for (String manager : groupManagerArr) {
            if (loginMember.getId() == Integer.parseInt(manager)) {
                isManagerFlag = true;
                break;
            }
            //判断是否已关注该群组
            GroupFans groupFans = groupFansService.findByMemberAndGroup(groupTopic.getGroup().getId(), loginMember.getId());
            if (groupFans != null) {
                isfollow = true;
            }
        }
        if (isManagerFlag || loginMember.getId().intValue() == group.getCreator().intValue()) {
            isManager = 1;
        }
        model.addAttribute("isManager", isManager);
        model.addAttribute("isfollow", isfollow);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/topic";
    }

    @RequestMapping(value = "/post/{groupId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String post(@PathVariable("groupId") Integer groupId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId, loginMember.getId());
        if (groupFans == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1003, Const.INDEX_ERROR_FTL_PATH);
        }
        if (group.getCanPost() == 0) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1006, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group", group);
        List<GroupTopicType> groupTopicTypeList = groupTopicTypeService.list(groupId);
        model.addAttribute("groupTopicTypeList", groupTopicTypeList);
        return jeesnsConfig.getFrontTemplate() + "/group/post";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result post(GroupTopic groupTopic) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.save(loginMember, groupTopic));
        result.setData(groupTopic.getId());
        return result;
    }

    @RequestMapping(value = "/topicEdit/{topicId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String topicEdit(@PathVariable("topicId") Integer topicId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopic groupTopic = groupTopicService.findById(topicId, loginMember);
        if (groupTopic == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1004, Const.INDEX_ERROR_FTL_PATH);
        }
        if (loginMember.getId().intValue() != groupTopic.getMember().getId().intValue()) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1001, Const.INDEX_ERROR_FTL_PATH);
        }
        List<GroupTopicType> groupTopicTypeList = groupTopicTypeService.list(groupTopic.getGroup().getId());
        model.addAttribute("groupTopicTypeList", groupTopicTypeList);
        model.addAttribute("groupTopic", groupTopic);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/topicEdit";
    }

    @RequestMapping(value = "/topicUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result topicUpdate(GroupTopic groupTopic) {
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean flag = groupTopicService.update(loginMember, groupTopic);
        Result result = new Result(flag);
        if (flag) {
            result.setCode(2);
            result.setUrl(Const.GROUP_PATH + "/topic/" + groupTopic.getId());
        }
        return result;
    }

    /**
     * 关注群组
     *
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/follow/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result follow(@PathVariable("groupId") Integer groupId) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.follow(loginMember, groupId, 0));
    }

    /**
     * 取消关注群组
     *
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/nofollow/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result nofollow(@PathVariable("groupId") Integer groupId) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.follow(loginMember, groupId, 1));
    }

    /**
     * 帖子评论
     *
     * @param groupTopicId
     * @param content
     * @return
     */
    @RequestMapping(value = "/comment/{groupTopicId}", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result comment(@PathVariable("groupTopicId") Integer groupTopicId, String content, Integer groupTopicCommentId) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupTopicCommentService.save(loginMember, content, groupTopicId, groupTopicCommentId));
    }


    @RequestMapping(value = "/commentList/{groupTopicId}.json", method = RequestMethod.GET)
    @ResponseBody
    public Result commentList(@PathVariable("groupTopicId") Integer groupTopicId) {
        Page page = new Page(request);
        if (groupTopicId == null) {
            groupTopicId = 0;
        }
        return groupTopicCommentService.listByGroupTopic(page, groupTopicId);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result delete(@PathVariable("id") int id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.indexDelete(request, loginMember, id));
        return result;
    }

    /**
     * 未审核帖子列表
     */
    @RequestMapping(value = "/auditList/{groupId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String auditList(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        group.setCreatorMember(memberService.findById(group.getCreator()));
        model.addAttribute("group", group);
        Member loginMember = MemberUtil.getLoginMember(request);
        int memberId = 0;
        if (loginMember != null) {
            memberId = loginMember.getId();
        }
        //判断是否已关注该群组
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId, memberId);
        if (groupFans == null) {
            model.addAttribute("isfollow", false);
        } else {
            model.addAttribute("isfollow", true);
        }
        //获取群组帖子列表
        Result result = groupTopicService.listByPage(page, null, groupId, 0, 0, 0);
        model.addAttribute("model", result);
        String managerIds = group.getManagers();
        List<Member> managerList = new ArrayList<>();
        if (StringUtils.isNotEmpty(managerIds)) {
            String[] idArr = managerIds.split(",");
            for (String id : idArr) {
                Member member = memberService.findById(Integer.parseInt(id));
                if (member != null) {
                    managerList.add(member);
                }
            }
        }
        model.addAttribute("managerList", managerList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/auditList";
    }


    @RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result audit(@PathVariable("id") Integer id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.audit(loginMember, id));
        return result;
    }

    @RequestMapping(value = "/fans/{groupId}", method = RequestMethod.GET)
    public String fans(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group", group);
        //获取群组粉丝列表,第一页，20条数据
        Result<GroupFans> result = groupFansService.listByPage(page, groupId);
        model.addAttribute("model", result);
        return jeesnsConfig.getFrontTemplate() + "/group/fans";
    }

    /**
     * 置顶、取消置顶
     *
     * @param id
     * @param top
     * @return
     */
    @RequestMapping(value = "/topic/top/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result top(@PathVariable("id") Integer id, @RequestParam("top") Integer top) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.top(loginMember, id, top));
        return result;
    }

    /**
     * 加精、取消加精
     *
     * @param id
     * @param essence
     * @return
     */
    @RequestMapping(value = "/topic/essence/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result essence(@PathVariable("id") Integer id, @RequestParam("essence") Integer essence) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.essence(loginMember, id, essence));
        return result;
    }


    /**
     * 帖子、喜欢
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/topic/favor/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result favor(@PathVariable("id") Integer id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if (id == null) {
            return new Result(-1, "非法操作");
        }
        Result result = groupTopicService.favor(loginMember, id);
        return result;
    }

    @RequestMapping(value = "/topicTypeList/{groupId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String topicTypeList(@PathVariable("groupId") Integer groupId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        group.setCreatorMember(memberService.findById(group.getCreator()));
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1001, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group", group);
        List<GroupTopicType> list = groupTopicTypeService.list(groupId);
        model.addAttribute("list", list);
        return jeesnsConfig.getFrontTemplate() + "/group/topicTypeList";
    }

    @RequestMapping(value = "/topicTypeAdd/{groupId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String topicTypeAdd(@PathVariable("groupId") Integer groupId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1001, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("group", group);
        return jeesnsConfig.getFrontTemplate() + "/group/topicTypeAdd";
    }

    @RequestMapping(value = "/topicTypeSave", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result topicTypeSave(GroupTopicType groupTopicType) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            throw new ParamException();
        }
        Result result = new Result(groupTopicTypeService.save(groupTopicType));
        if (result.getCode() == 0) {
            result.setCode(3);
        }
        return result;
    }

    @RequestMapping(value = "/topicTypeEdit/{typeId}", method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String topicTypeEdit(@PathVariable("typeId") Integer typeId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopicType groupTopicType = groupTopicTypeService.findById(typeId);
        if (groupTopicType == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1013, Const.INDEX_ERROR_FTL_PATH);
        }
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1001, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("groupTopicType", groupTopicType);
        return jeesnsConfig.getFrontTemplate() + "/group/topicTypeEdit";
    }

    @RequestMapping(value = "/topicTypeUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result topicTypeUpdate(GroupTopicType groupTopicType) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            throw new ParamException();
        }
        Result result = new Result(groupTopicTypeService.update(groupTopicType));
        if (result.getCode() == 0) {
            result.setCode(3);
        }
        return result;
    }

    @RequestMapping(value = "/topicTypeDelete/{typeId}", method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Object topicTypeDelete(@PathVariable("typeId") Integer typeId) {
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopicType groupTopicType = groupTopicTypeService.findById(typeId);
        if (groupTopicType == null) {
            return new Result(-1, "帖子分类不存在");
        }
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            throw new ParamException();
        }
        Result result = new Result(groupTopicTypeService.deleteById(typeId));
        if (result.getCode() == 0) {
            result.setCode(3);
        }
        return result;
    }

}
