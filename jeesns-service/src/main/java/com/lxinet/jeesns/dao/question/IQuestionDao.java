package com.lxinet.jeesns.dao.question;


import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.question.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题DAO接口
 * Created by zchuanzhao on 2018/12/7.
 */
public interface IQuestionDao extends BaseMapper<Question> {

    List<Question> list(@Param("page") Page page);

    Question findById(@Param("id") Integer id);
}