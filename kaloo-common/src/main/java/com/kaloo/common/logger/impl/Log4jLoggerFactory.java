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

import org.apache.log4j.Level;

import com.kaloo.common.logger.Logger;
import com.kaloo.common.logger.LoggerFactory;

public class Log4jLoggerFactory implements LoggerFactory {
	
	public Logger getLogger(Class<?> clazz) {
		return new Log4jLogger(clazz);
	}
	
	public Logger getLogger(String name) {
		return new Log4jLogger(name);
	}
}

class Log4jLogger extends Logger { 
	private org.apache.log4j.Logger logger;
	
	private static final String callerFQCN = Log4jLogger.class.getName();
	
	Log4jLogger(Class<?> clazz) {
		logger = org.apache.log4j.Logger.getLogger(clazz);
	}
	
	Log4jLogger(String name) {
		logger = org.apache.log4j.Logger.getLogger(name);
	}
	
	public void debug(String format, Object... args){
		String msg = String.format(format, args);
		logger.log(callerFQCN, Level.DEBUG, msg, null);
	} 
	
	public void info(String format, Object... args){
		String msg = String.format(format, args);
		logger.log(callerFQCN, Level.INFO, msg, null);
	}
	
	public void warn(String format, Object... args){
		String msg = String.format(format, args);
		logger.log(callerFQCN, Level.WARN, msg, null);
	}
	
	public void error(String format, Object... args){
		String msg = String.format(format, args);
		logger.log(callerFQCN, Level.ERROR, msg, null);
	}
	
	
	public void info(String message) {
		logger.log(callerFQCN, Level.INFO, message, null);
	}
	
	public void info(String message, Throwable t) {
		logger.log(callerFQCN, Level.INFO, message, t);
	}
	
	public void debug(String message) {
		logger.log(callerFQCN, Level.DEBUG, message, null);
	}
	
	public void debug(String message, Throwable t) {
		logger.log(callerFQCN, Level.DEBUG, message, t);
	}
	
	public void warn(String message) {
		logger.log(callerFQCN, Level.WARN, message, null);
	}
	
	public void warn(String message, Throwable t) {
		logger.log(callerFQCN, Level.WARN, message, t);
	}
	
	public void error(String message) {
		logger.log(callerFQCN, Level.ERROR, message, null);
	}
	
	public void error(String message, Throwable t) {
		logger.log(callerFQCN, Level.ERROR, message, t);
	}
	
	public void fatal(String message) {
		logger.log(callerFQCN, Level.FATAL, message, null);
	}
	
	public void fatal(String message, Throwable t) {
		logger.log(callerFQCN, Level.FATAL, message, t);
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}
	
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Level.WARN);
	}
	
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Level.ERROR);
	}
	
	public boolean isFatalEnabled() {
		return logger.isEnabledFor(Level.FATAL);
	}
}