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
	 * 查询出所有图书
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
	 * 根据信息查询图书
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
	 * 借阅图书
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
	 * 显示该用户的借阅记录
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
	 * 还书
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
