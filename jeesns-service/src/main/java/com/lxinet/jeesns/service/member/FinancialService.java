package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.dao.member.IFinancialDao;
import com.lxinet.jeesns.model.member.Financial;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Service
public class FinancialService extends BaseService<Financial> {
    @Resource
    private IFinancialDao financialDao;

    public List<Financial> list(Integer memberId) {
        List<Financial> list = financialDao.list(PageUtil.getPage(),memberId);
        return list;
    }

    public void save(Integer memberId, Double money, Double balance, Integer type, Integer paymentId, Integer foreignId, String remark, String operator) {
        //添加财务明细
        Financial financial = new Financial();
        financial.setBalance(balance);
        financial.setForeignId(foreignId);
        financial.setMemberId(memberId);
        financial.setMoney(money);
        financial.setType(type);
        financial.setPaymentId(paymentId);
        financial.setRemark(remark);
        financial.setOperator(operator);
        this.save(financial);
    }

}
