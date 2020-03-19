package org.gtiles.components.user.user.bean;

import org.gtiles.components.user.user.entity.UserLoginEntity;

/**
 * 登录方式 Bean
 * 
 * @author HuGuangJun
 */
public class UserLoginBean {

	private UserLoginEntity userLoginEntity;

	public UserLoginEntity toEntity() {
		return userLoginEntity;
	}

	public UserLoginBean() {
		userLoginEntity = new UserLoginEntity();
	}

	public UserLoginBean(UserLoginEntity userLoginEntity) {
		this.userLoginEntity = userLoginEntity;
	}

	/**
	 * 获取用户ID
	 */
	public String getUserId() {
		return userLoginEntity.getUserId();
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(String userId) {
		userLoginEntity.setUserId(userId);
	}

	/**
	 * 获取登录账号
	 */
	public String getLoginAccount() {
		return userLoginEntity.getLoginAccount();
	}

	/**
	 * 设置登录账号
	 */
	public void setLoginAccount(String loginAccount) {
		userLoginEntity.setLoginAccount(loginAccount);
	}

	/**
	 * 获取登录方式
	 */
	public Integer getLoginWay() {
		return userLoginEntity.getLoginWay();
	}

	/**
	 * 设置登录方式
	 */
	public void setLoginWay(Integer loginWay) {
		userLoginEntity.setLoginWay(loginWay);
	}

	/**
	 * 获取loginId
	 */
	public String getLoginId() {
		return userLoginEntity.getLoginId();
	}

	/**
	 * 设置loginId
	 */
	public void setLoginId(String loginId) {
		userLoginEntity.setLoginId(loginId);
	}

}
