package org.gtiles.components.user.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.gtiles.components.user.user.bean.UserLoginBean;
import org.gtiles.components.user.user.bean.UserLoginQuery;
import org.gtiles.components.user.user.entity.UserLoginEntity;
import org.gtiles.core.dao.mybatis.annotation.MybatisRepository;

/**
 * 登录方式 Dao接口
 * 
 * @author HuGuangJun
 */
@MybatisRepository("org.gtiles.components.user.user.dao.IUserLoginDao")
public interface IUserLoginDao {

	/**
	 * 新增登录方式
	 * 
	 * @param userLogin
	 *            登录方式对象
	 */
	void addUserLogin(UserLoginEntity userLogin);

	/**
	 * 更新登录方式
	 * 
	 * @param userLogin
	 *            登录方式对象
	 * @return 操作记录数
	 */
	int updateUserLogin(UserLoginEntity userLogin);

	/**
	 * 删除登录方式
	 * 
	 * @param userLoginIds
	 *            登录方式主键数组
	 * @return 操作记录数
	 */
	int deleteUserLogin(@Param("ids") String[] userLoginIds);

	/**
	 * 根据ID查询登录方式
	 * 
	 * @param userLoginId
	 *            登录方式主键
	 * @return 登录方式对象
	 */
	UserLoginBean findUserLoginById(@Param("id") String userLoginId);

	/**
	 * 分页查询登录方式
	 * 
	 * @param userLoginQuery
	 *            登录方式查询对象
	 * @return 登录方式对象分页查询列表
	 */
	List<UserLoginBean> findUserLoginListByPage(@Param("query") UserLoginQuery userLoginQuery);

}
