package cn.jeesns.service.question;

import cn.jeesns.dao.question.IQuestionTypeDao;
import cn.jeesns.model.question.QuestionType;
import cn.jeesns.core.service.BaseService;
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
