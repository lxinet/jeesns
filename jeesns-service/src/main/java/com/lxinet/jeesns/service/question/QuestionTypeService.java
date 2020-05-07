package com.lxinet.jeesns.service.question;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.question.IQuestionTypeDao;
import com.lxinet.jeesns.model.question.QuestionType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Service("questionTypeService")
public class QuestionTypeService extends BaseService<QuestionType> {

    @Resource
    private IQuestionTypeDao questionTypeDao;


}
