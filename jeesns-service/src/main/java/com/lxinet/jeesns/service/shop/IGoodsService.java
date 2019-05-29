package com.lxinet.jeesns.service.shop;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.enums.GoodsStatue;
import com.lxinet.jeesns.model.shop.Goods;
import java.util.List;


/**
 * 商品
 * Created by zchuanzhao on 2019/5/15.
 */
public interface IGoodsService extends IBaseService<Goods> {

    Goods findById(int id);

    boolean delete(int id);

    ResultModel listByPage(Page page, String key, int cateid, int status);

    void updateViewCount(int id);

    boolean changeStatus(int id);

    List<Goods> listByCustom(int cid, String sort, int num, int day, int thumbnail);
}
