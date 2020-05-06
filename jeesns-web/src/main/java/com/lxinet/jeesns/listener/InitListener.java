package com.lxinet.jeesns.listener;

import com.lxinet.jeesns.core.consts.PluginExists;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.model.system.Config;
import com.lxinet.jeesns.service.system.ConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/25.
 */
public class InitListener implements ServletContextListener {

    public InitListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Const.PROJECT_PATH = sce.getServletContext().getContextPath();
            sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            JeesnsConfig jeesnsConfig = SpringContextUtil.getBean("jeesnsConfig");
            sce.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);
            String frontTemplate = jeesnsConfig.getFrontTemplate();
            sce.getServletContext().setAttribute("frontTemplate",frontTemplate);
            String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
            Const.GROUP_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getGroupPath();
            Const.WEIBO_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getWeiboPath();
            sce.getServletContext().setAttribute("managePath",managePath);
            sce.getServletContext().setAttribute("groupPath",Const.GROUP_PATH);
            sce.getServletContext().setAttribute("weiboPath",Const.WEIBO_PATH);
            sce.getServletContext().setAttribute("payExists",PluginExists.PAY);
            sce.getServletContext().setAttribute("extExists",PluginExists.EXT);
            sce.getServletContext().setAttribute("systemVersion",Const.SYSTEM_VERSION);
            sce.getServletContext().setAttribute("systemName",Const.SYSTEM_NAME);
            ConfigService configService = SpringContextUtil.getBean(ConfigService.class);
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
