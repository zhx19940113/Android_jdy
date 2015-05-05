package com.app.jdy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * description :
 * 
 * @version 1.0
 * @author zhonghuixiong
 * @createtime : 2015-1-15 上午9:30:58
 * 
 *             修改历史: 修改人 修改时间 修改内容 --------------- -------------------
 *             ----------------------------------- zhonghuixiong 2015-1-15
 *             上午9:30:58
 * 
 */
public class StringUtils {
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private final static SimpleDateFormat dateFormatUserId = new SimpleDateFormat("yyMMddkkmmss");

	private static Pattern p = null;
	private static Matcher m = null;

	/**
	 * 
	 * @author zhonghuixiong
	 * @createtime 2015-1-17 上午11:50:08
	 * @Decription 判断字符串是否包含字符
	 * 
	 * @param str
	 * @param searchChars
	 * @return
	 */
	public static String containsAny(String str, String height, String mindle, String low) {
		if (str.contains(height)) {
			return height;
		} else if (str.contains(mindle)) {
			return mindle;
		} else {
			return low;
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate2(String sdate) {
		try {
			return dateFormater2.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Time(Date date) {
		String time = sqlDateFormat.format(date);
		return time;
	}

	/**
	 * 将日期转换为 年月日 格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		String datestring = null;
		if (date == null) {
			datestring = "";
		} else {
			datestring = dateFormater2.format(date);
		}
		return datestring;
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.format(cal.getTime());
		String paramDate = dateFormater2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.format(time);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.format(today);
			String timeDate = dateFormater2.format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 格式化soap传过来的date+time数据
	 * 
	 * @param soapDateTime
	 * @return
	 */
	public static String formatSoapDateTime(String soapDateTime) {
		String returnString = soapDateTime.substring(0, 19).replace("T", " ");
		return returnString;
	}

	/**
	 * 
	 * @param anyType
	 * @return
	 */
	public static String formatSoapNullString(String anyType) {
		String returnString = anyType.equals("anyType{}") ? "" : anyType;
		return returnString;
	}

	/**
	 * 检查邮箱输入格式是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmailInput(String email) {
		p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		m = p.matcher(email);
		return m.matches();
	}

	/**
	 * ###检查账号是否由 数字、字母、下划线 组合而成 是则返回true，否则返回false## 账号可为任意字符，包括中文
	 * 
	 * @param username
	 * @return
	 */
	public static boolean checkUsernameInput(String username) {
		// p = Pattern.compile("^\\w+$");
		// m = p.matcher(username);
		return 1 > 0;
	}

	/**
	 * 检查两次输入的密码是否一致
	 * 
	 * @param password
	 * @param password2
	 * @return
	 */
	public static boolean check2Password(String password, String password2) {
		return password.equals(password2);
	}

	/**
	 * 生成用户id，用时间生成
	 * 
	 * @return
	 */
	public static String date2UserId() {
		String time = dateFormatUserId.format(new Date());
		return time;
	}

	/**
	 * 格式化生成当前时间
	 * 
	 * @return
	 */
	public static Date genCurrentDate() {
		Date date = null;
		try {
			String now = dateFormater.format(new Date());
			// DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = dateFormater.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 格式化boolean字符串
	 * 
	 * @param booleanStr
	 * @return
	 */
	public static boolean formatBoolean(String booleanStr) {
		if ("true".equalsIgnoreCase(booleanStr)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否数字
	 * 
	 * @author ccw
	 * @createtime 2015-1-25 下午4:53:28
	 * @Decription
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 正则判断是否小数
	 * 
	 * @author ccw
	 * @createtime 2015-1-26 上午10:51:56
	 * @Decription
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		return Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?").matcher(str).matches();
	}

	/**
	 * 判断传过来是否为空或null
	 * 
	 * @author ccw
	 * @createtime 2015-1-26 上午10:53:54
	 * @Decription
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {

		if ("".equals(string) || "null".equals(string)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isNotEmpty(String string) {
		if (string != null && !"".equals(string) && !"null".equals(string)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否是空字符串 null和"" null返回result,否则返回字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String isEmpty(String s, String result) {
		if (s != null && !s.equals("") && !s.equals("null")) {
			return s;
		}
		return result;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static int isIntEmpty(Integer src, Integer defValue) {
		if (src == null || src == 0) {
			return defValue;
		}
		return src;
	}
}
