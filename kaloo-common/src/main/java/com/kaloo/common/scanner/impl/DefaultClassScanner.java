package com.kaloo.common.scanner.impl;

import java.lang.annotation.Annotation;
import java.util.List;

import com.kaloo.common.scanner.ClassScanner;
import com.kaloo.common.scanner.support.AnnotationScannerTemplate;
import com.kaloo.common.scanner.support.ScannerTemplate;
import com.kaloo.common.scanner.support.SupperClassScannerTemplate;
import com.kaloo.common.utils.ClassUtils;

/**
 * 默认类扫描器
 */
public class DefaultClassScanner implements ClassScanner {

	private String packageName;

	private String annotationClass;

	private String superClass;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAnnotationClass() {
		return annotationClass;
	}

	public void setAnnotationClass(String annotationClass) {
		this.annotationClass = annotationClass;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<Class<?>> getClassList() {
		return getClassList(this.packageName);
	}

	@SuppressWarnings("unchecked")
	public List<Class<?>> getClassListByAnnotation() {
		Class<?> clazz = ClassUtils.loadClass(this.annotationClass);
		if (clazz.isAssignableFrom(Annotation.class)) {
			return getClassListByAnnotation(this.packageName, (Class<? extends Annotation>) clazz);
		} else {
			throw new IllegalArgumentException("annotationClass is invalid, it's not an annotation!");
		}

	}

	public List<Class<?>> getClassListBySuper() {
		Class<?> clazz = ClassUtils.loadClass(this.superClass);
		return getClassListBySuper(this.packageName, clazz);
	}

	@Override
	public List<Class<?>> getClassList(String packageName) {
		return new ScannerTemplate(packageName) {
			@Override
			public boolean checkAddClass(Class<?> cls) {
				String className = cls.getName();
				String pkgName = className.substring(0, className.lastIndexOf("."));
				return pkgName.startsWith(packageName);
			}
		}.getClassList();
	}

	@Override
	public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
		return new AnnotationScannerTemplate(packageName, annotationClass) {
			@Override
			public boolean checkAddClass(Class<?> cls) {
				return cls.isAnnotationPresent(annotationClass);
			}
		}.getClassList();
	}

	@Override
	public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
		return new SupperClassScannerTemplate(packageName, superClass) {
			@Override
			public boolean checkAddClass(Class<?> cls) {
				return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
			}
		}.getClassList();
	}
}
