package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

/*
 * �������첽��ʾ��ѡ����
 */
public class SearchWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String word = request.getParameter("word");
		ProductService service = (ProductService) BeanFactory.getBean("productService");
		List<Object> productList=null;
		try {
			productList=service.searchProductByWord(word);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ʹ��jsonת�����߽�����򼯺�ת��json��ʽ���ַ���
		/*JSONArray fromObject = JSONArray.fromObject(productList);
		String string = fromObject.toString();
		System.out.println(string);*/
		
		//ʹ��Gsonת������ת������
		Gson gson = new  Gson();
		String json = gson.toJson(productList);
		System.out.println(json);
		response.getWriter().write(json);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}
}