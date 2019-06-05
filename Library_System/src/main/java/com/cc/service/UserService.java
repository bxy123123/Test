package com.cc.service;

import com.cc.util.LibraryResult;
import com.cc.util.PageUtil;

public interface UserService {

	//查询出所有图书
	PageUtil ShowAllBooks(Integer curPage);

	//根据信息查询图书
	PageUtil SelectBookByMessage(String bookname, String booktype, String autho, Integer curPage);

	//借阅图书
	LibraryResult BorrowBook(Integer bookId, String username);

	//显示该用户的借阅记录
	PageUtil ShowBorrowMessage(String username, Integer curPage);

	
	//还书
	LibraryResult BackBook(Integer bookId);

}
