package com.cc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cc.entity.AllBooks;
import com.cc.entity.Borrow;

public interface UserMapper {

	
	//��ѯ������ͼ�������
	int findAllCountBooks();

	//��ҳ��ѯ������ͼ��
	List<AllBooks> findAllBooks(@Param("start")int start, @Param("end")int end);

	
	//������Ϣ��ѯͼ������
	int SelectBookByMessageCount(Map<String, Object> map);

	
	//������Ϣ��ѯ����ͼ��
	List<AllBooks> SelectBookByMessage(Map<String, Object> map);

	
	//����ͼ���Ų鿴��ͼ����
	AllBooks findBookById(Integer bookId);

	//�����ĵ�ͼ����Ϣ���������Ϣ����
	void BorrowBook(Borrow borrow);

	//����ͼ����
	void updateBookCount(Integer bookId);

	//��ѯ���û��Ľ�����
	int SelectBorrowMessageByUserName(String username);

	//��ҳ��ѯ��¼�û��Ľ��ļ�¼
	List<Borrow> SelectBorrowMessage(@Param("start")int start, @Param("end")int end, @Param("username")String username);

	//����
	void BackBook(Integer bookId);

}
