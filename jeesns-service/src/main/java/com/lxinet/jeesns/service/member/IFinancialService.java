package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.member.Financial;
import java.util.List;


/**
 * Created by zchuanzhao on 2018/11/28.
 */
public interface IFinancialService extends IBaseService<Financial> {

    List<Financial> list(Integer memberId);


}
