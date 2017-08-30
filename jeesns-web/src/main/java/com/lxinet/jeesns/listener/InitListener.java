package com.lxinet.jeesns.listener;

import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.SpringContextHolder;
import com.lxinet.jeesns.model.system.Config;
import com.lxinet.jeesns.service.system.IConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/25.
 */
public class InitListener implements ServletContextListener {

    public InitListener() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        try {
            Const.PROJECT_PATH = sce.getServletContext().getContextPath();
            sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            JeesnsConfig jeesnsConfig = SpringContextHolder.getBean("jeesnsConfig");
            sce.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);
            String frontTemplate = jeesnsConfig.getFrontTemplate();
            sce.getServletContext().setAttribute("frontTemplate",frontTemplate);
            String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
            sce.getServletContext().setAttribute("managePath",managePath);
            IConfigService configService = SpringContextHolder.getBean("configService");
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
