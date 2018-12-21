package com.lxinet.jeesns.service.question;


import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;


/**
 * Created by zchuanzhao on 2018/12/21.
 */
public interface IAnswerService extends IBaseService<Answer> {

    ResultModel listByQuestion(Integer questionId);

    boolean update(Member loginMember, Answer answer);

    boolean delete(Member loginMember, Integer id);

}
