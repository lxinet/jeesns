CREATE TABLE `tbl_checkin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `member_id` INT(11),
  `continue_day` INT(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE `tbl_checkin` ADD CONSTRAINT `fk_checkin_member` FOREIGN KEY (`member_id`) REFERENCES `tbl_member` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

INSERT INTO tbl_action(id, create_time, name, log, status, update_time) VALUES (10004,now(),'签到','签到',0,now());

INSERT INTO tbl_score_rule(id,create_time,update_time,name,score,remark,type,status) VALUES (14,now(),now(),'签到',1,'签到奖励','day',1);



