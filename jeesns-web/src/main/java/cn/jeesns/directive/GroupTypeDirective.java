package cn.jeesns.directive;

import cn.jeesns.service.group.GroupTypeService;
import cn.jeesns.core.directive.BaseDirective;
import cn.jeesns.core.handler.DirectiveHandler;
import cn.jeesns.model.group.GroupType;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/16 下午16:14
 */
@Component
public class GroupTypeDirective extends BaseDirective {

    @Resource
    private GroupTypeService groupTypeService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        List<GroupType> list = groupTypeService.list();
        handler.put("groupTypeList", list).render();
    }

}
