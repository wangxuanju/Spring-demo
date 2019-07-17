## AOP的优点
代码重用；

业务逻辑与非业务逻辑的解耦。
## AOP术语
Aspect：日志，安全等功能

Join point：函数执行或者属性访问

Advice:在某个函数执行点上要执行的切面功能

Pointcut:匹配横切目标函数的表达式
## Advice类型
Before:函数执行之前

After returning:函数正常返回之后

After throwing：函数抛出异常之后

After finally：函数返回之后

Around:函数执行前后

## Spring AOP
非完整AOP实现

整合AOP与IoC

AspectJ(更完整的AOP实现）
## Spring AOP 两种不同的实现：
XML schema-based AOP

@AspectJ annotation-based AOP
## @AspectJ与Schema的区别
@AspectJ：配置分散，兼容AspectJ（推荐）

Schema:配置集中
## @AspectJ AOP
aspectJweaver.jar

配置文件中：<aop:aspectJ-autoproxy/>

### 定义Aspect
<bean id=”loggingAsepct” class=”com.netease.course.LoggingAspect”></bean>

Import org.aspectj.lang.annotation.Aspect;
@Aspect
Public class LoggingAspect{

}

### 定义Pointcut
@Pointcut(“execution(* com.netease.course.Caculator.*(..))”)
Private void arithmetic(){}

### Pointcut表达式
designator (modifiers? return-type declaring-type? name (param) throws?)
(execution,within)(public,private)(返回类型，*)(包名，类名)（函数名，*）（参数列表：（）无惨，（）任意参数）（异常类型）


### Pointcut示例
所有的public函数 
```java
execution(public **(..)) 
private void publicMethod()
```
所有DAO模块中的public函数  
```java
execution(public * com.netease.dao *.*(..))
private void publicDaoMethod()
```
所有以save开头的函数 
```java
execution(* save*(..))
private void saveMethod()
```
所有以save开头的Public函数 
```java
exectution(publciMehod()&&saveMehod())
private void publicSaveMethod()
```
### 定义Advice
```java
@Before(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(){

}
@Before(“execution(* com.netease.course.Caculator.*(..))”)
Public void doLog(){

}
//函数返回之后
@AfterReturning(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(){
}
@AfterThrowing(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(){

}
@After(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(){
}
```
### Advice参数
```java
//函数上下文信息
@Before(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(JoinPoint jp){
    System.out.println(jp.getSignature()+”,”+jp.getArgs());
}

@Around(“com.netease.course.LoggingAspect.arithmetic()”)
Public void doLog(ProceedingJoinPoint pjp){
System.out.println(“start method:”+pjp.toStirng())

Object retVal = pjp.proceed();
System.out.println(“stop method:”+pjp.toStirng())
Return retVal;

}

//返回值
@AfterRturning(
pointcut=”com.netease.course.LoggingAspect.arithmetic()”,
returning=”retVal”)
public void doLog(Object retVal){

}

//异常
@AfterThrowing(
Pointcut=”com.netease.course.LoggingAspect.arithmetic()”,
Throwing=”ex”)
Public void doLog(IllegalArgumentException ex){

}


//目标函数参数
@Before(“com.netease.course.LoggingAspect.arithmetic()&&args(a,..)”)
Public void doLog(JoinPoint jp,int a){

}
```
## Schema-based AOP
### 定义Aspect
```java
<aop:config>
    <aop:aspect id="loggingAspect" ref="loggingBean">
        ...
    </aop:aspect>
</aop:config>

<bean id="loggingBean" class="com.netease.course.LoggingAspect"></bean>
```
### 定义Pointcut
```java
<aop:config>
    <aop:pointcut id="arithmetic" 
    expression="execution(* com.netease.course.Caculator.*(..))"/>
</aop:config>

<aop:config>
    <aop:pointcut id="arithmetic"
    expression="com.netease.course.LoggingAspect.arithmetic()"/>
</aop:config>

<aop:config>
    <aop:aspect id="loggingAspect" ref="loggingBean">
        <aop:pointcut id="arithmetic"
        expression="execution(* com.netease.course.Caculator.*(..))"/>
    </aop:aspect>
</aop:config>
```
### 定义Advice
```java
<aop:aspect id="loggingAspect" ref="loggingBean">
    <aop:before pointcut-ref="arithmetic" method="doLog"/>
</aop:aspect>

<aop:aspect id="loggingAspect" ref="loggingBean">
    <aop:before
        pointcut="execution(* com.netease.course.Caculator.*(..))"
        method="doLog"/>
</aop:aspect>

<aop:aspect id="loggingAspect" ref="loggingBean">
    <aop:after-returning pointcut-ref="arithmetic" returning="reVal" method="doLog"/>
</aop:aspect>

<aop:aspect id="loggingAspect" ref="loggingBean">
    <aop:after-throwing pointcut-ref="arithmetic" throwing="ex" method="doLog"/>
</aop:aspect>
```
