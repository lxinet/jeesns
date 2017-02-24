package com.lxinet.jeesns.modules.cms.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.cms.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */
public interface IArticleDao extends IBaseDao<Article> {

    int save(Article article);

    List<Article> listByPage(@Param("page") Page page, @Param("key") String key, @Param("cateid") Integer cateid, @Param("status") Integer status,@Param("memberId") Integer memberId);

    /**
     * 设置某个栏目的所有文章为未分类文章，一般在删除某个栏目的时候调用
     * @param cateid
     * @return
     */
    int setArticleAsNoneCate(@Param("cateid") int cateid);

    /**
     * 更新阅读数
     * @param articleId
     * @return
     */
    int updateViewCount(@Param("articleId") int articleId);

    /**
     * 审核文章
     * @param id
     * @return
     */
    int audit(@Param("id") int id);

    Article findById(@Param("id") Integer id, @Param("loginMemberId") Integer loginMemberId);
}