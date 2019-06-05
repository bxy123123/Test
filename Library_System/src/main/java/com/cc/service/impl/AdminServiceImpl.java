package com.cc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.entity.AllBooks;
import com.cc.mapper.AdminMapper;
import com.cc.service.AdminService;
import com.cc.util.LibraryResult;
@Service
public class AdminServiceImpl implements AdminService {

	
	@Resource
	private AdminMapper am;
	
	/**
	 * 添加图书
	 */
	public LibraryResult AddBook(AllBooks book) {
		am.AddBook(book);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

	/**
	 * 修改图书(数据的回显)
	 */
	public LibraryResult showBookById(Integer bookId) {
		AllBooks book=am.showBookById(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		result.setData(book);
		return result;
	}

	/**
	 * 修改图书
	 */
	public LibraryResult UpdateBook(AllBooks book) {
		am.UpdateBook(book);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

	/**
	 * 根据图书编号删除图书
	 */
	public LibraryResult DeleteBook(Integer bookId) {
		am.DeleteBook(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

}
