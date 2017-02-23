
ALTER TABLE `tbl_member` ADD follows INT(11) DEFAULT '0' comment '关注会员数量';
ALTER TABLE `tbl_member` ADD fans INT(11) DEFAULT '0' comment '粉丝数量';
UPDATE `tbl_member` set follows=0;
UPDATE `tbl_member` set fans=0;