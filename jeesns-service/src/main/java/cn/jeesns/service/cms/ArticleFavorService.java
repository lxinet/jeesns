package cn.jeesns.service.cms;

import cn.jeesns.dao.cms.IArticleFavorDao;
import cn.jeesns.model.cms.ArticleFavor;
import cn.jeesns.core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("articleFavorService")
public class ArticleFavorService extends BaseService<ArticleFavor> {
    @Resource
    private IArticleFavorDao articleFavorDao;

    public ArticleFavor find(Integer articleId, Integer memberId) {
        return articleFavorDao.find(articleId,memberId);
    }

    public void save(Integer articleId, Integer memberId) {
        articleFavorDao.save(articleId,memberId);
    }

    public void delete(Integer articleId, Integer memberId) {
        articleFavorDao.delete(articleId,memberId);
    }
}
