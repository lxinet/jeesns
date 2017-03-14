package com.lxinet.jeesns.modules.mem.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.mem.dao.IMessageDao;
import com.lxinet.jeesns.modules.mem.entity.Message;
import com.lxinet.jeesns.modules.mem.service.IMessageService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/9.
 */
@Service("messageService")
public class MessageServiceImpl implements IMessageService {
    @Resource
    private IMessageDao messageDao;

    @Override
    public ResponseModel save(Integer fromMemberId, Integer toMemberId, String content) {
        if(fromMemberId.intValue() == toMemberId.intValue()){
            return new ResponseModel(-1, "不能发信息给自己");
        }
//        Message message = new Message(fromMemberId, toMemberId, content);
        Message message = new Message();
        message.setFromMemberId(fromMemberId);
        message.setToMemberId(toMemberId);
        message.setContent(content);
        if(messageDao.save(message) == 1){
            return new ResponseModel(0, "信息发送成功");
        }
        return new ResponseModel(-1, "信息发送失败");
    }

    @Override
    public ResponseModel<Message> listByPage(Page page, Integer fromMemberId, Integer toMemberId) {
        List<Message> list = messageDao.listByPage(page,fromMemberId, toMemberId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel<Message> messageRecords(Page page, Integer fromMemberId, Integer toMemberId) {
        List<Message> list = messageDao.messageRecords(page, fromMemberId, toMemberId);
        ResponseModel model = new ResponseModel(0, page);
        model.setData(list);
        //设置该会员聊天记录为已读
        this.setRead(fromMemberId,toMemberId);
        return model;
    }

    @Override
    public int deleteByMember(Integer memberId) {
        return messageDao.deleteByMember(memberId);
    }

    @Override
    public int clearMessageByMember(Integer fromMemberId, Integer toMemberId) {
        return messageDao.clearMessageByMember(fromMemberId, toMemberId);
    }

    @Override
    public int countUnreadNum(int memberId) {
        return messageDao.countUnreadNum(memberId);
    }

    @Override
    public int setRead(Integer fromMemberId, Integer toMemberId) {
        return messageDao.setRead(fromMemberId,toMemberId);
    }
}
