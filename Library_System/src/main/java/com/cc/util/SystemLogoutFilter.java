package com.cc.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;

@Service
public class SystemLogoutFilter extends LogoutFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// ������ִ���˳�ϵͳǰ��Ҫ��յ�����
		Subject subject = getSubject(request, response);

		String redirectUrl = getRedirectUrl(request, response, subject);
		
		 SecurityUtils.getSubject().getSession().removeAttribute("User");
		try {

			subject.logout();

		} catch (SessionException ise) {

			ise.printStackTrace();

		}

		issueRedirect(request, response, redirectUrl);

		// ����false��ʾ��ִ�к����Ĺ�������ֱ�ӷ�����ת����¼ҳ��
		return false;

	}
}
