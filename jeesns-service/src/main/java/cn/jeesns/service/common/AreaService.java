package cn.jeesns.service.common;

import cn.jeesns.model.common.Area;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.common.IAreaDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 省市区
 * Created by zchuanzhao on 2019/5/16.
 */
@Service("areaService")
public class AreaService extends BaseService<Area> {
    @Resource
    private IAreaDao areaDao;

    public List<Area> provinceList() {
        return areaDao.provinceList();
    }

    public List<Area> cityList(String provinceCode) {
        return areaDao.cityList(provinceCode);
    }

    public List<Area> areaList(String cityCode) {
        return areaDao.areaList(cityCode);
    }
}
