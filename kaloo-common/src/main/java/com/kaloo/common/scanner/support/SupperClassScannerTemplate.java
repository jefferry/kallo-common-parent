package com.kaloo.common.scanner.support;

/**
 * 用于获取子类的模板类
 */
public abstract class SupperClassScannerTemplate extends ScannerTemplate {

	protected final Class<?> superClass;

	protected SupperClassScannerTemplate(String packageName, Class<?> superClass) {
		super(packageName);
		this.superClass = superClass;
	}
}
