package com.mumu.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName ObjUtil
 * @Description: TODO(对象处理工具)
 * @author huguangjun
 * @date 2015年9月24日 上午12:00:27
 *
 */
public final class ObjectUtil {

	public static boolean notNull(Object obj) {
		return obj != null;
	}

	public static boolean isNull(Object obj) {
		return !notNull(obj);
	}

	public static boolean notEmpty(String str) {
		return notNull(str) && (str.trim().length() > 0);
	}

	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	public static boolean notNullOrEmpty(String str) {
		return notNull(str) && notEmpty(str);
	}

	public static boolean isNullOrEmpty(String str) {
		return !notNullOrEmpty(str);
	}

	public static String defaultString(Object o, String defaultString) {
		if (o == null)
			return defaultString;
		else
			return o.toString();
	}

	/**
	 * 将Null转换为""
	 * 
	 * @param str
	 * @return
	 */
	public static String handleStringNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * 生成随机数
	 * 
	 * @author: wubingji
	 * @param len
	 *            长度
	 * @return 随机数
	 */
	public static String genRandomNum(int len) {
		// 0开始，10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0;
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

}
