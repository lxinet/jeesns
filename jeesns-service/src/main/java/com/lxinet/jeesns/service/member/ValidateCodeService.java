package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.member.IValidateCodeDao;
import com.lxinet.jeesns.model.member.ValidateCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/1/20.
 */
@Service("validateCodeService")
public class ValidateCodeService extends BaseServiceImpl<ValidateCode> {
    @Resource
    private IValidateCodeDao validateCodeDao;

    @Override
    public boolean save(ValidateCode validateCode) {
        return validateCodeDao.save(validateCode) == 1;
    }

    public ValidateCode valid(String email, String code, int type) {
        return validateCodeDao.valid(email,code,type);
    }

    public boolean used(Integer id) {
        return validateCodeDao.used(id) == 1;
    }
}
