package com.lxinet.jeesns.dao.weibo;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.weibo.WeiboTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Mapper
public interface IWeiboTopicDao extends BaseMapper<WeiboTopic> {

    WeiboTopic findByName(@Param("name") String name);

}