package com.lxinet.jeesns.dao.common;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.common.Ads;
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
