package com.itheima.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;

public class UserLoginPrivilegeFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		// �ж��û��Ƿ��Ѿ���¼ δ��¼������벻ִ��
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// û�е�¼
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
