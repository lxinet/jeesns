package cn.jeesns.dao.cms;

import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.model.cms.ArticleFavor;
import org.apache.ibatis.annotations.*;

/**
 * 文章点赞DAO接口
 * Created by zchuanzhao on 2017/2/9.
 */
@Mapper
public interface IArticleFavorDao extends BaseMapper<ArticleFavor> {

    @Select("select * from tbl_article_favor where article_id = #{articleId} and member_id = #{memberId}")
    ArticleFavor find(@Param("articleId") Integer articleId, @Param("memberId") Integer memberId);

    @Insert("insert into tbl_article_favor (article_id,member_id,create_time) values (#{articleId}, #{memberId}, now())")
    Integer save(@Param("articleId") Integer articleId, @Param("memberId") Integer memberId);

    @Delete("delete from tbl_article_favor where article_id = #{articleId} and member_id = #{memberId}")
    Integer delete(@Param("articleId") Integer articleId, @Param("memberId") Integer memberId);
}