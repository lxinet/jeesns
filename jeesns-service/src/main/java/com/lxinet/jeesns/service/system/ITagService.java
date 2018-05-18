package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.system.Tag;

public interface ITagService {
    ResultModel save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    ResultModel update(Tag tag);

    ResultModel delete(Integer id);

    Tag findById(Integer id);

}
