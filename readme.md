# springmvc入门学习

## helloWorld

springmvc  demo 工程

### @RequestMapping注解

#### 注解的位置

标识一个类：设置映射请求的请求路径的初始信息

标识一个方法：设置映射请求请求路径的具体信息

#### value属性

value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求

#### method属性

method属性通过请求的请求方式（get或post）匹配请求映射， 没有设置method属性，则get和post请求默认都可以访问 



SpringMVC中提供了@RequestMapping的派生注解

处理get请求的映射-->@GetMapping

处理post请求的映射-->@PostMapping

处理put请求的映射-->@PutMapping

处理delete请求的映射-->@DeleteMapping



常用的请求方式有get，post，put，delete

#### params属性

params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系

"param"：要求请求映射所匹配的请求必须携带param请求参数

"!param"：要求请求映射所匹配的请求必须不能携带param请求参数

"param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value

"param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value



### 获取请求参数

#### 通过ServletAPI获取

将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象

#### 通过控制器方法的形参获取

在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参

#### @RequestParam

@RequestParam是将请求参数和控制器方法的形参创建映射关系

@RequestParam注解一共有三个属性：

value：指定为形参赋值的请求参数的参数名

required：设置是否必须传输此请求参数，默认值为true

若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；若设置为false，则当前请求不是必须传输value所指定的 请求参数，若没有传输，则注解所标识的形参的值为null

defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值

```java
    @GetMapping(name = "/")
    @ResponseBody
    public String getMapping(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "id", defaultValue = "0") Long id) {

        logger.warn("get Mapping " + id);
        return "getMapping " + id;
    }

```

#### @RequestHeader

@RequestHeader是将请求头信息和控制器方法的形参创建映射关系

@RequestHeader注解一共有三个属性：value、required、defaultValue，用法同@RequestParam

```java
@GetMapping(name = "/")
@ResponseBody
public String getMapping(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "id", defaultValue = "0") Long id,
                         @RequestHeader(value = "cookie", defaultValue = "0") 
                         String cookie) {

    logger.warn(MessageFormat.format("get Mapping id: {0} cookie: {1}", id.toString(), cookie));
    return "getMapping " + id;
}
```

#### @CookieValue

##### @RequestHeader 和 @CookieValue 的关系

@RequestHeader可以将 Request header （请求头）的key-value键值对的value，绑定到controller方法的参数，

@CookieValue 只能将 Request header中关于cookie的值绑定到方法的参数上 ，

即，@RequestHeader 可以传递 key为 cookie ，value为cookie连续键值对，而@CookieValue传递 cookie连续键值对（请求头中key为cookie的 value）



### 解决获取请求参数的乱码问题

必须在web.xml中进行注册

```xml
<!--配置springMVC的编码过滤器-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

SpringMVC中处理编码的过滤器一定要配置到其他过滤器之前，否则无效



### 遇到的问题

#### 启动工程后，页面放回404

springmvc配置文件中，没有配置了对@Controller标签的支持

[springmvc不进入Controller导致404](https://blog.csdn.net/qq_36769100/article/details/71746449)



#### Log4j和Log4j2的区别

 log4j2和log4j是一个作者，只不过log4j2是重新架构的一款日志组件，他抛弃了之前log4j的不足，以及吸取了优秀的logback的设计重新推出的一款新组件。

[Log4j和Log4j2的区别](https://www.cnblogs.com/KylinBlog/p/7841217.html)