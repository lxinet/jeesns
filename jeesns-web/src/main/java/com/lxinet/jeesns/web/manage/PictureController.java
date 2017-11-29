package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.service.system.ITagService;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/11/01.
 */
@Controller
@RequestMapping("/${managePath}/picture")
@Before(AdminLoginInterceptor.class)
public class PictureController extends BaseController{
    private static final String MANAGE_FTL_PATH = "/manage/picture/";
    @Resource
    private ITagService tagService;

    @RequestMapping("/tagList")
    public String list(Model model){
        Page page = new Page(request);
        ResponseModel responseModel = tagService.listByPage(page, AppTag.PICTURE);
        model.addAttribute("model",responseModel);
        return MANAGE_FTL_PATH + "tagList";
    }

}
