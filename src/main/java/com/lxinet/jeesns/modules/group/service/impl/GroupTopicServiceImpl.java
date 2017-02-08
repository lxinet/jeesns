package com.lxinet.jeesns.modules.group.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Archive;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.modules.group.dao.IGroupTopicDao;
import com.lxinet.jeesns.modules.group.entity.Group;
import com.lxinet.jeesns.modules.group.entity.GroupTopic;
import com.lxinet.jeesns.modules.group.service.IGroupFansService;
import com.lxinet.jeesns.modules.group.service.IGroupService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicCommentService;
import com.lxinet.jeesns.modules.group.service.IGroupTopicService;
import com.lxinet.jeesns.modules.mem.entity.Member;
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

    @Override
    public GroupTopic findById(int id) {
        return groupTopicDao.findById(id);
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
            int result = groupTopicDao.save(groupTopic.getGroupId(),archive.getArchiveId(),group.getTopicReview()==0?1:0);
            if(result == 1){
                return new ResponseModel(2,"帖子发布成功","../detail/"+groupTopic.getGroupId());
            }
        }
        return new ResponseModel(-1,"帖子发布失败");
    }

    @Override
    public ResponseModel listByPage(Page page, String key, int groupId,int status) {
        PageInterceptor.startPage(page);
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<GroupTopic> list = groupTopicDao.listByPage(key,groupId,status);
        ResponseModel model = new ResponseModel(0,PageInterceptor.endPage());
        model.setData(list);
        return model;
    }

    @Override
    @Transactional
    public ResponseModel update(Member member,GroupTopic groupTopic) {
        GroupTopic findGroupTopic = this.findById(groupTopic.getId());
        if(findGroupTopic == null){
            return new ResponseModel(-2);
        }
        if(member.getId() != findGroupTopic.getMember().getId()){
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
            //更新栏目
            if(findGroupTopic.getGroupId() != groupTopic.getGroupId()){
                findGroupTopic.setGroupId(groupTopic.getGroupId());
                groupTopicDao.update(findGroupTopic);
            }
            return new ResponseModel(0,"更新成功");
        }
        return new ResponseModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResponseModel delete(int id) {
        GroupTopic groupTopic = groupTopicDao.findById(id);
        int result = groupTopicDao.delete(id);
        if(result == 1){
            archiveService.delete(groupTopic.getArchiveId());
            groupTopicCommentService.deleteByTopic(id);
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }


    @Override
    @Transactional
    public ResponseModel indexDelete(Member member, int id) {
        if(member == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id);
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
        if(member.getId() == groupTopic.getMember().getId() || member.getIsAdmin() == 1 || isManager || member.getId() == group.getCreator()){
            return this.delete(id);
        }
        return new ResponseModel(-1,"权限不足");
    }

    @Override
    public ResponseModel audit(Member member,int id) {
        if(member == null){
            return new ResponseModel(-1,"请先登录");
        }
        GroupTopic groupTopic = this.findById(id);
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
        if(member.getId() == groupTopic.getMember().getId() || member.getIsAdmin() == 1 || isManager || member.getId() == group.getCreator()){
            if(groupTopicDao.audit(id) == 1){
                return new ResponseModel(1,"审核成功");
            }else {
                return new ResponseModel(-1,"审核失败");
            }
        }
        return new ResponseModel(-1,"权限不足");
    }
}
