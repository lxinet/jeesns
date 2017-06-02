CREATE TABLE `tbl_weibo_favor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME DEFAULT NULL,
  `weibo_id` INT(11) DEFAULT '0',
  `member_id` INT(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE (`weibo_id`,`member_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;


ALTER TABLE `tbl_archive` ADD favor int(11) default '0' COMMENT '喜欢、点赞';

CREATE TABLE `tbl_archive_favor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME DEFAULT NULL,
  `archive_id` INT(11) DEFAULT '0',
  `member_id` INT(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE (`archive_id`,`member_id`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;


ALTER TABLE `tbl_group_topic` ADD is_top int(11) DEFAULT '0' COMMENT '置顶，0不置顶，1置顶，2超级置顶';
ALTER TABLE `tbl_group_topic` ADD is_essence int(11) DEFAULT '0' COMMENT '精华，0不加精，1加精';

CREATE TABLE `tbl_action` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME DEFAULT NULL,
  `name` VARCHAR(50),
  `log` VARCHAR(255),
  `status` INT(11) DEFAULT '0' COMMENT '状态，0正常，1禁用',
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`name`)
)ENGINE = InnoDb DEFAULT CHARSET = utf8;

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

CREATE TABLE `tbl_action_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME DEFAULT NULL,
  `member_id` INT(11),
  `action_id` INT(11),
  `remark` VARCHAR(1000),
  `type` TINYINT(2) DEFAULT '0',
  `foreign_id` INT(11) DEFAULT '0',
  `action_ip` VARCHAR(15),
  PRIMARY KEY (`id`)
)ENGINE = InnoDb DEFAULT CHARSET = utf8;


CREATE TABLE `tbl_member_fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` DATETIME DEFAULT NULL,
  `follow_who` INT(11) DEFAULT '0',
  `who_follow` INT(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE (`follow_who`,`who_follow`)
) ENGINE=InnoDb DEFAULT CHARSET=utf8;


