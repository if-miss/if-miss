package com.mumu.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mumu.manager.entity.User;
import com.mumu.manager.repository.UserRepository;

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
		User byUserNameAndPassWord = ((UserRepository) baseRepository)
				.getByUserNameAndPassWord(userName, passWord);
		return byUserNameAndPassWord;
	};

}
