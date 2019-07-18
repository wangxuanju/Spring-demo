## Spring JDBC
#### 有关数据访问的几个概念

DAO（数据访问对象）----包含数据访问相关的接口

ORM（对象关系映射）

### 数据访问：
连接参数

打开连接

声明SQL语句以及参数

执行SQL，并循环访问结果

执行业务

处理异常

关闭连接，语句以及结果集

### DataSource
驱动类名

连接地址

用户名

密码
```java
//简化的方式依赖注入
<bean id=”dataSource” class=”org.apach.commons.dbcp.BasicDataSource” destroy-method=”close”>
    <property name=”driverClassname” value=”${jdbc.driverClassname}”/>
    <property name=”url” value=”${jdbc.url}”/>
    <property name=”username” value=”${jdbc.username}”/>
    <property name=”password” value=”${jdbc.password}”/>
</bean>
    <context:property-placeholder location=”db.properties”/>

//依赖注入
<bean id=”headerProperties”
    Class=”org.springframework.beans.factory.config.PropertyPlaceholderConfigurer”>
    <property name=”location” value=”classpath:db.properties”/>
</bean>
```

### JdbcTemplate
```java
Int rowCount = this.jdbcTemplate.queryForObject(“select count(*) from user”,Integer.class);

Int countOfNameJoe= this.jdbcTemplate.queryForObject (“select count(*) from user where first_name=?”,Integer.class,”Joe”);

String lastName = this.jdbcTemplate.queryForObject(“select last_name from user where id=?”,New Object[]{1212L},String.class);

//插入一些语句
This.jdbcTemplate.update(
    “insert into user(first_name,last_name) value(?,?)”,”Meimei”,”Han”);
This.jdbcTemplate.update(
    “update user set last_name=? where id=?”,”Li”,5276L);
This.jdbcTemplate.update(
    “delete from user where id=?”,Long.valueOf(userId));
//创建表和删除表的功能
This.jdbcTemplate.execute(“create table user(id integer,first_name varchar(100),last_name varchar(100))”);




User user = this.jdbcTemplate.queryForObject(“select first_name,last_name from user where id=?”,
    New Object[]{1212L},
    New RowMpper<User>(){
        Public User mapRow(ResultSet rs,int rowNum) throws SQLException{
            User user=new User();
    user.setFirstName(rs.getString(“first_name”));
    user.setLastName(rs.getString(“last_name”));
        return user;
    }
}）；


List<User> users = this.jdbcTemplate.query(
    “select first_name,last_name from user”,
    New RowMapper<User>(){
    Public User mapRow(RsultSet rs,int rowNum)throws SQLException{\
        User user=new User();
            user.setFirstName(rs.getString(“first_name”));
            user.setLastName(rs.getString(“last_name”));
        return user;
    }
}）；



Public class JdbcExampleDao implements ExampleDao{
    Private JdbcTemplate jbdctemplate;
    Public void setDataSource(DataSoruce dataSource){
        This.jdbcTemplate = new JdbcTemplate(dataSource);

}
//…DAO接口实现
}
```


### XML来配置DAO
<bean id=”exampleDao” class=”com.netease.course.JdbcExampleDao”>
    <property name=”dataSource” ref=”dataSource”/>
</bean>

<bean id=”dataSource” class=”org.apach.commons.dbcp.BasicDataSource” destroy-method=”close”>
    <property name=”driverClassname” value=”${jdbc.driverClassName}”/>
    <property name=”url” value=”${jdbc.url}”/>
    <property name=”username” value=”${jdbc.username}”/>
    <property name=”password” value=”${jdbc.password}”/>
</bean>
    <context:property-placeholder location=”db.properties”/>




### 通过Annotation来配置DAO
       @Repository
Public class JdbcExampleDao implements ExampleDao{
    Private JdbcTemplate jbdctemplate;
    @Autowired
    Public void setDataSource(DataSoruce dataSource){
        This.jdbcTemplate = new JdbcTemplate(dataSource);

}
//…DAO接口实现
}




### NamedParameterJdbcTemplate
```java
//不再通过问号，而是通过命名的方式定义参数的方法
Private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
Public void setDataSource(DataSource dataSource){
    This.namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
}
Public int countOfUserByFirstName(String firstName){
    String sql = “select count(*) from user where first_name =:first_name”;
    Map<String,Stirng> namedParameters = Collections.singtonMap(“first_name”,firstName);
    Return this.namedParameterJdbcTemplate.queryForObject(sql,namedParameters,Integer.class);
}


//接口
queryForObject(String sql,Map<String,?>paramMap,RowMapper<T> rowMapper)
queryFor(Object(String sql,SqlParameterSource paramSource,Class<T> requiredType)



SqlParameterSource

BeanPropertySqlParameterSource

Private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
Public void setDataSource(DataSource dataSource){
    This.namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
}
Public int countOfUserByFirstName(String firstName){
   String sql = “select count(*) from user where first_name =:first_name”;
    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(exampleUser);
Return this.namedParameterJdbcTemplate.queryForObject(sql,namedParameters,Integer.class);
}
```


### 异常处理
SQLException（无法连接数据库、表询有语法错误、表或列不存在）

DateAccessException
