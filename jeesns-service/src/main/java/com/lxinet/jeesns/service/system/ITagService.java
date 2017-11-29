package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.system.Tag;

public interface ITagService {
    ResponseModel save(Tag tag);

    ResponseModel listByPage(Page page,int funcType);

    ResponseModel update(Tag tag);

    ResponseModel delete(Integer id);

    Tag findById(Integer id);

}
