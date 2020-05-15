package cn.jeesns.dao.member;


import cn.jeesns.model.member.MemGroup;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员分组DAO
 * Created by zchuanzhao on 16/9/26.
 */
@Mapper
public interface IMemberGroupDao extends BaseMapper<MemGroup> {
    
}