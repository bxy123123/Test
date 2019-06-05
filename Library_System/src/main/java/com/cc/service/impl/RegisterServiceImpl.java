package com.cc.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.cc.entity.User;
import com.cc.mapper.RegisterMapper;
import com.cc.service.RegisterService;
import com.cc.util.ByteSourceUtils;
import com.cc.util.LibraryResult;
@Service
public class RegisterServiceImpl implements RegisterService {

	@Resource
	private RegisterMapper rm;
	
	/**
	 * ◊¢≤·’À∫≈
	 */
	public LibraryResult CheckedRegister(User user) {
		//MD5º”√‹
		String hash="MD5";
		Object salt=ByteSourceUtils.bytes(user.getUser_phone());
		int d=1024;
		Object Md5Pwd=new SimpleHash(hash,user.getUser_pwd(),salt,d);
		String mp = Md5Pwd.toString();
		user.setUser_pwd(mp);
		rm.CheckedRegister(user);
		
		LibraryResult result=new LibraryResult();
		result.setStatus(0);
		return result;
	}

}
