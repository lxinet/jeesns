package cn.jeesns.service.question;

import cn.jeesns.dao.question.IAnswerDao;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.question.Answer;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.exception.OpeErrorException;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.core.utils.PageUtil;
import cn.jeesns.core.utils.ValidUtill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/12/21.
 */
@Service("answerService")
public class AnswerService extends BaseService<Answer> {

    @Resource
    private IAnswerDao answerDao;
    @Resource
    private QuestionService questionService;


    public Result<Answer> listByQuestion(Integer questionId) {
        List<Answer> list = answerDao.listByQuestion(PageUtil.getPage(), questionId);
        Result model = new Result(0,PageUtil.getPage());
        model.setData(list);
        return model;
    }

    @Override
    public Answer findById(Integer id) {
        return answerDao.findById(id);
    }

    @Override
    public boolean save(Answer answer) {
        ValidUtill.checkIsBlank(answer.getContent(), "回答不能为空");
        super.save(answer);
        questionService.updateAnswerCount(answer.getQuestionId());
        return true;
    }

    public boolean update(Member loginMember, Answer answer) {

        return super.update(answer);
    }

    public boolean delete(Member loginMember, Integer id) {
        Answer findAnswer = findById(id);
        if(loginMember.getId().intValue() == findAnswer.getMember().getId().intValue() || loginMember.getIsAdmin() > 0){
            return super.deleteById(id);
        }
        throw new OpeErrorException("没有权限");
    }

}
