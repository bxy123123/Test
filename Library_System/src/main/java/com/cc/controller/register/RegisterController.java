package com.cc.controller.register;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.entity.User;
import com.cc.service.RegisterService;
import com.cc.util.LibraryResult;

@Controller
public class RegisterController {
	
	
	@Resource
	private RegisterService rs;
	
	/**
	 * Ìø×ªµ½×¢²áÒ³Ãæ
	 * @return
	 */
	@RequestMapping("register.do")
	public String register(){
		return "register";
	}
	
	/**
	 * ×¢²áÕËºÅ
	 * @param username
	 * @param userpwd
	 * @param useremail
	 * @param userphone
	 * @return
	 */
	@RequestMapping("CheckedRegister.do")
	@ResponseBody
	public LibraryResult CheckedRegister(String username,String userpwd,String useremail,String userphone){
		User user=new User();
		user.setUser_email(useremail);
		user.setUser_name(username);
		user.setUser_phone(userphone);
		user.setUser_pwd(userpwd);
		user.setUser_type("user");
		
		LibraryResult result=rs.CheckedRegister(user);
		return result;
	}
	
}
