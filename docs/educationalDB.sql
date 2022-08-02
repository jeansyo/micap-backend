-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 54.82.158.125    Database: educational
-- ------------------------------------------------------
-- Server version	5.7.38

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Lenguaje',10,1,'asdasd',NULL),(2,'MAtematicas',13,0,'sadsadasd',NULL),(3,'sadsdasd',13,0,'sadasdas',NULL),(4,'avion',13,0,'asdasdas',NULL),(5,'biologia',13,0,'sadsadasd',NULL),(6,'matematicalals',13,1,'asdasdas',NULL),(7,'sadsadasdasdasd',13,0,'asdsadasd',NULL),(8,'12321312312',13,0,'sadasdasd',NULL),(9,'asdasda',13,1,'sadasdsaasdsad',NULL),(10,'Nuevo Curso',13,1,'saddsad',NULL),(11,'Nuevo  2',13,0,'sadasdas',NULL),(12,'Sociales',13,0,'asdas',NULL),(13,'Random',13,1,'dsadasd',NULL),(14,'Nuevo curso3',13,1,'MAterias',NULL),(15,'Nuevo curso34',13,1,'MAterias','trigno, algrbran, suma, resta...'),(16,'Nuevo curso34as',13,1,'MAterias','trigno, algrbran, suma, resta...'),(17,'curso creado',13,1,'Números naturales - Primaria - Matemáticas','Elementos de la naturaleza: Tierra, agua, aire y sol., Elementos de la naturaleza: Tierra, agua, aire y sol.'),(18,'asdasdasd',13,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Números naturales'),(19,'MAT 312',23,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Adición y Sustracción de números naturales, Números naturales'),(20,'matematicas 2',24,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Números naturales'),(21,'MAT-312',23,1,'Secundaria - Matemáticas - Medidas de peso y capacidad: Kilo y litro en la práctica cotidiana.','Figuras geométricas, Cuerpos geométricos en el entorno natural y su diferencia con las figuras geométricas., Líneas rectas, curvas, cerradas y abiertas'),(22,'Biologia 2',24,1,'Secundaria - Ciencias Naturales - Sistema respiratorio','Cuerpo humano, prácticas de salud e higiene en la familia y comunidad.'),(23,'MAtematicas paralelo C',23,0,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Ciclo vital de los seres vivos, cuidado y protección en armonía y equilibrio con la Madre Tierra., Elementos de la naturaleza: Tierra, agua, aire y sol., Cuerpo humano, prácticas de salud e higiene en la familia y comunidad., Números naturales'),(24,'ASdasd',29,1,'Primaria - Matemáticas - Números naturales','Adición y Sustracción de números naturales'),(25,'Mamtematicas',35,1,'Primaria - Matemáticas - Números naturales',''),(26,'Cómo no jugar lol ',36,1,'Secundaria - Comunicación y Lenguaje - Medios de comunicación masiva y personal','Homónimas y parónimas, Acento y su clasificación, Medios de comunicación masiva y personal'),(27,'matematicas',13,0,'Primaria - Matemáticas - Números naturales','Adición y Sustracción de números naturales'),(28,'matematicas basicas',13,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','Números naturales'),(29,'Lenguaje 2',35,1,'Primaria - Comunicación y Lenguajes - Formas de comunicación (oral, corporal, simbólica y gestual)',''),(30,'Matematicas2',35,1,'Primaria - Matemáticas - Adición y Sustracción de números naturales','');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `downloads`
--

DROP TABLE IF EXISTS `downloads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `downloads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `material` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `downloads`
--

LOCK TABLES `downloads` WRITE;
/*!40000 ALTER TABLE `downloads` DISABLE KEYS */;
INSERT INTO `downloads` VALUES (1,6,28,8),(2,6,28,9),(3,6,28,10);
/*!40000 ALTER TABLE `downloads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluations`
--

DROP TABLE IF EXISTS `evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(11) NOT NULL,
  `start` varchar(245) COLLATE utf8mb4_bin NOT NULL,
  `end` varchar(245) COLLATE utf8mb4_bin NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluations`
--

LOCK TABLES `evaluations` WRITE;
/*!40000 ALTER TABLE `evaluations` DISABLE KEYS */;
INSERT INTO `evaluations` VALUES (1,6,'123-23-23','123-3123-12',0),(2,6,'123-23-23','123-3123-12',0),(3,6,'123-23-23','123-3123-12',0),(4,6,'Thu Jul 07 2022 20:00:00 GMT-0400 (hora de Venezuela)','Tue Jul 05 2022 20:00:00 GMT-0400 (hora de Venezuela)',1),(5,6,'Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)','Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)',1),(6,6,'Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)','Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)',1),(7,6,'Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)','Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)',1),(8,6,'Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)','Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)',1),(9,6,'Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)','Wed Dec 31 1969 20:00:00 GMT-0400 (hora de Venezuela)',1),(10,6,'Mon Jul 25 2022 20:00:00 GMT-0400 (hora de Venezuela)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Venezuela)',0),(11,6,'Invalid Date','Invalid Date',0),(12,20,'Wed Jul 27 2022 20:00:00 GMT-0400 (hora de Bolivia)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Bolivia)',0),(13,20,'Wed Jul 27 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(14,20,'Invalid Date','Invalid Date',0),(15,28,'Mon Jul 25 2022 20:00:00 GMT-0400 (hora de Bolivia)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(16,25,'Tue Jul 26 2022 20:00:00 GMT-0400 (hora de Bolivia)','Wed Jul 27 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(17,6,'Invalid Date','Invalid Date',0),(18,6,'Thu Jul 28 2022 20:00:00 GMT-0400 (hora de Bolivia)','Thu Aug 04 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(19,6,'Tue Jul 26 2022 20:00:00 GMT-0400 (hora de Venezuela)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Venezuela)',1),(20,18,'Mon Jul 18 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(21,6,'Thu Jul 21 2022 20:00:00 GMT-0400 (hora de Bolivia)','Sat Aug 06 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(22,6,'Wed Jun 29 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(23,6,'Invalid Date','Invalid Date',1),(24,6,'Tue Jul 26 2022 20:00:00 GMT-0400 (hora de Venezuela)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Venezuela)',1),(25,25,'Invalid Date','Invalid Date',0),(26,28,'Mon Jul 18 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Aug 05 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(27,25,'Invalid Date','Invalid Date',1),(28,29,'Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)','Sat Jul 30 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(29,29,'Thu Jul 28 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(30,30,'Invalid Date','Invalid Date',0),(31,30,'Invalid Date','Invalid Date',0),(32,30,'Thu Jul 28 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',0),(33,30,'Invalid Date','Invalid Date',1),(34,30,'Thu Jul 28 2022 20:00:00 GMT-0400 (hora de Bolivia)','Fri Jul 29 2022 20:00:00 GMT-0400 (hora de Bolivia)',1),(35,25,'Wed Jul 13 2022 20:00:00 GMT-0400 (hora de Bolivia)','Wed Jul 13 2022 20:00:00 GMT-0400 (hora de Bolivia)',1);
/*!40000 ALTER TABLE `evaluations` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'CLase 12','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',1,1,1,'Fri Jun 24 10:26:29 VET 2022',''),(2,'CLase 14','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',1,1,1,'Fri Jun 24 10:27:00 VET 2022',''),(3,'Clase 123','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FFrame%20347.png?alt=media&token=8f6066db-fb02-435f-8917-c2fa3c35e301',2,6,0,'Wed Jun 29 08:21:47 VET 2022',''),(4,'Clase 125','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fassetlinks.json?alt=media&token=92a48919-a8c1-4e76-bd8f-7a0bc96adf97',1,6,0,'Wed Jun 29 08:22:27 VET 2022',''),(5,'unicornio123','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FRecursos-Secci%C3%B3n2.pdf?alt=media&token=17383d8d-6005-473c-ab43-bf2ea3d7297d',1,9,1,'Wed Jun 29 08:24:05 VET 2022',''),(6,'unicornio12323423','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FClinitify%20Health.png?alt=media&token=e33f3773-313a-4c75-9b75-f6c7ae8f1759',2,6,0,'Wed Jun 29 08:26:18 VET 2022',''),(7,'sg23423','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FOB8a1WaL7enWITQFEBeN_source-code.zip?alt=media&token=76d6a894-bdcb-4f17-8d31-aa7f37950c6e',1,6,0,'Wed Jun 29 08:28:37 VET 2022',''),(8,'asdasdas','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FMe%20encanta%20%C3%A9ste%20video%20jaja%20alchile..mp4?alt=media&token=160791a6-12ae-439b-ba17-a09e3d90a66a',0,6,1,'Wed Jun 29 08:29:47 VET 2022',''),(9,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,1,'Wed Jun 29 09:26:40 VET 2022',''),(10,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,1,'Thu Jun 30 08:36:36 VET 2022',''),(11,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Thu Jun 30 08:47:03 VET 2022',''),(12,'qwe 34','https://www.youtube.com/watch?v=-2J7TCOzORA&list=RDMM-2J7TCOzORA&start_radio=1',0,6,0,'Fri Jul 01 06:42:14 VET 2022','videoprubea.mp4'),(13,'Clase Clinitify','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fcli-saluddental.png?alt=media&token=4c10b8fe-d8b4-4eec-a4c9-b12c542c06a2',2,6,0,'Fri Jul 01 06:58:05 VET 2022','cli-saluddental.png'),(14,'Social Media','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FDigital%20Investments%20Blue%20Green%20Tech%20Logo.png?alt=media&token=21de2b6e-6ad1-4488-b78d-a9629a3da719',2,12,1,'Fri Jul 01 07:30:46 VET 2022','Digital Investments Blue Green Tech Logo.png'),(15,'Imagen','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FClinitify%20Health3.png?alt=media&token=7bc6200b-d959-41cc-9b87-eef6f57f2056',2,6,0,'Fri Jul 01 07:47:43 VET 2022','Clinitify Health3.png'),(16,'Clase 01','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fwebseno.mp4?alt=media&token=f8e52be6-d3b8-4ceb-940a-08a1128b3789',0,6,0,'Fri Jul 01 08:46:40 VET 2022','webseno.mp4'),(17,'qwe 34','',4,6,0,'Fri Jul 01 09:20:45 VET 2022',''),(18,'asdasd','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fpackage.json?alt=media&token=b7663563-f4d5-4559-8529-42ace3e18765',1,6,1,'Fri Jul 01 09:22:59 VET 2022','package.json'),(19,'sadasd','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FRecursos-Secci%C3%B3n2.pdf?alt=media&token=8bbbf5a5-14d2-4546-8abe-be92a1129186',1,6,1,'Fri Jul 01 09:26:43 VET 2022','Recursos-Sección2.pdf'),(20,'video ','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fwebseno.mp4?alt=media&token=2e5dabe1-f14e-43f5-bda4-9d96577299d0',0,6,1,'Fri Jul 01 09:27:39 VET 2022','webseno.mp4'),(21,'saasdas','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FMe%20encanta%20%C3%A9ste%20video%20jaja%20alchile..mp4?alt=media&token=532ef053-03d1-47ab-bc27-f568cc69ea59',0,6,1,'Fri Jul 01 09:27:57 VET 2022','Me encanta éste video jaja alchile..mp4'),(22,'Video Big','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fpexels-koolshooters-7694001.mp4?alt=media&token=69b17bec-9bdb-4b57-8e5b-17004ab26dbe',0,6,1,'Fri Jul 01 09:31:00 VET 2022','pexels-koolshooters-7694001.mp4'),(23,'sasadas','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fmoyo1.JPG?alt=media&token=9edf01f7-69b3-43aa-bd90-48a085443b6f',2,19,0,'Wed Jul 06 15:36:24 UTC 2022','moyo1.JPG'),(24,'clase 1 numeros IA pdf','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FTutorial-IA-Programacion.pdf?alt=media&token=069c057f-5fd4-4ea4-965c-5dfebec726e1',1,20,1,'Wed Jul 06 19:45:42 UTC 2022','Tutorial-IA-Programacion.pdf'),(25,'numeros odbc','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2F2.conectar%20odbc.mp4?alt=media&token=4ca21485-1c72-44ec-9e44-2f79b7027f58',0,20,1,'Wed Jul 06 21:04:34 UTC 2022','2.conectar odbc.mp4'),(26,'numeros reales','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FDibujo3.PNG?alt=media&token=eaf26c04-ea2c-49dd-96eb-bd2f269263c0',2,20,1,'Fri Jul 08 01:48:52 UTC 2022','Dibujo3.PNG'),(27,'adicion','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FA%20Smarter%20Way%20to%20Learn%20JavaScript%20-%20The%20new%20approach%20that%20uses%20technology%20to%20cut%20your%20effort%20in%20half.pdf?alt=media&token=dfebbf62-72db-42af-be42-b3c16eec16cb',1,20,1,'Fri Jul 08 05:09:38 UTC 2022','A Smarter Way to Learn JavaScript - The new approach that uses technology to cut your effort in half.pdf'),(28,'Imagen 12','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2Fpexels-fauxels-3183148.jpg?alt=media&token=8f21ffc5-7882-4d23-9220-6f4e4cc379e7',2,19,1,'Fri Jul 08 23:18:08 UTC 2022','pexels-fauxels-3183148.jpg'),(29,'del 1 al 10 ','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FAprende%20a%20contar%20los%20n%C3%BAmeros%20del%201%20al%2010%20con%20los%20dedos%20-%20Estrategias%20de%20aprendizaje%20para%20ni%C3%B1os.mp4?alt=media&token=964d53be-2893-4856-be0c-f8cf2c20722e',0,25,1,'Mon Jul 25 20:23:50 UTC 2022','Aprende a contar los números del 1 al 10 con los dedos - Estrategias de aprendizaje para niños.mp4'),(30,'11 al 20','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2F11%20al%2020.png?alt=media&token=97772537-bec3-47c1-8e2a-2623f09041be',2,25,1,'Tue Jul 26 03:13:00 UTC 2022','11 al 20.png'),(31,'20 al 30','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FAdivina%20los%20n%C3%BAmeros%20del%2020%20al%2030%20-%20Aprende%20a%20escribir%20y%20leer%20los%20n%C3%BAmeros%20del%201%20al%20100.mp4?alt=media&token=da473f31-51ae-41c3-8471-085ab760515e',0,25,1,'Tue Jul 26 03:20:51 UTC 2022','Adivina los números del 20 al 30 - Aprende a escribir y leer los números del 1 al 100.mp4'),(32,'40 al 50','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FAdivina%20los%20n%C3%BAmeros%20del%2020%20al%2030%20-%20Aprende%20a%20escribir%20y%20leer%20los%20n%C3%BAmeros%20del%201%20al%20100.mp4?alt=media&token=6cfb64e3-8ff8-46cf-a6d0-a0ff54c9cee6',0,25,0,'Tue Jul 26 22:07:08 UTC 2022','Adivina los números del 20 al 30 - Aprende a escribir y leer los números del 1 al 100.mp4'),(33,'verbal','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FAdivina%20los%20n%C3%BAmeros%20del%2020%20al%2030%20-%20Aprende%20a%20escribir%20y%20leer%20los%20n%C3%BAmeros%20del%201%20al%20100.mp4?alt=media&token=6fac6215-6ca0-4a5f-8e1c-534ded424693',0,29,1,'Fri Jul 29 11:53:16 UTC 2022','Adivina los números del 20 al 30 - Aprende a escribir y leer los números del 1 al 100.mp4'),(34,'verbal2','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2F11%20al%2020.png?alt=media&token=d122cd79-c827-4dcc-a639-b1cbbb708c1a',2,29,1,'Fri Jul 29 11:54:39 UTC 2022','11 al 20.png'),(35,'video 1','https://firebasestorage.googleapis.com/v0/b/lmsbm-28298.appspot.com/o/static%2FAdivina%20los%20n%C3%BAmeros%20del%2020%20al%2030%20-%20Aprende%20a%20escribir%20y%20leer%20los%20n%C3%BAmeros%20del%201%20al%20100.mp4?alt=media&token=c739b4ac-5afc-42eb-8054-3354d43b34f5',0,30,1,'Fri Jul 29 19:34:59 UTC 2022','Adivina los números del 20 al 30 - Aprende a escribir y leer los números del 1 al 100.mp4');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluation` int(11) NOT NULL,
  `firstanswer` varchar(545) COLLATE utf8mb4_bin NOT NULL,
  `secondanswer` varchar(545) COLLATE utf8mb4_bin NOT NULL,
  `thirdanswer` varchar(545) COLLATE utf8mb4_bin NOT NULL,
  `answer` varchar(545) COLLATE utf8mb4_bin NOT NULL,
  `question` varchar(545) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,1,'firstanswer','secondanswer','thirdanswer','thirdanswer','question'),(2,1,'firstanswer1','secondanswer1','thirdanswer1','thirdanswer1','question2'),(3,1,'firstanswer','secondanswer','thirdanswer','answer','question'),(4,5,'firstanswer','secondanswer','thirdanswer','answer','question'),(5,5,'firstanswer','secondanswer','thirdanswer','answer','question'),(6,8,'mondo','mundo','mundial','firstAnswer','hola mundo?'),(7,8,'1mondo','2mundo','5mundial','thirdAnswer','aho1la mundo?'),(8,9,'1mondo','2mundo','5mundial','thirdAnswer','aho1la mundo?'),(9,9,'mondo','mundo','mundial','firstAnswer','hola mundo?'),(10,10,'1','2','3','thirdAnswer','aho1la mundo?'),(11,10,'1','2','3','thirdAnswer','Evaluacion'),(12,11,'mondo','mundo','mundial','mundial','hola mundo?'),(13,11,'1mondo','2mundo','5mundial','5mundial','aho1la mundo?'),(14,12,'1','2','3','1','uno?'),(15,12,'1','2','3','2','dos?'),(16,12,'1','2','3','3','tres?'),(17,13,'1','2','3','1','uno?'),(18,13,'1','2','3','2','dos?'),(19,13,'1','2','3','3','tres?'),(20,15,'1','2','3','1','uno?'),(21,15,'1','2','3','3','tres?'),(22,15,'1','2','3','2','dos?'),(23,16,'1','2','3','3','cantidad de puntos   ...'),(24,17,'123','2132','asdasdas','123','hola mindo'),(25,19,'123','1234','12345','123','Prueba'),(26,21,'1','2','3','2','dos?'),(27,21,'1','2','3','1','uno?'),(28,24,'1','2','3','1','Prueba1 '),(29,25,'aaa','bbb','ccc','aaa','asdasd'),(30,25,'www','eee','rrr','www','qqqqqqqqqqq'),(31,26,'1','2','3','1','uno?'),(32,27,'1','2','4','4','cantidad de guiones: ----'),(33,27,'3','5','9','5','cantidad de  barras: \\\\\\\\\\'),(34,28,'1','2','3','2','asdadasd'),(35,28,'4','5','6','6','sdfdsfdsfsdf'),(36,29,'1','2','3','1','asdasdsa'),(37,29,'3','4','5','5','fgfhgfjhgj'),(38,32,'1','2','3','1','asdasdsad'),(39,32,'3','4','5','3','hfhfghfh'),(40,33,'0','6','4','6','1+5'),(41,33,'1','4','6','4','5-1'),(42,33,'4','6','8','4','2+2'),(43,34,'1','2','3','1','1 + 0'),(44,35,'Conquistador','Libertador','Presidente','Conquistador','Quien era critobal Colon');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scores`
--

DROP TABLE IF EXISTS `scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `evaluation` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scores`
--

LOCK TABLES `scores` WRITE;
/*!40000 ALTER TABLE `scores` DISABLE KEYS */;
INSERT INTO `scores` VALUES (1,13,1,50),(4,13,10,0),(5,28,5,0),(6,28,8,0),(7,28,10,0),(8,26,13,33),(9,28,17,0),(10,28,9,0),(11,28,13,33),(12,28,19,100),(13,28,21,100),(14,26,5,0),(15,13,24,100),(16,26,8,0),(17,26,24,100),(18,28,24,100),(19,34,16,100),(20,34,25,0),(21,26,15,33),(22,26,26,100),(23,13,26,100),(24,28,15,66),(25,28,26,100),(26,34,27,100),(27,34,28,50),(28,34,29,50),(29,34,33,66),(30,34,34,100);
/*!40000 ALTER TABLE `scores` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,16,2),(8,13,9),(15,28,6),(16,26,20),(18,26,22),(19,28,19),(20,27,19),(21,34,25),(22,28,20),(23,26,6),(24,26,28),(25,28,18),(26,28,28),(27,37,25),(28,34,29),(29,34,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'CODUSR-0023','User Demo','userdemo@gmail.com','123456',0,1),(2,'CODUSR-2222','jeansyoi','jeanr@gmail.com','1234342',0,1),(4,'CODUSR-213','jeansyo2','jeanrasd@gmail.com','12343425',0,1),(5,'CODUSR-12312','jeansyo3','jeanrassd@gmail.com','1234asdasd',0,1),(6,'CODUSER-0','jeansyo4','jeanra34d@gmail.com','1234xxdasd',0,1),(7,'CODUSER-35','jeansyo5','jeanra35d@gmail.com','1234zzxdasd',0,1),(8,'CODUSER-175','jeansyo6','jeanra66d@gmail.com','xtsaedsa',0,1),(9,'CODUSR-74','jeansyo7','jeanra77d@gmail.com','xtsaedsa',0,1),(10,'CODUSR-098114','jeansyo8','jeanra88d@gmail.com','xtsaedsa',0,1),(11,'CODUSR-292998','jeansyo9','jeanra89sd@gmail.com','xtsaedsa',0,1),(12,'CODUSR-609114','jeansyo10','jeanaassssd@gmail.com','xtsaedsa',0,1),(13,'CODUSR-757527','JEAN RADA','demo123@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$DU2LAKZqs5wfUiJbVNKkLQ$RsCHiUKs+YK59+8rgQ96KuvmbulWhnlLUbxr+PonLbU',0,1),(14,'CODUSR-010582','syodemo','demosyo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$jw6gI/Fd2ti921El5ei8xg$XFquk4yTd941we4X7iCgxjDm8evokzGmRdVQT8UV4eM',1,1),(15,'CODUSR-375174','syode2mo','demos1yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$Jormpw8pPumt0VZzSf/o6g$AEbb3vSNgQStupA0HtchXkaNPKUj8e3YExF3prtdb2w',1,1),(16,'CODUSR-474536','syode3mo','demos12yo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$/yOvm+9vAcw0ZGpcQP+dsA$VU1cPy83LkYwHA5lkYGx2bTOtFW9uzx+cWrNYDy9yXc',1,1),(17,'CODUSR-787037','syode5','demos55@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$RGeXC7N2fXzMD6kIamaOYw$6EFZ/BZuTFc+Bhgmh4UmMGikrME227rgvZ6q6zW34Nw',1,1),(18,'CODUSR-323830','readsadas','radajean36@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$hIdRg4Qon+6yzd0ar1em/Q$hTsuxS7pWmpDVvMbewcVFb7zTR8w8sbK4j1ehw0tKp4',0,1),(19,'CODUSR-147335','123123','radajean336@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$qVbkrEqPiwIPg53DVSFNAA$P9SBqTAi8/A3a4d918LnuyOalSWUxW8YzHGGZ4W1yK4',0,1),(20,'CODUSR-628093','sdasdas','radaj23ean36@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$jM9HmrwquPa/9iKlf/99xA$jneJhMMk97WI5mvFXF2IXHpVZD/2XRLm+z5HC8YuR9M',0,1),(21,'CODUSR-824255','nuevouser','nuevo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$lnUuP+LVAu9LYWMQyCNK5A$s3Wf9EJjgv/vynXxIbY7c102FgMFcBswqctBGAUVIP4',0,1),(22,'CODUSR-027294','Severo Cruz','servero@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$ypRdet+O00dEtMBOJHG6nQ$KOhu4ZYYQIjFXLT8up4nckUDmxUrrEcjpLg47fZ1Pik',1,1),(23,'CODUSR-196198','lolanda','lolanda@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$940VNtrv853R89Dl7gKR+Q$q6imPMvk0T2OHH3EMqL+5z0mZC57f0S+MEvUI0o3xAQ',0,1),(24,'CODUSR-669095','severo cruz nina','severo_86@hotmail.com','$argon2id$v=19$m=1024,t=1,p=1$2Oho33LEhAq10Y3iQ9V3qQ$DwUCiDi6Xjw8RLGsHHaMizgVkLn8uF+DIrnYqiMh85I',0,1),(25,'CODUSR-896214','syode55','demos555@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$OT0z8gO6wMQ7CTaSasRnlg$kvx2hzbFwavAVltxPvIJKTrymYNRkpAryVnyDX53Esc',1,1),(26,'CODUSR-608271','sevevero','sevicocruz21@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$Y+8cEjB4Lq7+PSJIHh0grw$h8YJWdCUB6ODGW9JK+Yg15Ug8YRiFe2bqCdenMsRgWQ',1,1),(27,'CODUSR-743194','Jean Syo','jeansyo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$9QBSWCEkaMtarutVTPdX/Q$JEC/r45GZOXs1cqMSavsUUAohz8+kQ9TwTRcHyr3GH4',1,1),(28,'CODUSR-145242','JEAN Rada','jean@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$sHUJqShPzDMNcCZrmZl8Fg$uPVGy7Cs/KREWxOWIolEzaFKB3HKu015pYe5fR9PSWU',1,1),(29,'CODUSR-687142','Ronal Rada','ronal@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$1BF6r7nhK9Uoqc7y3oiRUg$W5BZY1xOtpWrwgyVDS4Et6hQcCb6qVAjl2EJdW2/uLM',0,1),(30,'CODUSR-551752','jazmin','jazmin@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$LhqRILwMxb+8XSQurSUYSw$s+6xZ/2pSB2xjregHWDwlCA5A+zq5Z04Ejn/VKQtglc',1,1),(31,'CODUSR-396404','jean ','ejemplo@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$m0OdMhqG2wxUFNNbJCJrow$kYS9uLKqhKW6jm7UlcMGBR0mNBbB30CEWcckcGTRZ0Y',1,1),(32,'CODUSR-013202','maxi ','maxico@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$IreMrdVrgMSzCg0xidCB7g$d6rLuzcVZ11NCY5pXQ2GMgmeY8ICn6mNhk60ENIP4qk',1,1),(33,'CODUSR-054372','Alexander ','11119manuelpoma@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$9c1EknEvm1Jwu6Iw98XjGg$HrrxVJY6egUEG8dQqCLTSl8QIxVmyqXULoV7HkrbrSo',1,1),(34,'CODUSR-357341','Alexander Mamani','1119manuelpoma@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$yYkuiSNjeyAf3lVELuIepg$1UaWYB+qd+t1vGrwYF2kdSB2K0/Q+EPIEeIy0pcBLP0',1,1),(35,'CODUSR-807789','Manuel Poma','alexander.mamani.poma@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$ZVw40B1lG1GhxZ1EK2+MxA$RrOD4PiG5/DEjzEXNCZZ4Rg9TXvdyOxKr5j7foi6XPY',0,1),(36,'CODUSR-732289','Gadiel Bacarreza ','gadiel.bacarreza@ucb.edu.bo','$argon2id$v=19$m=1024,t=1,p=1$dd8XQYlC7r5aXcGAPNRsJQ$aSr8USx/8C8B/BcZQRPXifg5SQJzZXiDONA8m4IsPTU',0,1),(37,'CODUSR-035150','Gadiel','gadi77altair.gabg@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$YvRvVWfXFMzdcB+bdyDaOQ$G5BX4xMlmFrlD2GC1cM2KLtOysmXpBemPjLsH4yPP7c',1,1),(38,'CODUSR-398330','prueba ','prueba@gmail.com','$argon2id$v=19$m=1024,t=1,p=1$hceb37XZMhdBRG5Ny/+8Kw$RMT7oDs1pVjvvWMVgCiO+0QBI+FaMD+Xzdy7kL8XYJo',1,1);
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

-- Dump completed on 2022-08-02 17:22:12
