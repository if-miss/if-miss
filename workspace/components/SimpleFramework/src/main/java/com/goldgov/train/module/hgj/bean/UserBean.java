package com.goldgov.train.module.hgj.bean;

import java.util.Date;

public class UserBean {

	private String userId;// 用户ID
	private String userName;// 用户名
	private String LoginName;// 登录名
	private String password;// 密码
	private String userMail;// 用户邮箱
	private Date loginFailTime;// 登录失败时间

	/**
	 * 扩展字段界面显示
	 * 
	 * @return
	 */
	private Date loginSuccessTime;// 登录成功时间

	private int loginNumber;// 登录次数

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginFailTime() {
		return loginFailTime;
	}

	public void setLoginFailTime(Date loginFailTime) {
		this.loginFailTime = loginFailTime;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getLoginSuccessTime() {
		return loginSuccessTime;
	}

	public void setLoginSuccessTime(Date loginSuccessTime) {
		this.loginSuccessTime = loginSuccessTime;
	}

	public int getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(int loginNumber) {
		this.loginNumber = loginNumber;
	}

}
