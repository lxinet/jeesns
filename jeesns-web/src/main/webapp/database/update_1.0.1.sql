##积分规则表
CREATE TABLE `tbl_score_rule` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` VARCHAR(30) DEFAULT '0' COMMENT '规则名称',
  `score` INT(11) DEFAULT '0' COMMENT '变化积分',
  `remark` VARCHAR(255) COMMENT '说明',
  `type` VARCHAR(10) DEFAULT 'unlimite' COMMENT '奖励次数类型，day每天一次，week每周一次，month每月一次，year每年一次，one只有一次，unlimite不限次数',
  `status` INT(11) DEFAULT '1' COMMENT '状态，0禁用，1启用',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##积分明细表
CREATE TABLE `tbl_score_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `member_id` INT(11) DEFAULT '0' COMMENT '会员ID',
  `type` INT(11) DEFAULT '0' COMMENT '类型，0是普通积分增加，1是奖励，2是撤销奖励',
  `score` INT(11) DEFAULT '0' COMMENT '变化积分',
  `balance` INT(11) DEFAULT '0' COMMENT '账户剩余积分',
  `remark` VARCHAR(255) COMMENT '说明',
  `foreign_id` INT(11) DEFAULT '0' COMMENT '外键ID',
  `score_rule_id` INT(11) DEFAULT '0' COMMENT '积分规则ID',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tbl_score_rule(id,create_time,update_time,name,score,remark,type,status) VALUES
  (1,now(),now(),'注册奖励',100,'注册奖励','one',1),
  (2,now(),now(),'邮箱认证',1,'邮箱认证奖励积分，只有一次','one',1),
  (3,now(),now(),'每天登陆奖励',1,'每天登陆奖励积分，一天仅限一次','day',1),
  (4,now(),now(),'文章投稿',1,'文章投稿奖励积分，如需审核，审核之后奖励','unlimite',1),
  (5,now(),now(),'文章评论',1,'评论文章奖励积分','unlimite',1),
  (6,now(),now(),'文章收到喜欢',1,'文章收到喜欢，作者奖励积分','unlimite',1),
  (7,now(),now(),'发布微博',1,'发布微博奖励积分','unlimite',1),
  (8,now(),now(),'评论微博',1,'评论微博奖励积分','unlimite',1),
  (9,now(),now(),'微博收到点赞',1,'微博收到点赞，作者奖励积分','unlimite',1),
  (10,now(),now(),'申请群组',-10,'申请群组扣除/奖励积分，如需要扣除积分，请填写负数','unlimite',1),
  (11,now(),now(),'群组发帖',1,'群组发帖奖励积分，如需审核，审核之后奖励','unlimite',1),
  (12,now(),now(),'群组帖子评论',1,'群组帖子评论奖励积分','unlimite',1),
  (13,now(),now(),'群组帖子收到喜欢',1,'群组帖子收到喜欢奖励积分','unlimite',1);

##修改jvalue字段长度为500
ALTER TABLE tbl_config MODIFY jvalue varchar(500) DEFAULT '';
INSERT INTO `tbl_config` (`jkey`, `jvalue`, `description`)
VALUES
  ('site_icp','闽ICP备12013573号','备案号'),
  ('site_copyright','Copyright © 2012 - 2017.','版权说明'),
  ('site_tongji','<script>var _hmt = _hmt || [];(function() {var hm = document.createElement("script");hm.src = "https://hm.baidu.com/hm.js?6e79d941db914e4195f4a839b06f2567";var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(hm, s);})();</script>','统计代码');
