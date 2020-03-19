package com.mumu.manager.repository;

import com.mumu.manager.entity.User;

/**
 * 用户接口
 * 
 * @author Administrator
 * 
 */
public interface UserRepository extends BaseRepository<User, Long> {
	/**
	 * 根据用户名和密码返回的一个user对象，定义方法为getBy即为查询
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User getByUserNameAndPassWord(String userName, String passWord);

}
