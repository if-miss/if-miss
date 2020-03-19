package com.yiyi.dao.user;

import com.yiyi.entity.user.User;

/**
 * @ClassName UserDao
 * @Description: TODO(用户管理接口)
 * @author HuGuangJun
 * @date 2016年4月20日 下午2:52:26
 */
public interface UserDao {
	/**
	 * 
	 * @Title: queryUserByUserNamePassWord
	 * @Description: TODO(根据用户名密码查询用户)
	 * @param user
	 * @return
	 * @author HuGuangJun
	 * @date 2016年4月20日 下午2:55:21
	 */
	User getUserByUserNamePassWord(User user);

}
