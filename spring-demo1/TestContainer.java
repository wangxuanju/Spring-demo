```java

package com.netease.course;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestContainer {

	public static void main(String[] args) {
		//Bean的使用
		//初始化容器
		//ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		//获取对象
		//ScrewDriver screwDriver = context.getBean("screwDriver",ScrewDriver.class);
		//使用对象
		//screwDriver.use();

		
		
		
		//ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		//ScrewDriver screwDriver = context.getBean("screwDriver",ScrewDriver.class);
		//screwDriver.setColor("green");
		//screwDriver.use();
		
		//ScrewDriver screwDriver1 = context.getBean("screwDriver",ScrewDriver.class);
		//screwDriver1.use();
		
		
		
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ScrewDriver screwDriver = context.getBean("screwDriver",ScrewDriver.class);
		screwDriver.use();
		
		((ConfigurableApplicationContext)context).close();
	}

}


```
