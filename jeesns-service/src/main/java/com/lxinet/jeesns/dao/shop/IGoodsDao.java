package com.lxinet.jeesns.dao.shop;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.shop.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品DAO接口
 * Created by zchuanzhao on 2019/5/15.
 */
public interface IGoodsDao extends BaseMapper<Goods> {

    List<Goods> list(@Param("page") Page page, @Param("key") String key, @Param("cateid") Integer cateid, @Param("status") Integer status);


    Goods findById(@Param("id") Integer id);

    /**
     * 设置某个分类的所有商品为未分类商品，一般在删除某个分类的时候调用
     * @param cateid
     * @return
     */
    int setGoodsAsNoneCate(@Param("cateid") int cateid);

    /**
     * 修改商品状态
     * @param id
     * @return
     */
    int changeStatus(@Param("id") int id);

    /**
     * 自定义条件查询
     * @param cid 分类ID，0不限制
     * @param sort 排序字段
     * @param num 获取数量
     * @param day 天，获取多少天之内的数据，0不限制
     * @param thumbnail 缩略图 0不限制，1必须有缩略图
     * @return
     */
    List<Goods> listByCustom(@Param("cid") int cid, @Param("sort") String sort, @Param("num") int num, @Param("day") int day, @Param("thumbnail") int thumbnail);

    /**
     * 更新阅读数
     * @param id
     * @return
     */
    int updateViewCount(@Param("id") int id);

}