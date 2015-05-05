package com.app.jdy.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChineseMoneyUtils {

	private static String[] digit = { "", "拾", "佰", "仟", "万", "拾万", "佰万", "仟万", "亿", "拾亿", "佰亿", "仟亿", "万亿" };
	private static String[] num_digit = { "元", "万", "亿", "万亿" };
	private static final String FEN = "分";
	private static final String JIAO = "角";
	private static final String YUAN = "圆";
	private static final String ZHENG = "整";

	public static void main(String[] args) {

		System.out.println(getMoneyString(12312332));
	}

	/**
	 * 取得对应金额的阿拉伯数字+中文单位的形式(最大4位) 例如: 1234567 -> 123.45万
	 * 不过"123.45"放在字符数组0下标里,"万"放在放在字符数组1下标里
	 * 
	 * @param money
	 * @return
	 */
	public static String[] numWithDigitArray(double money) {
		return numWithDigitArray(money, 2, BigDecimal.ROUND_DOWN);
	}

	public static String[] numWithDigitArray(double money, int scale, int roundMode) {
		String strResult[] = new String[2];
		BigDecimal b = new BigDecimal(String.valueOf(money));
		int index = 0;
		while (b.compareTo(new BigDecimal("10000")) == 1) {
			b = b.divide(new BigDecimal("10000"));
			index++;
		}
		b = b.setScale(scale, roundMode);
		if (index < num_digit.length) {
			strResult[0] = b.toString();
			strResult[1] = num_digit[index];
		} else {
			System.err.println("格式化的金额超过[" + num_digit[num_digit.length - 1] + "],无法格式化");
		}

		return strResult;
	}

	/**
	 * 取得对应金额的阿拉伯数字+中文单位的形式(最大4位) 例如: 1234567 -> 123.45万
	 * 
	 * @param money
	 * @return
	 */
	public static String numWithDigit(double money) {
		String resultArray[] = numWithDigitArray(money);
		if (isNotEmpty(resultArray[0]) && isNotEmpty(resultArray[1])) {
			return resultArray[0] + resultArray[1];
		}
		return "";
	}

	/**
	 * 取得对应金额的阿拉伯数字+中文单位的形式(最大4位) 例如: 1234567 -> 123.45万
	 * 
	 * @param money
	 * @return
	 */
	public static String numWithDigit(BigDecimal money) {
		Double d = null;
		if (money == null) {
			return null;
		} else {
			d = money.doubleValue();
		}
		String resultArray[] = numWithDigitArray(d);
		if (isNotEmpty(resultArray[0]) && isNotEmpty(resultArray[1])) {
			return resultArray[0] + resultArray[1];
		}
		return "";
	}

	/**
	 * 取得数字对应的中文
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoneyString(double money) {
		// 将字符串转为为BigDecimal格式
		BigDecimal b = new BigDecimal(String.valueOf(money));
		// 设置精度为2，小数点后2位
		String strMoney = "" + b.setScale(2, BigDecimal.ROUND_UNNECESSARY);
		// 按小数点分为 整数 和 小数 两部分
		String[] amt = strMoney.split("\\.");
		// 调用函数获取 元 和 小数 部分的字符串
		strMoney = getYuan(amt[0]) + YUAN + getJIAOFEN(amt[1]);
		// 返回最终得到的字符串
		return strMoney;
	}

	/**
	 * 得到元的部分
	 * 
	 * @param s
	 * @return
	 */
	public static String getYuan(String s) {
		char[] c = s.toCharArray();
		StringBuffer chSb = new StringBuffer();
		int len = c.length;
		List list = new ArrayList();
		String d = "";
		for (int i = 0; i < c.length; i++) {
			// 如果有几个0挨在一起时, 只显示一个零即可
			if (i > 0 && c[i] == '0' && c[i] == c[i - 1]) {
				--len;
				continue;
			}
			// 得到数字对应的中文
			chSb.append(getChinese(c[i]));

			// 非零时, 显示是几佰, 还是几仟
			if (!getChinese(c[i]).equals("零")) {
				d = digit[--len];
				list.add(d);// 当数字中有万和十万时, 要去掉十万
				chSb.append(d);
			} else {
				--len; // 如果是0则不取位数
			}
		}
		String chStr = chSb.toString();
		// 如果同时包含有万和十万, 则将十万中的万去掉
		if (list.contains("万") && list.contains("拾万")) {
			chStr = chStr.replaceAll("拾万", "拾");
		}
		if (list.contains("万") && list.contains("佰万")) {
			chStr = chStr.replaceAll("佰万", "佰");
		}
		if (list.contains("万") && list.contains("仟万")) {
			chStr = chStr.replaceAll("仟万", "仟");
		}
		// 如果同时包含亿和十亿, 则将十亿中的亿字去掉
		if (list.contains("亿") && list.contains("拾亿")) {
			chStr = chStr.replaceAll("拾亿", "拾");
		}
		if (list.contains("亿") && list.contains("佰亿")) {
			chStr = chStr.replaceAll("佰亿", "佰");
		}
		if (list.contains("亿") && list.contains("仟亿")) {
			chStr = chStr.replaceAll("仟亿", "仟");
		}
		if (list.contains("亿") && list.contains("万亿")) {
			chStr = chStr.replaceAll("万亿", "万");
		}
		// 如果最后一位是 0, 则去掉
		if ((chSb.charAt(chSb.length() - 1)) == '零') {
			chStr = chStr.substring(0, chStr.length() - 1);
		}
		return chStr;
	}

	/**
	 * 分角转换为字符串 例: 25 2角5分 02 零2分 50 5角 00 整 0 整 2 整
	 * 
	 * @param FENJIAO
	 * @return
	 */
	private static String getJIAOFEN(String FENJIAO) {
		// 分角字符串转为为字符数组
		char[] ch = FENJIAO.toCharArray();
		// 按长度为0，为1，为2 来区分。
		if (ch.length == 0) {
			return ZHENG;
		} else if (ch.length == 1) {
			if (ch[0] == '0') {
				return ZHENG;
			} else {
				return getChinese(ch[0]) + JIAO;
			}
		} else {
			if (ch[0] == '0' && ch[1] == '0') {
				return ZHENG;
			} else if (ch[0] == '0' && ch[1] != '0') {
				return getChinese(ch[0]) + getChinese(ch[1]) + FEN;
			} else if (ch[0] != '0' && ch[1] == '0') {
				return getChinese(ch[0]) + JIAO;
			} else {
				return getChinese(ch[0]) + JIAO + getChinese(ch[1]) + FEN;
			}
		}
	}

	/**
	 * 取得数字对应的中文
	 * 
	 * @param i
	 * @return
	 */
	private static String getChinese(char i) {
		String ch = "";
		switch (i) {
		case '0':
			ch = "零";
			break;
		case '1':
			ch = "壹";
			break;
		case '2':
			ch = "贰";
			break;
		case '3':
			ch = "叁";
			break;
		case '4':
			ch = "肆";
			break;
		case '5':
			ch = "伍";
			break;
		case '6':
			ch = "陆";
			break;
		case '7':
			ch = "柒";
			break;
		case '8':
			ch = "捌";
			break;
		case '9':
			ch = "玖";
			break;
		}
		return ch;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {
		boolean flag = true;
		if (str != null && !str.equals("")) {
			if (str.toString().length() > 0) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	public static String isNumberNull(Object number, String notNullValue, String nullValue) {
		if (number == null) {
			return nullValue;
		} else {
			return notNullValue;
		}
	}

	public static String isNumberNull(BigDecimal number, String defValue) {
		if (number == null) {
			return defValue;
		} else {
			return number.toString();
		}
	}

	public static BigDecimal isNumberNull(BigDecimal number) {
		return isNumberNull(number, new BigDecimal(0));
	}

	public static BigDecimal isNumberNull(BigDecimal number, BigDecimal defValue) {
		if (number == null) {
			return defValue;
		} else {
			return number;
		}
	}
}
