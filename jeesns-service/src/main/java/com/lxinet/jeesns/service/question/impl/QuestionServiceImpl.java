package com.lxinet.jeesns.service.question.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.core.utils.HtmlUtil;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.dao.question.IQuestionDao;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.service.question.IQuestionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService {

    @Resource
    private IQuestionDao questionDao;


    @Override
    public ResultModel<Question> list() {
        List<Question> list = questionDao.list(PageUtil.getPage());
        ResultModel model = new ResultModel(0,PageUtil.getPage());
        model.setData(list);
        return model;
    }

    @Override
    public Question findById(Integer id) {
        return questionDao.findById(id);
    }

    @Override
    public boolean save(Question question) {
        ValidUtill.checkParam(question.getMemberId() == null, "用户不能为空");
        ValidUtill.checkParam(question.getTypeId() == null, "类型不能为空");
        ValidUtill.checkParam(question.getTitle() == null, "标题不能为空");
        if (StringUtils.isEmpty(question.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(question.getContent());
            if (contentStr.length() > 200) {
                question.setDescription(contentStr.substring(0, 200));
            } else {
                question.setDescription(contentStr);
            }
        }
        return super.save(question);
    }

    @Override
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

    @Override
    public boolean delete(Member loginMember, Integer id) {
        Question findQuestion = findById(id);
        if(loginMember.getId().intValue() == findQuestion.getMember().getId().intValue() || loginMember.getIsAdmin() > 0){
            return super.deleteById(id);
        }
        throw new OpeErrorException("没有权限");
    }

}
