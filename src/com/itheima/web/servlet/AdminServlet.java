package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

public class AdminServlet extends BaseServlet {
	
	//���ݶ���id��ѯ���������Ʒ��Ϣ
	public void findOrderInfoByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//���oid
		String oid = request.getParameter("oid");
		
		//�ý���ϵķ�ʽ���б���----��web����service������
		//ʹ�ù���+����+�����ļ�
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		
		List<Map<String, Object>> mapList=null;
		try {
			mapList = service.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(mapList);
		/*[
		 * 	{"shop_price":4499.0,"count":2,"pname":"���루Lenovo��С��V3000�����","pimage":"products/1/c_0034.jpg","subtotal":8998.0},
		 *  {"shop_price":2599.0,"count":1,"pname":"��Ϊ Ascend Mate7","pimage":"products/1/c_0010.jpg","subtotal":2599.0}
		 *]*/
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().write(json);
		
	}
	
	//������еĶ���
	public void findAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������еĶ�����Ϣ----List<Order>
		
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		List<Order> orderList=null;
		try {
			orderList = service.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
		
	}

	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//�ṩһ��List<Category> ת��json�ַ���
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		List<Category> categoryList=null;
		try {
			categoryList = service.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		
		response.setContentType("text/html;charset=UTF-8");
		
		response.getWriter().write(json);
		
	}

	
}
