package com.cc.mapper;

import org.apache.ibatis.annotations.Param;

import com.cc.entity.User;

public interface IndexMapper {

	//根据账号查找用户
	User SelectUserByName(String uname);

	//根据账号修改密码
	void UpdatePwd(@Param("uname")String uname, @Param("newpwd")String newpwd);

}
