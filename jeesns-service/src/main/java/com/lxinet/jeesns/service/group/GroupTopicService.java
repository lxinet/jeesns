package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.core.utils.HtmlUtil;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.MessageService;
import com.lxinet.jeesns.service.member.ScoreDetailService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.utils.ActionLogType;
import com.lxinet.jeesns.utils.ActionUtil;
import com.lxinet.jeesns.utils.ScoreRuleConsts;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.dao.group.IGroupTopicDao;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.group.GroupTopic;
import com.lxinet.jeesns.model.member.Member;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/26.
 */
@Service("groupTopicService")
public class GroupTopicService extends BaseService<GroupTopic> {
    @Resource
    private IGroupTopicDao groupTopicDao;
    @Resource
    private GroupService groupService;
    @Resource
    private GroupTopicCommentService groupTopicCommentService;
    @Resource
    private GroupFansService groupFansService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private MessageService messageService;
    @Resource
    private MemberService memberService;
    @Resource
    private GroupTopicFavorService groupTopicFavorService;

    public GroupTopic findById(int id) {
        return this.findById(id,null);
    }

    public GroupTopic findById(int id,Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        return this.atFormat(groupTopicDao.findById(id,loginMemberId));
    }

    @Transactional
    public boolean save(Member member, GroupTopic groupTopic) {
        if(groupTopic.getGroupId() == null || groupTopic.getGroupId() == 0){
            throw new ParamException();
        }
        Group group = groupService.findById(groupTopic.getGroupId());
        ValidUtill.checkIsNull(group, "群组不存在");
        if(groupFansService.findByMemberAndGroup(group.getId(),member.getId()) == null){
            throw new OpeErrorException("必须关注该群组后才能发帖");
        }
        if(group.getCanPost() == 0){
            throw new OpeErrorException("群组已关闭发帖功能");
        }
        groupTopic.setMemberId(member.getId());
        //保存文章
        groupTopic.setStatus(group.getTopicReview()==0?1:0);
        int result = groupTopicDao.saveObj(groupTopic);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(member.getId(),groupTopic.getContent(), AppTag.GROUP, MessageType.GROUP_TOPIC_REFER,groupTopic.getId());
            //群组发帖奖励
            scoreDetailService.scoreBonus(member.getId(), ScoreRuleConsts.GROUP_POST, groupTopic.getId());
            actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_GROUP_TOPIC,"", ActionLogType.GROUP_TOPIC.getValue(),groupTopic.getId());
        }
        return result == 1;
    }

    public Result listByPage(Page page, String key, int groupId, int status, int memberId, int typeId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<GroupTopic> list = groupTopicDao.list(page, key,groupId,status,memberId,typeId);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public boolean update(Member member, GroupTopic groupTopic) {
        GroupTopic findGroupTopic = this.findById(groupTopic.getId(),member);
        ValidUtill.checkIsNull(findGroupTopic, "帖子不存在");
        if(member.getId().intValue() != findGroupTopic.getMember().getId().intValue()){
            throw new OpeErrorException("没有权限");
        }
        if (groupTopic.getTypeId() != null && !groupTopic.getTypeId().equals(findGroupTopic.getTypeId())){
            groupTopicDao.updateType(groupTopic.getId(),groupTopic.getTypeId());
        }
        findGroupTopic.setTitle(groupTopic.getTitle());
        findGroupTopic.setThumbnail(groupTopic.getThumbnail());
        findGroupTopic.setContent(groupTopic.getContent());
        findGroupTopic.setDescription(groupTopic.getDescription());
        findGroupTopic.setKeywords(groupTopic.getKeywords());
        //普通会员
        if (member.getIsAdmin() == 0) {
            if (member.getId().intValue() != findGroupTopic.getMemberId().intValue()) {
                return false;
            }
        } else {
            //管理员
            findGroupTopic.setSource(groupTopic.getSource());
            if (groupTopic.getViewCount() != null && groupTopic.getViewCount() > 0){
                findGroupTopic.setViewCount(groupTopic.getViewCount());
            }
            findGroupTopic.setWriter(groupTopic.getWriter());
            if (groupTopic.getViewRank() != null && groupTopic.getViewRank() > 0){
                findGroupTopic.setViewRank(groupTopic.getViewRank());
            }
        }
        if (findGroupTopic.getViewCount() == null) {
            findGroupTopic.setViewCount(0);
        }
        if (findGroupTopic.getViewRank() == null) {
            findGroupTopic.setViewRank(0);
        }
        if (StringUtils.isEmpty(findGroupTopic.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(findGroupTopic.getContent());
            if (contentStr.length() > 200) {
                findGroupTopic.setDescription(contentStr.substring(0, 200));
            } else {
                findGroupTopic.setDescription(contentStr);
            }
        }
        if (StringUtils.isEmpty(findGroupTopic.getThumbnail())) {
            Document doc = Jsoup.parseBodyFragment(findGroupTopic.getContent());
            Elements elements = doc.select("img[src]");
            if (elements.size() > 0) {
                String imgsrc = elements.get(0).attr("src");
                findGroupTopic.setThumbnail(imgsrc);
            }else {
                findGroupTopic.setThumbnail(null);
            }
        }
        return groupTopicDao.updateObj(findGroupTopic) == 1;
    }


    @Transactional
    public boolean delete(Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        int result = groupTopicDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_POST,id);
            groupTopicCommentService.deleteByTopic(id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC,"ID："+groupTopic.getId()+"，标题："+groupTopic.getTitle());

        }
        return result == 1;
    }


    @Transactional
    public boolean indexDelete(HttpServletRequest request, Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id,loginMember);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        Group group = groupService.findById(groupTopic.getGroup().getId());
        ValidUtill.checkIsNull(group);
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
            boolean flag = this.delete(loginMember,id);
            return flag;
        }
        throw new OpeErrorException("没有权限");
    }

    public boolean audit(Member member, int id) {
        GroupTopic groupTopic = this.findById(id,member);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        Group group = groupService.findById(groupTopic.getGroup().getId());
        ValidUtill.checkIsNull(group);
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
            return groupTopicDao.audit(id) == 1;
        }
        throw new OpeErrorException("没有权限");
    }

    public boolean top(Member member, int id, int top) {
        GroupTopic groupTopic = this.findById(id,member);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        Group group = groupService.findById(groupTopic.getGroup().getId());
        ValidUtill.checkIsNull(group);
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
            return groupTopicDao.top(id,top) == 1;
        }
        throw new OpeErrorException("没有权限");
    }

    /**
     * 将帖子设置精华
     * @param member
     * @param id
     * @param essence
     * @return
     */
    public boolean essence(Member member, int id, int essence) {
        GroupTopic groupTopic = this.findById(id,member);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        Group group = groupService.findById(groupTopic.getGroup().getId());
        ValidUtill.checkIsNull(group);
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
            return groupTopicDao.essence(id,essence) == 1;
        }
        throw new OpeErrorException("没有权限");
    }


    public Result favor(Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        int favor = groupTopic.getFavor();
        String message;
        Result<Integer> result;
        if(groupTopicFavorService.find(id,loginMember.getId()) == null){
            //增加
            groupTopicDao.favor(id,1);
            groupTopicFavorService.save(id,loginMember.getId());
            message = "喜欢成功";

            favor += 1;
            //帖子收到喜欢
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
            //点赞之后发送系统信息
            messageService.diggDeal(loginMember.getId(),groupTopic.getMemberId(),AppTag.GROUP,MessageType.GROUP_TOPIC_LIKE,groupTopic.getId());
        }else {
            //减少
            groupTopicDao.favor(id,-1);
            groupTopicFavorService.delete(id,loginMember.getId());
            message = "取消喜欢成功";
            favor -= 1;
            //帖子取消喜欢
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
        }
        result = new Result(0,message);
        result.setData(favor);
        return result;
    }

    public List<GroupTopic> listByCustom(int gid, String sort, int num, int day,int thumbnail) {
        return groupTopicDao.listByCustom(gid,sort,num,day,thumbnail);
    }

    public void updateViewCount(int id) {
        groupTopicDao.updateViewCount(id);
    }

    public GroupTopic atFormat(GroupTopic groupTopic){
        if (groupTopic != null){
            groupTopic.setContent(memberService.atFormat(groupTopic.getContent()));
        }
        return groupTopic;
    }
}
