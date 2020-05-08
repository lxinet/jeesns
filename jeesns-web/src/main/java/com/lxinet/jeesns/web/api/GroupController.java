package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.ErrorUtil;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.group.*;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.service.group.*;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiGroupController")
@RequestMapping("/api/${groupPath}")
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


    /**
     * 群组详情页面
     *
     * @param groupId
     * @param model
     * @return
     */
    @GetMapping(value = "/detail/{groupId}")
    public String detail(@PathVariable("groupId") Integer groupId, @RequestParam(value = "typeId", defaultValue = "0") Integer typeId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
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
        if (loginMember == null) {
            model.addAttribute("isManager", 0);
        } else {
            boolean isManager = false;
            for (String manager : groupManagerArr) {
                if (loginMember.getId() == Integer.parseInt(manager)) {
                    isManager = true;
                }
            }
            if (isManager || loginMember.getId().intValue() == group.getCreator().intValue()) {
                model.addAttribute("isManager", 1);
            }
        }
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

    @PostMapping(value = "/apply")
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

    @PostMapping(value = "/update")
    public Result update(Group group) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupService.update(loginMember, group));
    }

    @GetMapping(value = "/topic/{topicId}")
    public String topic(@PathVariable("topicId") Integer topicId, Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        GroupTopic groupTopic = groupTopicService.findById(topicId, loginMember);
        if (groupTopic == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1004, Const.INDEX_ERROR_FTL_PATH);
        }
        groupTopicService.updateViewCount(groupTopic.getId());
        model.addAttribute("groupTopic", groupTopic);

        Group group = groupService.findById(groupTopic.getGroup().getId());
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1000, Const.INDEX_ERROR_FTL_PATH);
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isfollow = false;
        if (loginMember == null) {
            model.addAttribute("isPermission", 0);
        } else {
            boolean isManager = false;
            for (String manager : groupManagerArr) {
                if (loginMember.getId() == Integer.parseInt(manager)) {
                    isManager = true;
                }
            }
            if (loginMember.getId().intValue() == groupTopic.getMember().getId().intValue() || loginMember.getIsAdmin() > 0 ||
                    isManager || loginMember.getId().intValue() == group.getCreator().intValue()) {
                model.addAttribute("isPermission", 1);
            }
            //判断是否已关注该群组
            GroupFans groupFans = groupFansService.findByMemberAndGroup(groupTopic.getGroup().getId(), loginMember.getId());
            if (groupFans != null) {
                isfollow = true;
            }
        }
        model.addAttribute("isfollow", isfollow);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/group/topic";
    }

    @PostMapping(value = "/post")
    public Result post(GroupTopic groupTopic) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.save(loginMember, groupTopic));
        result.setData(groupTopic.getId());
        return result;
    }

    @GetMapping(value = "/topicEdit/{topicId}")
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

    @PostMapping(value = "/topicUpdate")
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
    @GetMapping(value = "/follow/{groupId}")
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
    @GetMapping(value = "/nofollow/{groupId}")
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
    @PostMapping(value = "/comment/{groupTopicId}")
    public Result comment(@PathVariable("groupTopicId") Integer groupTopicId, String content, Integer groupTopicCommentId) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(groupTopicCommentService.save(loginMember, content, groupTopicId, groupTopicCommentId));
    }


    @GetMapping(value = "/commentList/{groupTopicId}")
    @ResponseBody
    public Result commentList(@PathVariable("groupTopicId") Integer groupTopicId) {
        Page page = new Page(request);
        if (groupTopicId == null) {
            groupTopicId = 0;
        }
        return groupTopicCommentService.listByGroupTopic(page, groupTopicId);
    }

    @PostMapping(value = "/delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.indexDelete(request, loginMember, id));
        return result;
    }

    /**
     * 未审核帖子列表
     */
    @GetMapping(value = "/auditList/{groupId}")
    public String auditList(@PathVariable("groupId") Integer groupId, Model model) {
        Page page = new Page(request);
        Group group = groupService.findById(groupId);
        if (group == null) {
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1002, Const.INDEX_ERROR_FTL_PATH);
        }
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


    @GetMapping(value = "/audit/{id}")
    public Result audit(@PathVariable("id") Integer id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(groupTopicService.audit(loginMember, id));
        return result;
    }

    @GetMapping(value = "/fans/{groupId}")
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
    @PostMapping(value = "/topic/top/{id}")
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
    @GetMapping(value = "/topic/essence/{id}")
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
    @GetMapping(value = "/topic/favor/{id}")
    public Result favor(@PathVariable("id") Integer id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if (id == null) {
            return new Result(-1, "非法操作");
        }
        Result result = groupTopicService.favor(loginMember, id);
        return result;
    }

    @GetMapping(value = "/topicTypeList/{groupId}")
    public String topicTypeList(@PathVariable("groupId") Integer groupId, Model model) {
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
        List<GroupTopicType> list = groupTopicTypeService.list(groupId);
        model.addAttribute("list", list);
        return jeesnsConfig.getFrontTemplate() + "/group/topicTypeList";
    }

    @GetMapping(value = "/topicTypeAdd/{groupId}")
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

    @PostMapping(value = "/topicTypeSave")
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

    @GetMapping(value = "/topicTypeEdit/{typeId}")
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

    @PostMapping(value = "/topicTypeUpdate")
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

    @GetMapping(value = "/topicTypeDelete/{typeId}")
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
