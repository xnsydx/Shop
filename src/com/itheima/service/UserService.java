package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public interface UserService {

	// ����
	public void active(String activeCode);

	// У���û����Ƿ����
	public boolean checkUsername(String username);

	// �û���¼�ķ���
	public User login(String username, String password) throws SQLException;

	// ע��
	public boolean regist(User user);

}
