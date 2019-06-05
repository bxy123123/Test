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
	 * 查询出所有图书
	 */
	public PageUtil ShowAllBooks(Integer curPage) {
		PageUtil page = new PageUtil();
		if (curPage == null ) {
			curPage = 1;
		}
		//查出总条数
		int count = um.findAllCountBooks();
		//计算总页数
		page.setTotalCount(count);
		//当前页
		page.setCurrentPage(curPage);
		//每页条数
		page.setPageSize(5);
		int start = page.getStart();//起始页
		int end = page.getEnd();//每页条数
		List<AllBooks> list = um.findAllBooks(start,end);
		page.setStatus(0);
		page.setData(list);
		return page;
	}

	/**
	 * 根据信息查询图书
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
		//查出总条数
		page.setTotalCount(count);
		//当前页
		page.setCurrentPage(curPage);
		//每页条数
		page.setPageSize(5);
		int start = page.getStart();//起始页
		int end = page.getEnd();//每页条数
		map.put("start",start);
		map.put("end", end);
		List<AllBooks> list = um.SelectBookByMessage(map);
		System.out.println(list);
		if(list==null || list.size()==0){
			page.setStatus(1);
			page.setMsg("没有查到你想要的！");
			return page;
		}
		page.setStatus(0);
		page.setMsg("查询成功");
		page.setData(list);
		return page;
	}

	/**
	 * 借阅图书
	 */
	public LibraryResult BorrowBook(Integer bookId, String username) {
		LibraryResult result = new LibraryResult();
		Borrow borrow = new Borrow();
		//根据图书id来查询库存
		AllBooks book = um.findBookById(bookId);
		int num = book.getCounts();
		if(num<=0){
			result.setMsg("没有库存");
			result.setStatus(1);
			return result;
		}
			//把信息放入借阅表中
			borrow.setBook_id(book.getBook_id());
			borrow.setBook_name(book.getBook_name());
			borrow.setUser_name(username);
			um.BorrowBook(borrow);
			//同时库存减一
			um.updateBookCount(bookId);
			result.setStatus(0);
			result.setMsg("借阅成功");
			return result;
	}

	/**
	 * 查询登录用户的借阅记录
	 */
	public PageUtil ShowBorrowMessage(String username, Integer curPage) {
		PageUtil page = new PageUtil();
		if (curPage == null ) {
			curPage = 1;
		}
		//查出总条数
		int count = um.SelectBorrowMessageByUserName(username);
		page.setTotalCount(count);
		//当前页
		page.setCurrentPage(curPage);
		//每页条数
		page.setPageSize(5);
		int start = page.getStart();//起始页
		int end = page.getEnd();//每页条数
		List<Borrow> list = um.SelectBorrowMessage(start,end,username);
		if(list==null || list.size()==0){
			page.setStatus(1);
			page.setMsg("你还没有借阅任何书！");
			return page;
		}
		page.setStatus(0);
		page.setData(list);
		return page;
	}

	/**
	 * 还书
	 */
	public LibraryResult BackBook(Integer bookId) {
		um.BackBook(bookId);
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}
}
