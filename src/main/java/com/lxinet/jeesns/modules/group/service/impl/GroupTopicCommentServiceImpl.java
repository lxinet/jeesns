package com.lxinet.jeesns.modules.group.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.group.dao.IGroupTopicCommentDao;
import com.lxinet.jeesns.modules.group.entity.GroupTopic;
import com.lxinet.jeesns.modules.group.entity.GroupTopicComment;
import com.lxinet.jeesns.modules.group.service.IGroupTopicCommentService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/27.
 */
@Service("groupTopicCommentService")
public class GroupTopicCommentServiceImpl implements IGroupTopicCommentService {
    @Resource
    private IGroupTopicCommentDao groupTopicCommentDao;
    @Resource
    private IGroupTopicService groupTopicService;

    @Override
    public GroupTopicComment findById(int id) {
        return groupTopicCommentDao.findById(id);
    }

    @Override
    public ResponseModel save(Member loginMember, String content, Integer groupTopicId) {
        GroupTopic groupTopic = groupTopicService.findById(groupTopicId,loginMember);
        if(groupTopic == null){
            return new ResponseModel(-1,"帖子不存在");
        }
        if(StringUtils.isEmpty(content)){
            return new ResponseModel(-1,"内容不能为空");
        }
        GroupTopicComment groupTopicComment = new GroupTopicComment();
        groupTopicComment.setMemberId(loginMember.getId());
        groupTopicComment.setGroupTopicId(groupTopicId);
        groupTopicComment.setContent(content);
        int result = groupTopicCommentDao.save(groupTopicComment);
        if(result == 1){
            return new ResponseModel(1,"评论成功");
        }else {
            return new ResponseModel(-1,"评论失败");
        }
    }

    @Override
    public ResponseModel listByGroupTopic(Page page, int groupTopicId) {
        PageInterceptor.startPage(page);
        List<GroupTopicComment> list = groupTopicCommentDao.listByGroupTopic(groupTopicId);
        ResponseModel model = new ResponseModel(0,PageInterceptor.endPage());
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByTopic(int groupTopicId) {
        groupTopicCommentDao.deleteByTopic(groupTopicId);
    }

    @Override
    public ResponseModel delete(int id) {
        int result = groupTopicCommentDao.delete(id);
        if(result == 1){
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }

}
