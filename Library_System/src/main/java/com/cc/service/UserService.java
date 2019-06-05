package com.cc.service;

import com.cc.util.LibraryResult;
import com.cc.util.PageUtil;

public interface UserService {

	//��ѯ������ͼ��
	PageUtil ShowAllBooks(Integer curPage);

	//������Ϣ��ѯͼ��
	PageUtil SelectBookByMessage(String bookname, String booktype, String autho, Integer curPage);

	//����ͼ��
	LibraryResult BorrowBook(Integer bookId, String username);

	//��ʾ���û��Ľ��ļ�¼
	PageUtil ShowBorrowMessage(String username, Integer curPage);

	
	//����
	LibraryResult BackBook(Integer bookId);

}
