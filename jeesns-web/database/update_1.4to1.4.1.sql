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
