package cn.jeesns.service.common;

import cn.jeesns.core.dto.Result;
import cn.jeesns.core.exception.OpeErrorException;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.common.IAdsDao;
import cn.jeesns.model.common.Ads;
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
