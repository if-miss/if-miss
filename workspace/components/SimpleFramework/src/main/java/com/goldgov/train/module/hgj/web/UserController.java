package com.goldgov.train.module.hgj.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.train.annotation.PropertyUtil;
import com.goldgov.train.mail.Mail;
import com.goldgov.train.mail.MailSender;
import com.goldgov.train.module.hgj.bean.UserBean;
import com.goldgov.train.module.hgj.service.IUserService;

/**
 * 用户controller
 * 
 * @author IF阳光请疯狂
 *
 */
@Controller
public class UserController {

	// 允许失败的次数
	public final static int ALLOW_LOGIN_FAIL_NUMBER = 3;

	// 登录失败后多长时间可以从新登录（单位小时）
	public final static int OVER_TIME = 1;

	public final static String SESSION_USER_KEY = "SESSION_USER_KEY";

	// 关键字
	public final static List<String> KEYWORD = new ArrayList<String>();
	static {
		KEYWORD.add("习近平");
		KEYWORD.add("毛泽东");
		/**
		 * 需要新增在加，或者做成关键字功能
		 */
	}

	@Autowired
	IUserService userService;

	@Autowired
	private MailSender mailSender;

	/**
	 * 去登录成功页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toLogin")
	public String toLogin(Model model) throws Exception {
		model.addAttribute("user", new UserBean());
		return "hgj/login.jsp";
	}

	/**
	 * 新增用户
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUser")
	public String addUser(UserBean user, Model model, HttpServletRequest request) throws Exception {
		boolean successFlag = true;
		if (PropertyUtil.objectNotEmpty(user.getLoginName()) && PropertyUtil.objectNotEmpty(user.getPassword())) {
			// 校验是否存再关键字
			for (String word : KEYWORD) {
				if (user.getLoginName().indexOf(word) != -1) {
					successFlag = false;
					break;
				}
			}
			// 不包含关键字则保存
			if (successFlag) {
				userService.addUser(user);
				// 发邮件
				Mail mail = new Mail();
				mail.setTo(user.getUserMail());
				mail.setSubject("快来学系统账号激活");
				mail.setContent("请尽完成账号激活,<a href='./autoLogin?userId=" + user.getUserId() + "' >自动激活</a>");
				mailSender.send(mail);
				model.addAttribute("userBean", user);
			}
		}
		if (successFlag) {
			return "hgj/regsuccess.jsp";
		} else {
			model.addAttribute("message", "注册失败，用户名不能出现关键安字");
			return "hgj/fail.jsp";
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userLongin")
	public String userLongin(UserBean user, Model model, HttpServletRequest request) throws Exception {
		/**
		 * 前端校验用户名密码必填，这里也应该校验用户密码必填，此处省略校验
		 * 
		 * 
		 * 
		 * 登陆的校验规则繁琐可以考虑做成可插拔， 时间限制没有做
		 */
		boolean successFlag = false;
		UserBean loginUser = userService.findUserByNameAndPassword(user.getLoginName(), user.getPassword());
		if (PropertyUtil.objectNotEmpty(loginUser)) {
			successFlag = true;
			// 超时登录
			if (PropertyUtil.objectNotEmpty(loginUser.getLoginFailTime())) {
				successFlag = PropertyUtil.calcDateFrom(loginUser.getLoginFailTime(), new Date(), OVER_TIME);
				if (!successFlag) {
					model.addAttribute("message", "请" + OVER_TIME + "小时后再登录");
					return "hgj/fail.jsp";
				}
			}
			// 登录成功则将登录信息放入session
			if (successFlag) {
				loginUser.setLoginSuccessTime(new Date());
				request.getSession().setAttribute(SESSION_USER_KEY, loginUser);
			}
		} else {
			// 登录失败超过系统设定次数则记录时间
			if (ALLOW_LOGIN_FAIL_NUMBER == user.getLoginNumber()) {
				UserBean failUser = new UserBean();
				failUser.setLoginName(user.getLoginName());
				failUser.setLoginFailTime(new Date());
				userService.updateUserByName(loginUser);
				model.addAttribute("message", "登陆失败" + ALLOW_LOGIN_FAIL_NUMBER + "次,请在" + OVER_TIME + "小时后再登录");
				return "hgj/fail.jsp";
			}
		}

		return "hgj/success.jsp";
	}

	/**
	 * 去登录成功页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toSuccess")
	public String toSuccess(Model model, HttpServletRequest request) throws Exception {
		// 判断该用户是否登录
		UserBean user = (UserBean) request.getSession().getAttribute(SESSION_USER_KEY);
		if (PropertyUtil.objectNotEmpty(user)) {
			model.addAttribute("loginUser", user);
			return "hgj/success.jsp";
		} else {
			model.addAttribute("message", "当前未登录系统请登录系统");
			return "hgj/fail.jsp";
		}
	}

	/**
	 * 自动登录，邮箱验证后直接登录
	 * 
	 * @param loginName
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/autoLogin")
	public String autoLogin(String userId, Model model, HttpServletRequest request) throws Exception {
		UserBean loginUser = userService.findUserById(userId);
		loginUser.setLoginSuccessTime(new Date());
		request.getSession().setAttribute(SESSION_USER_KEY, loginUser);
		model.addAttribute("loginUser", loginUser);
		return "hgj/success.jsp";
	}

}