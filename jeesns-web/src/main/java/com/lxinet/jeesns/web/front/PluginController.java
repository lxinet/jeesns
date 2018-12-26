package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.model.system.ActionLog;
import com.lxinet.jeesns.service.system.IActionLogService;
import com.lxinet.jeesns.web.common.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 插件
 * Created by zchuanzhao on 2018/11/26.
 */
@Controller("pluginController")
public class PluginController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;

    /**
     * get请求
     * @param beanName Spring bean名称，不能调用含有Service和Dao字符串的bean
     * @param methodName 方法名称
     * @param params 参数，参数中间用&|;按方法参数的顺序依次分隔
     * @param model
     * @return
     */
    @GetMapping("/plugin/{beanName}/{methodName}/{params}")
    public String plugin(@PathVariable("beanName") String beanName,
                       @PathVariable("methodName") String methodName,
                       @PathVariable("params") String params,
                       Model model){
        String[] paramsArr = new String[0];
        if (StringUtils.isNotBlank(params)){
            paramsArr = params.split("&\\|;");
        }
        Object obj = JeesnsInvoke.invoke(beanName, methodName, paramsArr);
        model.addAttribute("model", obj);
        return jeesnsConfig.getFrontTemplate() + "/plugin/" + beanName + "/" + methodName;
    }

    /**
     * post请求
     * @param beanName Spring bean名称，不能调用含有Service和Dao字符串的bean
     * @param methodName 方法名称
     * @param params 参数，参数中间用&|;按方法参数的顺序依次分隔
     * @return
     */
    @PostMapping("/plugin/{beanName}/{methodName}")
    @ResponseBody
    public ResultModel plugin(@PathVariable("beanName") String beanName,
                       @PathVariable("methodName") String methodName,
                       @Param("params") String params){
        String[] paramsArr = new String[0];
        if (StringUtils.isNotBlank(params)){
            paramsArr = params.split("&\\|;");
        }
        ResultModel resultModel = (ResultModel) JeesnsInvoke.invoke(beanName, methodName, paramsArr);
        return resultModel;
    }


}
