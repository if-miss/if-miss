package org.gtiles.components.user.user.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.gtiles.core.dao.mybatis.annotation.MybatisRepository;
import org.gtiles.components.user.user.entity.UserEntity;
import org.gtiles.components.user.user.bean.UserBean;
import org.gtiles.components.user.user.bean.UserQuery;

/**
 * 用户 Dao接口
 * 
 * @author HuGuangJun
 */
@MybatisRepository("org.gtiles.components.user.user.dao.IUserDao")
public interface IUserDao {

	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户对象
	 */
	void addUser(UserEntity user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 操作记录数
	 */
	int updateUser(UserEntity user);

	/**
	 * 删除用户
	 * 
	 * @param userIds
	 *            用户主键数组
	 * @return 操作记录数
	 */
	int deleteUser(@Param("ids") String[] userIds);

	/**
	 * 根据ID查询用户
	 * 
	 * @param userId
	 *            用户主键
	 * @return 用户对象
	 */
	UserBean findUserById(@Param("id") String userId);

	/**
	 * 分页查询用户
	 * 
	 * @param userQuery
	 *            用户查询对象
	 * @return 用户对象分页查询列表
	 */
	List<UserBean> findUserListByPage(@Param("query") UserQuery userQuery);

}
