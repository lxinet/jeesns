package com.lxinet.jeesns.dao.member;


import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.member.MemGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员分组DAO
 * Created by zchuanzhao on 16/9/26.
 */
@Mapper
public interface IMemberGroupDao extends BaseMapper<MemGroup> {
    
}