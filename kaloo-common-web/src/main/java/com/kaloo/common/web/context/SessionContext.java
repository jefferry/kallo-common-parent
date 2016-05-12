package com.kaloo.common.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionContext {

	private static ThreadLocal<HttpServletRequest> threadLocalHttpRequest = new ThreadLocal<HttpServletRequest>();
	
	private static ThreadLocal<HttpServletResponse> threadLocalHttpResponse = new ThreadLocal<HttpServletResponse>();

	public static void setHttpRequest(HttpServletRequest request) {
		threadLocalHttpRequest.set(request);
	}

	public static HttpServletRequest getHttpRequest() {
		return threadLocalHttpRequest.get();
	}

	public static void setHttpResponse(HttpServletResponse response) {
		threadLocalHttpResponse.set(response);
	}

	public static HttpServletResponse getHttpResponse() {
		return threadLocalHttpResponse.get();
	}

	public static void remove() {
		threadLocalHttpRequest.remove();
		threadLocalHttpResponse.remove();
	}
}