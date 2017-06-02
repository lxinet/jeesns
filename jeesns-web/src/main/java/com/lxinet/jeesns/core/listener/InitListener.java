package com.lxinet.jeesns.core.listener; /**
 * Created by zchuanzhao on 2017/5/25.
 */

import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.SpringContextHolder;
import com.lxinet.jeesns.system.entity.Config;
import com.lxinet.jeesns.system.service.IConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class InitListener implements ServletContextListener {

    public InitListener() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
        JeesnsConfig jeesnsConfig = SpringContextHolder.getBean("jeesnsConfig");
        sce.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);
        String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
        sce.getServletContext().setAttribute("managePath",managePath);
        IConfigService configService = SpringContextHolder.getBean("configService");
        List<Config> configList = configService.allList();
        for (Config config : configList) {
            sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
