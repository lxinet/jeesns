package com.lxinet.jeesns.dao.question;


import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题回答DAO接口
 * Created by zchuanzhao on 2018/12/21.
 */
@Mapper
public interface IAnswerDao extends BaseMapper<Answer> {

    List<Answer> listByQuestion(@Param("page") Page page,@Param("questionId") Integer questionId);

    Answer findById(@Param("id") Integer id);
}