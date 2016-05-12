/**
 * The MIT License (MIT)
 * Copyright (c) 2009-2015 HONG LEIMING
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kaloo.common.logger.impl;

import java.util.logging.Level;

import com.kaloo.common.logger.Logger;
import com.kaloo.common.logger.LoggerFactory;

public class JdkLoggerFactory implements LoggerFactory {
	
	public Logger getLogger(Class<?> clazz) {
		return new JdkLogger(clazz);
	}
	
	public Logger getLogger(String name) {
		return new JdkLogger(name);
	}
}
class JdkLogger extends Logger { 
	
	private java.util.logging.Logger logger;
	private String clazzName;
	
	JdkLogger(Class<?> clazz) {
		logger = java.util.logging.Logger.getLogger(clazz.getName());
		clazzName = clazz.getName();
	}
	
	JdkLogger(String name) {
		logger = java.util.logging.Logger.getLogger(name);
		clazzName = name;
	}
	
	public void debug(String message) {
		logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}
	
	public void debug(String message,  Throwable t) {
		logger.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}
	
	public void info(String message) {
		logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}
	
	public void info(String message, Throwable t) {
		logger.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}
	
	public void warn(String message) {
		logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}
	
	public void warn(String message, Throwable t) {
		logger.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}
	
	public void error(String message) {
		logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}
	
	public void error(String message, Throwable t) {
		logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}
	 
	public void fatal(String message) {
		logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}
 
	public void fatal(String message, Throwable t) {
		logger.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}
	
	public boolean isDebugEnabled() {
		return logger.isLoggable(Level.FINE);
	}
	
	public boolean isInfoEnabled() {
		return logger.isLoggable(Level.INFO);
	}
	
	public boolean isWarnEnabled() {
		return logger.isLoggable(Level.WARNING);
	}
	
	public boolean isErrorEnabled() {
		return logger.isLoggable(Level.SEVERE);
	}
	
	public boolean isFatalEnabled() {
		return logger.isLoggable(Level.SEVERE);
	}
}