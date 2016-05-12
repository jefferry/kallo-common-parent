package com.kaloo.common.utils.export;

public class DataPage {
	/**
	 * 总页数
	 */
	// private int countPage = 1;

	/**
	 * 一页中最多条目数
	 */
	private int maxEntityInOnePage;

	public DataPage() {
	}

	public DataPage(int maxEntityInOnePage) {
		this.maxEntityInOnePage = maxEntityInOnePage;
	}

	// public int getCountPage() {
	// return countPage;
	// }
	//
	// public void setCountPage(int countPage) {
	// this.countPage = countPage;
	// }

	public int getMaxEntityInOnePage() {
		return maxEntityInOnePage;
	}

	public void setMaxEntityInOnePage(int maxEntityInOnePage) {
		this.maxEntityInOnePage = maxEntityInOnePage;
	}

}
