package cn.jeesns.directive;

import cn.jeesns.service.group.GroupService;
import cn.jeesns.core.directive.BaseDirective;
import cn.jeesns.core.handler.DirectiveHandler;
import cn.jeesns.model.group.Group;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/5/18.
 */
@Component
public class GroupDirective extends BaseDirective {
    @Resource
    private GroupService groupService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        int num = handler.getInteger("num",0);
        String sort = handler.getString("sort","id");
        int status = handler.getInteger("status",-1);
        List<Group> list = groupService.listByCustom(status,num,sort);
        handler.put("groupList", list).render();
    }

}
