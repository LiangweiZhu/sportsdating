/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50718
Source Host           : rm-uf68rr69o96u8ey3oo.mysql.rds.aliyuncs.com:3306
Source Database       : sportsapp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-11-22 22:39:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for abilities
-- ----------------------------
DROP TABLE IF EXISTS `abilities`;
CREATE TABLE `abilities` (
  `user_id` char(10) NOT NULL,
  `tennis` tinyint(3) unsigned NOT NULL,
  `badminton` tinyint(3) unsigned NOT NULL,
  `ping_pang` tinyint(3) unsigned NOT NULL,
  `basketball` tinyint(3) unsigned NOT NULL,
  `football` tinyint(3) unsigned NOT NULL,
  `volleyball` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of abilities
-- ----------------------------

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities` (
  `activity_id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) DEFAULT NULL,
  `initiator` char(20) NOT NULL COMMENT '活动发起者',
  `content` text,
  `remarks` text COMMENT '备注',
  `init_time` bigint(20) unsigned DEFAULT NULL,
  `start_time` bigint(20) unsigned DEFAULT NULL,
  `end_time` bigint(20) unsigned DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `people_needs` smallint(255) unsigned DEFAULT NULL,
  `people_have` smallint(255) unsigned DEFAULT '0',
  `activity_pic` varchar(255) DEFAULT NULL,
  `ac_ra` char(10) DEFAULT NULL COMMENT '此项区分活动或者比赛',
  `click_num` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES ('58', '來一场网球', '2016214073', '', '', '1510727613436', '1510733613436', '1510739613436', '', '6', '1', null, 'active', '0');
INSERT INTO `activities` VALUES ('61', '打羽毛', '2016214073', '哈哈', '来呀', '1510729592098', '1510735592098', '1510741592098', '太极', '3', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('62', '乒乓球', '2016214073', '玩会乒乓', '带上必备品', '1510749875305', '1510755875305', '1510761875305', '风雨', '8', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('63', '打羽毛球', '2016214073', '带球拍', '按时到', '1510756874151', '1510762874151', '1510768874151', '逸夫楼', '2', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('64', 'run', '2016210395', '123', 'kaui', '1510759865254', '1510765865254', '1510771865254', 'yf', '2', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('69', '啪羽毛', '2016214073', '4', '！', '1510887915113', '1510893915113', '1510899915113', '！', '8', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('70', '打羽毛', '2016214073', '一起打羽毛', '', '1510926619651', '1510932619651', '1510938619651', '老操', '4', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('73', '一起打网球呗', '2016214073', '一起嗨皮，打打网球，锻炼身体', '不要迟到哦', '1510986585973', '1511004600', '1511010000', '网球场', '4', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('74', '一起篮球去', '2016214073', '3人一组，一起嗨皮', '不要迟到哟', '1510987567949', '1511004600000', '1511013600000', '灯光篮球场', '6', '0', null, 'active', '0');
INSERT INTO `activities` VALUES ('116', '都好都好还是', '2015210054', '大喊大叫', '都觉得', '1511063599007', '1509465600000', '1512057600000', '打电话', '4646', '0', '1511063600785IMG_20171118_185427.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('117', '想好好的', '2015210054', '电光火石', '习惯电话', '1511064218101', '1509465600000', '1512057600000', '嘘嘘', '4646', '0', '1511064220005IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('118', '一起打篮球', '2015210054', '一起打篮球，一起嗨皮', '不要迟到哦', '1511067306061', '1509465600000', '1512057600000', '风雨操场', '8', '0', '1511067309151IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('119', '打乒乓球走起', '2015210054', '一起打乒乓球', '不要迟到哦', '1511067572502', '1509465600000', '1511971200000', '风雨操场', '3', '0', '1511067574978IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('120', '一起去登山', '2015210054', '一起去爬山', '记得带上必备品', '1511067690539', '1512057600000', '1514563200000', '南山', '10', '0', '1511067692574IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('121', '打网球', '2015210054', '电话号', '不想好像', '1511067755725', '1512057600000', '1512230400000', '南山', '3', '0', '1511067757898IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('122', '一起打乒乓', '2015210054', '超喜欢', '大家大家', '1511067905016', '1512057600000', '1514649600000', '堵得慌', '42', '0', '1511067906857IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('123', '给给给', '2015210054', '发给你好尴尬过分', '', '1511068152272', '1483200000000', '1483372800000', '反反复复拍', '12', '0', '1511068155072IMG20171119020031.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('124', '发发发', '2015210054', '发发发', '', '1511068211649', '1512057600000', '1512144000000', '单独', '84', '0', '1511068213784IMG_20171118_185845.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('125', '打篮球', '2015210054', '打个电话', '', '1511068269744', '1509465600000', '1511539200000', '行吧行吧', '8', '0', '1511068272501IMG_20171118_185427.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('126', '打乒乓球', '2015210054', '都好都好还是', '', '1511068315174', '1509465600000', '1512057600000', '对不对哈哈', '54545', '0', '1511068318793IMG_20171110_105536.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('127', '大好河山', '2015210054', '行吧行吧', '下班好像', '1511068516916', '1512057600000', '1514476800000', '行吧行吧', '454', '0', '1511068519198IMG_20171116_124921.jpg', 'activity', '0');
INSERT INTO `activities` VALUES ('128', '散步', '2015210054', '下班不想', '下班不想', '1511068877911', '1512057600000', '1512662400000', '的棒棒哒', '2', '0', '1511068884188IMG_20171116_124921.jpg', 'activity', '0');

-- ----------------------------
-- Table structure for date
-- ----------------------------
DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `date_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_sponsor` char(10) NOT NULL,
  `sports` enum('tennis','badminton','pingpong','basketball','football','volleyball') NOT NULL,
  `peole_needs` tinyint(3) DEFAULT '1',
  `date_start` datetime NOT NULL,
  `date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`date_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of date
-- ----------------------------

-- ----------------------------
-- Table structure for dynamics
-- ----------------------------
DROP TABLE IF EXISTS `dynamics`;
CREATE TABLE `dynamics` (
  `dynamic_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` char(10) NOT NULL,
  `init_time` bigint(20) DEFAULT NULL,
  `words` text COMMENT '动态的文字内容',
  `dynamic_pic` varchar(255) DEFAULT NULL COMMENT '动态的图片',
  `like_num` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`dynamic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamics
-- ----------------------------
INSERT INTO `dynamics` VALUES ('19', '2015210098', '5656234819', '这是测试2啦啦啦', '15105013116722.jpg', '0');
INSERT INTO `dynamics` VALUES ('20', '2015210098', '5656234819', null, '15104997719012.jpg', '0');
INSERT INTO `dynamics` VALUES ('21', '2015210105', '1510762874151', '第二次测试', null, '0');
INSERT INTO `dynamics` VALUES ('22', '2015210105', '1510762874151', '第三次测试', null, '0');
INSERT INTO `dynamics` VALUES ('23', '2015210105', '1510762874151', '第四次测试', '15107628331212.jpg', '0');
INSERT INTO `dynamics` VALUES ('24', '2015210105', '1510762874151', '第三次测试', null, '0');
INSERT INTO `dynamics` VALUES ('25', '2015210105', '1510762874151', '远程测试', '15107958282162.jpg', '1');
INSERT INTO `dynamics` VALUES ('26', '2016210395', '1511008431022', null, null, '0');
INSERT INTO `dynamics` VALUES ('27', '2016210395', '1511008468037', null, null, '0');
INSERT INTO `dynamics` VALUES ('28', '2016210395', '1511008583319', null, null, '0');
INSERT INTO `dynamics` VALUES ('29', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('30', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('31', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('32', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('33', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('34', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('35', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('36', '2016210395', '1511008674048', null, null, '0');
INSERT INTO `dynamics` VALUES ('37', '2016210395', '1511008933962', null, null, '0');
INSERT INTO `dynamics` VALUES ('38', '2016210395', '1511008933962', null, null, '0');
INSERT INTO `dynamics` VALUES ('39', '2016210395', '1511008933962', null, null, '0');
INSERT INTO `dynamics` VALUES ('40', '2016210395', '1511009065331', 'im happy 心情', null, '0');
INSERT INTO `dynamics` VALUES ('41', '2016210395', '1511010993085', 'adauhdja', null, '0');
INSERT INTO `dynamics` VALUES ('42', '2016210395', '1511011816474', 'wuyhushw', null, '0');
INSERT INTO `dynamics` VALUES ('43', '2016210395', '1511011904332', 'gjhghgj', null, '0');
INSERT INTO `dynamics` VALUES ('44', '2016210395', '1511011904332', 'gjhghgj', null, '0');
INSERT INTO `dynamics` VALUES ('45', '2016210395', '1511011904332', 'gjhghgj', null, '0');
INSERT INTO `dynamics` VALUES ('46', '2016210395', '1511025130824', 'nnajanajbnjabjs', null, '0');
INSERT INTO `dynamics` VALUES ('47', '2016210395', '1511025180126', 'good job', null, '0');
INSERT INTO `dynamics` VALUES ('48', '2016210395', '1511025287287', 'yresdd', null, '0');
INSERT INTO `dynamics` VALUES ('49', '2016210395', '1511025522598', 'ghgh', null, '0');
INSERT INTO `dynamics` VALUES ('50', '2016210395', '1511025630959', 'hhhhhhhha', null, '0');
INSERT INTO `dynamics` VALUES ('51', '2016210395', '1511025805547', '我真是太菜了', null, '0');
INSERT INTO `dynamics` VALUES ('52', '2016210395', '1511026077209', '好困啊好困(¦3[▓▓', null, '0');
INSERT INTO `dynamics` VALUES ('53', '2016214073', '1511026231524', '下班不懂', null, '0');
INSERT INTO `dynamics` VALUES ('54', '2016214073', '1511026240205', '你就', null, '0');
INSERT INTO `dynamics` VALUES ('55', '2016214073', '1511026252320', '123', null, '0');
INSERT INTO `dynamics` VALUES ('56', '2016214073', '1511026269640', '无关', null, '0');
INSERT INTO `dynamics` VALUES ('57', '2016214073', '1511026279768', '啥话', null, '0');
INSERT INTO `dynamics` VALUES ('58', '2016210395', '1511026277952', '看到了吗', null, '0');
INSERT INTO `dynamics` VALUES ('59', '2016210395', '1511026299842', '其实我们可以聊天', null, '0');
INSERT INTO `dynamics` VALUES ('60', '2016210395', '1511026608105', '好想睡觉啊', null, '0');
INSERT INTO `dynamics` VALUES ('61', '2016210395', '1511062360006', '金龙门', null, '0');
INSERT INTO `dynamics` VALUES ('62', '2016210395', '1511063055007', 'sxishi', null, '0');
INSERT INTO `dynamics` VALUES ('63', '2016213991', '1511065919631', '我很开心今天', null, '0');
INSERT INTO `dynamics` VALUES ('64', '2016213991', '1511067241567', '我没吃饭', null, '0');
INSERT INTO `dynamics` VALUES ('65', '2016210395', '1511068049072', '5斤吐了', null, '0');
INSERT INTO `dynamics` VALUES ('66', '2016210395', '1511068379036', '快乐旅途', null, '0');
INSERT INTO `dynamics` VALUES ('67', '2016210395', '1511068390530', '路口', null, '0');
INSERT INTO `dynamics` VALUES ('68', '2016210395', '1511070061121', 'jusawgjgj', null, '0');
INSERT INTO `dynamics` VALUES ('69', '2016210395', '1511078518249', 'hhhn', null, '0');
INSERT INTO `dynamics` VALUES ('70', '2016210395', '1511181524900', '聚聚', null, '0');

-- ----------------------------
-- Table structure for dynamics_like
-- ----------------------------
DROP TABLE IF EXISTS `dynamics_like`;
CREATE TABLE `dynamics_like` (
  `serial_num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dynamic_id` int(10) unsigned NOT NULL,
  `user_id` char(10) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_like` char(10) DEFAULT 'like',
  `like_time` bigint(20) unsigned NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`serial_num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamics_like
-- ----------------------------
INSERT INTO `dynamics_like` VALUES ('1', '12', '2015210097', '周及', 'like', '0');
INSERT INTO `dynamics_like` VALUES ('4', '25', '2015210098', '朱良伟', 'unlike', '1234567890');

-- ----------------------------
-- Table structure for sportsgroup
-- ----------------------------
DROP TABLE IF EXISTS `sportsgroup`;
CREATE TABLE `sportsgroup` (
  `group_id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) NOT NULL,
  `sponsor_id` char(10) NOT NULL,
  `group_pic` varchar(255) DEFAULT NULL,
  `intro` text,
  PRIMARY KEY (`group_id`),
  KEY `id` (`sponsor_id`),
  CONSTRAINT `id` FOREIGN KEY (`sponsor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sportsgroup
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(10) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nick_name` varchar(20) DEFAULT NULL,
  `qq_num` varchar(12) DEFAULT NULL,
  `telephone_num` char(11) DEFAULT NULL,
  `college` char(30) DEFAULT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  `ability` tinyint(3) unsigned DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `control` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2015210098', '朱良伟', '男', '046717', '朱良伟', null, null, '通信与信息工程学院', null, '1', null, null);
INSERT INTO `user` VALUES ('2016210395', '彭礼烨', '男', '117515', '彭礼烨', null, null, '通信与信息工程学院', null, '1', null, null);
INSERT INTO `user` VALUES ('2016213991', '彭博韬', '男', '240332', '彭博韬', null, null, '软件工程学院', null, '1', null, null);
INSERT INTO `user` VALUES ('2016214073', '涂文泰', '男', '128670', '涂文泰', null, null, '软件工程学院', null, '1', null, null);

-- ----------------------------
-- Table structure for user_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity` (
  `serial_num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` char(10) NOT NULL,
  `activity_id` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`serial_num`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`),
  CONSTRAINT `activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`activity_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_activity
-- ----------------------------
INSERT INTO `user_activity` VALUES ('1', '2015210098', '70');
INSERT INTO `user_activity` VALUES ('2', '2016210395', '70');
INSERT INTO `user_activity` VALUES ('3', '2015210098', '64');
INSERT INTO `user_activity` VALUES ('4', '2015210098', '58');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `serial_num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` char(10) NOT NULL,
  `group_id` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`serial_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
