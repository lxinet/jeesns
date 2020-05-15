package cn.jeesns.dao.weibo;

import cn.jeesns.model.weibo.WeiboTopic;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Mapper
public interface IWeiboTopicDao extends BaseMapper<WeiboTopic> {

    WeiboTopic findByName(@Param("name") String name);

}