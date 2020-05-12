package com.lxinet.jeesns.web.manage.question;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.QuestionTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Controller
@RequestMapping("${jeesns.managePath}/question/type/")
@Before(AdminLoginInterceptor.class)
public class QuestionTypeController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/question/type/";
    @Resource
    private QuestionTypeService questionTypeService;

    @GetMapping("list")
    public String list(Model model){
        List<QuestionType> list = questionTypeService.listAll();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("add")
    public String add(Model model){
        return MANAGE_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public Result save(QuestionType questionType){
        if(questionType == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(questionType.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(questionTypeService.save(questionType));
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        QuestionType questionType = questionTypeService.findById(id);
        model.addAttribute("questionType",questionType);
        return MANAGE_FTL_PATH + "edit";
    }

    @PostMapping("update")
    @ResponseBody
    public Result update(QuestionType questionType){
        if(questionType == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(questionType.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(questionTypeService.update(questionType));
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        return new Result(questionTypeService.deleteById(id));
    }
}
