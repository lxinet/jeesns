package cn.jeesns.service.shop;

import cn.jeesns.model.shop.ShopCart;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.dao.shop.IShopCartDao;
import cn.jeesns.model.shop.Goods;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车
 * Created by zchuanzhao on 2019/5/27.
 */
@Service("shopCartService")
public class ShopCartService extends BaseService<ShopCart> {

    @Resource
    private IShopCartDao shopCartDao;
    @Resource
    private GoodsService goodsService;

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

    public Result listByMemberId(Page page, Integer memberId) {
        List<ShopCart> list =  shopCartDao.listByMemberId(page, memberId);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public ShopCart find(Integer goodsId, Integer memberId) {
        return shopCartDao.find(goodsId, memberId);
    }

    public Boolean updateNum(Integer id, Integer num, Integer memberId) {
        ValidUtill.checkParam(num < 1, "数量不能少于1");
        return shopCartDao.updateNum(id, num, memberId) == 1;
    }

    public Boolean delete(Integer id, Integer memberId) {
        return shopCartDao.delete(id, memberId) == 1;
    }

    public Boolean deleteByMemberId(Integer memberId) {
        return shopCartDao.deleteByMemberId(memberId) > 0;
    }

}
