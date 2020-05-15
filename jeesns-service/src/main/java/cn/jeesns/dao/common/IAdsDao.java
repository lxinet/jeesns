package cn.jeesns.dao.common;

import cn.jeesns.model.common.Ads;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
@Mapper
public interface IAdsDao extends BaseMapper<Ads> {

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    List<Ads> list(@Param("page") Page page);

    int enable(@Param("id") Integer id);
}
