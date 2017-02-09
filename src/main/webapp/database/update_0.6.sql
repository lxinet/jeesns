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
