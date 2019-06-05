package com.cc.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.util.LibraryResult;

@Controller
public class LoginController {

	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(){
		return "login";
	}
	
	/**
	 * 检验登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/CheckLogin.do")
	@ResponseBody
	public LibraryResult CheckLogin(String username,String password){
		//获取Subject对象
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			//在没有被验证的情况下，需要获取账号和密码与后台的数据进行匹配
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			try {
				currentUser.login(token);
			} catch (IncorrectCredentialsException ice) {
                throw new IncorrectCredentialsException("密码错误！");
            }catch (UnknownAccountException i) {
                throw new UnknownAccountException("用户名错误！");
            }catch (AuthenticationException ae) {
            	System.out.println("登录失败"+ae.getMessage());
            }
		}
		return new LibraryResult();
		
	}
}
