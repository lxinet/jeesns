package com.lxinet.jeesns.service.shop.impl;

import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.cms.IArticleCateDao;
import com.lxinet.jeesns.dao.shop.IGoodsCateDao;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.model.shop.GoodsCate;
import com.lxinet.jeesns.service.cms.IArticleCateService;
import com.lxinet.jeesns.service.shop.IGoodsCateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类
 * Created by zchuanzhao on 2019/5/15.
 */
@Service("goodsCateService")
public class GoodsCateServiceImpl extends BaseServiceImpl<GoodsCate> implements IGoodsCateService {

    @Resource
    private IGoodsCateDao goodsCateDao;

    @Override
    public boolean save(GoodsCate goodsCate) {
        if(goodsCate.getFid() == null){
            goodsCate.setFid(0);
            goodsCate.setLevel(1);
        }else {
            goodsCate.setLevel(2);
        }
        if(goodsCate.getFid() != 0){
            GoodsCate fatherGoodsCate = this.findById(goodsCate.getFid());
            if(fatherGoodsCate == null){
                throw new ParamException("上级分类不存在");
            }
            if(fatherGoodsCate.getFid() != 0){
                throw new OpeErrorException("只要顶级分类才可以添加下级分类");
            }
        }
        if (!super.save(goodsCate)){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean update(GoodsCate goodsCate) {
        GoodsCate findGoodsCate =this.findById(goodsCate.getId());
        if(findGoodsCate == null){
            throw new ParamException("分类不存在");
        }
        if(goodsCate.getFid() == null){
            goodsCate.setFid(0);
            goodsCate.setLevel(1);
        }else {
            goodsCate.setLevel(2);
        }
        if(goodsCate.getFid().intValue() == goodsCate.getId().intValue()){
            throw new ParamException("上级分类不能是自己");
        }
        if(goodsCate.getFid() != 0){
            GoodsCate fatherGoodsCate = this.findById(goodsCate.getFid());
            if(fatherGoodsCate == null){
                throw new ParamException("上级分类不存在");
            }
            if(fatherGoodsCate.getFid() != 0){
                throw new OpeErrorException("只要顶级分类才可以添加下级分类");
            }
        }
        findGoodsCate.setFid(goodsCate.getFid());
        findGoodsCate.setName(goodsCate.getName());
        findGoodsCate.setSort(goodsCate.getSort());
        findGoodsCate.setLevel(goodsCate.getLevel());
        findGoodsCate.setStatus(goodsCate.getStatus());
        if (!super.update(findGoodsCate)){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        List sonList = this.findListByFid(id);
        if(sonList.size() > 0){
            throw new OpeErrorException("请先删除下级分类");
        }
        boolean result = super.deleteById(id);
        if(!result){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public List<GoodsCate> list() {
        return goodsCateDao.list();
    }

    @Override
    public List<GoodsCate> topList() {
        return goodsCateDao.topList();
    }

    @Override
    public List<GoodsCate> sonList() {
        return goodsCateDao.sonList();
    }

    @Override
    public List<GoodsCate> findListByFid(int fid) {
        return goodsCateDao.findListByFid(fid);
    }

}
