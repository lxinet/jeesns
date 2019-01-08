
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


