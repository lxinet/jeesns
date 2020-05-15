package cn.jeesns.service.member;

import cn.jeesns.model.member.DeliveryAddress;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.member.IDeliveryAddressDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("deliveryAddressService")
public class DeliveryAddressService extends BaseService<DeliveryAddress> {
    @Resource
    private IDeliveryAddressDao deliveryAddressDao;

    public List<DeliveryAddress> listByMemberId(Integer memberId) {
        return deliveryAddressDao.listByMemberId(memberId);
    }

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
