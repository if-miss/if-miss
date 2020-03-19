package org.gtiles.components.user.user.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gtiles.components.user.user.bean.UserBean;
import org.gtiles.components.user.user.bean.UserQuery;
import org.gtiles.components.user.user.service.IUserService;
import org.gtiles.core.web.OperatingType;
import org.gtiles.core.web.annotation.ModelQuery;
import org.gtiles.core.web.annotation.ModuleOperating;
import org.gtiles.core.web.annotation.ModuleResource;
import org.gtiles.core.web.json.ClientSuccessMessage;
import org.gtiles.core.web.token.WebToken;
import org.gtiles.core.web.token.WebToken.TokenHandleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户Controller
 * 
 * @author HuGuangJun
 */
@RequestMapping("/workbench/user")
@Controller("org.gtiles.components.user.user.web.UserController")
@ModuleResource(name = "用户管理", code = "user")
public class UserController {

	Log logger = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("org.gtiles.components.user.user.service.impl.UserServiceImpl")
	private IUserService userService;

	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 用户列表查询
	 * 
	 * @param query
	 *            查询条件对象
	 * @param request
	 * @param model
	 * @return
	 */
	@ClientSuccessMessage
	@RequestMapping("/findUserList")
	@ModuleOperating(code = "user.findList", name = "列表查询", type = OperatingType.FindList)
	public String findList(@ModelQuery("query") UserQuery query, HttpServletRequest request, Model model)
			throws Exception {
		List<UserBean> resultList = userService.findUserList(query);
		query.setResultList(resultList);
		return "";
	}

	/**
	 * 用户预新增
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/preAdd")
	@WebToken(handle = TokenHandleType.GENERATE, verifymethod = "/saveOrUpdateUser")
	public String preAdd(Model model, HttpServletRequest request) throws Exception {
		UserBean user = new UserBean();
		model.addAttribute(user);
		return "";
	}

	/**
	 * 新增或更新用户
	 * 
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 */
	@ClientSuccessMessage
	@RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
	@WebToken(handle = TokenHandleType.VERIFY)
	@ModuleOperating(code = "user.saveOrUpdate", name = "新增或更新", type = OperatingType.Save)
	public String saveOrUpdateInfo(UserBean user, Model model, HttpServletRequest request) throws Exception {

		String userId = user.getUserId();
		if (userId == null || "".equals(userId.trim())) {
			UserBean result = userService.addUser(user);
			model.addAttribute(result);
		} else {
			userService.updateUser(user);
		}
		return "";
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param model
	 * 
	 */
	@ClientSuccessMessage
	@RequestMapping("/deleteUserByIds")
	@ModuleOperating(code = "user.delete", name = "删除", type = OperatingType.Delete)
	public String deleteUserByIds(HttpServletRequest request, Model model) throws Exception {
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			int delCol = userService.deleteUser(ids);
			model.addAttribute(delCol);
		}
		return "";
	}

	/**
	 * 查询用户
	 * 
	 * @param model
	 * @param id
	 * @param request
	 */
	@ClientSuccessMessage
	@RequestMapping("/findUser")
	@WebToken(handle = TokenHandleType.GENERATE, verifymethod = "/saveOrUpdateUser")
	@ModuleOperating(code = "user.find", name = "查询", type = OperatingType.Find)
	public String findUser(Model model, String id, HttpServletRequest request) throws Exception {

		UserBean user = userService.findUserById(id);
		model.addAttribute(user);
		return "";
	}

}
