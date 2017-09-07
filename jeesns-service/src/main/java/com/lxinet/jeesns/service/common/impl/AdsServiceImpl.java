package com.lxinet.jeesns.service.common.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IAdsDao;
import com.lxinet.jeesns.model.common.Ads;
import com.lxinet.jeesns.model.group.GroupFans;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.common.IAdsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MMF on 2017-09-07.
 */
@Service("adsService")
public class AdsServiceImpl implements IAdsService {
    @Resource
    private IAdsDao adsDao;

    /**
     * 保存广告信息
     *
     * @param ads
     * @return
     */
    @Override
    public ResponseModel save(Ads ads) {
        if (adsDao.save(ads) == 1) {
            return new ResponseModel(1, "插入广告成功");
        }
        return new ResponseModel(-1, "插入广告失败");
    }

    @Override
    public ResponseModel listByPage(Page page) {
        List<Ads> list = adsDao.listByPage(page);
        ResponseModel model = new ResponseModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel update(Ads ads) {
        Ads findAds = this.findByID(ads.getId());
        if (findAds == null){
            return new ResponseModel(1, "广告不存在");
        }
        ads.setCreateTime(findAds.getCreateTime());
        if (adsDao.update(ads) > 0) {
            return new ResponseModel(1, "更新广告成功");
        }
        return new ResponseModel(-1, "更新广告失败");
    }

    @Override
    public ResponseModel delete(Integer id) {
        if (adsDao.delete(id) > 0) {
            return new ResponseModel(1, "删除广告成功");
        }
        return new ResponseModel(-1, "删除广告失败");
    }

    @Override
    public Ads findByID(Integer id) {
        return adsDao.findById(id);
    }
}
