package com.kaloo.common.scanner.support;

import java.lang.annotation.Annotation;

/**
 * 用于获取注解类的模板类
 */
public abstract class AnnotationScannerTemplate extends ScannerTemplate {

	protected final Class<? extends Annotation> annotationClass;

	protected AnnotationScannerTemplate(String packageName, Class<? extends Annotation> annotationClass) {
		super(packageName);
		this.annotationClass = annotationClass;
	}
}
