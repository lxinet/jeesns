package cn.jeesns.dao.question;

import cn.jeesns.model.question.QuestionType;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题类型DAO接口
 * Created by zchuanzhao on 2018/12/7.
 */
@Mapper
public interface IQuestionTypeDao extends BaseMapper<QuestionType> {



}