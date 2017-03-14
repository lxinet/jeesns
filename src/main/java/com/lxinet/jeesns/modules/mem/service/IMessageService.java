package com.lxinet.jeesns.modules.mem.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.mem.entity.Message;

/**
 * 会员私信Service
 * Created by zchuanzhao on 17/3/9.
 */
public interface IMessageService {

    ResponseModel save(Integer fromMemberId, Integer toMemberId, String content);

    ResponseModel<Message> listByPage(Page page, Integer fromMemberId, Integer toMemberId);

    ResponseModel<Message> messageRecords(Page page, Integer fromMemberId, Integer toMemberId);

    /**
     * 删除某个会员的所有聊天记录
     * @param memberId
     * @return
     */
    int deleteByMember(Integer memberId);

    /**
     * 清空两个会员的聊天记录
     * @param fromMemberId
     * @param toMemberId
     * @return
     */
    int clearMessageByMember(Integer fromMemberId, Integer toMemberId);

    /**
     * 会员未读私信数量
     * @param memberId
     * @return
     */
    int countUnreadNum(int memberId);

    /**
     * 设置已读状态
     * @param fromMemberId
     * @param toMemberId
     * @return
     */
    int setRead(Integer fromMemberId, Integer toMemberId);
}