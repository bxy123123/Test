package com.cc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cc.entity.AllBooks;
import com.cc.entity.Borrow;

public interface UserMapper {

	
	//查询出所有图书的总数
	int findAllCountBooks();

	//分页查询出所有图书
	List<AllBooks> findAllBooks(@Param("start")int start, @Param("end")int end);

	
	//根据信息查询图书数量
	int SelectBookByMessageCount(Map<String, Object> map);

	
	//根据信息查询所有图书
	List<AllBooks> SelectBookByMessage(Map<String, Object> map);

	
	//根据图书编号查看该图书库存
	AllBooks findBookById(Integer bookId);

	//将借阅的图书信息放入借阅信息表中
	void BorrowBook(Borrow borrow);

	//更改图书库存
	void updateBookCount(Integer bookId);

	//查询该用户的借阅数
	int SelectBorrowMessageByUserName(String username);

	//分页查询登录用户的借阅记录
	List<Borrow> SelectBorrowMessage(@Param("start")int start, @Param("end")int end, @Param("username")String username);

	//还书
	void BackBook(Integer bookId);

}
