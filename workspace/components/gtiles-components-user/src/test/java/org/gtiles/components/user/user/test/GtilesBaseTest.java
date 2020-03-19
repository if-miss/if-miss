package org.gtiles.components.user.user.test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gtiles.core.Keys;
import org.gtiles.core.module.ModuleContext;
import org.gtiles.core.web.GTilesContext;
import org.gtiles.core.web.json.TokenMessage;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Gtiles测试类基类
 * @author SONGHQ
 */
public abstract class GtilesBaseTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	protected WebApplicationContext context;
	protected MockMvc mockMvc;
	protected String methodUrl;
	protected String token;
	protected MockHttpSession session = new MockHttpSession();
	protected MultiValueMap<String, String> requestDataMap = new LinkedMultiValueMap<String, String>();
	protected Map<String, Object> sessionDataMap = new HashMap<String, Object>();
	private List<String> pkIdList;

	public abstract String getServerHeader();

	public abstract String getControllerUrl();

	public abstract void boforePost();// before post exceute

	public class Result {
		private String responseText;
		private int statusCode;

		
		public Result(String responseText, int statusCode) {
			this.responseText = responseText;
			this.statusCode = statusCode;
		}
		public Result() {}


		public <T> T JsonResultToObject(Class<T> valueType) throws Exception {
			JsonNode resoultTree = new ObjectMapper().readTree(responseText);
			return new ObjectMapper().treeToValue(resoultTree.findValue("data"), valueType);
		}
		public TokenMessage getTokenMessage() throws Exception{
			JsonNode resoultTree = new ObjectMapper().readTree(responseText);
			if(resoultTree.findValue("tokenmessage")!=null){
				return new ObjectMapper().treeToValue(resoultTree.findValue("tokenmessage"), TokenMessage.class);
			}else{
				return null;
			}
		}

		public String getResponseText() {
			return responseText;
		}

		public void setResponseText(String responseText) {
			this.responseText = responseText;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

	}

	@Before
	public void before() throws Exception {
		ModuleContext moduleContext = GTilesContext.getModuleContext();
		moduleContext.initContext();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		boforePost();
	}

	@After
	public abstract void after() throws Exception;

	/**
	 * 执行get方法
	 * 
	 * @return
	 * @throws Exception
	 */
	protected Result runGetMethod() throws Exception {
		String executeUrl = getServerHeader() + getControllerUrl() + methodUrl;
		executeUrl += "?"+Keys.DEFAULT_WEBTOKEN_NAME+"="+token;
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(executeUrl);
		if (!requestDataMap.isEmpty()) {
			request.params(requestDataMap);
		}
		return runTest(request);
	}
	/**
	 * 执行post方法（@requestBody形式）
	 * @return
	 * @throws Exception
	 */
	protected Result runPostMethodForRequestBody() throws Exception {
		String executeUrl = getServerHeader() + getControllerUrl() + methodUrl;
		executeUrl += "?" + Keys.DEFAULT_WEBTOKEN_NAME + "=" + token;
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(executeUrl);
		// @requestBody 接收参数形式
		Map<String, Object> compositeMap = new HashMap<String, Object>();
		if (!requestDataMap.isEmpty()) {
			for (String key : requestDataMap.keySet()) {
				if (requestDataMap.get(key).size() < 2) {
					compositeMap.put(key, requestDataMap.get(key).get(0));
				} else {
					compositeMap.put(key, requestDataMap.get(key));
				}
			}
		}
		request.content(new ObjectMapper().writeValueAsString(compositeMap))
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
						MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")));
		return runTest(request);
	}

	/**
	 * 执行post方法
	 * 
	 * @return
	 * @throws Exception
	 */
	protected Result runPostMethod() throws Exception {
		String executeUrl = getServerHeader() + getControllerUrl() + methodUrl;
		executeUrl += "?"+Keys.DEFAULT_WEBTOKEN_NAME+"="+token;
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(executeUrl);
		// 正常form提交接收参数形式
		if (!requestDataMap.isEmpty()) {
			request.params(requestDataMap);
		}
		request.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
				Charset.forName("utf8")));
		return runTest(request);
	}

	private Result runTest(MockHttpServletRequestBuilder request) throws Exception {
		if (!sessionDataMap.isEmpty()) {
			request.session(session);
			request.sessionAttrs(sessionDataMap);
		}
		MvcResult rs = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		int status = rs.getResponse().getStatus();
		final String responseText = rs.getResponse().getContentAsString();
		requestDataMap.clear();
		Result result = new Result(responseText, status);
		if(result.getTokenMessage()!=null){
			token = result.getTokenMessage().getToken();
		}
		return result;
	}

	public List<String> getPkIdList() {
		return pkIdList;
	}

	public void setPkIdList(List<String> pkIdList) {
		this.pkIdList = pkIdList;
	}

	public void addPkId(String pkId) {
		if (pkIdList == null) {
			pkIdList = new ArrayList<String>();
		}
		pkIdList.add(pkId);
	}
}
