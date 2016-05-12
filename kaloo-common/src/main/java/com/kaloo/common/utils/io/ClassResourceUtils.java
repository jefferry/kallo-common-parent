package com.kaloo.common.utils.io;

import java.io.InputStream;

import com.kaloo.common.logger.Logger;

public class ClassResourceUtils {

	private static final Logger log = Logger.getLogger(ClassResourceUtils.class);

	private static final ClassLoaderAccessor THREAD_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
		@Override
		protected ClassLoader doGetClassLoader() throws Throwable {
			return Thread.currentThread().getContextClassLoader();
		}
	};

	private static final ClassLoaderAccessor CLASS_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
		@Override
		protected ClassLoader doGetClassLoader() throws Throwable {
			return ClassResourceUtils.class.getClassLoader();
		}
	};

	private static final ClassLoaderAccessor SYSTEM_CL_ACCESSOR = new ExceptionIgnoringAccessor() {
		@Override
		protected ClassLoader doGetClassLoader() throws Throwable {
			return ClassLoader.getSystemClassLoader();
		}
	};

	public static InputStream getResourceAsStream(String name) {

		InputStream is = THREAD_CL_ACCESSOR.getResourceStream(name);

		if (is == null) {
			if (log.isDebugEnabled()) {
				log.warn("Resource [" + name + "] was not found via the thread context ClassLoader.  Trying the "
						+ "current ClassLoader...");
			}
			is = CLASS_CL_ACCESSOR.getResourceStream(name);
		}

		if (is == null) {
			if (log.isDebugEnabled()) {
				log.warn("Resource [" + name + "] was not found via the current class loader.  Trying the "
						+ "system/application ClassLoader...");
			}
			is = SYSTEM_CL_ACCESSOR.getResourceStream(name);
		}

		if (is == null && log.isDebugEnabled()) {
			log.warn("Resource [" + name + "] was not found via the thread context, current, or "
					+ "system/application ClassLoaders.  All heuristics have been exhausted.  Returning null.");
		}

		return is;
	}

	@SuppressWarnings("rawtypes")
	private static interface ClassLoaderAccessor {
		Class loadClass(String fqcn);

		InputStream getResourceStream(String name);
	}

	@SuppressWarnings("rawtypes")
	private static abstract class ExceptionIgnoringAccessor implements ClassLoaderAccessor {

		public Class loadClass(String fqcn) {
			Class clazz = null;
			ClassLoader cl = getClassLoader();
			if (cl != null) {
				try {
					clazz = cl.loadClass(fqcn);
				} catch (ClassNotFoundException e) {
					if (log.isDebugEnabled()) {
						log.debug("Unable to load clazz named [" + fqcn + "] from class loader [" + cl + "]");
					}
				}
			}
			return clazz;
		}

		public InputStream getResourceStream(String name) {
			InputStream is = null;
			ClassLoader cl = getClassLoader();
			if (cl != null) {
				is = cl.getResourceAsStream(name);
			}
			return is;
		}

		protected final ClassLoader getClassLoader() {
			try {
				return doGetClassLoader();
			} catch (Throwable t) {
				if (log.isDebugEnabled()) {
					log.debug("Unable to acquire ClassLoader.", t);
				}
			}
			return null;
		}

		protected abstract ClassLoader doGetClassLoader() throws Throwable;
	}
}