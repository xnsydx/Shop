package com.itheima.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {

	public static Object getBean(String id){
		
		//��������---�����嵥����----�����ļ�----��ÿһ��bean�����������ϸ���䵽�����ļ���
		//ʹ��dom4j��xml��������
		
		try {
			//1������������
			SAXReader reader = new SAXReader();
			//2�������ĵ�---bean.xml��src��
			String path = BeanFactory.class.getClassLoader().getResource("bean.xml").getPath();
			Document doc = reader.read(path);
			//3�����Ԫ��---������xpath����
			Element element = (Element) doc.selectSingleNode("//bean[@id='"+id+"']");
			//<bean id="adminService" class="com.itheima.service.impl.AdminServiceImpl"></bean>
			String className = element.attributeValue("class");
			//com.itheima.service.impl.AdminServiceImpl
			//ʹ�÷��䴴������
			Class clazz = Class.forName(className);
			Object object = clazz.newInstance();
			
			return object;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
