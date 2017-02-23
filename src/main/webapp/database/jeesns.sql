##开始创建表
CREATE TABLE `tbl_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `log` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '状态，0正常，1禁用',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_action_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `type` tinyint(2) DEFAULT '0',
  `foreign_id` int(11) DEFAULT '0',
  `action_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_archive` (
  `archive_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_type` int(11) DEFAULT '0' COMMENT '发布类型，1是普通文章，2是群组文章',
  `title` varchar(255) DEFAULT NULL COMMENT '文档标题',
  `member_id` int(11) DEFAULT NULL COMMENT '会员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述说明',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键词',
  `view_rank` int(11) DEFAULT '0' COMMENT '浏览权限，0不限制，1会员',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `writer` varchar(30) DEFAULT '' COMMENT '作者',
  `source` varchar(30) DEFAULT '' COMMENT '来源',
  `pub_time` datetime DEFAULT NULL COMMENT '发布日期',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `last_reply` datetime DEFAULT NULL COMMENT '最后回复时间',
  `can_reply` int(1) DEFAULT '0' COMMENT '是否可以回复，0可以回复，1不可以回复',
  `good_num` int(11) DEFAULT '0' COMMENT '点赞数量',
  `bad_num` int(11) DEFAULT '0' COMMENT '踩数量',
  `check_admin` int(11) DEFAULT '0' COMMENT '审核管理员id',
  `content` text COMMENT '内容',
  `favor` int(11) DEFAULT '0' COMMENT '喜欢、点赞',
  PRIMARY KEY (`archive_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_archive_favor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `archive_id` int(11) DEFAULT '0',
  `member_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `archive_id` (`archive_id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `tbl_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_time` datetime DEFAULT NULL,
  `cate_id` int(11) DEFAULT NULL COMMENT '栏目ID',
  `archive_id` int(11) DEFAULT NULL COMMENT '文章ID',
  `status` int(11) DEFAULT '0' COMMENT '状态，0未审核，1已审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_article_cate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT '0' COMMENT '上级类目ID，顶级栏目为0',
  `name` varchar(30) DEFAULT NULL COMMENT '栏目名称',
  `status` int(1) DEFAULT '0' COMMENT '0正常，1隐藏',
  `sort` int(11) DEFAULT '50' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_config` (
  `jkey` varchar(100) NOT NULL DEFAULT '',
  `jvalue` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `tbl_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '群组名字',
  `logo` varchar(255) DEFAULT NULL COMMENT '群组logo',
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  `managers` varchar(200) DEFAULT NULL COMMENT '管理员',
  `tags` varchar(100) DEFAULT NULL COMMENT '标签',
  `introduce` varchar(255) DEFAULT NULL COMMENT '介绍',
  `can_post` int(11) DEFAULT '0' COMMENT '是否能发帖，0不可以，1可以',
  `topic_review` int(11) DEFAULT '0' COMMENT '帖子是否需要审核，0不需要，1需要',
  `status` int(11) DEFAULT '0' COMMENT '0未审核，1已审核，-1审核不通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_group_fans` (
  `create_time` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_group_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_time` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `archive_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '状态，0未审核，1已审核',
  `is_essence` int(11) DEFAULT '0' COMMENT '精华，0不加精，1加精',
  `is_top` int(11) DEFAULT '0' COMMENT '置顶，0不置顶，1置顶，2超级置顶',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_group_topic_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `group_topic_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0' COMMENT '分组ID',
  `name` varchar(50) DEFAULT NULL COMMENT '会员名称',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `password` varchar(32) DEFAULT '' COMMENT '密码',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `regip` varchar(15) DEFAULT '' COMMENT '注册IP',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `curr_login_time` datetime DEFAULT NULL COMMENT '本次登录时间',
  `curr_login_ip` varchar(15) DEFAULT NULL COMMENT '本次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '上次登录IP',
  `update_time` datetime DEFAULT NULL COMMENT '更新资料时间',
  `money` double(11,2) DEFAULT '0.00' COMMENT '金额',
  `score` int(11) DEFAULT '0' COMMENT '积分',
  `is_active` int(1) DEFAULT '0' COMMENT '是否已激活，0未激活，1已激活',
  `status` int(2) DEFAULT '0' COMMENT '-1禁用，0启用',
  `birthday` varchar(10) DEFAULT NULL COMMENT '生日',
  `addprovince` varchar(20) DEFAULT '' COMMENT '居住省份',
  `addcity` varchar(20) DEFAULT '' COMMENT '居住城市',
  `addarea` varchar(20) DEFAULT '' COMMENT '居住地区',
  `address` varchar(50) DEFAULT '' COMMENT '居住地址',
  `qq` varchar(15) DEFAULT '' COMMENT 'QQ',
  `wechat` varchar(20) DEFAULT '' COMMENT '微信',
  `contact_phone` varchar(11) DEFAULT '' COMMENT '联系手机号',
  `contact_email` varchar(32) DEFAULT '' COMMENT '联系邮箱',
  `website` varchar(50) DEFAULT '' COMMENT '个人网站',
  `introduce` varchar(255) DEFAULT '' COMMENT '个人介绍',
  `is_admin` int(11) DEFAULT '0' COMMENT '是否管理员，0不是，1是',
  `follows` INT(11) DEFAULT '0' comment '关注会员数量',
  `fans` INT(11) DEFAULT '0' comment '粉丝数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_memgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isadmin` int(1) DEFAULT '0' COMMENT '是否是管理组，0不是，1是',
  `name` varchar(50) DEFAULT '' COMMENT '分组名称',
  `fid` int(11) DEFAULT '0' COMMENT '上级分组ID，默认0，0是顶级分组',
  `rankid` int(11) DEFAULT '0' COMMENT '权限ID，0-99是会员权限，100以上是管理员权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_validate_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `email` varchar(32) NOT NULL DEFAULT '',
  `code` varchar(50) NOT NULL DEFAULT '',
  `status` int(1) NOT NULL DEFAULT '0',
  `type` int(1) DEFAULT '0' COMMENT '1是重置密码，2会员激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_weibo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `member_id` int(11) NOT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0为普通文本,1为图片',
  `content` varchar(1000) DEFAULT NULL,
  `favor` int(11) DEFAULT '0' COMMENT '赞',
  `status` tinyint(11) DEFAULT '0' COMMENT '0未审核，1已审核，-1审核不通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tbl_weibo_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `weibo_id` int(11) NOT NULL DEFAULT '0',
  `comment_id` int(11) DEFAULT '0' COMMENT '评论的id',
  `content` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0正常，1禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_weibo_favor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `weibo_id` int(11) DEFAULT '0',
  `member_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `weibo_id` (`weibo_id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




##数据
INSERT INTO `tbl_config` (`jkey`, `jvalue`, `description`)
VALUES
  ('cms_post','1','cms会员文章投稿，0关闭，1开启'),
  ('cms_post_review','0','cms投稿审核，0需要审核，1不需要审核'),
  ('group_alias','群组','群组别名'),
  ('group_apply','1','群组是否可以申请，0不可以，1可以'),
  ('group_apply_review','0','群组申请是否需要审核，0需要审核，1不需要审核'),
  ('member_email_valid','0','邮箱验证，0不需要验证，1需要验证'),
  ('member_login_open','1','会员登录开关，0关闭，1开启'),
  ('member_register_open','1','会员注册开关，0关闭，1开启'),
  ('site_description','JEESNS是一款基于JAVA企业级平台研发的社交管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源SNS先河。','网站描述'),
  ('site_domain','http://www.jeesns.cn/','网站域名'),
  ('site_keys','jeesns,sns,java','网站关键词'),
  ('site_logo','/res/common/images/lxinetlogo.png','网站LOGO'),
  ('site_name','JEESNS','网站名称'),
  ('site_send_email_account','','发送邮箱账号'),
  ('site_send_email_password','','发送邮箱密码'),
  ('site_send_email_smtp','','发送邮箱SMTP服务器地址'),
  ('site_seo_title','又一个JEESNS社区','SEO标题'),
  ('weibo_alias','微博','微博别名'),
  ('weibo_post','1','微博发布，0不可以发布，1可以发布'),
  ('weibo_post_maxcontent','140','微博内容字数');


INSERT INTO `tbl_member` (`id`, `group_id`, `name`, `email`, `phone`, `password`, `sex`, `avatar`, `create_time`, `regip`, `login_count`, `curr_login_time`, `curr_login_ip`, `last_login_time`, `last_login_ip`, `update_time`, `money`, `score`, `is_active`, `status`, `birthday`, `addprovince`, `addcity`, `addarea`, `address`, `qq`, `wechat`, `contact_phone`, `contact_email`, `website`, `introduce`, `is_admin`)
VALUES
  (1,0,'admin','admin@jeesns.cn','13800138000','56b0436e6dd61a1f5f6a636cdf790eee','女','/res/common/images/default-avatar.png',now(),'',0,now(),'127.0.0.1',now(),'127.0.0.1',NULL,0.00,0,1,0,'1971-12-20','','','','','8888888','admin','13800138000','admin@jeesns.cn','www.jeesns.cn','',1);


INSERT INTO tbl_action(id, create_time, name, log, status, update_time) VALUES
  (1,now(),'会员注册','注册了账号',0,now()),
  (2,now(),'会员登录','登录了账号',0,now()),
  (3,now(),'修改密码','修改了密码',0,now()),
  (4,now(),'找回密码','找回了密码',0,now()),
  (5,now(),'登录失败','登录失败',0,now()),
  (3001,now(),'删除微博','删除了微博',0,now()),
  (3002,now(),'删除微博评论','删除了微博评论',0,now()),
  (3003,now(),'删除群组','删除了群组',0,now()),
  (3004,now(),'删除群组帖子','删除了帖子',0,now()),
  (3005,now(),'删除群组帖子评论','删除了帖子评论',0,now()),
  (3006,now(),'删除文章','删除文章',0,now()),
  (3007,now(),'删除文章评论','删除了文章评论',0,now()),
  (10001,now(),'发布微博','发布了微博',0,now()),
  (10002,now(),'群组发帖','发布了群组帖子',0,now()),
  (10003,now(),'发布文章','发布了文章',0,now());
