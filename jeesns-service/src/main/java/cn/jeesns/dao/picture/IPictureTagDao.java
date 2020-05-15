package cn.jeesns.dao.picture;

import cn.jeesns.model.picture.PictureTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPictureTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureTag record);

    int insertSelective(PictureTag record);

    PictureTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureTag record);

    int updateByPrimaryKey(PictureTag record);
}