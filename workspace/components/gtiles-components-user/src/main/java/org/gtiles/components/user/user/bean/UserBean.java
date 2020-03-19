package org.gtiles.components.user.user.bean;

import java.util.Date;

import org.gtiles.components.user.user.entity.UserEntity;

/**
 * 用户 Bean
 * 
 * @author HuGuangJun
 */
public class UserBean {

	private UserEntity userEntity;

	public UserEntity toEntity() {
		return userEntity;
	}

	public UserBean() {
		userEntity = new UserEntity();
	}

	public UserBean(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	/**
	 * 获取用户ID
	 */
	public String getUserId() {
		return userEntity.getUserId();
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		userEntity.setUserId(userId);
	}

	/**
	 * 获取用户账号
	 */
	public String getUserAccount() {
		return userEntity.getUserAccount();
	}

	/**
	 * 设置用户账号
	 */
	public void setUserAccount(String userAccount) {
		userEntity.setUserAccount(userAccount);
	}

	/**
	 * 获取用户密码
	 */
	public String getUserPassword() {
		return userEntity.getUserPassword();
	}

	/**
	 * 设置用户密码
	 */
	public void setUserPassword(String userPassword) {
		userEntity.setUserPassword(userPassword);
	}

	/**
	 * 获取用户昵称
	 */
	public String getUserName() {
		return userEntity.getUserName();
	}

	/**
	 * 设置用户昵称
	 */
	public void setUserName(String userName) {
		userEntity.setUserName(userName);
	}

	/**
	 * 获取用户性别
	 */
	public Integer getUserSex() {
		return userEntity.getUserSex();
	}

	/**
	 * 设置用户性别
	 */
	public void setUserSex(Integer userSex) {
		userEntity.setUserSex(userSex);
	}

	/**
	 * 获取用户手机
	 */
	public String getUserPhone() {
		return userEntity.getUserPhone();
	}

	/**
	 * 设置用户手机
	 */
	public void setUserPhone(String userPhone) {
		userEntity.setUserPhone(userPhone);
	}

	/**
	 * 获取用户邮箱
	 */
	public String getUserEmail() {
		return userEntity.getUserEmail();
	}

	/**
	 * 设置用户邮箱
	 */
	public void setUserEmail(String userEmail) {
		userEntity.setUserEmail(userEmail);
	}

	/**
	 * 获取updateTime
	 */
	public Date getUpdateTime() {
		return userEntity.getUpdateTime();
	}

	/**
	 * 设置updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		userEntity.setUpdateTime(updateTime);
	}

}
