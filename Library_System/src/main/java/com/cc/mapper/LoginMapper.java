package com.cc.mapper;

import com.cc.entity.User;

public interface LoginMapper {

	//检验登录（获取用户信息）
	User CheckLogin(String uname);

}
