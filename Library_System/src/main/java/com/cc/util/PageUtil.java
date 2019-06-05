package com.cc.util;

public class PageUtil {
	//ÿҳ��ʾ5������
	private int pageSize = 5;
	//�����ݵ�����
	private int totalCount;
	//��ҳ��
	private int totalPage;
	//��ǰҳ
	private int currentPage;
	//��ǰ��ʼ�ļ�¼����
	private int start;
	//��ǰ�����ļ�¼����
	private int end;
	//���ص�����
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
	//����������
	public int getTotalCount() {
		return totalCount;
	}
	//�趨�ܼ�¼����������ҳ��
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
	//���õ�ǰҳ,���㵱ǰҳ�Ŀ�ʼ�ͽ����ļ�¼����
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
