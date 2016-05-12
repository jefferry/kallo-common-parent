package com.kaloo.common.page;

import java.io.Serializable;

/**
 * 分页参数传递工具类 .
 */
public class PageParam implements Serializable {

	private static final long serialVersionUID = 6297178964005032338L;

	private int pageNum; // 当前页数

	private int pageSize; // 每页记录数

	private String orderBy; // 排序

	public PageParam(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	/** 当前页数 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页数 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页记录数 */
	public int getPageSize() {
		return pageSize;
	}

	/** 每页记录数 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
