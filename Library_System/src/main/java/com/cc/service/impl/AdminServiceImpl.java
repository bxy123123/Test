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
	 * ���ͼ��
	 */
	public LibraryResult AddBook(AllBooks book) {
		am.AddBook(book);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

	/**
	 * �޸�ͼ��(���ݵĻ���)
	 */
	public LibraryResult showBookById(Integer bookId) {
		AllBooks book=am.showBookById(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		result.setData(book);
		return result;
	}

	/**
	 * �޸�ͼ��
	 */
	public LibraryResult UpdateBook(AllBooks book) {
		am.UpdateBook(book);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

	/**
	 * ����ͼ����ɾ��ͼ��
	 */
	public LibraryResult DeleteBook(Integer bookId) {
		am.DeleteBook(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

}
