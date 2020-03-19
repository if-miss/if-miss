package com.yiyi.manager.handler.home;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiyi.entity.user.User;
import com.yiyi.service.user.UserService;

/**
 * @ClassName HomeHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuGuangJun
 * @date 2016年5月4日 上午11:25:36
 */
@Controller
@RequestMapping("/home")
public class HomeHandler {
	@Autowired
	UserService userService;

	/**
	 * 
	 * @Title: toHome
	 * @Description: TODO(去主页)
	 * @return
	 * @author HuGuangJun
	 * @date 2016年5月4日 上午11:26:34
	 */
	@RequestMapping("/toHome")
	public String toHome() {
		return "home/home";
	}

	/**
	 * 
	 * @Title: getCache
	 * @Description: TODO(获取缓存)
	 * @param map
	 * @return
	 * @author HuGuangJun
	 * @date 2016年5月5日 上午11:20:25
	 */
	@RequestMapping("/cache")
	public String getCache(Map<String, Object> map) {
		User user = userService.getUserByUserNamePassWord(new User(1l,"admin","admin"));
		map.put("parm", userService.getTimestamp("parm"));
		return "home/home";
	}

}
