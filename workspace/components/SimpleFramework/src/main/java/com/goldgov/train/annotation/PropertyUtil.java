package com.goldgov.train.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 工具类 常量类
 * 
 * @author IF阳光请疯狂
 *
 */
public class PropertyUtil {

	/**
	 * 比较日期
	 * 
	 * @param biginDate
	 * @param endDate
	 * @param hour
	 * @return
	 */
	public static boolean calcDateFrom(Date biginDate, Date endDate, int hour) {
		return (endDate.getTime() - biginDate.getTime()) - hour * 60 * 60 * 1000 > 0;
	}

	/**
	 * 检验传入对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean objectNotEmpty(Object obj) {
		boolean result = true;
		if (obj == null) {
			return false;
		}
		if (obj instanceof String && "".equals(((String) obj).trim())) {
			result = false;
		}
		if (obj instanceof String[] && 0 == ((String[]) (String[]) obj).length) {
			result = false;
		}
		if (obj instanceof Integer[] && 0 == ((Integer[]) (Integer[]) obj).length) {
			result = false;
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			result = false;
		}
		if (obj instanceof List) {
			if (((List) obj).size() == 0 || ((List) obj).isEmpty()) {
				result = false;
			}
		}
		if (obj instanceof Set) {
			if (((Set) obj).isEmpty() || ((Set) obj).size() == 0) {
				result = false;
			}
		}
		return result;
	}
}
