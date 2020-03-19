package org.gtiles.components.user.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.gtiles.components.user.user.dao.IUserDao;
import org.gtiles.components.user.user.service.IUserService;
import org.gtiles.components.user.user.bean.UserBean;
import org.gtiles.components.user.user.entity.UserEntity;
import org.gtiles.components.user.user.bean.UserQuery;
/**
 * 用户 Service实现
 * @author HuGuangJun
 */
@Service("org.gtiles.components.user.user.service.impl.UserServiceImpl")
public class UserServiceImpl implements IUserService {

	
	@Autowired
	@Qualifier("org.gtiles.components.user.user.dao.IUserDao")
	private IUserDao userDao;
	
	@Override
	public UserBean addUser(UserBean user) {
		UserEntity userEntity = user.toEntity();
		userDao.addUser(userEntity);
		return new UserBean(userEntity);
	}

	@Override
	public int updateUser(UserBean user) {
		return userDao.updateUser(user.toEntity());
	}

	@Override
	public int deleteUser(String[] userIds) {
		return userDao.deleteUser(userIds);
	}

	@Override
	public List<UserBean> findUserList(UserQuery userQuery) {
		return userDao.findUserListByPage(userQuery);
	}

	@Override
	public UserBean findUserById(String userId) {
		return userDao.findUserById(userId);
	}

}
