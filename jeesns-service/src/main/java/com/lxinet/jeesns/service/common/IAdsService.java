package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.common.Ads;

/**
 * Created by zchuanzhao on 2017/9/07.
 */
public interface IAdsService extends IBaseService<Ads> {

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    ResultModel listByPage(Page page);

    boolean enable(Integer id);
}
