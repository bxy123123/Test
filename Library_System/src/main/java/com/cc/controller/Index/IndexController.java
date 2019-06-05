package com.cc.controller.Index;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.service.IndexService;
import com.cc.util.LibraryResult;

@Controller
public class IndexController {
	
	@Resource
	private IndexService is;
	
	/**
	 * ��ת����½ҳ��
	 * @return
	 */
	@RequestMapping("/Index.do")
	public String Index(){
		return "index";
	}
	
	/**
	 * �޸�����
	 * @param uname
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	@RequestMapping("/Updatepwd.do")
	@ResponseBody
	public LibraryResult Updatepwd(String uname,String oldpwd,String newpwd){
		LibraryResult result=is.Updatepwd(uname,oldpwd,newpwd);
		return result;
	}
}
