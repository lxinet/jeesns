package com.lxinet.jeesns.modules.group.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Archive;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.ActionLogType;
import com.lxinet.jeesns.core.utils.ActionUtil;
import com.lxinet.jeesns.core.utils.ScoreRuleConsts;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.group.dao.IGroupTopicDao;
import com.lxinet.jeesns.modules.group.entity.Group;
import com.lxinet.jeesns.modules.group.entity.GroupTopic;
import com.lxinet.jeesns.modules.group.service.IGroupFansService;
import com.lxinet.jeesns.modules.group.service.IGroupService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicCommentService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/26.
 */
@Service("groupTopicService")
public class GroupTopicServiceImpl implements IGroupTopicService {
    @Resource
    private IGroupTopicDao groupTopicDao;
    @Resource
    private IGroupService groupService;
    @Resource
    private IGroupTopicCommentService groupTopicCommentService;
    @Resource
    private IGroupFansService groupFansService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreRuleService scoreRuleService;

    @Override
    public GroupTopic findById(int id) {
        return groupTopicDao.findById(id,0);
    }

    @Override
    public GroupTopic findById(int id,Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        return groupTopicDao.findById(id,loginMemberId);
    }

    @Override
    @Transactional
    public ResponseModel save(Member member, GroupTopic groupTopic) {
        if(groupTopic.getGroupId() == null || groupTopic.getGroupId() == 0){
            return new ResponseModel(-1,"群组不能为空");
        }
        Group group = groupService.findById(groupTopic.getGroupId());
        if(group == null){
            return new ResponseModel(-1,"群组不存在");
        }
        if(groupFansService.findByMemberAndGroup(group.getId(),member.getId()) == null){
            return new ResponseModel(-1,"必须关注该群组后才能发帖");
        }
        if(group.getCanPost() == 0){
            return new ResponseModel(-1,"群组已关闭发帖功能");
        }
        groupTopic.setMemberId(member.getId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(groupTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        archive.setPostType(2);
        //保存文档
        if(archiveService.save(member,archive)){
            //保存文章
            groupTopic.setStatus(group.getTopicReview()==0?1:0);
            groupTopic.setArchiveId(archive.getArchiveId());
            int result = groupTopicDao.save(groupTopic);
            if(result == 1){
                //群组发帖奖励
                scoreRuleService.scoreRuleBonus(member.getId(), ScoreRuleConsts.GROUP_POST, groupTopic.getId());
                actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_GROUP_TOPIC,"", ActionLogType.GROUP_TOPIC.getValue(),groupTopic.getId());
                return new ResponseModel(2,"帖子发布成功","../detail/"+groupTopic.getGroupId());
            }
        }
        return new ResponseModel(-1,"帖子发布失败");
    }

    @Override
    public ResponseModel listByPage(Page page, String key, int groupId,int status,int memberId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<GroupTopic> list = groupTopicDao.listByPage(page, key,groupId,status,memberId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    @Transactional
    public ResponseModel update(Member member,GroupTopic groupTopic) {
        GroupTopic findGroupTopic = this.findById(groupTopic.getId(),member);
        if(findGroupTopic == null){
            return new ResponseModel(-2);
        }
        if(member.getId().intValue() != findGroupTopic.getMember().getId().intValue()){
            return new ResponseModel(-1,"没有权限");
        }
        groupTopic.setArchiveId(findGroupTopic.getArchiveId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(groupTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(archiveService.update(member,archive)){
            return new ResponseModel(0,"更新成功");
        }
        return new ResponseModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResponseModel delete(Member loginMember,int id) {
        GroupTopic groupTopic = this.findById(id);
        if(groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        int result = groupTopicDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreRuleService.scoreRuleCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_POST,id);
            archiveService.delete(groupTopic.getArchiveId());
            groupTopicCommentService.deleteByTopic(id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC,"ID："+groupTopic.getId()+"，标题："+groupTopic.getTitle());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }


    @Override
    @Transactional
    public ResponseModel indexDelete(Member loginMember, int id) {
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id,loginMember);
        if (groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            return new ResponseModel(-1,"出现异常");
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(loginMember.getId().intValue() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(loginMember.getId().intValue() == groupTopic.getMember().getId().intValue() || loginMember.getIsAdmin() == 1 ||
                isManager || loginMember.getId().intValue() == group.getCreator().intValue()){
            return this.delete(loginMember,id);
        }
        return new ResponseModel(-1,"权限不足");
    }

    @Override
    public ResponseModel audit(Member member,int id) {
        if(member == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            return new ResponseModel(-1,"出现异常");
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() == 1 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.audit(id) == 1){
                return new ResponseModel(1,"审核成功");
            }else {
                return new ResponseModel(-1,"审核失败");
            }
        }
        return new ResponseModel(-1,"权限不足");
    }

    @Override
    public ResponseModel top(Member member,int id, int top) {
        if(member == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            return new ResponseModel(-1,"出现异常");
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() == 1 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.top(id,top) == 1){
                return new ResponseModel(1,"操作成功");
            }else {
                return new ResponseModel(-1,"操作失败");
            }
        }
        return new ResponseModel(-1,"权限不足");
    }

    /**
     * 将帖子设置精华
     * @param member
     * @param id
     * @param essence
     * @return
     */
    @Override
    public ResponseModel essence(Member member,int id, int essence) {
        if(member == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            return new ResponseModel(-1,"出现异常");
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() == 1 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.essence(id,essence) == 1){
                return new ResponseModel(1,"操作成功");
            }else {
                return new ResponseModel(-1,"操作失败");
            }
        }
        return new ResponseModel(-1,"权限不足");
    }


    @Override
    public ResponseModel favor(Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id);
        if(groupTopic != null){
            ResponseModel responseModel = archiveService.favor(loginMember,groupTopic.getArchiveId());
            if(responseModel.getCode() == 0){
                //帖子收到喜欢
                scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
            }else if(responseModel.getCode() == 1){
                //帖子取消喜欢
                //扣除积分
                scoreRuleService.scoreRuleCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
            }
            return responseModel;
        }
        return new ResponseModel(-1,"帖子不存在");
    }
}
