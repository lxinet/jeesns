package com.lxinet.jeesns.web.manage.picture;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.service.picture.PictureService;
import com.lxinet.jeesns.service.system.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/01
 */
@Controller
@RequestMapping("/${jeesns.managePath}/picture")
@Before(AdminLoginInterceptor.class)
public class PictureController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/picture/";
    @Resource
    private PictureService pictureService;
    @Resource
    private TagService tagService;

    @RequestMapping("/tagList")
    public String tagList(Model model){
        Page page = new Page(request);
        Result result = tagService.listByPage(page, AppTag.PICTURE);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "tagList";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Page page = new Page(request);
        Result result = pictureService.listByPage(page,0);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "/delete/{pictureId}",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("pictureId") Integer pictureId){
        return new Result(pictureService.delete(request,pictureId));
    }

}
