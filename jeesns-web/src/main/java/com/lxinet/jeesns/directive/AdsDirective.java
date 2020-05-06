package com.lxinet.jeesns.directive;

import com.lxinet.jeesns.core.directive.BaseDirective;
import com.lxinet.jeesns.core.handler.DirectiveHandler;
import com.lxinet.jeesns.model.common.Ads;
import com.lxinet.jeesns.service.common.AdsService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zchuanzhao on 2017/09/08.
 */
@Component
public class AdsDirective extends BaseDirective {

    @Resource
    private AdsService adsService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        Integer id = handler.getInteger("id",0);
        Ads ads = adsService.findById(id);
        handler.put("ad", ads).render();
    }

}
