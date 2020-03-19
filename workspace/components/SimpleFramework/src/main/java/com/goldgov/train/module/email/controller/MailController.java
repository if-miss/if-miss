package com.goldgov.train.module.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.train.mail.Mail;
import com.goldgov.train.mail.MailRepository;

@Controller
public class MailController {

	@RequestMapping("mail")
	public String mailList(Model model){
		MailRepository repository = MailRepository.getInstance();
		Mail mail = repository.getLastMail();
		model.addAttribute("mail", mail);
		
		return "email/pages/mail.ftl";
	}
}
