
alter table `tbl_config` CHANGE `jvalue` `jvalue` varchar(2000);

CREATE TABLE `tbl_pay` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime  DEFAULT NULL COMMENT '创建时间',
  `no` varchar(32) COMMENT '订单号',
  `member_id` INT(11) COMMENT '会员',
  `money` double(11,2) COMMENT '充值金额',
  `fee` double(11,2) DEFAULT '0' COMMENT '手续费',
  `act_money` double(11,2) DEFAULT '0' COMMENT '实充金额',
  `type` int(11) COMMENT '类型，1支付宝，2微信',
  `status` int(11) DEFAULT '0' COMMENT '状态，0未付款，1已付款，2已退款，3已关闭',
  `trade_no` varchar(32)  COMMENT '商家交易号',
  `qrcode` varchar(128)  COMMENT '微信二维码',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tbl_config` (`jkey`, `jvalue`, `description`)
VALUES
  ('alipay_open','0','支付宝支付是否开启，0关闭，1开启'),
  ('alipay_app_id','','支付宝应用ID'),
  ('alipay_merchant_private_key','','支付宝商户私钥'),
  ('alipay_public_key','','支付宝公钥'),
  ('payjs_open','0','PAYJS微信支付是否开启，0关闭，1开启'),
  ('payjs_mchid','','PAYJS商户号'),
  ('payjs_key','','PAYJS通信密钥'),
  ('group_follow_pay_fee','0','付费群组收取手续费');

ALTER TABLE `tbl_member` ADD COLUMN `super_member_id` INT DEFAULT null;



CREATE TABLE `tbl_goods_cate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '分类名称',
  `fid` int(1) DEFAULT '0' COMMENT '父类ID',
  `level` int(1) DEFAULT '1' COMMENT '层级',
  `status` int(1) DEFAULT '0' COMMENT '0正常，1隐藏',
  `sort` int(11) DEFAULT '50' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cate_id` int(11) DEFAULT null COMMENT '所属分类',
  `title` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `subtitle` varchar(255) DEFAULT NULL COMMENT '副标题',
  `no` varchar(64) DEFAULT NULL COMMENT '商品编号',
  `content` text DEFAULT NULL COMMENT '商品描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述说明',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键词',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `pub_time` datetime DEFAULT NULL COMMENT '上架时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `price` double(11,4) DEFAULT '0' COMMENT '价格',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `status` int(1) DEFAULT '0' COMMENT '状态，0下架，1正常',
  `sort` int(11) DEFAULT '50' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(6) DEFAULT NULL COMMENT '地区代码',
  `name` varchar(32) DEFAULT NULL COMMENT '地区名称',
  `fcode` varchar(6) DEFAULT '' COMMENT '上级地区代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_delivery_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `member_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(32) NOT NULL DEFAULT '',
  `province` varchar(32)NOT NULL DEFAULT '',
  `city` varchar(32) NOT NULL DEFAULT '',
  `area` varchar(32) NOT NULL DEFAULT '',
  `address` varchar(128) NOT NULL DEFAULT '',
  `zip` varchar(6) NOT NULL DEFAULT '',
  `phone` varchar(11) NOT NULL DEFAULT '',
  `is_default` tinyint(1) NOT NULL DEFAULT '0'
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8;