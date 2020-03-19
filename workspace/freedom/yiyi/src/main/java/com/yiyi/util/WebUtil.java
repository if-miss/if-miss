package com.yiyi.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yiyi.entity.user.User;

/**
 * 关于web的工具方法
 * 
 * @author Administrator
 * 
 */
public class WebUtil {
	/**
	 * 检查用户是否是自动登录
	 * 
	 * @param request
	 */
	public static boolean checkLogin(HttpServletRequest request) {
		boolean flag = false;
		HttpSession session = request.getSession();
		// 1: 拿去到session中的用户信息
		User user = (User) session.getAttribute("user");
		/**
		 * 用户没有登录的情况下才需要自动登录
		 */
		if (user == null) {// 表示没有用户登录,用户登录的情况下就不会执行以下代码
			// 2: 在没有用户登录情况下，才到cookie去找是否有存过需要自动登录的用户
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					String name = c.getName();
					if (name.equals("userinfo")) {// 表示存在持久化的用户登录信息
						String[] value = c.getValue().split("\\|");
						// 3: 将存储在cookie中的信息取出来并存放在user对象中
						User u = new User(new Long(value[0]), value[1],
								value[2]);
						// 4: 然后将u对象保存在session中
						session.setAttribute("user", u);
						flag = true;
					}
				}

			}
		}
		return flag;
	}

	/**
	 * 实现自动登录的功能,并实现保存用户信息，实现下次自动登录
	 * 
	 * @param request
	 * @param response
	 * @param user
	 */
	public static void login(HttpServletRequest request,
			HttpServletResponse response, User user, boolean flag) {
		// 1: 拿去到session对象
		HttpSession session = request.getSession();
		// 2:将当前用户信息给保存到的session中
		session.setAttribute("user", user);
		if (flag) {// 3: 表示选中自动登录，此时我们将session持久化

			// 4: 创建一个cookie对象，将user对象的信息用字符串方法到cookie中
			Cookie cookie = new Cookie("userinfo", user.getId() + "|"
					+ user.getUserName() + "|" + user.getPassWord());
			// 5:可以存一天
			cookie.setMaxAge(60 * 60 * 24 * 7);
			// 6:存放的位置为项目虚拟路径
			cookie.setPath(request.getContextPath());
			// 7:告诉浏览器，我需存储该cookie值
			response.addCookie(cookie);
		}
	}

	/**
	 * 获取已经登录的用户
	 * 
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {

		return (User) request.getSession().getAttribute("user");
	}

}
