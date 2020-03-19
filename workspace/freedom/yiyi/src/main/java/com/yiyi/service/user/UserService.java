package com.yiyi.service.user;

import com.yiyi.entity.user.User;

/**
 * @ClassName UserService
 * @Description: TODO(业务层)
 * @author HuGuangJun
 * @date 2016年4月19日 下午4:37:31
 */
public interface UserService {
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

	/**
	 * 
	 * @Title: getTimestamp
	 * @Description: TODO(获取缓存)
	 * @param parm
	 * @return
	 * @author HuGuangJun
	 * @date 2016年5月5日 上午11:24:13
	 */
	String getTimestamp(String parm);

}
