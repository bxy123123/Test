package com.cc.service;

import com.cc.entity.AllBooks;
import com.cc.util.LibraryResult;

public interface AdminService {

	//���ͼ��
	LibraryResult AddBook(AllBooks book);

	//�޸�ͼ��(���ݵĻ���)
	LibraryResult showBookById(Integer bookId);

	
	//�޸�ͼ��
	LibraryResult UpdateBook(AllBooks book);

	
	//����ͼ����ɾ��ͼ��
	LibraryResult DeleteBook(Integer bookId);

}
