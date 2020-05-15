package cn.jeesns.dao.common;

import cn.jeesns.model.common.Link;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
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
