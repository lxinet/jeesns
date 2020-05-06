package com.lxinet.jeesns.service.weibo;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.weibo.IWeiboFavorDao;
import com.lxinet.jeesns.model.weibo.WeiboFavor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/8.
 */
@Service("weiboFavorService")
public class WeiboFavorService extends BaseServiceImpl<WeiboFavor> {
    @Resource
    private IWeiboFavorDao weiboFavorDao;


    public WeiboFavor find(Integer weiboId, Integer memberId) {
        return weiboFavorDao.find(weiboId,memberId);
    }

    public void save(Integer weiboId, Integer memberId) {
        weiboFavorDao.save(weiboId,memberId);
    }

    public void delete(Integer weiboId, Integer memberId) {
        weiboFavorDao.delete(weiboId,memberId);
    }
}
