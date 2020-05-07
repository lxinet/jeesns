package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.common.IAdsDao;
import com.lxinet.jeesns.model.common.Ads;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
@Service("adsService")
public class AdsService extends BaseService<Ads> {
    @Resource
    private IAdsDao adsDao;

    public Result listByPage(Page page) {
        List<Ads> list = adsDao.list(page);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    public boolean enable(Integer id) {
        if (adsDao.enable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }
}
