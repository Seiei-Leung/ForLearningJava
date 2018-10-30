package top.Seiei.javaUtil;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	final String smtpHost;
	final String userName;
	final String passWord;
	final boolean debug;

	public SendEmail(String smtpHost, String userName, String passWord,boolean debug) {
		this.smtpHost = smtpHost;
		this.debug = debug;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	// 创建 与邮件服务器会话对象 Session 对象
	public Session creatSession() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "SMTP"); 
		properties.put("mail.smtp.host", this.smtpHost); // SMTP 主机名
		properties.put("mail.smtp.port", "25"); // 主机端口名
		properties.put("mail.smtp.auth", "true"); // 是否需要用户验证
		// 验证账号及密码，密码需要是第三方授权码
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("18689407365@163.com", "mrlzc19938215216");
		    }
		};
		Session session = Session.getInstance(properties, auth);
		session.setDebug(this.debug); // 显示调试信息
		return session;
	}
	
	// 创建一个Message，它相当于是邮件内容
	public Message createMessage(Session session, String from, String subject, String body) throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress(this.userName));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(from));
		// 设置主题
		message.setSubject(subject, "UTF-8");
		// 设置内容
		// message.setText(body, "UTF-8"); // 发送纯文本内容
		message.setText(body, "UTF-8", "html"); // 发送网页， 163邮箱报错
		
		return message;
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
		// 此密码不是登录密码，需要另外申请	
		SendEmail sendEmail = new SendEmail("smtp.163.com", "18689407365@163.com", "mrlzc19938215216", true);
		// 创建session
		Session session = sendEmail.creatSession();
		// 创建message
		Message message = sendEmail.createMessage(session, "786883603@qq.com", "邮件测试题目", "<h1>这是一封有意义的信件测试呀！！！</h1><p>这是内容</p><ul><li>这是li</li></ul>");
		// 发送邮件
		Transport.send(message);
	}

}
