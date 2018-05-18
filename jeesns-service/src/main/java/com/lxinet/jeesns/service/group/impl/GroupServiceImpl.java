package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.group.Group;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IGroupService;
import com.lxinet.jeesns.dao.group.IGroupDao;
import com.lxinet.jeesns.service.group.IGroupFansService;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IScoreDetailService;
import com.lxinet.jeesns.service.system.IActionLogService;
import com.lxinet.jeesns.service.system.IConfigService;
import com.lxinet.jeesns.common.utils.ActionUtil;
import com.lxinet.jeesns.common.utils.ConfigUtil;
import com.lxinet.jeesns.common.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/12/23.
 */
@Service("groupService")
public class GroupServiceImpl implements IGroupService {
    @Resource
    private IGroupDao groupDao;
    @Resource
    private IGroupFansService groupFansService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IConfigService configService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;

    @Override
    public List<Group> list(int status, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Group> list = groupDao.list(status,key);
        return list;
    }

    /**
     * 关注、取消关注群组
     * @param loginMember
     * @param groupId
     * @param type 0关注，1取消关注
     * @return
     */
    @Override
    public ResultModel follow(Member loginMember, Integer groupId, int type) {
        Group group = this.findById(groupId);
        if(group == null){
            return new ResultModel(-1,"群组不存在");
        }
        if(type == 0){
            return groupFansService.save(loginMember,groupId);
        }else {
            //创建者无法取消关注
            if(loginMember.getId().intValue() == group.getCreator().intValue()){
                return new ResultModel(-1,"管理员不能取消关注");
            }
            return groupFansService.delete(loginMember,groupId);
        }

    }

    @Override
    public ResultModel changeStatus(int id) {
        if(groupDao.changeStatus(id) == 1){
            return new ResultModel(1,"操作成功");
        }
        return new ResultModel(-1,"操作失败");
    }

    @Override
    public List<Group> listByCustom(int status, int num, String sort) {
        return groupDao.listByCustom(status,num,sort);
    }

    @Override
    public Group findById(int id) {
        return groupDao.findById(id);
    }

    @Override
    @Transactional
    public ResultModel save(Member loginMember, Group group) {
        Map<String,String> config = configService.getConfigToMap();
        group.setCreator(loginMember.getId());
        if(loginMember.getIsAdmin() > 0){
            group.setStatus(1);
        }else {
            if("0".equals(config.get(ConfigUtil.GROUP_APPLY))){
                return new ResultModel(-1,"群组申请功能已关闭");
            }
            if("0".equals(config.get(ConfigUtil.GROUP_APPLY_REVIEW))){
                group.setStatus(0);
            }else {
                group.setStatus(1);
            }
        }
        //默认图标
        if(StringUtils.isEmpty(group.getLogo())){
            group.setLogo(Const.DEFAULT_IMG_URL);
        }
        //设置管理员
        String managerIds = String.valueOf(loginMember.getId());
        group.setManagers(managerIds);
        group.setCanPost(1);
        group.setTopicReview(0);
        if(groupDao.save(group) == 1){
            //创建者默认关注群组
            groupFansService.save(loginMember,group.getId());
            //申请群组奖励、扣款
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.APPLY_GROUP, group.getId());
            return new ResultModel(1,"申请成功，请等待审核");
        }
        return new ResultModel(-1,"操作失败，请重试");
    }

    @Override
    public ResultModel update(Member loginMember, Group group) {
        Group findGroup = this.findById(group.getId());
        if(findGroup == null){
            return new ResultModel(-1,"群组不存在");
        }
        if(loginMember.getId().intValue() != findGroup.getCreator().intValue()){
            return new ResultModel(-1,"没有权限");
        }

        //设置管理员
        String managerNames = group.getManagers();
        String managerIds = "";
        String[] names = managerNames.split(",");
        if(names.length > 10){
            return new ResultModel(-1,"管理员不能超过10个");
        }
        for (String name : names){
            Member member = memberService.findByName(name.trim());
            if(member != null){
                managerIds += member.getId() + ",";
            }
        }
        if(managerIds.length() > 0){
            managerIds = managerIds.substring(0,managerIds.length()-1);
        }
        if(StringUtils.isNotEmpty(group.getLogo())){
            findGroup.setLogo(group.getLogo());
        }
        findGroup.setManagers(managerIds);
        findGroup.setName(group.getName());
        findGroup.setTags(group.getTags());
        findGroup.setCanPost(group.getCanPost());
        findGroup.setTopicReview(group.getTopicReview());
        findGroup.setIntroduce(group.getIntroduce());
        findGroup.setTypeId(group.getTypeId());
        if(groupDao.update(findGroup) == 1){
            return new ResultModel(1,"操作成功");
        }

        return new ResultModel(-1,"操作失败，请重试");
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        Group group = this.findById(id);
        if(group == null){
            return new ResultModel(-1,"群组不存在");
        }
        if(groupDao.delete(id) == 1){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP,"ID："+group.getId()+"，名字："+group.getName());
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }


}
