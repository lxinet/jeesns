package cn.jeesns.directive;

import cn.jeesns.model.common.Ads;
import cn.jeesns.service.common.AdsService;
import cn.jeesns.core.directive.BaseDirective;
import cn.jeesns.core.handler.DirectiveHandler;
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
