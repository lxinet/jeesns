package cn.jeesns.service.weibo;

import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.weibo.IWeiboTopicDao;
import cn.jeesns.model.weibo.WeiboTopic;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
@Service("weiboTopicService")
public class WeiboTopicService extends BaseService<WeiboTopic> {
    @Resource
    private IWeiboTopicDao weiboTopicDao;

    public WeiboTopic findByName(String name) {
        return weiboTopicDao.findByName(name);
    }

}
