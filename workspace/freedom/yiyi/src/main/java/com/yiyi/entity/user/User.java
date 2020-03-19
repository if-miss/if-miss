package com.yiyi.entity.user;

import com.yiyi.entity.common.BaseObject;

/**
 * @ClassName User
 * @Description: TODO(用户信息)
 * @author HuGuangJun
 * @date 2016年4月19日 上午9:50:16
 */
public class User extends BaseObject {
	/**
	 * @Fields serialVersionUID: TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	/** 用户名 */
	public String userName;
	/** 密码 */
	public String passWord;
	/** 电话 */
	public String iphone;
	/** 地址 */
	public String address;

	public User() {
		super();
	}

	public User(Long id, String userName, String passWord) {
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

	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
