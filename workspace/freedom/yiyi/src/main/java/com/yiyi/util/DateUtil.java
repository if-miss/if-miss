package com.yiyi.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @ClassName DateUtil
 * @Description: TODO(日期工具类)
 * @author huguangjun
 * @date 2015年9月23日 下午11:57:32
 *
 */
public final class DateUtil {
	/**
	 * getCurrDate方法参数日期格式编写参考。 yyyy-MM-dd hh:mm:ss yyyyMMddhhmmss
	 */
	public static final String YEAR04 = "yyyy";
	public static final String MONTH02 = "MM";
	public static final String DAY02 = "dd";
	public static final String DATE_07 = "yyyy-MM";
	public static final String DATE_10 = "yyyy-MM-dd";
	public static final String DATE_13 = "yyyy-MM-dd HH";
	public static final String DATE_16 = "yyyy-MM-dd HH:mm";
	public static final String DATE_19 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE06 = "yyyyMM";
	public static final String DATE08 = "yyyyMMdd";
	public static final String DATE10 = "yyyyMMddHH";
	public static final String DATE12 = "yyyyMMddHHmm";
	public static final String DATE14 = "yyyyMMddHHmmss";

	private DateUtil() {
	}

	/**
	 * 生成日期解析对象
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param pattern
	 *            转换格式
	 * @return DateFormat 日期解析对象
	 */
	public static DateFormat doDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/**
	 * 日期转换到字符串
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param paramDate
	 *            要转换的日期
	 * @param pattern
	 *            转换格式：例：yyyy-MM-dd
	 * @return String 日期字符串
	 */
	public static String dateToString(Date paramDate, String pattern) {
		return doDateFormat(pattern).format(paramDate);
	}

	/**
	 * 
	 * 日期格式转换
	 * 
	 * @author 胡光军
	 * @date 2014年11月27日
	 * @param paramDate
	 * @param pattern
	 * @return
	 */
	public static Date dateFormat(Date paramDate, String pattern) {
		String dateStr = doDateFormat(pattern).format(paramDate);
		return stringToDate(dateStr, pattern);
	}

	/**
	 * 字符串转换到日期
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            转换格式：例：yyyy-MM-dd
	 * @return Date 转换后的日期
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		try {
			return doDateFormat(pattern).parse(dateStr);
		} catch (Exception e) {
			System.out.println("字符串转换到日期出错！");
			throw new RuntimeException();
		}
	}

	/**
	 * 获取当前日期(字符串格式)
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param pattern
	 *            转换格式：例：yyyy-MM-dd
	 * @return String 日期字符串
	 */
	public static String getCurrDate(String pattern) {
		return dateToString(new Date(), pattern);
	}

	/**
	 * 获取当前日期(日期格式)
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param pattern
	 *            转换格式：例：yyyy-MM-dd
	 * @return Date 日期
	 */
	public static Date getCurrDateOfDate(String pattern) {
		return stringToDate(dateToString(new Date(), pattern), pattern);
	}

	/**
	 * 获取日期是星期几
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param paramDate
	 *            参数日期
	 * @param retFormat
	 *            返回格式：0、表示返回数字格式 1、表示返回中文格式
	 * @return String 星期几
	 */
	public static String getDayOfWeek(Date paramDate, int retFormat) {
		Calendar c = Calendar.getInstance();
		c.setTime(paramDate);
		int dayOfWeek = (c.get(Calendar.DAY_OF_WEEK) == 1) ? 7 : c
				.get(Calendar.DAY_OF_WEEK) - 1;
		String dayOfWeekStr = null;
		switch (dayOfWeek) {
		case 1:
			dayOfWeekStr = (0 == retFormat) ? "1" : "一";
			break;
		case 2:
			dayOfWeekStr = (0 == retFormat) ? "2" : "二";
			break;
		case 3:
			dayOfWeekStr = (0 == retFormat) ? "3" : "三";
			break;
		case 4:
			dayOfWeekStr = (0 == retFormat) ? "4" : "四";
			break;
		case 5:
			dayOfWeekStr = (0 == retFormat) ? "5" : "五";
			break;
		case 6:
			dayOfWeekStr = (0 == retFormat) ? "6" : "六";
			break;
		case 7:
			dayOfWeekStr = (0 == retFormat) ? "7" : "日";
			break;
		}
		return dayOfWeekStr;
	}

	/**
	 * 指定日期几天后或者几天前的日期
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param paramDate
	 *            指定日期
	 * @param days
	 *            天数
	 * @return Date 几天后或者几天前的日期
	 */
	public static Date addDate(Date paramDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paramDate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 指定日期几月后或者几月前的日期
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param paramDate
	 *            指定日期
	 * @param months
	 *            月数
	 * @return Date 几月后或者几月前的日期
	 */
	public static Date addDateOfMonth(Date paramDate, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paramDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 根据指定日期获取指定日期所在周的开始日期和结束日期(星期一、星期天)
	 * 
	 * @author 胡光军
	 * @date 2014-9-28
	 * @param paramDate
	 *            指定日期
	 * @return String[] 开始日期和结束日期数组
	 */
	public static String[] getWeekStartAndEndDate(Date paramDate) {
		String[] retAry = new String[2];

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paramDate);
		// 以周一为一周的开始
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		retAry[0] = dateToString(calendar.getTime(), "yyyy-MM-dd");
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		retAry[1] = dateToString(calendar.getTime(), "yyyy-MM-dd");

		return retAry;
	}

	/**
	 * 根据指定日期获取指定日期所在月的第一天和最后一天
	 * 
	 * @author majun
	 * @date 2014-9-28
	 * @param paramDate
	 *            指定日期
	 * @return String[] 第一天和最后一天数组
	 */
	public static String[] getMonthStartAndEndDate(Date paramDate) {
		String[] retAry = new String[2];

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paramDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retAry[0] = dateToString(calendar.getTime(), "yyyy-MM-dd");
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		retAry[1] = dateToString(calendar.getTime(), "yyyy-MM-dd");

		return retAry;
	}

	/**
	 * 获取指定两个日期相差的天数
	 * 
	 * @author majun
	 * @date 2014-9-28
	 * @param paramDate1
	 *            指定日期1
	 * @param paramDate2
	 *            指定日期2
	 * @return int 相差天数
	 */
	public static int getDiffDaysOfTwoDate(String paramDate1, String paramDate2) {
		Date date1 = stringToDate(paramDate1, "yyyy-MM-dd");
		Date date2 = stringToDate(paramDate2, "yyyy-MM-dd");

		Long diffTimes = date1.getTime() - date2.getTime();
		Long diffDays = diffTimes / (3600 * 1000 * 24);

		return Math.abs(diffDays.intValue());
	}

	/**
	 * 获取指定日期相差月份数
	 * 
	 * @author majun
	 * @date 2014-9-28
	 * @param paramDate1
	 *            指定日期1
	 * @param paramDate2
	 *            指定日期2
	 * @return int 相差月份数 注：日期所在月都算一月
	 */
	public static int getDiffMonthsOfTwoDate(String paramDate1,
			String paramDate2) {
		// 指定日期1的年份、月份
		int tempYear1 = Integer.parseInt(paramDate1.substring(0, 4));
		int tempMonth1 = Integer.parseInt(paramDate1.substring(5, 7));

		// 指定日期2的年份、月份
		int tempYear2 = Integer.parseInt(paramDate2.substring(0, 4));
		int tempMonth2 = Integer.parseInt(paramDate2.substring(5, 7));

		return Math.abs((tempYear1 * 12 + tempMonth1)
				- (tempYear2 * 12 + tempMonth2)) + 1;
	}

	/**
	 * 获取指定日期所在月有多少天
	 * 
	 * @author majun
	 * @date 2014-9-28
	 * @param paramDate
	 *            指定日期(yyyy-MM格式)
	 * @return int 指定日期所在月有多少天
	 */
	public static int getDaysOfMonths(String paramDate) {
		int days = 0;
		try {
			Date date = doDateFormat("yyyy-MM").parse(paramDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			System.out.println("字符串转换到日期出错");
			throw new RuntimeException();
		}
		return days;
	}

	/**
	 * 比较两个日期是否相等(精确到天)
	 * 
	 * @author caohuan
	 * @date 2014年12月2日
	 * @param dateOne
	 * @param dateOther
	 * @return
	 */
	public static boolean isEqualDates(Date dateOne, Date dateOther) {
		if (null == dateOne || null == dateOther) {
			return false;
		}
		Calendar calOne = Calendar.getInstance();
		Calendar calOther = Calendar.getInstance();

		calOne.setTime(dateOne);
		calOther.setTime(dateOther);

		int subYear = calOne.get(Calendar.YEAR) - calOther.get(Calendar.YEAR);
		int subMonth = calOne.get(Calendar.MONTH)
				- calOther.get(Calendar.MONTH);
		int subDay = calOne.get(Calendar.DAY_OF_MONTH)
				- calOther.get(Calendar.DAY_OF_MONTH);

		if (subYear == 0 && subMonth == 0 && subDay == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * 判断date1是否在date2之后
	 * 
	 * @author shibaorui
	 * @date 2014年12月4日
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return
	 */
	public static boolean isAfter(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		if (StringUtils.isEmpty(date1)) {
			return true;
		}
		if (StringUtils.isEmpty(date2)) {
			return false;
		}
		Date d1;
		try {
			d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);
			return d1.after(d2);
		} catch (ParseException e) {
			System.out.println("字符串转换到日期出错");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @Title: timeStamp2String
	 * @Description: TODO(时间戳转换为字符串)
	 * @param date
	 *            时间戳
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public static String timeStamp2String(Timestamp date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 
	 * @Title: addDateDay
	 * @Description: TODO(在某个日期上加上固定天数)
	 * @param date
	 *            传入的日期格式：yyyy-MM-dd
	 * @param day
	 *            需要添加的天数
	 * @return
	 * @throws ParseException
	 * @author 胡光军
	 * @date 2015年6月30日 下午6:11:18
	 */
	public static String addDateDay(String date, int day) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = df.parse(date);
		} catch (ParseException e) {
			System.out.println("字符串转日期出错");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return df.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: addDateDay
	 * @Description: TODO(当前时间上添加多少天)
	 * @param day
	 *            添加的天数
	 * @return
	 * @author 胡光军
	 * @date 2015年6月30日 下午6:15:22
	 */
	public static String addDateDay(int day) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return df.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: getNextDay
	 * @Description: TODO(某个日期的前一天)
	 * @param date
	 * @return yyyy-MM-dd
	 * @author 胡光军
	 * @date 2015年8月27日 上午10:07:09
	 */
	public static String getNextDay(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return df.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: isEqualDates
	 * @Description: TODO(判断date1是否大于等于date2)
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 * @author 胡光军
	 * @date 2015年8月14日 上午11:23:01
	 */
	public static boolean isEqualDates(String date1, String date2) {
		if (isEqualDates(stringToDate(date1, "yyyy-MM-dd"),
				stringToDate(date2, "yyyy-MM-dd"))
				|| isAfter(date1, date2)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(DateUtil.isEqualDates("2015-01-19",
		// "2015-01-20"));
		// System.out.println(DateUtil.getCurrDate("yyyyMMddhhmmss"));
		System.out.println("yyyy=" + DateUtil.getCurrDate(DateUtil.YEAR04));
		System.out.println("MM=" + getCurrDate(MONTH02));
		System.out.println("dd=" + getCurrDate(DAY02));
		System.out.println("yyyy-MM=" + getCurrDate(DATE_07));
		System.out.println("yyyy-MM-dd=" + getCurrDate(DATE_10));
		System.out.println("yyyy-MM-dd hh=" + getCurrDate(DATE_13));
		System.out.println("yyyy-MM-dd hh:mm=" + getCurrDate(DATE_16));
		System.out.println("yyyy-MM-dd hh:mm:ss=" + getCurrDate(DATE_19));
		System.out.println("yyyyMM=" + getCurrDate(DATE06));
		System.out.println("yyyyMMdd=" + getCurrDate(DATE08));
		System.out.println("yyyyMMddhh=" + getCurrDate(DATE10));
		System.out.println("yyyyMMddhhmm=" + getCurrDate(DATE12));
		System.out.println("yyyyMMddhhmmss=" + getCurrDate(DATE14));
		System.out.println();
		System.out.println(DateUtil.getNextDay(new Date()));

	}
}
