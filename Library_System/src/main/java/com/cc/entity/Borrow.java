package com.cc.entity;

public class Borrow {

	private int book_id;//图书编号
	private String book_name;//图书名称
	private String user_name;//用户账号
	private String borrow_datetime;//借阅日期
	private String borrow_endtime;//还书日期
	private String borrow_status;//0表示未还   1表示已还
	
	


	


	@Override
	public String toString() {
		return "Borrow [book_id=" + book_id + ", book_name=" + book_name + ", user_name=" + user_name
				+ ", borrow_datetime=" + borrow_datetime + ", borrow_endtime=" + borrow_endtime + ", borrow_status="
				+ borrow_status + "]";
	}


	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	public String getBook_name() {
		return book_name;
	}


	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getBorrow_datetime() {
		return borrow_datetime;
	}


	public void setBorrow_datetime(String borrow_datetime) {
		this.borrow_datetime = borrow_datetime;
	}


	public String getBorrow_endtime() {
		return borrow_endtime;
	}


	public void setBorrow_endtime(String borrow_endtime) {
		this.borrow_endtime = borrow_endtime;
	}


	public String getBorrow_status() {
		return borrow_status;
	}


	public void setBorrow_status(String borrow_status) {
		this.borrow_status = borrow_status;
	}
	
	
	
}
