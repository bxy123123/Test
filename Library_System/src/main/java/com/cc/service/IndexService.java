package com.cc.service;

import com.cc.util.LibraryResult;

public interface IndexService {

	//�޸�����
	LibraryResult Updatepwd(String uname, String oldpwd, String newpwd);

}
