package com.cc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.entity.User;
import com.cc.mapper.LoginMapper;
import com.cc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private LoginMapper lm;
	
	/**
	 * 检验登录（获取用户信息）
	 */
	public User CheckLogin(String uname) {
		User user=lm.CheckLogin(uname);
		return user;
	}

}
