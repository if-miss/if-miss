package com.guangjun.manager.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guangjun.manager.entity.User;
import com.guangjun.manager.service.UserService;
import com.guangjun.util.WebUtil;

@Controller
@RequestMapping("/user")
public class UserHandler {
	@Autowired
	UserService userService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/login")
	public String input(Map<String, Object> map, HttpServletRequest request) {
		if (WebUtil.checkLogin(request)) {
			return "home/index";
		}
		map.put("user", new User());
		return "home/login";
	}

	/**
	 * 跳转到登录页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(User user) {

		userService.saveAndUpdate(user);
		System.out.println(user.getUserName() + "--" + user.getPassWord());
		return "home/index";
	}

	/**
	 * 登录操作
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "state", required = false, defaultValue = "0") String state,
			HttpServletRequest request, HttpServletResponse response, User user) {
		User loginUser = userService.getByNameAndPassword(user.getUserName(),
				user.getPassWord());
		// 将用户信息放入session中
		WebUtil.login(request, response, loginUser, "1".equals(state) ? true
				: false);
		return "home/index";
	}
}
