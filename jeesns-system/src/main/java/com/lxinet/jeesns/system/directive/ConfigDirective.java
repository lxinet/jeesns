package com.lxinet.jeesns.system.directive;

import com.lxinet.jeesns.core.directive.BaseDirective;
import com.lxinet.jeesns.core.handler.DirectiveHandler;
import com.lxinet.jeesns.system.service.IConfigService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zchuanzhao on 2017/5/18.
 */
@Component
public class ConfigDirective extends BaseDirective {
    @Resource
    private IConfigService configService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {

//        handler.put("articleList", list).render();
    }

}
