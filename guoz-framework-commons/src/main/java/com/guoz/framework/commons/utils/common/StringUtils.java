package com.guoz.framework.commons.utils.common;



import com.guoz.framework.commons.Exception.AppException;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	//下划线字符
	public static final char UNDERLINE = '_';

	/**
	 * 连接字符串，空的跳过
	 * @param strings
	 * @return
	 */
	public static String concat(String... strings) {
		if (strings == null || strings.length == 0)
			return "";
		StringBuffer sb = new StringBuffer("");
		for (String s : strings) {
			if (s == null) {
				continue;
			}
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否为空或是空白字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTrimEmpty(Object strobj) {
		if (strobj instanceof String) {
			String str = (String) strobj;
			return str == null || str.equals("") || str.trim().isEmpty();
		}
		return strobj == null;
	}

	/**
	 * 检查字符串是否为空，为空抛出异常
	 * 
	 * @param str
	 * @param msg
	 * @return
	 */
	public static String checkNotEmpty(String str, String msg) {
		if (isTrimEmpty(str))
			throw new RuntimeException(msg == null ? "参数为空" : msg);
		return str;
	}

	/**
	 * 使用分隔串连接字符串数组，如果元素为空白字符将不连接
	 * 
	 * @param columns
	 * @param split
	 * @return
	 */
	public static String join(String[] items, String split) {
		if (items == null || items.length == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for (String s : items) {
			if (isTrimEmpty(s))
				continue;
			sb.append(s + split);
		}
		if (sb.indexOf(split) >= 0) {
			return sb.substring(0, sb.lastIndexOf(split));
		}
		return sb.toString();
	}

	/**
	 * 使用分隔串连接元素，如果元素为空或空白字符将不连接
	 * 
	 * @param
	 * @param split
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(Collection items, String split) {
		if (items == null || items.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer();
		for (Object item : items) {
			if (item == null)
				continue;
			if ((item instanceof String) && isTrimEmpty((String) item))
				continue;
			sb.append(item + split);
		}
		if (sb.indexOf(split) >= 0) {
			return sb.substring(0, sb.lastIndexOf(split));
		}
		return sb.toString();
	}

	/**
	 * 驼峰转下划线
	 * 
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线转驼峰
	 * 
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		return strToCamel(param, UNDERLINE+"");
	}

	/**
	 * 下划线转驼峰,指定分割符
	 * @param param
	 * @return
	 */
	public static String strToCamel(String param, String split) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile(split).matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 格式化字符串输出
	 * 例如：String.format("Hello %s!", "tom");获得 Hello tom!
	 * @param string
	 * @param objects
	 * @return
	 */
	public static String format(String string, Object... objects) {
		if (string == null)
			return "";
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null) {
					objects[i] = "";
				}
			}
		}
		return String.format(string, objects);
	}
	/**
	 * 检查字符是否为中文
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 判断字符串是否为空，空串或null字符串。
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * @param
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int letterLenth(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 对字符串进行URL转码
	 * 
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

	public static boolean isEmpty(String password) {
		return password == null || password.isEmpty();
	}
}
