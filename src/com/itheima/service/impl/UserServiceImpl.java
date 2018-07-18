package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService{

	// ����
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// У���û����Ƿ����
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist > 0 ? true : false;
	}

	// �û���¼�ķ���
	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username, password);
	}

	//ע��
	public boolean regist(User user) {

		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row > 0 ? true : false;
	}

}
