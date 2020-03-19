package org.gtiles.components.user.user.bean;

import org.gtiles.core.service.Query;

/**
 * 登录方式 QueryBean
 * 
 * @author HuGuangJun
 */
public class UserLoginQuery extends Query<UserLoginBean> {

	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
