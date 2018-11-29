CREATE TABLE `tbl_cardkey` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `member_id` INT(11),
  `no` varchar(32),
  `money` double(11,2),
  `status` INT(11) default '0',
  `expire_time` datetime,
  `use_time` datetime,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_payment_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(32),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tbl_payment_type(id, create_time, name) values
(1,now(), '余额支付'),
(2,now(), '卡密支付'),
(3,now(), '支付宝支付'),
(4,now(), '微信支付');


CREATE TABLE `tbl_financial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT '0' COMMENT '类型，0收入，1支出',
  `member_id` int(11) NULL DEFAULT NULL,
  `balance` double(11, 2) NULL DEFAULT NULL,
  `money` double(11, 2) NULL DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `foreign_id` INT(11)  DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `tbl_group` ADD COLUMN `follow_pay` INT DEFAULT '0' COMMENT '付费加入，0免费，1收费';
ALTER TABLE `tbl_group` ADD COLUMN `pay_money` double(11,2) DEFAULT '0' COMMENT '付费加入金额';
alter table `tbl_article` CHANGE `content` `content` longtext comment '内容';
alter table `tbl_group_topic` CHANGE `content` `content` longtext comment '内容';