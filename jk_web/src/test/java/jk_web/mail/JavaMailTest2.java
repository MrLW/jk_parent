package jk_web.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailTest2 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-mail.xml");

		SimpleMailMessage message = (SimpleMailMessage) ac.getBean("mailMessage");// 加载简单邮件对象
		JavaMailSender sender = (JavaMailSender) ac.getBean("mailSender"); // 得到邮件的发送对象，专门用于邮件发送

		// 设置简单邮件对象的属性
		message.setSubject("spring与javamail的测试");// 主题
		message.setText("hello,spring and javamail ");// 内容
		message.setTo("1819270694@qq.com");// 收件箱

		// 发送邮件
		sender.send(message);
	}
}
