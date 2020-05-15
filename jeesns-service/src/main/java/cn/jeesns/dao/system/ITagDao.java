package cn.jeesns.dao.system;

import cn.jeesns.model.system.Tag;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
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
