package com.lxinet.jeesns.core.utils;

import com.lxinet.jeesns.modules.sys.service.IConfigService;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderUtil {
	private static final String CONFIG_SERVICE = "configService";

	private EmailSenderUtil(){

	}

	private static boolean sendMail(String email, String content,String title) {
		IConfigService configService = SpringContextHolder.getBean(CONFIG_SERVICE);
		Map<String,String> config = configService.getConfigToMap();
		final String account = config.get(ConfigUtil.SITE_SEND_EMAIL_ACCOUNT);
		final String passWord = config.get(ConfigUtil.SITE_SEND_EMAIL_PASSWORD);
		final String smtp = config.get(ConfigUtil.SITE_SEND_EMAIL_SMTP);
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", smtp);// 用户主机
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, passWord);// 获取用户名和密码
			}
		});

		Message msg = new MimeMessage(session);
		session.setDebug(false);// 查看调试信息:true,不查看：false;
		try {
			String sendnickname = "";
			try {
				sendnickname = javax.mail.internet.MimeUtility.encodeText(config.get(ConfigUtil.SITE_NAME));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
			msg.setFrom(new InternetAddress(account));
//			msg.setFrom(new InternetAddress(sendnickname + " <" + email +">"));
			msg.setSubject(title);
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(email));// 多个收件人
			msg.setContent(content, "text/html;charset=utf-8");// 文本/指定文本类型，字符集
			Transport.send(msg);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			//发送失败
			return false;
		}// 发送端
		return true;
	}

	/**
	 * 会员激活
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public static boolean activeMember(String email,String randomCode){
		IConfigService configService = SpringContextHolder.getBean(CONFIG_SERVICE);
		Map<String,String> config = configService.getConfigToMap();
		String title = config.get(ConfigUtil.SITE_NAME) + "会员账号激活";
		String content = "欢迎加入"+config.get("site_name")+"，您的账号激活验证码为：【"+randomCode+"】，30分钟内有效，请马上进行验证。若非本人操作，请忽略此邮件。";
		return sendMail(email,content,title);
	}

	/**
	 * 忘记密码
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public static boolean forgetpwd(String email,String randomCode){
		IConfigService configService = SpringContextHolder.getBean(CONFIG_SERVICE);
		Map<String,String> config = configService.getConfigToMap();
		String title = config.get(ConfigUtil.SITE_NAME) + "找回密码";
		String content = "<h4>您好，"+email+"：</h4><p>请点击下面的链接来重置您的密码<br  />"+
				"<a href='"+config.get(ConfigUtil.SITE_DOMAIN)+"member/resetpwd?email="+email+"&token="+randomCode+"' target='_blank'>"+
				config.get(ConfigUtil.SITE_DOMAIN)+"member/resetPwd?email="+email+"&token="+randomCode+"</a><br  />" +
				"本链接30分钟内有效。<br />" +
				"(如果点击链接无反应，请复制链接到浏览器里直接打开)<p>" ;
		return sendMail(email,content,title);
	}

}
