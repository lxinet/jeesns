package cn.jeesns.dao.common;

import cn.jeesns.model.common.Area;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
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
