package com.kaloo.common.datasource.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.annotation.Transactional;

import com.kaloo.common.datasource.DynamicDataSourceHolder;
import com.kaloo.common.datasource.annotation.DataSource;

public class DataSourceAspect {

	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = target.getClass().getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(Transactional.class)) {
				DataSource data = m.getAnnotation(DataSource.class);
				DynamicDataSourceHolder.set(data.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
