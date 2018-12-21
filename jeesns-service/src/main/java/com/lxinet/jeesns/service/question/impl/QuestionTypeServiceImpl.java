package com.lxinet.jeesns.service.question.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.question.IQuestionTypeDao;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.IQuestionTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Service("questionTypeService")
public class QuestionTypeServiceImpl extends BaseServiceImpl<QuestionType> implements IQuestionTypeService {

    @Resource
    private IQuestionTypeDao questionTypeDao;


}
