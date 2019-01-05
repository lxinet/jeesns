package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.member.Financial;
import java.util.List;


/**
 * Created by zchuanzhao on 2018/11/28.
 */
public interface IFinancialService extends IBaseService<Financial> {

    List<Financial> list(Integer memberId);

    /**
     * 增加财务明细
     * @param memberId 会员ID
     * @param money 变化金额
     * @param balance 会员账户余额
     * @param type 类型，0 收入， 1 支出
     * @param paymentId 支付方式
     * @param foreignId 关联的外键
     * @param remark 备注
     * @param operator 操作人用户名
     */
    void save(Integer memberId, Double money, Double balance, Integer type, Integer paymentId, Integer foreignId, String remark, String operator);
}
