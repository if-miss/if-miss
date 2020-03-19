package com.goldgov.train.module.hgj.dao;

import org.apache.ibatis.annotations.Param;

import com.goldgov.train.annotation.MybatisRepository;
import com.goldgov.train.module.hgj.bean.UserBean;

/**
 * 用户DAO
 *
 * @author IF阳光请疯狂
 *
 */
@MybatisRepository
public interface IUserDao {

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	void addUser(UserBean user);

	/**
	 * 更新的用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserById(UserBean user);

	/**
	 * 根据用户更新信息
	 * 
	 * @param user
	 * @return
	 */
	int updateUserByName(UserBean user);

	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	UserBean findUserById(@Param("userId") String userId);
	
	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	UserBean findUserByName(@Param("loginName") String userName);

	/**
	 * 根据用户名和密码获取用户
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */

	UserBean findUserByNameAndPassword(@Param("loginName") String userName, @Param("passWord") String passWord);
}
