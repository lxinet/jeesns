package com.lxinet.jeesns.cms.web.manage;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.MemberToken;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.cms.model.ArticleCate;
import com.lxinet.jeesns.cms.service.IArticleCateService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller
@RequestMapping("/")
public class ArticleCateController extends BaseController {
    @Resource
    private IArticleCateService articleCateService;

    @RequestMapping("${managePath}/cms/articleCate/list")
    @ResponseBody
    public Object list(){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        List<ArticleCate> list = articleCateService.list();
        return list;
    }


    @RequestMapping("${managePath}/cms/articleCate/save")
    @ResponseBody
    public Object save(ArticleCate articleCate){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
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

    @RequestMapping("${managePath}/cms/articleCate/info")
    @ResponseBody
    public Object info(@Param("id") int id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        ArticleCate articleCate = articleCateService.findById(id);
        return articleCate;
    }

    @RequestMapping("${managePath}/cms/articleCate/update")
    @ResponseBody
    public Object update(ArticleCate articleCate){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
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


    @RequestMapping("${managePath}/cms/articleCate/delete")
    @ResponseBody
    public Object delete(@Param("id") int id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        ResponseModel response = articleCateService.delete(id);
        return response;
    }
}
