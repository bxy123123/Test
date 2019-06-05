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
	 * ��ת����½ҳ��
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(){
		return "login";
	}
	
	/**
	 * �����¼
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/CheckLogin.do")
	@ResponseBody
	public LibraryResult CheckLogin(String username,String password){
		//��ȡSubject����
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			//��û�б���֤������£���Ҫ��ȡ�˺ź��������̨�����ݽ���ƥ��
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			try {
				currentUser.login(token);
			} catch (IncorrectCredentialsException ice) {
                throw new IncorrectCredentialsException("�������");
            }catch (UnknownAccountException i) {
                throw new UnknownAccountException("�û�������");
            }catch (AuthenticationException ae) {
            	System.out.println("��¼ʧ��"+ae.getMessage());
            }
		}
		return new LibraryResult();
		
	}
}
