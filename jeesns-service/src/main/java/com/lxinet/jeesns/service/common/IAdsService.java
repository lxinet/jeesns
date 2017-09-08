package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.common.Ads;

/**
 * Created by MMF on 2017-09-07.
 */
public interface IAdsService {
    /**
     * 保存广告信息
     * @param ads
     * @return
     */
    ResponseModel save(Ads ads);
    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    ResponseModel listByPage(Page page);

    ResponseModel update(Ads ads);

    ResponseModel delete(Integer id);

    Ads findById(Integer id);

    ResponseModel enable(Integer id);
}
