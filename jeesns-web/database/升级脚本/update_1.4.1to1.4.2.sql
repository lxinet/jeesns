CREATE TABLE `tbl_question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime  DEFAULT NULL COMMENT '创建时间',
  `member_id` INT(11) COMMENT '会员',
  `type_id` INT(11) COMMENT '类型版块',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `description` varchar(255) DEFAULT NULL COMMENT '描述说明',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `status` int(11) DEFAULT '0' COMMENT '状态，默认0，0是未解决，1是已解决, -1关闭问题',
  `answer_id` int(11) DEFAULT null COMMENT '最佳答案ID',
  `answer_count` int(11) DEFAULT '0' COMMENT '回答数量',
  `bonus` int(11) DEFAULT '0' COMMENT '奖励积分',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_question_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime  DEFAULT NULL COMMENT '创建时间',
  `name` varchar(32),
  `sort` int(11) default '50',
  `bonus_type` int(11) DEFAULT '0' COMMENT '奖励类型，0是积分，1是现金',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `tbl_answer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime  DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime  DEFAULT NULL COMMENT '最后修改时间',
  `member_id` INT(11) COMMENT '会员',
  `question_id` int(11) DEFAULT NULL COMMENT '问题ID',
  `content` longtext COMMENT '内容',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论数量',
  `favor_count` int(11) DEFAULT '0' COMMENT '点赞',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


alter table `tbl_score_detail` drop foreign key `fk_score_detail_score_rule`;