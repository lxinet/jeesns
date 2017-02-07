package com.lxinet.jeesns.modules.mem.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.entity.ValidateCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 验证码DAO接口
 * Created by zchuanzhao on 17/01/20.
 */
public interface IValidateCodeDao extends IBaseDao<ValidateCode> {

    /**
     * 验证，30分钟以内有效
     * @param email
     * @param code
     * @param type
     * @return
     */
    ValidateCode valid(@Param("email") String email,@Param("code") String code,@Param("type") int type);

    int used(@Param("id") Integer id);
}