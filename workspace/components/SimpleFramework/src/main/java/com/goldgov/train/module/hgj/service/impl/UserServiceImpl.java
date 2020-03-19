package com.goldgov.train.module.hgj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.train.module.hgj.bean.UserBean;
import com.goldgov.train.module.hgj.dao.IUserDao;
import com.goldgov.train.module.hgj.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;

	public UserBean addUser(UserBean user) {
		userDao.addUser(user);
		return user;
	}

	public int updateUserById(UserBean user) {
		return userDao.updateUserById(user);
	}

	public UserBean findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}

	public UserBean findUserByNameAndPassword(String userName, String passWord) {
		return userDao.findUserByNameAndPassword(userName, passWord);
	}

	public int updateUserByName(UserBean user) {
		return userDao.updateUserByName(user);
	}

	public UserBean findUserById(String userId) {
		return userDao.findUserById(userId);
	}

}
