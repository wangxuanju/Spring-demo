###  参考网易某课程整理的代码
IOC容器= ApplicationContext(代码中表达）
        org.springframework.context
        spring-context
### 初始化
Web.xml中
```java
<context-param>
<param-name>contextConfigLocation</parm-name>
<param-value>
    classpath:application-context.xml</param-value>
</context-param>

<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener
</listener-class>
</listener>

ApplicationContext context = new ClassPathXmlApplicationContext(“application-context.xml”);
//文件系统中(文件的绝对或相对路径)
ApplicationContext context = new FileSystemXmlApplicationContext(“/home/user/application-context.xml”)
```
### Bean定义
```java
EG <bean id=”screwDriver” class=”com.netease.course.ScrewDriver”></bean>
```
### Bean的使用
```java
//初始化容器
ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//获取对象
ScrewDriver screwDriver = context.getBean("screwDriver",ScrewDriver.class);
//使用对象
screwDriver.use();
```
### 什么是Bean的作用域？有效范围
singleton(默认作用域)                                        request session
Prototype（每次引用都创建一个新的实例）               global  session application
（剩下四种是应用于web场景）

### Bean生命周期回调
```java
创建-申请资源
//可以实现一个接口

Public interface InitializingBean{
    Void afterPropertiesSet()throws Exception;
}

<bean id=“screwDriver” class=”com.netease.course.ScrewDriver” init-method=”init”></bean>

public class ScrewDriver{
Public void init(){
        System.out.prinln(“Init screwDriver”);
}
}
销毁-释放资源

//可以实现一个接口
Public interface DisposableBean{
    Void destroy()throws Exception;
}

<bean id=“screwDriver” class=”com.netease.course.ScrewDriver” init-method=”init” destroy-method=”cleanup”></bean>

public class ScrewDriver{
Public void cleanup(){
        System.out.prinln(“cleanup screwDriver”);
}
}
```

### IOC
定义接口
实现接口
以接口为基础注入

依赖注入方式：基于构造函数(强依赖)、基于setter方法（可选依赖）
Public class ScrewDriver{
Private Header header;
Public ScrewDriver(Header header){
    This.header = header;
}
}

Public class ScrewDriver{
Private Header header;
Public setHeader(Header header){
    This.header = header;
}
}

## 依赖注入
基本类型（int,Sting）
集合
Bean
配置文件
当依赖很多的情况下注入,所以提供了自动装配的机制（Autowiring）

### 自动装配
byname:根据Bean名称
byType:根据Bean类型
constructor：构造函数，根据类型


### 通过Annotation配置
```java
@Controller
@RequestMpping(value=”/hello”)
Public class HelloController{
@RequestMapping(value=”/spring”)
Public void spring(HttpServletResponse response)throws IOException{
response.getWriter().write(“Hello, Spring Web”);
}
```

配置用XML(繁琐，代码独立)还是Annotation（简洁分散，代码耦合），实际开发过程中一般混合用


### Annotation
@Component:定义Bean
@Value:properties注入
@Autowired&@Resource:自动装配依赖
@PostConstruct&@PreDestroy：生命周期回调
