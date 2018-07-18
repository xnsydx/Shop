package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.MD5Utils;

public class UserServlet extends BaseServlet {

	// �Ñ�����
	public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// ��ȡ������
		String activeCode = request.getParameter("activeCode");

		UserService service = (UserService) BeanFactory.getBean("userService");
		service.active(activeCode);

		// ת������¼ҳ��
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		
	}

	// �û�ע��
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// ��session�н�userɾ��
		session.removeAttribute("user");

		// ���洢�ڿͻ��˵�cookieɾ����
		Cookie cookie_username = new Cookie("cookie_username", "");
		cookie_username.setMaxAge(0);
		// �����洢�����cookie
		Cookie cookie_password = new Cookie("cookie_password", "");
		cookie_password.setMaxAge(0);

		response.addCookie(cookie_username);
		response.addCookie(cookie_password);

		response.sendRedirect(request.getContextPath() + "/login.jsp");

	}

	// �û���¼
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// ���������û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// ���û��������봫�ݸ�service��
		UserService service = (UserService) BeanFactory.getBean("userService");
		User user = null;
		try {
			user = service.login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// �ж��û��Ƿ��¼�ɹ� user�Ƿ���null
		if (user != null) {
			// ��¼�ɹ�
			// ***************�ж��û��Ƿ�ѡ���Զ���¼*****************
			String autoLogin = request.getParameter("autoLogin");
			if ("autoLogin".equals(autoLogin)) {
				// Ҫ�Զ���¼
				// �����洢�û�����cookie
				Cookie cookie_username = new Cookie("cookie_username", user.getUsername());
				cookie_username.setMaxAge(10 * 60);
				// �����洢�����cookie
				Cookie cookie_password = new Cookie("cookie_password", user.getPassword());
				cookie_password.setMaxAge(10 * 60);

				response.addCookie(cookie_username);
				response.addCookie(cookie_password);

			}

			// ***************************************************
			// ��user����浽session��
			session.setAttribute("user", user);

			// �ض�����ҳ
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			request.setAttribute("loginError", "�û������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
