package org.gtiles.components.user.user.test;

import java.text.DateFormat;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.gtiles.components.user.user.bean.UserBean;
import org.gtiles.components.user.user.bean.UserQuery;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.gtiles.components.securityworkbench.bean.SwbAuthUser;
import org.gtiles.components.securityworkbench.login.SwbSessionUtils;
import org.gtiles.components.utils.mock.valuemock.ValueMockUtils;
import org.gtiles.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(locations = { "classpath*:gtiles-config/spring-config/core/**/*.xml",
		"classpath*:gtiles-config/spring-config/plugin/**/*.xml",
		"classpath*:gtiles-config/spring-config/project/**/*.xml" })
@WebAppConfiguration
public class UserTest extends GtilesBaseTest {

	private String preAddUrl = "/preAdd.json";
	private String addUrl = "/saveOrUpdateUser.json";
	private String updateUrl = "/saveOrUpdateUser.json";
	private String deleteUrl = "/deleteUserByIds.json";
	private String listUrl = "/findUserList.json";
	private String findUrl = "/findUser.json";
	//TODO 如果返回405异常，可能为用户没有权限，请调整userId为系统默认管理员：admin
//	private String userId = "admin";
	private String userId = ValueMockUtils.getValueAsString("1-10");
	private String userName = ValueMockUtils.getValueAsString("5-10");
	private String displayName = ValueMockUtils.getValueAsString("10-20");

	private SwbAuthUser authUser = new SwbAuthUser(userId, userName, displayName);
	private int initRowNum = 11;

	@Override
	public String getServerHeader() {
		return "http://localhost";
	}

	@Override
	public String getControllerUrl() {
		return "/workbench/user";
	}

	@Override
	public void boforePost() {
		// init session
		this.sessionDataMap.put(SwbSessionUtils.SWB_USER_KEY, authUser);
	}

	@Test
	public void execute() throws Exception {
		// TODO 测试过程执行
		// 1、执行数据新增
		addUserTest();
		// 2、执行数据查询
		findUserTest(null);
		// 3、执行数据更新
		updateUserTest();
		// 4、执行数据列表查询
		findUserListTest();
		// 5、执行数据删除
		deleteUserByIdsTest();
		

	}

	@Override
	public void after() throws Exception {
		
		sessionDataMap.clear();// 清空session
	}

	/**
	 * 新增用户单元测试
	 * 
	 * @throws Exception
	 */
	public void addUserTest() throws Exception {
		for (int i = 0; i < initRowNum; i++) {
			//预新增
			methodUrl = preAddUrl;
			runPostMethod();
			//新增
			methodUrl = addUrl;
			
			String userAccountMock = ValueMockUtils.getValueAsString("1-10");
			String userAccountMockText = String.valueOf(userAccountMock);
			requestDataMap.add("userAccount", userAccountMockText);//设置用户账号字段内容
			
			String userPasswordMock = ValueMockUtils.getValueAsString("1-10");
			String userPasswordMockText = String.valueOf(userPasswordMock);
			requestDataMap.add("userPassword", userPasswordMockText);//设置用户密码字段内容
			
			String userNameMock = ValueMockUtils.getValueAsString("1-10");
			String userNameMockText = String.valueOf(userNameMock);
			requestDataMap.add("userName", userNameMockText);//设置用户昵称字段内容
			
			Integer userSexMock = ValueMockUtils.getValueAsInteger(100);
			String userSexMockText = String.valueOf(userSexMock);
			requestDataMap.add("userSex", userSexMockText);//设置用户性别字段内容
			
			String userPhoneMock = ValueMockUtils.getValueAsString("1-10");
			String userPhoneMockText = String.valueOf(userPhoneMock);
			requestDataMap.add("userPhone", userPhoneMockText);//设置用户手机字段内容
			
			String userEmailMock = ValueMockUtils.getValueAsString("1-10");
			String userEmailMockText = String.valueOf(userEmailMock);
			requestDataMap.add("userEmail", userEmailMockText);//设置用户邮箱字段内容
			
			Date updateTimeMock = ValueMockUtils.getValueAsDate();
			String updateTimeMockText = DateUtils.formatDate(updateTimeMock);
			requestDataMap.add("updateTime", updateTimeMockText);//设置updateTime字段内容
			Result result = runPostMethod();
			Assert.assertEquals("method " + methodUrl + "execute faild!", HttpServletResponse.SC_OK,result.getStatusCode());
			UserBean resultBean = result.JsonResultToObject(UserBean.class);
			Assert.assertNotNull("UserId is null!", resultBean.getUserId());
			addPkId(resultBean.getUserId());// 添加主键到主键集合中
		}

	}

	/**
	 * 更新用户单元测试
	 * 
	 * @throws Exception
	 */
	public void updateUserTest() throws Exception {
		methodUrl = updateUrl;
		String updatePKId = getPkIdList().get(0);
		List<String> assertBeforeList = new ArrayList<String>();
		List<String> assertAfterList = new ArrayList<String>();
		//初始化请求参数
		String userAccountMock = ValueMockUtils.getValueAsString("1-10");
		String userAccountMockText = String.valueOf(userAccountMock);
		requestDataMap.add("userAccount", userAccountMockText);//设置用户账号字段内容
		assertBeforeList.add(userAccountMockText);
		String userPasswordMock = ValueMockUtils.getValueAsString("1-10");
		String userPasswordMockText = String.valueOf(userPasswordMock);
		requestDataMap.add("userPassword", userPasswordMockText);//设置用户密码字段内容
		assertBeforeList.add(userPasswordMockText);
		String userNameMock = ValueMockUtils.getValueAsString("1-10");
		String userNameMockText = String.valueOf(userNameMock);
		requestDataMap.add("userName", userNameMockText);//设置用户昵称字段内容
		assertBeforeList.add(userNameMockText);
		Integer userSexMock = ValueMockUtils.getValueAsInteger();
		String userSexMockText = String.valueOf(userSexMock);
		requestDataMap.add("userSex", userSexMockText);//设置用户性别字段内容
		assertBeforeList.add(userSexMockText);
		String userPhoneMock = ValueMockUtils.getValueAsString("1-10");
		String userPhoneMockText = String.valueOf(userPhoneMock);
		requestDataMap.add("userPhone", userPhoneMockText);//设置用户手机字段内容
		assertBeforeList.add(userPhoneMockText);
		String userEmailMock = ValueMockUtils.getValueAsString("1-10");
		String userEmailMockText = String.valueOf(userEmailMock);
		requestDataMap.add("userEmail", userEmailMockText);//设置用户邮箱字段内容
		assertBeforeList.add(userEmailMockText);
		Date updateTimeMock = ValueMockUtils.getValueAsDate();
		String updateTimeMockText = DateFormat.getDateInstance().format(updateTimeMock);
		requestDataMap.add("updateTime", updateTimeMockText);//设置updateTime字段内容
		assertBeforeList.add(updateTimeMockText);
		assertBeforeList.add(updatePKId);//设置用户ID字段内容
		requestDataMap.add("userId", updatePKId);
		
		Result result = runPostMethod();
		Assert.assertEquals("method " + methodUrl + "execute faild!", HttpServletResponse.SC_OK, result.getStatusCode());
		UserBean updateResult = findUserTest(updatePKId);
		//初始化断言内容
		assertAfterList.add(updateResult.getUserAccount());
		assertAfterList.add(updateResult.getUserPassword());
		assertAfterList.add(updateResult.getUserName());
		assertAfterList.add(String.valueOf(updateResult.getUserSex()));
		assertAfterList.add(updateResult.getUserPhone());
		assertAfterList.add(updateResult.getUserEmail());
		assertAfterList.add(DateFormat.getDateInstance().format(updateResult.getUpdateTime()));
		assertAfterList.add(updatePKId);
		Assert.assertArrayEquals("update failed!", assertBeforeList.toArray(new String[0]),assertAfterList.toArray(new String[0]));
	}

	/**
	 * 删除用户单元测试
	 * 
	 * @throws Exception
	 */
	public void deleteUserByIdsTest() throws Exception {

		methodUrl = deleteUrl;
		for (String id : getPkIdList()) {
			requestDataMap.add("ids", id);
		}
		Result result = runGetMethod();
		Assert.assertEquals("method " + methodUrl + "execute faild!", HttpServletResponse.SC_OK, result.getStatusCode());
		Integer operateCount = result.JsonResultToObject(Integer.class);
		Assert.assertEquals("delete record is not actual!", getPkIdList().size(), operateCount.intValue());
	}

	/**
	 * 查询用户列表单元测试
	 * 
	 * @throws Exception
	 */
	public void findUserListTest() throws Exception {
		methodUrl = listUrl;
		for (String id : getPkIdList()) {
			requestDataMap.add("searchCartIds", id);
		}
		Result result = runGetMethod();
		Assert.assertEquals("method " + methodUrl + "execute faild!", HttpServletResponse.SC_OK, result.getStatusCode());
		UserQuery query = result.JsonResultToObject(UserQuery.class);
		Assert.assertEquals("findList reocord is not actual!", getPkIdList().size(), query.getCount());
	}

	/**
	 * 查询用户单元测试
	 * 
	 * @param id
	 * @throws Exception
	 */
	public UserBean findUserTest(String id) throws Exception {
		methodUrl = findUrl;
		if (id == null || "".equals(id)) {
			id = getPkIdList().get(0);
		}
		requestDataMap.add("id", id);
		Result result = runGetMethod();
		Assert.assertEquals("method " + methodUrl + "execute faild!", HttpServletResponse.SC_OK, result.getStatusCode());
		UserBean resultBean = result.JsonResultToObject(UserBean.class);
		Assert.assertEquals("query result is not actual!", id,resultBean.getUserId());
		return resultBean;
	}

}
