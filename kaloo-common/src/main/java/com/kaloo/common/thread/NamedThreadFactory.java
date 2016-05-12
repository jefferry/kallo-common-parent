package com.kaloo.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
	
	private static final AtomicInteger THREAD_POOL_SEQ = new AtomicInteger(1);

	private final AtomicInteger threadNumber = new AtomicInteger(1);

	private final String threadNamePrefix;
	
	private final ThreadGroup threadGroup;

	private final boolean isDaemon;

	public NamedThreadFactory() {
		this("pool-" + THREAD_POOL_SEQ.getAndIncrement(), false);
	}

	public NamedThreadFactory(String threadNamePrefix) {
		this(threadNamePrefix, false);
	}

	public NamedThreadFactory(String threadNamePrefix, boolean isDaemon) {
		this.threadNamePrefix = threadNamePrefix + "-" + "thread-";
		this.isDaemon = isDaemon;
		SecurityManager s = System.getSecurityManager();
		this.threadGroup = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
	}

	@Override
	public Thread newThread(Runnable runnable) {
		String threadName = threadNamePrefix + threadNumber.getAndIncrement();
		Thread thread = new Thread(threadGroup, runnable, threadName);
		thread.setDaemon(isDaemon);
		return thread;
	}

}
