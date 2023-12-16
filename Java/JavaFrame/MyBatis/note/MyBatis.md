# 一、MyBatis简介

## 1.MyBatis历史

MyBatis最初是Apache的一个开源项目**iBatis**，2010nian6月这个项目由Apache Software Foundation迁移到了Google Code。随着开发团队转投Google Code旗下，iBatis3.x正式更名为MyBatis。代码于2013年11月迁移到Github。

iBatis一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。iBatis提供的持久层框架包括SQLMaps和Data Access Object（DAO）。

## 2.MyBatis特性

- 1）MyBatis是支持定制化SQL、储存过程以及高级映射的优秀的持久层框架
- 2）MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集
- 3）MyBatis可以使用简单的XML或注释用于配置和原始映射，将接口和Java的POJO（Plain Old Java Object，普通的Java对象）映射成数据库中的记录。
- 4）MyBatis是一个半自动的ORM（Object Relation Mapping）框架

## 3.MyBatis的下载

MyBatis下载地址：https://github.com/mybatis/mybatis-3

## 4.和其它持久化层技术对比

- JDBC
  - SQL夹杂在Java代码中耦合度高，导致硬编码内伤
  - 维护不易而且实际开发需求中SQL有变化，频繁修改的情况多见
  - 代码冗长，开发效率低
- Hibernate和JPA
  - 操作简便，开发效率高
  - 程序中的长难复杂SQL需要绕过框架
  - 内部自动生产的SQL，不容易做特殊优化
  - 基于全映射的全自动框架，大量字段的POJO进行部分映射时比较困难
  - 反射操作太多，导致数据库性能下降
- MyBatis
  - 轻量级、性能出色
  - SQL和Java编码分开，功能边界清晰。Java代码专注业务，SQL语句专注数据
  - 开发效率稍逊于HiBernate，但是在可接受范围内

# 二、搭建MyBatis环境

## 1.创建maven工程

- 1）打包方式：jar
- 2）引入依赖

```xml
<dependencies>
    <!--junit测试包-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <!--Mysql驱动包-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
    <!--MyBatis核心-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
</dependencies>
```

## 2.创建MyBatis核心配置文件

> 习惯上命名为mybatis-config.xml，这个文件名仅仅只是建议，并非强制要求。将来整合Spring后，这个配置文件可以省略。
>
> 核心配置文件主要用于配置连接数据库的环境以及MyBatis的全局配置信息
>
> 核心配置文件存放的位置是 src/main/resources/ 目录下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 配置文件的根元素 -->
<configuration>
    <!--配置连接数据库的环境-->
    <environments default="development">
        <environment id="development">
        <transactionManager type="JDBC" />
            <dataSource type="POOLED">
                <property name="driver" value="${driver}" />
                <property name="url" value="${url}" />
                <property name="username" value="${username}" />
                <property name="password" value="${password}" />
            </dataSource>
        </environment>
    </environments>
    <!--引入映射文件-->
    <mappers>
    	<mapper resource="org/mybatis/example/BlogMapper.xml" />
    </mappers>
</configuration>
```

## 3.创建mapper接口

> MyBatis中的mapper接口相当于以前的dao，mapper仅仅只是一个接口，我们不需要为其提供实现类

```java
public interface UserMapper {
    /*
    添加用户信息
     */
    int insertUser();
}
```

## 4.创建MyBatis的映射文件

相关概念：**ORM**（Object Relationship Mapping）对象关系映射

- 对象：Java的实体类对象
- 关系：关系型数据库
- 映射：二者之间的对应关系

| Java概念 | 数据库概念 |
| :------: | :--------: |
|    类    |     表     |
|   属性   |  字段/列   |
|   对象   |  记录/行   |

> - 1、映射文件的命名规则
>
>   表所对应的实体类的类名 + Mapper.xml
>
>   例如：表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml
>
>   因此一个映射文件对应一个实体类，对应一张表的操作
>
>   MyBatis映射文件用于编写SQL，访问以及操作表中的数据
>
>   MyBatis映射文件存放的位置是：src/main/resources/mappers 目录下
>
> - 2、MyBatis中可以面向接口操作数据，但是要保证两个一致：
>
>   a、映射文件的namespace要和mapper接口的全类名保持一致
>
>   b、映射文件中SQL语句的id要和mapper接口中的方法名一致

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.MrLi.mapper.UserMapper">
    <insert id="insertUser">
        insert into `t_user`(username,`password`,age,sex,email) values('smith','123abc',12,'boy','smith@qq.com')
    </insert>
</mapper>
```

## 5.通过junit测试功能

```java
@Test
public void testMybatis() throws Exception {
    //加载核心配置文件
    InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
    //获取sqlSessionFactoryBuilder
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    //获取sqlSessionFactory
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
    //获取sqlSession
    //sqlSession默认不自动提交事务，如果需要自动提交事务可以使用：sqlSessionFactory.openSession(true);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    //获取mapper接口对象
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    //测试功能
    int result = userMapper.insertUser();
    //提交事务
    //sqlSession.commit();
    System.out.println("一共影响了" + result + "行数据");
}
```

> - sqlSession：代表Java程序和数据库之间的对话（HttpSession是Java程序和浏览器之间的对话）
>
> - sqlSessionFactory：是生产“SqlSession”的工厂
>
> - 工厂模式：如果创建一个对象，使用的过程基本固定，那么我们就可以把创建这个对象的相关代码封装到一个“工厂类”中，在以后使用这个工厂类来生产我们需要的对象

## 6.加入 log4j 日志功能

- 1）加入依赖

```xml
<!--log4j日志-->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

- 2）加入log4j的配置文件

> log4j的配置文件名是：log4j.xml，存放的位置是：src/main/resources目录下

```xml
<?xml version="1.0" encoding="utf-8" ?>
<! DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
        <param name="Encoding" value="UTF-8" />
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m(%F:%L) \n" />
        </layout>
    </appender>
    <logger name="java.sql">
        <level value="debug" />
    </logger>
    <logger name="org.apache.ibatis">
        <level value="info" />
    </logger>
    <root>
        <level value="debug" />
        <appender-ref ref="STDOUT" />
    </root>
</log4j:configuration>
```

> **日志的级别**
>
> FATAL(致命) > ERROR(错误) > WARN(警告) > INFO(信息) > DEBUG(调试)
>
> 从左到右打印的内容越来越详细

# 三、核心配置文件详解

核心配置文件中的标签必须按照固定的顺序：

properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,plugins?,environments?,databaseLdProvider?,mappers?

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 配置文件的根元素 -->
<configuration>
    <properties resource="jdbc.properties" />

    <!--
        核心配置文件中的标签必须按照固定的顺序：
            properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,
            plugins?,environments?,databaseLdProvider?,mappers?
    -->
    <typeAliases>
        <!--<typeAlias type="com.MrLi.pojo.User" alias="user" />  不常用-->

        <!--以包为单位，为包下所有的类型设置默认的类型别名，即类名且不区分大小写  常用-->
        <package name="com.MrLi.pojo"/>
    </typeAliases>

    <!--
        environments：配置多个连接数据库的环境
        属性：
            default：设置默认使用的环境的 id
    -->
    <environments default="development">
        <!--
            environment：配置某个具体的环境
            属性：
                id：表示连接数据库环境的唯一标识，不能重复
        -->
        <environment id="development">
            <!--
                transactionManager：设置事务管理方式
                属性：
                    type="JDBC"：表示当前环境中，执行SQL时，使用的是JDBC中原生的事务管理方式，事物的提交或者回滚需要手动设置
                    type="MANAGED"：表示被管理，例如Spring
            -->
            <transactionManager type="JDBC" />
            <!--
                dataSource：配置数据源
                属性：
                    type="POOLED"：表示使用数据库连接池缓存数据库连接
                    type="UNPOOLED"：表示不使用数据库连接池
                    type="JNDI"：表示使用上下文中的数据源
            -->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}" />
                <property name="url" value="${jdbc.url}" />
                <property name="username" value="${jdbc.username}" />
                <property name="password" value="${jdbc.password}" />
            </dataSource>
        </environment>
    </environments>
    <!--引入映射文件-->
    <mappers>
        <!--<mapper resource="mappers/UserMapper.xml" />-->

        <!--
            以包为单位引入映射文件
            要求：
                1、mapper接口所在的包要和映射文件所在的包一致
                2、mapper接口要和映射文件的名字一致
        -->
        <package name="com.MrLi.mapper"/>
    </mappers>
</configuration>
```

# 四、MyBatis的增删改查

## 1.添加用户信息

```xml
<!--添加用户信息-->
<insert id="insertUser">
    insert into `t_user`(username,`password`,age,sex,email) values('smith','123abc',12,'boy','smith@qq.com')
</insert>
```

## 2.删除用户信息

```xml
<!--删除用户信息-->
<delete id="deleteUser">
    delete  from t_user where id=2
</delete>
```

## 3.修改用户信息

```xml
<!--修改用户信息-->
<update id="updateUser">
    update t_user set username='tom',`password`='123abc',age=15,sex='boy',email='smith@qq.com' where id=1
</update>
```

## 4.查询用户信息

```xml
<!--根据id查询用户信息-->
<!--
        查询功能的标签必须设置 resultType 或 resultMap
        resultType：设置默认的映射关系
        resultMap：设置自定义的映射关系
    -->
<select id="queryUserById" resultType="com.MrLi.pojo.User">
    select * from t_user where id=2
</select>

<select id="queryUsers" resultType="user">
    select * from t_user
</select>
```

# 五、MyBatis获取参数值的两种方式

MyBatis获取参数值的两种方式：**${}**和**#{}**

${}的本质是字符串的拼接，#{}的本质是占位符的赋值

${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，需要手动加单引号；但是#{}使用占位符复制的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以自动添加单引号。

## 1.单个字面量类型的参数

若mapper接口中的方法参数为单个的字面量类型

此时可以使用${}和#{}以任意的名称获取参数的值，注意${}需要手动加单引号

```xml
<!--根据员工名查询员工信息-->
<select id="queryUserByUsername" resultType="User">
	<!--select * from t_user where username=#{username}-->
	select * from t_user where username='${username}'
</select>
```

## 2.多个字面量类型的参数

若mapper接口中的方法参数为多个时

此时mybatis会自动将这些参数放在一个map集合中，以arg0，arg1……为键，以参数为值；以param1，param2……为键，以参数为值；因此只需要通过${}和#{}访问map集合的键就可以获取相对应的值，注意${}需要手动加单引号。

```xml
<!--根据用户名和用户密码查询用户信息-->
<select id="queryUserByUsernameAndPassword" resultType="User">
    <!--select * from t_user where username=#{arg0} and password=#{arg1}-->
    select * from t_user where username='${param1}' and password='${param2}'
</select>
```

## 3.map集合类型的参数

若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中储存

只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

```xml
<!--通过map查询用户信息-->
<select id="queryUserByMap" resultType="User">
    <!--select * from t_user where username=#{username} and password=#{password}-->
    select * from t_user where username='${username}' and password='${password}'
</select>
```

## 4.实体类类型的参数

只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

```xml
<!--添加用户信息-->
<insert id="insertUser">
    <!--insert into t_user(username,`password`,age,sex,email) values(#{username},#{password},#{age},#{sex},#{email})-->
    insert into t_user(username,`password`,age,sex,email) values('${username}','${password}','${age}','${sex}','${email}')
</insert>
```

## 5.使用 @param 命名参数

此时MyBatis会将这些参数放在一个map集合中，以两种方式进行储存

- 1）以@param注解的值为键，以参数为值

- 2）以param1，param2……为键。以参数为值

因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题

```java
//通过@param查询用户信息
User queryUserByParam(@Param("username")String username, @Param("password")String password);
```

```xml
<!--通过@param查询用户信息-->
<select id="queryUserByParam" resultType="User">
    <!--select * from t_user where username=#{username} and password=#{password}-->
    select * from t_user where username='${username}' and password='${password}'
</select>
```

## 6.建议

> 当传输的参数为一个**map集合**时，使用方法3
>
> 当传输的参数为**单个字面量**、**多个字面量**、**实体类**类型时，使用方法5

# 六、MyBatis的各种查询功能

## 1.查询一个实体类对象

```java
//根据id查询用户信息
User getUserById(@Param("id") Integer id);
```

```java
/*
        mybatis的各种查询功能：
            1、若查询出的数据只有一条，可以通过实体类对象或者列表接收
            2、若查询出的数据有多条，可以通过列表接收，但一定不可以通过实体类对象接受
 */
@Test
public void getUserById() {
    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
    User user = mapper.getUserById(3);
    System.out.println(user);
}
```

## 2.查询一个list集合

```java
//查询所有用户信息
List<User> getAllUser();
```

```xml
<!--查询所有用户信息-->
<select id="getAllUser" resultType="com.MrLi.pojo.User">
    select * from t_user
</select>
```

```java
/*
        mybatis的各种查询功能：
            1、若查询出的数据只有一条，可以通过实体类对象或者列表接收
            2、若查询出的数据有多条，可以通过列表接收，但一定不可以通过实体类对象接受
 */
@Test
public void getAllUser() {
    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
    List<User> users = mapper.getAllUser();
    users.forEach(System.out::println);
}
```

## 3.查询一个单行单列的数据

```java
//查询用户信息总记录数
Integer getUserCount();
```

```xml
<!--查询用户信息总记录数-->
<select id="getUserCount" resultType="java.lang.Integer">
    select count(*) from t_user
</select>
```

## 4.查询一条数据为map集合

```java
//根据用户id查询用户信息到一个map集合中
Map<String, Object> getUserByIdToMap(@Param("id")Integer id);
```

```xml
<!--根据用户id查询用户信息到一个map集合中-->
<select id="getUserByIdToMap" resultType="java.util.Map">
    select * from t_user where id=#{id}
</select>
```

## 5.查询多条数据为map集合

### 方式一：

```java
//查询所有用户信息到一个map集合中
List<Map<String, Object>> getAllUserToMap();
```

```xml
<!--查询所有用户信息到一个map集合中-->
<select id="getAllUserToMap" resultType="java.util.Map">
    select * from t_user
</select>
```

### 方式二：

```java
//查询所有用户信息到一个map集合中
@MapKey("id")
Map<String, Object> getAllUserToMap();
```

```xml
<!--查询所有用户信息到一个map集合中-->
<select id="getAllUserToMap" resultType="java.util.Map">
    select * from t_user
</select>
```

# 七、特殊SQL的执行

## 1.模糊查询

#{}方式：报异常

```xml
<!--根据用户名模糊查询用户信息-->
<select id="getUserByLike" resultType="com.MrLi.pojo.User">
    select * from t_user username like '%#{username}%'
</select>
```

${}方式：正常运行

```xml
<!--根据用户名模糊查询用户信息-->
<select id="getUserByLike" resultType="com.MrLi.pojo.User">
    select * from t_user where username like '%${username}%'
</select>
```

concat()字符串拼接：正常运行

```xml
<!--根据用户名模糊查询用户信息-->
<select id="getUserByLike" resultType="com.MrLi.pojo.User">
    select * from t_user where username like concat('%',#{username},'%')
</select>
```

"%"方式：正常运行，推荐

```xml
<!--根据用户名模糊查询用户信息-->
<select id="getUserByLike" resultType="com.MrLi.pojo.User">
    select * from t_user where username like "%"#{username}"%"
</select>
```

## 2.批量删除

```java
//批量删除用户信息
int deleteMore(@Param("ids") String ids);
```

```xml
<!--批量删除用户信息-->
<delete id="deleteMore">
    delete from t_user where id in (${ids})
</delete>
```

## 3.动态设置表名

```java
//查询指定表中的数据
List<User> getUserByTableName(@Param("tableName") String tableName);
```

```xml
<!--批量删除用户信息-->
<delete id="deleteMore">
    delete from t_user where id in (${ids})
</delete>
```

## 4.添加功能获取自增的主键

t_clazz(clazz_id, clazz_name)

t_student(student_id, student_name, clazz_id)

- 1、添加班级信息
- 2、获取新添加的班级id
- 3、为班级分配学生，即将某学生的班级id修改为新添加的班级id

```java
//添加用户信息
void insertUser(User user);
```

```xml
<!--
        添加用户信息
        useGeneratedKeys：设置当前标签中的sql使用了自增的主键
        keyProperty：将自增的主键值赋值给传输到映射文件中参数的某个属性
    -->
<insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
    insert into t_user values(null,#{username},#{password},#{age},#{sex},#{email})
</insert>
```

```java
@Test
public void insertUser() {
    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
    User user = new User(null,"amy","123abc",15,"girl","amy@qq.com");
    mapper.insertUser(user);
    System.out.println(user.getId());
}
```

# 八、自定义映射resultMap

## 1.resultMap处理字段和属性的映射关系

若字段名和实体类中的属性不一致，则可以通过resultMap设置自定义映射

### 方式一：为字段起别名

```xml
<!--查询所有员工信息-->
<select id="getAllEmp" resultType="com.MrLi.pojo.Emp">
    select eid,emp_name empName,age,sex,email,did from `t_emp`
</select>
```

### 方式二：修改全局配置

```xml
<!--MyBatis的全局配置-->
<settings>
    <!--mapUnderscoreToCamelCase：将 _ 自动映射为驼峰，即 emp_name -> empName-->
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```

```xml
<!--查询所有员工信息-->
<select id="getAllEmp" resultType="com.MrLi.pojo.Emp">
    select * from `t_emp`
</select>
```

### 方式三：resultMap设置自定义映射关系

```xml
<!--查询所有员工信息-->
<!--resultMap：设置自定义映射关系-->
<resultMap id="empResultMap" type="emp">
    <id property="eid" column="eid" />
    <result property="empName" column="emp_name" />
    <result property="age" column="age" />
    <result property="sex" column="sex" />
    <result property="email" column="email" />
    <result property="did" column="did" />
</resultMap>
<select id="getAllEmp" resultMap="empResultMap">
    <!--select eid,emp_name empName,age,sex,email,did from `t_emp`-->
    select * from `t_emp`
</select>
```

## 2.处理多对一映射关系

### 方式一：级联属性赋值

```xml
<!--查询员工以及员工所对应的部门信息-->
<resultMap id="empAndDeptResultMapOne" type="emp">
    <id property="eid" column="eid" />
    <result property="empName" column="emp_name" />
    <result property="age" column="age" />
    <result property="sex" column="sex" />
    <result property="email" column="email" />
    <result property="dept.did" column="did" />
    <result property="dept.deptName" column="dept_name" />
</resultMap>
<select id="getEmpAndDept" resultMap="empAndDeptResultMapOne">
    select * from `t_emp` left join `t_dept` on t_emp.did = t_dept.did where t_emp.eid = #{eid}
</select>
```

### 方式二：通过 *association* 标签

```xml
<!--查询员工以及员工所对应的部门信息-->
<resultMap id="empAndDeptResultMapTwo" type="emp">
    <id property="eid" column="eid" />
    <result property="empName" column="emp_name" />
    <result property="age" column="age" />
    <result property="sex" column="sex" />
    <result property="email" column="email" />
    <association property="dept" javaType="Dept">
        <id property="did" column="did" />
        <result property="deptName" column="dept_name" />
    </association>
</resultMap>
<select id="getEmpAndDept" resultMap="empAndDeptResultMapOne">
    select * from `t_emp` left join `t_dept` on t_emp.did = t_dept.did where t_emp.eid = #{eid}
</select>
```

### 方式三：分布查询（常用）

```java
//通过分布查询查询员工以及员工所对应的部门信息
//分布查询第一步：查询员工信息
Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);
```

```java
//通过分布查询查询员工以及员工所对应的部门信息
//分布查询第二步：通过did查询员工所对应的部门
Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);
```

```xml
<!--通过分布查询查询员工以及员工所对应的部门信息
        分布查询第一步：查询员工信息-->
<resultMap id="empAndDeptByStepResultMap" type="Emp">
    <id property="eid" column="eid" />
    <result property="empName" column="emp_name" />
    <result property="age" column="age" />
    <result property="sex" column="sex" />
    <result property="age" column="age" />
    <result property="email" column="email" />
    <!--
            property：需要处理的多对一中的属性
            select：设置分布查询的sql的唯一标识（namespace.SQLId或者mapper接口的全类名 + .方法名）
            column：设置分布查询的条件
        -->
    <association property="dept"
                 select="com.MrLi.mapper.DeptMapper.getEmpAndDeptByStepTwo"
                 column="did" />
</resultMap>
<select id="getEmpAndDeptByStepOne" resultMap="empAndDeptByStepResultMap">
    select * from `t_emp` where eid = #{eid}
</select>
```

```xml
<!--通过分布查询查询员工以及员工所对应的部门信息
        分布查询第二步：通过did查询员工所对应的部门-->
<select id="getEmpAndDeptByStepTwo" resultType="com.MrLi.pojo.Dept">
    select * from t_dept where did = #{did}
</select>
```

> 分布查询的优点：可以实现延迟加载，但是必须在核心配置文件中设置全局配置信息
>
> lazyLoadingEnabled：延迟加载的全局开关。当其开启时，所有关联对象都会延迟加载。
>
> aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的所有属性。否则，每个属性都会按需加载。
>
> 此时就可实现按需加载，获取的数据是什么，就只会执行相对应的sql。
>
> 此时可通过 association 和 collection 中的 fetchType 属性设置当前的分布查询是否使用延迟加载，fetchType="lazy(延迟加载)|eager(立即加载)"。

# 九、动态SQL

MyBatis的动态SQL技术是一种根据特定条件拼接SQL语句的功能，它存在的意义是为了解决拼接SQL语句字符串时的痛点问题。

## 1. if

if标签可以通过test属性的表达式进行判断。若表达式的结果为ture，则标签中的内容就会执行；反之标签中的内容不会执行。

```java
//多条件查询
List<Emp> getEmpByCondition(Emp emp);
```

```xml
<!--List<Emp> getEmpByCondition();-->
<select id="getEmpByCondition" resultType="com.MrLi.pojo.Emp">
    select * from `t_emp` where 1=1
    <if test="empName != null and empName != ''" >
        and emp_name = #{empName}
    </if>
    <if test="age != null and age != ''" >
        and age = #{age}
    </if>
    <if test="sex != null and sex != ''" >
        and sex = #{sex}
    </if>
    <if test="email != null and email != ''" >
        and email = #{email}
    </if>
</select>
```

## 2.where

当where标签中有内容时，会自动生成where关键字，并且将**内容前**多余的 and 或 or 去掉；

当where标签中没有内容时，此时的where标签没有任何效果，即where标签不会自动生成where关键字；

> 注意：where标签不可以将**内容后面**多余的and或or去掉

```xml
select * from `t_emp`
<where>
    <if test="empName != null and empName != ''" >
        and emp_name = #{empName}
    </if>
    <if test="age != null and age != ''" >
        and age = #{age}
    </if>
    <if test="sex != null and sex != ''" >
        and sex = #{sex}
    </if>
    <if test="email != null and email != ''" >
        and email = #{email}
    </if>
</where>
```

## 3.trim

若trim标签中有内容时：

- prefix/suffix：在trim标签中内容的前面或后面添加指定内容
- prefixOverrides/suffixOverrides：在trim标签中内容的前面或后面去掉指定内容

若trim标签中没有内容时，其将会与where标签一样，没有任何效果。

```xml
<select id="getEmpByCondition" resultType="com.MrLi.pojo.Emp">
    select * from `t_emp`
    <trim prefix="where" suffixOverrides="and|or">
        <if test="empName != null and empName != ''" >
            emp_name = #{empName} and
        </if>
        <if test="age != null and age != ''" >
            age = #{age} and
        </if>
        <if test="sex != null and sex != ''" >
            sex = #{sex} or
        </if>
        <if test="email != null and email != ''" >
            email = #{email}
        </if>
    </trim>
</select>
```

## 4.choose、when、otherwise

相当于Java中的 if……else if……else

> when 至少要有一个，otherwise至多只能有一个

```xml
<!--List<Emp> getEmpByChoose();-->
<select id="getEmpByChoose" resultType="com.MrLi.pojo.Emp">
    select * from t_emp
    <choose>
        <when test="empName == null and empName = ''">
            emp_name = #{empName}
        </when>
        <when test="age == null and age = ''">
            age = #{age}
        </when>
        <when test="sex == null and sex = ''">
            sex = #{sex}
        </when>
        <when test="email == null and email = ''">
            email = #{email}
        </when>
        <otherwise>
            did = 2
        </otherwise>
    </choose>
</select>
```

## 5.foreach

- collection：设置需要循环的数组或集合
- item：表示数组或集合中的每一个数据
- separator：循环体之间的分隔符
- open：foreach标签所循环的所有内容的开始符
- close：foreach标签所循环的所有内容的结束符

通过数组实现批量删除：

```java
//通过数组实现批量删除
int deleteMoreByArray(@Param("eids") Integer[] eids);
```

```xml
<!--int deleteMoreByArray(@Param("eids") Integer[] eids);-->
<delete id="deleteMoreByArray">
    delete from t_emp where eid in 
    <!--
            <foreach collection="eids" item="eid" separator="," open="(" close=")">
                #{eid}
            </foreach>
        -->
    <foreach collection="eids" item="eid" separator="or">
        eid = #{eid}
    </foreach>
</delete>
```

通过list集合实现批量添加功能：

```java
//通过list集合实现批量添加功能
ArrayList<Emp> insertMoreByList(@Param("emps") ArrayList<Emp> emps);
```

```xml
<!--List<Emp> insertMoreByList(List<Emp> emps);-->
<insert id="insertMoreByList">
    insert into t_emp values
    <foreach collection="emps" item="emp" separator=",">
        (null, #{emp.empName}, #{emp.age}, #{emp.sex}, #{emp.email}, null)
    </foreach>
</insert>
```

## 6.sql

```xml
<sql id="empColumns">eid, emp_name, age, sex, email</sql>

<!--List<Emp> getEmpByChoose();-->
<select id="getEmpByChoose" resultType="com.MrLi.pojo.Emp">
    select <include refid="empColumns" /> from t_emp
    <choose>
        <when test="empName == null and empName = ''">
            emp_name = #{empName}
        </when>
        <when test="age == null and age = ''">
            age = #{age}
        </when>
        <when test="sex == null and sex = ''">
            sex = #{sex}
        </when>
        <when test="email == null and email = ''">
            email = #{email}
        </when>
        <otherwise>
            did = 2
        </otherwise>
    </choose>
</select>
```

# 十、MyBatis的缓存

## 1.myBatis的一级缓存（默认开启）

一级缓存时sqlSession级别的，通过同一个sqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，而不会从数据库中重新访问。

使一级缓存失效的四种情况：

- 1）是不同的SQLSession对应不同的一级缓存
- 2）是同一个sqlSession但是查询的条件不同
- 3）同一个sqlSession两次查询期间执行了任何一次增删改查操作
- 4）同一个sqlSession在两次查询的期间手动清空了缓存

## 2.MyBatis的二级缓存（手动开启）

二级缓存是sqlSessionFactory级别，通过同一个sqlSessionFactory创建的sqlSession查询的结果会被缓存；此后若再次执行相同的查询语句，结果就会从缓存中获取。

二级缓存开启的条件：

- 1）在核心配置文件中，设置全局配置属性**cacheEnabled="true"**，默认为true，不需要设置
- 2）在映射文件中设置标签**\<cache/\>**
- 3）二级缓存必须在sqlSession关闭或提交之后有效
- 4）查询的数据所转换的实体类类型必须实现序列化的接口：**Serializable**

使二级缓存失效的情况：

- 两次查询之间执行了任意的增删改，会使一级缓存和二级缓存同时失效

> 手动清空缓存只对一级缓存有效

## 3.二级缓存的相关配置

在mapper配置文件中添加的cache标签可以设置一些属性：

- eviction属性：缓存回收策略
  - LRU（Least Recently Used）最近最少使用的：一处最长时间不被使用的对象。
  - FiFo（First in Fist Out）先进先出：按对象进入缓存的顺序来移除它们。
  - SOFT 软引用：移除基于垃圾回收器状态和软引用规则的对象。
  - WEAK 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。

> 默认是 LRU	

- flushInterval属性：刷新间隔，单位是**毫秒**
  - 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用**增删改**语句时刷新
- size属性：引用数目，其为正整数
  - 代表缓存最多可以储存多少个对象，太大容易导致内存溢出
- readOnly属性：只读，true/false
  - true：只会缓存，会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改，这提供了很重要的性能优势。
  - false：读写缓存，会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是false

## 4.MyBatis缓存查询的顺序

- 先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以直接拿来使用
- 如果二级缓存没有命中，则再查询一级缓存
- 如果一级缓存也没有命中，则查询数据库
- sqlSession关闭之后，一级缓存中的数据会写入二级缓存

## 5.整合第三方缓存EHCache

- 1）添加依赖

  ```xml
  <!--MyBatis EHCache整合包-->
  <dependency>
      <groupId>org.mybatis.caches</groupId>
      <artifactId>mybatis-ehcache</artifactId>
      <version>1.2.1</version>
  </dependency>
  <!--slf4j 日志门面的一个具体实现-->
  <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.2.3</version>
  </dependency>
  ```

- 2)各种jar包功能

|    jar包名称    |              作用              |
| :-------------: | :----------------------------: |
| mybatis-ehcache |    MyBatis和EACache的整合包    |
|     ehcache     |         EACache核心包          |
|    slf4j-api    |        SLF4j日志门面包         |
| logback-classic | 支持SLFj门面接口的一个具体实现 |



- 3）创建EHCache的配置文件**ehcache.xml**

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">
      <!--磁盘保存路径-->
      <diskStore path="D:\Java\Frame\MyBatis\ehcache" />
  
      <defaultCache
                    maxElementsInMemory="1000"
                    maxElementsOnDisk="10000000"
                    eternal="false"
                    overflowToDisk="true"
                    timeToidleSeconds="120"
                    timeToLiveSeconds="120"
                    diskExpiryThreadIntervalSeconds="120"
                    memoryStoreEvictionPolicy="LRU" />
  </ehcache>
  ```

- 4）设置二级缓存的类型

  ```xml
  <cache type="org.mybatis.caches.ehcache.EHcacheCache" />
  ```

- 5）加入logback日志

  存在SLF4j时，作为简易日志的log4j将会失效，此时我们需要借助SLF4j的具体实现logback来打印日志。

  创建logback的配置文件logback.xml：

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <configuration debug="true">
      <!--指定日志输出的位置-->
      <appender name="STDOUT"
                class="ch.qos.logback.core.ConsoleAppender">
          <encoder>
              <!--日志输出格式-->
              <!--按照顺序分别是：时间、日志级别、线程名称、打印日志的类、日志主体内容、换行-->
              <pattern>[%d{HH:mm:ss,SSS}] [%-5level] [%thread] [%logger] [%msg]%n</pattern>
          </encoder>
      </appender>
  
      <!--设置全局日志级别。日志级别按顺序分别是：DEBUG、INFO、WARN、ERROR-->
      <!--指定任何一个日志级别都只打印当前级别和后面级别的日志-->
      <root levle="DEBUG">
          <!--指定打印日志1的appender，这里通过"STDOUT"引用了前面配置的appender-->
          <appender-ref ref="STDOUT" />
      </root>
  
      <!--根据特殊需求指定局部日志级别-->
      <logger name="com.MrLi.mapper" level="DEBUG" />
  </configuration>
  ```

# 十一、MyBatis的逆向工程

- 正向工程：先创建Java实体类，由框架负责根据实体类生成数据库表。Hibernate是支持正向工程的。
- 逆向工程：先创建数据库表，由框架负责根据数据库表，方向生成以下资源：
  - Java实体类
  - Mapper接口
  - Mapper映射文件

## 1.创建逆向工程的步骤

- 1）添加依赖和插件

  ```xml
  <dependencies>
      <!--junit测试包-->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.13.2</version>
          <scope>test</scope>
      </dependency>
      <!--MyBatis核心包-->
      <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.7</version>
      </dependency>
  </dependencies>
  
  <!--控制 Maven 在构建过程中相关的配置-->
  <build>
  	<!--构建过程中会用到的插件-->
      <plugins>
      	<!--具体的插件，逆向工程的操作是以构建过程中插件形式出现的-->
          <plugin>
          	<groupId>org.mybatis.generator</groupId>
              <artifactId>mybatis-generator-maven-plugin</artifactId>
              <version>1.3.0</version>
              
              <!--插件的依赖-->
              <dependencies>
              	<!--逆向工程的核心依赖-->
                  <denpendency>
                  	<groupId>org.mybatis.generator</groupId>
                      <artifactId>mybatis-generator-core</artifactId>
                      <version>1.3.2</version>
                  </denpendency>
                  
                  <!--数据库连接池-->
                  <denpendency>
                  	<groupId>com.mchange</groupId>
                      <artifactId>c3p0</artifactId>
                      <version>0.9.2</version>
                  </denpendency>
                  
                  <!--MySQL驱动-->
                  <denpendency>
                  	<groupId>mysql</groupId>
                      <artifactId>mysql-connector-java</artifactId>
                      <version>5.1.8</version>
                  </denpendency>
              </dependencies>
          </plugin>
      </plugins>
  </build>
  ```

## 2.创建myBatis的核心配置文件

## 3.创建逆向工程核心配置文件

> 文件名必须是：generatorConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
		targetRuntime：执行生成的逆向工程的版本
            Mybatis3Simple：生成基本的CRUD（清新简洁版）
            MyBatis3：生成带条件的CRUD（奢华至尊版）
	-->
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <!--数据库的连接信息-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/mybatis"
                        userId="root"
                        password="123abc" />
        <!--javaBean的生成策略-->
        <javaModelGenerator targetPackage="com.MrLi.pojo" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimString" value="true" />
        </javaModelGenerator>
        <!--SQL映射文件的生成策略-->
        <sqlMapGenerator targetPackage="com.MrLi.mapper" targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>
        <!--mapper接口的生成策略-->
        <javaClientGenerator targetPackage="com.MrLi.mapper" targetProject=".\src\main\java" type="XMLMAPPER">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!--逆向分析的表-->
        <!--tableName设置为 * 号，可以对应所有的表，此时不写 domainObjectName-->
        <!--domainObjectName 属性指定生成出来的实体类的类名-->
        <table tableName="t_emp" domainObjectName="Emp"/>
        <table tableName="t_dept" domainObjectName="Dept"/>
    </context>
</generatorConfiguration>
```

# 十二、分页插件

> 实现分页功能需要的元素有：limit、index、pageSize、pageNum    
>
> - index：当前页的起始索引    
>
> - pageSize：每页显示的条数    
>
> - pageNum：当前页的页码    
>
> - index = (pageNum - 1) * pageSize

## 1.分页插件的使用步骤

- 1）添加依赖

  ```xml
  <!--分页插件的依赖-->
  <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.2.0</version>
  </dependency>
  ```

- 2）配置分页插件

  在MyBatis的核心配置文件中配置插件

  ```xml
  <!--配置分页插件-->
  <plugins>
      <plugin interceptor="com.github.pagehelper.PageInterceptor"/>
  </plugins>
  ```

## 2.分页插件的使用

- 1）需要在查询功能之前开启分页：PageHelper.startPage(int pageNum, int pageSize);

- 2）在查询功能之后获取分页相关信息：PageInfo<Emp> page = new pageInfo<>(list, 5);

  > list --> 表示分页数据
  >
  > 5 --> 表示当前导航分页的数量

## 3.分页插件常用的数据

- pageNum：当前页的页码
- pageSize：每页显示的条数
- size：当前页显示的真实条数
- total：总记录数
- pages：总页数
- prePage：上一页的页码
- nextPage：下一页的页码
- isFirstPage/isLastPage：是否为第一页/最后一页
- hasPreviouspage/hasNextPage：是否存在上一页/下一页
- navigatePages：导航分页的页码数
- navigatePageNums：导航分页的页码，[1,2,3,4,5]