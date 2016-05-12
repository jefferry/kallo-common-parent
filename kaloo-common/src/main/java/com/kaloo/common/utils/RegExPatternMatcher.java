package com.kaloo.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExPatternMatcher {

	public static boolean matches(String pattern, String source) {
		if (pattern == null) {
			throw new IllegalArgumentException("pattern argument cannot be null.");
		}
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		return m.matches();
	}

}
