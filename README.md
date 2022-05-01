# SringBoot+Vue脚手架
### 简介
本网站采用了Vue+SpringBoot进行开发，前台页面用Vue进行数据渲染，组件丰富完善，界面美观简洁；后台采用SpringBoot+MyBatisPlus 相关框架结合开发；开发环境为Apache服务器。

该项目为前后端分离项目的前端部分，前端项目`s`w地址：[传送门](https://github.com/AKvtion/shanWeiVue)

**项目描述**

新闻管理系统，前后端分离。

### 运行环境

jdk8+tomcat9+mysql+IntelliJ IDEA+maven

**项目技术(必填)**

SpringBoot+mybatisPlus+vuejs



### 软件架构

前端：Vue，Vue-Router，Vuex，Axios，ElementUI

后台：SpringBoot，Mybatis-Plus

## 如何使用

后台：maven导入springboot工程，然后配置数据库

后台springboot启动，然后启动tomcat

http://localhost:8085/

启动前端后台vue工程

http://localhost:9876/

前台：进入vue文件夹，执行命令：
```
npm install
```

```
npm run serve
```
即可启动前台vue工程

http://localhost:8080/



## 功能清单

- 登录注册
- 单表增删改查（包括分页模糊查询）
- 登录验证码
- 在线留言
- 个人头像
- 整合Echarts图表  

### 项目演示

https://www.bilibili.com/video/BV1AQ4y1Y7dt?share_source=copy_web

### 功能结构图
![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651377409(1).jpg)


### 运行结果图

![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651376652(1).jpg)

![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651376641(1).jpg)

![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651376584(1).jpg)

![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651376566(1).jpg)

![image-20220322212446660](https://raw.githubusercontent.com/AKvtion/shanWeiSpringboot/main/files/1651376673(1).jpg)


### 工具简介

| 工具    | 说明              |
| ------- | ----------------- |
| VSCode  | 前端开发工具      |
| IDEA    | 开发IDE           |
| Navicat | 数据库连接工具   |
| Typora  | Markdown编辑器    |
| Postman | API接口调试工具    |
| X-shell | Linux远程连接工具 |

### 开发环境

| 工具   | 版本号 |
| ------ | ------ |
| JDK    |   1.8  |
| Mysql  |   5.6  |
| Nginx  | 1.20.0 |
| Tomcat |   8.5  |
| node.js | 14.16.0|



## 注意
> 1. 数据库的字段设计要以下划线分割，而不是驼峰，例如 可以使用 user_id 而不要使用 userId，字段设计的名称一定要规范
> 2. 不要使用单个字母作为分割的一部分，例如：不要使用 u_id，而推荐你使用user_id
> 3. 数据库多表关联查询推荐使用单独的VO来做扩展，尽量不要改动数据库映射的entity，比如你可以加一个类UserVO来扩充一些字段，而不是在User实体里面扩展
> 4. 如果非要在User实体里面扩展，你需要加上 @TableField(exist=false) 来表示这个字段在数据库不存在，否则会报错
> 5. 日期字段的格式化，需要使用  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") 注解来做日期解析，可以参考Book实体里面的createTime字段


### 系统实施

部署实施
	为了使师生方便浏览学校新闻，最好部署在阿里云服务器上。首先，准备好以下工具：

- 阿里服务器1核 2 GiB
- Linux发行版 CentOS  7.9 64位
- Tomcat9.5
- Jdk1.8
- Apache Web服务器
- 阿里RDS数据库


在前后台项目部署到阿里云服务器上，下面是相关的解决方法:

linux Web server failed to start. Port 9090 was already in use.
netstat -lnp|grep 9090


使用如下命令杀掉占用端口的进程

```java
Kill -9 4324
```

杀掉进程图
	Nginx的405 not allowed错误解决办法：
静态文件采用的是post方法，nginx是不允许post访问静态资源，修改错误界面指向error_page 405 =200 @405;

Apache、IIS、Nginx等绝大多数web服务器，都不允许静态文件响应POST请求，否则会返回“HTTP/1.1 405 Method not allowed”错误。

```java
error_page  405 =200 @405;
location @405 {
    proxy_method GET;
    proxy_pass http://ivolcano.top:9090;}
proxy_method ： GET 将 405 报错的 method 改为 GET
proxy_pass： http://localhost:8090 转发域名
```

apache响应404：
资源找不到，在本地测试的就可以访问服务器后端的接口，证明后端的接口正常，检查apache的配置文件，资源路径。
apache反向代理502 Proxy Error：
检查报文，响应内容，有可能服务器的ip字符输入错误。

vue项目部署服务器跨域处理（Apache）：
vue项目上线后，apache反向代理配置跨域，配置https反向代理地址，vue项目上线请求api接口为例，网站和网站之间请求数据是浏览器所不允许的，浏览器会认为这是不安全的行为，所以这就涉及到跨域。
```java
<Directory "/www/server/apache/cgi-bin">
    AllowOverride All
    Options None
    Require all granted
</Directory>
Proxyrequests off
<Proxy http://ivolcano.top:80>
         Order deny,allow
         Allow from all
</Proxy>
ProxyPass /api http://ivolcano.top:9090/
ProxyPassReverse /api http://ivolcano.top:9090/
```
