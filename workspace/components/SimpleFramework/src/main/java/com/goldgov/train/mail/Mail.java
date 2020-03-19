package com.goldgov.train.mail;

public class Mail {

	private String to;
	
	private String subject;//邮件主题
	private String content;//邮件内容
	
	public Mail(){}
	
	public Mail(String subject,String content){
		this(subject,content,null);
	}
	
	public Mail(String subject,String content,String to){
		this.subject = subject;
		this.content = content;
		this.to = to;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
