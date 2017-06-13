
ALTER TABLE `tbl_member` ADD follows INT(11) DEFAULT '0' comment '关注会员数量';
ALTER TABLE `tbl_member` ADD fans INT(11) DEFAULT '0' comment '粉丝数量';
UPDATE `tbl_member` set follows=0;
UPDATE `tbl_member` set fans=0;


CREATE TABLE `tbl_picture` (
  `picture_id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `type` INT(11) NOT NULL COMMENT '1是文章图片，2是群组帖子图片，3是微博图片',
  `path` VARCHAR(255) NOT NULL COMMENT '图片路径',
  `narrow_path` VARCHAR(255) COMMENT '缩小的图片路径',
  `md5` VARCHAR(32) NOT NULL,
  `width` INT(11) DEFAULT '0',
  `height` INT(11) DEFAULT '0',
  PRIMARY KEY (picture_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tbl_message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `from_member_id` INT(11) DEFAULT '0',
  `to_member_id` INT(11) DEFAULT '0',
  `content` TEXT,
  `url` VARCHAR(255),
  `isread` INT(1) DEFAULT '0' COMMENT '是否已读，0未读，1已读',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

