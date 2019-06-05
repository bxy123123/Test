package com.cc.entity;
/**
 * 所有的登录账号类
 * @author Administrator
 *
 */
public class User {

	private int user_id;//用户id
	private String user_name;//用户名或账号
	private String user_pwd;//用户密码
	private String user_email;//用户邮箱
	private String user_phone;//用户手机号
	private String user_type;//用户类型（管理员/一般用户）
	
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_pwd=" + user_pwd + ", user_email="
				+ user_email + ", user_phone=" + user_phone + ", user_type=" + user_type + "]";
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_pwd() {
		return user_pwd;
	}


	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_phone() {
		return user_phone;
	}


	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_type() {
		return user_type;
	}


	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	
}
