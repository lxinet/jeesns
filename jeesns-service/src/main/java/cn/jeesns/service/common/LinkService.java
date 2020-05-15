package cn.jeesns.service.common;

import cn.jeesns.core.conditions.SqlWrapper;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.dao.common.ILinkDao;
import cn.jeesns.model.common.Link;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
@Service("linkService")
public class LinkService extends BaseService<Link> {
    @Resource
    private ILinkDao linkDao;

    public Result listByPage(Page page) {
        List<Link> list = linkDao.list(page);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    public Result allList() {
        List<Link> list = linkDao.listAll(new SqlWrapper<>(Link.class));
        Result model = new Result(0);
        model.setData(list);
        return model;
    }

    public Result recommentList() {
        List<Link> list = linkDao.recommentList();
        Result model = new Result(0);
        model.setData(list);
        return model;
    }

    public boolean enable(Integer id) {
       return linkDao.enable(id) == 1;
    }
}
