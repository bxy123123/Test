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
	 * shiro��֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//�ȶ��˺ź������Ƿ���ȷ
		//1.��AuthenticationToken����ת����UsernamePasswordToken����
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		//��ȡ�û��ڵ�¼ҳ��������˺�
		String uname=uptoken.getUsername();
		//��service�е��÷���,��ø��û�����Ϣ
		User user = ls.CheckLogin(uname);
		//��ֵ����
		ByteSource salt=ByteSource.Util.bytes(user.getUser_phone());
		//�Զ��������ȶ�
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
	 * shiro��Ȩ
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.��PrincipalCollection��ȡ��¼�û�����Ϣ
		//User username = (User) principals.getPrimaryPrincipal();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("User");
		String user_type =String.valueOf(user.getUser_type());
		//2.���õ�¼���û���Ϣ���鿱�����û��Ľ�ɫ��Ȩ��(�п���Ҫ�����ݿ��в�ѯ)
		Set<String> Set = new HashSet<String>();
		Set.add(user_type);
		//3.����SimpleAuthorizationInfo,���������ɫ��Ȩ��
		SimpleAuthorizationInfo sai=new SimpleAuthorizationInfo(Set);
		//sai.setStringPermissions(Set);
		//4.����SimpleAuthorizationInfo����
		return sai;
	}
}


