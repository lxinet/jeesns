# JEESNS
开发语言：JAVA

数据库：MYSQL

开发框架：Spring MVC+Spring+Mybatis

模板引擎：Freemarker

## 简介
JEESNS是一款基于JAVA企业级平台研发的社交管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源SNS先河。数据库使用MYSQL，全部源代码开放，官方网址：[http://www.jeesns.cn](http://www.jeesns.cn/)。

## 应用场景
- JEESNS是一个企业级的开源社区系统，是一个可以用来搭建门户、群组、论坛和微博的社区系统。
- JEESNS是将SNS社会化网络元素，人和群组结合在一起的新型的社交系统。
- JEESNS以人为中心，通过用户的需求和行为将最有价值的信息得以不断整合。
- JEESNS是一个稳定、安全、可扩展的社区系统，可以帮您搭建与众不同的交流社区。
- 如果您要需要搭建一个论坛,那么您可以用JEESNS
- 如果您需要一个群组，那么您可以用JEESNS
- 如果您需要因为某个话题来汇聚人群，那么您可以用JEESNS

## 环境要求

- JDK7或更高版本
- Tomcat7.0或更高版本
- MySQL5.1或更高版本

## 搭建步骤

1. 创建数据库。如使用MySQL，字符集选择为`utf8`或者`utf8mb4`（支持更多特殊字符，推荐）。
2. 执行数据库脚本。数据库脚本在`/src/main/webapp/database`目录下。
3. 在eclipse中导入maven项目。点击eclipse菜单`File` - `Import`，选择`Maven` - `Existing Maven Projects`。创建好maven项目后，会开始从maven服务器下载第三方jar包（如spring等），需要一定时间，请耐心等待。
4. 修改数据库连接。打开`/src/main/resources/jeesns.propertis`文件，根据实际情况修改`jdbc.url`、`jdbc.user`、`jdbc.password`的值，修改后台路径：`managePath`，如：`managePath=manage`
5. 运行程序。在eclipse中，右键点击项目名，选择`Run as` - `Maven build...`，`Goals`填入`tomcat7:run`，然后点击`Run`。
6. 访问系统。前台地址：[http://localhost:8080/](http://localhost:8080/)；后台登录地址：[http://localhost:8080/manage/login](http://localhost:8080/manage/login)，用户名：admin，密码：jeesns。

## 相关网站
官方网站：[http://www.jeesns.cn](http://www.jeesns.cn/)

技术支持：[http://www.lxinet.com](http://www.lxinet.com/)

服务器支持：[http://www.919dns.com](http://www.919dns.com/)
