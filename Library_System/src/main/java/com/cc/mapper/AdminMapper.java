package com.cc.mapper;

import com.cc.entity.AllBooks;

public interface AdminMapper {

	
	//���ͼ��
	void AddBook(AllBooks book);

	//�޸�ͼ��(���ݵĻ���)
	AllBooks showBookById(Integer bookId);

	
	//�޸�ͼ��
	void UpdateBook(AllBooks book);

	//����ͼ����ɾ��ͼ��
	void DeleteBook(Integer bookId);

}
