package cn.jeesns.dao.member;

import cn.jeesns.model.member.DeliveryAddress;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Mapper
public interface IDeliveryAddressDao extends BaseMapper<DeliveryAddress> {

    List<DeliveryAddress> listByMemberId(Integer memberId);

    Boolean dealDefault(Integer id);

}