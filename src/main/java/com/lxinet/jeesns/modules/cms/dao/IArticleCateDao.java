package com.lxinet.jeesns.modules.cms.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章栏目DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */
public interface IArticleCateDao extends IBaseDao<ArticleCate> {

    /**
     * 获取栏目
     * @return
     */
    List<ArticleCate> list();

    /**
     * 通过父类ID获取子类列表
     * @param fid
     * @return
     */
    List<ArticleCate> findListByFid(@Param("fid") int fid);

}