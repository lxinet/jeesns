package com.lxinet.jeesns.dao.common;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.model.common.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAreaDao extends BaseMapper<Area> {

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
    List<Area> cityList(@Param("provinceCode") String provinceCode);

    /**
     * 获取城市下的地区列表
     * @param cityCode
     * @return
     */
    List<Area> areaList(@Param("cityCode") String cityCode);
}
