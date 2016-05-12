package com.kaloo.common.datasource;

public class DynamicDataSourceHolder {
	
	private static final ThreadLocal<String> dsHolder = new ThreadLocal<String>();
	
	public static void set(String dsName) {
		dsHolder.set(dsName);
	}
	
	public static String get() {
		return dsHolder.get();
	}
	
	public static void remove() {
		dsHolder.remove();
	}
	
}
