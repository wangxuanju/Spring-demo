### 自动装配及Annotation
#### 自动装配
   byname:根据Bean名称
   byType:根据Bean类型
   constructor：构造函数，根据类型
依赖注入

配置用XML(繁琐，代码独立)还是Annotation（简介，代码耦合），一般混合用

#### Annotation
@Component:定义Bean
@Value:properties注入
@Autowired&@Resource:自动装配依赖
@PostConstruct&@PreDestroy：生命周期回调
