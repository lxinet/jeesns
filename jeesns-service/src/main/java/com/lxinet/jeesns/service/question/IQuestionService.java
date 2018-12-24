package com.lxinet.jeesns.service.question;


import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Question;


/**
 * Created by zchuanzhao on 2018/12/7.
 */
public interface IQuestionService extends IBaseService<Question> {


    ResultModel list(Integer typeId, String statusName);

    boolean update(Member loginMember, Question question);

    boolean delete(Member loginMember, Integer id);

    void close(Member loginMember, Integer id);

    void bestAnswer(Member loginMember, Integer answerId, Integer id);

    void updateStatus(Integer status, Question question);

    Integer updateAnswerCount(Integer id);

    Integer setBestAnswer(Integer answerId, Integer id);

    void updateViewCount(Integer id);
}
