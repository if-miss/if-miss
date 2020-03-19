package com.yiyi.service.user.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiyi.dao.user.UserDao;
import com.yiyi.entity.user.User;
import com.yiyi.module.datasource.DataSource;
import com.yiyi.module.datasource.DataSourceEnum;
import com.yiyi.service.user.UserService;

/**
 * @ClassName UserService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuGuangJun
 * @date 2016年4月19日 下午4:37:31
 */
@Component
@DataSource(DataSourceEnum.MASTER)
public class UserServiceImpl implements UserService {
	/** 自动注入用户 */
	@Autowired
	UserDao userDao;

	/** 获取用户信息 */
	public User getUserByUserNamePassWord(User user) {
		return userDao.getUserByUserNamePassWord(user);
	}
	/**
	 * 
	 * @Title: getTimestamp
	 * @Description: TODO(缓存文件)
	 * @param parm
	 * @return
	 * @author HuGuangJun
	 * @date 2016年5月4日 下午5:31:13
	 */
	public String getTimestamp(String parm){
		 Long time=System.currentTimeMillis();
		 System.out.println("执行方法:"+time);
		return time.toString();
	}

}
