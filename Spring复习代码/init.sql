CREATE DATABASE `family` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `family`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `avatar_url` varchar(500) DEFAULT NULL COMMENT '头像',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信openId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '组名称',
  `desc` varchar(500) DEFAULT NULL COMMENT '组描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组表';

CREATE TABLE `user_group` (
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `user_group_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_group_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-组关系表';

CREATE TABLE `express_company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '公司名称',
  `code` varchar(50) NOT NULL COMMENT '公司代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司表';

CREATE TABLE `express` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL COMMENT '短信内容',
  `phone` varchar(20) NOT NULL COMMENT '取件人手机号',
  `fetch_code` varchar(50) NOT NULL COMMENT '快递取件码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未取 1已取',
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `express_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `express_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `express_ibfk_3` FOREIGN KEY (`company_id`) REFERENCES `express_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递表';

CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL COMMENT '金额', 
  `type` varchar(50) NOT NULL COMMENT '账单类型',
  `date` date NOT NULL COMMENT '还款/支付日期',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未付 1已付',
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账单表';