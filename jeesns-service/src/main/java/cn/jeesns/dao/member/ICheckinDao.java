package cn.jeesns.dao.member;

import cn.jeesns.model.member.Checkin;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员签到DAO
 * Created by zchuanzhao on 18/8/20.
 */
@Mapper
public interface ICheckinDao extends BaseMapper<Checkin> {
    List<Checkin> list(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<Checkin> todayList(@Param("page") Page page);

    List<Checkin> todayContinueList();

    Checkin todayCheckin(@Param("memberId") Integer memberId);

    Checkin yesterdayCheckin(@Param("memberId") Integer memberId);

}