package com.kaloo.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class WaitPolicy implements RejectedExecutionHandler {

	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		BlockingQueue<Runnable> queue = executor.getQueue();
		try {
			queue.put(runnable);
		} catch (InterruptedException e) {
			throw new RejectedExecutionException("this is an interruptedAdded thread, queue task failed, ", e);
		}		
	}
	
}
