package org.gtiles.components.support.excel.excelexport;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * Excel 模板下载
 * 
 * @author Tony
 */
public class ExcelTempletExport {

	/**
	 * 通过模板下载Excel文件
	 * <jx:forEach items="${list}" var="bean"> ${bean.courseName} </jx:forEach>
	 * 
	 * @param templateUrl
	 *            模板路径 /org/gtiles/components/gtclasses/workbench/
	 *            teacherfacecoursecount/list/templateTeacherCourse.xlsx
	 * @param tableBody
	 *            输出内容list
	 * @param fileName
	 *            文件名称
	 * @param response
	 *            相应流
	 * 
	 * @throws Exception
	 */
	public static void downloadExcel(String templatePath, List<?> tableBody, String fileName,
			HttpServletResponse response) throws Exception {
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", tableBody);
		downloadExcel(templatePath, beanParams, fileName, response);
	}

	/**
	 * 通过模板下载Excel文件
	 * <jx:forEach items="${list}" var="bean"> ${bean.courseName} </jx:forEach>
	 * 
	 * @param templateUrl
	 *            模板路径 /org/gtiles/components/gtclasses/workbench/
	 *            teacherfacecoursecount/list/templateTeacherCourse.xlsx
	 * @param map
	 *            输出内容 key-value
	 * @param fileName
	 *            文件名称
	 * @param response
	 *            相应流
	 * 
	 * @throws Exception
	 */
	public static void downloadExcel(String templatePath, Map<String, Object> map, String fileName,
			HttpServletResponse response) throws Exception {
		// 1: 创建XLSTransformer对象
		XLSTransformer transformer = new XLSTransformer();
		// 2:获取模板,读取jar文件并返回流
		InputStream is = ExcelTempletExport.class.getResourceAsStream(templatePath);
		// InputStream is =
		// Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(templatePath);
		try {
			// 4：设置响应头
			response.setHeader("Content-Disposition",
					"attachment;filename=\"" + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xlsx\"");
			// response.setHeader("Content-Type", "application/x-msexcel");
			// response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setContentType("application/vnd.ms-excel");
			// 5:通过流向客户端写数据
			transformer.transformXLS(is, map).write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

	/**
	 * 通过模板将Excel写入到另外一个Excel文件中
	 * 
	 * @param srcFilePath
	 *            模板路径
	 * @param tableBody
	 *            输出内容
	 * @param destFilePath
	 *            输出目标文件
	 * @throws Exception
	 */
	public static void downloadExcel(String srcFilePath, List<?> tableBody, String destFilePath) throws Exception {
		// 1: 创建XLSTransformer对象
		XLSTransformer transformer = new XLSTransformer();
		// 2:将数据转换成Map格式
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", tableBody);
		// 3:写出文件
		transformer.transformXLS(srcFilePath, beanParams, destFilePath);
	}

}
