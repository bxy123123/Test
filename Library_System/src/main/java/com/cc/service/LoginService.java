package com.cc.service;

import com.cc.entity.User;

public interface LoginService {

	//检验登录（获取用户信息）
	User CheckLogin(String uname);

}
