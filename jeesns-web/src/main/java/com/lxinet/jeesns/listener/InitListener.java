package com.lxinet.jeesns.listener;

import com.lxinet.jeesns.core.consts.PluginExists;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.model.system.Config;
import com.lxinet.jeesns.service.system.ConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/25.
 */
@WebListener
public class InitListener implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        try {
            Const.PROJECT_PATH = sre.getServletContext().getContextPath();
            sre.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            JeesnsConfig jeesnsConfig = SpringContextUtil.getBean(JeesnsConfig.class);
            sre.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);
            String frontTemplate = jeesnsConfig.getFrontTemplate();
            sre.getServletContext().setAttribute("frontTemplate",frontTemplate);
            String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
            Const.GROUP_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getGroupPath();
            Const.WEIBO_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getWeiboPath();
            sre.getServletContext().setAttribute("managePath",managePath);
            sre.getServletContext().setAttribute("groupPath",Const.GROUP_PATH);
            sre.getServletContext().setAttribute("weiboPath",Const.WEIBO_PATH);
            sre.getServletContext().setAttribute("payExists",PluginExists.PAY);
            sre.getServletContext().setAttribute("extExists",PluginExists.EXT);
            sre.getServletContext().setAttribute("systemVersion",Const.SYSTEM_VERSION);
            sre.getServletContext().setAttribute("systemName",Const.SYSTEM_NAME);
            ConfigService configService = SpringContextUtil.getBean(ConfigService.class);
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sre.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
