package com.mumu.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "g_user")*/
public class User extends IdEntity {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String passWord;

	public User() {
		super();
	}

	public User(Long id, String userName, String passWord) {
		super(id);
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
