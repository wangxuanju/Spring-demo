package com.netease.course;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestData {

	public static void main(String[] args)throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		   
		JdbcTemplateDao dao = context.getBean("jdbcTemplateDao",JdbcTemplateDao.class);
		//dao.createTable();
		//dao.insertData();
		
		System.out.println(dao.count());

		List<User> userList = dao.getUserList();
		
		for(User user:userList) {
			System.out.println(user.getId()+":"+user.getFirstName()+""+user.getLastName());	
		}

		((ConfigurableApplicationContext)context).close();

	}

}
