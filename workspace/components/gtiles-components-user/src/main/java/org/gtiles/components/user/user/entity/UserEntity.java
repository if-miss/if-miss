package org.gtiles.components.user.user.entity;

import java.util.Date;

/**
 * 用户 实体对象
 * 
 * @author HuGuangJun
 */
public class UserEntity {
	private String userId;// 用户ID
	private String userAccount;// 用户账号
	private String userPassword;// 用户密码
	private String userName;// 用户昵称
	private Integer userSex;// 用户性别
	private String userPhone;// 用户手机
	private String userEmail;// 用户邮箱
	private Date updateTime;// updateTime

	/**
	 * 获取用户ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户账号
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * 设置用户账号
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * 获取用户密码
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * 设置用户密码
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 获取用户昵称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户昵称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取用户性别
	 */
	public Integer getUserSex() {
		return userSex;
	}

	/**
	 * 设置用户性别
	 */
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	/**
	 * 获取用户手机
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 设置用户手机
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * 获取用户邮箱
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 设置用户邮箱
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * 获取updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
