package cn.jeesns.dao.member;

import cn.jeesns.model.member.Financial;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Mapper
public interface IFinancialDao extends BaseMapper<Financial> {

    List<Financial> list(@Param("page") Page page, @Param("memberId") Integer memberId);

}