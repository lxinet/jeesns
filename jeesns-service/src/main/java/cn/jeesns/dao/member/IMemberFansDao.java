package cn.jeesns.dao.member;

import cn.jeesns.model.member.MemberFans;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/16.
 */
@Mapper
public interface IMemberFansDao extends BaseMapper<MemberFans> {

    List<MemberFans> followsList(@Param("page") Page page, @Param("whoFollowId") Integer whoFollowId);

    List<MemberFans> fansList(@Param("page") Page page, @Param("followWhoId") Integer followWhoId);

    MemberFans find(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer save(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer delete(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);
}