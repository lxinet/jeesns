
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


INSERT INTO `tbl_config` (`jkey`, `jvalue`, `description`)
VALUES
('score_reg','100','注册奖励积分'),
('score_','0','cms投稿审核，0需要审核，1不需要审核'),
('group_alias','群组','群组别名'),
('group_apply','1','群组是否可以申请，0不可以，1可以'),
('group_apply_review','0','群组申请是否需要审核，0需要审核，1不需要审核'),
('member_email_valid','0','邮箱验证，0不需要验证，1需要验证'),
('member_login_open','1','会员登录开关，0关闭，1开启'),
('member_register_open','1','会员注册开关，0关闭，1开启'),
('site_description','JEESNS是一款基于JAVA企业级平台研发的社交管理系统，依托企业级JAVA的高效、安全、稳定等优势，开创国内JAVA版开源SNS先河。','网站描述'),
('site_domain','http://www.jeesns.cn/','网站域名'),
('site_keys','jeesns,sns,java','网站关键词'),
('site_logo','/res/common/images/lxinetlogo.png','网站LOGO'),
('site_name','JEESNS','网站名称'),
('site_send_email_account','','发送邮箱账号'),
('site_send_email_password','','发送邮箱密码'),
('site_send_email_smtp','','发送邮箱SMTP服务器地址'),
('site_seo_title','又一个JEESNS社区','SEO标题'),
('weibo_alias','微博','微博别名'),
('weibo_post','1','微博发布，0不可以发布，1可以发布'),
('weibo_post_maxcontent','140','微博内容字数');