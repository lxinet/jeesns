package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.modules.weibo.entity.WeiboComment;

/**
 * Created by zchuanzhao on 2017/2/25.
 */
public class WeiboCommentUtil {

    public static void format(WeiboComment weiboComment){
        // 判断内容是否存在:*:格式的内容，
        // 如果有存在，默认说明是有存在Emoji，则替换相应内容，
        // 不存在Emoji则直接返回内容
        String regEmoji="[\\s\\S]*:[\\s\\S]*:[\\s\\S]*";
        if(weiboComment.getContent().matches(regEmoji)){
            weiboComment.setContent(EmojiUtil.replace(weiboComment.getContent()));
        }
        String regAt = "[\\s\\S]*@[\\s\\S]*";
        if(weiboComment.getContent().matches(regAt)){
            weiboComment.setContent(AtUtil.at(weiboComment.getContent()));
        }
    }
}
