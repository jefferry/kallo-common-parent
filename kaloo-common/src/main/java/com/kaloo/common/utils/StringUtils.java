package com.kaloo.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)");

	private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

	private StringUtils() {
	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0)
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean isEquals(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		return s1.equals(s2);
	}

	public static boolean isNumber(String str) {
		if (str == null || str.length() == 0)
			return false;
		return NUMBER_PATTERN.matcher(str).matches();
	}

	public static int parseInteger(String str) {
		if (!isNumber(str))
			return 0;
		return Integer.parseInt(str, 10);
	}

	public static long parseLong(String str) {
		if (!isNumber(str))
			return 0;
		return Long.parseLong(str, 10);
	}

	public static boolean isJavaIdentifier(String s) {
		if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
			return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Character.isJavaIdentifierPart(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isContains(String[] values, String value) {
		if (value != null && value.length() > 0 && values != null && values.length > 0) {
			for (String v : values) {
				if (value.equals(v)) {
					return true;
				}
			}
		}
		return false;
	}

	public static String translat(String src, String from, String to) {
		if (isEmpty(src))
			return src;
		StringBuilder sb = null;
		int ix;
		char c;
		for (int i = 0, len = src.length(); i < len; i++) {
			c = src.charAt(i);
			ix = from.indexOf(c);
			if (ix == -1) {
				if (sb != null)
					sb.append(c);
			} else {
				if (sb == null) {
					sb = new StringBuilder(len);
					sb.append(src, 0, i);
				}
				if (ix < to.length())
					sb.append(to.charAt(ix));
			}
		}
		return sb == null ? src : sb.toString();
	}

	public static String[] split(String str, char ch) {
		List<String> list = null;
		char c;
		int ix = 0, len = str.length();
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (c == ch) {
				if (list == null)
					list = new ArrayList<String>();
				list.add(str.substring(ix, i));
				ix = i + 1;
			}
		}
		if (ix > 0)
			list.add(str.substring(ix));
		return list == null ? EMPTY_STRING_ARRAY : (String[]) list.toArray(EMPTY_STRING_ARRAY);
	}

	public static String join(String[] array) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String s : array)
			sb.append(s);
		return sb.toString();
	}

	public static String join(String[] array, char split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String join(String[] array, String split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String join(Collection<String> coll, String split) {
		if (coll.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String s : coll) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(split);
			sb.append(s);
		}
		return sb.toString();
	}

	private static Map<String, String> parseKeyValuePair(String str, String itemSeparator) {
		String[] tmp = str.split(itemSeparator);
		Map<String, String> map = new HashMap<String, String>(tmp.length);
		for (int i = 0; i < tmp.length; i++) {
			Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
			if (matcher.matches() == false)
				continue;
			map.put(matcher.group(1), matcher.group(2));
		}
		return map;
	}

	public static String getQueryStringValue(String qs, String key) {
		Map<String, String> map = StringUtils.parseQueryString(qs);
		return map.get(key);
	}

	public static Map<String, String> parseQueryString(String qs) {
		if (qs == null || qs.length() == 0)
			return new HashMap<String, String>();
		return parseKeyValuePair(qs, "\\&");
	}

	public static String toQueryString(Map<String, String> ps) {
		StringBuilder buf = new StringBuilder();
		if (ps != null && ps.size() > 0) {
			for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (key != null && key.length() > 0 && value != null && value.length() > 0) {
					if (buf.length() > 0) {
						buf.append("&");
					}
					buf.append(key);
					buf.append("=");
					buf.append(value);
				}
			}
		}
		return buf.toString();
	}

	public static String camelToSplitName(String camelName, String split) {
		if (camelName == null || camelName.length() == 0) {
			return camelName;
		}
		StringBuilder buf = null;
		for (int i = 0; i < camelName.length(); i++) {
			char ch = camelName.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (buf == null) {
					buf = new StringBuilder();
					if (i > 0) {
						buf.append(camelName.substring(0, i));
					}
				}
				if (i > 0) {
					buf.append(split);
				}
				buf.append(Character.toLowerCase(ch));
			} else if (buf != null) {
				buf.append(ch);
			}
		}
		return buf == null ? camelName : buf.toString();
	}

}