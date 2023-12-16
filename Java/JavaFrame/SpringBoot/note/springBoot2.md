# 一、SpringBoot介绍

---

## 1.什么是springBott

 https://spring.io

## 2.为什么用springBoot

<img src="images/springBoot.png" alt="为什么要用springBoot">

能快速创建出生产级别的spring应用

### 2-1.springBoot的优点

- Create stand-alone Spring applications
  
- 创建独立spring应用
  
- Embed Tamcat, Jetty or Undertow directly (no need to play WAR files)
  
- 内嵌web服务器
  
- Provide opinionated 'starter' dependencies to simplify your build configuration
  
- 自动starter依赖，简化构建配置
  
- Automatically configure Spring and 3rd party libraries whenever possible
  
- 自动化配置spring以及第三方功能
  
- Provide production-ready features such as metrics, health checks, and externalized configuration
  
- 提供生产级别的监控，健康检查以及外部化配置
  
- Absolutely code generation and no requirement for XML configuration

  - 无代码生成，无需编写XML

  > springBoot是整合spring技术栈的一站式框架
  >
  > springBoot是简化spring技术栈的快速开发脚手架

### 2-2.springBoot的缺点

- 版本帝，迭代快，需要时刻关注变化
- 封装太深，内部原理复杂，不容易精通

## 3.时代背景

### 3-1.微服务

James Lewis and Martin Fowler(2014)提出微服务完整概念。https://martinfowler.com/microservices/

>  In short, the microservice architectural style is an approach to developing a single application as a **suite of small services**, each **running in its own process** and communicating with lightweight mechanisms, often an HTTP resource API. These services are **built around business capabilities** and **independently deployable** by fully automated deployment machinery. There is a **bare minimum of centralized management** of these services, which may be written in different programming languages and use different data storage technologies. 

- 微服务是一种架构风格
- 一个应用拆分成一组小型服务
- 每个服务运行在自己的进程内，也就是可以独立部署和升级
- 服务之间使用轻量级HTTP交互
- 服务围绕业务功能进行拆分
- 可以由全自动部署机制独立部署
- 去中心化、服务自治、服务可以使用不同的语言、不同的储存技术

### 3-2.分布式

<img src="images/分布式.png" alt="什么是分布式架构">

**分布式的困难：**

- 远程调用
- 服务发现
- 负载均衡
- 服务容错
- 配置管理
- 服务监控
- 链路追踪
- 日志管理
- 任务调度
- ……

**分布式的解决：**

- springBoot + springCloud

  <img src="images/分布式的解决.png" alt="分布式问题的解决办法">

### 3-3.云原生

原生应用如何上云：**Cloud Native**

**上云的困难：**

- 服务自愈
- 弹性伸缩
- 服务隔离
- 自动部署
- 灰度发布
- 流量治理
- ……

***上云的解决：***

<img src="images/大厂学院.png" alt="云原生的技术栈">

# 二、SpringBoot基础入门

需求：浏览发送 /hello 请求，响应 Hello, SpringBoot2

## 1.编写HelloWorld

### 1-1.创建maven工程

### 1-2.引入依赖

```xml
<parent>
    <artifactId>spring-boot-starter-parent</artifactId>
    <groupId>org.springframework.boot</groupId>
    <version>2.3.4.RELEASE</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.6.4</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

### 1-3.创建主程序

```java
//主程序类
//@SpringBootApplication：标注这是一个springBoot应用
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```

### 1-4.编写业务

```java
//@Controller
//@ResponseBody
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle1() {
        return "Hello SpringBoot2!";
    }
}
```

### 1-5.测试

直接运行main方法

### 1-6.简化配置

application.xml

```properties
server.port=8806
```

### 1-7.简化部署

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.6.4</version>
        </plugin>
    </plugins>
</build>
```

将项目打成jar包，直接在目标服务器内运行即可。

> 注意点：用DOS窗口运行程序时，需要取消掉DOS窗口的快速编辑模式

## 2.了解自动配置原理

### 2-1.springBoot特点

#### 2-1-1.依赖管理

- 依赖管理

  ```xml
  依赖管理作用
  <parent>
      <artifactId>spring-boot-starter-parent</artifactId>
      <groupId>org.springframework.boot</groupId>
      <version>2.3.4.RELEASE</version>
  </parent>
  
  其父项目：
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>2.3.4.RELEASE</version>
  </parent>
  ```

> 几乎声明了所有开发中常用的依赖的版本号

- 开发导入starter场景启动器

  - 1.会遇到很多 starter-boot-starter-*

  - 2.只需要引入 starter，这场景的所有常规需要的依赖都可以自动引入

  - 3.springBoot所有支持的场景 --> 见官方文档

  - 4.见到 *.spring-boot-starter，是第三方为我们提供的简化开发的场景启动器

  - 5.所有的场景启动器的最底层的依赖：

    ```xml
    <dependency>
    	<groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <version>2.3.4RELEASE</version>
        <scope>compile</scope>
    </dependency>
    ```

- 无需关注版本号，自动版本仲裁

  - 1.引入依赖默认都可以不写版本号
  - 2.当引入非版本仲裁的jar包，一定要写版本号

- 可以修改版本号

  - 1.查看 spring-boot-dependencies 里面规定当前依赖的版本用的 key

  - 2.在当前项目里面重写配置

    ```xml
    <properties>
    	<mysql.version>5.1.43</mysql.version>
    </properties>
    ```

#### 2-1-2.自动配置

- 自动配好 Tomcat

  - 引入Tomcat依赖

  - 配置Tomcat

    ```xml
    <dependency>
    	<groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <version>2.3.4RELEASE</version>
        <scope>compile</scope>
    </dependency>
    ```

- 自动配好 SpringMVC

  - 引入 SpringMVC 全套组件
  - 自动配好 SpringMVC 常用组件（功能）

- 自动配好 Web 常见功能，如字符编码问题

  - SpringBoot 帮我们配置好了所有web开发的常见场景

- 默认的包结构

  - 主程序所在包一起下面的所有子包及其所有子包里面的组件都会被默认扫描进来
  - 无需以前的包扫描配置
  - 想要改变扫描路径，可以通过 ***@SpringBootApplication(scanBasePackages="com.MrLi")*** 或者 ***@ComponentScan*** 指定扫描路径

  ```java
  @SpringBootApplication
  等同于↓
  @SpringBootConfiguration
  @EnableAutoConfiguration
  @ComponentScan
  ```

- 各种配置拥有默认值

  - 默认配置文件最终都是映射到 MultipartProperties
  - 配置文件的值最终都会绑定到每个类上，这个类会在容器中创建对象

- 按需求加载所有自动配置项

  - 非常多的starter
  - 引入了相对应的场景，这个场景相对应的自动配置才会开启
  - SpringBoot所有的自动配置功能都在 spring-boot-autoconfigure 包里面

- ……

### 2-2.容器功能

#### 2-2-1.组件添加

##### 1.@Configuration

- 基本使用

  ```java
  /*
  1、配置类里面使用 @Bean 标注在方法上给容器注册组件，默认是单实例
  2、配置类本身也是组件
  3、proxyBeanMethods：代理bean的方法
  4、外部无论对配置类中的这个组件注册方法调用多少次获取都是之前注册容器中的单实例对象
  5、方法返回的值，就是组件在容器中的实例
   */
  @Configuration(proxyBeanMethods = true) //将这个类声明为 SpringBoot 的配置类 == 配置文件
  public class MyConfig {
      @Bean//往容器中添加组件，以方法名作为组件的id
      public User user1() {
          return new User(1, "user1");
      }
  
      @Bean("smith")
      public User user2() {
          return new User(2, "user2");
      }
  }
  ```

  

- **Full模式域Lite模式**

  - 示例
  - 最佳实战
    - 配置类组件之间没有依赖关系用Lite模式加速容器启动，减少判断
    - 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式

##### 2.@Bean、@Component、@Controller、@Service、@Repository

##### 3.@ComponentScan、@Import

```java
@Import({User.class, DBHelper.class})//给容器中自动创建出这两个类型的组件
@Configuration(proxyBeanMethods = true) //将这个类声明为 SpringBoot 的配置类 == 配置文件
public class MyConfig {
    @Bean//往容器中添加组件，以方法名作为组件的id
    public User user1() {
        return new User(1, "user1");
    }

    @Bean("smith")
    public User user2() {
        return new User(2, "user2");
    }
}
```

##### 4.@Conditional

条件装配：当满足了Conditional指定的条件，则进行组件注入

<img src="images/@Conditional.png" alt="@Conditional注解及其派生注解">

#### 2-2-2原生配置文件引入

##### 1.@ImportResource

可以允许导入spring的配置文件

```java
@Import({User.class, DBHelper.class})//给容器中自动创建出这两个类型的组件
@Configuration(proxyBeanMethods = true) //将这个类声明为 SpringBoot 的配置类 == 配置文件
@ImportResource("classpath:beans.xml")//可以允许导入spring的配置文件
public class MyConfig {
    @Bean//往容器中添加组件，以方法名作为组件的id
    public User user1() {
        return new User(1, "user1");
    }

    @Bean("smith")
    public User user2() {
        return new User(2, "user2");
    }
}
```

#### 2-2-3.配置绑定

如何使用Java读取到properties文件中的内容，并且把它封装到JavaBean之中，一共随时使用

原生方式：

```java
public class getProperties {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties pre = new Propertis();
        pres.load(new FileInputStream("a.properties"));
        Enumeration enuml = pre.prepertyNames();//获取配置文件名
        while(enuml.hasMoreElements()) {
            String strKey = (String) enuml.nextElement();
            String strValue = pre.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
            //封装到JavaBean
        }
    }
}
```

##### 1.@ConfigurationProperties

配置属性注解，一般和其他注解搭配使用。

##### 2.@EnableConfigurationProperties + @ConfigurationProperties

```java
//@Component//声明其为容器中的组件
@ConfigurationProperties(prefix = "mycar")
public class Car {

/*
1、开启Car的配置绑定功能
2、将Car这个组件自动注册到容器中
*/
@EnableConfigurationProperties(Car.class)
public class MyConfig {
```

##### 3.@Component + @ConfigurationProperties

```java
/*
只有在容器中的组件，才能使用SpringBoot提供的强大功能
 */
@Component//声明其为容器中的组件
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private String price;

    public Car() {
    }

    public Car(String brand, String price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
```

### 2-3.自动配置原理入门

#### 2-3-1.引导加载自动配置类

```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {}
```

##### 1.@SpringBootConfiguration

@Configuration,代表当前类是一个注解类

##### 2.@ComponentScan

指定扫描的包，属于spring注解

##### 3.@EnableAutoConfiguration

```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {}
```

- @AutoConfigurationPackage

  ```java
  /*
  1、利用Register给容器导入一系列的组件
  2、将指定的一个包下的所有组件导入进来 --> 即MainApplication所在的包下
  */
  @Import({Registrar.class})	//负责给容器导入一个组件
  public @interface AutoConfigurationPackage {}
  ```

- @Import({AutoConfigurationImportSelector.class})

  - 1、利用getAutoConfigurationEntry(annotationMetadata);给容器中批量导入一些组件

  - 2、调用List<String> configurations = getCandidateConfigurations(annotationMetadata,attributes);获取到所有需要导入到容器中的配置类。

  - 3、利用工厂加载 Map<String,List<String>> loadSpringFactories(@Nullable Classloader classLoader);得到所有的组件。

  - 4、从 META-INF/spring.factories 位置加载一个文件；

    ​	默认扫描当前系统里面所有 META-INF/spring.factories 位置的文件

    ​	spring-boot-autoconfigure-2.3.4.RELEASE.jar包里面也有 META-INF/sping.factories

  > 在文件里写死了 spring-boot 一启动就需要给容器中加载的所有配置类
  >
  > 配置类位置：spring-boot-autoconfigure-2.3.4.RELEASE.jar/META-INF/sping.factories

<img src="images/configurations.png" alt="configurations">

#### 2-3-2.按需开启自动配置项

虽然127个场景的所有自动配置启动的时候默认全部加载，但是依据条件装配规则(@Conditional)，最终会按需配置.

#### 2-3-3.修改默认配置

```java
@Bean
@ConditionalOnBean(MultipartResolver.class) //容器中有这个类型的组件
@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) //容器中没有这个名字 multipartResolver 的组件
public MultipartResolver multipartResolver(MultipartResolver resolver) {
    //给@Bean标注的方法传入了对象参数，这个参数的值就会从容器中找
    //Detect if the user has created a MultipartResolver but named it incoreectly
    return resolver;
}
```

> SpringBoot默认会在底层配置好所有的组件，但是如果用户自己配置好了，就以用户的优先
>
> 总结：
>
> - SpringBoot先加载所有的自动配置类 xxxxAutoConfiguration
> - 每个自动配置类按照条件进行生效，而且生效的类默认都会绑定配置文件指定的值 --> xxxProperties里面拿 --> xxxProperties和配置文件进行了绑定
> - 生效的配置类就会给容器中装配很多组件
> - 只要容器中有这些组件，相当于这些功能就都有了
> - 定制化配置：
>   - 用户直接自己 @Bean 替换底层的组件
>   - 用户去看这个组件是获取得配置文件里面的什么样的值就去修改什么样的值
>
> xxxAutoConfiguration ---> 组件 ---> xxxxProperties里面拿值 ---> application.properties
>
> ```java
> @Bean
> @CconditionalOnMissingBean
> public CharacterEncodingFilter characterEncodingFilter(){}
> ```

#### 2-3-4.最佳实践

- 引入场景依赖
  - https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
- 查看自动配置了哪些内容
  - 自己分析，引入场景对应的自动配置是否生效
  - 配置文件中开启 “debug=true” 的自动配置报告 --> Negative(不生效)/Positive(生效)
- 是否需要修改
  - 参照文档修改配置项
    - https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties
    - 自己分析，xxxProperties绑定了配置文件的哪些
  - 自定义加入或者替换组件
    - @Bean、@Component……
  - 自定义器 **xxxCustomizer
  - ……

### 2-4.开发小技巧

#### 2-4-1.Lombok

简化JavaBean方法的开发

- 1.引入依赖

  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
  </dependency>
  ```

- 2.安装lombok插件

  <img src="images/Lombok.png" alt="安装Lombok插件">

- 3.使用Lombok的注解

  ```java
  @Data //set、get方法
  @ToString //toString方法
  @NoArgsConstructor //无参构造器
  @AllArgsConstructor //有参构造器
  public class User {
      private Integer id;
      private String username;
  }
  ```

  

#### 2-4-2.Spring Initailizr

IDEA项目初始化向导

<img src="images/springInitializr.png" alt="">

#### 2-4-3.dev-tools

重新编译项目，自动重启

- 导入依赖

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
  </dependency>
  ```

> Ctrl + F9

# 三、SpringBoot核心功能

## 1.配置文件

### 1-1.properties

同以前的properties用法

### 1-2.yaml

#### 1-2-1.简介

YAML是"YAML Ain't Markup Language"（YAML不是一种标记语言）的递归缩写，在开发这种语言时，YAML的意思其实是"Yet Another Markup Language"(仍是一种标记语言)。

YAML非常适合用来做以数据为中心的配置文件。

#### 1-2-2.基本语法

- key: value;（kv之间有空格）
- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用tab，只允许空格
- 所进的空格数不重要，只要相同层级的元素左对齐就可
- "#" 表示注释
- 字符串无需加引号，如果要加，'' 和 "" 表示字符串内容，会被转义或不转义

#### 1-2-3.数据类型

- 字面量：单个的、不可再分的值（data、boolean、string、number、null）

  ```yaml
  k: v
  ```

- 对象：键值对的集合（map、hash、set、object）

  ```yaml
  #行内写法
  k: {k1:v1,k2:v2,k3:v3}
  #或
  k:
   k1: v1
   k2: v2
   k3: v3
  ```

- 数组：一组按次序排列的值（array、list、queue）

  ```yaml
  #行内写法
  k: [v1,v2,v3]
  #或者
  k:
   - v1
   - v2
   - v3
  ```

#### 1-2-4.案例

Person类

```java
@Component
@ConfigurationProperties(prefix = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String username;
    private Boolean boos;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] Interests;
    private List<String> animal;
    private Set<Double> salary;
    private Map<String, List<Pet>> allPets;
}
```

Pet类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Double weight;
}
```

YAML配置文件

```yaml
person:
  username: smith
  boss: true
  birth: 2001/1/1
  age: 21
  interests: [篮球, 足球]
  animal:
    - cat
    - dog
  salarys:
    - 10000
    - 10000
  pet:
    name: cat
    weight: 20
  allPets:
    sick:
      - {name: dog1, weight: 50}
      - name: cat1
        weight: 30
    height: [{name: dog2,weight: 50}, {name: cat2,weight: 30}]
```

### 1-3.配置提示

一般配置文件的绑定是没有提示的，但是可以添加以下依赖解决：

```xml
<configuration>
    <excludes>
        <exclude>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </exclude>
    </excludes>
</configuration>
```

## 2.Web开发

### 2-1.SpringMVC自动配置概览

Spring Boot provides auto-configuration for Spring MVC that **works well with most applications.(大多场景我们都无需自动配置)**

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
  - 内容协商视图解析器和BeanName视图解析器
- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))).
  - 静态资源（包括webjars）
- Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.
  - 自动注册 Converter, GenericConverter、Formatter
- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters)).
  - 支持 HttpMessageConverters
- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/spring-boot-features.html#boot-features-spring-message-codes)).
  - 自动注册 MessageCodeResolver（国际化使用）
- Static `index.html` support.
  - 静态 index.html 页支持
- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)).
  - 自动使用 ConfigurableWebBindingInitializer（DataBinder负责将请求数据绑定到JavaBean上）

> - If you want to keep those Spring Boot MVC customizations and make more [MVC customizations](https://docs.spring.io/spring/docs/5.3.13/reference/html/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.
>
>     **不用 @EnableWebMvc 注解，使用 @Configuration + WebMvcConfigurer 自定义规则**
>
> - If you want to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, and still keep the Spring Boot MVC customizations, you can declare a bean of type `WebMvcRegistrations` and use it to provide custom instances of those components.
>
>     **声明 WebMvcRegistration 改变没人底层组件**
>
> - If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`, or alternatively add your own `@Configuration`-annotated `DelegatingWebMvcConfiguration` as described in the Javadoc of `@EnableWebMvc`.
>
>     **使用 @EnableWebMvc + @Configuration + DelegatingWebMvcConfiguration 全面接管SpringMVC**

### 2-2.简单功能分析

#### 2-2-1.静态资源访问

##### 1.静态资源目录

类路径下：called /static (or /public or /resources or /META-INF/resources)

访问方式：当前项目根路径/ + 静态资源名

原理：静态映射/\**，当请求进来时，先去找**Controller方法**是否可以处理请求，如果Controller方法不能处理又会由**静态资源处理器处理**请求，如若静态资源处理器也无法处理请求便会报**404错误**

##### 2.静态资源访问前缀

默认情况下无前缀。

```yaml
spring:
  mvc:
    static-path-pattern: /res/**
```

访问方式：当前项目 + static-path-pattern + 静态资源名

##### 3.webjar

可以自动映射到jQuery、JavaScript之类的静态文件

https://www.webjars.org/

访问方式：http://localhost/webjars/jquery/3.5.1/jquery.js

#### 2-2-2.欢迎页支持

- 在静态资源路径下创建一个 index.html 页面
  - 可以配置静态资源路径
  - 但是不可以配置静态资源的访问前缀，否则将会不能默认访问 index.html 页面
- 创建能处理 /index 请求的controller

#### 2-2-3.自定义 Favicon

As with pther static resources, Spring Boot looks for a **`favicon.ico`** in the configured static content locations. If such a file is present, it is automatically used as the favicon of the application.

> 不可以配置静态资源的访问前缀，否则将会不能默认访问 **`favicon.ico`**

#### 2-2-4.静态资源管理配置原理

- springBoot启动默认加载 xxxAutoConfiguration类（自动配置类）

- springMVC功能的自动配置类 WebMvcAutoConfiguration，其是否生效：生效

  ```java
  @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
  @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
  @AutoConfigureOrder(-2147483638)
  @AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
  public class WebMvcAutoConfiguration {}
  ```

- 给容器中配了什么设置

  ```java
  @Configuration(
      proxyBeanMethods = false
  )
  @Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
  @EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class})
  @Order(0)
  public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {}
  ```

- 配置文件的相关属性(WebMvcProperties、ResourceProperties)和什么进行了绑定

  - WebMvcProperties --> spring.mvc
  - ResourceProperties --> spring.resources

- 资源处理的默认规则

  ```java
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      if (!this.resourceProperties.isAddMappings()) {
          logger.debug("Default resource handling disabled");
      } else {
          Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
          CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
          //webjars的规则
          if (!registry.hasMappingForPattern("/webjars/**")) {
              this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
          }
  
          String staticPathPattern = this.mvcProperties.getStaticPathPattern();
          if (!registry.hasMappingForPattern(staticPathPattern)) {
              this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
          }
  
      }
  }
  ```

  yaml禁用所有静态资源规则

  ```yaml
  resources:
  	add-mapping: false	#禁用所有静态资源规则
  ```

  静态资源路径默认的四个位置

  ```JAVA
  @ConfigurationProperties(
      prefix = "spring.resources",
      ignoreUnknownFields = false
  )
  public class ResourceProperties {
      private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
      private String[] staticLocations;
  }
  ```

- 欢迎页的处理规则

  - HandlerMapping（处理器映射）：保存了每一个handler可以处理的请求

  ```java
  @Bean
  public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
      WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(new TemplateAvailabilityProviders(applicationContext), applicationContext, this.getWelcomePage(), this.mvcProperties.getStaticPathPattern());
      welcomePageHandlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
      welcomePageHandlerMapping.setCorsConfigurations(this.getCorsConfigurations());
      return welcomePageHandlerMapping;
  }
  ```

  ```java
  WelcomePageHandlerMapping(TemplateAvailabilityProviders templateAvailabilityProviders, ApplicationContext applicationContext, Optional<Resource> welcomePage, String staticPathPattern) {
      if (welcomePage.isPresent() && "/**".equals(staticPathPattern)) {
          //要用欢迎页功能，请求路径必须是 /**
          logger.info("Adding welcome page: " + welcomePage.get());
          this.setRootViewName("forward:index.html");
      } else if (this.welcomeTemplateExists(templateAvailabilityProviders, applicationContext)) {
          //调用controller处理 /index
          logger.info("Adding welcome page template: index");
          this.setRootViewName("index");
      }
  
  }
  ```

> 配置类只有一个有参构造器
>
> - ResourceProperties resourceProperties：获取和 spring.resources 绑定的所有值的对象
> - WebMvcProperties mvcProperties：获取和 spring.mvc 绑定的所有值的对象
> - ListableBeanFactory beanFactory：spring的beanFactory
> - HttpMessageConverters：找到所有的 HttpMessageConverters
> - resourceHandlerRegistrationCustomizer：找到 资源处理器的自定义器
> - servletRegistrations：给应用注册Servlet、Filter等
>
> ```java
> //有参构造器所有参数的值都会从容器中确定
> public WebMvcAutoConfigurationAdapter(ResourceProperties resourceProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider, ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider, ObjectProvider<DispatcherServletPath> dispatcherServletPath, ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
>     this.resourceProperties = resourceProperties;
>     this.mvcProperties = mvcProperties;
>     this.beanFactory = beanFactory;
>     this.messageConvertersProvider = messageConvertersProvider;
>     this.resourceHandlerRegistrationCustomizer = (WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer)resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
>     this.dispatcherServletPath = dispatcherServletPath;
>     this.servletRegistrations = servletRegistrations;
> }
> ```

### 2-3.请求参数处理

#### 2-3-1.请求映射

##### 1.rest的使用和原理

- @xxxMapping

- Rest风格支持（使用Http请求方式动词来表示对资源的操作）

  - 以前：/getUser:获取用户、/deleteUser：删除用户、/editUser：修改用户、/saveUser：保存用户

  - 现在：/user：GET-获取用户，DELETE-删除用户，PUT-修改用户，POST-保存用户

  - 核心Filter：HiddenHttpMethodFilter

    - 用法：表单method=post，隐藏域_method=put
    - 需要在springBoot中手动开启

  - Rest原理（表单提交要使用REST的时候）

    - 表单提交会带上**_method=PUT**
    - 请求过来被**HiddenHttpMethodFilter**拦截
      - 请求是否正常，并且是**POST请求**
        - 获取到_method的值
        - 兼容以下请求：**PUT、DELETE、PATCH**
        - **原生的request(post)，经过包装模式requestWrapper重写了的getMethod方法，返回的是传入的值**
        - **过滤器链放行时使用wrapper，当后来的方法调用getMethod时其实是调用的是requestWrapper**
    - Rest使用客户端工具
      - 如PostMan直接发送Put、Delete等方式请求，无需Filter

    ```java
    @Bean
    @ConditionalOnMissingbean(HiddenHttpMethodFilter.class)
    @ConditionalProperty(prefix = "spring.mvc.hiddenmethod.filter", name="enabled", matchIfMissing = false)
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }
    ```

    ```yaml
    #选择性开启
    spring:
      mvc:
        hiddenmethod:
          filter:
            enabled: true
    ```

##### 2.请求映射原理

<img src="images/请求映射原理.png" alt="请求映射原理的图解">

springMVC功能分析都从 org.springframework.web.servlet.DispatcherServlet --> doDispatch()

```java
protected void doDispatcher(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequedt = request;
    handlerExcutionChain mappedHandler = null;
    boolean multipartRequestparsed = false;
    
    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    
    try {
        ModelAndView mv = null;
        Exception dispatcherException = null;
        
        try {
            processedRequest = checkMultipart(request);
            multipartRequestParsed = (processedRequest != request);
            
            //找到当前请求的需要使用的 Handler(Controller)
            mappedhandler = getHandler(processedRequest);
        }
    }
}
```

HandlerMapping：处理器映射

<img src="images/handlerMapping.png" alt="五个处理器映射">

- RequestMappingHandlerMapping：保存了所有@RequestMapping和Handler的映射规则

  - <img src="images/四个请求.png" alt="RequestMappingHandlerMapping保存的controller方法">
  - 总结：所有的请求映射都放在 **HandlerMapping** 中

  > - SpringBoot自动配置了欢迎页的 HandlerMapping，所以访问 “/”就可以访问到 index.html
  >
  > - 请求进来，挨个寻找所有的HandlerMapping查看是否有请求信息
  >   - 如果有就找到这个请求对应的handler
  >   - 如果没有就是下一个HandlerMapping
  > - 如果有需要一些自定义的映射处理，也可以自己往容器中放自定义的**HandlerMapping**

#### 2-3-2.普通参数和基本注解

##### 1.注解

@PathVariable、@RequestHeader、@ModelAttribute、@RequestParam、@MatrixVariable、@RequestBody

```html
测试基本注解：
<ul>
    <li><a href="/PathVariable/1/owner/tom">@PathVariable (路径变量)</a></li>
    <li><a href="/RequestHeader/2/owner/smith">@RequestHeader (获取请求头)</a></li>
    <li><a href="/RequestParam/2/owner/smith?age=18&inters=tea&inters=coffee">@RequestParam (获取请求参数)</a></li>
    <li><a href="/PathVariable/1/owner/tom">@CookieValue (获取cookie值)</a></li>
    <li><a href="/goto">@RequestAttribute (获取request域属性)</a></li>
    <li><a href="/MatrixVariable/sell;low=34;brand=byd,audi,yd">@MatrixVariable (矩阵变量)</a></li>
    <li><a href="/MatrixVariable2/1;age=20/2;age=10">@MatrixVariable2 (矩阵变量)</a></li>
</ul>
<form method="post" action="/RequestBody">
    @RequestBody (获取请求体) <br><br>
    用户名：<label><input name="username" type="text"/></label><br><br>
    邮箱：<label><input name="email" type="text" /></label><br><br>
</form>
```

```java
package com.mrli.springboot2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrLi on 2022/03/18/15:04
 */
@RestController
public class ParameterTestController {
    @GetMapping("/PathVariable/{id}/owner/{username}")
    public Map<String, Object> testPathVariable(@PathVariable("id") Integer id,
                                                @PathVariable("username") String name,
                                                @PathVariable Map<String, String> pv) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        return map;
    }

    @GetMapping("/RequestHeader/{id}/owner/{username}")
    public Map<String, Object> testRequestHeader(@PathVariable("id") Integer id,
                                                 @PathVariable("username") String name,
                                                 @PathVariable Map<String, String> pv,
                                                 @RequestHeader("User-Agent") String userAgent,
                                                 @RequestHeader Map<String, Object> header) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("header", header);
        return map;
    }

    @GetMapping("/RequestParam/{id}/owner/{username}")
    public Map<String, Object> testRequestParam(@PathVariable("id") Integer id,
                                                @PathVariable("username") String name,
                                                @RequestParam("age") Integer age,
                                                @RequestParam("inters")List<String> inters,
                                                @RequestParam Map<String, String> params) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        return map;
    }

    @GetMapping("/RequestParam/{id}/owner/{username}")
    public Map<String, Object> testCookieValue(@PathVariable("id") Integer id,
                                               @PathVariable("username") String name,
                                               @CookieValue("_ga") String _ga) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("_ga", _ga);
        return map;
    }

    @PostMapping("/RequestBody")
    public Map<String, Object> testRequestBody(@RequestBody String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", content);

        return map;
    }

    /*
    1、/MatrixVariable/sell;low=34;brand=byd,audi,yd
    2、SpringBoot默认是禁用了矩阵变量的功能
              手动开启：原理是对于路径的处理，需要通过的 UrlPathHelper 的解析
                      而在UrlPathHelper里又有个 removeSemicolonContent(移除分号内容) 支持矩阵变量
    3、矩阵变量必须有url路径变量才能被解析
    */
    @RequestMapping("/MatrixVariable/{path}")
    public Map<String, Object> testMatrixVariable(@MatrixVariable("low") Integer low,
                                                  @MatrixVariable("brand") List<String> brand,
                                                  @PathVariable String path) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    // /MatrixVariable2/1;age=20/2;age=10
    @RequestMapping("/MatrixVariable2/{bossId}/{employeeId}")
    public Map<String, Object> testMatrixVariable2(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,
                                                   @MatrixVariable(value = "age", pathVar = "employeeId") Integer empAge) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;
    }
}
```

RequestController

```java
package com.mrli.springboot2.controller;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MrLi on 2022/03/20/8:52
 */
public class RequestController {

    @RequestMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        request.setAttribute("msg", "success…………");
        return "forward:/success";
    }

    @ResponseBody
    @RequestMapping("/success")
    public String success(@RequestAttribute("msg") String msg) {
        return msg;
    }
}

```

WebConfig.java

```java
package com.mrli.springboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * Created by MrLi on 2022/03/20/10:00
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig/* implements WebMvcConfigurer*/ {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //不移除“;”后面的内容，是矩阵变量的内容生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }

    /*    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //不移除“;”后面的内容，是矩阵变量的内容生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }*/
}

```

##### 2.ServletAPI

WebRequest、ServletRequest、MultipartRequest、HttpSession、javax.servlet.http.PushBuilder、Principal、InputStream、Reader、HttpMethod、Locale、TimeZone、Zoneld

**ServletRequestMethodArgumentResolver** 可以解析以上部分参数

```java
public boolean supportsParameter(MethodParameter parameter) {
    Class<?> paramType = parameter.getParameterType();
    return WebRequest.class.isAssignableFrom(paramType) || ServletRequest.class.isAssignableFrom(paramType) || MultipartRequest.class.isAssignableFrom(paramType) || HttpSession.class.isAssignableFrom(paramType) || pushBuilder != null && pushBuilder.isAssignableFrom(paramType) || Principal.class.isAssignableFrom(paramType) && !parameter.hasParameterAnnotations() || InputStream.class.isAssignableFrom(paramType) || Reader.class.isAssignableFrom(paramType) || HttpMethod.class == paramType || Locale.class == paramType || TimeZone.class == paramType || ZoneId.class == paramType;
}
```

##### 3.复杂参数

Map、Errors/BindingResult、Model、RedirectAttribute、ServletResponse、SessionStatus、UriComponentBuilder、ServletUriComponentsBuilder

- Map、Model：map、model里面的数据会被放在request请求域中，相当于 request.setAttribute()
- RedirectAttribute：重定向携带数据
- ServletResponse：response响应

Map<String, Object> map、Model model、HttpServletRequest request、HttpServletResponse response都可以往request域中存放数据。存放的数据通过 request.getAttribute(XXX) 获取

Map类型的参数，会返回 mavContainer.getModel();

- 其中Model的全称是 BindingAwareModelMap，其既是Model也是一个Map

<img src="images/ModelAndMap.png" alt="">

##### 4.自定义对象参数

可以自动类型转换与格式化，可以级联封装

```java
//数据绑定：页面提交的请求数据（GET、POST）都可以和对象属性进行绑定
@Data
public class Person {
    private String username;
    private Integer age;
    private Date birth;
    private Pet pet;
}
@Data
public class Pet {
    private String name;
    private Integer age;
}
```

#### 2-3-3.POJO封装过程

**支持的参数处理器是ServletModelAttributeMethodProcessor**

**判断是否是简单类型**

```java
public static boolean isSimpleValueType(Class<?> type) {
    return Void.class != type && Void.TYPE != type && (ClassUtils.isPrimitiveOrWrapper(type) || Enum.class.isAssignableFrom(type) || CharSequence.class.isAssignableFrom(type) || Number.class.isAssignableFrom(type) || Date.class.isAssignableFrom(type) || Temporal.class.isAssignableFrom(type) || URI.class == type || URL.class == type || Locale.class == type || Class.class == type);
}
```

```java
@Nullable
public final Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
    Assert.state(mavContainer != null, "ModelAttributeMethodProcessor requires ModelAndViewContainer");
    Assert.state(binderFactory != null, "ModelAttributeMethodProcessor requires WebDataBinderFactory");
    String name = ModelFactory.getNameForParameter(parameter);
    ModelAttribute ann = (ModelAttribute)parameter.getParameterAnnotation(ModelAttribute.class);
    if (ann != null) {
        mavContainer.setBinding(name, ann.binding());
    }

    Object attribute = null;
    BindingResult bindingResult = null;
    if (mavContainer.containsAttribute(name)) {
        attribute = mavContainer.getModel().get(name);
    } else {
        try {
            attribute = this.createAttribute(name, parameter, binderFactory, webRequest);
        } catch (BindException var10) {
            if (this.isBindExceptionRequired(parameter)) {
                throw var10;
            }

            if (parameter.getParameterType() == Optional.class) {
                attribute = Optional.empty();
            } else {
                attribute = var10.getTarget();
            }

            bindingResult = var10.getBindingResult();
        }
    }

    if (bindingResult == null) {
        WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
        if (binder.getTarget() != null) {
            if (!mavContainer.isBindingDisabled(name)) {
                this.bindRequestParameters(binder, webRequest);
            }

            this.validateIfApplicable(binder, parameter);
            if (binder.getBindingResult().hasErrors() && this.isBindExceptionRequired(binder, parameter)) {
                throw new BindException(binder.getBindingResult());
            }
        }

        if (!parameter.getParameterType().isInstance(attribute)) {
            attribute = binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
        }

        bindingResult = binder.getBindingResult();
    }

    Map<String, Object> bindingResultModel = bindingResult.getModel();
    mavContainer.removeAttributes(bindingResultModel);
    mavContainer.addAllAttributes(bindingResultModel);
    return attribute;
}
```

**WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);**

**WebDataBinder（web数据绑定器）：将请求参数的值绑定到指定的JavaBean里面**

**GenericConversionService：在设置每一个值的时候，找到它里面所有的converter中可以将这个数据类型（request带来的参数的字符串）转换到指定的数据类型（Integer等）的converter**

**原理：WebDataBinder利用内部的 Converters 将请求数据转成指定的数据类型，然后再次封装到JavaBean中**

<img src="images/web数据绑定器.png" alt="web数据绑定器">

#### 2-3-4.参数处理原理

- 首先在HandlerMapping中找到可以处理请求的 Handler（Controller中的方法）
- 再为当前Handler找到一个适配器(HandlerAdapter)：**RequestMappingHandlerAdapter**

##### 1.HandlerAdapter

<img src="images/4种HandlerAdapter.png" alt="4种HandlerAdapter">

- 1.支持方法上标注 @RequestMapping
- 2.支持函数式编程
- ……

##### 2.执行目标方法

```java
//Actually invoke the handler
//DispatcherServlet --> doDispatcher
mv = ha.handler(processedRequest, response, mappedHandler.getHandler());
```

```java
//执行目标方法
mav = invokeHandlerMethod(request, response, HandlerMethod);
```

```java
//属于 ServletInvocableHandlerMethod 类
//真正执行目标方法
Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);
//获取方法参数值
Object[] args = getMethodArgumentValues(request, mavContainer, peovidedArgs);
```

##### 3.设置参数解析器

作用：确定将要执行的目标方法里面每一个参数的值

SpringMVC目标方法可以写多少种参数类型，是取决于参数解析器。

<img src="images/参数.png" alt="参数解析器里的26个参数">

参数解析器的两个接口：

<img src="images/接口.png" alt="参数解析器里的两个接口">

- 当前解析器是否支持解析这种参数的类型
- 如果支持就会调用 resolveArgument 方法

##### 4.返回值处理器

<img src="images/返回值处理器.png" alt="返回值处理器可以解决的15种参数类型">

##### 5.如何获取目标方法每一个参数的值

```java
//属于 InvocableHandlerMethod 类里面
protected Object[] getMethodArgumentValues(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer, Object... providedArgs) throws Exception {
    MethodParameter[] parameters = this.getMethodParameters();
    if (ObjectUtils.isEmpty(parameters)) {
        return EMPTY_ARGS;
    } else {
        Object[] args = new Object[parameters.length];

        for(int i = 0; i < parameters.length; ++i) {
            MethodParameter parameter = parameters[i];
            parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
            args[i] = findProvidedArgument(parameter, providedArgs);
            if (args[i] == null) {
                if (!this.resolvers.supportsParameter(parameter)) {
                    throw new IllegalStateException(formatArgumentError(parameter, "No suitable resolver"));
                }

                try {
                    args[i] = this.resolvers.resolveArgument(parameter, mavContainer, request, this.dataBinderFactory);
                } catch (Exception var10) {
                    if (logger.isDebugEnabled()) {
                        String exMsg = var10.getMessage();
                        if (exMsg != null && !exMsg.contains(parameter.getExecutable().toGenericString())) {
                            logger.debug(formatArgumentError(parameter, exMsg));
                        }
                    }

                    throw var10;
                }
            }
        }

        return args;
    }
}
```

- 挨个判断所有的参数解析器中支持这个参数的参数解析器

  ```java
  @Nullable
  private HandlerMethodArgumentResolver getArgumentResolver(MethodParameter parameter) {
      HandlerMethodArgumentResolver result = (HandlerMethodArgumentResolver)this.argumentResolverCache.get(parameter);
      if (result == null) {
          Iterator var3 = this.argumentResolvers.iterator();
  
          while(var3.hasNext()) {
              HandlerMethodArgumentResolver resolver = (HandlerMethodArgumentResolver)var3.next();
              if (resolver.supportsParameter(parameter)) {
                  result = resolver;
                  this.argumentResolverCache.put(parameter, resolver);
                  break;
              }
          }
      }
  
      return result;
  }
  ```

##### 6.目标方法执行完成之后

将所有的数据都放在 ModelAndViewContainer中，里面包含要转发的页面地址view，以及需要转发的数据Model

<img src="images/ModelAndViewContainer.png" alt="ModelAndViewContainer">

##### 7.处理派发结果

processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);

renderMergedOutputModel(mergedModel, getRequestToExpose(request), response);

```java
//来自 InternalResourceView 类
protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.exposeModelAsRequestAttributes(model, request);
    this.exposeHelpers(request);
    String dispatcherPath = this.prepareForRendering(request, response);
    RequestDispatcher rd = this.getRequestDispatcher(request, dispatcherPath);
    if (rd == null) {
        throw new ServletException("Could not get RequestDispatcher for [" + this.getUrl() + "]: Check that the corresponding file exists within your web application archive!");
    } else {
        if (this.useInclude(request, response)) {
            response.setContentType(this.getContentType());
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Including [" + this.getUrl() + "]");
            }

            rd.include(request, response);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Forwarding to [" + this.getUrl() + "]");
            }

            rd.forward(request, response);
        }

    }
}
```

```java
//暴露模型作为请求域属性
this.exposeModelAsRequestAttributes(model, request);

//model 中的所有数据挨个遍历放到请求域中
protected void exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request) throws Exception {
    model.forEach((name, value) -> {
        if (value != null) {
            request.setAttribute(name, value);
        } else {
            request.removeAttribute(name);
        }

    });
}
```

### 2-4.响应数据与内容协商

#### 2-4-1.响应JSON

##### 1.jackson.jar + @ResponseBody

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

<img src="images/jackson.png" alt="jackson">

就可以给前端自动返回json数据

##### 2.返回值解析器原理

<img src="images/返回值解析器.png" alt="返回值解析器">

```java
try {
    this.returnValueHandlers.handleReturnValue(
    	returnValue, getReturnValueType(returnValue), mavContainer, webRequest
    );
} catch(Exception e) {
    if (logger.isTraceEnabled()) {
        logger.trace(formatErrorForReturnValue(returnValue), e);
    }
    throw e;
}
```

```java
public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
    HandlerMethodReturnValueHandler handler = this.selectHandler(returnValue, returnType);
    if (handler == null) {
        throw new IllegalArgumentException("Unknown return value type: " + returnType.getParameterType().getName());
    } else {
        handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
```

<img src="images/返回值处理器方法.png" alt="返回值处理器的两个方法">

```java
//属于 RequestResponseBodyMethodProcessor 类
public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
    mavContainer.setRequestHandled(true);
    ServletServerHttpRequest inputMessage = this.createInputMessage(webRequest);
    ServletServerHttpResponse outputMessage = this.createOutputMessage(webRequest);
    //使用消息转换器进行写出操作
    this.writeWithMessageConverters(returnValue, returnType, inputMessage, outputMessage);
}
```

返回值处理器逻辑：

- 1.先判断是否支持这种类型的返回值，通过**supportReturnType**
- 2.如果支持返回值处理器调用**HandleReturnValue**进行处理
- 3.在众多返回值处理器中，RequestResponseBodyMethodProcessor可以处理标注了@ResponseBody注解的返回值
  - 利用 MessageConverters进行数据处理，将数据写为json
    - 1）内容协商（浏览器默认会以请求头的方式告知服务器它可以接受哪些内容类型）
    - 2）服务器最终会根据自己自身的能力，而决定发送什么样类型的数据
    - 3）springMVC会逐个遍历所有容器底层的 HttpMessageConverter，寻找能够处理数据的消息转换器
      - a、得到 MappingJackson2HttpMessageConverter，可以将对象写为 JSON
      - b、利用 MappingJackson2HttpMessageConverter 将对象转为 JSON 再写出去

<img src="images/requestHeaders.png" alt="Request Headers">

##### 3.springMVC支持的返回值类型

- ModelAndView
- Model
- View
- ResponseEntity
- ResponseBodyEmitter
- StreamingResponseBody
- HttpEntity
- HttpHeaders
- Callable
- DeferredResult
- ListenableFuture
- CompletionStage
- WebAsyncTask
- 标注 @ModelAttribute 注解
- 标注 @ResponseBody 注解

##### 4.HttpMessageConverter原理

HttpMessageConverter 检查是否支持将此Class类型的对象，转为目标类型的对象

> 例如将Person类型对象转换为JSON对象（响应）；或者将JSON对象转为Person对象（请求）

<img src="images/消息转换器.png" alt="消息转换器接口的方法">

系统默认的 messageConverter：

<img src="images/messageConverter.png" alt="系统默认的messageConverter">

- 0 -> 返回值只支持Byte类型
- 1 -> 返回值只支持 String 类型
- 2 -> 返回值只支持 String 类型
- 3 -> 返回值只支持 Resource 类型
- 4 -> 返回值只支持 ResourceRegion 类型
- 5 -> 返回值只支持 DOMSource.class、SAXSource.class、StAXSource.class、StreamSource.class、Source.class 类型
- 6 -> 返回值只支持 MultiValueMap 类型
- 7 -> true
- 8 -> true
- 9 -> 支持注解方式

最终通过 MappingJackson2HttpMessageConverter 将对象转为 JSON（利用底层jackson的objectMapper转换）

<img src="images/outputMessage.png" alt="outputMessage">

#### 2-4-2.内容协商

根据客户端接受能力的不同，而返回不同媒体类型的数据

##### 1.引入xml依赖

```xml
<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```



##### 2.postman分别测试返回json和xml

只需改变请求头中Accept字段（是Http协议中规定的），告诉服务器客户端可以接受的数据类型。

<img src="images/postman.png" alt="用postman改变请求头信息">

##### 3.开启浏览器参数方式内容协商功能

为了方便内容协商，开启基于请求参数内容协商功能

```yaml
#开启Rest风格支持
spring:
  mvc:
    #开启参数方式的内容协商
    contentnegotiation:
      favor-parameter: true
```

发送请求：http://localhost:8080/test/person?format=json 或 http://localhost:8080/test/person?format=xml

<img src="images/基于参数的内容协商原理.png" alt="基于参数的内容协商的原理">

确定客户端接收什么样的内容类型：

- 1.Parameter策略优先确定是要返回json数据（通过获取请求头中的 format 的值）

  ```java
  return request.getParameter(getParameterName());
  ```

  

##### 4.内容协商原理

- 1.判断当前响应头中是否已经有确定的媒体类型：MediaType

- **2.获取客户端（PostMan、浏览器）支持接收的内容类型：application/xml（获取客户端Accept请求头字段）**

  - **contentNegotiationManager（内容协商管理器）：默认使用基于请求头的策略**

    <img src="images/内容协商管理器的所有策略.png" alt="内容协商管理器的所有策略">

  - **基于请求头的内容协商策略最终会调用方法 HeaderContentNegotiationStrategy，来确定客户端可以接受的内容类型**

    <img src="images/获取请求头的值.png" alt="HeaderContentNegotiationStrategy中是如何获取请求头的">

- 3.循环遍历所有当前系统的MessageConverter，查询支持对象（Person）的converter

- 4.查询到支持操作Person对象的converter之后，将converter支持的媒体类型全部保存到集合中

- 5.客户端需要：application/xml；服务端可以提供：10种、json、xml

  <img src="images/支持的数据类型.png" alt="支持的10种数据类型">

- 6.进行内容协商的最佳匹配

  <img src="images/匹配.png" alt="">

- 用支持将对象转为最佳匹配媒体类型的converter，调用它进行转化

<img src="images/支持的converter.png" alt="">

导入了jackson处理xml的包，xml的converter就会自动进来

```java
jackson2xmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", classLoader);
```

```java
if (jackson2XmlPresent) {
    Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
    if (this.applicationContext != null) {
        builder.applicationContext(this.applicationContext);
    }
    messageConverters.add(new MappingJackson2XmlHttpMessageConverter(builder.build()));
}
```

##### 5.自定义MessageConverter

**实现多协议数据兼容：json、xml、x-guigu**

- 1.@ResponseBody响应数据出去，调用 **RequestResponseBodyMethodProcessor** 处理
- 2.Processor 处理方法返回值，通过 **MessageConverter** 处理
- 3.所有的 **MessageConverter** 合起来可以支持各种媒体类型数据的操作（读/写）
- 4.内容协商找到最终的 **messageConverter**

WebConfig.java

```java
@Configuration(proxyBeanMethods = false)
public class WebConfig/* implements WebMvcConfigurer*/ {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //自定义内容协商策略
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                //指定支持解析的参数对应的媒体类型
                mediaTypeMap.put("json", MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml", MediaType.APPLICATION_XML);
                mediaTypeMap.put("gg", MediaType.parseMediaType("application/x-guigu"));

                //基于参数
                ParameterContentNegotiationStrategy paramStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);
                //基于请求头
                HeaderContentNegotiationStrategy headStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(List.of(paramStrategy, headStrategy));
            }

            //添加消息匹配器 messageConverter
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiguConverter());
            }	
        }
    }
```

GuiguConverter.java

```java
public class GuiguConverter implements HttpMessageConverter<Person> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    /*
    服务器统计所有的MessageConverter需要写出的内容类型
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-guigu");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /*
    自定义协议数据的写出
     */
    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data = person.getUsername() + ";" + person.getAge();

        //将数据写出
        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());
    }
}
```

旧的适配器策略：

<img src="images/旧的适配策略.png" alt="旧的适配策略">

新的适配器策略：

<img src="images/新的适配策略.png" alt="新的适配策略">

> 在添加自定义组件时，有可能添加的自定义功能会覆盖原有的许多功能，导致默认功能失效

### 2-5.视图解析与模板引擎

视图解析：**SpringBoot默认不支持JSP，需要引入第三方的模板引擎技术实现页面渲染**

#### 2-5-1.视图解析

视图处理方式：转发、重定向、自定义视图

##### 1.视图解析原理

- 1.目标方法的处理过程中，所有的数据都会被放在 **ModelAndViewController** 中，包括数据和视图地址

- 2.方法的参数是一个自定义类型对象（从请求参数中确定），将自定义类型的对象重新放在 **ModelAndViewController** 中

- 3.任何目标方法执行完成以后都会返回 ModelAndView（保存有数据和视图地址）

- 4.**processDispatchResult** 处理派发结果（最终决定页面的响应方式）

  - 调用 **render(mv,request,response);** 进行页面渲染

    - 根据方法的 String 返回值得到 view 对象（vie对象定义了页面的渲染逻辑）

      - 1.尝试所有的视图解析器是否可以根据当前返回值得到view对象

      - 2.根据返回值 redirect:/main.html，确定视图解析器是 RedirectView

      - 3.ContentNegotiatingViewResolver里面包含了下方所有的视图解析器，所以内部还是会利用下方所有的视图解析器得到视图对象

      - 4.视图对象调用自定义的 render 方法进行页面渲染功能

        view.render(mv, getMethodInternal(), request, response)
        - 1.获取目标url地址
        - 2.调用方法：response.sendRedirect(encodedURL);

<img src="images/view.png" alt="视图对象的render方法">

<img src="images/视图解析器.png" alt="所有的视图解析器">

<img src="images/ContentNegotatingViewResoulver.png" alt="ContentNegotatingViewResoulver视图解析器">

> 返回值：
>
> - 以 **forward:** 开始：**new InternalResourceView(forwardUrl); --> request.getRequestDispatcher(path).forward(request, response);**
> - 以 **redirect:** 开始：**new RedirectView() --> response.sendRedirect(encodedURL);**
> - 是**普通字符串**：**new ThymeleafView()**

#### 2-5-2.模板引擎-Thymeleaf

##### 1.thymeleaf简介

https://www.thymeleaf.org/

Thymeleaf is a modern server-side Java template engine for both web and standalone environments.Thymeleaf's main goal is to bring elegant natural templates to your development workflow — HTML that can be correctly displayed in browsers and also work as static prototypes, allowing for stronger collaboration in development teams.With modules for Spring Framework, a host of integrations with your favourite tools, and the ability to plug in your own functionality, Thymeleaf is ideal for modern-day HTML5 JVM web development — although there is much more it can do.

##### 2.基本语法

###### 2-1.表达式

| 表达式名字 |  语法  |                用途                |
| :--------: | :----: | :--------------------------------: |
|  变量取值  | ${...} |   获取请求域、session域、对象域    |
|  选择变量  | *{...} |          获取上下文对象值          |
|    消息    | #{...} |           获取国际化等值           |
|    链接    | @{...} |              生成链接              |
| 片段表达式 | ~{...} | 类似jsp:include 作用：引入公共页面 |

###### 2-2.字面量

文本值：'one text', 'Another one!', ...

数字：0, 32, 3.0, 12.3, ...

布尔值: true，false

空值：null

变量：one，two，...（变量不能有空格）

###### 2-3.文本操作

字符串拼接：+

变量替换：|The name is ${name}|

###### 2-4.数学运算

运算符：+、-、*、/、%

###### 2-5.布尔运算

运算符：and，or

一元运算：!，not

###### 2-6.比较运算

比较：>、<、>=、<=（gt、lt、ge、le）

等式：==、!=（eq、ne）

###### 2-7.条件运算

if-them：(if) ? (then)

if-then-else：(if) ? (then) : (else)

Default：(value) ?: (defaultvalue)

###### 2-8.特殊操作

无操作：_

##### 3.设置属性值：th:attr

```html
<form: action="subscribe.html" th:attr="action=@{/subscribe}">
	<fieldset>
        <input type="text" name="email" />
        <input type="submit" value="Subscribe!" th:attr="value=#{subscribe.submit}" />
    </fieldset>
</form:>
```

设置多个值：

```html
<img src="../../images/gtvglogo.png" th:attr="src=@{/images/gtvglogo.png},title=#{logo},alt=""" />
```

以上两个的替代写法：

```html
<input type="submit" value="Subscribe!" th:value="#{subscribe.submit}" />
<form action="subscribe.html" th:action="@{/subscribe}" />
```

所有h5兼容的标签写法：

https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-value-to-specific-attributes

##### 4.迭代

```html
<tr th:each="prod : ${prods}">
	<td th:text="${prod.name}">Onions</td>
    <td th:text="${prod.price}">2.41</td>
    <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```

```html
<tr th:each="prod,iterStat : ${prods}" th:class="${iterStat.odd}? 'odd'">
    <td th:text="${prod.name}">Onions</td>
    <td th:text="${prod.price}">2.41</td>
    <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```

##### 5.条件运算

```html
<a href="comments.html"
   th:href="@{/product/comments(prodId=$(prod.id))}"
   th:if="@{not #lists.isEmpty(prod.comments)}">view</a>
```

```html
<div th:switch="${user.role}">
    <p th:case="'admin'">User is an administrator</p>
    <p th:case="#{roles.manager}">User is a manager</p>
    <p th:case="*">User is some other thing</p>
</div>
```

#### 2-5-3.Thymeleaf的使用

##### 1.引入starter

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

##### 2.自动配置好了的thymeleaf

```java
@Configuration(
    proxyBeanMethods = false
)
@EnableConfigurationProperties({ThymeleafProperties.class})
@ConditionalOnClass({TemplateMode.class, SpringTemplateEngine.class})
@AutoConfigureAfter({WebMvcAutoConfiguration.class, WebFluxAutoConfiguration.class})
public class ThymeleafAutoConfiguration {}
```

自动配好的策略：

- 1.所有的thymeleaf的配置值都在 **ThymeleafProperties** 里
- **2.配置好了 SpringTemplateEngine**
- 3.**配置好了 ThymeleafViewResolver**
- 4.用户只需要开发页面

```java
public static final String DEFAULT_PREFIX = "classpath:/templates/";
public static final String DEFAULT_SUFFIX = ".html";
```

##### 3.编写代码

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>SUCCESS</title>
    </head>
    <body>
        <h1 th:text="${msg}">index</h1>
        <h2>
            <a href="bilibili.com" th:href="${link}">b站</a>
        </h2>
    </body>
</html>
```

### 2-6.拦截器

配置拦截器步骤：

- 1.编写一个拦截器实现 HandlerInterceptor 接口
- 2.将拦截器注册到容器中：实现 WebMvcConfigure 的 addInterceptors 方法
- 3.指定拦截器规则

> 如果是拦截所有请求，静态资源也会被拦截

#### 2-6-1.HandlerInterceptor接口

```java
//登录检查拦截器
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @author MrLi
     * @create 2022/3/24 9:05
     * @description 目标方法执行之前
     *
     * 登录检查
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        //如果用户有登录，返回true，放行
        if (loginUser != null) return true;
        //如果用户没有登录，返回false，不放行
        //并且跳转到登录页
//        session.setAttribute("msg", "用户未登录");
//        response.sendRedirect("/");
        request.setAttribute("msg", "用户未登录");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

    /**
     * @author MrLi
     * @create 2022/3/24 9:05
     * @description 目标方法执行之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * @author MrLi
     * @create 2022/3/24 9:06
     * @description 页面渲染完成以后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
```

#### 2-6-2.配置拦截器

```java
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")     //所有请求都会被拦截
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**");    //设置不会被拦截的请求
    }
}
```

#### 2-6-3.验证拦截器

…………

#### 2-6-4.拦截器原理

1.根据当前请求，找到 **HandlerExcutionChain（可以处理请求的 Handler 以及 handler 的所有拦截器）**

2.先**顺序执行**所有拦截器的 preHandler 方法

- 如果当前拦截器 preHandler 返回为true，则执行下一个拦截器的 preHandler
- 如果当前拦截器 preHandler 返回为false，则直接倒叙执行所有已经执行了的拦截器的 afterHandler

3.**如果任何一个拦截器返回false，直接跳出不执行目标方法**

4.如果所有拦截器都返回true，就会执行目标方法

5.会倒序执行所有拦截器的 postHandler

6.在页面成功渲染完成以后，也会倒序触发 afterCompletion

> 前面的步骤如果出现任何异常都会直接触发 afterCompletion 结束程序

<img src="images/拦截器链.png" alt="拦截器链">

<img src="images/拦截器图解.png" alt="拦截器的执行顺序图示">

### 2-7.文件上传

#### 2-7-1.页面表单

```html
<form method="post" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file"><br><br>
    <input type="submit" value+"提交">
</form>
```

#### 2-7-2.文件上传代码

```java
@Controller
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layouts() {
        return "form/form_layouts";
    }

    /*
    MultipartFile：自动封装浏览器上传的文件
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestParam("headerImg") MultipartFile headerImg,
                         @RequestParam("photos") MultipartFile[] photos) {
        //保存到文件服务器，例如OSS服务器
        if (!headerImg.isEmpty()) headerImg.transferTo(new File("D:\\" + headerImg.getOriginalFilename()));
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (photo.isEmpty()) photo.transferTo(new File("D:\\" + photo.getOriginalFilename()));
            }
        }
        return "index";
    }
}
```

```properties
spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=100MB
```

#### 2-7-3.自动配置原理

**文件上传自动配置类 - MultipartAutoConfiguration-MultipartProperties**

- 自动配置好了 **StandardServletMultipartResolver（文件上传解析器）**

- 原理步骤：

  - 1.请求进来先使用文件上传解析器（**isMultipart**）判断，并封装文件上传请求（**通过resolveMultipart方法将原生request封装返回为MultipartHttpServletRequest**）

  - 2.参数解析器解析请求中的文件内容。然后将其封装成 **MultipartFile**

  - 3.将request中的文件信息封装成一个Map：**MultiValueMap<String, MultipartFile>**

  - 4.transferTo方法通过**工具类FileCopyUtils**实现了文件流的拷贝

    <img src="images/参数解析器.png" alt="文件上传中的参数解析器">

### 2-8.异常处理

#### 2-8-1.错误处理

##### 1.默认规则

- 默认情况下，SpringBoot提供了`/error`处理所有错误的映射

- 对于机器客户端，它将生成JSON响应，其中包含错误信息、HTTP状态信息和异常信息的详细信息；对于浏览器客户端，响应一个“whitelabel”的错误视图，以HTML格式呈现相同的数据

  <img src="images/404错误.png" alt="机器客户端的404错误">

  <img src="images/浏览器404错误.png" alt="浏览器上的404错误">

- **要对其进行自定义，添加`view`解析为`error`**

- 要完全替换默认行为，可以通过实现`ErrorController`并注册该类型的Bean定义，或者添加`ErrorAttributes`类型的组件以在使用现有机制的前提下替换所有内容

##### 2.定制错误处理逻辑

- 自定义错误页面

  - `error/404.html error/5xx.html`

  <img src="images/error.png" alt="错误页面">

- @ControllerAdvice + @ExceptionHandler处理异常

  - 底层原理是 **ExceptionHandlerExceptionResolver** 来处理异常

- @ResponseStatus + 自定义异常

  - 底层原理是 **ResponseStatusExceptionResolver**，可以把responseStatus注解的信息组装后再次调用 **`response.sendError(statusCode, resolverReason);`**
  - `sendError(statusCode, resolverReason)` 就是tomcat发送的 /error

- spring底层的异常，如参数类型转换异常

  - **DefaultHandlerExceptionResolver** 处理框架底层的异常

  - response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());

    <img src="images/tomcat错误页.png" alt="tomcat原生错误页">

- 自定义实现 HandlerExceptionResolver 处理异常

  - 可以作为默认的全局异常处理规则

  <img src="images/自定义异常.png" alt="">

- ErrorViewResolver 实现自定义处理异常

  - 底层只要想要response.sendError，error请求就会转到basicErrorController
  - 异常没有任何处理器能处理，也会交给tomcat底层的 response.sendError 处理，error请求就会转到basicErrorController
  - 而basicErrorController要去的页面地址是 **ErrorViewResolver**

##### 3.异常处理原理

- **ErrorMvcAutoConfiguration：异常处理规则的自动配置类**

  - 容器中的组件类型：DefaultErrorAttributes --> 组件id：errorAttributes

    - `public class DefaultErrorAttributes implements ErrorAttributes, HandlerExceptionResolver`
    - DefaultErrorAttributes作用：定义了错误页面中所包含的错误信息数据

    <img src="images/getErrorAttributes.png" alt="">

    <img src="images/getErrorAttributes2.png" alt="">

  - 容器中的组件类型：BasicErrorController--> 组件id：basicErrorController

    - 处理默认`/error`路径的请求，页面响应：`new ModelAndView("error", model);`
    - 容器中还有组件：view --> id：error
      - 作用：响应默认错误页
    - 容器中还有组件：BeanNameViewResolver（视图解析器）
      - 作用：按照返回的视图名作为组件的id去容器中查找view对象

  - 容器中组件类型：DefaultErrorViewResolver --> 组件id：conventionErrorViewResolver

    - 如果出现错误，会以 Http 状态码作为视图页的地址（viewName），而找到页面
      - error/404、5xx.html

> 总结：出现错误时如果想要返回页面，就会寻找error视图：StaticView（默认是一个白页）

JSON样式的错误页信息展示：

<img src="images/responseEntity.png" alt="">

HTML样式的错误页信息展示：

<img src="images/errorHtml.png" alt="">

##### 4.异常处理步骤流程

1.执行目标方法，目标方法运行期间如果有任何异常都会被catch，而且被标志当前请求结束，通过**dispatchException**

2.进入视图解析器（页面渲染）

processDispatchResult(processedRequest,response,mappedHandler,mv,dipatchException);

3.mv = processHandlerException;处理handler发生的异常，处理完成后返回ModelAndView

- 1.遍历所有的 handlerExceptionResolvers，寻找可以处理当前异常的处理器异常解析器（HandlerExceptionResolver）

  <img src="images/HandlerExceptionResolver.png" alt="">

- 2.系统默认的异常解析器有：

  <img src="images/异常处理器.png" alt="系统默认的异常解析器">
  - 1.先由 DefaultErrorAttributes 处理异常，将异常信息保存到request域中，并且返回null

  - 2.默认异常解析器中没有任何解析器可以处理异常，所以异常会被抛出

    - 1.没有任何异常解析器可以处理异常，最终底层就会发送 /error 请求，交给底层的 BasicErrorController 处理

    - 2.解析错误视图：即遍历所有的 ErrorViewResolver，寻找可以解析的错误视图解析器

      <img src="images/defaultErrorViewResolver.png" alt="">

    - 3.默认的 DefaultErrorViewResolver 作用：把响应状态码作为错误页的地址 --> error/500.html
    - 4.模板引擎最终响应这个页面：error/500.html

### 2-9.Web原生组件注入（Servlet、Filter、Listener）

#### 2-9-1.使用原生Servlet API(推荐)

##### 1.Servlet

@ServletComponentScan(basePackages = "com.MrLi.admin.servlet")：指定原生Servlet组件位置

```java
@ServletComponentScan(basePackages = "com.MrLi.admin.servlet")
@SpringBootApplication
public class SpringBoot3Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3Application.class, args);
    }
}
```

@WebServlet(urlPatterns = "/myServlet")：效果 --> 直接响应，且不需要经过Spring的拦截器

```java
@WebServlet(urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("I love you!");
    }
}
```

##### 2.Filter

@WebFilter(urlPatterns = {"/fonts/*", "/images/*"})

```java
@WebFilter(urlPatterns = {"/fonts/*", "/images/*"})
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       log.info("MyFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter 执行");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilter 销毁");
    }
}
```

##### 3.Listener

@WebListener

```java
@Slf4j
@WebListener
public class MyServletContentListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("MyServletContentListener 监听到项目初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("MyServletContentListener 监听到项目销毁");
    }
}
```

扩展：DispatcherServlet是如何注册进来的

- 容器中自动配置了 DispatcherServlet 属性绑定到 WebMvcProperties上，对应的配置文件配置项是 spring.mvc
- DispatcherServletConfiguration 通过`ServletRegistrationBean<DispatcherServlet>`将 DispatcherServlet 配置进来
- DispatcherServlet默认映射的是 / 路径

Tomcat-Servlet：

- 当有多个Servlet都可以处理到同一层路径时，会有一个精确优选原则

  A: /my/

  B: /my/

#### 2-9-2.使用RegistrationBean

```java
//proxyBeanMethods = true：保证依赖的组件始终是单实例
@Configuration
public class MyRegistrationBeanConfig {
    //servlet
    @Bean
    public ServletRegistrationBean<MyServlet> myServlet() {
        return new ServletRegistrationBean<>(new MyServlet(), "/my", "my1");
    }

    //filter
    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my", "/css/*"));
        return filterRegistrationBean;
    }

    //listener
    @Bean
    public ServletListenerRegistrationBean<MyServletContentListener> myListener() {
        return new ServletListenerRegistrationBean<>(new MyServletContentListener());
    }
}
```

### 2-10.嵌入式Servlet容器

#### 2-10-1.切换嵌入式Servlet容器

- 默认支持的WebServer

  - Tomcat、Jetty or Undertow
  - ServletWebServerApplicationContext 容器启动后寻找 ServletWebServerFactory 并引导创建服务器

- 切换服务器

  ```xml
  <dependency>
  	<groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
      	<exclusion>
          	<groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-tomcat</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  <dependency>
  	<groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-undertow</artifactId>
  </dependency>
  ```

  <img src="images/spring底层支持的服务器.png" alt="">

- 原理

  - 当springBoot应用启动时发现当前是Web应用，而web场景包又导入了tomcat
  - 之后web应用会创建一个web版的容器：`ServletWebServerApplicationContext`
  - `ServletWebServerApplicationContext`在启动的时候寻找`ServletWebServerFactory`（Servlet的web服务器工厂 --> 生产Servlet的web服务器）
  - SpringBoot底层默认的WebServer工厂
    - `TomcatServletWebServerfactory`、`JettyServletWebServerFactory` or `UndertowServletWebServerFactory`
  - 底层有一个自动配置类：`ServletWebServerFactoryAutoConfiguration`
    - 自动配置类导入了：`ServletWebServerFactoryConfiguration`
  - `ServletWebServerFactoryAutoConfiguration`导入了`ServletWebServerFactoryConfiguration`(配置类)
  - `ServletWebServerFactoryConfiguration`配置类动态判断系统中导入的web服务器的包（本地默认是web-starter-tomcat包），容器中就会创建`TomcatServletWebServerFactory`
  - 之后`TomcatServletWebServerFactory`创建出Tomcat服务器并且启动
    - Tomcat服务器指TomcatWebServer
      - TomcatServer的构造器有一个初始化方法：initialize，又会调用`this.tomcat.start();`

  > 总结：内嵌服务器，就是手动调用启动服务器的代码(前提：tomcat核心jar包存在)

#### 2-10-2.定制Servlet容器

- 实现 **`WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>`**
  - 把该配置文件的值和 **ServletWebServerFactory** 进行绑定
- 修改配置文件
- 直接自定义 **ConfigurableServletWebServerFactory**

**xxxCustomizer（定制化器）：可以改变xxx的默认规则**

```java
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(9000);
    }
}
```

### 2-11.定制化原理

#### 2-11-1.定制化的常见方式

- 修改配置文件

- **xxxxCustomizer;**

- **编写自定义的配置类：xxxConfiguration + @Bean替换、增加容器中默认组件；视图解析器**

- **web应用：编写一个配置类实现 WebMvcConfigurer 即可定制化web功能 + @Bean给容器中再扩展一些组件**

  ```java
  @Configuration
  public class AdminWebConfig implements WebMvcConfigurer{}
  ```

- @EnableWebMvc + WebMvcConfigurer --> @Bean可以全面接管SpringMVC，所有的规则全部自己重新配置，实现定制和扩展功能（慎用）

  - 原理：

    - 1.WebMvcAutoConfiguration是SpringMVC默认的自动配置功能类，里面静态资源、欢迎页…………

    - 2.一旦使用 @EnableWebMvc，就会有一个声明：`@Import(DelegatingWebMvcConfiguration.class)`

    - 3.DelegatingWebMvcConfiguration的作用：

      - 可以调用系统中所有的 WebMvcConfigurer，所有功能定制实现都是这些 WebMvcConfigurer 一起在发挥作用

      - 自动配置类一些十分底层的组件，例如：RequestMappingHandlerMapping，这些组件所依赖的组件都是从容器中获取

      - DelegatingWebMvcConfiguration只保证了SpringMVC最基本的使用功能

        ```java
        public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport
        ```

    - 5。WebMvcAutoConfiguration 内的配置如果要生效，必须声明一个条件注解：**`@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)`**，而又有

  - 总结：@EnableWebMvc 导致了 WebMvcAutoConfiguration 没有生效

  

  ```java
  /**
   * @EnableWebMvc：全面接管SpringMVC
   *      指静态资源、视图解析器、欢迎页等全部失效
   */
  @EnableWebMvc
  @Configuration
  public class AdminWebConfig implements WebMvcConfigurer {
      //定义静态资源行为
      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
          /*
          访问 /aa/** 所有请求都去 classpath:/static/ 下面进行匹配
           */
          registry.addResourceHandler("/aa/**")
              .addResourceLocations("classpath:/static/");
      }
  }
  ```

- ……

#### 2-11-2.原理分析

**场景 starter-xxxAutoConfiguration-导入xxx组件-绑定xxxProperties-绑定配置文件**

## 3.数据访问

### 3-1.SQL

#### 3-1-1.数据源的自动配置

**数据源：HikariDataSource**

##### 1.导入jdbc场景

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
```

<img src="images/数据库连接.png" alt="">

导入MySQL驱动

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
</dependency>
```

> 数据库版本应该和驱动版本一致
>
> 修改版本方式：
>
> 1、直接引入具体版本（maven的就近依赖原则）
>
> 2、重新声明版本（maven属性的就近优先原则）
>
> ```xml
> <properties>
> 	<java.version>1.8</java.version>
>     <mysql.version>8.0.22</mysql.version>
> </properties>
> ```

##### 2.分析自动配置

- DataSourceAutoConfiguration：数据源的自动配置

  - 修改数据源相关的配置：spring.datasource

  - 与数据库连接池相关的配置，是当容器中没有DataSource时才自动配置的

    ```java
    @Configuration(
        proxyBeanMethods = false
    )
    @Conditional({DataSourceAutoConfiguration.PooledDataSourceCondition.class})
    @ConditionalOnMissingBean({DataSource.class, XADataSource.class})
    @Import({Hikari.class, Tomcat.class, Dbcp2.class, OracleUcp.class, Generic.class, DataSourceJmxConfiguration.class})
    protected static class PooledDataSourceConfiguration {
        protected PooledDataSourceConfiguration() {
        }
    }
    ```

- 自动配置的类：

  - DataSourceTransactionManagerAutoConfiguration：事务管理器的自动配置
  - JdbcTemplateAutoconfiguration：JdbcTemplate的自动配置，可以对数据库进行CRUD操作
    - 可以修改配置：@ConfigurationProperties(prefix = "spring.jdbc") 来修改 JdbcTemplate
  - JndiDataSourceAutoConfiguration：jndi的自动配置
  - XADataSourceAutoConfiguration：分布式事务相关包

##### 3.添加自动配置

```yaml
spring:
  datasource:
    url: jdbc:mysql:///3306/book
    username: root
    password: 123abc
    driver-class-name: com.mysql.cj.jdbc.Driver
```

##### 4.测试

```java
@SpringBootTest
class SpringBoot4ApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        //select * from t_book where id=1
        Book book = jdbcTemplate.queryForObject("select * from t_book where id=1", Book.class);
        System.out.println(book);
    }

}
```

#### 3-2-2.使用Druid数据源

##### 1.druid官方github地址

https://github.com/alibaba/druid

##### 2.自定义方式

##### 2-1.引入 druid

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
```

##### 2-2.添加配置类

```java
@Configuration
@ConfigurationProperties("spring.datasource")
public class MyDataSourceConfig {
    //配置 druid web监控
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(List.of("/*"));
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    //配置 druid 监控页功能
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        return new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    }

    //配置数据源
    @Bean
    public DataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        //加入监控功能
        dataSource.setFilters("stat");
        return dataSource;
    }
}
```

##### 2-3.测试代码

```java
@Test
void contextLoads() {
    //select * from t_book where id=1
    Book book = jdbcTemplate.queryForObject("select * from t_book where id=1", Book.class);
    System.out.println(book);
}
```

#### 3.使用官方starter方式

##### 3-1.引入druid-starter

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.8</version>
</dependency>
```

##### 3-2.分析自动配置

- 扩展配置项：**spring.datasource.druid**

- DruidSpringAopConfiguration.class：监控SpringBean的；配置项：**spring.datasource.druid.aop-patterns**

- DruidStatViewServletConfiguration.class：监控页的配置；配置项：**spring.datasource.druid.stat-view-servlet，默认开启**

- DruidWebStatFilterConfiguration.class：web监控配置；配置项：**spring.datasource.druid.web-stat-filter，默认开启**

- DruidFilterConfiguration.class，所有Druid自己的filter的配置：

  ```java
  private static final String FILTER_STAT_PREFIX = "spring.datasource.druid.filter.stat";
  private static final String FILTER_CONFIG_PREFIX = "spring.datasource.druid.filter.config";
  private static final String FILTER_ENCODING_PREFIX = "spring.datasource.druid.filter.encoding";
  private static final String FILTER_SLF4J_PREFIX = "spring.datasource.druid.filter.slf4j";
  private static final String FILTER_LOG4J_PREFIX = "spring.datasource.druid.filter.log4j";
  private static final String FILTER_LOG4J2_PREFIX = "spring.datasource.druid.filter.log4j2";
  private static final String FILTER_COMMONS_LOG_PREFIX = "spring.datasource.druid.filter.commons-log";
  private static final String FILTER_WALL_PREFIX = "spring.datasource.druid.filter.wall";
  private static final String FILTER_WALL_CONFIG_PREFIX = "spring.datasource.druid.filter.wall.config";
  ```

##### 3-3.配置示例

```yaml
spring:
  datasource:
    url: jdbc:mysql:///3306/book
    username: root
    password: 123abc
    driver-class-name: com.mysql.cj.jdbc.Driver

    druid:
      filters: stat,wall
      stat-view-servlet:
        login-username: root
        login-password: 123abc
        reset-enable: false

      web-stat-filter:
        enabled: true
        url-pattern: /*

      aop-patterns: com.MrLi.demo.*
```

#### 3-2-3.整合MyBatis操作

starter：https://github.com/mybatis

SpringBoot官方的starter：`spring-boot-starter-*`

第三方：`*-spring-boot-starter`

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>
```

<img src="images/mybatis依赖.png" alt="">

##### 1.配置模式

需要：

- 全局配置文件
- SqlSesionFactory：已经自动配置好了
- SqlSession：自动配置了 SqlSessionTemplate，在这里面组合了 sqlSession
- @Imporl(AutoConfiguredMapperScannerRegistrar.class);
- Mapper：只要写的操作MyBatis的接口标注了 @Mapper 就可以被自动识别

```java
@EnableConfigurationProperties(MybatisProperties.class) //MyBatis配置文件绑定类
@AutoConfigureAfter({DataSourceAutoConfiguration.class,MybatisLaguageDriverAutoCOnfiguration.class})
public class MyBatisConfiguration() {}
```

```java
@ConfigurationProperties(prefix = MyBatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {}
```

如果想要修改修改MyBatis可以修改配置文件中以 mybatis 开始的所有。

步骤：

- 1.配置Mybatis规则

  ```yaml
  mybatis:
    config-location: classpath:mybatis/mybatis-config.xml #全局配置文件位置
    mapper-locations: classpath:mybatis/mapper/*.xml #sql映射文件位置
  ```

- 2.mapper接口 && 绑定xml

  ```java
  @Mapper
  public interface UserMapper {
      User selectUsernameAndPasswordById(Integer id);
  }
  ```

  ```xml
  <mapper namespace="com.MrLi.demo.mapper.UserMapper">
  
      <!--User selectUsernameAndPasswordById(Integer id);-->
      <select id="selectUsernameAndPasswordById" resultType="com.MrLi.demo.bean.User">
          select username,password from t_user where id = #{id}
      </select>
  </mapper>
  ```

- 3.测试代码

  ```java
  @Service
  public class UserService {
      @Autowired
      UserMapper userMapper;
  
      public User getUsernameAndPasswordById(Integer id) {
          if (id != 0) {
              return userMapper.selectUsernameAndPasswordById(id);
          }
          return null;
      }
  }
  ```

  ```java
  @Controller
  public class IndexController {
      @Autowired
      UserService userService;
  
      @GetMapping("/getUsernameAndPasswordById")
      public String getUsernameAndPasswordById(@RequestParam("id") Integer id) {
          return userService.getUsernameAndPasswordById(id).toString();
      }
  }
  ```

> 开启驼峰命名规则
>
> ```xml
> <!--开启驼峰命名-->
> <settings>
>     <setting name="mapUnderscoreToCamelCase" value="true"/>
> </settings>
> ```

除了配置 mybatis-config.xml 还可以配置 `private Configuration configuration;mybatis.configuration`下面的所有属性，相当于修改 mybatis-config.xml 全局配置文件中的值

```yaml
mybatis:
  #config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mybatis/mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true
```

> 可以不写全局配置文件，所有全局配置文件的配置都放在configuration配置项中即可
>
> 全局配置文件和全局配置项只能存在一个

##### 2.注解模式

```java
@Mapper
public interface CityMapper {
    @Select("select 'name','state','country' from city where id=#{id}")
    City selectAllById(Integer id);

    @Insert("insert into city(`name`,state,country) values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertCity(City city);
}
```

##### 3.混合模式

```java
@Mapper
public interface CityMapper {
    @Select("select 'name','state','country' from city where id=#{id}")
    City selectAllById(Integer id);
    
    Integer insertCity(City city);
}
```

```xml
<!--Integer insertCity(City city);-->
<insert id="insertCity" useGeneratedKeys="true" keyProperty="id">
    insert into city(`name`,state,country) values(#{name},#{state},#{country})
</insert>
```

最佳实战：

- 引入mybatis-starter
- 配置application.yaml，指定mapper-location的位置
- 编写Mapper接口并标注`@Mapper`注解
- 简单的方法直接用注解方式
- 复杂的方法要编写mapper.xml进行绑定映射

> 可以通过`@MapperScan("")`简化，其他的接口就可以不用再标注`@Mapper`注解
>
> ```java
> @MapperScan("com.MrLi.demo.mapper")
> @SpringBootApplication
> public class SpringBoot4Application {}
> ```

#### 3-2-4.整合MyBatis-Plus完成CRUD

##### 1.什么是MyBatis-plus

MyBatis-plus（简称MP）是一个MyBatis的增强工具，在MyBatis的基础上只做增强不做改变，为简化开发、提高效率而生。

<a href=" https://www.mybatis-plus.com/">MyBatis-plus官网</a>

##### 2.整合MyBatis-plus

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>
```

自动配置的属性有：

- 配置类：MybatisPlusAutoConfiguration；配置项绑定：MybatisPlusProperties；mybatis-plus：xxx就是对mybatis-plus的定制
- SqlSessionFactory 自动配置好了，底层中有容器默认的数据源
- mapperLocations 自动配置好了，且有默认值：classpath*:/mapper/**/*.xml(任意包的类路径下所有的mapper文件夹下任意路径下的所有xml都是sql映射文件)，建议sql映射文件都放在mapper文件下
- SqlSessionTemplate 自动配置好了
- `@Mapper`标注的接口也会被自动扫描到

Mybatis-plus的优点：只需要Mapper接口继承 BaseMapper 就可以拥有CRUD功能

### 3-2.NoSQL

 Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。 它支持多种类型的数据结构，如 [字符串（strings）](http://www.redis.cn/topics/data-types-intro.html#strings)， [散列（hashes）](http://www.redis.cn/topics/data-types-intro.html#hashes)， [列表（lists）](http://www.redis.cn/topics/data-types-intro.html#lists)， [集合（sets）](http://www.redis.cn/topics/data-types-intro.html#sets)， [有序集合（sorted sets）](http://www.redis.cn/topics/data-types-intro.html#sorted-sets) 与范围查询， [bitmaps](http://www.redis.cn/topics/data-types-intro.html#bitmaps)， [hyperloglogs](http://www.redis.cn/topics/data-types-intro.html#hyperloglogs) 和 [地理空间（geospatial）](http://www.redis.cn/commands/geoadd.html) 索引半径查询。 Redis 内置了 [复制（replication）](http://www.redis.cn/topics/replication.html)，[LUA脚本（Lua scripting）](http://www.redis.cn/commands/eval.html)， [LRU驱动事件（LRU eviction）](http://www.redis.cn/topics/lru-cache.html)，[事务（transactions）](http://www.redis.cn/topics/transactions.html) 和不同级别的 [磁盘持久化（persistence）](http://www.redis.cn/topics/persistence.html)， 并通过 [Redis哨兵（Sentinel）](http://www.redis.cn/topics/sentinel.html)和自动 [分区（Cluster）](http://www.redis.cn/topics/cluster-tutorial.html)提供高可用性（high availability）。 

#### 3-2-1.Redis自动配置

##### 1.导入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

<img src="images/redis项目结构.png" alt="">

##### 2.redis自动配置的内容

- RedisAutoConfiguration 自动配置类，RedisProperties属性类 --> spring.redis：对redis的配置的配置文件
- 连接工厂：LettuceConnectionfactory是准备好的，还导入了配置文件类：**Lettuce**ConnectionConfiguration和**Jedis**ConnectionConfiguration
  - 自动注入了**RedisTemplate**<**Object**,**Object**>（key: value）
  - 自动注入了**StringRedisTemplate**（k:v 都是String）
  - 只需要使用底层的**RedisTemplate**、**StringRedisTemplate**就可以操作Redis

##### 3.Redis环境搭建

- 1.购买阿里云按量付费的redis（经典网络）
- 2.申请redis公网连接地址

#### 3-2-2.RedusTemplate和Lettuce

```yaml
  #配置redis
  redis:
    url: redis://user:password@example.com:6379
```

```java
@Autowired
RedisTemplate<String, String> redisTemplate;
@Test
void testRedis() {
    ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
    opsForValue.set("hello", "redis");
    System.out.println(opsForValue.get("hello"));
}
```

#### 3-2-3.切换至jedis

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

```yaml
  #配置redis
  redis:
    url: redis://user:password@example.com:6379
    client-type: jedis
    jedis:
      pool:
        max-active: 10
```

```java
@Autowired
RedisConnectionFactory redisConnectionFactory;
@Test
void testRedis() {
    ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
    opsForValue.set("hello", "redis");
    System.out.println(opsForValue.get("hello"));
    System.out.println(redisConnectionFactory.getClass());
}
```

## 4.单元测试

### 4-1.JUnit5的变化

**SpringBoot 2.2.0版本开始引入 JUnit5 作为单元测试默认库**

作为最新版本的JUnit框架，JUnit5与之前版本的JUnit框架有很大的不同。JUnit5由三个不同的子项目的几个不同模块组成。

> Junit5 = JUnit Platform + Junit Jupiter + JUnit Vintage

**JUnit Platform**：JUnit Platform是在JVM上启动测试框架的基础，不仅支持JUnit自制的测试引擎，其他测试引擎也都可以接入。

**JUnit Jupiter**：JUnit Jupiter提供了JUnit5新的编程模型，是JUnit5新特性的核心。内部包含了一个**测试引擎**，用于在Junit Platform上运行。

**JUnit Vintage**：由于JUnit已经发展多年，为了照顾老的项目JUnit Vintage提供了兼容JUnit 4.x，JUnit 3.x的测试引擎。

<img src="images/JUnit5.png" alt="">

> 注意：**SpringBoot 2.4以上的版本已处理默认对Vintage的依赖，如果需要兼容JUnit4需要自行引入**

现在的版本：

```java
@SpringBootTest
class SpringBoot3ApplicationTests {
    @Test
    void contextLoads() {
    }

}

```

以前的版本：@SpringBootTest + @RunWith(SpringTest.class)

### 4-2.JUnit5常用注解

JUnit5的注解和JUnit4的注解有所变化：

https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations

- **@Test**：测试方法。与JUnit4的@Test不同，JUnit4职责十分单一，不能声明任何属性，扩展的测试将会由Jupiter提供额外测试。
- **@ParameterizedTest**：参数化测试
- **@RepeatedTest**：可重复执行
- **@DisplayName**：为测试类或者测试方法设置展示名称
- **@BeforeEach**：表示在每个单元测试之前执行
- **@AfterEach**：表示在每个单元测试之后执行
- **@BeforeAll**：表示在所有单元测试之前执行
- **@AfterAll**：表示在所有单元测试之后执行
- **@Tag**：表示单元测试类别，类似于JUnit4中的@Categories
- **@Disabled**：表示测试类或测试方法不执行，类似于JUnit4中的@lgnore
- **@Timeout**：表示测试方法如果超过了指定时间将会返回错误
- **@ExtendWith**：为测试类或测试方法提供扩展类引用

```java
import java.util.concurrent.TimeUnit;

/**
 * Created by MrLi on 2022/03/29/14:04
 */
@DisplayName("Junit5功能测试类")
public class Junit5Test {
    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName() {
        System.out.println(1);
    }

    @Disabled
    @DisplayName("测试方法2")
    @Test
    void test2() {
        System.out.println(2);
    }

    //当方法超出规定时间后，会抛出超时异常
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("测试方法3")
    @Test
    void test3() {
        try {
            Thread.sleep(2000);
            System.out.println(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试即将开始……");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试即将结束……");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试即将开始……");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试即将结束……");
    }
}
```

### 4-3.断言（assertions）

断言（assertions）是测试方法中的核心部分，用来对测试需要满足的条件进行验证。**这些断言方法都是`org.junit.jupiter.Assertions`的静态方法**。断言的优点：所有的测试运行结束以后，会有一个详细的测试报告。

JUnit5内置的断言**根据检察业务逻辑返回的数据是否合理**可以分为以下几个类别：

#### 4-3-1.简单断言

用来对单个值进行简单的验证，如：

| 方法            | 说明                                 |
| :-------------- | ------------------------------------ |
| assertEquals    | 判断两个对象或两个原始类型是否相等   |
| assertNotEquals | 判断两个对象或两个原始类型是否不相等 |
| assertSame      | 判断两个对象引用是否指向同一个对象   |
| assertNotSame   | 判断两个对象引用是否指向不同对象     |
| assertTrue      | 判断给定的布尔值是否为true           |
| assertFalse     | 判断给定的布尔值是否为false          |
| assertNull      | 判断给定的对象引用是否为null         |
| assertNotNull   | 判断给定的对象引用是否不为null       |

```java
//断言：前面的断言失败，后面的代码都不会执行
@DisplayName("测试简单断言")
@Test
void testSimpleAssertions() {
    Assertions.assertEquals(2, 1+1, "业务逻辑计算失败");

    Assertions.assertSame(new Object(), new Object(), "两个对象不一样");
}
```

#### 4-3-2.数组断言

通过 assertArrayEquals 方法来判断两个对象或原始类型的数组是否相等

```java
@DisplayName("assertArrayEquals")
@Test
void array() {
    Assertions.assertArrayEquals(new int[]{1,2}, new int[]{1,2});
}
```

#### 4-3-3.组合断言

assertAll 方法接收多个 `org.junit.jupiter.api.Executable`函数时接口的实例作为要验证的断言，可以通过lambda表达式很容易的提供这些断言

```java
@DisplayName("组合断言")
@Test
void testAssertAll() {
    Assertions.assertAll("test",
                         () -> Assertions.assertTrue(true && true),
                         () -> Assertions.assertEquals(2, 1+1));
}
```

#### 4-3-4.异常断言

在junit4时期，想要测试方法的异常情况，需要用**@Rule**注解的ExpectedException变量会有点麻烦，而junit5提供了一种新的断言方法 **`Assertions.assertThrows()`**配合函数式编程就可以使用。

```java
@DisplayName("异常断言")
@Test
void testAssertThrows() {
    //断定业务逻辑一定会出现异常
    Assertions.assertThrows(
        //扔出断言异常
        ArithmeticException.class, () -> System.out.println(1 % 0)
    );
}
```

#### 4-3-5.超时断言

junit5还提供了**Assertions.assertTimeout()**为测试方法设置了超时时间

```java
//如果测试方法超过1s就会异常
@DisplayName("超时断言")
@Test
void testAssertTimeout() {
    Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
}
```

#### 4-3-6.快速失败

通过fail 方法直接使得测试失败

```java
@DisplayName("快速失败")
@Test
void testFail() {
    Assertions.fail("This should fail.");
}
```

### 4-4.前置条件（assumptions）

JUnit5 中的前置条件（assumptions【假设】）。类似于断言，不同之处在于不满足的断言会使得测试方法失败，而不满足的前置条件只会使得测试方法的执行终止。前置条件可以看成是测试方法执行的前提，当该前提不满足时，就没有继续执行的必要。

```java
@DisplayName("前置条件")
@Test
void testAssumptions() {
    Assumptions.assumeTrue(true, "结果是true");
    System.out.println("这里是testAssumptions");
}
```

### 4-5.嵌套测试

Junit5可以通过Java中的内部类和 @Nested 注解实现嵌套测试，从而可以更好的把相关的测试方法组织在一起。在内部类中可以使用 @BeforeEach 和 @AfterEach 注解，而且嵌套的层次没有限制。

```java
public class TestAStack {
    Stack<Object> stack;

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
        //嵌套测试情况下，外层的Test不能驱动内层的 BeforeEach/All 之类的方法之前/之后运行
        Assertions.assertNull(stack);
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            Assertions.assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.pop());
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            Assertions.assertThrows(EmptyStackException.class, () -> stack.peek());
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";
            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            //内层的 Test 可以驱动外层的 Test 不能驱动内层的 BeforeEach/All 之类的方法之前/之后运行
            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                Assertions.assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                Assertions.assertEquals(anElement, stack.pop());
                Assertions.assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                Assertions.assertEquals(anElement, stack.peek());
                Assertions.assertFalse(stack.isEmpty());
            }
        }
    }
}
```

### 4-6.参数化测试

参数化测试是JUnit5很重要的一个新特性，它使得用不同的参数进行多次运行测试成为了可能，也为单元测试带来了许多便利。通过利用 **@ValueSource** 等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个测试单元，省去了很多冗余的代码。

- **@ValueSource**：为参数化测试指定入参来源，支持八大基础类以及String类、Class类型。
- **@NullSource**：表示为为参数化测试提供一个null的入参
- **@EnumSource**：表示为为参数化测试提供一个枚举入参
- **@CsvFileSource**：表示读取指定CSV文件内容作为参数化测试入参
- **@MethodSource**：表示读取指定方法的返回值作为参数化测试入参（注意方法返回值需要的是一个流）

> 如果参数化测试仅仅只能做到指定普通的入参还达不到惊艳的地步，但参数化测试真正强大的地方在于它可以支持外部的各类入参，如：CSV、YML、JSON文件甚至方法的返回值也都可以入参。而这只需要去实现 **ArgumentProvider** 接口，任何外部文件都可以作为它的入参

```java
static Stream<String> stringProvider() {
    return Stream.of("A", "B", "C", "D");
}

@ParameterizedTest
@DisplayName("参数化测试")
@ValueSource(ints = {1,2,3,4,5,6,7})
void testParameterized(int i) {
    System.out.println(i);
}

@ParameterizedTest
@DisplayName("参数化测试2")
@MethodSource("stringProvider")
void testParameterized2(String i) {
    System.out.println(i);
}
```

### 4-7.迁移指南

在进行迁移的时候需要注意如下的变化：

- 注解在`org.junit.jupiter.api`包中，断言在`org.junit.jupiter.api.Assertions`类中，前置条件在`org.junit.jupiter.api.Assumptions`类中
- 把 @Before 和 @After 替换成 @BeforeEach 和 @AfterEach
- 把 @BeforeClass 和 @AfterClass 替换成 @BefpreAll 和 @AfterAll
- 把 @lgnore 替换成 @Disabled
- 把 @Category 替换成 @Tag
- 把 @RunWith、@Rule和 @ClassRule 替换成 @ExtendWith 

## 5.指标监控

### 5-1.SpringBoot Actuator

#### 5-1-1.简介

未来每一个微服务在云上部署以后，都需要对其进行监控、追踪、审计、控制等。SpringBoot为此抽取了Actuator场景，使得我们每个微服务快速引用即可获得生产级别的应用监控、审计等功能。

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

<img src="images/Actuator.png" alt="">

#### 5-1-2. 1.x和2.x的不同

<img src="images/1版本和2版本的不同.png" alt="">

#### 5-1-3.如何使用

- 引入场景

- 访问 http://localhost:8080/actuator/*

- 暴露所有监控信息为HTTP

  ```yaml
  # management 是所有监控指标 Actuator 的配置
  management:
    endpoints:
      enabled-by-default: true # 默认开启所有的监控端点
      web:
        exposure:
          include: '*' # 以web方式暴露所有的监控端点
  ```

- 测试

  - http://localhost:8080/actuator/beans
  - http://localhost:8080/actuator/configprops
  - http://localhost:8080/actuator/metrics
  - http://localhost:8080/actuator/metrics/jvm.gc.pause
  - …………

#### 5-1-4.可视化

https://github.com/codecentric/spring-boot-admin

### 5-2.Actuator Endpoint

#### 5-2-1.常用的端点

| ID               | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| auditevents      | 暴露当前应用程序的审核事件信息，需要一个**AuditEventRepository组件** |
| beans            | 显示应用程序中所有Spring Bean的完整列表                      |
| caches           | 暴露可用的缓存                                               |
| conditions       | 显示自动配置的所有条件信息，包括匹配或不匹配的原因           |
| configprops      | 显示所有的`@ConfigurationPropertis`                          |
| env              | 暴露Spring的属性 `ConfigurableEnviroment`                    |
| flyway           | 显示已应用的所有Flyway数据库迁移                             |
| health           | 显示引用查询运行状况信息                                     |
| httptrace        | 显示HTTP跟踪信息（默认情况下，最近100个HTTP请求-响应），需要一个`HttpTraceRepository`组件 |
| info             | 显示应用程序信息                                             |
| integrationgraph | 显示Spring的`integrationgraph`，需要依赖`spring-integration-core` |
| loggers          | 显示和修改应用程序中日志的配置                               |
| liquibase        | 显示已应用的所有Liquibase数据库迁移。需要一个或多个`Liquibase`组件 |
| metrics          | 显示当前应用程序的“指标”信息                                 |
| mappings         | 显示所有`@RequestMapping`路径列表                            |
| scheduledtasks   | 显示应用程序中的计划任务                                     |
| sessions         | 允许从Spring Session支持的会话储存中检索和删除用户会话，需要使用Spring Session的基于Servlet的Web应用程序 |
| shutdown         | 使应用程序正常关闭。默认禁用                                 |
| startup          | 显示由ApplicationStartup收集的启动步骤数据，需要使用`SpringApplication`进行配置`BufferingApplicationStartup` |
| threaddump       | 执行线程转储                                                 |

如果应用程序时Web应用程序（SpringMVC、Spring WebFlux或Jersey），则可以使用以下附加端点：

| ID         | 描述                                                         |
| ---------- | ------------------------------------------------------------ |
| heapdump   | 返回hprof堆转储文件                                          |
| jolokia    | 通过HTTP暴露JMX bean（需要引入Jolokia，不适用于WebFlux），需要引入`jolokia-core` |
| logfile    | 返回日志文件的内容（如果已设置`logging.file.name`或`logging.file.path`属性）；支持使用HTTP Range标头来检索部分日志文件的内容 |
| prometheus | 以Prometheus服务器可以抓取的格式公开指标；需要依赖`micrometer-registry-prometheus` |

最常用的Endpoint

- Health：监控状况
- Metrics：运行时指标
- Loggers：日志记录

#### 5-2-2.Health Endpoint

健康检查端点，一般用于云平台使用，平台会定时检查应用的健康状况，就会需要Health Endpoint可以为平台返回当前应用的一系列组件的健康状况的集合。

重要的点：

- 1.health endpoint返回的结果，应该是一系列健康检查后的一个汇总报告
- 2.很多的健康检查默认已经自动配置好了，比如：数据库、redis等
- 3.可以很容易的添加自定义的健康检查机制

#### 5-2-3.Metrics Endpoint

可以提供详细的、层级的空间指标信息，这些信息可以被pull（主动推送）或者push（被动获取）方式得到：

- 通过Metrics对接多种监控系统
- 简化核心Metrics开发
- 添加自定义的Metrics或者扩展已经有的Metrics

## 6.SpringBoot原理解析

### 6-1.Profile功能

为了方便多环节适配，SpringBoot简化了profile的功能

#### 6-1-1.application-profile功能

- 默认配置文件`application.yaml`在任何时候都会加载
- 指定环境配置文件`application-{env}.yaml`
- 激活指定环境
  - 配置文件激活
  - 命令行激活：java -jar xxx.jar -spring.profiles.active=prod --person.name=haha
    - 当需要修改配置文件的任意值，以命令行的优先
- 默认配置与环境配置同时生效
- 同名配置项，profile配置优先

```java
@RestController
public class HelloController {
    @Value("${person.name : temp-smith}")
    private String name;

    @GetMapping("/")
    public String hello() {
        return "hello, " + name;
    }
}
```

```properties
server.port=8080

# spring.profiles.active=prod
#指定激活的环境
#会加载默认配置文件和指定环境的配置文件，且指定环境的配置文件会覆盖默认配置文件
spring.profiles.active=test 
```

```yaml
application-prod.yaml
person:
  name: prod-tom

server:
  port: 7070
  
application-test.yaml
person:
  name: test-tom

server:
  port: 9090
```

#### 6-1-2.@Profile条件装配功能

```java
@Configuration(proxyBeanMethods = false)
@Profile("production")
public class ProductionConfiguration {
    //
}
```

#### 6-1-3.profile分组

```properties
spring.profiles.group.production[0]=proddb
spring.profiles.group.production[1]=prodmq

#通过使用 --spring.profiles.active=production 激活
```

### 6-2.外部化配置

https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config

#### 6-2-1.外部配置源

常用的：**Java属性文件、YAML文件、环境变量、命令行参数**

### 6-2-2.配置文件查找位置顺序

- 1.classpath 根路径
- 2.classpath 根路径下的config目录
- 3.jar包当前目录
- 4.jar包当前目录的config目录
- /config子目录的直接子目录

> 配置文件优先顺序：指定环境优先；外部优先；后面的会覆盖前面的

#### 6-2-3.配置文件加载顺序

- 1.当前jar包内部的`application.properties`和`application.yaml`
- 2.当前jar包内部的`application-{profile}.properties`和`application-{profile}.yaml`
- 3.引用的外部jar包的`application.properties`和`application.yaml`
- 4.引用的外部jar包的`application-{profile}.properties`和`application-{profile}.yaml`

> 配置文件优先顺序：指定环境优先；外部优先；后面的会覆盖前面的

### 6-3.自定义starter

#### 6-3-1.starter启动原理

- starter-pom引入 autoconfigurer 包

  <img src="images/自定义starter.png" alt="">

- autoconfigure包中配置使用 **META-INF/spring.factories** 中 **EnableAutoConfiguration** 的值，使得项目启动加载指定的自动配置类

- 编写自动配置类：xxxAutoConfiguration --> xxxProperties

  - @Configuration
  - @Conditional
  - @EnableConfigurationProperties
  - @Bean
  - ……

步骤：引入starter ---> xxxAutoConfiguration --> 容器中放入组件 --> 绑定xxxProperties --> 配置项

### 6-4.SpringBoot原理总结

Spring原理（**Spring注解**）、SpringMVC原理、自动配置原理、SpringBoot原理

#### 6-4-1.SpringBoot启动过程

- 创建SpringApplication
  - 保存一些信息
  - 判定当前应用的类型：Servlet应用还是响应式应用（通过ClassUtils实现）
  - 搜索**bootStrappers：初始启动引导器**（`List<Bootstrapper>`），**在spring.factories文件中找**`org.springframework.boot.Bootstrapper`
  - 搜索**ApplicationContextInitializer**：**在spring.factories中找**
    - `List<ApplicationContextInitializer<?> Initializers`
  - 搜索**ApplicationListener（应用监听器）**：**在spring.factories中找**
    - `List<ApplicationListener<?>> listener`

- 运行SpringApplication
  - StopWatch
  - 记录应用的启动时间
  - 创建引导上下文（Context环境）：createBootstrapContext()
    - 获取到之前的**bootstrappers，并且挨个执行`intitialize()`**，来完成对引导器的上下文环境设置
  - 让当前应用进入到**headless**模式 --> **java.awt.headless**
  - **获取所有的运行监听器（Runlistener）：为了方便所有的Listener进行事件感知**
    - **`getSpringFactoriesInstances`在`spring.factories`中搜索SpringApplicationRunListener**
  - 遍历**SpringApplicationRunListener并调用其 starting 方法**
    - **即通知所有的设置，项目正在 starting**
  - 保存命令行参数：ApplicationArguments
  - 准备环境 prepareEnvironment()：
    - 返回或者创建基础环境信息对象：**StandardServletEnvironment**
    - **配置环境信息对象**
      - **读取所有的配置源的配置属性值**
    - 绑定环境信息
    - 监听器调用**`listener.environmentPrepared()`**，通知所有的监听器当前运行环境准备完成
  - 创建IOC容器：**createApplicationContext()**
    - 根据项目类型（Servlet）创建容器
    - 当前会创建 **AnnotationConfigServletWebServerApplicationContext**
  - **准备ApplicationContext容器的基本信息（IOC容器），通过`prepareContext()`**
    - 保存环境信息
    - IOC容器的后置处理流程
    - 应用初始化器：applyInitializers
      - 遍历所有的 **ApplicationContextInitializer，同时调用`initialize()`，来对IOC容器进行初始化扩展功能**
      - 遍历所有的listener并调用 **contextPrepared**（**EventPublishRunListener：通知所有的监听器contextPrepared**）
    - **所有的监听器都会调用 contextLoaded，来通知所有的监听器 contextLoaded**
  - **刷新IOC容器，通过`refreshContext()`**
    - 创建容器中的所有组件（Spring注解）
  - 容器刷新完成后，调用`afterRefresh()`
  - 所有监听器调用**`listener.started(context);`，去通知所有的监听器 started**
  - **调用所有的runners的`callRunners()`**
    - **获取容器中的 ApplicationRunner**
    - **获取容器中的 CommandLineRunner**
    - **合并所有的runner并且按照 `@Order` 注解进行排序**
    - **遍历所有的runner，并调用`run()`**
  - 如果以上操作都没有异常
    - **调用Litener的 `failed()`**
  - **调用所有的监听器的`running()` --> `listeners.running(context)`，去通知所有的监听器 running**
  - **如果running有异常，就继续通知 falled，去调用 Listener 的`falled()`**

```java
public interface Bootstapper {
    void intitialize(BootstrapRegistry regitry);
}
```

```java
@FunctionalInterface
public interface ApplicationRunner {
    void run(ApplicationArguments args) throws Exception;
}
```

```java
@FunctionalInterface
public interface CommandLineRunner {
    void run(String... args) throws Exception;
}
```

<img src="images/SpringApplicationRunListener.png" alt="">

<img src="images/七个Initializers.png" alt="">

#### 6-4-2.Application Events and Listeners

https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-application-events-and-listeners

**ApplicationContextInitializer**

**ApplicationListener**

#### 6-4-3.AppplicationRunner 和 CommandLineRunner