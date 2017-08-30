package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.model.member.MemberToken;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.web.base.BaseController;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.weibo.IWeiboService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("mamageWeiboController")
@RequestMapping("/")
public class WeiboController extends BaseController {
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IMemberService memberService;

    @RequestMapping("${managePath}/weibo/list")
    @ResponseBody
    public Object list(@RequestParam(value = "key",required = false,defaultValue = "") String key) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        ResponseModel responseModel = weiboService.listByPage(page,0,0,key);
        return responseModel;
    }

    @RequestMapping(value = "${managePath}/weibo/delete", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@Param("id") int id) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        return weiboService.delete(request, loginMember,id);
    }
}
