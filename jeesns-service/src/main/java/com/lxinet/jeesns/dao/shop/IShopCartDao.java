package com.lxinet.jeesns.dao.shop;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.shop.ShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IShopCartDao extends BaseMapper<ShopCart> {

    List<ShopCart> listByMemberId(@Param("page") Page page, @Param("memberId") Integer memberId);

    ShopCart find(@Param("goodsId") Integer goodsId, @Param("memberId") Integer memberId);

    Integer updateNum(@Param("id") Integer id, @Param("num") Integer num, @Param("memberId") Integer memberId);

    Integer delete(@Param("id") Integer id, @Param("memberId") Integer memberId);

    Integer deleteByMemberId(@Param("memberId") Integer memberId);
}
