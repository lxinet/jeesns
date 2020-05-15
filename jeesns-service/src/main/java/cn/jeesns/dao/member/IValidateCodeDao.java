package cn.jeesns.dao.member;

import cn.jeesns.model.member.ValidateCode;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 验证码DAO接口
 * Created by zchuanzhao on 17/01/20.
 */
@Mapper
public interface IValidateCodeDao extends BaseMapper<ValidateCode> {

    /**
     * 验证，30分钟以内有效
     * @param email
     * @param code
     * @param type
     * @return
     */
    ValidateCode valid(@Param("email") String email, @Param("code") String code, @Param("type") int type);

    int used(@Param("id") Integer id);

    int save(ValidateCode validateCode);
}