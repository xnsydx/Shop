package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;

public interface AdminService {
	
	// 获取所有商品分类
	public List<Category> findAllCategory() throws SQLException ;

	//添加存储商品
	public void saveProducy(Product product) throws SQLException ;

	public List<Order> findAllOrders() throws SQLException ;

	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException; 

}
