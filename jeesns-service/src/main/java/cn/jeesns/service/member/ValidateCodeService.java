package cn.jeesns.service.member;

import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.member.IValidateCodeDao;
import cn.jeesns.model.member.ValidateCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/1/20.
 */
@Service("validateCodeService")
public class ValidateCodeService extends BaseService<ValidateCode> {
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
