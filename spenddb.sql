-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: spenddb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `group_transaction`
--

DROP TABLE IF EXISTS `group_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `creator_id` int NOT NULL,
  `price` double DEFAULT NULL,
  `active` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_group_fk_idx` (`creator_id`),
  CONSTRAINT `user_group_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_transaction`
--

LOCK TABLES `group_transaction` WRITE;
/*!40000 ALTER TABLE `group_transaction` DISABLE KEYS */;
INSERT INTO `group_transaction` VALUES (1,'Nhóm 1','arp',10,10000000,0),(2,'Nhóm 2','arp',9,5000030,0);
/*!40000 ALTER TABLE `group_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_users`
--

DROP TABLE IF EXISTS `group_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_members_fk_idx` (`group_id`),
  KEY `user_members_fk_idx` (`user_id`),
  CONSTRAINT `group_members_fk` FOREIGN KEY (`group_id`) REFERENCES `group_transaction` (`id`),
  CONSTRAINT `user_members_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_users`
--

LOCK TABLES `group_users` WRITE;
/*!40000 ALTER TABLE `group_users` DISABLE KEYS */;
INSERT INTO `group_users` VALUES (1,1,10),(2,1,9);
/*!40000 ALTER TABLE `group_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `type` varchar(100) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `noti_user_fk_idx` (`user_id`),
  CONSTRAINT `noti_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_transaction`
--

DROP TABLE IF EXISTS `personal_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `purpose` varchar(45) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_transaction`
--

LOCK TABLES `personal_transaction` WRITE;
/*!40000 ALTER TABLE `personal_transaction` DISABLE KEYS */;
INSERT INTO `personal_transaction` VALUES (1,'chi tiêu cá nhân','shoping','abc',2000000,NULL);
/*!40000 ALTER TABLE `personal_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(10) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `active` int NOT NULL DEFAULT '0',
  `user_role` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `joined_date` datetime DEFAULT NULL,
  `personal_transaction_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_personal_fk_idx` (`personal_transaction_id`),
  CONSTRAINT `user_personal_fk` FOREIGN KEY (`personal_transaction_id`) REFERENCES `personal_transaction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin pro','admin@admin.com','039474635','admin','$2a$10$oU0okApZiC38mt8qn0KNYuOh9EldEZjrQX/YM09hMezBsh3UzEXkO',1,'ROLE_ADMIN','https://res.cloudinary.com/cloudybeauty/image/upload/v1681060469/sxxdipein8lywpbh8xjd.jpg','2023-04-09 23:57:05',NULL),(9,'tấn đạt','nkocbroed123@gmail.com','0398475467','tandat1234','$2a$10$oU0okApZiC38mt8qn0KNYuOh9EldEZjrQX/YM09hMezBsh3UzEXkO',1,'ROLE_USER','','2023-04-09 23:57:05',NULL),(10,'dore mon','doremon@doremon.com','0394843635','doremon','$2a$10$bcSsEfB7cCeqKt4QZ7yNDOdXHa6uaASfsChF5CPhdVZXDTYY/0Y0O',1,'ROLE_USER','https://res.cloudinary.com/cloudybeauty/image/upload/v1681060469/sxxdipein8lywpbh8xjd.jpg','2023-04-10 00:14:29',1),(11,'T Ấ N Đ Ạ T','tandat0723@gmail.com','0393610575','tandat123','$2a$10$hs4r1CxAyWDv9.LtIjgvQepbGMuUVifNMGXJHdmR7BYm799Xec7b2',0,'ROLE_USER','https://res.cloudinary.com/cloudybeauty/image/upload/v1681675099/iqkvvnag91dgyayogpda.jpg','2023-04-17 02:58:20',NULL),(12,'T Ấ N Đ Ạ T','user111@gmail.com','0393610573','user111','$2a$10$KojV2K4/N5QsdzjIsxW/.e4QpsTpqsrUSvMf67fu/oa6D7k6VYdye',0,'ROLE_USER','https://res.cloudinary.com/cloudybeauty/image/upload/v1681675511/eaxakhdyboso7hmdui6g.jpg','2023-04-17 03:05:12',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-17  3:06:39
