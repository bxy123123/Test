package com.cc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.entity.AllBooks;
import com.cc.entity.Borrow;
import com.cc.mapper.UserMapper;
import com.cc.service.UserService;
import com.cc.util.LibraryResult;
import com.cc.util.PageUtil;

@Service
public class UserServiceImpl implements UserService {

	
	@Resource
	private UserMapper um;
	
	
	/**
	 * ��ѯ������ͼ��
	 */
	public PageUtil ShowAllBooks(Integer curPage) {
		PageUtil page = new PageUtil();
		if (curPage == null ) {
			curPage = 1;
		}
		//���������
		int count = um.findAllCountBooks();
		//������ҳ��
		page.setTotalCount(count);
		//��ǰҳ
		page.setCurrentPage(curPage);
		//ÿҳ����
		page.setPageSize(5);
		int start = page.getStart();//��ʼҳ
		int end = page.getEnd();//ÿҳ����
		List<AllBooks> list = um.findAllBooks(start,end);
		page.setStatus(0);
		page.setData(list);
		return page;
	}

	/**
	 * ������Ϣ��ѯͼ��
	 */
	public PageUtil SelectBookByMessage(String bookname, String booktype, String autho,Integer curPage) {
		PageUtil page = new PageUtil();
		if (curPage == null ) {
			curPage = 1;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bookname", bookname);
		map.put("booktype", booktype);
		map.put("Autho",autho);
		int count = um.SelectBookByMessageCount(map);
		//���������
		page.setTotalCount(count);
		//��ǰҳ
		page.setCurrentPage(curPage);
		//ÿҳ����
		page.setPageSize(5);
		int start = page.getStart();//��ʼҳ
		int end = page.getEnd();//ÿҳ����
		map.put("start",start);
		map.put("end", end);
		List<AllBooks> list = um.SelectBookByMessage(map);
		System.out.println(list);
		if(list==null || list.size()==0){
			page.setStatus(1);
			page.setMsg("û�в鵽����Ҫ�ģ�");
			return page;
		}
		page.setStatus(0);
		page.setMsg("��ѯ�ɹ�");
		page.setData(list);
		return page;
	}

	/**
	 * ����ͼ��
	 */
	public LibraryResult BorrowBook(Integer bookId, String username) {
		LibraryResult result = new LibraryResult();
		Borrow borrow = new Borrow();
		//����ͼ��id����ѯ���
		AllBooks book = um.findBookById(bookId);
		int num = book.getCounts();
		if(num<=0){
			result.setMsg("û�п��");
			result.setStatus(1);
			return result;
		}
			//����Ϣ������ı���
			borrow.setBook_id(book.getBook_id());
			borrow.setBook_name(book.getBook_name());
			borrow.setUser_name(username);
			um.BorrowBook(borrow);
			//ͬʱ����һ
			um.updateBookCount(bookId);
			result.setStatus(0);
			result.setMsg("���ĳɹ�");
			return result;
	}

	/**
	 * ��ѯ��¼�û��Ľ��ļ�¼
	 */
	public PageUtil ShowBorrowMessage(String username, Integer curPage) {
		PageUtil page = new PageUtil();
		if (curPage == null ) {
			curPage = 1;
		}
		//���������
		int count = um.SelectBorrowMessageByUserName(username);
		page.setTotalCount(count);
		//��ǰҳ
		page.setCurrentPage(curPage);
		//ÿҳ����
		page.setPageSize(5);
		int start = page.getStart();//��ʼҳ
		int end = page.getEnd();//ÿҳ����
		List<Borrow> list = um.SelectBorrowMessage(start,end,username);
		if(list==null || list.size()==0){
			page.setStatus(1);
			page.setMsg("�㻹û�н����κ��飡");
			return page;
		}
		page.setStatus(0);
		page.setData(list);
		return page;
	}

	/**
	 * ����
	 */
	public LibraryResult BackBook(Integer bookId) {
		um.BackBook(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}
}
