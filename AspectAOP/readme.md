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
## @AspectJ与Schema的区别
@AspectJ：配置分散，兼容AspectJ（推荐）

Schema:配置集中
