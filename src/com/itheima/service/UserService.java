package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public interface UserService {

	// 激活
	public void active(String activeCode);

	// 校验用户名是否存在
	public boolean checkUsername(String username);

	// 用户登录的方法
	public User login(String username, String password) throws SQLException;

	// 注册
	public boolean regist(User user);

}
