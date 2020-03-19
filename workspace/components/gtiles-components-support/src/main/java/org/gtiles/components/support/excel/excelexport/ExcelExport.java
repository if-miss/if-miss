package org.gtiles.components.support.excel.excelexport;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.gtiles.components.support.js2e.StringUtils;

/**
 * excel下载
 * 
 * @author Tony
 */
public class ExcelExport {

	/**
	 * 下载excel文件,内容使用MAP存放 。 支持多个sheet。两个map的长度必须一致,map的key为索引(index)
	 * 
	 * @param response
	 * @param headName
	 * @param tableHeadMap
	 *            每个sheet对应的表头为具体的value值
	 * @param tableBodyMap
	 *            每个sheet对应的内容为具体的value值
	 */
	public static void downloadExcelMap(HttpServletResponse response, String headName,
			Map<Integer, List<String>> tableHeadMap, Map<Integer, List<Map<Object, Object>>> tableBodyMap)
			throws Exception {
		headName = StringUtils.replaceAllSpecial(headName);
		// 1:创建一个workbook
		Workbook workbook = new HSSFWorkbook();

		// 创建样式
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderTop((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);

		// 设置合计样式
		CellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style1.setBorderTop((short) 1);
		style1.setBorderBottom((short) 1);
		style1.setBorderLeft((short) 1);
		style1.setBorderRight((short) 1);

		int sheetLength = tableHeadMap.size();
		if (sheetLength != tableBodyMap.size()) {
			throw new Exception("tableHeadMap与tableBodyMap对应的长度不一致");
		}
		for (int i = 0; i < sheetLength; i++) {
			Sheet sheet = workbook.createSheet(headName + (i + 1));
			List<String> tableHead = tableHeadMap.get(i);
			List<Map<Object, Object>> tableBody = tableBodyMap.get(i);
			// 2：合并单元格，表头。并设置值
			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, tableHead.size() - 1);
			sheet.addMergedRegion(cra);
			Row row = sheet.createRow(0);
			Cell tableName = row.createCell(0);
			tableName.setCellStyle(style);
			tableName.setCellValue(headName);

			// 3：设置表head
			Row row1 = sheet.createRow(1);
			for (int m = 0; m < tableHead.size(); m++) {
				Cell createCell = row1.createCell(m);
				createCell.setCellValue(tableHead.get(m));
				createCell.setCellStyle(style);
			}
			// 4：表格内容
			for (int m = 0; m < tableBody.size(); m++) {
				Row rows = sheet.createRow(m + 2);
				int j = 0;
				for (Map.Entry<Object, Object> entry : tableBody.get(m).entrySet()) {
					Cell createCell = rows.createCell(j);
					if (entry.getValue() != null && !"".equals(entry.getValue())) {
						createCell.setCellValue(entry.getValue().toString());
					} else {
						createCell.setCellValue("");
					}
					createCell.setCellStyle(style1);
					j++;
				}
			}
		}

		// 5：设置头
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(headName.getBytes("GB2312"), "ISO8859-1") + ".xls");
		// 6：设置头类型
		response.setContentType("application/vnd.ms-excel");
		// 7：写出
		OutputStream toClient = response.getOutputStream();
		workbook.write(toClient);
		toClient.flush();
		toClient.close();

	}

	/**
	 * 下载excel文件,内容使用MAP存放
	 * 
	 * @param response
	 * @param headName
	 * @param tableHead
	 * @param tableBody
	 * @throws IOException
	 */
	public static void downloadExcelMap(HttpServletResponse response, String headName, List<String> tableHead,
			List<Map<Object, Object>> tableBody) throws IOException {
		headName = StringUtils.replaceAllSpecial(headName);
		// 1:创建一个workbook
		Workbook workbook = new HSSFWorkbook();

		// 创建样式
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style.setBorderTop((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);

		// 设置合计样式
		CellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style1.setBorderTop((short) 1);
		style1.setBorderBottom((short) 1);
		style1.setBorderLeft((short) 1);
		style1.setBorderRight((short) 1);

		Sheet sheet = workbook.createSheet(headName);
		// 2：合并单元格，表头。并设置值
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, tableHead.size() - 1);
		sheet.addMergedRegion(cra);
		Row row = sheet.createRow(0);
		Cell tableName = row.createCell(0);
		tableName.setCellStyle(style);
		tableName.setCellValue(headName);

		// 3：设置表head
		Row row1 = sheet.createRow(1);
		for (int i = 0; i < tableHead.size(); i++) {
			Cell createCell = row1.createCell(i);
			createCell.setCellValue(tableHead.get(i));
			createCell.setCellStyle(style);
		}
		// 4：表格内容
		for (int i = 0; i < tableBody.size(); i++) {
			Row rows = sheet.createRow(i + 2);
			int j = 0;
			for (Map.Entry<Object, Object> entry : tableBody.get(i).entrySet()) {
				Cell createCell = rows.createCell(j);
				if (entry.getValue() != null && !"".equals(entry.getValue())) {
					createCell.setCellValue(entry.getValue().toString());
				} else {
					createCell.setCellValue("");
				}
				createCell.setCellStyle(style1);
				j++;
			}
		}
		// 5：设置头
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(headName.getBytes("GB2312"), "ISO8859-1") + ".xls");
		// 6：设置头类型
		response.setContentType("application/vnd.ms-excel");
		// 7：写出
		OutputStream toClient = response.getOutputStream();
		workbook.write(toClient);
		toClient.flush();
		toClient.close();

	}

	/**
	 * 下载excel。内容使用对象存储
	 * 
	 * @param response
	 * @param headName
	 * @param tableHead
	 * @param tableBody
	 * @throws Exception
	 */
	public static void downloadExcelObj(HttpServletResponse response, String headName, List<String> tableHead,
			List<Object> tableBody) throws Exception {
		// 将对象转换成map
		List<Map<Object, Object>> mapBody = new ArrayList<Map<Object, Object>>();
		for (int i = 0; i < tableBody.size(); i++) {
			Map<Object, Object> objectMap = new LinkedHashMap<Object, Object>();
			Field[] fields = tableBody.get(i).getClass().getDeclaredFields();
			for (Field v : fields) {
				v.setAccessible(true);
				Object va = v.get(tableBody.get(i));
				if (va == null) {
					va = "";
				}
				objectMap.put(v.getName(), va);
			}
			mapBody.add(objectMap);
		}
		downloadExcelMap(response, headName, tableHead, mapBody);
	}
}
