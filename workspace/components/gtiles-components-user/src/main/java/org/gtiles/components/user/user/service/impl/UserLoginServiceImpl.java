package org.gtiles.components.user.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.gtiles.components.user.user.dao.IUserLoginDao;
import org.gtiles.components.user.user.service.IUserLoginService;
import org.gtiles.components.user.user.bean.UserLoginBean;
import org.gtiles.components.user.user.entity.UserLoginEntity;
import org.gtiles.components.user.user.bean.UserLoginQuery;
/**
 * 登录方式 Service实现
 * @author HuGuangJun
 */
@Service("org.gtiles.components.user.user.service.impl.UserLoginServiceImpl")
public class UserLoginServiceImpl implements IUserLoginService {

	
	@Autowired
	@Qualifier("org.gtiles.components.user.user.dao.IUserLoginDao")
	private IUserLoginDao userLoginDao;
	
	@Override
	public UserLoginBean addUserLogin(UserLoginBean userLogin) {
		UserLoginEntity userLoginEntity = userLogin.toEntity();
		userLoginDao.addUserLogin(userLoginEntity);
		return new UserLoginBean(userLoginEntity);
	}

	@Override
	public int updateUserLogin(UserLoginBean userLogin) {
		return userLoginDao.updateUserLogin(userLogin.toEntity());
	}

	@Override
	public int deleteUserLogin(String[] userLoginIds) {
		return userLoginDao.deleteUserLogin(userLoginIds);
	}

	@Override
	public List<UserLoginBean> findUserLoginList(UserLoginQuery userLoginQuery) {
		return userLoginDao.findUserLoginListByPage(userLoginQuery);
	}

	@Override
	public UserLoginBean findUserLoginById(String userLoginId) {
		return userLoginDao.findUserLoginById(userLoginId);
	}

}
