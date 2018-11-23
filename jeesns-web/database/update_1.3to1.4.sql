CREATE TABLE `tbl_checkin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `member_id` INT(11),
  `continue_day` INT(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_weibo_topic` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255),
  `count` INT(11) DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `tbl_weibo` ADD COLUMN `topic_id` INT DEFAULT '0';


CREATE TABLE `tbl_member_level` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `tbl_member` ADD COLUMN `member_level_id` INT DEFAULT '1';
ALTER TABLE `tbl_member` ADD COLUMN `is_vip` INT DEFAULT '0';

INSERT INTO tbl_member_level(id, create_time, name) VALUES (1,now(),'普通会员');


ALTER TABLE `tbl_checkin` ADD CONSTRAINT `fk_checkin_member` FOREIGN KEY (`member_id`) REFERENCES `tbl_member` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

INSERT INTO tbl_action(id, create_time, name, log, status, update_time) VALUES (10004,now(),'签到','签到',0,now());

INSERT INTO tbl_score_rule(id,create_time,update_time,name,score,remark,type,status) VALUES (14,now(),now(),'签到',1,'签到奖励','day',1);


ALTER TABLE  `tbl_article` add (
  `title` varchar(255) NULL DEFAULT NULL COMMENT '文档标题',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) NULL DEFAULT NULL COMMENT '描述说明',
  `keywords` varchar(100) NULL DEFAULT NULL COMMENT '关键词',
  `view_rank` int(11) NULL DEFAULT 0 COMMENT '浏览权限，0不限制，1会员',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `writer` varchar(30) NULL DEFAULT '' COMMENT '作者',
  `source` varchar(30) NULL DEFAULT '' COMMENT '来源',
  `pub_time` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `thumbnail` varchar(255) NULL DEFAULT NULL COMMENT '缩略图',
  `last_reply` datetime(0) NULL DEFAULT NULL COMMENT '最后回复时间',
  `can_reply` int(1) NULL DEFAULT 0 COMMENT '是否可以回复，0可以回复，1不可以回复',
  `good_num` int(11) NULL DEFAULT 0 COMMENT '点赞数量',
  `bad_num` int(11) NULL DEFAULT 0 COMMENT '踩数量',
  `check_admin` int(11) NULL DEFAULT 0 COMMENT '审核管理员id',
  `content` text NULL COMMENT '内容',
  `favor` int(11) NULL DEFAULT 0 COMMENT '喜欢、点赞'
);

ALTER TABLE  `tbl_group_topic` add (
  `title` varchar(255) NULL DEFAULT NULL COMMENT '文档标题',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) NULL DEFAULT NULL COMMENT '描述说明',
  `keywords` varchar(100) NULL DEFAULT NULL COMMENT '关键词',
  `view_rank` int(11) NULL DEFAULT 0 COMMENT '浏览权限，0不限制，1会员',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `writer` varchar(30) NULL DEFAULT '' COMMENT '作者',
  `source` varchar(30) NULL DEFAULT '' COMMENT '来源',
  `pub_time` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  `thumbnail` varchar(255) NULL DEFAULT NULL COMMENT '缩略图',
  `last_reply` datetime(0) NULL DEFAULT NULL COMMENT '最后回复时间',
  `can_reply` int(1) NULL DEFAULT 0 COMMENT '是否可以回复，0可以回复，1不可以回复',
  `good_num` int(11) NULL DEFAULT 0 COMMENT '点赞数量',
  `bad_num` int(11) NULL DEFAULT 0 COMMENT '踩数量',
  `check_admin` int(11) NULL DEFAULT 0 COMMENT '审核管理员id',
  `content` text NULL COMMENT '内容',
  `favor` int(11) NULL DEFAULT 0 COMMENT '喜欢、点赞'
);

update tbl_article as a set
title=(select title from tbl_archive where archive_id = a.archive_id),
member_id=(select member_id from tbl_archive where archive_id = a.archive_id),
create_time=(select create_time from tbl_archive where archive_id = a.archive_id),
description=(select description from tbl_archive where archive_id = a.archive_id),
keywords=(select keywords from tbl_archive where archive_id = a.archive_id),
view_rank=(select view_rank from tbl_archive where archive_id = a.archive_id),
view_count=(select view_count from tbl_archive where archive_id = a.archive_id),
writer=(select writer from tbl_archive where archive_id = a.archive_id),
source=(select source from tbl_archive where archive_id = a.archive_id),
pub_time=(select pub_time from tbl_archive where archive_id = a.archive_id),
update_time=(select update_time from tbl_archive where archive_id = a.archive_id),
thumbnail=(select thumbnail from tbl_archive where archive_id = a.archive_id),
last_reply=(select last_reply from tbl_archive where archive_id = a.archive_id),
can_reply=(select can_reply from tbl_archive where archive_id = a.archive_id),
good_num=(select good_num from tbl_archive where archive_id = a.archive_id),
bad_num=(select bad_num from tbl_archive where archive_id = a.archive_id),
check_admin=(select check_admin from tbl_archive where archive_id = a.archive_id),
content=(select content from tbl_archive where archive_id = a.archive_id),
favor=(select favor from tbl_archive where archive_id = a.archive_id);

update tbl_group_topic as a set
title=(select title from tbl_archive where archive_id = a.archive_id),
member_id=(select member_id from tbl_archive where archive_id = a.archive_id),
create_time=(select create_time from tbl_archive where archive_id = a.archive_id),
description=(select description from tbl_archive where archive_id = a.archive_id),
keywords=(select keywords from tbl_archive where archive_id = a.archive_id),
view_rank=(select view_rank from tbl_archive where archive_id = a.archive_id),
view_count=(select view_count from tbl_archive where archive_id = a.archive_id),
writer=(select writer from tbl_archive where archive_id = a.archive_id),
source=(select source from tbl_archive where archive_id = a.archive_id),
pub_time=(select pub_time from tbl_archive where archive_id = a.archive_id),
update_time=(select update_time from tbl_archive where archive_id = a.archive_id),
thumbnail=(select thumbnail from tbl_archive where archive_id = a.archive_id),
last_reply=(select last_reply from tbl_archive where archive_id = a.archive_id),
can_reply=(select can_reply from tbl_archive where archive_id = a.archive_id),
good_num=(select good_num from tbl_archive where archive_id = a.archive_id),
bad_num=(select bad_num from tbl_archive where archive_id = a.archive_id),
check_admin=(select check_admin from tbl_archive where archive_id = a.archive_id),
content=(select content from tbl_archive where archive_id = a.archive_id),
favor=(select favor from tbl_archive where archive_id = a.archive_id);

CREATE TABLE `tbl_article_favor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `article_id` int(11) NULL DEFAULT 0,
  `member_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_article_id_member_id`(`article_id`, `member_id`),
  CONSTRAINT `fk_article_favor_article` FOREIGN KEY (`article_id`) REFERENCES `tbl_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_article_favor_member` FOREIGN KEY (`member_id`) REFERENCES `tbl_member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_group_topic_favor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `group_topic_id` int(11) NULL DEFAULT 0,
  `member_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_group_topic_id_member_id`(`group_topic_id`, `member_id`),
  CONSTRAINT `fk_group_topic_favor_group_topic` FOREIGN KEY (`group_topic_id`) REFERENCES `tbl_group_topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_group_topic_favor_member` FOREIGN KEY (`member_id`) REFERENCES `tbl_member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table `tbl_picture` CHANGE `picture_id` `id` int(11) NOT NULL AUTO_INCREMENT;
