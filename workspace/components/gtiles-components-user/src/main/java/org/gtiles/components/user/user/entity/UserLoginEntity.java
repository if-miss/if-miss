package org.gtiles.components.user.user.entity;

/**
 * 登录方式 实体对象
 * 
 * @author HuGuangJun
 */
public class UserLoginEntity {
	private String userId;// 用户ID
	private String loginAccount;// 登录账号
	private Integer loginWay;// 登录方式
	private String loginId;// loginId

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
	 * 获取登录账号
	 */
	public String getLoginAccount() {
		return loginAccount;
	}

	/**
	 * 设置登录账号
	 */
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	/**
	 * 获取登录方式
	 */
	public Integer getLoginWay() {
		return loginWay;
	}

	/**
	 * 设置登录方式
	 */
	public void setLoginWay(Integer loginWay) {
		this.loginWay = loginWay;
	}

	/**
	 * 获取loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * 设置loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
