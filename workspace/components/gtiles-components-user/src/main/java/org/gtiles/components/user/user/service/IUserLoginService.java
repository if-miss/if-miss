package org.gtiles.components.user.user.service;

import java.util.List;

import org.gtiles.components.user.user.bean.UserLoginBean;
import org.gtiles.components.user.user.bean.UserLoginQuery;
/**
 * 
 * 登录方式 Service接口
 * @author HuGuangJun
 */
public interface IUserLoginService {
	
	/**
	 * 新增登录方式
	 * @param userLogin 登录方式对象
	 * @return UserLoginBean 登录方式对象，包含主键
	 */
	 UserLoginBean addUserLogin(UserLoginBean userLogin);

	/**
	 * 更新登录方式
	 * @param userLogin 登录方式对象
	 * @return 更新记录数
	 */
	 int updateUserLogin(UserLoginBean userLogin);

	/**
	 * 删除登录方式
	 * @param userLoginIds 登录方式主键数组
	 * @return 删除记录数
	 */
	 int deleteUserLogin(String[] userLoginIds);
	
	/**
	 * 根据ID查询登录方式
	 * @param userLoginId 登录方式主键
	 * @return UserLoginBean 登录方式对象
	 */
	 UserLoginBean findUserLoginById(String userLoginId);
	
	/**
	 * 分页查询登录方式
	 * @param userLoginQuery 登录方式查询对象
	 * @return 登录方式对象分页查询列表
	 */
	 List<UserLoginBean> findUserLoginList(UserLoginQuery userLoginQuery);
}
