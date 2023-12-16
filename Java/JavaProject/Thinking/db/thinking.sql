-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: thinking
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminId` int NOT NULL AUTO_INCREMENT,
  `adminName` varchar(100) DEFAULT NULL,
  `adminPassword` varchar(100) DEFAULT NULL,
  `adminHead` text,
  `adminBirthday` date DEFAULT NULL,
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `adminName` (`adminName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','$2a$10$ll.brLxJofHt9Pj4MW.yqesIYcXGnOAOejA2qLU6qpoL9Wp6U5A9a','1689072292063e61fed0f-7c24-4fa2-a143-107bf047d847WX20230316-113808@2x.png','2023-07-11');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `articleId` int NOT NULL AUTO_INCREMENT,
  `articleTitle` varchar(200) DEFAULT NULL,
  `articleContent` text,
  `articleIssueTime` datetime DEFAULT NULL,
  `humanId` int DEFAULT NULL,
  `typeId` int DEFAULT NULL,
  PRIMARY KEY (`articleId`),
  KEY `articleTitleIndex` (`articleTitle`(20))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'今天的日记','<p style=\"text-align:center;\">今天的日记<span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\">今天的日记</span></p><p style=\"text-align:center;\">今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记<span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\"><br></span></p><p style=\"text-align:center;\">今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记<br></p><p style=\"text-align:center;\">今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记今天的日记<img src=\"/editorimages/168809045756791eb1f4b-d11c-4bfc-b283-3759269273f3WX20230316-113808@2x.png\" style=\"color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-size: var(--bs-body-font-size); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align); max-width: 100%;\" width=\"50%\"><br></p><p style=\"text-align:center;\"><span style=\"font-size: 1em; color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\"><br></span></p>','2023-06-30 10:01:28',1,3),(2,'ddsf','<p>sfdsfsdfasdfa</p>','2023-06-30 10:12:03',2,3);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `human`
--

DROP TABLE IF EXISTS `human`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `human` (
  `humanId` int NOT NULL AUTO_INCREMENT,
  `humanName` varchar(200) DEFAULT NULL,
  `humanNiceName` varchar(200) DEFAULT NULL,
  `humanPassword` varchar(200) DEFAULT NULL,
  `humanEmail` varchar(200) DEFAULT NULL,
  `humanDescribe` text,
  PRIMARY KEY (`humanId`),
  KEY `humanLoginIndex` (`humanName`(20),`humanPassword`(20))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `human`
--

LOCK TABLES `human` WRITE;
/*!40000 ALTER TABLE `human` DISABLE KEYS */;
INSERT INTO `human` VALUES (1,'xiaoming','小怪','123456','xiaohong@email.com',NULL),(2,'xiaojun','隔壁老王','admin','xiaojun@email',NULL),(3,'lanruijiang','隔壁老王','admin','364832707@qq.com',NULL);
/*!40000 ALTER TABLE `human` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageStore`
--

DROP TABLE IF EXISTS `imageStore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imageStore` (
  `imageStoreId` int NOT NULL AUTO_INCREMENT,
  `imageStoreAddress` text,
  `imageStoreDate` datetime DEFAULT NULL,
  PRIMARY KEY (`imageStoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageStore`
--

LOCK TABLES `imageStore` WRITE;
/*!40000 ALTER TABLE `imageStore` DISABLE KEYS */;
/*!40000 ALTER TABLE `imageStore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `messageId` int NOT NULL AUTO_INCREMENT,
  `messageTitle` varchar(200) DEFAULT NULL,
  `messageText` text,
  `messageTime` datetime DEFAULT NULL,
  `adminId` int DEFAULT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (31,'1','<p>1</p>','2023-07-12 17:28:06',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `replyId` int NOT NULL AUTO_INCREMENT,
  `replyContent` text,
  `replyTime` datetime DEFAULT NULL,
  `humanId` int DEFAULT NULL,
  `articleId` int DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,'<p>阿斯达是的撒</p>','2023-06-30 10:04:23',1,1),(2,'<p><img src=\"/editorimages/1688090675472ac358ff7-cbbb-4f7c-91e2-4a5fc14118e5WX20230316-113808@2x.png\" style=\"max-width:100%;\" width=\"30%\"><br></p>','2023-06-30 10:04:38',1,1),(3,'<p>扯淡<img class=\"eleImg\" src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png\" alt=\"[[舔屏]]\" style=\"color: var(--bs-body-color); font-family: var(--bs-body-font-family); font-weight: var(--bs-body-font-weight); text-align: var(--bs-body-text-align);\"></p>','2023-06-30 10:11:00',2,1),(4,'<p><img src=\"/editorimages/16880910721006938090d-3b94-42ac-9194-9c630a31cfdbq.png\" style=\"max-width:100%;\" width=\"30%\"><br></p>','2023-06-30 10:11:15',2,1);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleRelation`
--

DROP TABLE IF EXISTS `roleRelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roleRelation` (
  `roleRelationId` int NOT NULL AUTO_INCREMENT,
  `roleId` int DEFAULT NULL,
  `adminId` int DEFAULT NULL,
  PRIMARY KEY (`roleRelationId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleRelation`
--

LOCK TABLES `roleRelation` WRITE;
/*!40000 ALTER TABLE `roleRelation` DISABLE KEYS */;
INSERT INTO `roleRelation` VALUES (1,1,1),(4,3,2),(5,2,3),(6,3,4),(7,2,4),(11,2,5),(13,3,1);
/*!40000 ALTER TABLE `roleRelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) DEFAULT NULL,
  `roleDesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN','管理员'),(2,'ROLE_ADMINTYPE','文章类型管理'),(3,'ROLE_ADMINMESSAGE','新闻管理');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `typeId` int NOT NULL AUTO_INCREMENT,
  `typeName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (2,'我的日记'),(3,'我的感悟'),(4,'我的相册');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26  9:10:53
