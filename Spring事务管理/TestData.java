package com.netease.course;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestData {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		   
		AccountDao dao = context.getBean("accountDao",AccountDao.class);
	    dao.resetMoney();
	    
	    try {
	    	 dao.transferMoney("li", "han", 521);
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	   
	    
		List<Account> accountList = dao.accountList();
		
		for(Account account:accountList) {
			System.out.println(account.getUser()+":"+account.getBalance());	
		}

		((ConfigurableApplicationContext)context).close();


	}

}
