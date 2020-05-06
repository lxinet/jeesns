package com.lxinet.jeesns.service.weibo;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.weibo.IWeiboTopicDao;
import com.lxinet.jeesns.model.weibo.WeiboTopic;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Service("weiboTopicService")
public class WeiboTopicService extends BaseServiceImpl<WeiboTopic> {
    @Resource
    private IWeiboTopicDao weiboTopicDao;

    public WeiboTopic findByName(String name) {
        return weiboTopicDao.findByName(name);
    }

}
