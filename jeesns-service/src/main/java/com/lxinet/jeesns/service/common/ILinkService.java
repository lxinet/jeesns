package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.common.Link;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkService extends IBaseService<Link> {

    ResultModel listByPage(Page page);

    ResultModel allList();

    ResultModel recommentList();

    boolean enable(Integer id);
}
