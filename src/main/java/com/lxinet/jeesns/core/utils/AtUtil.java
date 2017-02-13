package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @会员 处理
 * Created by zchuanzhao on 2017/2/13.
 */
@Component
public class AtUtil {
    public static String at(String content){
        IMemberService memberService = SpringContextHolder.getBean("memberService");
        String[] contentArr = content.split("@");
        List<String> userNameList = new ArrayList<>();
        for (String con : contentArr){
            if(con.contains(" ")){
                con = con.substring(0,con.indexOf(" "));
                userNameList.add(con.trim());
            }else {
                userNameList.add(con.trim());
            }
        }
        for (String name : userNameList) {
            Member member = memberService.findByName(name);
            if (member != null) {
                String url = "<a href='" + Const.PROJECT_PATH + "/u/" + member.getId() + "' target='_blank'>@" + member.getName() + "</a>";
                content = content.replace("@" + member.getName() + "", url);
            }
        }
        return content;
    }
}
