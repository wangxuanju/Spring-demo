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
