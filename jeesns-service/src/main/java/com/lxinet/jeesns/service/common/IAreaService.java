package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.common.Area;

import java.util.List;

/**
 * 省市区
 * Created by zchuanzhao on 2019/5/16.
 */
public interface IAreaService extends IBaseService<Area> {

    /**
     * 获取省份列表
     * @return
     */
    List<Area> provinceList();

    /**
     * 获取省份下的城市列表
     * @param provinceCode
     * @return
     */
    List<Area> cityList(String provinceCode);

    /**
     * 获取城市下的地区列表
     * @param cityCode
     * @return
     */
    List<Area> areaList(String cityCode);
}
