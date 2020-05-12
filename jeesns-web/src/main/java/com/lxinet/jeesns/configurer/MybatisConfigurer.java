package com.lxinet.jeesns.configurer;

import com.lxinet.jeesns.core.interceptor.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author zhangchuanzhao
 * @date 2020/5/12 18:27
 */
@Configuration
public class MybatisConfigurer {

    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor sqlStatsInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        sqlStatsInterceptor.setProperties(properties);
        return sqlStatsInterceptor;
    }
}
