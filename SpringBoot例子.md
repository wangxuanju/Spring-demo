# 快速构建SpringBoot的方式
## domain：实体类
```java
public class User {
	String id;
	String username;
	String password;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
```
## dao层
```java

@Mapper
public interface UserDao {
	public List<User> selectUser(User user);

	public void insertUser(User user);
}


配置文件
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.test.myspringboot001.dao.UserDao">

	<select id="selectUser" parameterType="com.test.myspringboot001.domain.User"
		resultType="com.test.myspringboot001.domain.User">

		select * from user where username=#{username} and password=#{password}


	</select>
	
	<insert id="insertUser" parameterType="com.test.myspringboot001.domain.User" >
	
	insert into user(username,password) values(#{username},#{password})
	
	</insert>
</mapper>
```
## service层
```java
import java.util.List;
import com.test.myspringboot001.domain.User;

public interface UserService {

	public List<User> selectUser(User user);
	public void insertUser(User user);
}


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.myspringboot001.dao.UserDao;
import com.test.myspringboot001.domain.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> selectUser(User user) {
		// TODO Auto-generated method stub
		List<User> users = userDao.selectUser(user);
		return users;
	}

	@Transactional
	@Override
	public void insertUser(User user) {
		
		userDao.insertUser(user);
		//String s=null;
		//s.toCharArray();
		
		userDao.insertUser(user);
		// TODO Auto-generated method stub
	}
}
```
## 控制层
```java
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.myspringboot001.domain.User;
import com.test.myspringboot001.service.UserService;

@Controller
@RequestMapping("/user")
public class HelloController {
	@Autowired
	UserService userService;

	@RequestMapping("/hello")
	public String index() {

		return "Hello";
	}

	@RequestMapping("/selectUser")
	public String selectUser(User user) {
		List<User> users = userService.selectUser(user);

		return "Hello";
	}

	@RequestMapping("/insertUser")
	public String insertUser(User user) {
		userService.insertUser(user);

		return "Hello";
	}
}
```
## 自动生成
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Myspringboot001Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		SpringApplication.run(Myspringboot001Application.class, args);
	}
}
```
## pom.xml文件
```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.5.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.test</groupId>
	<artifactId>myspringboot-001</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>myspringboot-001</name>
	<packaging>war</packaging>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<scope>provided</scope>
		</dependency>
		<!-- <dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency> -->
		<!-- tomcat 的支持. -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.0.0</version>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.30</version>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>	
</project>
```
