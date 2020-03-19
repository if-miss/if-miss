package org.gtiles.components.user.user.service;

import java.util.List;

import org.gtiles.components.user.user.bean.UserBean;
import org.gtiles.components.user.user.bean.UserQuery;
/**
 * 
 * 用户 Service接口
 * @author HuGuangJun
 */
public interface IUserService {
	
	/**
	 * 新增用户
	 * @param user 用户对象
	 * @return UserBean 用户对象，包含主键
	 */
	 UserBean addUser(UserBean user);

	/**
	 * 更新用户
	 * @param user 用户对象
	 * @return 更新记录数
	 */
	 int updateUser(UserBean user);

	/**
	 * 删除用户
	 * @param userIds 用户主键数组
	 * @return 删除记录数
	 */
	 int deleteUser(String[] userIds);
	
	/**
	 * 根据ID查询用户
	 * @param userId 用户主键
	 * @return UserBean 用户对象
	 */
	 UserBean findUserById(String userId);
	
	/**
	 * 分页查询用户
	 * @param userQuery 用户查询对象
	 * @return 用户对象分页查询列表
	 */
	 List<UserBean> findUserList(UserQuery userQuery);
}
