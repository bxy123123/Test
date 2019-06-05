package com.cc.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cc.entity.User;
import com.cc.service.LoginService;


public class LoginRealm extends AuthorizingRealm{

	@Resource
	private LoginService ls;
	
	/**
	 * shiro认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//比对账号和密码是否正确
		//1.把AuthenticationToken对象转换成UsernamePasswordToken对象
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		//获取用户在登录页面输入的账号
		String uname=uptoken.getUsername();
		//从service中调用方法,获得该用户的信息
		User user = ls.CheckLogin(uname);
		//盐值加密
		ByteSource salt=ByteSource.Util.bytes(user.getUser_phone());
		//自动完成密码比对
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(uname,user.getUser_pwd(),salt,getName());
		SecurityUtils.getSubject().getSession().setAttribute("User", user);
		return info;
	}

	
	public static void main(String[] args) {
		String algorithmName="MD5";
		Object source="123456";
		Object salt=ByteSource.Util.bytes("13157118620");
		int a=1024;
		Object result=new SimpleHash(algorithmName, source,salt,a);
		System.out.println(result);
	}


	/**
	 * shiro授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.从PrincipalCollection获取登录用户的信息
		//User username = (User) principals.getPrimaryPrincipal();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("User");
		String user_type =String.valueOf(user.getUser_type());
		//2.利用登录的用户信息来查勘车该用户的角色或权限(有可能要从数据库中查询)
		Set<String> Set = new HashSet<String>();
		Set.add(user_type);
		//3.创建SimpleAuthorizationInfo,并设置其角色的权限
		SimpleAuthorizationInfo sai=new SimpleAuthorizationInfo(Set);
		//sai.setStringPermissions(Set);
		//4.返回SimpleAuthorizationInfo对象
		return sai;
	}
}


