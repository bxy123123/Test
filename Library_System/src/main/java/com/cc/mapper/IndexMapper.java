package com.cc.mapper;

import org.apache.ibatis.annotations.Param;

import com.cc.entity.User;

public interface IndexMapper {

	//�����˺Ų����û�
	User SelectUserByName(String uname);

	//�����˺��޸�����
	void UpdatePwd(@Param("uname")String uname, @Param("newpwd")String newpwd);

}
