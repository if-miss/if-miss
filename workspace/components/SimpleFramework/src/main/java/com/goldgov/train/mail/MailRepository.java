package com.goldgov.train.mail;

import java.util.ArrayList;
import java.util.List;

public class MailRepository {

	private static MailRepository mailRepo = new MailRepository();
	
	private List<Mail> mailList = new ArrayList<Mail>();
	
	public static MailRepository getInstance(){
		return mailRepo;
	}
	
	public void addMail(Mail mail){
		mailList.add(mail);
	}
	
	public Mail getMail(int index){
		return mailList.get(index);
	}
	
	public Mail getLastMail(){
		if(mailList.size() == 0){
			return null;
		}
		return mailList.get(mailList.size() -1);
	}
	
	public void removeMail(int index){
		mailList.remove(index);
	}
	
	public List<Mail> getAlldMail(){
		return mailList;
	}
}
