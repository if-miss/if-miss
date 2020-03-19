package com.mumu.manager.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumu.util.DateUtil;

/**
 * 
 * @ClassName FunctionHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HuGuangJun
 * @date 2015年12月9日 下午3:52:00
 *
 */
@Controller
@RequestMapping("/function")
public class FunctionHandler {

	@RequestMapping("/home")
	public String home(Map<String, Object> map, HttpServletRequest request) {
		return "home/home";
	}

	/**
	 * 
	 * @Title: input
	 * @Description: TODO(去html5页面)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2015年12月9日 下午3:51:52
	 */
	@RequestMapping("/html")
	public String html(Map<String, Object> map, HttpServletRequest request) {
		return "home/html";
	}

	/**
	 * 
	 * @Title: echarts
	 * @Description: TODO(数据图表文件)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2015年12月14日 下午3:17:51
	 */
	@RequestMapping("/echarts")
	public String echarts(Map<String, Object> map, HttpServletRequest request) {
		return "home/echarts";
	}

	/**
	 * 
	 * @Title: uploadify
	 * @Description: TODO(jquery上传组件)
	 * @param map
	 * @param request
	 * @return
	 * @author HuGuangJun
	 * @date 2015年12月16日 下午2:07:57
	 */
	@RequestMapping("/uploadify")
	@ResponseBody
	public String uploadify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			request.setCharacterEncoding("utf-8");
			// 1：创建一个工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2：存放目录
			String path = request.getSession().getServletContext()
					.getRealPath("/temp");
			factory.setRepository(new File(path));
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				if (!item.isFormField()) {
					String name = item.getName();
					String fileSuffix = name.substring(
							name.lastIndexOf(".") + 1, name.length());
					String oldName = name.replaceAll("." + fileSuffix, "");
					String fileName = DateUtil.getCurrDate("yyyyMMddhhmmss");
					String newName = fileName + "." + fileSuffix;
					OutputStream out = new FileOutputStream(new File(path,
							newName));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					/** 将上传处理后的数据返回 **/
					map.put("fileSuffix", fileSuffix);
					map.put("fileName", oldName);
					map.put("filePath", fileName);
					break;
				}
			}
			// response.setContentType("text/xml; charset=UTF-8");
			// response.setHeader("Cache-Control", "no-cache");
			// response.setHeader("Pragma", "no-cache");
			JSONObject jsonObject = JSONObject.fromObject(map);
			String msg = jsonObject.toString();
			return msg;
		} catch (Exception e) {
			System.out.println("出错了：" + e.getMessage());
			return null;
		}
	}

}
