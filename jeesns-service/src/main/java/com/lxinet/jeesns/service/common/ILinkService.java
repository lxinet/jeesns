package com.lxinet.jeesns.service.common;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.common.Link;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
public interface ILinkService {
   
    ResponseModel save(Link link);
   
    ResponseModel listByPage(Page page);

    ResponseModel allList();

    ResponseModel recommentList();

    ResponseModel update(Link link);

    ResponseModel delete(Integer id);

    Link findById(Integer id);

    ResponseModel enable(Integer id);
}
