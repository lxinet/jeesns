package com.lxinet.jeesns.configurer;

import com.lxinet.jeesns.directive.*;
import freemarker.template.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangchuanzhao
 * @date 2020/5/7 13:27
 */
@Component
public class FreemarkerConfigurer implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Autowired
    private Configuration configuration;
    @Autowired
    private AdsDirective adsDirective;
    @Autowired
    private ArticleDirective articleDirective;
    @Autowired
    private GroupDirective groupDirective;
    @Autowired
    private GroupTopicDirective groupTopicDirective;
    @Autowired
    private GroupTypeDirective groupTypeDirective;
    @Autowired
    private WeiboDirective weiboDirective;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("cms_article_list", articleDirective);
        configuration.setSharedVariable("wb_weibo_list", weiboDirective);
        configuration.setSharedVariable("group_list", groupDirective);
        configuration.setSharedVariable("group_topic_list", groupTopicDirective);
        configuration.setSharedVariable("group_type_list", groupTypeDirective);
        configuration.setSharedVariable("ads", adsDirective);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
