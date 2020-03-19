package com.mumu.manager.handler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.entity.user.User;
import com.mumu.service.user.UserService;
import com.mumu.util.WebUtil;

/**
 * 
 * @ClassName: UserHandler
 * @Description: TODO
 * @author 光军
 * @date 2015年9月23日 上午12:09:37
 *
 */
@Controller
@RequestMapping("/user")
public class UserHandler {
	/*
	 * @Autowired UserService userService;
	 */
	/**
	 * 
	 * @Title: input
	 * @Description: TODO(TO主页)
	 * @param map
	 * @param request
	 * @return
	 * @author huguangjun
	 * @date 2015年9月24日 上午12:16:14
	 */
	@RequestMapping("/login")
	public String login(Map<String, Object> map, HttpServletRequest request) {
		// if (WebUtil.checkLogin(request)) {
		// return "home/index";
		// }
		// map.put("user", new User());
		return "home/login";
	}
	
	@RequestMapping("/index")
	public String home(Map<String, Object> map, HttpServletRequest request) {
		// if (WebUtil.checkLogin(request)) {
		// return "home/index";
		// }
		// map.put("user", new User());
		return "home/index";
	}
	
	

	@RequestMapping("/insert")
	public String insert(User user) {
		// userService.saveAndUpdate(user);
		return "home/index";
	}

	/**
	 * 校验用户和密码是否正确
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkLogin")
	public boolean checkLogin(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "userName", required = true) String passWord) {
		/*
		 * User user = userService.getByNameAndPassword(userName, passWord);
		 * return user != null;
		 */
		return true;
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
		/*
		 * User loginUser = userService.getByNameAndPassword(user.getUserName(),
		 * user.getPassWord()); // 用户名和密码不正确的时候 if (loginUser == null) {
		 * request.setAttribute("message", "用户名或者密码错误"); return
		 * "redirect:/user/login"; // return "forward:/user/login"; } //
		 * 将用户信息放入session中F WebUtil.login(request, response, loginUser,
		 * "1".equals(state) ? true : false);
		 */
		return "home/login";
	}

	/**
	 * excel导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "exprotExcel")
	public void exprotExcel(HttpServletRequest request,
			HttpServletResponse response) {
		// WritableWorkbook wbook = Workbook.createWorkbook(os);
		// //jxl包中的，可以创建excel

		// 1:设置响应头
		XLSTransformer transformer = new XLSTransformer();
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/x-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;fileName=32.xls");
			// 获取项目虚拟路径
			Map<String, List> beanParams = new HashMap<String, List>();
			List<User> list = new ArrayList<User>();
			list.add(new User(12L, "guangjun", "1234"));
			list.add(new User(13L, "huihhui", "1234"));
			beanParams.put("list", list);
			String contextPath = request.getSession(true).getServletContext()
					.getRealPath("/");
			InputStream is = new BufferedInputStream(new FileInputStream(
					contextPath + "temp/user.xls"));
			Workbook transformXLS = transformer.transformXLS(is, beanParams);
			transformXLS.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
