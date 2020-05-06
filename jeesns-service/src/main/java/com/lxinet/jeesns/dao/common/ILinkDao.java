package com.lxinet.jeesns.dao.common;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.common.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-10-13.
 */
@Mapper
public interface ILinkDao extends BaseMapper<Link> {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<Link> list(@Param("page") Page page);

    List<Link> recommentList();

    int enable(@Param("id") Integer id);
}
