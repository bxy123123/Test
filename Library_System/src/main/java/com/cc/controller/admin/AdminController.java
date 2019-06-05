package com.cc.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.entity.AllBooks;
import com.cc.service.AdminService;
import com.cc.util.LibraryResult;

@Controller
public class AdminController {

	@Resource
	private AdminService as;
	
	/**
	 * 添加图书
	 * @param name
	 * @param type
	 * @param autho
	 * @param press
	 * @param counts
	 * @return
	 */
	@RequestMapping("/AddBook.do")
	@ResponseBody
	public LibraryResult AddBook(String name,String type,String autho,String press,Integer counts){
		AllBooks book=new AllBooks();
		book.setBook_name(name);
		book.setBook_type(type);
		book.setCounts(counts);
		book.setPress(press);
		book.setWriter_name(autho);
		LibraryResult result=as.AddBook(book);
		return result;
	}
	
	/**
	 * 修改图书(数据的回显)
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/showBookById.do")
	@ResponseBody
	public LibraryResult showBookById(Integer bookId){
		LibraryResult result=as.showBookById(bookId);
		return result;
	}
	
	/**
	 * 修改图书
	 * @param bookId
	 * @param name
	 * @param type
	 * @param autho
	 * @param press
	 * @param counts
	 * @return
	 */
	@RequestMapping("/UpdateBook.do")
	@ResponseBody
	public LibraryResult UpdateBook(Integer bookId,String name,String type,String autho,String press,Integer counts){
		AllBooks book=new AllBooks();
		book.setBook_id(bookId);
		book.setBook_name(name);
		book.setBook_type(type);
		book.setCounts(counts);
		book.setPress(press);
		book.setWriter_name(autho);
		LibraryResult result=as.UpdateBook(book);
		return result;
	}
	
	/**
	 * 根据图书编号删除图书
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/DeleteBook.do")
	@ResponseBody
	public LibraryResult DeleteBook(Integer bookId){
		LibraryResult result=as.DeleteBook(bookId);
		return result;
	}
}
