
CREATE TABLE `tbl_ads` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `type` INT(11) NOT NULL COMMENT '1是图片链接，2是文字链接，3是代码',
  `name` VARCHAR(100) COMMENT '广告名称',
  `start_time` datetime ,
  `end_time` datetime ,
  `content` VARCHAR(1000) NOT NULL COMMENT '内容，如果是图片链接，该内容为图片地址，如果是文字链接，改内容是文字描述信息，如果是代码，改内容是广告代码',
  `link` VARCHAR(255) COMMENT '链接，图片链接和文字链接类型时才有效',
  `status` INT(1) DEFAULT '0' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `tbl_group_topic_comment` ADD COLUMN `comment_id` INT;

CREATE TABLE `tbl_link` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` VARCHAR(100) COMMENT '网站名称',
  `url` VARCHAR(255) COMMENT '网址',
  `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序，越大越靠前',
  `recomment` INT(11) NOT NULL DEFAULT '0' COMMENT '推荐，0不推荐，1推荐',
  `status` INT(1) DEFAULT '0' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `tbl_picture` ADD COLUMN `member_id` INT;
ALTER TABLE `tbl_picture` ADD COLUMN `description` text;
ALTER TABLE `tbl_message` ADD COLUMN `app_tag` INT;
ALTER TABLE `tbl_message` ADD COLUMN `type` INT;
ALTER TABLE `tbl_message` ADD COLUMN `relate_key_id` INT;
ALTER TABLE `tbl_message` ADD COLUMN `member_id` INT;
ALTER TABLE `tbl_message` ADD CONSTRAINT `fk_message_member` FOREIGN KEY (`member_id`) REFERENCES `tbl_member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `tbl_message` ADD COLUMN `description` VARCHAR(500);
