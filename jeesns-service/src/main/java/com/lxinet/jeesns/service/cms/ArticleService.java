package com.lxinet.jeesns.service.cms;

import com.lxinet.jeesns.core.consts.AppTag;
import com.lxinet.jeesns.core.enums.MessageType;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.cms.IArticleDao;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.MessageService;
import com.lxinet.jeesns.service.member.ScoreDetailService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.service.system.ConfigService;
import com.lxinet.jeesns.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/10/14.
 */
@Service("articleService")
public class ArticleService extends BaseService<Article> {
    @Resource
    private IArticleDao articleDao;
    @Resource
    private ConfigService configService;
    @Resource
    private ArticleCommentService articleCommentService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private MessageService messageService;
    @Resource
    private MemberService memberService;
    @Resource
    private ArticleFavorService articleFavorService;

    @Override
    public Article findById(Integer id) {
        return this.findById(id,null);
    }

    public Article findById(int id, Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Article article = articleDao.findById(id,loginMemberId);
        this.atFormat(article);
        return article;
    }

    @Transactional
    public boolean save(Member member, Article article) {
        Map<String,String> config = configService.getConfigToMap();
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST))){
            throw new OpeErrorException("投稿功能已关闭");
        }
        if(article.getCateId() == null || article.getCateId() == 0){
            throw new ParamException("栏目不能为空");
        }
        article.setMemberId(member.getId());
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
            article.setStatus(0);
        }else {
            article.setStatus(1);
        }
        if (article.getViewCount() == null) {
            article.setViewCount(0);
        }
        if (article.getViewRank() == null) {
            article.setViewRank(0);
        }
        if (StringUtils.isEmpty(article.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(article.getContent());
            if (contentStr.length() > 200) {
                article.setDescription(contentStr.substring(0, 200));
            } else {
                article.setDescription(contentStr);
            }
        }
        if (StringUtils.isEmpty(article.getThumbnail())) {
            Document doc = Jsoup.parseBodyFragment(article.getContent());
            Elements elements = doc.select("img[src]");
            if (elements.size() > 0) {
                String imgsrc = elements.get(0).attr("src");
                article.setThumbnail(imgsrc);
            }else{
                article.setThumbnail(null);
            }
        }
        boolean result = super.save(article);
        if(result){
            //@会员处理并发送系统消息
            messageService.atDeal(member.getId(),article.getContent(), AppTag.CMS, MessageType.CMS_ARTICLE_REFER,article.getId());
            if(article.getStatus() == 1){
                //投稿审核通过奖励
                scoreDetailService.scoreBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
            }
            actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_ARTICLE,"", ActionLogType.ARTICLE.getValue(),article.getId());
        }
        return true;
    }

    public Result listByPage(Page page, String key, int cateid, int status, int memberId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<Article> list = articleDao.list(page, key,cateid,status,memberId);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public void updateViewCount(int id) {
        articleDao.updateViewCount(id);
    }

    public boolean audit(int id) {
        if(articleDao.audit(id) == 0){
             throw new OpeErrorException();
        }
        Article article = this.findById(id);
        if(article != null){
            //说明此次操作是审核通过
            if(article.getStatus() == 1){
                //投稿审核通过奖励
                scoreDetailService.scoreBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
            }
        }
        return true;
    }

    public Result favor(Member loginMember, int articleId) {
        Article article = this.findById(articleId);
        ValidUtill.checkIsNull(article, "文章不存在");
        int favor = article.getFavor();
        String message;
        Result<Integer> result;
        if(articleFavorService.find(articleId,loginMember.getId()) == null){
            //增加
            articleDao.favor(articleId,1);
            articleFavorService.save(articleId,loginMember.getId());
            message = "喜欢成功";
            favor += 1;
            //文章收到喜欢
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
            //点赞之后发送系统信息
            messageService.diggDeal(loginMember.getId(),article.getMemberId(),AppTag.CMS,MessageType.CMS_ARTICLE_LIKE,article.getId());
        }else {
            //减少
            articleDao.favor(articleId,-1);
            articleFavorService.delete(articleId,loginMember.getId());
            message = "取消喜欢成功";
            favor -= 1;
            //取消喜欢，扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
        }
        result = new Result(0,message);
        result.setData(favor);
        return result;
    }

    public List<Article> listByCustom(int cid, String sort, int num, int day,int thumbnail) {
        return articleDao.listByCustom(cid,sort,num,day,thumbnail);
    }

    @Transactional
    public boolean update(Member member, Article article) {
        Article findArticle = this.findById(article.getId(),member);
        ValidUtill.checkIsNull(article, "文章不存在");
        Map<String,String> config = configService.getConfigToMap();
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
            findArticle.setStatus(0);
        }else {
            findArticle.setStatus(1);
        }
        //更新栏目
        findArticle.setCateId(article.getCateId());

        findArticle.setTitle(article.getTitle());
        findArticle.setThumbnail(article.getThumbnail());
        findArticle.setContent(article.getContent());
        findArticle.setDescription(article.getDescription());
        findArticle.setKeywords(article.getKeywords());
        //普通会员
        if (member.getIsAdmin() == 0) {
            if (member.getId().intValue() != findArticle.getMemberId().intValue()) {
                return false;
            }
        } else {
            //管理员
            findArticle.setSource(article.getSource());
            if (article.getViewCount() != null && article.getViewCount() > 0){
                findArticle.setViewCount(article.getViewCount());
            }
            findArticle.setWriter(article.getWriter());
            if (article.getViewRank() != null && article.getViewRank() > 0){
                findArticle.setViewRank(article.getViewRank());
            }
        }
        if (findArticle.getViewCount() == null) {
            findArticle.setViewCount(0);
        }
        if (findArticle.getViewRank() == null) {
            findArticle.setViewRank(0);
        }
        if (StringUtils.isEmpty(findArticle.getDescription())) {
            String contentStr = HtmlUtil.delHTMLTag(findArticle.getContent());
            if (contentStr.length() > 200) {
                findArticle.setDescription(contentStr.substring(0, 200));
            } else {
                findArticle.setDescription(contentStr);
            }
        }
        if (StringUtils.isEmpty(findArticle.getThumbnail())) {
            Document doc = Jsoup.parseBodyFragment(findArticle.getContent());
            Elements elements = doc.select("img[src]");
            if (elements.size() > 0) {
                String imgsrc = elements.get(0).attr("src");
                findArticle.setThumbnail(imgsrc);
            }else {
                findArticle.setThumbnail(null);
            }
        }
        articleDao.updateObj(findArticle);
        return true;
    }

    @Transactional
    public boolean delete(Member member, int id) {
        Article article = this.findById(id);
        ValidUtill.checkIsNull(article, "文章不存在");
        boolean result = super.deleteById(id);
        if(!result){
            throw new OpeErrorException();
        }
        //扣除积分
        scoreDetailService.scoreCancelBonus(member.getId(),ScoreRuleConsts.ARTICLE_SUBMISSIONS,id);
        articleCommentService.deleteByArticle(id);
        actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.DELETE_ARTICLE,"ID："+article.getId()+"，标题："+article.getTitle());
        return true;
    }

    public Article atFormat(Article article){
        if (article != null){
            article.setContent(memberService.atFormat(article.getContent()));
        }
        return article;
    }
}
