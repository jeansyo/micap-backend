-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: educational
-- ------------------------------------------------------
-- Server version	5.7.37

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `user` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` int(11) NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `link` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `type` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) COLLATE utf8_bin NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(145) COLLATE utf8_bin NOT NULL,
  `role` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `codUser_UNIQUE` (`code`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'CODUSR-0023','User Demo','userdemo@gmail.com','123456',0,1),(2,'CODUSR-2222','jeansyoi','jeanr@gmail.com','1234342',0,1),(4,'CODUSR-213','jeansyo2','jeanrasd@gmail.com','12343425',0,1),(5,'CODUSR-12312','jeansyo3','jeanrassd@gmail.com','1234asdasd',0,1),(6,'CODUSER-0','jeansyo4','jeanra34d@gmail.com','1234xxdasd',0,1),(7,'CODUSER-35','jeansyo5','jeanra35d@gmail.com','1234zzxdasd',0,1),(8,'CODUSER-175','jeansyo6','jeanra66d@gmail.com','xtsaedsa',0,1),(9,'CODUSR-74','jeansyo7','jeanra77d@gmail.com','xtsaedsa',0,1),(10,'CODUSR-098114','jeansyo8','jeanra88d@gmail.com','xtsaedsa',0,1),(11,'CODUSR-292998','jeansyo9','jeanra89sd@gmail.com','xtsaedsa',0,1),(12,'CODUSR-609114','jeansyo10','jeanaassssd@gmail.com','xtsaedsa',0,1),(13,'CODUSR-757527','jeansydemo','demo123@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$6AqOXSP+LehOb4o52RWgFw$c/TfkWiCeB2guYTxnEsJXlnn5wm9Uc6UgpcOlVHJNBg',0,1),(14,'CODUSR-010582','syodemo','demosyo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$jw6gI/Fd2ti921El5ei8xg$XFquk4yTd941we4X7iCgxjDm8evokzGmRdVQT8UV4eM',1,1),(15,'CODUSR-375174','syode2mo','demos1yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$Jormpw8pPumt0VZzSf/o6g$AEbb3vSNgQStupA0HtchXkaNPKUj8e3YExF3prtdb2w',1,1),(16,'CODUSR-474536','syode3mo','demos12yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$/yOvm+9vAcw0ZGpcQP+dsA$VU1cPy83LkYwHA5lkYGx2bTOtFW9uzx+cWrNYDy9yXc',1,1),(17,'CODUSR-787037','syode5','demos55@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$RGeXC7N2fXzMD6kIamaOYw$6EFZ/BZuTFc+Bhgmh4UmMGikrME227rgvZ6q6zW34Nw',1,1);
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

-- Dump completed on 2022-06-23 12:58:53
