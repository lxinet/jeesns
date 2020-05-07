package com.lxinet.jeesns.service.question;

import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.core.utils.HtmlUtil;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.dao.question.IQuestionDao;
import com.lxinet.jeesns.model.member.Financial;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.member.ScoreDetail;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.member.FinancialService;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.ScoreDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Service("questionService")
public class QuestionService extends BaseService<Question> {

    @Resource
    private IQuestionDao questionDao;
    @Resource
    private QuestionTypeService questionTypeService;
    @Resource
    private MemberService memberService;
    @Resource
    private FinancialService financialService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private AnswerService answerService;


    public Result<Question> list(Integer typeId, String statusName) {
        Integer status = -2;
        if ("close".equalsIgnoreCase(statusName)){
            status = -1;
        }else if ("unsolved".equalsIgnoreCase(statusName)){
            status = 0;
        }else if ("solved".equalsIgnoreCase(statusName)){
            status = 1;
        }
        List<Question> list = questionDao.list(PageUtil.getPage(), typeId, status);
        Result model = new Result(0,PageUtil.getPage());
        model.setData(list);
        return model;
    }

    @Override
    public Question findById(Integer id) {
        return questionDao.findById(id);
    }

    @Override
    @Transactional
    public boolean save(Question question) {
        ValidUtill.checkParam(question.getMemberId() == null, "用户不能为空");
        ValidUtill.checkParam(question.getTypeId() == null, "类型不能为空");
        ValidUtill.checkParam(question.getTitle() == null, "标题不能为空");
        ValidUtill.checkParam(question.getBonus() < 0, "悬赏金额不能小于0");
        QuestionType questionType = questionTypeService.findById(question.getTypeId());
        ValidUtill.checkIsNull(questionType, "问答分类不存在");
        Member member = memberService.findById(question.getMemberId());
        if (StringUtils.isEmpty(question.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(question.getContent());
            if (contentStr.length() > 200) {
                question.setDescription(contentStr.substring(0, 200));
            } else {
                question.setDescription(contentStr);
            }
        }
        super.save(question);
        if (question.getBonus() > 0){
            if (questionType.getBonusType() == 0){
                //积分
                ValidUtill.checkParam(member.getScore().intValue() < question.getBonus().intValue(), "账户积分不足，无法悬赏，账户积分余额为："+member.getScore());
                memberService.updateScore(-question.getBonus(), member.getId());
                ScoreDetail scoreDetail = new ScoreDetail();
                scoreDetail.setType(1);
                scoreDetail.setMemberId(member.getId());
                scoreDetail.setForeignId(question.getId());
                scoreDetail.setScore(-question.getBonus());
                scoreDetail.setRemark("问答悬赏：" + question.getTitle() + "#" + question.getId());
                scoreDetailService.save(scoreDetail);
            }else if (questionType.getBonusType() == 1){
                //现金
                ValidUtill.checkParam(member.getMoney() < question.getBonus().intValue(), "账户余额不足，无法悬赏，账户余额为："+member.getMoney());
                memberService.updateMoney(-(double)question.getBonus(), member.getId());
                //添加财务明细
                Financial financial = new Financial();
                financial.setBalance(member.getMoney() - question.getBonus());
                financial.setForeignId(question.getId());
                financial.setMemberId(member.getId());
                financial.setMoney((double)question.getBonus());
                financial.setType(1);
                //1为余额支付
                financial.setPaymentId(1);
                financial.setRemark("问答悬赏：" + question.getTitle() + "#" + question.getId());
                financial.setOperator(member.getName());
                financialService.save(financial);
            }
        }
        return true;
    }

    public boolean update(Member loginMember, Question question) {
        Question findQuestion = findById(question.getId());
        ValidUtill.checkIsNull(findQuestion, "问题不存在");
        ValidUtill.checkParam(question.getTitle() == null, "标题不能为空");
        if (StringUtils.isEmpty(question.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(question.getContent());
            if (contentStr.length() > 200) {
                findQuestion.setDescription(contentStr.substring(0, 200));
            } else {
                findQuestion.setDescription(contentStr);
            }
        }
        findQuestion.setTitle(question.getTitle());
        findQuestion.setContent(question.getContent());
        return super.update(findQuestion);
    }

    public boolean delete(Member loginMember, Integer id) {
        Question findQuestion = findById(id);
        ValidUtill.checkParam(findQuestion.getAnswerCount() > 0, "该问题已有人回复，无法删除");
        if(loginMember.getId().intValue() == findQuestion.getMember().getId().intValue() || loginMember.getIsAdmin() > 0){
            return super.deleteById(id);
        }
        throw new OpeErrorException("没有权限");
    }

    public void close(Member loginMember, Integer id) {
        Question question = findById(id);
        ValidUtill.checkParam(question.getAnswerCount() > 0, "该问题已有人回复，无法关闭");
        if(loginMember.getId().intValue() == question.getMember().getId().intValue() || loginMember.getIsAdmin() > 0){
            updateStatus(-1, question);
            //关闭问题返还悬赏金额
            if (question.getBonus() > 0){
                if (question.getQuestionType().getBonusType() == 0){
                    //积分
                    memberService.updateScore(question.getBonus(), question.getMemberId());
                    ScoreDetail scoreDetail = new ScoreDetail();
                    scoreDetail.setType(1);
                    scoreDetail.setMemberId(question.getMemberId());
                    scoreDetail.setForeignId(question.getId());
                    scoreDetail.setScore(question.getBonus());
                    scoreDetail.setRemark("问答关闭，悬赏返还：" + question.getTitle() + "#" + question.getId());
                    scoreDetailService.save(scoreDetail);
                }else if (question.getQuestionType().getBonusType() == 1){
                    //现金
                    memberService.updateMoney((double)question.getBonus(), question.getMemberId());
                    Member member = memberService.findById(question.getMemberId());
                    //添加财务明细
                    Financial financial = new Financial();
                    financial.setBalance(member.getMoney() + question.getBonus());
                    financial.setForeignId(question.getId());
                    financial.setMemberId(member.getId());
                    financial.setMoney((double)question.getBonus());
                    financial.setType(0);
                    //1为余额支付
                    financial.setPaymentId(1);
                    financial.setRemark("问答关闭，悬赏返还：" + question.getTitle() + "#" + question.getId());
                    financial.setOperator(member.getName());
                    financialService.save(financial);
                }
            }
        }else {
            throw new OpeErrorException("没有权限");
        }
    }

    @Transactional
    public void bestAnswer(Member loginMember, Integer answerId, Integer id) {
        Question question = findById(id);
        ValidUtill.checkParam(question.getStatus() == 1, "该问题已解决，无法重新采用最佳答案");
        ValidUtill.checkParam(question.getStatus() == -1, "该问题已关闭，无法操作");
        if(loginMember.getId().intValue() == question.getMember().getId().intValue()){
            Answer answer = answerService.findById(answerId);
            //问题ID与回答的问题ID不一致
            ValidUtill.checkParam(answer.getQuestionId().intValue() != id, "非法操作");
            ValidUtill.checkParam(answer.getMemberId().intValue() == question.getMemberId().intValue(), "最佳答案不能是自己的回答");
            //设置最佳回答
            setBestAnswer(answerId, id);
            //设置最佳答案，答主获取奖励
            if (question.getBonus() > 0){
                if (question.getQuestionType().getBonusType() == 0){
                    //积分
                    memberService.updateScore(question.getBonus(), answer.getMemberId());
                    ScoreDetail scoreDetail = new ScoreDetail();
                    scoreDetail.setType(1);
                    scoreDetail.setMemberId(answer.getMemberId());
                    scoreDetail.setForeignId(answer.getId());
                    scoreDetail.setScore(question.getBonus());
                    scoreDetail.setRemark("回答被采用：" + question.getTitle() + "#" + question.getId());
                    scoreDetailService.save(scoreDetail);
                }else if (question.getQuestionType().getBonusType() == 1){
                    //现金
                    memberService.updateMoney((double)question.getBonus(), answer.getMemberId());
                    Member member = memberService.findById(answer.getMemberId());
                    //添加财务明细
                    Financial financial = new Financial();
                    financial.setBalance(member.getMoney() + question.getBonus());
                    financial.setForeignId(answer.getId());
                    financial.setMemberId(member.getId());
                    financial.setMoney((double)question.getBonus());
                    financial.setType(0);
                    //1为余额支付
                    financial.setPaymentId(1);
                    financial.setRemark("回答被采用：" + question.getTitle() + "#" + question.getId());
                    financial.setOperator(member.getName());
                    financialService.save(financial);
                }
            }
        }else {
            throw new OpeErrorException("没有权限");
        }
    }

    public void updateStatus(Integer status, Question question) {
        ValidUtill.checkParam(question.getStatus() != 0, "该问题当前状态无法改变");
        questionDao.updateStatus(status, question.getId());
    }

    public Integer updateAnswerCount(Integer id) {
        return questionDao.updateAnswerCount(id);
    }

    public Integer setBestAnswer(Integer answerId, Integer id) {
        return questionDao.setBestAnswer(answerId, id);
    }

    public void updateViewCount(Integer id) {
        questionDao.updateViewCount(id);
    }
}
