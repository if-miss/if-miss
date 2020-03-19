package org.gtiles.components.user.user.bean;

import org.gtiles.core.service.Query;

/**
 * 用户 QueryBean
 * 
 * @author HuGuangJun
 */
public class UserQuery extends Query<UserBean> {

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
