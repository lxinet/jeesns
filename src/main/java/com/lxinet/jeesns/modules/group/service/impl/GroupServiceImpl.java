package com.lxinet.jeesns.modules.group.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.modules.group.dao.IGroupDao;
import com.lxinet.jeesns.modules.group.entity.Group;
import com.lxinet.jeesns.modules.group.service.IGroupFansService;
import com.lxinet.jeesns.modules.group.service.IGroupService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import com.lxinet.jeesns.modules.sys.entity.ActionLog;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
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
    private IScoreRuleService scoreRuleService;

    @Override
    public ResponseModel listByPage(int status,Page page, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Group> list = groupDao.listByPage(page, status,key);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    /**
     * 关注、取消关注群组
     * @param loginMember
     * @param groupId
     * @param type 0关注，1取消关注
     * @return
     */
    @Override
    public ResponseModel follow(Member loginMember, Integer groupId,int type) {
        Group group = this.findById(groupId);
        if(group == null){
            return new ResponseModel(-1,"群组不存在");
        }
        if(type == 0){
            return groupFansService.save(loginMember,groupId);
        }else {
            //创建者无法取消关注
            if(loginMember.getId().intValue() == group.getCreator().intValue()){
                return new ResponseModel(-1,"管理员不能取消关注");
            }
            return groupFansService.delete(loginMember,groupId);
        }

    }

    @Override
    public ResponseModel changeStatus(int id) {
        if(groupDao.changeStatus(id) == 1){
            return new ResponseModel(1,"操作成功");
        }
        return new ResponseModel(-1,"操作失败");
    }

    @Override
    public Group findById(int id) {
        return groupDao.findById(id);
    }

    @Override
    @Transactional
    public ResponseModel save(Member loginMember,Group group) {
        Map<String,String> config = configService.getConfigToMap();
        group.setCreator(loginMember.getId());
        if(loginMember.getIsAdmin() == 1){
            group.setStatus(1);
        }else {
            if("0".equals(config.get(ConfigUtil.GROUP_APPLY))){
                return new ResponseModel(-1,"群组申请功能已关闭");
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
        String managerNames = group.getManagers();
        String managerIds = loginMember.getId()+",";
        String[] names = managerNames.split(",");
        if(names.length > 10){
            return new ResponseModel(-1,"管理员不能超过10个");
        }
        for (String name : names){
            Member member = memberService.findByName(name.trim());
            if(member != null){
                if(member.getId().intValue() != loginMember.getId().intValue()){
                    managerIds += member.getId() + ",";
                }
            }
        }
        if(managerIds.length() > 0){
            managerIds = managerIds.substring(0,managerIds.length()-1);
        }
        group.setManagers(managerIds);
        if(groupDao.save(group) == 1){
            //创建者默认关注群组
            groupFansService.save(loginMember,group.getId());
            //申请群组奖励、扣款
            scoreRuleService.scoreRuleBonus(loginMember.getId(), ScoreRuleConsts.APPLY_GROUP, group.getId());
            return new ResponseModel(1,"申请成功，请等待审核");
        }
        return new ResponseModel(-1,"操作失败，请重试");
    }

    @Override
    public ResponseModel update(Member loginMember, Group group) {
        Group findGroup = this.findById(group.getId());
        if(findGroup == null){
            return new ResponseModel(-1,"群组不存在");
        }
        if(loginMember.getId().intValue() != findGroup.getCreator().intValue()){
            return new ResponseModel(-1,"没有权限");
        }

        //设置管理员
        String managerNames = group.getManagers();
        String managerIds = "";
        String[] names = managerNames.split(",");
        if(names.length > 10){
            return new ResponseModel(-1,"管理员不能超过10个");
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
        if(groupDao.update(findGroup) == 1){
            return new ResponseModel(1,"操作成功");
        }

        return new ResponseModel(-1,"操作失败，请重试");
    }

    @Override
    public ResponseModel delete(Member loginMember, int id) {
        Group group = this.findById(id);
        if(group == null){
            return new ResponseModel(-1,"群组不存在");
        }
        if(groupDao.delete(id) == 1){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP,"ID："+group.getId()+"，名字："+group.getName());
            return new ResponseModel(1,"删除成功");
        }
        return new ResponseModel(-1,"删除失败");
    }


}
