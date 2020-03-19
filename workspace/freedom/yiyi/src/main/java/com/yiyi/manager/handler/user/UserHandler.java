package com.yiyi.manager.handler.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yiyi.entity.user.User;
import com.yiyi.service.user.UserService;

/**
 * @ClassName UserHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuGuangJun
 * @date 2016年4月19日 下午4:39:58
 */
@Controller
@RequestMapping("/user")
public class UserHandler {
	/** 日志打印 */
	Logger logger = LoggerFactory.getLogger(UserHandler.class);
	/** 自动注入 */
	@Autowired
	UserService userService;

	/**
	 * 
	 * @Title: home
	 * @Description: TODO(去主页面)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2016年4月21日 下午2:55:54
	 */
	@RequestMapping("/index")
	public String index(Map<String, Object> map, HttpServletRequest request) {
		return "index";
	}

	/**
	 * 
	 * @Title: login
	 * @Description: TODO(去登录页面)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2016年4月21日 下午2:56:36
	 */
	@RequestMapping("/tologin")
	public String login(Map<String, Object> map, HttpServletRequest request) {
		map.put("user", new User());
		return "user/login";
	}

	/**
	 * 
	 * @Title: user
	 * @Description: TODO(登录验证)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2016年4月26日 上午11:15:49
	 */
	@RequestMapping("/user")
	public String user(Map<String, Object> map,
			@RequestParam(required = true, value = "userName") String userName,
			@RequestParam(required = true, value = "passWord") String passWord) {
		logger.info("用户名为：" + userName + "密码为：" + passWord);

		User userByUserNamePassWord = userService
				.getUserByUserNamePassWord(new User(11l, userName, passWord));

		if (userByUserNamePassWord != null) {
			return "index";
		} else {
			map.put("user", new User());
			return "user/login";
		}
	}
}
