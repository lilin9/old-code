# 一、项目分析

## 1.项目功能：

- 登录
- 注册
- 热销商品
- 用户管理
  - 密码
  - 个人信息
  - 头像
  - 收货地址
- 购物车
  - 展示
  - 增加
  - 删除
- 订单模块

## 2.开发顺序

注册 --> 登录 --> 用户管理 --> 购物车 --> 商品 --> 订单模块

## 3.单一模块的开发

- 持久层开发：依据前端页面的设置规划相关的SQL语句，以及进行配置
- 业务层开发：核心功能控制、业务操作以及异常的处理
- 控制层开发：接受请求、处理响应
- 前端开发：JS、Query、Ajax等技术来连接后台

# 二、搭建项目

## 1.环境要求

- 1.JDK：11版本及以上的版本
- 2.maven：2.6.6版本及以上
- 3.数据库：MySQL 8.0及以上版本
- 4.开发平台：IDEA开发

## 2.搭建项目

- 1.项目名称：ComputerMall
- 2.项目结构：main.java.com.lilin
- 3.资源文件：resources文件夹下
- 4.单元测试:test.java.com.lilin

## 3.配置`application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/computer-mall?useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=123abc
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 4.创建数据库

```sql
create database computer_mall
```

## 5.测试连接

- 启动springBoot主类,是否有对应的 banner 输出
- 在单元测试类中测试数据库的连接是否可以正常的加载
- 测试访问目录的静态资源是否可以正常的加载,所有的静态资源都放到static目录下

> 注意：由于IDEA对于JS代码的兼容性差，编写lJS代码但是有的时候不能够正常的去加载，此时可以
>
> - 1、清理IDEA缓存
> - 2、clear-install
> - 3、rebuild重新构建
> - 4、重启Idea或者操作系统

# 三、注册登录功能的实现

## 1.创建数据表

创建 user 表

```mysql
CREATE TABLE t_user (
	uid INT AUTO_INCREMENT COMMENT '用户id',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
	password CHAR(32) NOT NULL COMMENT '密码',
	salt CHAR(36) COMMENT '盐值',
	phone VARCHAR(20) COMMENT '电话号码',
	email VARCHAR(30) COMMENT '电子邮箱',
	gender INT COMMENT '性别：0-女，1-男',
	avatar VARCHAR(50) COMMENT '头像',
	is_delete INT COMMENT '是否删除：0-未删除，1-已删除',
	created_user VARCHAR(20) COMMENT '日志-创建人',
	created_time DATETIME COMMENT '日志-创建时间',
	modified_user VARCHAR(20) COMMENT '日志-最后修改执行人',
	modified_time DATETIME COMMENT '日志-最后修改时间',
	PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

用户地址表

```mysql
CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT '收货地址id',
	uid INT COMMENT '归属的用户id',
	name VARCHAR(20) COMMENT '收货人姓名',
	province_name VARCHAR(15) COMMENT '省-名称',
	province_code CHAR(6) COMMENT '省-行政代号',
	city_name VARCHAR(15) COMMENT '市-名称',
	city_code CHAR(6) COMMENT '市-行政代号',
	area_name VARCHAR(15) COMMENT '区-名称',
	area_code CHAR(6) COMMENT '区-行政代号',
	zip CHAR(6) COMMENT '邮政编码',
	address VARCHAR(50) COMMENT '详细地址',
	phone VARCHAR(20) COMMENT '手机号码',
	tel VARCHAR(20) COMMENT '固定电话',
	tag VARCHAR(6) COMMENT '标签',
	is_default INT COMMENT '是否默认：0-不默认，1-默认',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## 2.创建用户的实体类

### 2.1 创建基类

通过表的结构提取出表的公共字段，以此创建一个实体类的基类，命名为`BaseEntity.java`

```java
@Data
public class BaseEntity implements Serializable {
    private String createdUser;     //日志-创建人
    private Date createdTime;       //日志-创建时间
    private String modifiedUser;    //日志-最后修改人
    private Date modifiedTime;      //日志-最后执行时间
}
```

### 2.2 创建实体类

创建用户的实体类需要实体类的基类

```java
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;        //用户id
    private String username;    //用户名
    private String password;    //密码
    private String salt;        //盐值
    private String phone;       //电话号码
    private String email;       //电子邮箱
    private Integer gender;     //性别：0-女，1-男
    private String avatar;      //头像
    private Integer isDelete;   //是否删除：0-未删除，1-已删除
}
```

创建地址类

```java
@EqualsAndHashCode(callSuper = true)
@Data
public class Address extends BaseEntity implements Serializable {
    private Integer aid;            //收货地址id
    private Integer uid;            //归属的用户id
    private String name;            //收货人姓名
    private String provinceName;    //省-名称
    private String provinceCode;    //省-行政代号
    private String cityName;        //市-名称
    private String city_code;       //市-行政代号
    private String areaName;        //区-名称
    private String areaCode;        //区-行政代号
    private String zip;             //邮政编码'
    private String address;         //详细地址
    private String phone;           //手机号码
    private String tel;             //固定电话
    private String tag;             //标签
    private Integer isDefault;      //是否默认：0-不默认，1-默认
}
```

## 3.注册功能的开发

### 3.1 持久层

通过MyBatis操作数据库，做Mybatis开发的流程

#### 3.1.1 设计接口和抽象方法

```java
public interface UserMapper {

    /**
     * @author LiLin
     * @create 2022/4/5 10:10
     * @return 返回受影响的函数
     * @description 插入用户数据
     */
    Integer insert(User user);
}
```

在启动类中配置mapper文件的位置

```java

@SpringBootApplication
@MapperScan("mapper")
public class MyBlogRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogRefactorApplication.class, args);
    }

}
```

在配置文件`application.properties`中注册`Mapper.xml`文件

```xml
mybatis.mapper-locations=classpath:mapper/*.xml
```

#### 3.1.2 编写映射文件

定义xml映射文件，与对应的接口进行关联，所有的映射文件都需要放在resources目录下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lilin.mapper.UserMapper">
    <resultMap id="UserEntityMapper" type="com.lilin.pojo.User">
        <!--在定义映射规则时主键不可省略-->
        <result property="uid" column="uid" />
        <result property="isDelete" column="is_delete" />
        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedTime" column="modified_time" />
    </resultMap>

    <!--Integer insert(User user);-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="uid">
        insert into
            t_user(username,`password`,salt,phone,email,gender,avatar,is_delete,created_user,created_time,modified_user,modified_time)
        values
               (#{username},#{password},#{salt},#{phone},#{email},#{gender},#{avatar},#{isDelete},#{createdUser},#{createdTime},#{modifiedUser},#{modifiedTime})
    </insert>
</mapper>
```

#### 3.1.3 单元测试

每个独立的层编写完毕后都需要编写单元测试方法，来测试当前的功能

```java
@SpringBootTest
//@RunWith(SpringRunner.class)：表示启动这个单元测试类（如果没有这个注解，单元测试类就不能启动）
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123abc");
        System.out.println(userMapper.insert(user));
    }
}
```

### 3.2 业务层

#### 3.2.1 异常规划

- 先创建`RuntimeException`异常的子类，即业务层异常的基类：`ServiceException`异常，然后再定义具体的异常类型来继承`ServiceException`异常，以此建立异常机制

  ```java
  public class ServiceException extends RuntimeException {
      public ServiceException() {
      }
  
      public ServiceException(String message) {
          super(message);
      }
  
      public ServiceException(String message, Throwable cause) {
          super(message, cause);
      }
  
      public ServiceException(Throwable cause) {
          super(cause);
      }
  
      public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
          super(message, cause, enableSuppression, writableStackTrace);
      }
  }
  ```

  > 根据业务层的不同功能来详细定义具体的异常类型，统一去继承`ServiceException`异常类

- 用户在进行注册的时候可能会产生用户名被占用的错误，此时可以抛出一个异常：`UsernameDuplicateException`异常

  ```java
  public class ServiceException extends RuntimeException {
      public ServiceException() {
      }
  
      public ServiceException(String message) {
          super(message);
      }
  
      public ServiceException(String message, Throwable cause) {
          super(message, cause);
      }
  
      public ServiceException(Throwable cause) {
          super(cause);
      }
  
      public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
          super(message, cause, enableSuppression, writableStackTrace);
      }
  }
  ```

- 正在执行数据插入操作的时候，服务器或数据库宕机，是正在执行插入操作的过程中产生的异常：`InsertException`异常

  ```java
  public class InsertException extends ServiceException {
      public InsertException() {
      }
  
      public InsertException(String message) {
          super(message);
      }
  
      public InsertException(String message, Throwable cause) {
          super(message, cause);
      }
  
      public InsertException(Throwable cause) {
          super(cause);
      }
  
      public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
          super(message, cause, enableSuppression, writableStackTrace);
      }
  }
  ```

#### 3.2.2 定义接口和实现类

接口

```java
public interface UserService {

    /**
     * @author LiLin
     * @create 2022/4/5 14:06
     * @description 实现用户注册操作
     */
    void register(User user);
}
```

实现类

```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    
    /**
     * @author LiLin
     * @create 2022/4/5 14:06
     * @description 实现用户注册操作
     */
    @Override
    public void register(User user) {
        //用户是否被删除
        user.setIsDelete(0);
        //日志-创建人
        user.setCreatedUser(user.getUsername());
        //日志-创建时间
        Date date = new Date();
        user.setCreatedTime(date);
        //日志-最后修改执行人
        user.setModifiedUser(user.getUsername());
        //日志-最后修改时间
        user.setModifiedTime(date);

        //判断用户注册信息是否可用
        String username = user.getUsername();
        if (userMapper.selectUserByUsername(username) == null) {
            //获取盐值（随机的字符串）
            String salt = UUID.randomUUID().toString().toUpperCase();
            //保存盐值到用户数据中
            user.setSalt(salt);
            //将密码进行加密操作
            String lockPassword = Md5Password.getMd5Password(user.getPassword(), salt);
            //将用户密码设置为加密后的密码
            user.setPassword(lockPassword);

            //如果用户名为空就插入用户信息
            Integer result = userMapper.insert(user);
            if (result != 1) {
                //如果出现用户插入失败则抛出以下异常
                throw new InsertException("在用户注册过程中出现不可知错误");
            }
        } else {
            //如果用户名不为空就抛出以下异常
            throw new UsernameDuplicateException("用户名已被占用");
        }
    }
}
```

#### 3.2.3 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("smith");
            user.setPassword("123abc");
            userService.register(user);
            System.out.println("执行完毕");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName() + " ---> " + e.getMessage());
        }
    }
}
```

### 3.3 控制层

#### 3.3.1 创建响应

将相关的状态码、状态描述信息、数据等信息封装在一个类中，再将此类作为方法返回值返回给前端浏览器

```java
@Data
@NoArgsConstructor
public class JsonResult<T> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //数据
    private T data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}
```

#### 3.3.2 处理请求

```java
//@Controller
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("register")
    //@ResponseBody //表示方法的返回值是JSON格式的
    public JsonResult<Void> register(User user) {
        JsonResult<Void> result = new JsonResult<>();
        try {
            //注册用户信息
            userService.register(user);
            //设置状态码
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicateException e) {
            //设置状态码
            result.setState(4000);
            result.setMessage("用户名已被占用");
        } catch (InsertException e) {
            //设置状态码
            result.setState(5000);
            result.setMessage("注册用户信息时产生未知错误");
        }
        return result;
    }
}
```

#### 3.3.3 控制层优化

在控制层中抽离出一个父类，在这个父类之中统一处理有关异常的相关操作，即编写一个`BaseController.java`

`BaseController.java`

```java
public class BaseController {
    public static final Integer OK = 200;

    /**
     * @author LiLin
     * @create 2022/4/5 16:22
     * @return 返回值是需要传递给前端的数据，即JSON数据
     * @description 用于请求中管理异常的统一方法
     *
     * 自动将异常对象传递给此方法的参数列表上
     */
    @ExceptionHandler(ServiceException.class) //指统一处理请求过程中抛出的异常
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);

        if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册用户信息时产生未知错误");
        } else if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名已被占用");
        }
        return result;
    }
}
```

优化后的`UserController.java`

```java
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping("register")
    //@ResponseBody //表示方法的返回值是JSON格式的
    public JsonResult<Void> register(User user) {
        //注册用户信息
        userService.register(user);
        //如果注册成功，返回 OK 状态码
        return new JsonResult<>(OK);
    }
}
```

### 3.4 前端页面

在register页面中编写发送请求的方法，通过点击事件完成

#### 3.4.1 Ajax介绍

JQuery封装了一个函数：`$.ajax()`，通过对象调用`ajax()`函数，可以异步加载相关的请求。依靠的是JavaScript提供的一个对象XHR(XmlHttpResponse)，封装了这个对象

ajax()的使用：

- 需要传递一个方法体作为方法的参数来使用（一对大括号就称之为一个方法体）
- ajax可以接受多个参数，参数和参数之间要求使用 “,” 进行分割
- 每一组参数之间使用 “;” 进行分割
- 参数的声明顺序没有意义
- 参数的组成部分
  - 一个是参数的名称（名称必须使用Ajax提供的，不能随意定义）
  - 一个是参数的值，要求用字符串来标识

语法结构：

```javascript
$.ajax({
    url: "",
    data: "",
    dataType: "",
    success: function() {
        
    },
    error: function() {
        
    }
});
```

`ajax()`函数参数的含义：

| 参数     | 功能描述                                                     |
| -------- | ------------------------------------------------------------ |
| url      | 标识请求的地址(url地址)，不能包含参数列表部分的内容。例如：url: "localhost:8080/users/register" |
| type     | 请求的类型（GET或POST请求）。例如：type: "POST"              |
| data     | 向指定的请求url地址需要提交的数据。例如：data: "username=tom&password=123" |
| dataType | 提交的数据的类型，数据的类型一般指定为JSON类型。例如：dataType: "json" |
| success  | 当服务器正常响应客户端时，会自动调用success参数的方法，并且将服务器返回的数据以参数的形式传递到这个方法的参数上 |
| error    | 当服务器没有正常响应客户端时，会自动调用error参数的方法，并且将服务器返回的数据以参数的形式传递到这个方法的参数上 |

#### 3.4.2 js代码的问题

js代码无法正常被服务器解析执行

- 体现在点击页面中的按钮没有任何响应
- 原因是：IDEA对js代码兼容性不好
- 解决方案：
  - 在项目的maven'下clear清理项目，install重新部署
  - 在项目的file选项下，cash清理缓存
  - 重新构建项目：build选项下，rebuild选项
  - 重启idea
  - 重启电脑

#### 3.4.3 代码

```javascript
<script>
    //1、监听注册按钮是否被点击
    $("#btn-reg").click(function () {
    $.ajax({
        url: $("#form-reg").serialize(),
        data: "username=?&password=?",
        dataType: "json",
        success: function (json) {
            let result = json.status
            if (result === 200) {
                alert("注册成功");
            } else if (result === 40000) {
                alert("用户名已被占用");
            } else if ((result === 5000)) {
                alert("注册用户信息时产生未知错误");
            }
        },
        error: function (xhr) {
            alert("注册时产生未知错误" + "\n" + xhr.status)
        }
    });
});
//2、发送Ajax的异步请求来完成用户注册功能
</script>
```

## 4.登录功能的开发

当用户输入用户名和密码后将数据提交给后台进行查询，如果存在对应的用户信息则表示登录成功，在登录成功后跳转到首页：`index.html`

> 如果在分析过程中发现某个功能模块已经被开发完成，就可以省略当前的开发步骤，但是开发之前的分析过程不可以省略。

###  4.1 持久层

#### 4.1.1 规划SQL

根据用户提交的用户名和密码做select查询（密码比较在业务层进行）

```mysql
select * from t_user where username=?
```

#### 4.1.2 设计接口和抽象方法

```java
public interface UserMapper {

    /**
     * @author LiLin
     * @create 2022/4/5 10:10
     * @return 返回受影响的函数
     * @description 插入用户数据
     */
    Integer insert(User user);

    /**
     * @author LiLin
     * @create 2022/4/5 10:16
     * @return 如果查询成功返回用户数据，如果查询失败返回null
     * @description 根据用户名查询用户信息
     */
    User selectUserByUsername(String username);
}
```

#### 4.1.3 单元测试

```java
@SpringBootTest
//@RunWith(SpringRunner.class)：表示启动这个单元测试类（如果没有这个注解，单元测试类就不能启动）
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123abc");
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void selectUserByUsername() {
        User user = userMapper.selectUserByUsername("admin");
        System.out.println(user);
    }
}
```

### 4.2 业务层

#### 4.2.1 规划异常

- 用户名对应的密码错误、密码匹配失败的异常：`PasswordNotMatchException.java`异常（运行时异常、业务异常）
- 用户名没有被找到异常：`UsernameNotFoundException.java`（运行异常、业务异常）
- 用户没有被找到异常：`UserNotFoundException.java`（运行异常、业务异常）
- 编写异常：
  - 业务层异常都需要继承`ServiceException.java`异常类
  - 在具体的异常类中定义构造方法（五个）

```java
//用户名对应的密码错误、密码匹配失败
public class PasswordNotMatchException extends ServiceException {
    public PasswordNotMatchException() {
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

```java
//用户名没有被找到异常
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

```java
//用户没有被找到异常
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

#### 4.2.2 设计接口类

```java
/**
     * @author LiLin
     * @create 2022/4/5 14:06
     * @description 实现用户登录操作
     */
User login(String username, String password);
```

#### 4.2.3 设计实现类

```java
/**
     * @author LiLin
     * @create 2022/4/5 14:04
     * @return 如果返回值为null说明登录失败；如果返回用户信息说明登录成功
     * @description 根据用户名和密码实现登录操作
     */
@Override
public User login(String username, String password) {
    //根据用户名查询用户数据是否存在
    User result = userMapper.selectUserByUsername(username);
    if (result == null) {
        //如果用户数据为空
        //抛出下列异常
        throw new UsernameNotFoundException("用户不存在");
    }

    //如果用户数据不为空
    //检查用户密码是否匹配
    //获取数据库中加密后的密码
    String oldPassword = result.getPassword();
    //先获取盐值，即用户信息中保存的盐值
    String salt = result.getSalt();
    //将用户密码按照相同规则就行md5加密
    String newPassword = Md5Password.getMd5Password(password, salt);
    //和用户传递过来的密码进行比较
    if (!newPassword.equalsIgnoreCase(oldPassword)) {
        //如果新旧密码不匹配
        throw new PasswordNotMatchException("密码错误");
    }

    //判断用户信息是否已被删除
    if (result.getIsDelete() == 1) {
        throw new UserNotFoundException("用户不存在");
    }

    //根据uid、用户名、用户头像重新封装一个user用户
    User user = new User();
    user.setUid(result.getUid());
    user.setUsername(result.getUsername());
    user.setAvatar(result.getAvatar());

    return user;
}
```

#### 4.2.4 单元测试

```java
    @Test
    public void login() {
        System.out.println(userService.login("amy", "123456"));
    }
```

> 问题：如果一个类没有手动创建，而是直接将其复制粘贴到项目中，idea就会找不到这个类
>
> 原因：由于之前的缓存导致无法正常找到这个类的符号
>
> 解决：重新构建项目

### 4.3 控制层

#### 4.3.1 处理异常

业务层抛出的异常，需要在统一异常处理类中进行统一的捕获和处理（如果抛出的异常类型在同一异常处理类中已经有了，就不需要重复添加）

```java
else if (e instanceof UserNotFoundException) {
    result.setState(5001);
    result.setMessage("用户信息不存在");
} else if (e instanceof PasswordNotMatchException) {
    result.setState(5002);
    result.setMessage("用户密码错误");
```

#### 4.3.2 设计请求

- url：/users/login
- type：POST
- param：String username, String password, HttpSession session
- return：JsonResult\<User>

#### 4.3.3 处理请求

```java
@RequestMapping("login")
public JsonResult<User> login(String username, String password) {
    return new JsonResult<>(OK, userService.login(username, password));
}
```

### 4.4 前端页面

```javascript
<script>
    $("#btn-login").click(function () {
    $.ajax({
        url: "/users/login",
        type: "POST",
        data: $("#form-login").serialize(),
        dataType: "json",
        success: function (json) {
            //如果登录成功，跳转到首页
            if (json.state === 200) {
                alert("登录成功");
                location.href = "index.html";
            } else {
                alert("登录失败");
            }
        },
        error: function (xhr) {
            alert("登录过程中发生未知错误" + "\n" + xhr.message);
        }
    });
});
</script>
```

## 5.session保存用户数据

session对象主要存在服务器端，可以用于保存服务器的临时数据对象，session保存的数据在整个项目中都可以通过访问来获取，可以把session的数据看作是一个共享的数据。

可以将首次登录时所获取的用户数据，转移到session对象中。

### 5.1 创建工具方法

步骤：

- 1、封装session对象中数据的获取（封装到父类中）、数据的设置（当用户登录后将数据设置到全局session中）
- 2、在父类中封装获取uid和获取username的两个方法（用户头像将会封装到cookie中）

```java
/**
     * @author LiLin
     * @create 2022/4/6 14:48
     * @return 返回uid
     * @description 从session域中获取用户的uid
     */
protected final Integer getUidFromSession(HttpSession session) {
    return Integer.valueOf(session.getAttribute("uid").toString());
}

/**
     * @author LiLin
     * @create 2022/4/6 14:53
     * @return 返回用户名
     * @description 从session中获取用户的username
     */
protected  final String getUsernameFromSession(HttpSession session) {
    return session.getAttribute("username").toString();
}
```

> 服务器本身自动创建有session对象，这个session对象已经是一个全局的session对象。而SpringBoot会直接使用session对象，直接将HttpSession类型的对象作为请求处理方法的参数值，会自动将全局的session对象注入到请求处理方法的session形参中。

### 5.2 优化登录功能

```java
@PostMapping("login")
public JsonResult<User> login(String username, String password, HttpSession session) {
    User data = userService.login(username, password);
    session.setAttribute("uid", data.getUid());
    session.setAttribute("username", data.getUsername());
    return new JsonResult<>(OK, data);
}
```

## 6.拦截器

将所有的请求统一拦截到拦截器中，可以在拦截其中定义过滤的规则。如果不满足系统设置的过滤规则，统一的处理是重新去打开`login.html`页面（重定向或转发）

在springBoot中拦截器的定义和使用是依靠springMVC来完成的，springMVC提供了一个`HandlerInterceptor`接口，来实现拦截功能。

### 6.1 定义拦截器

```java
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * @author LiLin
     * @create 2022/4/6 15:36
     * @description 在调用所有请求处理的方法之前被自动调用执行的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //查询session域中的uid
        Object uid = request.getSession().getAttribute("uid");
        //判断uid是否为空
        if (uid != null) {
            //如果不为空就放行
            return true;
        }
        //如果为空就重定向到登录页面
        response.sendRedirect("/web/login.html");
        //打印提示信息
        //response.getWriter().write("请先登录");
        //结束后续操作，不放行
        return false;
    }

    //在ModelAndView对象返回之后被调用的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //在整个请求所有关联的资源被执行完毕最后所执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
```

### 6.2 注册拦截器

通过借助`WebMvcConfigure.java`接口，可以将用户定义的拦截器进行注册，才可以保证拦截器可以生效和使用

步骤：

- 1、添加白名单：login.html、register.html、login、register、index.html、product.html
- 2、添加黑名单：/**

```java
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        registry.addInterceptor(interceptor)
                //对所有请求进行拦截
                .addPathPatterns("/**")
                //添加拦截白名单
                .excludePathPatterns("/bootstrap3/**",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/web/login.html",
                        "/web/register.html",
                        "/web/product.html",
                        "/web/index.html",
                        "/index.html",
                        "/users/login",
                        "/users/register");
    }
}
```

> 有一个浏览器的问题：当重定向次数过多，将会导致白名单的页面无法打开
>
> 解决方案：清空浏览器的cookie请求，再初始化浏览器

# 四、修改用户资料

## 1.修改密码

需要用户提交原始密码和新密码，再根据当前登录用户的uid进行信息的修改操作

### 1.1 持久层

#### 1.1.1 规划SQL

根据用户的uid修改用户的password值

```mysql
update t_user set `password`=?,modified_user=?,modified_time=? where uid=?
```

根据uid查询用户的数据，在修改密码之前，首先要保证当前用户的数据存在，即检车是否被标记为删除，然后要检验输入的原始密码是否正确

```mysql
select * from t_user where uid=?
```

#### 1.1.2 设计接口和抽象方法

```java
/**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @return 返回被影响的行数
     * @description 根据用户uid修改用户密码
     */
Integer updatePasswordByUid(Integer uid, 
                            String password, 
                            String modifiedUser, 
                            Date modifiedTime);
```

#### 1.2.3 编写映射文件

```xml
<!--Integer updatePasswordByUid(Integer uid);-->
<update id="updatePasswordByUid">
    update
    t_user
    set
    `password`=#{password}, modified_user=#{modifiedUser}, modified_time=#{modifiedTime}
    where
    uid=#{uid}
</update>
```

#### 1.2.4 单元测试

```java
@Test
public void updatePassword() {
    userMapper.updatePasswordByUid(1, "123456", "admin", new Date());
}
```

### 1.2 业务层

#### 1.2.1 异常规划

用户的原密码错误：is_delete==1、uid找不到，即用户找不到的异常：`UserNotFoundException.java`

update在更新的时候，可能会产生未知的异常：`UpdateException.java`

```java
public class UpdateException extends ServiceException {
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

#### 1.2.2 设计接口类和实现类

接口

```java
/**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @description 根据用户uid修改用户密码
     */
void updatePasswordByUid(Integer uid,
                         String newPassword,
                         String oldPassword);
```

实现类

```java
/**
     * @author LiLin
     * @create 2022/4/6 17:02
     * @description 根据用户uid修改用户密码
     */
@Override
public void updatePasswordByUid(Integer uid, String newPassword, String oldPassword) {
    //根据用户的uid查询用户信息
    User user = userMapper.selectUserByUid(uid);

    //判断用户是否存在
    if (user == null) {
        //如果用户不存在
        //执行以下异常
        throw new UserNotFoundException("用户不存在");
    }

    //判断用户账户是否被删除
    if (user.getIsDelete() == 1) {
        //如果用户账户被删除
        //执行以下异常
        throw new UserNotFoundException("用户不存在");
    }

    //获取盐值
    String salt = user.getSalt();
    //对用户的旧密码进行md5加密
    String lockPassword = Md5Password.getMd5Password(oldPassword, salt);
    //比较用户数据库密码和旧密码是否一致
    if (!lockPassword.equalsIgnoreCase(user.getPassword())) {
        //如果不一致抛出以下异常
        throw new PasswordNotMatchException("用户密码输入错误");
    }

    //如果用户没有被删除/用户存在
    //如果以上操作都无异常，执行修改用户密码操作
    Integer result = userMapper.updatePasswordByUid(uid, Md5Password.getMd5Password(newPassword, salt), user.getUsername(), new Date());
    //判断修改用户密码操作是否正确被执行
    if (result == null) {
        //如果修改用户密码操作没有被正确执行
        //执行以下异常
        throw new UpdateException("修改用户信息过程中出现不可知错误");
    }
}
```

#### 1.2.3 单元测试

```java
@Test
public void deletePasswordByUid() {
    //FABC4444121B4A1A3E17096428FF6BE4
    //24BA998850D43389B4FD5B607A500504
    userService.updatePasswordByUid(6, "123abc", "123456");
}
```

### 1.3 控制层

#### 1.3.1 处理异常

将`updateException`异常配置在控制层统一异常处理中

```java
else if (e instanceof UpdateException) {
    result.setState(5001);
    result.setMessage("更新用户信息时产生未知错误");

}
```

#### 1.3.2 设计请求

- url：/users/update_password
- type：post
- param：String oldPassword, String newPassword, HttpSession session（需要和表单中的name属性值保持一致）
- return：JsonResult\<Void>

#### 1.3.3 处理请求

```java
@PostMapping("update_password")
public JsonResult<Void> updatePassword(@Param("oldPassword") String oldPassword,
                                       @Param("newPassword") String newPassword,
                                       HttpSession session
                                      ) {
    userService.updatePasswordByUid(getUidFromSession(session),
                                    getUsernameFromSession(session),
                                    newPassword,
                                    oldPassword);
    return new JsonResult<>(OK);
}
```

### 1.4 前端页面

```javascript
<script>
    $("#btn-change-password").click(function () {
    $.ajax({
        url: "/users/update_password",
        type: "POST",
        data: $("#form-change-password").serialize(),
        dataType: "json",
        success: function (json) {
            //如果登录成功，跳转到首页
            if (json.state === 200) {
                alert("密码修改成功");
            } else {
                alert("密码修改失败");
            }
        },
        error: function (xhr) {
            alert("更新用户数据中出现不可知错误" + "\n" + xhr.message);
        }
    });
});
</script>
```

## 2.修改个人资料

### 2.1 持久层

#### 2.1.1 规划SQL

根据uid更新用户信息

```mysql
update t_user set phone=?,email=?,gender=?,modified_user=?,modified_time=? where uid=?
```

根据uid查询用户数据

```mysql
select * from t_user where uid=?
```

> 不需要再重复开发

#### 2.1.2 设计接口和抽象方法

```java
/**
     * @author LiLin
     * @create 2022/4/7 14:48
     * @return 返回用户信息
     * @description 根据uid更改用户的信息
     */
User updateInfoByUid(User user);
```

#### 2.1.3 编写映射文件

```xml
<!--User updateInfoByUid(Integer uid);-->
<update id="updateInfoByUid">
    update
    	t_user
    set
    	<if test="phone != null">`phone`=#{phone},</if>
    	<if test="email != null">`email`=#{email},</if>
    	<if test="gender != null">`gender`=#{gender},</if>
    	modified_user=#{modifiedUser},
    	modified_time=#{modifiedTime}
    where
    	uid=#{uid}
</update>
```

#### 2.1.4 单元测试

```java
@Test
public void updateInfoByUid() {
    User user = new User();
    user.setUid(6);
    user.setPhone("18593237840");
    user.setEmail("amy@qq.com");
    user.setGender(0);
    user.setModifiedUser("admin");
    user.setModifiedTime(new Date());

    Integer result = userMapper.updateInfoByUid(user);
    System.out.println("影响力" + result + "行");
}
```

### 2.2 业务层

设计两个功能：

- 当打开页面就获取用户的信息并且填充到对应的文本框中
- 检测用户是否点击了修改按钮，如果检测到点击了就执行修改用户信息的操作

#### 2.2.1 异常规划

可能发送的异常：

- 打开页面的时候可能找不到用户的数据：`updateException.java`
- 在用户点击删除按钮之前用户的数据可能被错误删除：`deleteException.java`

#### 2.2.2 设计接口和实现类

两个功能模块，对于两个抽象方法

```java
/**
     * @author LiLin
     * @create 2022/4/7 15:27
     * @return 返回用户的信息
     * @description 根据uid查询用户信息
     */
User getUserByUid(Integer uid);


/**
     * @author LiLin
     * @create 2022/4/7 15:43
     * @description 根据uid更新用户信息
     */
void updateInfo(Integer uid, String username , User user);
```

```java
    /**
     * @author LiLin
     * @create 2022/4/7 15:27
     * @return 返回用户的信息
     * @description 根据uid查询用户信息
     */
    @Override
    public User getUserByUid(Integer uid) {
        User result = userMapper.selectUserByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFoundException("用户不存在");
        }

        //重新包装前端页面需要的数据
        User user = new User();
        user.setUid(result.getUid());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * @author LiLin
     * @create 2022/4/7 15:43
     * @description 根据uid更新用户信息
     */
    @Override
    public void updateInfo(Integer uid, String username, User user) {
        User result = userMapper.selectUserByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFoundException("用户不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        //更新数据
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新用户数据时出现未知错误");
        }
    }
```

#### 2.2.3 单元测试

```java
@Test
public void updateInfo() {
    User user = new User();
    user.setPhone("11111222223");
    user.setEmail("smith@qq.com");
    user.setGender(1);

    userService.updateInfo(3, "smith", user);
}
```

### 2.3 控制层

#### 2.3.1 处理异常

> 无新异常

#### 2.3.2 设计请求

设置一打开页面就要发送当前用户数据的查询

- url：/users/get_user
- type：GET
- param：HttpSession session
- return：JsonResult\<User>

点击修改按钮就发送用户的数据进行修改操作

- url：/users/update_info
- type：GET
- param：User user, HttpSession session
- return：JsonResult\<Void>

#### 2.3.3 处理请求

```java
@GetMapping("get_user")
public JsonResult<User> getUser(HttpSession session) {
    User data = userService.getUserByUid(getUidFromSession(session));
    return new JsonResult<>(OK, data);
}

@GetMapping("update_info")
public JsonResult<Void> updateInfo(User user, HttpSession session) {
    userService.updateInfo(getUidFromSession(session),
                           getUsernameFromSession(session),
                           user);

    return new JsonResult<>(OK);
}
```

### 2.4 前端页面

```javascript
<script>
    $("#btn-change-info").click(function () {
    $.ajax({
        url: "/users/update_info",
        type: "POST",
        data: $("#form-change-info").serialize(),
        dataType: "json",
        success: function (json) {
            //如果登录成功，跳转到首页
            if (json.state === 200) {
                alert("用户资料修改成功");
                //资料修改完成，重新加载页面
                location.href = "userdata.html";
            } else {
                alert("用户资料修改失败");
            }
        },
        error: function (xhr) {
            alert("更新用户数据中出现不可知错误" + "\n" + xhr.message);
        }
    });
});

//在页面打开前加载
$(document).ready(function () {
    $.ajax({
        url: "/users/get_user",
        type: "GET",
        data: $("#form-change-info").serialize(),
        dataType: "json",
        success: function (json) {
            if (json.state === 200) {
                //将查询到的数据放到控件中
                $("#username").val(json.data.username);
                $("#phone").val(json.data.phone);
                $("#email").val(json.data.email);
                let radio = json.data.gender === 0 ? $("#gender-female") : $("#gender-male");
                radio.prop("checked", "checked");
            } else {
                alert("查询数据失败");
            }
        },
        error: function (xhr) {
            alert("查询数据过程中发生未知错误" + "\n" + xhr.message);
        }
    });
});
</script>
```

## 3.上传头像

### 3.1 持久层

将对象文件保存在操作系统中，再将这个文件的文件路径记录下来。因为记录文件保存路径是十分便捷和方便的，如果要打开这个文件只要根据路径找寻到文件即可。

> 在企业开发中都会将静态资源（图片、EXCEL、音视频等）保存在一台电脑上，之后再将这台电脑作为单独的一台服务器。

#### 3.1.1 规划SQL

对应一个更新用户avatar字段的sql语句：

```mysql
update t_user set avatar=?,modified_user=?,modified_time=? where uid=?
```

#### 3.1.2 设计接口和抽象方法

```java
/**
     * @author LiLin
     * @create 2022/4/8 13:38
     * @return 返回被影响的行数
     * @description 根据uid更改头像的储存地址
     */
Integer updateAvatarByUid(Integer uid, String avatar, String modifiedUser, Date modifiedTime);
```

#### 3.1.3 编写映射文件

```xml
<!--Integer updateAvatarByUid(Integer uid);-->
<update id="updateAvatarByUid">
    update
    	t_user
    set
    	avatar=#{avatar},modified_user=#{modifiedUser},modified_time=#{modifiedTime}
    where
    	uid=#{uid}
</update>
```

#### 3.1.4 单元测试

```java
@Test
public void updateAvatarByUid() {
    System.out.println(userMapper.updateAvatarByUid(6, "/images/avatar/1.jpg", "amy", new Date()));
}
```

### 3.2 业务层

#### 3.2.1 规划异常

- 用户数据不存在，找不到对应的用户数据：`UserNotFoundException.java`
- 更新用户数据的时候，有未知异常产生：`UpdateException.java`

#### 3.2.2 设计接口

```java
/**
     * @author LiLin
     * @create 2022/4/8 14:11
     * @description 根据用户uid更新用户头像
     */
void updateAvatar(Integer uid, String avatar, String username);
```

#### 3.2.3 设计实现类

```java
/**
     * @Author lilin
     * @Date 2022/4/8 15:00
     * @Param uid 用户id
     * @Param avatar 头像
     * @Param username 修改人姓名
     * @Description 根据uid更新用户头像
     */
@Override
public void updateAvatar(Integer uid, String avatar, String username) {
    //根据uid获取用户信息
    User result = userMapper.selectUserByUid(uid);

    if (result == null || result.getIsDelete() == 1) {
        //如果用户不存在或者用户已经被删除，抛出以下异常
        throw new UserNotFoundException("用户不存在");
    }

    //更新用户头像
    Integer integer = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
    if (integer != 1) {
        //如果更新头像失败，抛出以下异常
        throw new UpdateException("更新用户头像过程中出现未知错误");
    }
}
```

#### 3.2.4 单元测试

```java
@Test
public void updateAvatarByUid() {
    userService.updateAvatar(1, "/images/avatar/1.jpg", "admin");
}
```

### 3.3 控制层

#### 3.3.1 规划异常

- FileUploadException：泛指文件上传异常（作为父类继承`RuntimeException`）
  - FileEmptyException：文件为空的异常
  - FileSizeException：文件大小超出限制异常
  - FileTypeException：文件类型异常
  - FileUploadIOException：文件读写的异常
  - FileStatusException：文件状态异常

#### 3.3.2 处理异常

在基类`BaseController.java`中对异常进行统一管理

```java
@ExceptionHandler({ServiceException.class, FileUploadException.class}) //指统一处理请求过程中抛出的异常
public JsonResult<Void> handlerException(Throwable e) {
    JsonResult<Void> result = new JsonResult<>(e);

    if (e instanceof InsertException) {
        result.setState(5000);
        result.setMessage("注册用户信息时产生未知错误");
    }  else if (e instanceof UpdateException) {
        result.setState(5001);
        result.setMessage("更新用户信息时产生未知错误");
    } else if (e instanceof UsernameDuplicateException) {
        result.setState(4000);
        result.setMessage("用户名已被占用");
    } else if (e instanceof UserNotFoundException) {
        result.setState(4001);
        result.setMessage("用户信息不存在");
    } else if (e instanceof PasswordNotMatchException) {
        result.setState(4002);
        result.setMessage("用户密码错误");
    } else if (e instanceof FileEmptyException) {
        result.setState(6000);
        result.setMessage("文件为空");
    } else if (e instanceof FileSizeException) {
        result.setState(6001);
        result.setMessage("文件大小超出限制");
    } else if (e instanceof FileStatusException) {
        result.setState(6002);
        result.setMessage("文件状态异常");
    } else if (e instanceof FileTypeException) {
        result.setState(6003);
        result.setMessage("文件类型错误");
    } else if (e instanceof FileUploadIOException) {
        result.setState(6004);
        result.setMessage("读取文件失败");
    }
    return result;
}
```

#### 3.3.3 设计请求

- url：/users/update_avatar
- type：POST（GET请求提交的数据只有2KB）
- param：HttpSession session, MultipartFile file
- return：JsonResult\<String>

#### 3.3.4 处理请求

```java
/*
    MultipartFile 是 SpringMvc 提供的一个接口，这接口包装了获取的文件类型的数据（任何类型的文件都可以接收），
    因为 SpringBoot 又整合了 SpringMvc，所以只需要在处理请求方法的参数列表上声明一个参数类型为 MultipartFile 类型的参数，
    SpringBoot 就会自动将前端传进来的文件数据赋值给这个参数
     */

//设置上传的头像最大值
private static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
//设置上传头像的类型
private static final List<String> AVATAR_TYPE = new ArrayList<>();
static {
    AVATAR_TYPE.add("image/jpeg");
    AVATAR_TYPE.add("image/png");
    AVATAR_TYPE.add("image/gif");
    AVATAR_TYPE.add("image/bmp");
}

@PostMapping("update_avatar")
public JsonResult<String> updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
    //判断文件是否为空
    if (file.isEmpty()) throw new FileEmptyException("文件为空");
    //判断文件大小是否超出限制
    if (file.getSize() > AVATAR_MAX_SIZE) throw new FileSizeException("文件大小超出了限制");
    //判断文件类型是否符合要求
    if (!AVATAR_TYPE.contains(file.getContentType())) throw new FileTypeException("文件类型不符合要求");

    //获取上传文件的上下文路径
    String parent = session.getServletContext().getRealPath("avatar");
    File dir = new File(parent);
    //如果文件目录不存在，就创建文件目录
    if (!dir.exists()) dir.mkdirs();

    //获取到文件名
    String originalFilename = file.getOriginalFilename();
    //获取到后缀
    assert originalFilename != null;
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    //拼接获取文件名
    String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
    try {
        //先获取一个空文件夹：new File(dir, filename)
        //然后保存头像文件写入到空文件夹中：file.transferTo()
        file.transferTo(new File(dir, filename));
    } catch (IOException e) {
        throw new FileUploadIOException("文件读写过程中出现异常");
    } catch (FileStatusException e) {
        throw new FileStatusException("文件状态异常");
    }

    //头像路径只要返回相对路径
    String avatar = "/avatar" + filename;
    userService.updateAvatar(getUidFromSession(session), avatar, getUsernameFromSession(session));
    return new JsonResult<>(OK, avatar);
}
```

### 3.4 前端页面

#### 3.4.1 上传头像

> 在直接使用表单进行文件的上传时，需要给表单显示的添加一个属性：enctype="multipart/form-data"
>
> 如此可以确保不会损坏目标文件的数据结构

```html
<form action="/users/update_avatar" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
</form>
```

#### 3.4.2 显示头像

在页面中可以通过Ajax请求来提交文件，在提交完成之后返回JSON字符串，再解析出data中的数据，就可以设置到img头像标签中的src属性上。

- serialize()：可以将表单中的数据自动拼接成`key=value`的结构后提交给服务器，一般时提交普通的控件类型中的数据（text、password、radio、checkbox等等）

- FormData类：会将表单中的数据在保持原有的结构的时候进行数据的提交

  ```javascript
  new FromData($("#form")[0]); 文件类型的数据可以通过使用 FormData 对象进行储存
  ```

- 因为Ajax在处理数据时默认按照字符串的形式进行处理，以及会默认采用字符串的形式提交数据，所以需要关闭这两个默认的功能

  ```javascript
  processData: false,   //处理数据的形式，默认开启的
  contentType: false,   //提交数据的形式，默认开启的
  ```

```javascript
<script>
    $("#btn-change-avatar").click(function () {
        $.ajax({
            url: "/users/update_avatar",
            type: "POST",
            data: new FormData($("#form-change-avatar")[0]),
            processData: false,   //处理数据的形式，默认开启的
            contentType: false,   //提交数据的形式，默认开启的
            dataType: "json",
            success: function (json) {
                //如果登录成功，跳转到首页
                if (json.state === 200) {
                    alert("头像修改成功");
                    $("#img-avatar").attr("src", json.data);
                } else {
                    alert("头像修改失败");
                }
            },
            error: function (xhr) {
                alert("更新头像中出现不可知错误" + "\n" + xhr.message);
            }
        });
    });
</script>
```

#### 3.4.3 cookie保存头像

在更新头像成功后，将服务器返回的头像保存在客户端的cookie对象中，然后在每次检测到用户打开头像页面时，在页面中通过`ready()`方法自动检测读取cookie中的头像并设置到src属性上

步骤：

- 1、导入`cookie.js`文件

  ```html
  <script src="../bootstrap3/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
  ```

- 2、调用cookie

  ```javascript
  $.cookie(key, value, time); //单位：天
  ```

```javascript
$(document).ready(function () {
    const avatar = $.cookie("avatar");
    $("#img-avatar").attr("src", avatar);
});
```

> cookie 中的 avatar 是在登录页面中保存到 cookie 中的

### 3.5 Bug

#### 3.5.1 更改默认大小限制

SpringMvc默认可以上传的文件大小为1MB，可以手动的修改SpringMvc默认上传文件的大小

方法一：直接在配置文件中配置

```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=100MB
```

方法二：通过Java代码设置文件上传大小的限制，需要在主类中进行配置，为主类加上`@Configuration`注解，然后定义一个用`@Bean`修饰，`MultipartConfigElement`类型的方法

```java
@Configuration
@SpringBootApplication
@MapperScan("com.lilin.mapper")
public class MyBlogRefactorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogRefactorApplication.class, args);
    }

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        //创建配置的工厂类对象
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //设置上传的文件大小
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));

        //通过工厂类来创建 MultipartConfigElement 对象
        return factory.createMultipartConfig();
    }

}
```

# 五、收货地址

## 1.新增收货地址

功能模块：列表的展示、修改、删除、设置默认、新增收货地址

开发顺序：新增收货地址 --> 列表展示 --> 设置默认收货地址 --> 删除收货地址 --> 修改收货地址

### 1.1 持久层

#### 1.1.1 建立数据表

```mysql
CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT '收货地址id',
	uid INT COMMENT '归属的用户id',
	`name` VARCHAR(20) COMMENT '收货人姓名',
	province_name VARCHAR(15) COMMENT '省-名称',
	province_code CHAR(6) COMMENT '省-行政代号',
	city_name VARCHAR(15) COMMENT '市-名称',
	city_code CHAR(6) COMMENT '市-行政代号',
	area_name VARCHAR(15) COMMENT '区-名称',
	area_code CHAR(6) COMMENT '区-行政代号',
	zip CHAR(6) COMMENT '邮政编码',
	address VARCHAR(50) COMMENT '详细地址',
	phone VARCHAR(20) COMMENT '手机号码',
	tel VARCHAR(20) COMMENT '固定电话',
	tag VARCHAR(6) COMMENT '标签',
	is_default INT COMMENT '是否默认：0-不默认，1-默认',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

#### 1.1.2 创建实体类

```java
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Address extends BaseEntity implements Serializable {
    private Integer aid;            //收货地址id
    private Integer uid;            //归属的用户id
    private String name;            //收货人姓名
    private String provinceName;    //省-名称
    private String provinceCode;    //省-行政代号
    private String cityName;        //市-名称
    private String city_code;       //市-行政代号
    private String areaName;        //区-名称
    private String areaCode;        //区-行政代号
    private String zip;             //邮政编码'
    private String address;         //详细地址
    private String phone;           //手机号码
    private String tel;             //固定电话
    private String tag;             //标签
    private Integer isDefault;      //是否默认：0-不默认，1-默认
}
```

#### 1.1.3 规划SQL

插入语句

```mysql
insert into t_address(处理aid外的字段列表) values(字段值列表)
```

一个用户的收货地址规定最多只能有20条数据，故还有查询语句

```mysql
select count(*) from t_address where uid=?
```

#### 1.1.4 接口和抽象方法

```java
public interface AddressMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/9 16:55
     * @Param address 收货地址实体类
     * @return 返回影响的行数
     * @Description 增加新的收货地址
     */
    Integer insert(Address address);

    /**
     * @Author LiLin
     * @Date 2022/4/9 17:00
     * @Param uid 用户的id
     * @return 返回用户的收货地址个数
     * @Description 根据用户的id统计收货地址的个数
     */
    Integer countAddressByUid(Integer uid);
}
```

#### 1.1.5 SQL映射文件

```xm
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lilin.mapper.AddressMapper">

    <resultMap id="AddressEntityMapper" type="com.lilin.pojo.Address">
        <!--在定义映射规则时主键不可省略-->
        <result property="aid" column="aid" />
        <result property="provinceName" column="province_name" />
        <result property="provinceCode" column="province_code" />
        <result property="cityName" column="city_name" />
        <result property="cityCode" column="city_code" />
        <result property="areaName" column="area_name" />
        <result property="areaCode" column="area_code" />
        <result property="isDefault" column="is_default" />
        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedUser" column="modified_time" />
    </resultMap>

    <!--Integer insert(Address address);-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="aid">
        insert into
            t_address(uid,`name`,province_name,province_code,city_name,city_code,area_name,
                      area_code,zip,address,phone,tel,tag,is_default,created_user,created_time,
                      modified_user,modified_time)
        values
            (#{uid},#{name},#{provinceName},#{provinceCode},#{cityName},#{cityCode},#{areaName},
             #{areaCode},#{zip},#{address},#{phone},#{tel},#{tag},#{isDefault},#{createdUser},
             #{createdTime},#{modifiedUser},#{modifiedTime})
    </insert>
    <!--Integer countAddressByUid(Integer uid);-->
    <select id="countAddressByUid" resultType="java.lang.Integer">
        select
               count(*)
        from
             t_address
        where
              uid=#{uid}
    </select>
</mapper>
```

#### 1.1.6 单元测试

```java
@SpringBootTest
//@RunWith(SpringRunner.class)：表示启动这个单元测试类（如果没有这个注解，单元测试类就不能启动）
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setName("admin");
        address.setPhone("11111111111");
        addressMapper.insert(address);
    }

    @Test
    public void countAddressByUid() {
        System.out.println(addressMapper.countAddressByUid(1));
    }
}
```

### 1.2 业务层

#### 1.2.1 异常规划

- 1.如果用户是第一次插入收货地址，要遵循的规则：当用户插入的地址是第一条时，需要将当前地址作为默认的收货地址；如果查询到统计总数为0，则要将当前地址的 is_default 值设置为 1；如果查询统计的结果为 0，则无异常。
- 2.如果查询到的结果总数大于20，此时需要抛出业务控制的异常：`AddressCountLimitException.java`异常
- 3.在插入数据时可能会发生异常：`InsertException.java`

#### 1.2.2 接口和抽象方法

```java
public interface AddressService {
    /**
     * @Author LiLin
     * @Date 2022/4/12 10:36
     * @Param uid 用户的id
     * @Param username 用户名
     * @Param address 收货地址
     * @Description 添加新的收货地址
     */
    void addNewAddress(Integer uid, String username, Address address);
}
```

#### 1.2.3 实现抽象方法

在配置文件`application.properties`中定义收货地址最大个数

```properties
user.address.max-count=20
```

实现抽象方法

```java
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    //用户最多地址数
    //最大个数从配置文件 application.properties 中读取
    @Value("${user.address.max-count}")
    Integer MAX_COUNT;

    /**
     * @Author LiLin
     * @Date 2022/4/12 10:36
     * @Param uid 用户的id
     * @Param username 用户名
     * @Param address 收货地址
     * @Description 添加新的收货地址
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //判断用户收货地址数是否超出最大个数限制
        Integer count = addressMapper.countAddressByUid(uid);
        //超出了抛出异常
        if (count >= MAX_COUNT) throw new AddressCountLimitException("用户收货地址个数超出最大限制");

        //补全用户信息
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);   //1 标识默认，0 标识不默认
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);

        //插入收货地址
        Integer rows = addressMapper.insert(address);
        //如果插入失败，抛出异常
        if (rows != 1) throw new InsertException("保存用户地址时出现未知错误");
    }
}
```

#### 1.2.4 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    AddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setName("smith");
        address.setPhone("12345678901");
        address.setAddress("神圣兽国游尾郡窝窝乡独行族");

        addressService.addNewAddress(2, "tom", address);
    }
}
```

### 1.3 控制层

#### 1.3.1 处理异常

在业务层有抛出收货地址总数超标的异常，所以需要在`BaseController.java`中进行捕获

```java
else if (e instanceof AddressCountLimitException) {
    result.setState(4003);
    result.setMessage("用户收货地址超出上限");
}
```

#### 1.3.2 设计请求

- url：/addresses/add_new_address
- type：post
- param：Address address, HttpSession session
- return：JsonResult\<Void> 

#### 1.3.3 处理请求

```java
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }
}
```

### 1.4 前端页面

```javascript
<script>
    $("#btn-add-new-address").click(function () {
        $.ajax({
            url: "/addresses/add_new_address",
            type: "POST",
            data: $("#form-add-new-address").serialize(),
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    alert("新镇收货地址成功");
                } else {
                    alert("新增收货地址失败");
                }
            },
            error: function (xhr) {
                alert("新增收货地址产生未知异常" + "\n" + xhr.status);
            }
        });
    });
</script>
```

## 2.获取省市区列表

### 2.1 持久层

#### 2.1.1 建立数据库表

```mysql
CREATE TABLE t_dict_district (
  id int(11) NOT NULL auto_increment,
  parent varchar(6) DEFAULT NULL,
  code varchar(6) DEFAULT NULL,
  name varchar(16) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

> parent：父区域的代码号（省的父代码号是 +86）

#### 2.1.2 创建实体类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictDistrict {
    private Integer id;
    private String parent;  //父区域的代码号（省的父代码号是 +86）
    private String code;
    private String name;
}
```

#### 2.1.3 规划SQL

根据父代号进行查询

```mysql
select * from t_dict_district where parent=? order by code asc
```

#### 2.1.4 定义接口

```java
public interface DistrictMapper {

    List<District> selectDistrictByParent(Integer parent);
}
```

#### 2.1.5 创建映射文件

```xml
<mapper namespace="com.lilin.mapper.DistrictMapper">
    <!--List<District> selectDistrictByParent(Integer parent);-->
    <select id="selectDistrictByParent" resultType="com.lilin.pojo.District">
        select
               id,parent,`code`,`name`
        from
             t_dict_district
        where
              parent=#{parent}
        order by asc
    </select>
</mapper>
```

#### 2.1.6 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    DistrictMapper districtMapper;

    @Test
    public void selectDistrictByParent() {
        List<District> list = districtMapper.selectDistrictByParent("450300");
        for (District each: list) {
            System.out.println(each);
        }
    }
}
```

### 2.2 业务层

#### 2.2.1 创建接口

```java
public interface DistrictService {
    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 District
     * @Description 根据父区域编号查询区域信息
     */
    List<District> selectDistrictByParent(String parent);
}
```

#### 2.2.2 编写实现类

```java
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;
    /**
     * @Author LiLin
     * @Date 2022/4/12 14:27
     * @Param parent 父区域的编号
     * @return 返回list集合，类型是 District
     * @Description 根据父区域编号查询区域信息
     */
    @Override
    public List<District> selectDistrictByParent(String parent) {
        List<District> list = districtMapper.selectDistrictByParent(parent);
        
        //在进行网络数据传输时，为了尽量避免无效数据的，可以将无效数据设置为null
        //这样一方面可以节省流量，另一方面可以提高效率
        for (District each : list) {
            each.setId(null);
            each.setParent(null);
        }
        return list;
    }
}
```

#### 2.2.3 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    DistrictService districtService;

    @Test
    public void selectDistrictByParent() {
        List<District> list = districtService.selectDistrictByParent("86");
        list.forEach(System.out :: println);
    }
}
```

### 2.3 控制层

#### 2.3.1 设计请求

- url：/districts/
- type：GET
- param：String parent
- return：JsonResult\<List\<District>>

#### 2.3.2 处理请求

```java
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
    @Autowired
    DistrictService districtService;

    @GetMapping({"/", ""})
    public JsonResult<List<District>> selectDistrict(String parent) {
        return new JsonResult<>(OK, districtService.selectDistrictByParent(parent));
    }
}
```

> 因为地址信息无论是否登录都可以查看，所以需要将`/districts`请求添加到白名单中
>
> ```java
> public void addInterceptors(InterceptorRegistry registry) {
>     HandlerInterceptor interceptor = new LoginInterceptor();
>     registry.addInterceptor(interceptor)
>         //对所有请求进行拦截
>         .addPathPatterns("/**")
>         //添加拦截白名单
>         .excludePathPatterns("/bootstrap3/**",
>                              "/css/**",
>                              "/images/**",
>                              "/js/**",
>                              "/web/login.html",
>                              "/web/register.html",
>                              "/web/product.html",
>                              "/web/index.html",
>                              "/index.html",
>                              "/users/login",
>                              "/users/register",
>                              "/districts/**");
> }
> ```

## 3.获取省市区的名称

### 3.1 持久层

#### 3.1.1 规划sql

根据当前code来获取当前省市区的名称，通过查询语句完成

```sql
select `name` from t_dict_district where code=?
```

#### 3.1.2 定义接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/12 15:46
     * @Param code 省市区编号
     * @return 返回省市区的名字
     * @Description 根据code查询省市区名字
     */
String selectNameByCode(String code);
```

#### 3.1.3 编写映射文件

```xml
<!--String selectNameByCode(String code);-->
<select id="selectNameByCode" resultType="java.lang.String">
    select
    `name`
    from
    t_dict_district
    where
    code=#{code}
</select>
```

#### 3.1.4 单元测试

```java
@Test
public void selectNameByCode() {
    System.out.println(districtMapper.selectNameByCode("450300"));
}
```

### 3.2 业务层

#### 3.2.1 异常规划

> 没有异常

#### 3.2.2 创建接口

```java
/**
     * @Author LiLin
     * @Date 2022/4/12 15:59
     * @Param code 省市区的编号
     * @return 返回查询到的省市区的名字
     * @Description 根据code查询省市区的名字
     */
String selectNameByCode(String code);
```

#### 3.2.3 编写实现类

```java
/**
     * @Author LiLin
     * @Date 2022/4/12 15:59
     * @Param code 省市区的编号
     * @return 返回查询到的省市区的名字
     * @Description 根据code查询省市区的名字
     */
@Override
public String selectNameByCode(String code) {
    return districtMapper.selectNameByCode(code);
}
```

#### 3.2.4 单元测试

```java
@Test
public void selectNameByCode() {
    System.out.println(districtService.selectNameByCode("450300"));
}
```

### 3.3 控制层

> 无

### 3.4 收货地址业务层优化

与获取省市区列表相互依联起来

- 1.添加地址层依赖于 DistrictService 层

  ```java
  //在添加用户的收货地址的业务层时，会依赖于 DistrictService 的业务层接口
  @Autowired
  DistrictService districtService;
  ```

- 2.在`addNewAddress`方法中，将在接口`districtService`中获取到的省市区数据转移到`address`对象中，`address`对象就能够包含所有的用户收货地址的数据



### 3.5 前端页面

```javascript
<script>
    // value 属性用于表示当前的这个区域的code值
    let defaultOption = "<option value='0'>---- 请选择 ----</option>";


    //city-list发生了改变
    $("#city-list").change(function () {
        //先获取行政区的父代码
        let parent = $("#city-list").val();
        //清空标签里的所有子标签
        $("#area-list").empty();
        //填充默认值：请选择
        $("#area-list").append(defaultOption);

        if (parent === 0) return;
        $.ajax({
            url: "/districts/",
            type: "GET",
            data: "parent=" + parent,
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    for (let i = 0; i < list.length; i++) {
                        const option = "<option value='" + list[i].code + "'>" + list[i].name + "</option>";
                        $("#area-list").append(option);
                    }
                } else {
                    alert("获取的区县信息加载失败");
                }
            },
            error: function (xhr) {
                alert("加载区县数据产生未知异常" + "\n" + xhr.status);
            }
        });
    });


    //当province-list发生了改变
    $("#province-list").change(function () {
        //先获取行政区的父代码
        let parent = $("#province-list").val();
        //清空标签里的所有子标签
        $("#city-list").empty();
        $("#area-list").empty();
        //填充默认值：请选择
        $("#city-list").append(defaultOption);
        $("#area-list").append(defaultOption);

        if (parent === 0) return;
        $.ajax({
            url: "/districts/",
            type: "GET",
            data: "parent=" + parent,
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    for (let i = 0; i < list.length; i++) {
                        const option = "<option value='" + list[i].code + "'>" + list[i].name + "</option>";
                        $("#city-list").append(option);
                    }
                } else {
                    alert("获取的城市信息加载失败");
                }
            },
            error: function (xhr) {
                alert("加载城市数据产生未知异常" + "\n" + xhr.status);
            }
        });
    });


    $(document).ready(function () {
        showProvinceList();
        // 当用户没有在省列表中选择值时，设置控件的默认值：请选择
        $("#city-list").append(defaultOption);
        $("#area-list").append(defaultOption);
    });

    //省的下拉列表的数据展示
    function showProvinceList() {
        $.ajax({
            url: "/districts/",
            type: "GET",
            data: "parent=86",
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    for (let i = 0; i < list.length; i++) {
                        const option = "<option value='" + list[i].code + "'>" + list[i].name + "</option>";
                        $("#province-list").append(option);
                    }
                } else {
                    alert("省/直辖市信息加载失败");
                }
            },
            error: function (xhr) {
                alert("加载省/直辖市数据产生未知异常" + "\n" + xhr.status);
            }
        });
    }

    $("#btn-add-new-address").click(function () {
        $.ajax({
            url: "/addresses/add_new_address",
            type: "POST",
            data: $("#form-add-new-address").serialize(),
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    alert("新镇收货地址成功");
                } else {
                    alert("新增收货地址失败");
                }
            },
            error: function (xhr) {
                alert("新增收货地址产生未知异常" + "\n" + xhr.status);
            }
        });
    });
</script>
```

## 4.收货地址列表展示

### 4.1 持久层

#### 4.1.1 规划SQL

对地址的查询操作：

```sql
select * from t_address where uid=? order by is_default desc,created_time desc
```

#### 4.1.2 设计接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 12:50
     * @Param uid 用户id
     * @return 返回用户收货地址的列表
     * @Description 根据uid查询用户收货地址信息
     */
List<Address> selectAddressByUid(Integer uid);
```

#### 4.1.3 创建映射文件

```xml
    <!--List<District> selectAddressByUid(Integer uid);-->
    <select id="selectAddressByUid" resultMap="AddressEntityMapper">
        select
               uid,`name`,province_name,province_code,city_name,city_code,area_name,
               area_code,zip,address,phone,tel,tag,is_default,created_user,created_time,
               modified_user,modified_time
        from
             t_address
        where
              uid=#{uid}
        order by is_default desc,created_time desc
    </select>
```

#### 4.1.4 单元测试

```java
@Test
public void selectAddressByUid() {
    List<Address> list = addressMapper.selectAddressByUid(6);

    for (Address item : list) {
        System.out.println(item);
    }
}
```

### 4.2 业务层

#### 4.2.1 异常规划

> 不需要

#### 4.2.2 设置接口

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 13:09
     * @Param uid 用户id
     * @return 返回用户地址信息的列表
     * @Description 根据uid获取用户的地址信息
     */
List<Address> getAddressBYUid(Integer uid);
```

#### 4.2.3 实现方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 13:09
     * @Param uid 用户id
     * @return 返回用户地址信息的列表
     * @Description 根据uid获取用户的地址信息
     */
@Override
public List<Address> getAddressByUid(Integer uid) {
    List<Address> list = addressMapper.selectAddressByUid(uid);
    List<Address> temporary = new ArrayList<>();

    for (Address item : list) {
        Address address = new Address();
        address.setTag(item.getTag());
        address.setName(item.getName());
        address.setAddress(item.getAddress());
        address.setPhone(item.getPhone());
        temporary.add(address);
    }

    return temporary;
}
```

#### 4.2.4 单元测试

```java
@Test
public void getAddressByUid() {
    List<Address> list = addressService.getAddressByUid(1);

    list.forEach(System.out :: println);
}
```

### 4.3 控制层

#### 4.3.1 设计请求

- url：/address
- param：HttpSession session
- type：GET
- return：JsonResult<List\<Address>>

#### 4.3.2 实现请求

```java
@GetMapping({"", "/"})
public JsonResult<List<Address>> getAddressByUid(HttpSession session) {
    return new JsonResult<>(OK, addressService.getAddressByUid(getUidFromSession(session)));
}
```

### 4.4 前端页面

```javascript
<script>
    $(document).ready(function () {
        $("#address-list").empty();
        showAddressList();
    });

    //展示用户地址列表
    function showAddressList() {
        $.ajax({
            url: "/addresses/",
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    for (let i = 0; i < list.length; i++) {
                        let tr = '<tr>'
                            + '<td>#{tag}</td>'
                            + '<td>#{name}</td>'
                            + '<td>#{address}</td>'
                            + '<td>#{phone}</td>'
                            + '<td><a className="btn btn-xs btn-info"><span className="fa fa-edit"></span> 修改</a></td>'
                            + '<td><a className="btn btn-xs add-del btn-info"><span className="fa fa-trash-o"></span> 删除</a></td>'
                            + '<td><a className="btn btn-xs add-def btn-default">设为默认</a></td>'
                            + '</tr>';

                        tr = tr.replace(/#{tag}/g, list[i].tag);
                        tr = tr.replace(/#{name}/g, list[i].name);
                        tr = tr.replace("#{address}", list[i].address);
                        tr = tr.replace("#{phone}", list[i].phone);

                        $("#address-list").append(tr);
                    }

                    //hide()：可以将某个元素隐藏
                    $(".add-def:eq(0)").hide();
                } else {
                    alert("收货地址数据加载失败");
                }
            },
            error: function (xhr) {
                alert("收货地址数据产生未知异常" + "\n" + xhr.status);
            }
        });
    }
</script>
```

## 5.设置默认收货地址

### 5.1 持久层

#### 5.1.1 规划SQL

检测当前用户想设置为默认收货地址的数据是否存在

```mysql
select * from t_address aid=?
```

在修改用户的收获默认地址之前，现将所有的收货地址设置为非默认

```sql
update t_address set is_default=0 where uid=?
```

将用户当前选中的记录设置为默认收货地址

```sql
update t_address set is_default=1,modified_user=?,modified_time=? where aid=?
```

#### 5.1.2 设计接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 15:16
     * @Param aid 用户的收货地址id
     * @return 返回查询到的收货地址信息
     * @Description 根据aid，查询用户的默认收货地址
     */
Address selectDefaultAddress(Integer aid);

/**
     * @Author LiLin
     * @Date 2022/4/13 15:18
     * @Param uid 用户id
     * @return 返回影响的行数
     * @Description 更改用户的所有收货地址为非默认
     */
Integer updateAllAddressNonDefault(Integer uid);

/**
     * @Author LiLin
     * @Date 2022/4/13 15:20
     * @Param modifiedUser 修改人
     * @Param modifiedTime 修改时间
     * @Param aid 用户的收货地址id
     * @return 返回影响的行数
     * @Description 更改用户选中的收货地址为默认收货地址
     */
Integer updateAddressDefault(String modifiedUser, String modifiedTime, Integer aid);
```

#### 5.1.3 添加映射

```xml
    <!--Address selectDefaultAddress(Integer aid);-->
    <select id="selectDefaultAddress" resultMap="AddressEntityMapper">
        select
            uid,`name`,province_name,province_code,city_name,city_code,area_name,
            area_code,zip,address,phone,tel,tag,is_default,created_user,created_time,
            modified_user,modified_time
        from
             t_address
        where
              aid=#{aid}
    </select>
```

```xml
    <!--Integer updateAllAddressNonDefault(Integer uid);-->
    <update id="updateAllAddressNonDefault">
        update
            t_address
        set
            is_default=0
        where
              uid=#{uid}
    </update>
```

```xml
    <!--Integer updateAddressDefault(String modifiedUser, String modifiedTime, Integer aid);-->
    <update id="updateAddressDefault">
        update
            t_address
        set
            is_default=1,modified_user=#{modifiedUser},modified_time=#{modifiedTime}
        where
              aid=#{aid}
    </update>
```

#### 5.1.4 单元测试

```java
@Test
public void selectDefaultAddress() {
    System.out.println(addressMapper.selectDefaultAddress(4));
}

@Test
public void updateAllAddressNonDefault() {
    System.out.println("影响的行数：" + addressMapper.updateAllAddressNonDefault(6));
}

@Test
public void updateAddressDefault() {
    System.out.println("影响的行数：" + addressMapper.updateAddressDefault("admin", new Date(), 4));
}
```

### 5.2 业务层

#### 5.2.1 规划异常

- 在执行更新的时候产生更新异常（已经创建数据的无需重复创建）：`UpdateException.java`
- 访问的数据不是当前登录用户的收货地址数据（即非法访问）：`AccessDeniedException.java`
- 收货地址可能不存在：`AddressNotFoundException.java`

#### 5.2.2 设计接口方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 15:57
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @return 返回影响的行数
     * @Description 更改用户的收货地址为默认地址
     */
Integer updateDefaultAddress(Integer uid, Integer aid, String username);
```

#### 5.2.3 实现接口

```java
/**
     * @Author LiLin
     * @Date 2022/4/13 15:57
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @return 返回影响的行数
     * @Description 更改用户的收货地址为默认地址
     */
@Override
public void setDefaultAddress(Integer uid, Integer aid, String username) {
    //查询用户默认地址信息是否存在
    Address address = addressMapper.selectDefaultAddress(aid);
    // 如果不存在抛出异常
    if (address == null)
        throw new AddressNotFoundException("用户收货地址不存在");

    //检查当前获取的收货地址归属
    if (!address.getAid().equals(aid))
        throw new AccessDeniedException("数据非法访问");

    //将用户所有收货地址都设置为非默认地址
    Integer result = addressMapper.updateAllAddressNonDefault(uid);
    //如果修改失败，抛出异常
    if (result == null || result < 1)
        throw new UpdateException("修改用户默认收货地址过程中出现未知异常");

    //修改用户默认收货地址
    result = addressMapper.updateAddressDefault(modifiedUser, modifiedTime, aid);
    //如果修改失败，抛出异常
    if (result == null || result != 1)
        throw new UpdateException("修改用户默认收货地址过程中出现未知异常");
}
```

#### 5.2.4 单元测试

```java
@Test
public void setDefaultAddress() {
    addressService.setDefaultAddress(6, 3, "youLove");
}
```

### 5.3 控制层

#### 5.3.1 处理异常

```java
else if (e instanceof AccessDeniedException) {
    result.setState(4004);
    result.setMessage("用户数据被非法访问");
}  else if (e instanceof AddressNotFoundException) {
    result.setState(4005);
    result.setMessage("收货地址不存在");
}
```

#### 5.3.2 设计请求

- url：/address/set_default/{aid}
- type：GET
- data：@PathVariable("aid") Integer aid, HttpSession session
- return：JsonResult\<Void>

#### 5.3.3 处理请求

```java
@GetMapping("/set_default/{aid}")
public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
    addressService.setDefaultAddress(getUidFromSession(session), aid, getUsernameFromSession(session));
    return new JsonResult<>(OK);
}
```

### 5.4 前端页面

给设置默认收货地址的按钮添加一个onlick属性，指向一个方法的调用，并在这个方法中完成Ajax请求的方法

```javascript
let tr = '<tr>'
+ '<td>#{tag}</td>'
+ '<td>#{name}</td>'
+ '<td>#{address}</td>'
+ '<td>#{phone}</td>'
+ '<td><a className="btn btn-xs btn-info"><span className="fa fa-edit"></span> 修改</a></td>'
+ '<td><a className="btn btn-xs add-del btn-info"><span className="fa fa-trash-o"></span> 删除</a></td>'
+ '<td><a className="btn btn-xs add-def btn-default" onclick="setDefault(#{aid})">设为默认</a></td>'
+ '</tr>';

tr = tr.replace(/#{tag}/g, list[i].tag);
tr = tr.replace(/#{name}/g, list[i].name);
tr = tr.replace("#{address}", list[i].address);
tr = tr.replace("#{phone}", list[i].phone);
tr = tr.replace("#{aid}", list[i].aid);
```

完成`setDefault()`方法的声明和定义

```js
<script>
    $(document).ready(function () {
        showAddressList();
    });

    //展示用户地址列表
    function showAddressList() {
        //清空页面
        $("#address-list").empty();

        $.ajax({
            url: "/addresses/",
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    for (let i = 0; i < list.length; i++) {
                        let tr = '<tr>'
                            + '<td>#{tag}</td>'
                            + '<td>#{name}</td>'
                            + '<td>#{address}</td>'
                            + '<td>#{phone}</td>'
                            + '<td><a class="btn btn-xs btn-info"><span class="fa fa-edit"></span> 修改</a></td>'
                            + '<td><a className="btn btn-xs add-del btn-info"><span class="fa fa-trash-o"></span> 删除</a></td>'
                            + '<td><a onclick="setDefault(#{aid})" id="btn-default" class="btn btn-xs add-def btn-default">设为默认</a></td>'
                            + '</tr>';

                        tr = tr.replace(/#{tag}/g, list[i].tag);
                        tr = tr.replace(/#{name}/g, list[i].name);
                        tr = tr.replace("#{address}", list[i].address);
                        tr = tr.replace("#{phone}", list[i].phone);
                        tr = tr.replace("#{aid}", list[i].aid);

                        $("#address-list").append(tr);
                    }

                    //hide()：可以将某个元素隐藏
                    $("#btn-default:eq(0)").hide();
                } else {
                    alert("收货地址数据加载失败");
                }
            },
            error: function (xhr) {
                alert("收货地址数据产生未知异常" + "\n" + xhr.status);
            }
        });
    }


    function setDefault(aid) {
        $.ajax({
            url: "/addresses/set_default/" + aid,
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    //重新加载收货地址列表页面
                    showAddressList();
                } else {
                    alert("设置默认收货地址失败");
                }
            },
            error: function (xhr) {
                alert("设置默认收货地址发生未知错误" + " \n" + xhr.status);
            }
        });
    }
</script>
```

## 6.删除收货地址

### 6.1 持久层

#### 6.1.1 规划SQL

在删除之前判断该数据是否存在

> 已存在

判断该条地址数据的归属是否为当前用户

> 已存在

删除收货地址的信息

```sql
delete from t_address where aid=?
```

如果用户删除的是默认收货地址，将剩下的地址中的一条设置为默认收货地址，规则可以自定义：即最新修改的收货地址设置为默认的收货地址，可以通过`modified_time`字段完成

```sql
select * from t_address where uid=? order by modified_time desc limit 0,1
```

如果用户本身只有一条收货地址，执行删除操作后就可以不再进行其他操作了

#### 6.1.2 设计接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/14 8:27
     * @Param aid 收货地址id
     * @return 返回影响的行数
     * @Description 根据aid删除用户收货地址
     */
Integer deleteAddressByAid(Integer aid);

/**
     * @Author LiLin
     * @Date 2022/4/14 8:30
     * @Param uid 用户id
     * @return 返回查询到的收货地址信息
     * @Description 查询用户最后修改的收货地址记录
     */
Address selectLastModifiedAddress(Integer uid);
```

#### 6.1.3 添加映射

```sql
    <!--Integer deleteAddressByAid(Integer aid);-->
    <delete id="deleteAddressByAid">
        delete from 
                    t_address 
        where 
              aid=#{aid}
    </delete>
    
    <!--Address selectLastModifiedAddress(Integer uid);-->
    <select id="selectLastModifiedAddress" resultMap="AddressEntityMapper">
        select
            aid,uid,`name`,province_name,province_code,city_name,city_code,area_name,
            area_code,zip,address,phone,tel,tag,is_default,created_user,created_time,
            modified_user,modified_time
        from 
             t_address 
        where 
              uid=#{uid}
        order by modified_time desc limit 0,1
    </select>
```

#### 6.1.4 单元测试

```java
@Test
public void deleteAddressByAid() {
    System.out.println(addressMapper.deleteAddressByAid(5));
}

@Test
public void selectLastModifiedAddress() {
    System.out.println(addressMapper.selectLastModifiedAddress(6));
}
```

### 6.2 业务层

#### 6.2.1 规划异常

在执行删除的时候可能会产生未知的删除异常导致数据不能正常删除：`DeleteException.java`

#### 6.2.2 设计接口方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/14 8:50
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除用户的收货地址信息
     */
void deleteAddress(Integer uid, Integer aid, String username);
```

#### 6.2.3 实现接口

```java
/**
     * @Author LiLin
     * @Date 2022/4/14 8:50
     * @Param uid 用户id
     * @Param aid 收货地址id
     * @Param username 用户名
     * @Description 删除用户的收货地址信息
     */
@Override
public void deleteAddress(Integer uid, Integer aid, String username) {
    Address result = addressMapper.selectDefaultAddress(aid);
    //判断要删除的收货地址是否存在
    if (result == null)
        throw new AddressNotFoundException("收货地址不存在");

    //检查当前获取的收货地址归属
    if (!result.getUid().equals(uid))
        throw new AccessDeniedException("数据非法访问");

    //删除收货地址
    //如果删除失败
    if (addressMapper.deleteAddressByAid(aid) != 1)
        throw new DeleteException("删除收货地址过程中出现未知异常");

    //查询收货地址剩余数量
    //如果还有地址，以及删除的收货地址是默认地址
    if (addressMapper.countAddressByUid(uid) != 0 && result.getIsDefault() == 1) {
        //查询最新修改的数据
        Address address = addressMapper.selectLastModifiedAddress(uid);
        //将最新的数据设为默认收货地址
        Integer rows = addressMapper.updateAddressDefault(username, new Date(), address.getAid());

        //如果修改数据失败
        if (rows != 1)
            throw new UpdateException("修改收货地址信息时出现未知异常");
    }
}
```

#### 6.2.4 单元测试

```java
@Test
public void deleteAddress() {
    addressService.deleteAddress(1, 1, "admin");
}
```

### 6.3 控制层

#### 6.3.1 处理异常

```java
else if (e instanceof DeleteException) {
    result.setState(5002);
    result.setMessage("删除收货地址信息时产生未知错误");
}
```

#### 6.3.2 设计请求

- url：/addresses/delete_address/{aid}
- dataType：POST
- param：aid, HttpSession session
- return：JsonResult\<Void>

#### 6.3.3 处理请求

```java
@RequestMapping("delete_address/{aid}")
public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session) {
    addressService.deleteAddress(getUidFromSession(session), aid, getUsernameFromSession(session));
    return new JsonResult<>(OK);
}
```

### 6.4 前端页面

```js
+ '<td><a onclick="deleteAddress(#{aid}})" id="btn-delete" className="btn btn-xs add-del btn-info"><span className="fa fa-trash-o"></span> 删除</a></td>'
```

```js
function deleteAddress(aid) {
    $.ajax({
        url: "/addresses/delete_address/" + aid,
        type: "POST",
        dataType: "JSON",
        success: function (json) {
            if (json.state === 200) {
                //重新加载收货地址列表页面
                showAddressList();
            } else {
                alert("删除收货地址失败");
            }
        },
        error: function (xhr) {
            alert("删除收货地址发生未知错误" + " \n" + xhr.status);
        }
    });
}
```

# 六、热销商品排行

## 1.热销商品

### 1.1 持久层

#### 1.1.1 创建数据库表

```sql
CREATE TABLE t_product (
  id int(20) NOT NULL COMMENT '商品id',
  category_id int(20) DEFAULT NULL COMMENT '分类id',
  item_type varchar(100) DEFAULT NULL COMMENT '商品系列',
  title varchar(100) DEFAULT NULL COMMENT '商品标题',
  sell_point varchar(150) DEFAULT NULL COMMENT '商品卖点',
  price bigint(20) DEFAULT NULL COMMENT '商品单价',
  num int(10) DEFAULT NULL COMMENT '库存数量',
  image varchar(500) DEFAULT NULL COMMENT '图片路径',
  status int(1) DEFAULT '1' COMMENT '商品状态  1：上架   2：下架   3：删除',
  priority int(10) DEFAULT NULL COMMENT '显示优先级',
  created_time datetime DEFAULT NULL COMMENT '创建时间',
  modified_time datetime DEFAULT NULL COMMENT '最后修改时间',
  created_user varchar(50) DEFAULT NULL COMMENT '创建人',
  modified_user varchar(50) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

#### 1.1.2 创建实体类

```java
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    private Integer id;//商品id
    private Integer categoryId;//分类id
    private String itemType;//商品系列
    private String title;//商品标题
    private String sellPoint;//商品卖点
    private Integer price;//商品单价
    private Integer num;//库存数量
    private String image;//图片路径
    private Integer status;//商品状态  1：上架   2：下架   3：删除
    private Integer priority;//显示优先级
}
```

#### 1.1.3 规划SQL

```sql
select * from t_product where status=1 order by priority desc limit 0,4
```

#### 1.1.4 设计接口和抽象方法

```java
public interface ProductMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/14 14:45
     * @return 返回查询到的商品列表
     * @Description 查询热销商品
     */
    List<Product> selectHotList();
}
```

#### 1.1.5 SQL映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lilin.mapper.ProductMapper">

    <resultMap id="ProductEntityMapper" type="com.lilin.pojo.Product">
        <!--在定义映射规则时主键不可省略-->
        <result property="id" column="id" />
        <result property="categoryId" column="category_id" />
        <result property="itemType" column="item_type" />
        <result property="sellPoint" column="sell_point" />
        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedUser" column="modified_time" />
    </resultMap>

    <!--List<Product> selectHotList();-->
    <select id="selectHotList" resultMap="ProductEntityMapper">
        select
            id,category_id,item_type,title,sell_point,price,num,image,`status`,
            priority,created_time,modified_time,created_user,modified_user
        from
            t_product
        where
            `status`=1
        order by priority desc limit 0,4
    </select>
</mapper>
```

#### 1.1.6 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    ProductMapper productMapper;

    @Test
    public void selectHotList() {
        List<Product> list = productMapper.selectHotList();

        list.forEach(System.out :: println);
    }
}
```

### 1.2 业务层

#### 1.2.1 异常分析

> 无异常

#### 1.2.2 设计接口和抽象方法

```java
public interface ProductService {
    /**
     * @Author LiLin
     * @Date 2022/4/14 15:09
     * @return 返回查询到的商品列表
     * @Description 查询销量最多的商品的列表
     */
    List<Product> selectHotList();
}
```

#### 1.2.3 实现抽象方法

```java
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * @Author LiLin
     * @Date 2022/4/14 15:09
     * @return 返回查询到的商品列表
     * @Description 查询销量最多的商品的列表
     */
    @Override
    public List<Product> selectHotList() {
        List<Product> productList = productMapper.selectHotList();

        for (Product product : productList) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }

        return productList;
    }
}
```

#### 1.2.4 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    ProductService productService;
    
    @Test
    public void selectHotList() {
        List<Product> list = productService.selectHotList();
        list.forEach(System.out :: println);
    }
}
```

### 1.3 控制层

#### 1.3.1 规划异常

> 无

#### 1.3.2 设计请求

- url：/products/hot_list
- param：null
- type：GET
- return：JsonResult\<List\<Product>>

> 这个请求不需要被拦截，所以要将`index.html`和`products/**`添加到白名单中

#### 1.3.3 实现请求

```java
@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    ProductService productService;

    @GetMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        return new JsonResult<>(OK, productService.selectHotList());
    }
}
```

### 1.4 前端页面

```js
<script>
    $(document).ready(function () {
        showHotList();
    });

    function showHotList() {
        $("#hot-list").empty();

        $.ajax({
            url: "/products/hot_list",
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                let list = json.data;
                for (let i = 0; i < list.length; i++) {
                    let div = '<div class="col-md-12">\n' +
                                    '<div class="col-md-7 text-row-2"><a href="product.html?id=#{id}">#{title}</a></div>\n' +
                                    '<div class="col-md-2">￥#{price}</div>\n' +
                                    '<div class="col-md-3"><img src="..#{image}collect.png" class="img-responsive" alt=""/></div>' +
                              '</div>';

                    div = div.replace("#{id}", list[i].id);
                    div = div.replace("{title}", list[i].title);
                    div = div.replace("#{price}", list[i].price);
                    div = div.replace("#{image}", list[i].image)

                    $("#hot-list").append(div);
                }
            },
            error: function (xhr) {
                alert("加载商品信息出现未知错误" + "\n" + xhr.status)
            }
        });
    }
</script>
```

## 2.显示商品详情

### 2.1 持久层

#### 2.1.1 规划SQL

根据商品 id 显示商品详情

```sql
select * from t_product where id=?
```

#### 2.1.2 设计接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 9:13
     * @Param id 商品id
     * @return 返回查询到的商品信息
     * @Description 根据id查询商品详细信息
     */
Product selectProductById(Integer id);
```

#### 2.1.3 添加映射

```xml
    <!--Product selectProductById(Integer id);-->
    <select id="selectProductById" resultMap="ProductEntityMapper">
        select
            id,category_id,item_type,title,sell_point,price,num,image,`status`,
            priority,created_time,modified_time,created_user,modified_user
        from
            t_product
        where
              id=#{id}
    </select>
```

#### 2.1.4 单元测试

```java
@Test
public void selectProductById() {
    System.out.println(productMapper.selectProductById(10000001));
}
```

### 2.2 业务层

#### 2.2.1 异常分析

商品找不到异常：`ProductNotFoundException.java`

```java
public class ProductNotFoundException extends ServiceException {
   //
}
```

#### 2.2.2 设计抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 9:13
     * @Param id 商品id
     * @return 返回查询到的商品信息
     * @Description 根据id查询商品详细信息
     */
Product selectProductById(Integer id);
```

#### 2.2.3 实现抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 9:13
     * @Param id 商品id
     * @return 返回查询到的商品信息
     * @Description 根据id查询商品详细信息
     */
@Override
public Product selectProductById(Integer id) {
    Product product = productMapper.selectProductById(id);

    if (product == null)
        throw new ProductNotFoundException("尝试访问的商品信息不存在");

    product.setPriority(null);
    product.setCreatedUser(null);
    product.setCreatedTime(null);
    product.setModifiedUser(null);
    product.setModifiedTime(null);

    return product;
}
```

#### 2.2.4 单元测试

```java
@Test
public void selectProductById() {
    System.out.println(productService.selectProductById(10000001));
}
```

 ### 2.3 控制层

#### 2.3.1 规划异常

```java
else if (e instanceof ProductNotFoundException) {
    result.setState(4006);
    result.setMessage("尝试访问的商品信息不存在");
}
```

#### 2.3.2 设计请求

- url：/products/get_product/{id}
- param：Integer id
- type：GET
- return：JsonResult\<Product>

#### 2.3.3 实现请求

```java
@GetMapping("get_product/{id}")
public JsonResult<Product> getProduct(@PathVariable("id") Integer id) {
    return new JsonResult<>(OK, productService.selectProductById(id));
}
```

### 2.4 前端页面

```js
<script>
    $(document).ready(function () {
        showHotList();
    });

    function showHotList() {
        $("#hot-list").empty();

        let id = $.getUrlParam("id");

        $.ajax({
            url: "/products/get_product/" + id,
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    $("#product-title").html(json.data.title);
                    $("#product-sell-point").html(json.data.sellPoint)
                    $("#product-price").html(json.data.price);

                    //图片动态输出
                    for (let i = 1; i <= 5; i++) {
                        $("#product-image-" + i + "-big").attr("src", ".." + json.data.image + i + "_big.png");
                        $("#product-image-" + i).attr("src", ".." + json.data.image + i + ".jpg");
                    }
                } else if (json.state === 4006) {
                    //商品数据不存在的话返回首页
                    location.href = "index.html";
                } else {
                    alert("获取商品信息失败" + "\n" + json.message);
                }
            },
            error: function (xhr) {
                alert("加载商品信息出现未知错误" + "\n" + xhr.status)
            }
        });
    }
</script>
```

# 七、购物车

## 1.加入购物车

### 1.1 持久层

#### 1.1.1 创建数据表

```sql
create table t_cart (
	cid int auto_increment comment '购物车数据id',
    uid int not null comment '用户id',
    pid int not null comment '商品id',
    price bigint comment '加入商品时的单价',
    num int comment '商品数量',
    created_time datetime DEFAULT NULL COMMENT '创建时间',
    modified_time datetime DEFAULT NULL COMMENT '最后修改时间',
    created_user varchar(50) DEFAULT NULL COMMENT '创建人',
    modified_user varchar(50) DEFAULT NULL COMMENT '最后修改人',
    primary key (cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

#### 1.1.2 创建实体类

```java
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {
    private Integer cid;    //购物车数据id
    private Integer uid;    //用户id
    private Integer pid;    //商品id
    private Long price;     //加入商品时的单价
    private Integer num;    //商品数量
}
```

#### 1.1.3 规划SQL

向购物车中插入数据

```sql
insert into t_cart(cid除外) values(值列表)
```

查询购物车表中商品信息是否存在

```sql
select * from t_cart where uid=? and pid=?
```

当商品在购物车中已经存在，更新购物车中的商品数量

```sql
update t_cart set num=?,modified_user=?,modified_time=? where cid=?
```

#### 1.1.4 设计接口和抽象方法

```java
public interface CartMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/15 13:27
     * @Param cart 购物车实体类
     * @return 返回插入的行数
     * @Description 插入购物车数据
     */
    Integer insert(Cart cart);

    /**
     * @Author LiLin
     * @Date 2022/4/15 13:29
     * @Param cid 购物车id
     * @return 返回查询到的购物车信息
     * @Description 查询购物车信息
     */
    Cart selectCartByUidAndPid(Integer uid, Integer pid);

    /**
     * @Author LiLin
     * @Date 2022/4/15 13:30
     * @Param cid 购物车id
     * @Param uid 用户id
     * @return 返回更新的行数
     * @Description 更新购物车中商品数量
     */
    Integer updateCartNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
}
```

#### 1.1.5 编写映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lilin.mapper.CartMapper">
    <resultMap id="cartEntityMapper" type="com.lilin.pojo.Cart">
        <!--在定义映射规则时主键不可省略-->
        <result property="cid" column="cid" />
        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedUser" column="modified_time" />
    </resultMap>

    <!--Integer insert(Cart cart);-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="cid">
        insert into
            t_cart(uid,pid,price,num,created_time,modified_time,created_user,modified_user)
        values
               (#{uid},#{pid},#{price},#{num},#{createdTime},#{modifiedTime},
                #{createdUser},#{modifiedUser})
    </insert>
    <!--Integer updateCartNum(Integer cid, Integer uid);-->
    <update id="updateCartNum">
        update
            t_cart
        set
            num=#{num},modified_user=#{modifiedUser},modified_time=#{modifiedTime}
        where
              cid=#{cid}
    </update>
    <!--Cart selectCartByCid(Integer cid);-->
    <select id="selectCartByUidAndPid" resultMap="cartEntityMapper">
        select
            cid,uid,pid,price,num,created_time,modified_time,created_user,modified_user
        from
             t_cart
        where
              uid=#{uid} and pid=#{pid}
    </select>

</mapper>
```

#### 1.1.6 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart(null, 6, 10000001, 46L, 1);
        cartMapper.insert(cart);
    }

    @Test
    public void selectCartByUidAndPid() {
        System.out.println(cartMapper.selectCartByUidAndPid(6, 10000001));
    }

    @Test
    public void updateCartNum() {
        cartMapper.updateCartNum(1, 10, "admin", new Date());
    }
}
```

### 1.2 业务层

#### 1.2.1 异常分析

- 往购物车插入数据异常：`InsertException.java`
- 更新数据可能会产生异常：`UpdateException.java`

#### 1.2.2 设计接口

```java
public interface CartService {
    /**
     * @Author LiLin
     * @Date 2022/4/15 14:19
     * @Param cart 购物车实体类
     * @Description 添加购物车数据
     */
    void insert(Integer uid, Integer pid, Integer amount, String username);
}
```

#### 1.2.3 实现接口

```java
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * @Author LiLin
     * @Date 2022/4/15 14:19
     * @Param uid 用户id
     * @Param cid 购物车id
     * @Param amount 商品数量
     * @Param username 修改人用户名
     * @Description 添加购物车数据
     */
    @Override
    public void insert(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.selectCartByCid(uid, pid);
        Date date = new Date();
        if (result == null) { //如果购物车没有这个商品的数据，进行添加操作
            Cart cart = new Cart();
            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //添加价格，价格从商品中获取
            cart.setPrice(productMapper.selectProductById(pid).getPrice());

            //往购物车中添加商品数据
            if (cartMapper.insert(cart) != 1)
                throw new InsertException("添加商品数据时产生未知异常");
        } else { //如果购物车有了商品的数据，则进行更新操作
            //获取商品总数
            Integer num = result.getNum() + amount;
            //更新商品数量
            Integer rows = cartMapper.updateCartNum(result.getCid(), num, username, date);
            //如果更新失败
            if (rows != 1)
                throw new UpdateException("更新商品数量时出现未知异常");
        }
    }
}
```

#### 1.2.4 单元测试

```java
@Test
public void insert() {
    cartService.insert(6, 10000013, 1, "amy");
}
```

### 1.3 控制层

#### 1.3.1 规划异常

> 没有需要处理的异常

#### 1.3.2 设计请求

- url：/carts/add_to_cart
- type：GET
- param：Integer pid, Integer amount, HttpSession session
- return：JsonResult\<Void>

#### 1.3.3 实现请求

```java
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    CartService cartService;

    @GetMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.insert(getUidFromSession(session), pid, amount, getUsernameFromSession(session));
        return new JsonResult<>(OK);

    }
}
```

### 1.4 前端页面

```js
$("#btn-add-to-cart").click(function () {
    $.ajax({
        url: "/carts/add_to_cart",
        type: "GET",
        data: {
            "pid": id,
            "amount": $("#num").val()
        },
        dataType: "JSON",
        success: function (json) {
            if (json.state === 200) {

            } else {
                alert("加入购物车失败" + "\n" + json.message);
            }
        },
        error: function (xhr) {
            alert("加入购物车出现未知错误" + "\n" + xhr.status)
        }
    });
});
```

在ajax函数中data参数的数据设置的方式：

- `data:$("form表单选择").serialize()`

  > 使用场景：当参数过多并且在同一个表单中、字符串的提交等

- `data:new FormData($("form表单选择")[0])`

  > 只适用于提交文件

- `data:"username=Tom"`

  > 适合参数值固定并且参数值列表有限时，可以进行手动拼接

- 以下情况适合于JSON格式去提交数据

  ```js
  data: {
      "username": "Tom",
      "age": 18,
      "sex": 0
  }
  ```

## 2.显示购物车列表

### 1.1 持久层

#### 1.1.1 设计SQL

```mysql
select 

cid,uid,pid,		t_cart.price,t_cart.num,	t_product.title,t_product.image,t_product.price as realPrice 

from t_cart left join t_product on t_cart.pid = t_product.id

where uid=?

order by t_cart.created_time desc
```

#### 1.1.2 设计接口和抽象方法

##### 1.VO对象

创建VO实体类

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;
}
```

> VO：Value Object（值对象）
>
> 当进行`select`查询时，如果查询的结果包含多张表中的内容，此时就会出现一个问题：结果集不能直接使用某一个POJO对象实体类来接收，POJO实体类不能包含多张表查询出来的结果。
>
> 解决方法：此时需要重新去构建一个新的对象来储存所查询出来的结果集对应的映射，于是就把这个对象称之为**Value Object（值对象）**

##### 2.抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
List<CartVo> selectVOByUid(Integer uid);
```

#### 1.1.3 添加映射

```xml
    <!--List<CartVo> selectVOByUid(Integer uid);-->
    <select id="selectVOByUid" resultType="com.lilin.vo.CartVo">
        select
            cid,uid,pid,t_cart.price,t_cart.num,t_product.title,t_product.image,t_product.price as realPrice
        from
            t_cart left join t_product on t_cart.pid = t_product.id
        where
            uid=#{uid}
        order by
            t_cart.created_time desc
    </select>
```

#### 1.1.4 单元测试

```java
@Test
public void selectProductByUid() {
    List<CartVo> list = cartMapper.selectVOByUid(6);
    list.forEach(System.out :: println);
}
```

### 1.2 业务层

#### 1.2.1 设计异常

> 无异常

#### 1.2.2 设计抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
List<CartVo> selectVOByUid(Integer uid);
```

#### 1.2.3 实现抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
@Override
public List<CartVo> selectVOByUid(Integer uid) {
    return cartMapper.selectVOByUid(uid);
}
```

#### 1.2.4 单元测试

```java
@Test
public void selectVOByUid() {
    List<CartVo> list = cartService.selectVOByUid(6);
    list.forEach(System.out :: println);
}
```

### 1.3 控制层

#### 1.3.1 规划异常

> 无

#### 1.3.2 设计请求

- url：/carts/
- type：GET
- param：HttpSession session
- return：JsonResult<List\<CartVo>>

#### 1.3.3 实现请求

```java
@GetMapping({"", "/"})
public JsonResult<List<CartVo>> getCartVo(HttpSession session) {
    return new JsonResult<>(OK, cartService.selectVOByUid(getUidFromSession(session)));
}
```

### 1.4 前端页面

注释掉`cart.js`文件

```html
<!--<script src="../js/cart.js" type="text/javascript" charset="utf-8"></script>-->
```

js代码

```js
<script type="text/javascript">
    $(document).ready(function () {
        $("#cart-list").empty();
        showCartList();
    });

    function showCartList() {
        $.ajax({
            url: "/carts/",
            type: "GET",
            dataType: "JSON",
            success: function (json) {
                let list = json.data;
                for (let i = 0; i < list.length; i++) {
                    let div = '<tr>\n' +
                        '                            <td>\n' +
                        '                                <label>\n' +
                        '                                    <input name="cids" value="#{cid}" type="checkbox" class="ckitem"/>\n' +
                        '                                </label>\n' +
                        '                            </td>\n' +
                        '                            <td><img src="..#{image}collect.png" class="img-responsive" alt=""/>\n' +
                        '                            </td>\n' +
                        '                            <td>#{title}#{msg}</td>\n' +
                        '                            <td>¥<span id="goodsPrice#{cid}">#{price}</span></td>\n' +
                        '                            <td>\n' +
                        '                                <input name="price-#{cid}" type="button" value="-" class="num-btn" onclick="reduceNum(1)"/>\n' +
                        '                                <label>\n' +
                        '                                    <input id="goodsCount#{cid}" type="text" size="2" readonly="readonly" class="num-text" value="#{num}">\n' +
                        '                                </label>\n' +
                        '                                <input name="price+#{cid}" class="num-btn" type="button" value="+" onclick="addNum(1)"/>\n' +
                        '                            </td>\n' +
                        '                            <td><span id="goodsCast#{cid}">#{totalPrice}</span></td>\n' +
                        '                            <td>\n' +
                        '                                <input type="button" onclick="delCartItem(this)" class="cart-del btn btn-default btn-xs" value="删除"/>\n' +
                        '                            </td>\n' +
                              '</tr>';

                    div = div.replace("#{cid}", list[i].cid);
                    div = div.replace("#{image}", list[i].image);
                    div = div.replace("#{msg}", list[i].realPrice);
                    div = div.replace("#{title}", list[i].title);
                    div = div.replace("#{price}", list[i].price);
                    div = div.replace("#{num}", list[i].num);
                    div = div.replace("#{totalPrice}", list[i].price * list[i].num);

                    $("#cart-list").append(div);
                }
            },
            error: function (xhr) {
                alert("加载商品信息出现未知错误" + "\n" + xhr.status)
            }
        });
    }

    /*$(function () {
        //返回链接
        $(".link-account").click(function () {
            location.href = "orderConfirm.html";
        })
    })*/
</script>
```

## 3.增加购物车商品数量

### 3.1 持久层

#### 3.1.1 设计SQL

更新t_cart表记录的num值

```sql
update t_cart set num=?,modified_user=?,modified_time=? where cid=?
```

根据cid的值查询当前购物车的数据是否存在

```sql
select * from t_cart where cid=?
```

#### 3.1.2 设计接口和抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/16 10:39
     * @Param cid 购物车id
     * @return 返回查询到的购物车数据
     * @Description 查询购物车中的商品信息
     */
Cart selectCartByCid(Integer cid);

/**
     * @Author LiLin
     * @Date 2022/4/15 13:30
     * @Param cid 购物车id
     * @Param uid 用户id
     * @return 返回更新的行数
     * @Description 更新购物车中商品数量
     */
Integer updateCartNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
```

#### 3.1.3 添加映射

```xml
    <!--Cart selectCartByCid(Integer cid);-->
    <select id="selectCartByCid" resultMap="cartEntityMapper">
        select
            cid,uid,pid,price,num,created_time,modified_time,created_user,modified_user
        from
             t_cart
        where
              cid=#{cid}
    </select>
    <!--Integer updateCartNum(Integer cid, Integer uid);-->
    <update id="updateCartNum">
        update
            t_cart
        set
            num=#{num},modified_user=#{modifiedUser},modified_time=#{modifiedTime}
        where
              cid=#{cid}
    </update>
```

#### 3.1.4 单元测试

```java
@Test
public void selectCartByCid() {
    System.out.println(cartMapper.selectCartByCid(5));
}

@Test
public void updateCartNum() {
    cartMapper.updateCartNum(1, 10, "admin", new Date());
}
```

### 3.2 业务层

#### 3.2.1 设计异常

- 在更新时会产生更新异常：`UpdateException.java`
- 查询到的数据是否有权限访问：`AccessDeniedException.java`
- 查询的数据不存在：`CartNotFoundException.java`

#### 3.2.2 设计抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/16 10:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Param username 更改人姓名
     * @return 返回更新后的购物车数量
     * @Description 增加购物车中的商品数量
     */
Integer addCartNum(Integer cid, Integer uid, String username);
```

#### 3.2.3 实现抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/16 10:47
     * @Param cid 购物车id
     * @Param uid 用户id
     * @Param username 更改人姓名
     * @return 返回更新后的购物车数量
     * @Description 增加购物车中的商品数量
     */
@Override
public Integer addCartNum(Integer cid, Integer uid, String username) {
    //查询购物车数据是否存在
    Cart result = cartMapper.selectCartByCid(cid);
    if (result == null )
        throw new CartNotFoundException("数据不存在");

    //检查用户合法性
    if (!uid.equals(result.getUid()))
        throw new AccessDeniedException("数据非法访问");

    //更新数量
    Integer num = result.getNum() + 1;
    Integer rows = cartMapper.updateCartNum(cid, num, username, new Date());

    //如果更新失败
    if (rows != 1)
        throw new UpdateException("更新数据过程中出现未知异常");

    return num;
}
```

#### 3.2.4 单元测试

```java
@Test
public void addCartNum() {
    System.out.println(cartService.addCartNum(1, 6, "amy"));
}
```

### 3.3 控制层

#### 3.3.1 规划异常

```java
else if (e instanceof CartNotFoundException) {
    result.setState(4007);
    result.setMessage("尝试访问的购物车信息不存在");
}
```

#### 3.3.2 设计请求

- url：/carts/num/add/{cid}
- param：Integer cid, HttpSession session
- type：POST
- return：JsonResult\<Integer>

#### 3.3.3 实现请求

```java
@PostMapping("/add_num/{cid}")
public JsonResult<Integer> addCartNum(@PathVariable("cid") Integer cid, HttpSession session) {
    return new JsonResult<>(OK, cartService.addCartNum(cid, getUidFromSession(session), getUsernameFromSession(session)));
}
```

### 3.4 前端页面

```js
<script type="text/javascript">
    $(document).ready(function () {
    $("#cart-list").empty();
    showCartList();
});

function showCartList() {
    $.ajax({
        url: "/carts/",
        type: "GET",
        dataType: "JSON",
        success: function (json) {
            let list = json.data;
            for (let i = 0; i < list.length; i++) {
                let div = '<tr>\n' +
                    '                            <td>\n' +
                    '                                <label>\n' +
                    '                                    <input name="cids" value="#{cid}" type="checkbox" class="ckitem"/>\n' +
                    '                                </label>\n' +
                    '                            </td>\n' +
                    '                            <td><img src="..#{image}collect.png" class="img-responsive" alt=""/></td>\n' +
                    '                            <td>#{title}#{msg}</td>\n' +
                    '                            <td>¥<span id="goodsPrice#{cid}">#{price}</span></td>\n' +
                    '                            <td>\n' +
                    '                                <input name="price-#{cid}" type="button" value="-" class="num-btn" onclick="reduceNum(1)"/>\n' +
                    '                                <input id="goodsCount#{cid}" type="text" size="2" readonly="readonly" class="num-text" value="#{num}">\n' +
                    '                                <input name="price+#{cid}" class="num-btn" type="button" value="+" onclick="addNum(#{cid})"/>\n' +
                    '                            </td>\n' +
                    '                            <td><span id="goodsCast#{cid}">#{totalPrice}</span></td>\n' +
                    '                            <td>\n' +
                    '                                <input type="button" onclick="delCartItem(this)" class="cart-del btn btn-default btn-xs" value="删除"/>\n' +
                    '                            </td>\n' +
                    '</tr>';

                div = div.replace(/#{cid}/g, list[i].cid);
                div = div.replace("#{image}", list[i].image);
                div = div.replace("#{msg}", list[i].realPrice);
                div = div.replace("#{title}", list[i].title);
                div = div.replace("#{price}", list[i].price);
                div = div.replace("#{num}", list[i].num);
                div = div.replace("#{totalPrice}", list[i].price * list[i].num);

                $("#cart-list").append(div);
            }
        },
        error: function (xhr) {
            alert("加载商品信息出现未知错误" + "\n" + xhr.status);
        }
    });
}

function addNum(cid) {
    $.ajax({
        url: "/carts/add_num/" + cid,
        type: "POST",
        dataType: "JSON",
        success: function (json) {
            if (json.state === 200) {
                //设置商品数量
                $("#goodsCount" + cid).val(json.data);
                //设置商品总价
                $("#goodsCast" + cid).html($("#goodsPrice" + cid).html() * json.data);
            } else {
                alert("增加商品数量失败" + "\n" + json.message);
            }
        },
        error: function (xhr) {
            alert("添加商品数量出现未知错误" + "\n" + xhr.status);
        }
    });
}
</script>
```

## 4.显示勾选的购物车数据

### 4.1 持久层

#### 4.1.1 设计SQL

用户在购物车列表页面中随机勾选相关的商品，在点击“结算”按钮后，跳转到结算页面。在这个页面中需要展示用户在上一个页面所勾选的购物车对应的相关数据，即查询语句

```sql
select 
	cid,
	uid,
	pid,		
	t_cart.price,
	t_cart.num,	
	t_product.title,
	t_product.image,
	t_product.price as realPrice 
from 
	t_cart left join t_product on t_cart.pid = t_product.id
where 
	cid in (?,?,?)
order by 
	t_cart.create_time desc
```

#### 4.1.2 设计接口和抽象方法

```java
    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cid 购物车id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
List<CartVo> selectVOByCidList(Integer cid);
```

#### 4.1.3 添加映射

```xml
    <!--List<CartVo> selectVOByCid(Integer cid);-->
    <select id="selectVOByCidList" resultType="com.lilin.vo.CartVo">
        select
            cid,uid,pid,t_cart.price,t_cart.num,t_product.title,t_product.image,t_product.price as realPrice
        from
            t_cart left join t_product on t_cart.pid = t_product.id
        where
            cid in (
                <foreach collection="array" item="cid" separator=",">
                    #{cid}
                </foreach>
                )
        order by
            t_cart.created_time desc
    </select>
```

#### 4.1.4 单元测试

```java
@Test
public void selectVOByCid() {
    cartMapper.selectVOByCidList(new Integer[]{1, 2, 3, 4, 5}).forEach(System.out :: println);
}
```

### 4.2 业务层

#### 4.2.1 设计异常

> 无

#### 4.2.2 设计抽象方法

```java
    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cidList 购物车id列表
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
List<CartVo> selectVOByCidList(Integer uid, Integer[] cidList);
```

#### 4.2.3 实现抽象方法

```java
    /**
     * @Author LiLin
     * @Date 2022/4/15 20:22
     * @Param cidList 购物车id列表
     * @Param uid 用户id
     * @return 返回查询到的商品信息列表
     * @Description 查询购物车里的商品信息
     */
@Override
public List<CartVo> selectVOByCidList(Integer uid, Integer[] cidList) {
    List<CartVo> list = cartMapper.selectVOByCidList(cidList);
    //如果 cidList 集合中有不属于 uid 的数据，就将其移除
    list.removeIf(cartVo -> !Objects.equals(cartVo.getUid(), uid));
    return list;
}
```

#### 4.2.4 单元测试

```java
@Test
public void selectVOByCidList() {
    cartService.selectVOByCidList(6, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
        .forEach(System.out :: println);
}
```

### 4.3 控制层

#### 4.3.1 规划异常

> 无

#### 4.3.2 设计请求

- url：/carts/list
- param：Integer[] ids, HttpSession, session
- type：POST
- return：JsonResult<List\<CartVo>>

#### 4.3.3 实现请求

```java
@RequestMapping("list")
public JsonResult<List<CartVo>> getCartVoList(Integer[] cidList, HttpSession session) {
    return new JsonResult<>(OK, cartService.selectVOByCidList(getUidFromSession(session), cidList));
}
```

### 4.4 前端页面

#### 4.4.1 增加购物车的商品数量

将`cart.html`页面中的“结算”按钮属性更改为`submit`

```html
<input type="submit" value="  结  算  " class="btn btn-primary btn-lg link-account"/>
```

在`ordderConfirm.html`页面中自动加载从上一个页面中传递过来的`cidList`数据，再请求`ajax`，在页面中填充数据

```html
<script>
    $(document).ready(function () {
        $("#cart-list").empty();
        showCartList();
    });

    function showCartList() {
        $.ajax({
            url: "/carts/list",
            type: "POST",
            //location.search.substr(1) 中的 1 表示本地地址链接 ？ 后面的所有参数；0 表示 ？ 前面的部分
            data: location.search.substr(1),
            dataType: "JSON",
            success: function (json) {
                if (json.state === 200) {
                    let list = json.data;
                    let allCount = 0;
                    let allPrice = 0;

                    for (let i = 0; i < list.length; i++) {
                        let div = '<tr>\n' +
                                        '<td><img src="..#{image}collect.png" class="img-responsive" alt=""/></td>\n' +
                                        '<td>#{title}</td>\n' +
                                        '<td>¥<span>#{price}</span></td>\n' +
                                        '<td>#{num}</td>\n' +
                                        '<td><span>#{totalPrice}</span></td>\n' +
                                  '</tr>';

                        div = div.replace(/#{cid}/g, list[i].cid);
                        div = div.replace(/#{image}/g, list[i].image);
                        div = div.replace(/#{title}/g, list[i].title);
                        div = div.replace(/#{price}/g, list[i].price);
                        div = div.replace(/#{num}/g, list[i].num);
                        div = div.replace(/#{totalPrice}/g, list[i].price * list[i].num);

                        $("#cart-list").append(div);

                        allCount += list[i].num;
                        allPrice += list[i].price * list[i].num;
                    }

                    $("#all-count").html(allCount);
                    $("#all-price").html(allPrice);
                } else {
                    alert("加载购物车数据失败" + "\n" + json.message);
                }
            },
            error: function (xhr) {
                alert("加载购物车数据出现未知错误" + "\n" + xhr.status);
            }
        });
    }
</script>
```

#### 4.4.2 购物车收货地址显示

在`orderConfirm.html`页面中将收货地址存放在一个`select`下拉列表中，将查询到的当前登录用户的收货地址动态的加载到下拉列表中。

```js
//在 select 下拉框中展示收货地址
function showAddressListInOrderConfirm() {
    $.ajax({
        url: "/addresses",
        type: "GET",
        dataType: "JSON",
        success: function (json) {
            if (json.state === 200) {
                let list = json.data;

                for (let i = 0; i < list.length; i++) {
                    let option = '<option value="#{aid}">#{name}&nbsp;&nbsp;&nbsp;#{tag}&nbsp;&nbsp;&nbsp;#{provinceName}#{cityName}#{areaName}#{address}&nbsp;&nbsp;&nbsp;#{phone}</option>';

                    option = option.replace(/#{aid}/g, list[i].aid);
                    option = option.replace(/#{name}/g, list[i].name);
                    option = option.replace(/#{tag}/g, list[i].tag);
                    option = option.replace(/#{provinceName}/g, list[i].provinceName);
                    option = option.replace(/#{cityName}/g, list[i].cityName);
                    option = option.replace(/#{areaName}/g, list[i].areaName);
                    option = option.replace(/#{address}/g, list[i].address);
                    option = option.replace(/#{phone}/g, list[i].phone);

                    $("#address-list").append(option);
                }
            } else {
                alert("加载收货地址数据失败" + "\n" + json.message);
            }
        },
        error: function (xhr) {
            alert("加载收货地址数据出现未知错误" + "\n" + xhr.status);
        }
    });
}
```

# 八、订单

## 1.持久层

### 1.1 创建数据表

订单表

```sql
CREATE TABLE t_order (
	oid INT AUTO_INCREMENT COMMENT '订单id',
	uid INT NOT NULL COMMENT '用户id',
	recv_name VARCHAR(20) NOT NULL COMMENT '收货人姓名',
	recv_phone VARCHAR(20) COMMENT '收货人电话',
	recv_province VARCHAR(15) COMMENT '收货人所在省',
	recv_city VARCHAR(15) COMMENT '收货人所在市',
	recv_area VARCHAR(15) COMMENT '收货人所在区',
	recv_address VARCHAR(50) COMMENT '收货详细地址',
	total_price BIGINT COMMENT '总价',
	status INT COMMENT '状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成',
	order_time DATETIME COMMENT '下单时间',
	pay_time DATETIME COMMENT '支付时间',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (oid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_order_item (
```

订单项的表

```sql
CREATE TABLE t_order_item (
	id INT AUTO_INCREMENT COMMENT '订单中的商品记录的id',
	oid INT NOT NULL COMMENT '所归属的订单的id',
	pid INT NOT NULL COMMENT '商品的id',
	title VARCHAR(100) NOT NULL COMMENT '商品标题',
	image VARCHAR(500) COMMENT '商品图片',
	price BIGINT COMMENT '商品价格',
	num INT COMMENT '购买数量',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 1.2 创建实体类

订单实体类

```java
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    private Integer oid;            //订单id
    private Integer uid;            //用户id
    private String recvName;        //收货人姓名
    private String recvPhone;       //收货人电话
    private String recvProvince;    //收货人所在省
    private String recvCity;        //收货人所在市
    private String recvArea;        //收货人所在区
    private String recvAddress;     //收货详细地址
    private Long totalPrice;        //总价
    private Integer status;         //状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
    private Date orderTime;         //下单时间
    private Date payTime;           //支付时间
}
```

订单项实体类

```java
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {
    private Integer id;     //订单中的商品记录的id
    private Integer oid;    //所归属的订单的id
    private Integer pid;    //商品的id
    private String title;   //商品标题
    private String image;   //商品图片
    private Long price;     //商品价格
    private Integer num;    //购买数量
}
```

### 1.3 设计SQL

将数据插入到订单表中

```sql
insert into t_order(oid除外的所有字段) values(字段对应的值)
```

将数据插入到订单项的表中

```sql
insert into t_order_item(id除外的所有字段) values(字段对应的值)
```

### 1.4 设计接口和抽象方法

```java
public interface OrderMapper {
    /**
     * @Author LiLin
     * @Date 2022/4/19 15:16
     * @Param order 订单表的实体类
     * @return 返回影响的行数
     * @Description 向Order表中添加数据
     */
    Integer insertToOrder(Order order);

    /**
     * @Author LiLin
     * @Date 2022/4/19 15:16
     * @Param orderItem 订单项表的实体类
     * @return 返回影响的行数
     * @Description 向OrderItem表中添加数据
     */
    Integer insertToOrderItem(OrderItem orderItem);
}
```

### 1.5 创建映射文件

```sql
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.lilin.mapper.OrderMapper">
    <resultMap id="OrderEntityMapper" type="com.lilin.pojo.Order">
        <!--在定义映射规则时主键不可省略-->
        <result property="oid" column="oid" />

        <result property="recvName" column="recv_name" />
        <result property="recvPhone" column="recv_phone" />
        <result property="recvProvince" column="recv_province" />
        <result property="recvCity" column="recv_city" />
        <result property="recvArea" column="recv_area" />
        <result property="recvAddress" column="recv_address" />
        <result property="totalPrice" column="total_price" />
        <result property="orderTime" column="order_time" />
        <result property="payTime" column="pay_time" />
        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedTime" column="modified_time" />
    </resultMap>
    <resultMap id="OrderItemEntityMapper" type="com.lilin.pojo.OrderItem">
        <!--在定义映射规则时主键不可省略-->
        <result property="id" column="id" />

        <result property="createdUser" column="created_user" />
        <result property="createdTime" column="created_time" />
        <result property="modifiedUser" column="modified_user" />
        <result property="modifiedTime" column="modified_time" />
    </resultMap>

    <!--Integer insertToOrder(Order order);-->
    <insert id="insertToOrder" useGeneratedKeys="true" keyProperty="oid">
        insert into
            t_order(uid,recv_name,recv_phone,recv_province,recv_city,recv_area,recv_address,total_price,
                    `status`,order_time,pay_time,created_user,created_time,modified_user,modified_time,)
        values
           (#{uid},#{recvName},#{recvPhone},#{recvProvince},#{recvCity},#{recvArea},#{recvAddress},
            #{totalPrice},#{status},#{orderTime},#{payTime},#{createdUser},#{createdTime},
            #{modifiedUser},#{modifiedTime})
    </insert>
    <!--Integer insertToOrderItem(OrderItem orderItem);-->
    <insert id="insertToOrderItem" useGeneratedKeys="true" keyProperty="id">
        insert into
            t_order_item(oid,pid,title,image,price,num,created_user,created_time,modified_user,
                         modified_time)
        values
           (#{oid},#{pid},#{title},#{image},#{price},#{num},#{createdUser},#{createdTime},#{modifiedUser},
            #{modifiedTime})
    </insert>

</mapper>
```

### 1.6 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void insertToOrder() {
        Order order = new Order();
        order.setUid(1);
        order.setRecvName("admin");
        order.setRecvPhone("18276361404");

        System.out.println(orderMapper.insertToOrder(order));
    }

    @Test
    public void insertToOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("this is a title");
        orderItem.setImage("/i/am/god/i/love/myself/");
        orderItem.setPrice(1234L);
        orderItem.setNum(1);

        System.out.println(orderMapper.insertToOrderItem(orderItem));
    }
}
```

## 2.业务层

### 2.1 完善`AddressService`

在`AddressService.java`接口中定义根据收货地址的`id`获取收货地址的数据

#### 2.1.1 定义接口和抽象方法

```java
    /**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
Address getAddressByAid(Integer aid, Integer uid);
```

#### 2.1.2 实现抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/19 16:36
     * @Param aid 收货地址id
     * @Param uid 用户id
     * @return 返回查询到的收货地址数据
     * @Description 根据aid获取收货地址数据
     */
@Override
public Address getAddressByAid(Integer aid, Integer uid) {
    Address address = addressMapper.selectDefaultAddressByAid(aid);

    //异常判断
    if (address == null)
        throw new AddressNotFoundException("收货地址数据不存在");
    if (!uid.equals(address.getUid()))
        throw new AccessDeniedException("收货地址数据非法访问");

    //减少数据冗余
    address.setProvinceCode(null);
    address.setCityCode(null);
    address.setAreaCode(null);
    address.setCreatedUser(null);
    address.setCreatedTime(null);
    address.setModifiedUser(null);
    address.setModifiedTime(null);

    return address;
}
```

### 2.2 定义接口和抽象方法

```java
public interface OrderService {
    /**
     * @Author LiLin
     * @Date 2022/4/19 16:57
     * @Param aid 收货地址id
     * @Param uid 用户的id
     * @Param cids 购物车的id集合
     * @Param username 用户名
     * @return 返回创建的订单
     * @Description 创建订单
     */
    Order createOrder(Integer aid, Integer uid, Integer[] cids, String username);
}
```

### 2.3 实现抽象方法

```java
/**
     * @Author LiLin
     * @Date 2022/4/19 16:57
     * @Param aid 收货地址id
     * @Param uid 用户的id
     * @Param cids 购物车的id集合
     * @Param username 用户名
     * @return 返回创建的订单
     * @Description 创建订单
     */
@Override
public Order createOrder(Integer aid, Integer uid, Integer[] cids, String username) {
    //获取购物车数据
    List<CartVo> list = cartService.selectVOByCidList(uid, cids);

    //计算商品的总价
    long totalPrice = 0L;
    for (CartVo cartVo: list)
        totalPrice = cartVo.getRealPrice() * cartVo.getNum();

    //获取收货地址数据
    Address address = addressService.getAddressByAid(aid, uid);


    Order order = new Order();
    //补全order数据
    //添加收货地址数据
    order.setUid(uid);
    order.setRecvName(address.getName());
    order.setRecvPhone(address.getPhone());
    order.setRecvProvince(address.getProvinceName());
    order.setRecvCity(address.getCityCode());
    order.setRecvArea(address.getAreaName());
    order.setRecvAddress(address.getAddress());
    //添加支付、总价、时间
    Date date = new Date();
    order.setOrderTime(date);
    order.setTotalPrice(totalPrice);
    order.setStatus(0);
    //添加日志
    order.setCreatedUser(username);
    order.setModifiedUser(username);
    order.setCreatedTime(date);
    order.setModifiedTime(date);

    //保存订单信息
    if (orderMapper.insertToOrder(order) != 1)
        throw new InsertException("保存订单数据过程中出现未知异常");

    for (CartVo cartVo: list) {
        //创建订单详细项的数据
        OrderItem orderItem = new OrderItem();

        //补全orderItem数据
        orderItem.setOid(order.getOid());
        orderItem.setPid(cartVo.getPid());
        orderItem.setTitle(cartVo.getTitle());
        orderItem.setImage(cartVo.getImage());
        orderItem.setPrice(cartVo.getRealPrice());
        orderItem.setNum(cartVo.getNum());
        //日志信息
        orderItem.setCreatedUser(username);
        orderItem.setModifiedUser(username);
        orderItem.setCreatedTime(date);
        orderItem.setModifiedTime(date);

        //保存订单详细项数据
        if (orderMapper.insertToOrderItem(orderItem) != 1)
            throw new InsertException("保存订单详细项数据过程中出现未知异常");
    }

    return order;
}
```

### 2.4 单元测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    OrderService orderService;

    @Test
    public void createOrder() {
        System.out.println(orderService.createOrder(7, 6, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, "amy"));
    }
}
```

## 3.控制层

### 3.1 规划异常

> 无异常

### 3.2 设计请求

- url: /orders/create
- param: Integer aid, Integer[] cids, HttpSession session
- type: POST
- return: JsonResult\<Order>

### 3.3 实现请求

```java
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        return new JsonResult<>(OK, orderService.createOrder(aid, getUidFromSession(session), cids, getUsernameFromSession(session)));
    }
}
```

## 4.前端页面

```js
/*在线支付点击事件*/
$("#btn-create-order").click(function () {
    let aid = $("#address-list").val();     //aid=12
    let cids = location.search.substr(1);   //cids=1&cids=2

    $.ajax({
        url: "/orders/create",
        type: "POST",
        data: "aid=" + aid + "&" + cids,
        dataType: "JSON",
        success: function (json) {
            if (json.state === 200) {
                location.href = "payment.html";
                alert("创建订单成功");
            } else {
                alert("加载订单数据失败" + "\n" + json.message);
            }
        },
        error: function (xhr) {
            alert("加载订单数据出现未知错误" + "\n" + xhr.status);
        }
    });
})
```

# 九、AOP优化项目

统计业务方法的耗时时间。

检测项目的所有业务层方法的耗时时长（开始执行时间和结束执行之差），需要在不改变项目主体的流程代码的前提下完成此功能。

## 1.AOP概念

面向切面编程，并不是Spring框架的特性，但是Spring框架可以很好的支持AOP编程。

如果需要相对业务的某一些方法同时添加相同的功能需求，并且在不改变原有的业务功能逻辑的基础上完成，就可以使用AOP的切面编程完成。

步骤：

- 首先定义一个切面类
- 再在类里面定义切面方法（一共有5中类型的切面方法）
- 在切面方法中对要执行的业务逻辑的代码进行设计和编写
- 通过连接点连接目标方法，即用粗粒度表达式和细粒度表达式进行连接

## 2.切面方法注意

- 1.切面方法的修饰符必须是`Public`
- 2.切面方法的返回值可以是`Void`和`Object`，如果切面方法被`@Aroud`注解修饰此方法必须声明为`Object`类型；反之随意
- 3.切面方法的方法名可以自定义
- 4.切面方法可以接受参数。参数是`ProccedingJoinPoint`接口类型的参数，但是`@Aroud`注解所修饰的方法必须要传递这个参数，其他随意

## 3.统计业务方法执行时长

### 3.1 导包

由于AOP不是Spring的内部封装技术，所以需要进行导包操作

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjtools</artifactId>
</dependency>
```

### 3.2 定义切面类

```java
@Component  //将当前类的对象创建使用与维护交由Spring容器管理
@Aspect     //将当前类标记为切面类
public class TimerAspect {
}
```

### 3.3 定义切面方法

```java
public Object around(ProceedingJoinPoint pjp) throws Throwable {
    //记录当前时间
    long start = System.currentTimeMillis();

    Object result = pjp.proceed(); //调用目标方法，例如：login

    //记录结束时间
    long end = System.currentTimeMillis();
    System.out.println("耗时: " + (end-start));
    return result;
}
```

### 3.4 指定连接点

将当前的环绕通知映射到切面上

```java
@Around("execution(* com.lilin.service.impl.*.*(..))")
public Object around(ProceedingJoinPoint pjp) throws Throwable {
    //记录当前时间
    long start = System.currentTimeMillis();

    Object result = pjp.proceed(); //调用目标方法，例如：login

    //记录结束时间
    long end = System.currentTimeMillis();
    System.out.println("耗时: " + (end-start));
    return result;
}
```