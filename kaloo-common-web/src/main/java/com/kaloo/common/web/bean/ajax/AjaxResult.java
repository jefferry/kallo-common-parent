package com.kaloo.common.web.bean.ajax;

public class AjaxResult {
	/**
	 * 请求返回码
	 */
	private String code = ResultCode.SUCCESS.getCode();

	/**
	 * 请求返回信息
	 */
	private String message = ResultMsgConst.DEFAULT_SUCCESS_RETURNMSG;

	/**
	 * 请求返回结果
	 */
	private Object data = null;

	private AjaxResult() {
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 获取正确结果模板
	 *
	 * @param message
	 *            请求返回信息
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult buildSucc(String message, Object obj) {
		AjaxResult result = new AjaxResult();
		result.setMessage(message);
		result.setData(obj);
		return result;
	}

	/**
	 * 获取正确结果模板
	 *
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult buildSucc(Object obj) {
		AjaxResult result = new AjaxResult();
		result.setMessage(ResultMsgConst.DEFAULT_SUCCESS_RETURNMSG);
		result.setData(obj);
		return result;
	}
	
	/**
	 * 获取正确结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult buildSucc() {
		return buildSucc(ResultMsgConst.DEFAULT_SUCCESS_RETURNMSG, null);
	}

	/**
	 * 获取错误结果模板
	 *
	 * @param message
	 *            请求返回信息
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult buildFail(ResultCode resultCode, String message, Object obj) {
		AjaxResult result = new AjaxResult();
		result.setCode(resultCode.getCode());
		result.setMessage(message);
		result.setData(obj);
		return result;
	}

	/**
	 * 获取错误结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult buildFail(ResultCode resultCode) {
		return buildFail(resultCode, ResultMsgConst.DEFAULT_FAILED_RETURNMSG, null);
	}
	
	/**
	 * 获取结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult buildByResultCode(ResultCode resultCode) {
		return buildByResultCode(resultCode, null);
	}
	
	/**
	 * 获取结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult buildByResultCode(ResultCode resultCode, Object obj) {
		AjaxResult result = new AjaxResult();
		result.setCode(resultCode.getCode());
		result.setMessage(resultCode.getMsg());
		result.setData(obj);
		return result;
	}
	
	
	/**
	 * 获取结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult build(String code, String msg) {
		return build(code, msg, null);
	}
	
	/**
	 * 获取结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult build(String code, String msg, Object obj) {
		AjaxResult result = new AjaxResult();
		result.setCode(code);
		result.setMessage(msg);
		result.setData(obj);
		return result;
	}

	@Override
	public String toString() {
		return "ajaxResult => {" + "code='" + code + '\'' + ", message='" + message + '\'' + ", data=" + data
				+ '}';
	}
}
