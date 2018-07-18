package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public interface ProductService {

	// 获得热门商品
	public List<Product> findHotProductList();

	// 获得最新商品
	public List<Product> findNewProductList();

	public List<Category> findAllCategory();

	// 显示每页商品
	public PageBean findProductListByCid(String cid, int currentPage, int currentCount);

	public Product findProductByPid(String pid);

	// 提交订单 将订单的数据和订单项的数据存储到数据库中
	public void submitOrder(Order order);

	public void updateOrderAdrr(Order order);

	public void updateOrderState(String r6_Order);

	// 获得指定用户的订单集合
	public List<Order> findAllOrders(String uid);

	// 搜索
	public List<Object> searchProductByWord(String word) throws SQLException;

	// 通过oid获取指定用户的所有的订单
	public List<Map<String, Object>> findAllOrderItemByOid(String oid);
}
