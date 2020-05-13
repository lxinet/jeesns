package com.lxinet.jeesns.service.common.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.common.IAreaDao;
import com.lxinet.jeesns.model.common.Area;
import com.lxinet.jeesns.service.common.IAreaService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 省市区
 * Created by zchuanzhao on 2019/5/16.
 */
@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<Area> implements IAreaService {
    @Resource
    private IAreaDao areaDao;


    @Override
    public List<Area> provinceList() {
        return areaDao.provinceList();
    }

    @Override
    public List<Area> cityList(String provinceCode) {
        return areaDao.cityList(provinceCode);
    }

    @Override
    public List<Area> areaList(String cityCode) {
        return areaDao.areaList(cityCode);
    }
}
