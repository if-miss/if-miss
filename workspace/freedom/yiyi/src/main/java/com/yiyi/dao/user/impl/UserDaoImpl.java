package com.yiyi.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.yiyi.dao.user.UserDao;
import com.yiyi.entity.user.User;
import com.yiyi.module.mybatis.dao.MybatisBaseDao;

/**
 * @ClassName UserDaoImpl
 * @Description: TODO(用户管理实现类)
 * @author HuGuangJun
 * @date 2016年4月20日 下午2:49:43
 */
@Repository
public class UserDaoImpl extends MybatisBaseDao<User> implements UserDao {

	public User getUserByUserNamePassWord(User user) {
		User logUser = selectOne("user_getUser", user);
		return logUser;
	}

}
