package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.event.AnalyticsUseEvent;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.service.system.ConfigService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.NotLoginException;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.dao.member.IMemberDao;
import com.lxinet.jeesns.utils.EmailSendUtil;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.member.ValidateCode;
import com.lxinet.jeesns.utils.ActionUtil;
import com.lxinet.jeesns.utils.ConfigUtil;
import com.lxinet.jeesns.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by zchuanzhao on 16/9/29.
 */
@Service("memberService")
public class MemberService extends BaseService<Member> {
    @Resource
    private IMemberDao memberDao;
    @Resource
    private ValidateCodeService validateCodeService;
    @Resource
    private ConfigService configService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private MemberFansService memberFansService;
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private FinancialService financialService;

    public boolean login(Member member, HttpServletRequest request) {
        Map<String,String> config = configService.getConfigToMap();
        if("0".equals(config.get(ConfigUtil.MEMBER_LOGIN_OPEN))){
            throw new OpeErrorException("登录功能已关闭");
        }
        String password = member.getPassword();
        member.setPassword(Md5Util.getMD5Code(member.getPassword()));
        Member findMember = memberDao.login(member);
        if (null == findMember){
            actionLogService.save(IpUtil.getIpAddress(request),null,ActionUtil.MEMBER_LOGIN_ERROR,"登录用户名："+member.getName()+"，登录密码："+password);
            throw new OpeErrorException("用户名或者密码错误");
        }
        if(findMember.getStatus() == -1){
            throw new OpeErrorException("账号已被禁用");
        }
        //登录成功更新状态
        memberDao.loginSuccess(findMember.getId(), IpUtil.getIpAddress(request));
        findMember = this.findById(findMember.getId());
        MemberUtil.setLoginMember(request,findMember);
        actionLogService.save(findMember.getCurrLoginIp(),findMember.getId(), ActionUtil.MEMBER_LOGIN);
        //登录奖励
        scoreDetailService.scoreBonus(findMember.getId(), ScoreRuleConsts.LOGIN);
        return true;
    }

    public Member manageLogin(Member member,HttpServletRequest request) {
        String password = member.getPassword();
        member.setPassword(Md5Util.getMD5Code(member.getPassword()));
        Member findMember = memberDao.manageLogin(member);
        if(findMember != null){
            //登录成功更新状态
            memberDao.loginSuccess(findMember.getId(), IpUtil.getIpAddress(request));
            findMember = this.findById(findMember.getId());
        }else {
            actionLogService.save(IpUtil.getIpAddress(request),null,ActionUtil.MEMBER_LOGIN_ERROR,"登录用户名："+member.getName()+"，登录密码："+password);
        }
        return findMember;
    }

    @Override
    public Member findById(Integer id) {
        return memberDao.findById(id);
    }

    @Transactional
    public Result register(Member member, HttpServletRequest request) {
        if(memberDao.findByName(member.getName()) != null){
            throw new OpeErrorException("用户名已存在");
        }
        if(memberDao.findByEmail(member.getEmail()) != null){
            throw new OpeErrorException("邮箱已存在");
        }
        if(memberDao.findByPhone(member.getPhone()) != null){
            throw new OpeErrorException("手机号已存在");
        }
        Member superMember = null;
        if (member.getSuperMemberId() != null){
            superMember = findById(member.getSuperMemberId());
            if (superMember != null) {
                if (superMember.getStatus() == -1) {
                    throw new OpeErrorException("上级用户已被禁用，无法注册");
                }
            }else {
                member.setSuperMemberId(null);
            }

        }
        member.setRegip(IpUtil.getIpAddress(request));
        member.setPassword(Md5Util.getMD5Code(member.getPassword()));
        member.setAvatar(Const.DEFAULT_AVATAR);
        if(memberDao.register(member) == 1){
            actionLogService.save(member.getRegip(),member.getId(),ActionUtil.MEMBER_REG);
            //注册奖励
            scoreDetailService.scoreBonus(member.getId(),ScoreRuleConsts.REG_INIT);
            //上级奖励
            if (superMember != null && "1".equals(configService.getValue(ConfigUtil.MEMBER_RECOMMEND))){
                Integer rewardScore = 0;
                Double rewardMoney = 0d;
                try {
                    rewardScore = Integer.parseInt(configService.getValue(ConfigUtil.MEMBER_RECOMMEND_REWARD_SCORE));
                }catch (Exception e){

                }
                try {
                    rewardMoney = Double.parseDouble(configService.getValue(ConfigUtil.MEMBER_RECOMMEND_REWARD_MONEY));
                }catch (Exception e){

                }
                //奖励积分
                if (rewardScore > 0){
                    updateScore(rewardScore, superMember.getId());
                    scoreDetailService.save(0, superMember.getId(),null,rewardScore, "推荐用户奖励，推荐用户名："+member.getName());
                }
                //奖励金额
                if (rewardMoney > 0){
                    updateMoney(rewardMoney, superMember.getId());
                    financialService.save(superMember.getId(), rewardMoney, superMember.getMoney() + rewardMoney,0,1,null,"推荐用户奖励，推荐用户名："+member.getName(),member.getName());
                }

            }
            return new Result(2,"注册成功",request.getServletContext().getContextPath()+"/member/login");
        }
        return new Result(-1,"注册失败");
    }


    public Result delete(int id) {
        if(super.deleteById(id)){
            return new Result(1,"删除成功");
        }
        return new Result(-1,"删除失败");
    }

    public Result<Member> listByPage(Page page, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Member> list = memberDao.list(page, key);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public Result<Member> managerList(Page page, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Member> list = memberDao.managerList(page, key);
        Result model = new Result(0,page);
        model.setData(list);
        return model;
    }

    public Result managerAdd(Member loginMember, String name) {
        int isAdmin = 1;
        Member findMember = this.findByName(name);
        if(findMember == null){
            return new Result(-1,"会员["+name+"]不存在");
        }
        //为了旧系统升级使用
        if(findMember.getId() == 1 && findMember.getIsAdmin() == 1){
            isAdmin = 2;
        }
        if(isAdmin == 1 && loginMember.getId().intValue() == findMember.getId().intValue()){
            return new Result(-1,"不能操作自己的账号");
        }
        if(isAdmin != 2 && findMember.getIsAdmin() > 0){
            return new Result(-1,"会员["+name+"]已经是管理员，无需再授权");
        }
        //管理员只能对授权为普通管理员
        memberDao.managerAddAndCancel(isAdmin,findMember.getId());
        if(isAdmin == 2){
            loginMember.setIsAdmin(isAdmin);
        }
        return new Result(3,"操作成功");
    }

    public Result managerCancel(Member loginMember, int id) {
        Member findMember = this.findById(id);
        if(loginMember.getId().intValue() == findMember.getId().intValue()){
            return new Result(-1,"不能操作自己的账号");
        }
        if(findMember == null){
            return new Result(-1,"会员不存在");
        }
        memberDao.managerAddAndCancel(0,findMember.getId());
        return new Result(1,"操作成功");
    }


    /**
     * 会员启用、禁用操作
     * @param id
     * @return
     */
    public Result isenable(int id) {
        if(memberDao.isenable(id) == 1){
            return new Result(1,"操作成功");
        }
        return new Result(-1,"操作失败");
    }

    /**
     * 后台修改密码
     * @param id
     * @param password
     * @return
     */
    public Result changepwd(Member loginMember, int id, String password) {
        if(StringUtils.isBlank(password)){
            throw new OpeErrorException("密码不能为空");
        }
        if(password.length() < 6){
            return new Result(-1,"密码不能少于6个字符");
        }
        password = Md5Util.getMD5Code(password);
        if(memberDao.changepwd(id,password) == 1){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(),ActionUtil.CHANGE_PWD);
            return new Result(3,"密码修改成功");
        }
        return new Result(-1,"密码修改失败");
    }

    /**
     * 会员修改密码
     * @param loginMember
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Result changepwd(Member loginMember, String oldPassword, String newPassword) {
        if(StringUtils.isBlank(newPassword)){
            throw new OpeErrorException("密码不能为空");
        }
        if(newPassword.length() < 6){
            return new Result(-1,"密码不能少于6个字符");
        }
        oldPassword = Md5Util.getMD5Code(oldPassword);
        Member member = super.findById(loginMember.getId());
        if(!oldPassword.equals(member.getPassword())){
            return new Result(-1,"旧密码错误");
        }
        return this.changepwd(loginMember,member.getId(),newPassword);
    }

    /**
     * 修改头像
     * @param member
     * @param oldAvatar 旧头像
     * @param request
     * @return
     */
    public Result updateAvatar(Member member, String oldAvatar, HttpServletRequest request) {
        int result = memberDao.updateAvatar(member.getId(),member.getAvatar());
        if(result == 1){
            if(StringUtils.isNotEmpty(oldAvatar) && !Const.DEFAULT_AVATAR.equals(oldAvatar)){
                //头像真实路径
                String realPath = request.getServletContext().getRealPath(oldAvatar);
                //删除旧头像
                File file = new File(realPath);
                if(file.exists()){
                    file.delete();
                }
            }
            return new Result(0,"头像修改成功");
        }
        return new Result(-1,"头像修改失败，请重试");
    }

    /**
     * 修改会员基本信息
     * @param member 登录会员
     * @param name  昵称
     * @param sex  性别
     * @param introduce  个人说明
     * @return
     */
    public Result editBaseInfo(Member member, String name, String sex, String introduce) {
        if(!StringUtils.checkNickname(member.getName())){
            return new Result(-1,"昵称只能由中文、字母、数字、下划线(_)或者短横线(-)组成");
        }
        if (name != null && !name.equals(member.getName())){
            if(this.findByName(name) != null){
                return new Result(-1,"昵称已被占用，请更换一个");
            }
        }
        member.setName(name);
        member.setSex(sex);
        member.setIntroduce(introduce);
        if(memberDao.editBaseInfo(member) == 1){
            return new Result(0,"修改成功");
        }
        return new Result(-1,"修改失败");
    }

    /**
     * 修改会员其他信息
     * @param loginMember 登录会员
     * @param birthday
     * @param qq
     * @param wechat
     * @param contactPhone
     * @param contactEmail
     * @param website
     * @return
     */
    public Result editOtherInfo(Member loginMember, String birthday, String qq, String wechat, String contactPhone,
                                     String contactEmail, String website) {
        loginMember.setBirthday(birthday);
        loginMember.setQq(qq);
        loginMember.setWechat(wechat);
        loginMember.setContactPhone(contactPhone);
        loginMember.setContactEmail(contactEmail);
        loginMember.setWebsite(website);
        if(memberDao.editOtherInfo(loginMember) == 1){
            return new Result(0,"修改成功");
        }
        return new Result(-1,"修改失败");
    }

    public Member findByName(String name) {
        return memberDao.findByName(name);
    }

    public Member findByEmail(String email) {
        return memberDao.findByEmail(email);
    }

    public Member findByPhone(String phone) {
        return memberDao.findByPhone(phone);
    }

    public Result sendEmailActiveValidCode(Member loginMember, HttpServletRequest request) {
        loginMember = this.findById(loginMember.getId());
        if(loginMember.getIsActive() == 1){
            return new Result(-1,"您的账号已经激活，无需重复激活");
        }
        String randomCode = RandomCodeUtil.randomCode6();
        ValidateCode validateCode = new ValidateCode(loginMember.getEmail(),randomCode,2);
        if(validateCodeService.save(validateCode)){
            if(EmailSendUtil.activeMember(request, loginMember.getEmail(),randomCode)){
                return new Result(0,"邮件发送成功");
            }
        }
        return new Result(-1,"邮件发送失败，请重试");
    }

    @Transactional
    public Result active(Member loginMember, String randomCode, HttpServletRequest request) {
        try {
            loginMember = this.findById(loginMember.getId());
            if(loginMember.getIsActive() == 1){
                return new Result(-1,"您的账号已经激活，无需重复激活");
            }
            ValidateCode validateCode = validateCodeService.valid(loginMember.getEmail(),randomCode,2);
            if(validateCode == null){
                return new Result(-1,"验证码错误");
            }

            if(validateCodeService.used(validateCode.getId())){
                if(memberDao.active(loginMember.getId()) == 1){
                    loginMember.setIsActive(1);
                    MemberUtil.setLoginMember(request,loginMember);
                    //邮箱认证奖励
                    scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.EMAIL_AUTHENTICATION);
                    return new Result(2,"激活成功，正在进入会员中心...",request.getContextPath()+"/member/");
                }
            }
            return new Result(-1,"激活失败，请重试");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(-1,"激活失败，请重试");
        }
    }

    public Member findByNameAndEmail(String name, String email) {
        return memberDao.findByNameAndEmail(name,email);
    }

    public Result forgetpwd(String name, String email, HttpServletRequest request) {
        Member member = this.findByNameAndEmail(name,email);
        if(member == null){
            return new Result(-1,"会员不存在");
        }
        String randomCode = RandomCodeUtil.uuid();
        ValidateCode validateCode = new ValidateCode(email,randomCode,1);
        if(validateCodeService.save(validateCode)){
            if(EmailSendUtil.forgetpwd(request, email,randomCode)){
                return new Result(0,"邮件发送成功");
            }
        }
        return new Result(-1,"邮件发送失败，请重试");
    }

    @Transactional
    public Result resetpwd(String email, String token, String password, HttpServletRequest request) {
        Member member = memberDao.findByEmail(email);
        if(member == null){
            return new Result(-1,"会员不存在");
        }
        ValidateCode validateCode = validateCodeService.valid(email,token,1);
        if(validateCode == null){
            return new Result(-1,"验证码错误");
        }
        password = Md5Util.getMD5Code(password);
        if(memberDao.changepwd(member.getId(),password) == 1){
            validateCodeService.used(validateCode.getId());
            actionLogService.save(IpUtil.getIpAddress(request),member.getId(), ActionUtil.FIND_PWD);
            return new Result(2,"密码重置成功",request.getContextPath()+"/member/login");
        }
        return new Result(-1,"密码重置失败");
    }

    @Transactional
    public Result follows(Member loginMember, Integer followWhoId) {
        if(loginMember == null){
            throw new NotLoginException();
        }
        if(this.findById(followWhoId) == null){
            return new Result(-1,"关注的会员不存在");
        }
        if(loginMember.getId().intValue() == followWhoId.intValue()){
            return new Result(-1,"不能关注自己");
        }
        if(memberFansService.find(loginMember.getId(),followWhoId) == null){
            //关注
            memberFansService.save(loginMember.getId(),followWhoId);
            memberDao.follows(loginMember.getId());
            memberDao.fans(followWhoId);
            return new Result(1,"关注成功");
        }else {
            //取消关注
            memberFansService.delete(loginMember.getId(),followWhoId);
            memberDao.follows(loginMember.getId());
            memberDao.fans(followWhoId);
            return new Result(0,"取消关注成功");
        }
    }

    public Result isFollowed(Member loginMember, Integer followWhoId) {
        int loginMemberId = 0;
        if(loginMember != null){
            loginMemberId = loginMember.getId().intValue();
        }
        if(memberFansService.find(loginMemberId,followWhoId) == null){
            return new Result(0,"未关注");
        }else {
            return new Result(1,"已关注");
        }
    }

    public List<Member> listContactMemberIds(Page page, Integer memberId) {
        List<Member> list = memberDao.listContactMemberIds(page, memberId);
        return list;
    }


    /**
     * 获取私信中的联系人列表
     * @param page
     * @param memberId
     * @return
     */
    public Result<Member> listContactMembers(Page page, Integer memberId) {
        List<Member> memberIdList = this.listContactMemberIds(page,memberId);
        List<Member> list = new ArrayList<>();
        if(memberIdList.size() > 0){
            List<Integer> idList = new ArrayList<>();
            String idString = "";
            for (Member member : memberIdList){
                if (member != null){
                    idList.add(member.getId());
                    idString += member.getId() + ",";
                }
            }
            if (idString.length() > 0){
                idString = idString.substring(0,idString.length()-1);
            }
            if (StringUtils.isNotEmpty(idString)){
                list = memberDao.listContactMembers(memberId, idList, idString);
            }
        }
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    /**
     * 更新会员积分
     * @param score
     * @param memberId
     * @return
     */
    public boolean updateScore(Integer score, Integer memberId) {
        return memberDao.updateScore(score,memberId) == 1;
    }

    /**
     * 更新会员余额
     * @param money
     * @param memberId
     * @return
     */
    public boolean updateMoney(Double money, Integer memberId) {
        return memberDao.updateMoney(money,memberId) == 1;
    }

    public String atFormat(String content) {
        List<String> nameList = AtUtil.getAtNameList(content);
        for (String name : nameList){
            Member member = this.findByName(name);
            if (member != null){
                content = AtUtil.replaceAt(content,name,member.getId());
            }
        }
        return content;
    }

    public void increaseMoney(Double money, Integer memberId) {
        ValidUtill.checkParam(money == 0, "输入金额不能为0");
        Member loginMember = MemberUtil.getLoginMember(ContextHolderUtil.getRequest());
        Integer type = 0;
        String remark = "管理员加款";
        Member findMember = findById(memberId);
        if (money < 0){
            type = 1;
            remark = "管理员扣款";
            if (findMember.getMoney().doubleValue() < Math.abs(money)){
                throw new OpeErrorException("账户余额不足，无法扣款");
            }
        }
        updateMoney(money, memberId);
        financialService.save(memberId, Math.abs(money), findMember.getMoney() + money, type, 1, null, remark, loginMember.getName());
    }

    public void increaseScore(Integer score, Integer memberId) {
        ValidUtill.checkParam(score == 0, "输入积分不能为0");
        Integer type = 0;
        if (score < 0){
            type = 1;
            Member findMember = findById(memberId);
            if (findMember.getScore().intValue() < Math.abs(score)){
                throw new OpeErrorException("账户积分不足，无法扣积分");
            }
        }
        updateScore(score, memberId);
        scoreDetailService.save(type, memberId, null, score, "管理员操作");
    }


}
