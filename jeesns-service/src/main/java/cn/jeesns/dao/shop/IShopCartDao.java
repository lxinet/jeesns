package cn.jeesns.dao.shop;

import cn.jeesns.model.shop.ShopCart;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IShopCartDao extends BaseMapper<ShopCart> {

    List<ShopCart> listByMemberId(@Param("page") Page page, @Param("memberId") Integer memberId);

    ShopCart find(@Param("goodsId") Integer goodsId, @Param("memberId") Integer memberId);

    Integer updateNum(@Param("id") Integer id, @Param("num") Integer num, @Param("memberId") Integer memberId);

    Integer delete(@Param("id") Integer id, @Param("memberId") Integer memberId);

    Integer deleteByMemberId(@Param("memberId") Integer memberId);
}
