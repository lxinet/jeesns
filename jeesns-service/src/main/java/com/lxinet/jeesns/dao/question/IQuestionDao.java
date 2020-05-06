package com.lxinet.jeesns.dao.question;


import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.question.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题DAO接口
 * Created by zchuanzhao on 2018/12/7.
 */
@Mapper
public interface IQuestionDao extends BaseMapper<Question> {

    List<Question> list(@Param("page") Page page, @Param("typeId") Integer typeId, @Param("status") Integer status);

    Question findById(@Param("id") Integer id);

    Integer updateStatus(@Param("status") Integer status, @Param("id") Integer id);

    Integer setBestAnswer(@Param("answerId") Integer answerId, @Param("id") Integer id);

    Integer updateAnswerCount(@Param("id") Integer id);

    /**
     * 更新阅读数
     * @param id
     * @return
     */
    int updateViewCount(@Param("id") int id);
}