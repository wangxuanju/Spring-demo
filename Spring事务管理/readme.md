## Spring事务管理
统一的事务编程模型

编程式事务及声明式事务（AOP）

### 事务管理
```java
public interface PlatformTransactionManager{
	TransactionStatus getTransaction(TransactionDefinition definition)throws TansactionException;
	void commit(TransactionStatus status)throws TransactionException;
	void commit(TransactionStatus status)throws TransactionException;
}
(org.springframework.transaction)
```


### 事务管理器
TransactionDefinition
    getName：事务名称
    getIsolationLevel:隔离级别
    getPropagationBehavior：传播行为
    getTimeout：超时时间
    isReadOnly：是否只读事务

### TransactionStatus
isNewTransaction：是否是新的事务
hasSavepoint：是否有savepint（诊断，NESTED）
isCompleted:是否已完成
isRollbackOnly：事务结果是否是rollback-only

setRollbackOnly:设置事务rollback-only(TransactionTemplate)


### 隔离级别
ISOLATION_READ_UNCOMMITTED:读未提交
ISOLATION_READ_COMMITTED：读提交
ISOlATION_REPEATABLE_READ：重复读
ISOLATION_SERIALIZABLE：串行化
ISOlATION_DEFAULT：默认

### 传播行为
class DaoA{
	public void transaction() {
		
	}
	
}
class DaoB{
	public void transactionB() {
		daoA.transaction();
	}
}


PROPAGATION_MANDATORY:必须在一个事务中运行，不存在则跑异常
PROPAGATION_NEVER：不应该在事务中运行，存在则抛异常
PAROPGATION_NOT_SUPPORTED：不应该在事务中运行，存在则挂起
PROPAGATION_SUPPORTS:不需要事务，有则在事务中执行

PROPAGATION_REQUIRED:
	必须在事务中执行，如果不存在，则启动新事物
	内部事务会影响外部事务
PROPAGATION_NESTED：
        必须在事务中执行，如果不存在，则启动新事物
        事务之间相互不影响(savepoints)
PROPAGATION_REQUIRES_NEW:
	必须在新事物中执行，挂起当前事务
	独立physics事务

	
	
## 声明式事务
添加shema
xmlns:aop:=
xmlns:tx=
...

定义事务管理器
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTansactionManager">
    <property name="dataSource" ref="dataSource"/>    
</bean>

定义事务advice
<tx.advice:id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
        <tx:method name="get*" read-only="true"/>
        <tx:method name="*"/>
    </tx:attributes>

</tx:advice>

定义Pointcut
<aop:config>
    <aop:pointcut id="daoOperation" expression="execution(* com.netease.AccountDao.*(..))"/>
    <aop:advisor advice-red="txAdvice" point-ref="daoOperation"/>
</aop:config>


配置<tx:method/>
    name:匹配的函数名称，支持*匹配
    propagation：事务传播行为
    isolation:事务隔离级别
    timeout:超时
    read-only：是否只读事务
    rollback-for:触发回滚的异常，逗号分隔
    no-rollback-for：不触发回滚异常，逗号分隔
    
    
@Tansactional(注释来配置)
<tx:annotation-driven transaction-manager="txManager"/>
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTansactionManager">
    <property name="dataSource" ref="dataSource"/>    
</bean>
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destory-method="close">
    <property name="driverClassName " value="${jdbc.driverClassName}"/>
    <property name="url " value="${jdbc.url}"/>
    <property name="username " value="${jdbc.username}"/>
    <property name="password " value="${jdbc.password}"/>
</bean>
<context:property-placeholder location="db.properties">



@Transactional
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public boolean deleteClusterByClusterId(String clusterId) {
	
}

@Transactional
    value:使用的TransactionManager
    propagation:事务传播行为
    isolation：事务隔离级别
    timeout：超时
    readOnly：是否只读事务
    rollbackFor：触发回滚的异常类对象数组
    rollbackForClassName：触发回滚的异常类名称数组
    noRollbackFor：不触发回滚的异常类对象数组
    noRollbackForClassName:不触发回滚的异常类名称数组
    
  
    
## 编程式事务
TransactionTemplate
PlatformTransactionManager的实现

定义TransactionTemplate   
public class SimpleService implements Service{
	private final TransactionTemplate transactionTemplate;
	public SimpleService(PlatformTransactionManager transactionManager) {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		
		this.transactionTemplate.setIsolationLevel(TransactionDefination.ISOLATION_READ_UNCOMMITTED);
		this.transactionTemplate.setTimeout(30);
	}
	
}

使用TransactionTemplate
public Object someMethod() {
	return transactionTemplate.execute(new TransactionCallback() {
		public Object doInTransaction(TransactionStatus status) {
			updateOperation1();
			return resultOfUpdataOperation2();
		}
	});
}


public Oject someMethodWithoutRsult() {
	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		protected void doInTransactionWithoutResult(TransactionStatus status) {
			updateOperation1();
			updateOperation2();
		}
	}	
			);
}

//有时候抛出异常
transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	protected void doInTransactionWithoutResult(TransactionStatus status) {
		try {
		updateOperation1();
		updateOperation2();
	}catch(SomeBusinessExeption ex) {
		status.setRollbackOnly();
	}
}	
		);

第二种方式
PlatformTransactionManager的实现
DefaultTransactionDefinition def=new DefaultTransactionDefination();
def.setName("SomeTxName");
def.setPropagationBehavior(TransactionDefination.PROPAGATION_REQUIRED);

TransactionStatus status=txManager.getTransaction(def);
try {
	//
}catch(MyException ex) {
	txManager.rollback(status);
	throw ex;
}
txManager.commit(status);


## 编程式事务（事务操作少）与声明式事务（事务操作多）的区别
