package com.chinasoft.util;

import java.util.ArrayList;

public class PageModel {
	/**
	 *  共有多少条数据
	 */
	private int count = 0;
	/**
	 * 每页多少条数据
	 */
	private int pageSize = 0;
	/**
	 * 当前是第几页
	 */
	private int pageNo = 0;
	/**
	 * 上一页
	 */
	private int pagePre = 0;
	/**
	 * 下一页
	 */
	private int pageNext = 0;
	/**
	 * 第一页
	 */
	private int pageFirst = 0;
	/**
	 * 尾页
	 */
	private int pageLast = 0;
	/**
	 * 分页数据
	 */
	private ArrayList list;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPagePre() {
		pagePre = pageNo - 1 == 0 ? 1 : pageNo - 1;
		return pagePre;
	}

	public int getPageNext() {
		pageNext = pageNo + 1 > getPageLast() ? getPageLast() : pageNo + 1;
		return pageNext;
	}

	public int getPageFirst() {
		pageFirst = 1;
		return pageFirst;
	}

	public int getPageLast() {
		// 通过计算获得
		pageLast = count == 0 ? 1 : (count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
		return pageLast;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

}
