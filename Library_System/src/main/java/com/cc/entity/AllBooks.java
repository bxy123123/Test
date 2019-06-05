package com.cc.entity;
/**
 * 所有图书类
 * @author Administrator
 *
 */
public class AllBooks {
	
	private int book_id;//图书编号
	private String book_type;//图书类型
	private String book_name;//图书名字
	private String writer_name;//作者
	private String press;//出版社
	private int counts;//库存量
	
	
	@Override
	public String toString() {
		return "AllBooks [book_id=" + book_id + ", book_type=" + book_type + ", book_name=" + book_name
				+ ", writer_name=" + writer_name + ", press=" + press + ", counts=" + counts + "]";
	}


	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	public String getBook_type() {
		return book_type;
	}


	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}


	public String getBook_name() {
		return book_name;
	}


	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}


	public String getWriter_name() {
		return writer_name;
	}


	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}


	public String getPress() {
		return press;
	}


	public void setPress(String press) {
		this.press = press;
	}


	public int getCounts() {
		return counts;
	}


	public void setCounts(int counts) {
		this.counts = counts;
	}
	
	
}
