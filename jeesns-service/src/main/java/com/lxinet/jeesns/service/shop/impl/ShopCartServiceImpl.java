package com.lxinet.jeesns.service.shop.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.dao.shop.IShopCartDao;
import com.lxinet.jeesns.model.shop.ShopCart;
import com.lxinet.jeesns.service.shop.IShopCartService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车
 * Created by zchuanzhao on 2019/5/27.
 */
@Service("shopCartService")
public class ShopCartServiceImpl extends BaseServiceImpl<ShopCart> implements IShopCartService {

    @Resource
    private IShopCartDao shopCartDao;

    @Override
    public List<ShopCart> listByMemberId(Integer memberId) {
        return shopCartDao.listByMemberId(memberId);
    }

    @Override
    public Boolean updateNum(Integer id, Integer num, Integer memberId) {
        ValidUtill.checkParam(num < 1, "数量不能少于1");
        return shopCartDao.updateNum(id, num, memberId) == 1;
    }

    @Override
    public Boolean delete(Integer id, Integer memberId) {
        return shopCartDao.delete(id, memberId) == 1;
    }

    @Override
    public Boolean deleteByMemberId(Integer memberId) {
        return shopCartDao.deleteByMemberId(memberId) > 0;
    }

}
