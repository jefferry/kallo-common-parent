package com.kaloo.common.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.kaloo.common.logger.Logger;
import com.kaloo.common.utils.StringUtils;

/**
 * 过滤输入内容中的的特殊符号，防御跨站脚本攻击.<br/>
 * 对每个post请求的参数过滤一些关键字，替换成安全的，例如：< > ' " \ / # &
 * 方法是实现一个自定义的HttpServletRequestWrapper，然后在Filter里面调用它，替换掉getParameter函数.<br/>
 * 参考：http://www.cnblogs.com/Mainz/archive/2012/11/01/2749874.html
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger logger = Logger.getLogger(XssHttpServletRequestWrapper.class);

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	
	
	public String[] getParameterValues(String parameter) {
		if(logger.isDebugEnabled()) {
			logger.debug("=== getParameterValues");
		}
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		cleanXSS(values); // 传引用
		return values;
	}

	/**
	 * 对单一参数值进行过滤.
	 */
	public String getParameter(String parameter) {
		// log.info("=== getParameter");
		String value = super.getParameter(parameter);
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		if(logger.isDebugEnabled()) {
			logger.debug("=== getHeader");
		}
		String value = super.getHeader(name);
		return cleanXSS(value);
	}

	/**
	 * 对字符串数组进行过滤
	 * 
	 * @param values
	 */
	private void cleanXSS(String[] values) {
		if (values != null) {
			for (int num = 0; num < values.length; num++) {
				values[num] = this.cleanXSS(values[num]);
			}
		}
	}

	/**
	 * HTML过滤危险内容.
	 * 
	 * @param value
	 * @return
	 */
	private String cleanXSS(String value) {
		if(logger.isDebugEnabled()) {
			logger.debug("=== beforce cleanXSS: " + value);
		}
		if (StringUtils.isBlank(value)) {
			return null;
		}
		//不要对<,>,',"进行编码，避免长文本显示出问题
		// You'll need to remove the spaces from the html entities below
		/**
		 * 使用一个 Whitelist 类用来对 HTML 文档进行过滤.<br/>
		 * 参考：http://www.oschina.net/question/12_14127 .
		 */
		 value = Jsoup.clean(value, Whitelist.relaxed());
		 if(logger.isDebugEnabled()) {
				logger.debug("=== after cleanXSS: " + value);
		 }
	    return value; // 最宽松的一个过滤方法
	}

}