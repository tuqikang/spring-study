-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '书名',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `amount` int(11) NOT NULL COMMENT '总量',
  `rest` int(11) NOT NULL COMMENT '余量',
  `price` decimal(10,0) NOT NULL COMMENT '价格',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` int(11) DEFAULT '0' COMMENT '逻辑删除0未删除 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='图书表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'傲慢与偏见','讲述爱情故事',100,100,90,'2019-06-22 12:33:05',1),(2,'时间简史','作者:霍金',100,100,87,'2019-06-22 12:33:31',1),(3,'绿皮书','不知道说什么',100,100,100,'2019-06-22 12:34:08',1),(4,'绿皮书','hh',100,99,100,'2019-06-22 12:40:11',0),(5,'时间简史','作者:霍金',100,99,100,'2019-06-22 12:40:44',0),(6,'三体','科幻',100,97,100,'2019-06-22 12:40:59',0),(7,'流浪地球','科幻小说',100,99,100,'2019-06-22 12:41:48',0),(8,'java','编程',100,100,100,'2019-06-22 12:43:12',0),(9,'c++','编程',100,100,100,'2019-06-22 12:43:43',0),(10,'c#','编程',100,100,100,'2019-06-22 12:43:53',0),(11,'心理学','生命与科学',100,100,100,'2019-06-22 12:44:13',0),(12,'百科全书','是一本非常全面的书',100,100,100,'2019-06-22 12:44:28',0),(13,'猫鼠游戏','演员的个人修养',100,100,100,'2019-06-22 12:45:06',0),(14,'傲慢与偏见','爱情故事',100,100,100,'2019-06-23 06:44:24',0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `definition`
--

DROP TABLE IF EXISTS `definition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '对应角色',
  `upper_limit` int(11) NOT NULL COMMENT '数量上限',
  `time_limit` int(11) NOT NULL COMMENT '天数上限',
  `forfeit` decimal(10,0) NOT NULL COMMENT '超时的每天罚金',
  `update` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='规则定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `definition`
--

LOCK TABLES `definition` WRITE;
/*!40000 ALTER TABLE `definition` DISABLE KEYS */;
INSERT INTO `definition` VALUES (1,1,14,10,1,'2019-06-22 00:00:00'),(2,2,20,30,2,'2019-06-22 00:00:00');
/*!40000 ALTER TABLE `definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL COMMENT '标签',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'STUDENT','STUDENT'),(2,'TEACHER','TEACHER'),(3,'ADMIN','ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book`
--

DROP TABLE IF EXISTS `user_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `book_id` int(11) NOT NULL COMMENT '图书id',
  `status` int(11) NOT NULL COMMENT '状态 1.未归还 2.已按时归还 3.未按时归还',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='用户图书表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book`
--

LOCK TABLES `user_book` WRITE;
/*!40000 ALTER TABLE `user_book` DISABLE KEYS */;
INSERT INTO `user_book` VALUES (1,2,1,3,'2019-06-22 00:00:00','2019-06-12 00:00:00'),(2,2,6,2,'2019-06-22 00:00:00','2019-07-02 00:00:00'),(3,2,8,2,'2019-06-22 00:00:00','2019-07-02 00:00:00'),(4,2,9,2,'2019-06-22 00:00:00','2019-07-02 00:00:00'),(9,2,1,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(10,2,6,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(11,2,7,1,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(12,2,8,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(13,2,6,1,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(14,2,7,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(15,2,9,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(16,2,10,2,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(17,2,11,3,'2019-06-23 00:00:00','2019-06-13 00:00:00'),(18,2,4,1,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(19,2,6,1,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(20,2,5,1,'2019-06-23 00:00:00','2019-07-03 00:00:00'),(21,2,6,1,'2019-06-23 00:00:00','2019-07-03 00:00:00');
/*!40000 ALTER TABLE `user_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_user_role_role_id` (`role_id`),
  UNIQUE KEY `unq_user_role_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT 'ISGMyneATSuhkiwz4BURBQ==' COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) NOT NULL COMMENT '性别1.男 2.女',
  `age` int(11) NOT NULL COMMENT '年龄',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `identity_card` varchar(255) DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','ISMvKXpXpadDiUoOSoAfww==','admin',1,21,'18787659822','213@qq.com','123123142141343143'),(2,'tuqikang','ICy5YqxZB1uWSwcVLSNLcA==','涂齐康',1,20,'18787659821','123@qq.com',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-23  7:05:36
