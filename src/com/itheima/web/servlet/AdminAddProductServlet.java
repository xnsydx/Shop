package com.itheima.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product product = new Product();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> parseRequest = upload.parseRequest(request);

			Map<String, Object> map = new HashMap<String, Object>();
			if (parseRequest != null) {
				for (FileItem item : parseRequest) {
					boolean formField = item.isFormField();// 是否是普通文件项

					if (formField) {
						// 普通上传项
						String fieldName = item.getFieldName();
						String fieldValue = item.getString("UTF-8");
						map.put(fieldName, fieldValue);
					} else {
						// 文件上传项
						String path = this.getServletContext().getRealPath("upload");
						System.out.println(path);
						String fileName = item.getName();
						InputStream in = item.getInputStream();
						OutputStream out = new FileOutputStream(path + "/" + fileName);
						IOUtils.copy(in, out);
						map.put("pimage", "upload/" + fileName);
						in.close();
						out.close();
					}
				}
			}
			
			BeanUtils.populate(product, map);
			// private String pid;
			product.setPid(UUID.randomUUID().toString());
			// private Date pdate;
			product.setPdate(new Date());
			// private int pflag;
			product.setPflag(0);
			// private Category category;
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);

			// 将product传递给Service层
			AdminService service = (AdminService) BeanFactory.getBean("AdminService");
			service.saveProducy(product);

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/admin/product/list.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}
}