package com.kaloo.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatUtils {
	/**
	 * 将数值转换为百分数
	 * @param num
	 * @return
	 */
	public static String conventToPercent(Double num) {
		return conventToPercent(num, 4, 2);
	}
	
	/**
	 * 将数值转换为百分数
	 * @param num
	 * @param fractionDigits 小数位数
	 * @return
	 */
	public static String conventToPercent(Double num, int fractionDigits) {
		return conventToPercent(num, 4, fractionDigits);
	}
	
	/**
	 * 将数值转换为百分数
	 * @param num
	 * @param IntegerDigits  整数位数
	 * @param fractionDigits 小数位数
	 * @return
	 */
	public static String conventToPercent(Double num, int IntegerDigits, int fractionDigits ) {
		if(num == null) {
			return "";
		}
		
		NumberFormat numFormat = NumberFormat.getPercentInstance();
		numFormat.setMaximumIntegerDigits(IntegerDigits);
		numFormat.setMaximumFractionDigits(fractionDigits);
		numFormat.setMinimumFractionDigits(fractionDigits);
		
		return numFormat.format(num);
	}
	
	public static String numberFormat(Object num) {
		return numberFormat(num, "###,###,###,###,###");
	}
	
	/**
	 * 格式化数字
	 * @param num
	 * @param format
	 * @return
	 */
	public static String numberFormat(Object num, String format) {
		if(num == null) {
			return "";
		}
		
		DecimalFormat dfInt = new DecimalFormat(format);
		
		return dfInt.format(num);
	}
	
}
