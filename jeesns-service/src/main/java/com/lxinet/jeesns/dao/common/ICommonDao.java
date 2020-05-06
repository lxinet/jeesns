package com.lxinet.jeesns.dao.common;


import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zchuanzhao on 2017/2/6.
 */
@Mapper
public interface ICommonDao extends IBaseDao {

    String getMysqlVsesion();
    
}