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
  `classname` varchar(250) COLLATE utf8mb4_bin NOT NULL,
  `recommended` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Lenguaje',10,1,'asdasd',NULL),(2,'MAtematicas',13,0,'sadsadasd',NULL),(3,'sadsdasd',13,0,'sadasdas',NULL),(4,'avion',13,0,'asdasdas',NULL),(5,'biologia',13,0,'sadsadasd',NULL),(6,'matematicalals',13,1,'asdasdas',NULL),(7,'sadsadasdasdasd',13,0,'asdsadasd',NULL),(8,'12321312312',13,0,'sadasdasd',NULL),(9,'asdasda',13,1,'sadasdsaasdsad',NULL),(10,'Nuevo Curso',13,1,'saddsad',NULL),(11,'Nuevo  2',13,0,'sadasdas',NULL),(12,'Sociales',13,0,'asdas',NULL),(13,'Random',13,1,'dsadasd',NULL),(14,'Nuevo curso3',13,1,'MAterias',NULL),(15,'Nuevo curso34',13,1,'MAterias','trigno, algrbran, suma, resta...'),(16,'Nuevo curso34as',13,1,'MAterias','trigno, algrbran, suma, resta...'),(17,'curso creado',13,1,'Números naturales - Primaria - Matemáticas','Elementos de la naturaleza: Tierra, agua, aire y sol., Elementos de la naturaleza: Tierra, agua, aire y sol.'),(18,'asdasdasd',13,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Números naturales');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `link` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `type` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `date` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `filename` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'CLase 12','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',1,1,1,'Fri Jun 24 10:26:29 VET 2022',''),(2,'CLase 14','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',1,1,1,'Fri Jun 24 10:27:00 VET 2022',''),(3,'Clase 123','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FFrame%20347.png?alt=media&token=8f6066db-fb02-435f-8917-c2fa3c35e301',2,6,0,'Wed Jun 29 08:21:47 VET 2022',''),(4,'Clase 125','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fassetlinks.json?alt=media&token=92a48919-a8c1-4e76-bd8f-7a0bc96adf97',1,6,0,'Wed Jun 29 08:22:27 VET 2022',''),(5,'unicornio123','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FRecursos-Secci%C3%B3n2.pdf?alt=media&token=17383d8d-6005-473c-ab43-bf2ea3d7297d',1,9,1,'Wed Jun 29 08:24:05 VET 2022',''),(6,'unicornio12323423','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FClinitify%20Health.png?alt=media&token=e33f3773-313a-4c75-9b75-f6c7ae8f1759',2,6,0,'Wed Jun 29 08:26:18 VET 2022',''),(7,'sg23423','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FOB8a1WaL7enWITQFEBeN_source-code.zip?alt=media&token=76d6a894-bdcb-4f17-8d31-aa7f37950c6e',1,6,0,'Wed Jun 29 08:28:37 VET 2022',''),(8,'asdasdas','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FMe%20encanta%20%C3%A9ste%20video%20jaja%20alchile..mp4?alt=media&token=160791a6-12ae-439b-ba17-a09e3d90a66a',0,6,0,'Wed Jun 29 08:29:47 VET 2022',''),(9,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Wed Jun 29 09:26:40 VET 2022',''),(10,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Thu Jun 30 08:36:36 VET 2022',''),(11,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Thu Jun 30 08:47:03 VET 2022',''),(12,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Fri Jul 01 06:42:14 VET 2022','videoprubea.mp4'),(13,'Clase Clinitify','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fcli-saluddental.png?alt=media&token=4c10b8fe-d8b4-4eec-a4c9-b12c542c06a2',2,6,0,'Fri Jul 01 06:58:05 VET 2022','cli-saluddental.png'),(14,'Social Media','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FDigital%20Investments%20Blue%20Green%20Tech%20Logo.png?alt=media&token=21de2b6e-6ad1-4488-b78d-a9629a3da719',2,12,1,'Fri Jul 01 07:30:46 VET 2022','Digital Investments Blue Green Tech Logo.png'),(15,'Imagen','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FClinitify%20Health3.png?alt=media&token=7bc6200b-d959-41cc-9b87-eef6f57f2056',2,6,0,'Fri Jul 01 07:47:43 VET 2022','Clinitify Health3.png'),(16,'Clase 01','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fwebseno.mp4?alt=media&token=f8e52be6-d3b8-4ceb-940a-08a1128b3789',0,6,0,'Fri Jul 01 08:46:40 VET 2022','webseno.mp4'),(17,'qwe 34','',4,6,0,'Fri Jul 01 09:20:45 VET 2022',''),(18,'asdasd','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fpackage.json?alt=media&token=b7663563-f4d5-4559-8529-42ace3e18765',1,6,1,'Fri Jul 01 09:22:59 VET 2022','package.json'),(19,'sadasd','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FRecursos-Secci%C3%B3n2.pdf?alt=media&token=8bbbf5a5-14d2-4546-8abe-be92a1129186',1,6,1,'Fri Jul 01 09:26:43 VET 2022','Recursos-Sección2.pdf'),(20,'video ','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fwebseno.mp4?alt=media&token=2e5dabe1-f14e-43f5-bda4-9d96577299d0',0,6,1,'Fri Jul 01 09:27:39 VET 2022','webseno.mp4'),(21,'saasdas','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FMe%20encanta%20%C3%A9ste%20video%20jaja%20alchile..mp4?alt=media&token=532ef053-03d1-47ab-bc27-f568cc69ea59',0,6,1,'Fri Jul 01 09:27:57 VET 2022','Me encanta éste video jaja alchile..mp4'),(22,'Video Big','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fpexels-koolshooters-7694001.mp4?alt=media&token=69b17bec-9bdb-4b57-8e5b-17004ab26dbe',0,6,1,'Fri Jul 01 09:31:00 VET 2022','pexels-koolshooters-7694001.mp4');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `course` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,16,2),(8,13,9),(15,9,6);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'CODUSR-0023','User Demo','userdemo@gmail.com','123456',0,1),(2,'CODUSR-2222','jeansyoi','jeanr@gmail.com','1234342',0,1),(4,'CODUSR-213','jeansyo2','jeanrasd@gmail.com','12343425',0,1),(5,'CODUSR-12312','jeansyo3','jeanrassd@gmail.com','1234asdasd',0,1),(6,'CODUSER-0','jeansyo4','jeanra34d@gmail.com','1234xxdasd',0,1),(7,'CODUSER-35','jeansyo5','jeanra35d@gmail.com','1234zzxdasd',0,1),(8,'CODUSER-175','jeansyo6','jeanra66d@gmail.com','xtsaedsa',0,1),(9,'CODUSR-74','jeansyo7','jeanra77d@gmail.com','xtsaedsa',0,1),(10,'CODUSR-098114','jeansyo8','jeanra88d@gmail.com','xtsaedsa',0,1),(11,'CODUSR-292998','jeansyo9','jeanra89sd@gmail.com','xtsaedsa',0,1),(12,'CODUSR-609114','jeansyo10','jeanaassssd@gmail.com','xtsaedsa',0,1),(13,'CODUSR-757527','JEAN RADA','demo123@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$DU2LAKZqs5wfUiJbVNKkLQ$RsCHiUKs+YK59+8rgQ96KuvmbulWhnlLUbxr+PonLbU',0,1),(14,'CODUSR-010582','syodemo','demosyo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$jw6gI/Fd2ti921El5ei8xg$XFquk4yTd941we4X7iCgxjDm8evokzGmRdVQT8UV4eM',1,1),(15,'CODUSR-375174','syode2mo','demos1yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$Jormpw8pPumt0VZzSf/o6g$AEbb3vSNgQStupA0HtchXkaNPKUj8e3YExF3prtdb2w',1,1),(16,'CODUSR-474536','syode3mo','demos12yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$/yOvm+9vAcw0ZGpcQP+dsA$VU1cPy83LkYwHA5lkYGx2bTOtFW9uzx+cWrNYDy9yXc',1,1),(17,'CODUSR-787037','syode5','demos55@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$RGeXC7N2fXzMD6kIamaOYw$6EFZ/BZuTFc+Bhgmh4UmMGikrME227rgvZ6q6zW34Nw',1,1),(18,'CODUSR-323830','readsadas','radajean36@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$hIdRg4Qon+6yzd0ar1em/Q$hTsuxS7pWmpDVvMbewcVFb7zTR8w8sbK4j1ehw0tKp4',0,1),(19,'CODUSR-147335','123123','radajean336@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$qVbkrEqPiwIPg53DVSFNAA$P9SBqTAi8/A3a4d918LnuyOalSWUxW8YzHGGZ4W1yK4',0,1),(20,'CODUSR-628093','sdasdas','radaj23ean36@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$jM9HmrwquPa/9iKlf/99xA$jneJhMMk97WI5mvFXF2IXHpVZD/2XRLm+z5HC8YuR9M',0,1),(21,'CODUSR-824255','nuevouser','nuevo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$lnUuP+LVAu9LYWMQyCNK5A$s3Wf9EJjgv/vynXxIbY7c102FgMFcBswqctBGAUVIP4',0,1),(22,'CODUSR-027294','Severo Cruz','servero@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$ypRdet+O00dEtMBOJHG6nQ$KOhu4ZYYQIjFXLT8up4nckUDmxUrrEcjpLg47fZ1Pik',1,1),(23,'CODUSR-196198','lolanda','lolanda@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$940VNtrv853R89Dl7gKR+Q$q6imPMvk0T2OHH3EMqL+5z0mZC57f0S+MEvUI0o3xAQ',0,1);
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

-- Dump completed on 2022-07-06  9:59:19
