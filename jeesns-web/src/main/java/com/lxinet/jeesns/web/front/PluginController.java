package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.NotLoginException;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

/**
 * 插件
 * Created by zchuanzhao on 2018/11/26.
 */
@Controller("pluginController")
public class PluginController extends BaseController {

    /**
     * get请求
     * @param beanName Spring bean名称，不能调用含有Service和Dao字符串的bean
     * @param methodName 方法名称
     * @param params 参数，参数中间用&|;按方法参数的顺序依次分隔
     * @param model
     * @return
     */
    @GetMapping({"/plugin/{beanName}/{methodName}","/plugin/{beanName}/{methodName}/{params}"})
    public String plugin(@PathVariable("beanName") String beanName,
                       @PathVariable("methodName") String methodName,
                       @PathVariable(value = "params", required = false) String params,
                       Model model){
        Object[] paramsArr = new Object[0];
        if (StringUtils.isNotBlank(params)){
            paramsArr = params.split("&\\|;");
        }
        try {
            Object obj = JeesnsInvoke.invoke(beanName, methodName, paramsArr);
            model.addAttribute("model", obj);
        } catch (NotLoginException e1) {
            try {
                response.sendRedirect(Const.PROJECT_PATH + "/member/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/plugin/" + beanName + "/" + methodName;
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
    public Result plugin(@PathVariable("beanName") String beanName,
                       @PathVariable("methodName") String methodName,
                       @Param("params") String params){
        Object[] paramsArr = new Object[0];
        if (StringUtils.isNotBlank(params)){
            paramsArr = params.split("&\\|;");
        }
        try {
            Result result = (Result) JeesnsInvoke.invoke(beanName, methodName, paramsArr);
            return result;
        } catch (NotLoginException e1) {
            return new Result(-99,"请先登录");
        }
    }
}
