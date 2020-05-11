package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.group.*;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.service.group.*;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.utils.JwtUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiGroupController")
@RequestMapping("/api/group")
public class GroupController extends BaseController {
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
    @Resource
    private JwtUtil jwtUtil;

    /**
     * 群组详情
     * @param groupId
     * @return
     */
    @GetMapping(value = "/{groupId:\\d}/info")
    public Result info(@PathVariable("groupId") Integer groupId) {
        Map<String, Object> data = new HashMap<>(3);
        Group group = groupService.findById(groupId);
        Member loginMember = jwtUtil.getMember(request);
        int memberId = loginMember != null ? loginMember.getId() : 0;
        //判断是否已关注该群组
        GroupFans groupFans = groupFansService.findByMemberAndGroup(groupId, memberId);
        //管理员列表
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
        data.put("group", group);
        data.put("managerList", managerList);
        data.put("isfollow", groupFans != null);
        return new Result(data);
    }
    /**
     * 群组贴纸
     * @param groupId
     * @return
     */
    @GetMapping(value = "/{groupId:\\d}/topics")
    public Result topics(@PathVariable("groupId") Integer groupId, @RequestParam(value = "typeId", defaultValue = "0") Integer typeId) {
        Page page = new Page(request);
        //获取群组帖子列表
        Result result = groupTopicService.listByPage(page, null, groupId, 1, 0, typeId);
        return result;
    }
    /**
     * 群组粉丝
     * @param groupId
     * @return
     */
    @GetMapping(value = "/{groupId:\\d}/fans")
    public Result topics(@PathVariable("groupId") Integer groupId) {
        Page page = new Page(request);
        //获取群组帖子列表
        List<GroupFans> groupFansList = (List<GroupFans>) groupFansService.listByPage(page, groupId).getData();
        return new Result(groupFansList);
    }

    /**
     * 申请群组
     * @param group
     * @return
     */
    @PostMapping(value = "/apply")
    public Result apply(Group group) {
        Member loginMember = jwtUtil.getMember(request);
        return new Result(groupService.save(loginMember, group));
    }


    @PostMapping(value = "/update")
    public Result update(Group group) {
        Member loginMember = jwtUtil.getMember(request);
        return new Result(groupService.update(loginMember, group));
    }

    /**
     * 帖子详情
     * @param topicId
     * @return
     */
    @GetMapping(value = "/topic/{topicId:\\d}")
    public Result topic(@PathVariable("topicId") Integer topicId) {
        Member loginMember = jwtUtil.getMember(request);
        GroupTopic groupTopic = groupTopicService.findById(topicId, loginMember);
        ValidUtill.checkParam(groupTopic == null);
        //更新点击次数
        groupTopicService.updateViewCount(groupTopic.getId());
        return new Result(groupTopic);
    }

    /**
     * 发帖
     * @param groupTopic
     * @return
     */
    @PostMapping(value = "/post")
    public Result post(GroupTopic groupTopic) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(groupTopicService.save(loginMember, groupTopic));
        result.setData(groupTopic.getId());
        return result;
    }


    @PostMapping(value = "/topic/update")
    public Result topicUpdate(GroupTopic groupTopic) {
        Member loginMember = jwtUtil.getMember(request);
        groupTopicService.update(loginMember, groupTopic);
        return new Result(Result.SUCCESS);
    }

    /**
     * 关注群组
     * @param groupId
     * @return
     */
    @PostMapping(value = "/follow/{groupId}")
    public Result follow(@PathVariable("groupId") Integer groupId) {
        Member loginMember = jwtUtil.getMember(request);
        return new Result(groupService.follow(loginMember, groupId, 0));
    }

    /**
     * 取消关注群组
     * @param groupId
     * @return
     */
    @GetMapping(value = "/nofollow/{groupId}")
    public Result nofollow(@PathVariable("groupId") Integer groupId) {
        Member loginMember = jwtUtil.getMember(request);
        return new Result(groupService.follow(loginMember, groupId, 1));
    }

    /**
     * 帖子评论
     * @param groupTopicId
     * @param content
     * @return
     */
    @PostMapping(value = "/comment/{groupTopicId}")
    public Result comment(@PathVariable("groupTopicId") Integer groupTopicId, String content, Integer groupTopicCommentId) {
        Member loginMember = jwtUtil.getMember(request);
        return new Result(groupTopicCommentService.save(loginMember, content, groupTopicId, groupTopicCommentId));
    }

    /**
     * 评论列表
     * @param groupTopicId
     * @return
     */
    @GetMapping(value = "/commentList/{groupTopicId}")
    public Result commentList(@PathVariable("groupTopicId") Integer groupTopicId) {
        Page page = new Page(request);
        groupTopicId = groupTopicId == null ? 0 : groupTopicId;
        return groupTopicCommentService.listByGroupTopic(page, groupTopicId);
    }

    /**
     * 删除帖子
     * @param id
     * @return
     */
    @PostMapping(value = "/topic/delete/{id}")
    public Result topicDelete(@PathVariable("id") int id) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(groupTopicService.indexDelete(request, loginMember, id));
        return result;
    }

    /**
     * 未审核帖子列表
     */
    @GetMapping(value = "/auditList/{groupId}")
    public Result auditList(@PathVariable("groupId") Integer groupId) {
        Page page = new Page(request);
        //获取群组帖子列表
        Result result = groupTopicService.listByPage(page, null, groupId, 0, 0, 0);
        return result;
    }

    /**
     * 审核帖子
     * @param id
     * @return
     */
    @PostMapping(value = "/audit/{id}")
    public Result audit(@PathVariable("id") Integer id) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(groupTopicService.audit(loginMember, id));
        return result;
    }

    /**
     * 置顶、取消置顶
     * @param id
     * @param top
     * @return
     */
    @PostMapping(value = "/topic/top/{id}")
    public Result top(@PathVariable("id") Integer id, @RequestParam("top") Integer top) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(groupTopicService.top(loginMember, id, top));
        return result;
    }

    /**
     * 加精、取消加精
     * @param id
     * @param essence
     * @return
     */
    @GetMapping(value = "/topic/essence/{id}")
    public Result essence(@PathVariable("id") Integer id, @RequestParam("essence") Integer essence) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(groupTopicService.essence(loginMember, id, essence));
        return result;
    }


    /**
     * 帖子、喜欢
     * @param id
     * @return
     */
    @GetMapping(value = "/topic/favor/{id}")
    public Result favor(@PathVariable("id") Integer id) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = groupTopicService.favor(loginMember, id);
        return result;
    }

    /**
     * 帖子类型列表
     * @param groupId
     * @return
     */
    @GetMapping(value = "/topicTypeList/{groupId}")
    public Result topicTypeList(@PathVariable("groupId") Integer groupId) {
        List<GroupTopicType> list = groupTopicTypeService.list(groupId);
        return new Result(list);
    }

    /**
     * 添加
     * @param groupTopicType
     * @return
     */
    @PostMapping(value = "/topicType/save")
    public Result topicTypeSave(GroupTopicType groupTopicType) {
        Member loginMember = jwtUtil.getMember(request);
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            throw new ParamException();
        }
        Result result = new Result(groupTopicTypeService.save(groupTopicType));

        return result;
    }


    @PostMapping(value = "/topicType/update")
    public Result topicTypeUpdate(GroupTopicType groupTopicType) {
        Member loginMember = jwtUtil.getMember(request);
        Group group = groupService.findById(groupTopicType.getGroupId());
        String managerIds = group.getManagers();
        if (("," + managerIds + ",").indexOf("," + loginMember.getId() + ",") == -1) {
            throw new ParamException();
        }
        Result result = new Result(groupTopicTypeService.update(groupTopicType));
        return result;
    }

    @GetMapping(value = "/topicType/delete/{typeId}")
    public Object topicTypeDelete(@PathVariable("typeId") Integer typeId) {
        Member loginMember = jwtUtil.getMember(request);
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
        return result;
    }

}
