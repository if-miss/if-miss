package org.gtiles.solutions.styling;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.gtiles.core.module.ModuleContext;
import org.gtiles.core.web.GTilesContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
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

@ContextConfiguration(locations = { "classpath*:gtiles-config/spring-config/core/**/*.xml",
		"classpath*:gtiles-config/spring-config/plugin/**/*.xml",
		"classpath*:gtiles-config/spring-config/project/**/*.xml" })
@WebAppConfiguration
public class ComponentPluginTest extends AbstractJUnit4SpringContextTests {

	private String SERVER_HEADER = "http://localhost";
	private String CONTROLLER_URL = "you Controller path";

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	private String methodUrl;
	private MultiValueMap<String, String> requestDataMap = new LinkedMultiValueMap<String, String>();
	private Map<String, Object> sessionDataMap = new HashMap<String, Object>();// session parameter
	private String tempNum = String.valueOf(System.currentTimeMillis()).substring(8, 12);

	public class Result {
		private String resultStr;

		public Result(String resultStr) {
			this.resultStr = resultStr;
		}

		public String getResuleStr() {
			return resultStr;
		}

		public <T> T JsonResultToObject(Class<T> valueType) throws Exception {
			JsonNode resoultTree = new ObjectMapper().readTree(resultStr);
			return new ObjectMapper().treeToValue(resoultTree.findValue("data"), valueType);
		}

	}

	@Before
	public void before() throws Exception {
		ModuleContext moduleContext = GTilesContext.getModuleContext();
		moduleContext.initContext();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private Result runTestGet() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(SERVER_HEADER + CONTROLLER_URL + methodUrl);
		if (!requestDataMap.isEmpty()) {
			request.params(requestDataMap);
		}
		return new Result(this.runTest(request));
	}

	private Result runTestPost() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(SERVER_HEADER + CONTROLLER_URL + methodUrl);
		if (!requestDataMap.isEmpty()) {
			Map<String, Object> compositeMap = new HashMap<String, Object>();
			for (String key : requestDataMap.keySet()) {
				if (requestDataMap.get(key).size() < 2) {
					compositeMap.put(key, requestDataMap.get(key).get(0));
				} else {
					compositeMap.put(key, requestDataMap.get(key));
				}
			}
			request.content(new ObjectMapper().writeValueAsString(compositeMap))
					.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
							MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")));
		}
		return new Result(this.runTest(request));
	}

	private String runTest(MockHttpServletRequestBuilder request) throws Exception {
		if (!sessionDataMap.isEmpty()) {
			request.sessionAttrs(sessionDataMap);
		}
		MvcResult rs = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
		int status = rs.getResponse().getStatus();
		final String str = rs.getResponse().getContentAsString();
		System.out.println("return code " + status + ":" + str);
		requestDataMap.clear();
		sessionDataMap.clear();
		return str;
	}

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() {
		Assert.assertTrue(true);
	}
}
