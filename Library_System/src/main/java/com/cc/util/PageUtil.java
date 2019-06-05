package com.cc.util;

public class PageUtil {
	//每页显示5条数据
	private int pageSize = 5;
	//总数据的条数
	private int totalCount;
	//总页数
	private int totalPage;
	//当前页
	private int currentPage;
	//当前开始的记录索引
	private int start;
	//当前结束的记录索引
	private int end;
	//返回的数据
	private int status;
	private String msg;
	private Object data;
	
	//get/set
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	//计算总条数
	public int getTotalCount() {
		return totalCount;
	}
	//设定总记录数并计算总页数
	public void setTotalCount(int totalCount) {
		if (totalCount != 0) {
			this.totalCount = totalCount;
			if (this.totalCount % pageSize == 0) {
				totalPage = this.totalCount / pageSize;
			}else {
				totalPage = this.totalCount / pageSize + 1;
			}
		}else {
			totalPage = 1;
		}
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	//设置当前页,计算当前页的开始和结束的记录索引
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
		if (this.currentPage > totalPage) {
			this.currentPage = totalPage;
		}
		start = (this.currentPage-1) * pageSize;
		end = pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
