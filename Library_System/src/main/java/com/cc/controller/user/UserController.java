package com.cc.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.service.UserService;
import com.cc.util.LibraryResult;
import com.cc.util.PageUtil;

@Controller
public class UserController {

	@Resource
	private UserService us;
	
	/**
	 * ��ѯ������ͼ��
	 * @param curPage
	 * @return
	 */
	@RequestMapping("/ShowAllBooks.do")
	@ResponseBody
	public PageUtil ShowAllBooks(Integer curPage){
		PageUtil page = us.ShowAllBooks(curPage);
		return page;
	}
	
	/**
	 * ������Ϣ��ѯͼ��
	 * @param bookname
	 * @param booktype
	 * @param autho
	 * @param curPage
	 * @return
	 */
	@RequestMapping("/SelectBookByMessage.do")
	@ResponseBody
	public PageUtil SelectBookByMessage(String bookname,String booktype,String autho,Integer curPage){
		PageUtil page = us.SelectBookByMessage(bookname,booktype,autho,curPage);
		return page;
	}
	
	/**
	 * ����ͼ��
	 * @param bookId
	 * @param username
	 * @return
	 */
	@RequestMapping("/BorrowBook.do")
	@ResponseBody
	public LibraryResult BorrowBook(Integer bookId,String username ){
		LibraryResult result=us.BorrowBook(bookId,username);
		return result;
	}
	
	/**
	 * ��ʾ���û��Ľ��ļ�¼
	 * @param username
	 * @param curPage
	 * @return
	 */
	@RequestMapping("/ShowBorrowMessage.do")
	@ResponseBody
	public PageUtil ShowBorrowMessage(String username,Integer curPage){
		PageUtil page = us.ShowBorrowMessage(username,curPage);
		return page;
	}
	
	/**
	 * ����
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/BackBook.do")
	@ResponseBody
	public LibraryResult BackBook(Integer bookId){
		LibraryResult result=us.BackBook(bookId);
		return result;
	}
}
