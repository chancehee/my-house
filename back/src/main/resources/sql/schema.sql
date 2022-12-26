create database `hh`;
use hh;

DROP TABLE IF EXISTS `hh`.`boards`;
create table if not exists `hh`.`boards`(
	`article_no` int not null auto_increment,
    `user_id` varchar(16) not null,
    `title` varchar(16) not null,
    `text` varchar(200) not null,
    `hit` int null default 0,
    `regtime` timestamp not null default current_timestamp,
    primary key (`article_no`),
    constraint `boards_to_members_fk`
		foreign key (`user_id`)
        references `hh`.`members` (`userid`));
        
DROP TABLE IF EXISTS `hh`.`memos`;
create table if not exists `hh`.`memos` (
	`memo_no` int not null auto_increment,
    `user_id` varchar(16) not null,
    `comment` varchar(200) not null,
    `memotime` timestamp not null default current_timestamp,
    `article_no` int not null,
    primary key (`memo_no`),
    constraint `memos_to_boards_fk`
		foreign key (`article_no`)
        references `hh`.`boards` (`article_no`),
	constraint `memos_to_members_fk`
		foreign key (`user_id`)
        references `hh`.`members` (`userid`)
        on delete no action
        on update no action
);

DROP TABLE IF EXISTS `hh`.`notices`;
create table if not exists notices(
	`num` int(10) not null auto_increment primary key,
	`user_id` varchar(16) not null,
	`title` varchar(16) not null,
	`text` varchar(200) not null,
	`join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `hh`.`members`;
CREATE TABLE IF NOT EXISTS `hh`.`members` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `email` VARCHAR(20) NULL DEFAULT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `salt` VARCHAR(100) NOT NULL,
  `token` VARCHAR(1000) NULL,
  PRIMARY KEY (`userid`));


DROP TABLE IF EXISTS `dongcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dongcode` (
  `dongCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dongCode`),
  KEY `sidoName` (`sidoName`),
  CONSTRAINT `dongcode_ibfk_1` FOREIGN KEY (`sidoName`) REFERENCES `sidocode` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

  
DROP TABLE IF EXISTS `housedeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `housedeal` (
  `no` int NOT NULL AUTO_INCREMENT,
  `aptCode` int DEFAULT NULL,
  `dealAmount` varchar(20) DEFAULT NULL,
  `dealYear` int DEFAULT NULL,
  `dealMonth` int DEFAULT NULL,
  `dealDay` int DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `floor` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `rentMoney` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `aptCode` (`aptCode`),
  CONSTRAINT `housedeal_ibfk_1` FOREIGN KEY (`aptCode`) REFERENCES `houseinfo` (`aptCode`)
) ENGINE=InnoDB AUTO_INCREMENT=68865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
  
  
  
DROP TABLE IF EXISTS `houseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houseinfo` (
  `aptCode` int NOT NULL,
  `aptName` varchar(50) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `buildYear` int DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  KEY `dongCode` (`dongCode`),
  CONSTRAINT `houseinfo_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

create table if not exists `hh`.`favorite`(
	userid varchar(16) not null,
  aptCode varchar(45) not null,
  favCode bigint not null auto_increment,
  primary key(favCode),
  constraint `favorite_to_members_fk` 
		foreign key (`userid`)
		references `hh`.`members` (`userid`)
);

  
