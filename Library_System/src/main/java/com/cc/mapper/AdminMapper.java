package com.cc.mapper;

import com.cc.entity.AllBooks;

public interface AdminMapper {

	
	//添加图书
	void AddBook(AllBooks book);

	//修改图书(数据的回显)
	AllBooks showBookById(Integer bookId);

	
	//修改图书
	void UpdateBook(AllBooks book);

	//根据图书编号删除图书
	void DeleteBook(Integer bookId);

}
