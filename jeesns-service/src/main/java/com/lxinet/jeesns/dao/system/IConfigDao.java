package com.lxinet.jeesns.dao.system;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.system.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */
@Mapper
public interface IConfigDao extends BaseMapper<Config> {

    boolean update(@Param("key") String key,@Param("value") String value);

    Config selectByKey(@Param("key") String key);
}