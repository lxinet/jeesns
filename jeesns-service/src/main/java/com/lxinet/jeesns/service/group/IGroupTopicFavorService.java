package com.lxinet.jeesns.service.group;


import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.cms.ArticleFavor;
import com.lxinet.jeesns.model.group.GroupTopicFavor;

/**
 * 文章点赞Service接口
 * Created by zchuanzhao on 2018/11/21.
 */
public interface IGroupTopicFavorService extends IBaseService<GroupTopicFavor> {

    GroupTopicFavor find(Integer groupTopicId, Integer memberId);

    void save(Integer groupTopicId, Integer memberId);

    void delete(Integer groupTopicId, Integer memberId);
}