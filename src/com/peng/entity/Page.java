package com.peng.entity;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	private int pageNo=1;
	
	private int pageSize=10;
	
	private List<T> dataList;
	
	private long total;
	
	public Page() {}

	public Page(int pageNo, int pageSize, List<T> dataList, long total) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.dataList = dataList;
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	

}
