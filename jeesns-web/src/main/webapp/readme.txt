JEESNS
开发语言：JAVA
数据库：MYSQL
JAVA开发框架：Spring MVC+Spring+Mybatis
前台前端开发框架：ZUI+JQuery+Bootstrap
前台模板引擎：Freemarker

简介
JEESNS是一款基于JAVA企业级平台研发的社交管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源SNS先河，JEESNS可以用来搭建门户、论坛、社区、微博、问答、知识付费平台等。
 交流社区：http://www.jeesns.cn
 官方网址：http://www.lxinet.com
 github(源代码下载)：https://github.com/zchuanzhao/jeesns



应用场景
    JEESNS是一个企业级的开源社区系统，是一个可以用来搭建门户、群组、论坛、微博和问答等知识付费社区平台系统。
    JEESNS是将SNS社会化网络元素，人和群组结合在一起的新型的社交系统。
    JEESNS以人为中心，通过用户的需求和行为将最有价值的信息得以不断整合。
    JEESNS是一个稳定、安全、可扩展的社区系统，可以帮您搭建与众不同的交流社区。
    如果您要需要搭建一个论坛,那么您可以用JEESNS
    如果您需要一个群组，那么您可以用JEESNS
    如果您想做一个类似于知乎的知识问答平台，那么您可以用JEESNS
    如果您想做一个知识付费平台，那么您可以用JEESNS
    如果您需要因为某个话题来汇聚人群，那么您可以用JEESNS

私信功能
    界面模仿PC版微信
    可以查看私聊过的联系人
    聊天界面自动刷新
个人主页
    关注会员
    私信会员
    查看动态
    查看粉丝、关注、微博、文章、帖子、群组
    财务明细
    积分明细
    卡密充值
    VIP会员等级
微博模块
    支持图片类型的微博
    多图画廊展示
    支持添加Emoji标签
    点赞功能
    微博话题
群组模块
    可以关注群组
    支持上传群组logo
    支持发帖审核开关
    授权管理员
    帖子喜欢功能
    帖子加精、置顶
    付费加入群组
文章模块
    文章喜欢功能
    文章投稿功能开关
    文章审核功能开关
    文章评论
问答模块
    问答悬赏
    问答回答
动态模块
    洞悉一切

环境要求
 JDK8或更高版本
 Tomcat8.0或更高版本
 MySQL5.1或更高版本

搭建步骤
 1、将重新上传到支持JAVA的空间或者服务器上；
 2、导入数据库，将/database/jeesns.sql导入到mysql数据库中；
 3、修改数据库信息，修改文件：WEB-INF\classes\jeesns.properties
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://数据库服务器IP(本地直接用localhost):端口号(默认3306)/数据库名?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull
    jdbc.user=数据库用户名
    jdbc.password=数据库密码
    #后台管理目录
    managePath=manage

 4、重启Tomcat。
  后台登录地址：http://域名/manage/
  后台用户名：admin
  密码：jeesns