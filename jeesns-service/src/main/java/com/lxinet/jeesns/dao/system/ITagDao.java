package com.lxinet.jeesns.dao.system;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.system.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ITagDao extends BaseMapper<Tag> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Tag> list(@Param("page") Page page, @Param("funcType") int funcType);

}
