package com.cc.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.cc.entity.User;
import com.cc.mapper.IndexMapper;
import com.cc.service.IndexService;
import com.cc.util.ByteSourceUtils;
import com.cc.util.LibraryResult;

@Service
public class IndexServiceImpl implements IndexService {

	
	@Resource
	private IndexMapper im;
	
	
	/**
	 * –ﬁ∏ƒ√‹¬Î
	 */
	public LibraryResult Updatepwd(String uname, String oldpwd, String newpwd) {
		LibraryResult result=new LibraryResult();
		User user=im.SelectUserByName(uname);
		System.out.println(oldpwd);
		System.out.println(newpwd);
		System.out.println(user);
		//MD5º”√‹
		String hash="MD5";
		Object salt=ByteSourceUtils.bytes(user.getUser_phone());
		int d=1024;
		Object Md5Pwd=new SimpleHash(hash,oldpwd,salt,d);
		String mp = Md5Pwd.toString();
		System.out.println(mp);
		if(!mp.equals(user.getUser_pwd())){
			result.setStatus(0);
			return result;
		}else{
			Object Md5Pwd1=new SimpleHash(hash,newpwd,salt,d);
			String mp1 = Md5Pwd1.toString();
			im.UpdatePwd(uname,mp1);
			result.setStatus(1);
			return result;
		}
	}

}
