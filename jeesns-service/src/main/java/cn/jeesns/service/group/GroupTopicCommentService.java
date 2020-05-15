package cn.jeesns.service.group;

import cn.jeesns.service.member.MemberService;
import cn.jeesns.service.member.MessageService;
import cn.jeesns.service.member.ScoreDetailService;
import cn.jeesns.service.system.ActionLogService;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.core.consts.AppTag;
import cn.jeesns.core.enums.MessageType;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.model.group.GroupTopic;
import cn.jeesns.model.group.GroupTopicComment;
import cn.jeesns.model.member.Member;
import cn.jeesns.dao.group.IGroupTopicCommentDao;
import cn.jeesns.utils.ActionUtil;
import cn.jeesns.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/27.
 */
@Service("groupTopicCommentService")
public class GroupTopicCommentService extends BaseService<GroupTopicComment> {
    @Resource
    private IGroupTopicCommentDao groupTopicCommentDao;
    @Resource
    private GroupTopicService groupTopicService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private MessageService messageService;
    @Resource
    private MemberService memberService;

    public GroupTopicComment findById(int id) {
        return this.atFormat(groupTopicCommentDao.findById(id));
    }

    public boolean save(Member loginMember, String content, Integer groupTopicId, Integer commentId) {
        GroupTopic groupTopic = groupTopicService.findById(groupTopicId,loginMember);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        ValidUtill.checkIsBlank(content, "内容不能为空");
        GroupTopicComment groupTopicComment = new GroupTopicComment();
        groupTopicComment.setMemberId(loginMember.getId());
        groupTopicComment.setGroupTopicId(groupTopicId);
        groupTopicComment.setContent(content);
        groupTopicComment.setCommentId(commentId);
        int result = groupTopicCommentDao.saveObj(groupTopicComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.GROUP, MessageType.GROUP_TOPIC_COMMENT_REFER,groupTopicComment.getId());
            messageService.diggDeal(loginMember.getId(),groupTopic.getMemberId(),content,AppTag.GROUP,MessageType.GROUP_TOPIC_REPLY,groupTopic.getId());
            if (commentId != null){
                GroupTopicComment replyGroupTopicComment = findById(commentId);
                if(replyGroupTopicComment != null){
                    messageService.diggDeal(loginMember.getId(),replyGroupTopicComment.getMemberId(),content,AppTag.GROUP,MessageType.GROUP_TOPIC_REPLY_REPLY,replyGroupTopicComment.getId());
                }
            }
            //群组帖子评论奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_COMMENTS, groupTopicComment.getId());
        }
        return result == 1;
    }

    public Result listByGroupTopic(Page page, int groupTopicId) {
        List<GroupTopicComment> list = groupTopicCommentDao.listByGroupTopic(page, groupTopicId);
        this.atFormat(list);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public void deleteByTopic(int groupTopicId) {
        groupTopicCommentDao.deleteByTopic(groupTopicId);
    }

    public boolean delete(Member loginMember, int id){
        GroupTopicComment groupTopicComment = this.findById(id);
        ValidUtill.checkIsNull(groupTopicComment, "评论不存在");
        boolean result = super.deleteById(id);
        if(result){
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_COMMENTS,id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC_COMMENT,"ID："+groupTopicComment.getId()+"，内容："+groupTopicComment.getContent());
        }
        return result;
    }

    public GroupTopicComment atFormat(GroupTopicComment groupTopicComment){
        groupTopicComment.setContent(memberService.atFormat(groupTopicComment.getContent()));
        return groupTopicComment;
    }

    public List<GroupTopicComment> atFormat(List<GroupTopicComment> groupTopicCommentList){
        for (GroupTopicComment groupTopicComment : groupTopicCommentList){
            atFormat(groupTopicComment);
        }
        return groupTopicCommentList;
    }
}
