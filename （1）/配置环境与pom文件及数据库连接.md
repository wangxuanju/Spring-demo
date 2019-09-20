# 配置环境
## IDEA快速创建SpringBoot
new Project---Spring Initializr---Group/Artifact---选取响应的Dependencies(如web--web)---自动生成配置文件和相应的类
## IDEA创建SSM项目（是否用模板）
new Project---Maven---Create from archetype---maven-archetype-quickstart---GroupId/ArtifactId---pom.xml
### 两种模板的差异
```java
常用archetype
一、maven-archetype-quickstart
默认的Archetype,基本内容包括：
一个包含junit依赖声明的pom.xml
src/main/java主代码目录及一个名为App的类
src/test/java测试代码目录及一个名为AppTest的测试用例

二、maven-archetype-webapp
一个最简单的Maven war项目模板，当需要快速创建一个Web应用的时候可以使用它。生成的项目内容包括：
一个packaging为war且带有junit依赖声明的pom.xml
src/main/webapp/目录
src/main/webapp/index.jsp文件
src/main/webapp/WEB-INF/web.xml文件
```
## IDEA创建包含多个子项目的文件
new Project---Maven---GroupId/ArtifactId--完成---删掉自动生成的src目录；

new Module---Maven---GroupId/ArtifactId--完成(此过程为在父Module下建立子Module)
```java
父Module的pom.xml文件写法（举例）
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.imooc</groupId>
    <artifactId>oa</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    
    //父Module下的三个子Module(关键点）
    <modules> 
        <module>oa_dao</module>
        <module>oa_biz</module>
        <module>oa_web</module>
    </modules>
    
    <properties>
        <spring.version>4.0.2.RELEASE</spring.version>
    </properties>
</project>
```
# 写好Pom.xml文件
```java 总体原则是需要哪个jar包就导入到Pom.xml文件中
除了必备的SSM需要的包以外，常用的还有MySQL数据驱动(mysql-connector-java）、日志文件（log4j、slf4j）、Junit、JSTL标签等
<dependencies>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
                  <version>5.1.40</version> //明确指出使用的版本号
	</dependency>
         
         <dependency>
                  <groupId>log4j</groupId>
		<artifactId>log4j</artifactId>
                  //如果前面<properties></properties>标签中定义了<log4j-version>1.2.17</log4j-version>
                  <version>${log4j.version}</version>
         </dependency>
         .....    
  </dependencies>       
       
必要情况下，不仅指定版本号,还指定<scope>（如果前面已经定义了版本号则只指定<scope>）
 //<scope>provided</scope> //一个maven项目中，如果存在编译需要而发布不需要的jar包，可以用scope标签，值设为provided
 //<scope>runtime</scope> //runtime 依赖在运行和测试系统的时候需要
 //<scope>test</scope> //test范围依赖 在一般的编译和运行时都不需要，它们只有在测试编译和测试运行阶段可用。
```
## 导入常用的依赖jar包
以后添加

# 数据库如何连接
```java
两种图形化数据库MySQL Work Bench与Navicat的比较：
MySQL Work Bench可以直接键入语句；
而Navicat需要与MySQL command配合使用，在命令行键入语句或者在Navicat创建语句。

user database;显示数据库列表
use wenda；使用wenda数据库
show wenda;显示wenda数据库中有哪些表
show columns from user;显示user表的内容
```
application.properties文件
```java 
spring.mvc.view.suffix=.jsp
spring.mvc.view.prefix=/WEB-INF/jsp/

//连接的是databases中的wenda数据库
spring.datasource.url=jdbc:mysql://localhost:8080/wenda
spring.datasource.username=wang // MySQL数据库的用户名
spring.datasource.password=wang // MySQL数据库的密码

spring.datasource.driver-class-name=com.mysql.jdbc.Driver //驱动名称
mybatis.mapper-locations=classpath:com/example/gerenxuexi999/dao/*.xml
```
