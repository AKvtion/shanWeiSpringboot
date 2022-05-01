# SringBoot+Vue脚手架

**项目描述**

新闻管理系统，前后端分离。

**运行环境**

jdk8+tomcat9+mysql+IntelliJ IDEA+maven

**项目技术(必填)**

SpringBoot+mybatisPlus+vuejs




## 介绍

这是一个极其简单的前后端分离项目脚手架

前端：Vue，Vue-Router，Vuex，Axios，ElementUI

后台：SpringBoot，Mybatis-Plus（不用写sql）

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



## 注意
> 1. 数据库的字段设计要以下划线分割，而不是驼峰，例如 可以使用 user_id 而不要使用 userId，字段设计的名称一定要规范
> 2. 不要使用单个字母作为分割的一部分，例如：不要使用 u_id，而推荐你使用user_id
> 3. 数据库多表关联查询推荐使用单独的VO来做扩展，尽量不要改动数据库映射的entity，比如你可以加一个类UserVO来扩充一些字段，而不是在User实体里面扩展
> 4. 如果非要在User实体里面扩展，你需要加上 @TableField(exist=false) 来表示这个字段在数据库不存在，否则会报错
> 5. 日期字段的格式化，需要使用  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") 注解来做日期解析，可以参考Book实体里面的createTime字段
