package com.lxinet.jeesns.service.shop.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.utils.HtmlUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.dao.shop.IGoodsDao;
import com.lxinet.jeesns.enums.GoodsStatue;
import com.lxinet.jeesns.model.shop.Goods;
import com.lxinet.jeesns.service.shop.IGoodsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * Created by zchuanzhao on 2019/5/15.
 */
@Service("goodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements IGoodsService {
    @Resource
    private IGoodsDao goodsDao;

    @Override
    public Goods findById(int id) {
        return this.findById(id);
    }

    @Override
    @Transactional
    public boolean save(Goods goods) {
        if(goods.getCateId() == null || goods.getCateId() == 0){
            throw new ParamException("分类不能为空");
        }
        if (goods.getViewCount() == null) {
            goods.setViewCount(0);
        }
        if (StringUtils.isEmpty(goods.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(goods.getContent());
            if (contentStr.length() > 200) {
                goods.setDescription(contentStr.substring(0, 200));
            } else {
                goods.setDescription(contentStr);
            }
        }
        if (StringUtils.isEmpty(goods.getThumbnail())) {
            Document doc = Jsoup.parseBodyFragment(goods.getContent());
            Elements elements = doc.select("img[src]");
            if (elements.size() > 0) {
                String imgsrc = elements.get(0).attr("src");
                goods.setThumbnail(imgsrc);
            }else{
                goods.setThumbnail(null);
            }
        }
        boolean result = super.save(goods);
        return true;
    }

    @Override
    public ResultModel listByPage(Page page, String key, int cateid, int status) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<Goods> list = goodsDao.list(page, key,cateid, status);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void updateViewCount(int id) {
        goodsDao.updateViewCount(id);
    }

    @Override
    public boolean changeStatus(int id) {
        if(goodsDao.changeStatus(id) == 0){
             throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public List<Goods> listByCustom(int cid, String sort, int num, int day,int thumbnail) {
        return goodsDao.listByCustom(cid,sort,num,day,thumbnail);
    }

    @Override
    @Transactional
    public boolean update(Goods goods) {
        Goods findGoods = this.findById(goods.getId());
        ValidUtill.checkIsNull(findGoods, "商品不存在");
        findGoods.setStatus(goods.getStatus());
        //更新栏目
        findGoods.setCateId(goods.getCateId());
        findGoods.setTitle(goods.getTitle());
        findGoods.setSubtitle(goods.getSubtitle());
        findGoods.setThumbnail(goods.getThumbnail());
        findGoods.setContent(goods.getContent());
        findGoods.setDescription(goods.getDescription());
        findGoods.setKeywords(goods.getKeywords());
        findGoods.setPrice(goods.getPrice());
        findGoods.setStock(goods.getStock());
        findGoods.setNo(goods.getNo());
        findGoods.setUpdateTime(new Date());
        if (StringUtils.isEmpty(findGoods.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(findGoods.getContent());
            if (contentStr.length() > 200) {
                findGoods.setDescription(contentStr.substring(0, 200));
            } else {
                findGoods.setDescription(contentStr);
            }
        }
        if (StringUtils.isEmpty(findGoods.getThumbnail())) {
            Document doc = Jsoup.parseBodyFragment(findGoods.getContent());
            Elements elements = doc.select("img[src]");
            if (elements.size() > 0) {
                String imgsrc = elements.get(0).attr("src");
                findGoods.setThumbnail(imgsrc);
            }else {
                findGoods.setThumbnail(null);
            }
        }
        goodsDao.updateObj(findGoods);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        boolean result = super.deleteById(id);
        if(!result){
            throw new OpeErrorException();
        }
        return true;
    }

}
