package com.lxinet.jeesns.service.shop.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.dao.shop.IShopCartDao;
import com.lxinet.jeesns.model.shop.Goods;
import com.lxinet.jeesns.model.shop.ShopCart;
import com.lxinet.jeesns.service.shop.IGoodsService;
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
    @Resource
    private IGoodsService goodsService;

    @Override
    public boolean save(ShopCart shopCart) {
        ShopCart findShopCart = this.find(shopCart.getGoodsId(), shopCart.getMemberId());
        Goods goods = goodsService.findById(shopCart.getGoodsId());
        if (findShopCart == null){
            shopCart.setUnitPrice(goods.getPrice());
            shopCart.setTotalPrice(goods.getPrice() * shopCart.getNum());
            super.save(shopCart);
        }else {
            findShopCart.setNum(findShopCart.getNum() + shopCart.getNum());
            findShopCart.setUnitPrice(goods.getPrice());
            findShopCart.setTotalPrice(goods.getPrice() * findShopCart.getNum());
            super.update(findShopCart);
        }
        return true;
    }

    @Override
    public ResultModel listByMemberId(Page page, Integer memberId) {
        List<ShopCart> list =  shopCartDao.listByMemberId(page, memberId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ShopCart find(Integer goodsId, Integer memberId) {
        return shopCartDao.find(goodsId, memberId);
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
