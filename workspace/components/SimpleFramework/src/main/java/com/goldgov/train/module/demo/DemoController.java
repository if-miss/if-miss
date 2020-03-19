package com.goldgov.train.module.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.train.mail.Mail;
import com.goldgov.train.mail.MailSender;
import com.goldgov.train.module.demo.mybatis.MyBatisDaoDemo;

@Controller
public class DemoController {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MyBatisDaoDemo myBatisDaoDemo ;
	
	@RequestMapping("helloworld")
	public String helloworld(){
		Mail mail = new Mail();
		mail.setTo("liuhaigang@goldgov.com");
		mail.setSubject("邮件标题");
		mail.setContent("邮件内容啊，邮件内容啊，邮件内容啊，邮件内容啊。");
		mailSender.send(mail);
		System.out.println(myBatisDaoDemo.findAllDatas().size());;
		return "demo.jsp";
	}
}
