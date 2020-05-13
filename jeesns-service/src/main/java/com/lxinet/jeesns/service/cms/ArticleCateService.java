package com.lxinet.jeesns.service.cms;

import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.cms.IArticleCateDao;
import com.lxinet.jeesns.model.cms.ArticleCate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Service("articleCateService")
public class ArticleCateService extends BaseService<ArticleCate> {

    @Resource
    private IArticleCateDao articleCateDao;

    @Override
    public boolean save(ArticleCate articleCate) {
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = this.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                throw new ParamException("上级栏目不存在");
            }
            if(fatherArticleCate.getFid() != 0){
                throw new OpeErrorException("只要顶级栏目才可以添加下级栏目");
            }
        }
        if (!super.save(articleCate)){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean update(ArticleCate articleCate) {
        ArticleCate findArticleCate =this.findById(articleCate.getId());
        if(findArticleCate == null){
            throw new ParamException("栏目不存在");
        }
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid().intValue() == articleCate.getId().intValue()){
            throw new ParamException("上级栏目不能是自己");
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = this.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                throw new ParamException("上级栏目不存在");
            }
            if(fatherArticleCate.getFid() != 0){
                throw new OpeErrorException("只要顶级栏目才可以添加下级栏目");
            }
        }
        findArticleCate.setFid(articleCate.getFid());
        findArticleCate.setName(articleCate.getName());
        findArticleCate.setSort(articleCate.getSort());
        if (!super.update(findArticleCate)){
            throw new OpeErrorException();
        }
        return true;
    }

    public boolean delete(int id) {
        List sonList = this.findListByFid(id);
        if(sonList.size() > 0){
            throw new OpeErrorException("请先删除下级栏目");
        }
        boolean result = super.deleteById(id);
        if(!result){
            throw new OpeErrorException();
        }
        return true;
    }

    public List<ArticleCate> list() {
        return articleCateDao.list();
    }

    public List<ArticleCate> findListByFid(int fid) {
        return articleCateDao.findListByFid(fid);
    }

}
