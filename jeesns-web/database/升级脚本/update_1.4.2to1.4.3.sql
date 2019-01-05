
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
  `trade_no` varchar(32)  COMMENT '支付宝交易号',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tbl_config` (`jkey`, `jvalue`, `description`)
VALUES
 ('alipay_app_id','','支付宝应用ID'),
  ('alipay_merchant_private_key','','支付宝商户私钥'),
  ('alipay_public_key','','支付宝公钥'),
  ('group_follow_pay_fee','0','付费群组收取手续费'),
  ('member_recommend','0','会员推荐注册，0关闭，1开启'),
  ('member_recommend_reward_score','0','推荐注册奖励积分'),
  ('member_recommend_reward_money','0','推荐注册奖励金额');


ALTER TABLE `tbl_member` ADD COLUMN `super_member_id` INT DEFAULT null;


