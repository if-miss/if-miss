package com.goldgov.train.module.hgj.service;

import com.goldgov.train.module.hgj.bean.UserBean;

public interface IUserService {
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	UserBean addUser(UserBean user);

	/**
	 * 根据ID更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserById(UserBean user);

	/**
	 * 根据用户名更新信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserByName(UserBean user);

	/**
	 * 根据ID获取用户
	 * 
	 * @param userName
	 * @return
	 */
	UserBean findUserById(String userId);

	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	UserBean findUserByName(String userName);

	/**
	 * 根据用户名和密码获取用户
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */

	UserBean findUserByNameAndPassword(String userName, String passWord);

}
