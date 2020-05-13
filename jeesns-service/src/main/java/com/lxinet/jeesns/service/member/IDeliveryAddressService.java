package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.member.DeliveryAddress;

import java.util.List;


public interface IDeliveryAddressService extends IBaseService<DeliveryAddress> {

    List<DeliveryAddress> listByMemberId(Integer memberId);

    Boolean dealDefault(Integer id);

}
