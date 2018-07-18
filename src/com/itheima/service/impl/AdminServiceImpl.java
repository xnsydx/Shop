package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.AdminService;
import com.itheima.service.impl.AdminServiceImpl;

public class AdminServiceImpl implements AdminService{
	
	// 获取所有商品分类
	public List<Category> findAllCategory() throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.findAllCategory();
	}

	//添加存储商品
	public void saveProducy(Product product) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.saveProduct(product);
	}

	public List<Order> findAllOrders() throws SQLException {
		AdminDao dao = new AdminDao();
		List<Order> orderList=dao.findAllOrders();
		return orderList;
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		AdminDao dao = new AdminDao();
		List<Map<String, Object>> map=dao.findOrderInfoByOid(oid);
		return map;
	}

}
