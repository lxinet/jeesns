package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.event.AnalyticsUseEvent;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.core.utils.SpringContextUtil;
import com.lxinet.jeesns.dao.system.IConfigDao;
import com.lxinet.jeesns.model.system.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/27.
 */
@Service("configService")
public class ConfigService extends BaseService<Config> {
    @Resource
    private IConfigDao configDao;


    public List<Config> allList() {
        return super.listAll();
    }

    public Map<String, String> getConfigToMap() {
        List<Config> allList = this.allList();
        Map<String,String> map = new HashMap<>();
        for (Config config : allList) {
            map.put(config.getJkey(),config.getJvalue());
        }
        return map;
    }

    public String getValue(String key) {
        Config config = configDao.selectByKey(key);
        if (config != null){
            return config.getJvalue();
        }
        return null;
    }

    public boolean update(Map<String, String> params, HttpServletRequest request) {
        for(Map.Entry entry : params.entrySet()){
            if(((String)entry.getValue()).length() > 2000){
                throw new ParamException("只能输入2000个字符");
            }else {
                configDao.update((String)entry.getKey(),(String)entry.getValue());
                request.getServletContext().setAttribute(((String)entry.getKey()).toUpperCase(),entry.getValue());
            }
        }
        try {
            AnalyticsUseEvent analyticsEvent = new AnalyticsUseEvent(this, request);
            SpringContextUtil.getApplicationContext().publishEvent(analyticsEvent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


}
