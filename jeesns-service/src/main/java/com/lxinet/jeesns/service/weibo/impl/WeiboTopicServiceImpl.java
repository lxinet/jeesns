package com.lxinet.jeesns.service.weibo.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.weibo.IWeiboTopicDao;
import com.lxinet.jeesns.model.weibo.WeiboTopic;
import com.lxinet.jeesns.service.weibo.IWeiboTopicService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Service("weiboTopicService")
public class WeiboTopicServiceImpl extends BaseServiceImpl<WeiboTopic> implements IWeiboTopicService {
    @Resource
    private IWeiboTopicDao weiboTopicDao;

    @Override
    public WeiboTopic findByName(String name) {
        return weiboTopicDao.findByName(name);
    }

}
