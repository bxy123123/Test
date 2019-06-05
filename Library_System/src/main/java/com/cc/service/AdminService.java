package com.cc.service;

import com.cc.entity.AllBooks;
import com.cc.util.LibraryResult;

public interface AdminService {

	//添加图书
	LibraryResult AddBook(AllBooks book);

	//修改图书(数据的回显)
	LibraryResult showBookById(Integer bookId);

	
	//修改图书
	LibraryResult UpdateBook(AllBooks book);

	
	//根据图书编号删除图书
	LibraryResult DeleteBook(Integer bookId);

}
