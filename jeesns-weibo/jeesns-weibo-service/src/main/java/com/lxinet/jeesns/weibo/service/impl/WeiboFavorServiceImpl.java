package com.lxinet.jeesns.weibo.service.impl;

import com.lxinet.jeesns.weibo.dao.IWeiboFavorDao;
import com.lxinet.jeesns.weibo.model.WeiboFavor;
import com.lxinet.jeesns.weibo.service.IWeiboFavorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/8.
 */
@Service("weiboFavorService")
public class WeiboFavorServiceImpl implements IWeiboFavorService {
    @Resource
    private IWeiboFavorDao weiboFavorDao;


    @Override
    public WeiboFavor find(Integer weiboId, Integer memberId) {
        return weiboFavorDao.find(weiboId,memberId);
    }

    @Override
    public void save(Integer weiboId, Integer memberId) {
        weiboFavorDao.save(weiboId,memberId);
    }

    @Override
    public void delete(Integer weiboId, Integer memberId) {
        weiboFavorDao.delete(weiboId,memberId);
    }
}
