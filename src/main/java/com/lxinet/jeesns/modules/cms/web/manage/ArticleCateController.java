package com.lxinet.jeesns.modules.cms.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import com.lxinet.jeesns.modules.cms.service.IArticleCateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class ArticleCateController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/articleCate";
    @Resource
    private IArticleCateService articleCateService;

    @RequestMapping("${managePath}/cms/articleCate/list")
    public String list(Model model){
        List<ArticleCate> list = articleCateService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "/list";
    }

    @RequestMapping("${managePath}/cms/articleCate/add")
    public String add(Model model){
        return MANAGE_FTL_PATH + "/add";
    }

    @RequestMapping("${managePath}/cms/articleCate/save")
    @ResponseBody
    public Object save(ArticleCate articleCate){
        if(articleCate == null){
            return new ResponseModel(-2);
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            return new ResponseModel(-1,"名称不能为空");
        }
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = articleCateService.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                return new ResponseModel(-1,"上级栏目不存在");
            }
            if(fatherArticleCate.getFid() != 0){
                return new ResponseModel(-1,"只有顶级栏目才可以添加下级栏目");
            }
        }
        articleCateService.save(articleCate);
        return new ResponseModel(3,"保存成功");
    }

    @RequestMapping("${managePath}/cms/articleCate/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        ArticleCate articleCate = articleCateService.findById(id);
        model.addAttribute("articleCate",articleCate);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping("${managePath}/cms/articleCate/update")
    @ResponseBody
    public Object update(ArticleCate articleCate){
        if(articleCate == null){
            return new ResponseModel(-2);
        }
        ArticleCate findArticleCate = articleCateService.findById(articleCate.getId());
        if(findArticleCate == null){
            return new ResponseModel(-1,"栏目不存在");
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            return new ResponseModel(-1,"名称不能为空");
        }
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid() == articleCate.getId()){
            return new ResponseModel(-1,"上级栏目不能是本身");
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = articleCateService.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                return new ResponseModel(-1,"上级栏目不存在");
            }
            if(fatherArticleCate.getFid() != 0){
                return new ResponseModel(-1,"只有顶级栏目才可以添加下级栏目");
            }
        }
        findArticleCate.setFid(articleCate.getFid());
        findArticleCate.setName(articleCate.getName());
        findArticleCate.setSort(articleCate.getSort());
        int flag = articleCateService.update(findArticleCate);
        if (flag == 1){
            return new ResponseModel(3,"修改成功");
        }
        return new ResponseModel(-1,"修改失败");
    }


    @RequestMapping("${managePath}/cms/articleCate/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        ResponseModel response = articleCateService.delete(id);
        return response;
    }
}
