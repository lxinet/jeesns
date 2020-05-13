package com.lxinet.jeesns.service.member.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.member.IDeliveryAddressDao;
import com.lxinet.jeesns.model.member.DeliveryAddress;
import com.lxinet.jeesns.service.member.IDeliveryAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("deliveryAddressService")
public class DeliveryAddressServiceImpl extends BaseServiceImpl<DeliveryAddress> implements IDeliveryAddressService {
    @Resource
    private IDeliveryAddressDao deliveryAddressDao;

    @Override
    public List<DeliveryAddress> listByMemberId(Integer memberId) {
        return deliveryAddressDao.listByMemberId(memberId);
    }

    @Override
    public Boolean dealDefault(Integer id) {
        return deliveryAddressDao.dealDefault(id);
    }

    @Override
    public boolean save(DeliveryAddress deliveryAddress) {
        boolean result = super.save(deliveryAddress);
        if (deliveryAddress.getIsDefault() == 1 && result){
            dealDefault(deliveryAddress.getId());
        }
        return result;
    }

    @Override
    public boolean update(DeliveryAddress deliveryAddress) {
        boolean result = super.update(deliveryAddress);
        if (deliveryAddress.getIsDefault() == 1 && result){
            dealDefault(deliveryAddress.getId());
        }
        return result;
    }
}
