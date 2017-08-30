package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.model.member.MemberToken;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.web.base.BaseController;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.service.cms.IArticleService;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("manageArticleController")
@RequestMapping("/")
public class ArticleController extends BaseController {
    @Resource
    private IArticleService articleService;
    @Resource
    private IMemberService memberService;

    @RequestMapping("${managePath}/cms/article/list")
    @ResponseBody
    public Object list(String key, @RequestParam(value = "cateId",defaultValue = "0",required = false) Integer cateId,
                @RequestParam(value = "status",defaultValue = "2",required = false) Integer status,
                @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        ResponseModel responseModel = articleService.listByPage(page,key,cateId,status,memberId);
        return responseModel;
    }

    @RequestMapping(value="${managePath}/cms/article/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        ResponseModel responseModel = articleService.save(loginMember,article);
        return responseModel;
    }

    @RequestMapping(value="${managePath}/cms/article/info",method = RequestMethod.GET)
    @ResponseBody
    public Object info(@Param("id") Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        Article article = articleService.findById(id,loginMember);
        return article;
    }

    @RequestMapping(value="${managePath}/cms/article/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@Valid Article article,BindingResult bindingResult) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        if(bindingResult.hasErrors()){
            new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new ResponseModel(-2);
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        ResponseModel responseModel = articleService.update(loginMember,article);
        if(responseModel.getCode() == 0){
            responseModel.setCode(3);
        }
        return responseModel;
    }


    @RequestMapping(value = "${managePath}/cms/article/delete",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@Param("id") Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        ResponseModel response = articleService.delete(loginMember,id);
        return response;
    }

    @RequestMapping(value = "${managePath}/cms/article/audit",method = RequestMethod.GET)
    @ResponseBody
    public Object audit(@Param("id") Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        ResponseModel response = articleService.audit(id);
        return response;
    }

}
