package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.common.utils.ActionLogType;
import com.lxinet.jeesns.common.utils.ActionUtil;
import com.lxinet.jeesns.common.utils.ScoreRuleConsts;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.dao.group.IGroupTopicDao;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.common.Archive;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.group.GroupTopic;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.common.IArchiveService;
import com.lxinet.jeesns.service.group.IGroupFansService;
import com.lxinet.jeesns.service.group.IGroupService;
import com.lxinet.jeesns.service.group.IGroupTopicCommentService;
import com.lxinet.jeesns.service.group.IGroupTopicService;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IMessageService;
import com.lxinet.jeesns.service.member.IScoreDetailService;
import com.lxinet.jeesns.service.system.IActionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public GroupTopic findById(int id) {
        return this.findById(id,null);
    }

    @Override
    public GroupTopic findById(int id,Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        return this.atFormat(groupTopicDao.findById(id,loginMemberId));
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
                //@会员处理并发送系统消息
                messageService.atDeal(member.getId(),groupTopic.getContent(), AppTag.GROUP, MessageType.GROUP_TOPIC_REFER,groupTopic.getId());
                //群组发帖奖励
                scoreDetailService.scoreBonus(member.getId(), ScoreRuleConsts.GROUP_POST, groupTopic.getId());
                actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_GROUP_TOPIC,"", ActionLogType.GROUP_TOPIC.getValue(),groupTopic.getId());
                if (groupTopic.getStatus() == 0){
                    return new ResponseModel(2,"帖子发布成功，请等待管理员审核通过","../detail/"+groupTopic.getGroupId());
                }

                return new ResponseModel(2,"帖子发布成功","../topic/"+groupTopic.getId());
            }
        }
        return new ResponseModel(-1,"帖子发布失败");
    }

    @Override
    public ResponseModel listByPage(Page page, String key, int groupId, int status, int memberId) {
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
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_POST,id);
            archiveService.delete(groupTopic.getArchiveId());
            groupTopicCommentService.deleteByTopic(id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC,"ID："+groupTopic.getId()+"，标题："+groupTopic.getTitle());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }


    @Override
    @Transactional
    public ResponseModel indexDelete(HttpServletRequest request, Member loginMember, int id) {
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
        if(loginMember.getId().intValue() == groupTopic.getMember().getId().intValue() || loginMember.getIsAdmin() > 0 ||
                isManager || loginMember.getId().intValue() == group.getCreator().intValue()){
            ResponseModel responseModel = this.delete(loginMember,id);
            if(responseModel.getCode() > 0){
                responseModel.setCode(2);
                responseModel.setUrl(request.getContextPath() + "/group/detail/"+group.getId());
            }
            return responseModel;
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
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
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
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
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
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
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
                scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
                //点赞之后发送系统信息
                messageService.diggDeal(loginMember.getId(),groupTopic.getMemberId(),AppTag.GROUP,MessageType.GROUP_TOPIC_LIKE,groupTopic.getId());
            }else if(responseModel.getCode() == 1){
                //帖子取消喜欢
                //扣除积分
                scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
            }
            return responseModel;
        }
        return new ResponseModel(-1,"帖子不存在");
    }

    @Override
    public List<GroupTopic> listByCustom(int gid, String sort, int num, int day,int thumbnail) {
        return groupTopicDao.listByCustom(gid,sort,num,day,thumbnail);
    }

    public GroupTopic atFormat(GroupTopic groupTopic){
        groupTopic.setContent(memberService.atFormat(groupTopic.getContent()));
        return groupTopic;
    }
}
