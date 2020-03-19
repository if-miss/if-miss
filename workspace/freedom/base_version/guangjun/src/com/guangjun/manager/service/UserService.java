package com.guangjun.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guangjun.common.service.BaseService;
import com.guangjun.manager.entity.User;
import com.guangjun.manager.repository.UserRepository;

@Transactional
@Service
public class UserService extends BaseService<User, Long> {

	/**
	 * 根据用户名和密码登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User getByNameAndPassword(String userName, String passWord) {
		return ((UserRepository) baseRepository).getByUserNameAndPassWord(
				userName, passWord);
	};

}
