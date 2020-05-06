package com.lxinet.jeesns.dao.question;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.model.question.QuestionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题类型DAO接口
 * Created by zchuanzhao on 2018/12/7.
 */
@Mapper
public interface IQuestionTypeDao extends BaseMapper<QuestionType> {



}