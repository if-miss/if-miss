package com.goldgov.train.mail.impl;

import org.springframework.stereotype.Component;

import com.goldgov.train.mail.Mail;
import com.goldgov.train.mail.MailRepository;
import com.goldgov.train.mail.MailSender;

@Component("mailSender")
public class MailSenderImpl implements MailSender{

	public void send(Mail mail) {
		MailRepository.getInstance().addMail(mail);
	}

}
