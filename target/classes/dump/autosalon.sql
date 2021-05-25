-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: autosalone
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bodytype`
--

DROP TABLE IF EXISTS `bodytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bodytype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodytype`
--

LOCK TABLES `bodytype` WRITE;
/*!40000 ALTER TABLE `bodytype` DISABLE KEYS */;
INSERT INTO `bodytype` VALUES (1,'Седан'),(2,'Хетчбек (3 двери)'),(3,'Хетчбек (5 дверей)'),(4,'Родстер'),(6,'Пикап'),(7,'Купе'),(8,'Кабриолет'),(9,'Внедорожник (3 двери)'),(10,'Внедорожник (5 дверей)'),(11,'Универсал');
/*!40000 ALTER TABLE `bodytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `patronymic` varchar(50) NOT NULL,
  `passport` varchar(100) NOT NULL,
  `phone` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES (1,'Яна','Долихина','Александровна','2214 380490',732069014),(2,'Кирилл','Горохов','Владимирович','1403 666999',732409503),(3,'Алексей','Серебряков','Алексеевич','4312 142355',910763247),(4,'Максим','Востряков','Юрьевич','1235 235355',910123787);
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seria` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `buyer_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `instanceModel_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_contract_buyer1_idx` (`buyer_id`),
  KEY `fk_contract_employee1_idx` (`employee_id`),
  KEY `fk_contract_instanceModel1_idx` (`instanceModel_id`),
  CONSTRAINT `fk_contract_buyer1` FOREIGN KEY (`buyer_id`) REFERENCES `buyer` (`id`),
  CONSTRAINT `fk_contract_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_contract_instanceModel1` FOREIGN KEY (`instanceModel_id`) REFERENCES `instancemodel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'423456','3465','2021-05-20',3,1,5),(2,'345235','3256','2023-08-20',4,2,6),(3,'687563','3267','2014-02-20',1,3,2),(4,'347335','2377','2003-04-20',2,2,3),(5,'252355','2523','2010-12-12',4,3,1),(6,'578675','3456','2012-06-10',2,2,4);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealer`
--

DROP TABLE IF EXISTS `dealer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dealer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` int NOT NULL,
  `director` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealer`
--

LOCK TABLES `dealer` WRITE;
/*!40000 ALTER TABLE `dealer` DISABLE KEYS */;
INSERT INTO `dealer` VALUES (1,'LADA БЦР Моторс','г. Нижний Новогород, ул. Новикова-Прибоя, д. 2',312389429,'Олег Г.А.'),(2,'Nissan Нижегородец','г. Нижний Новгород, Комсомольское ш., д. 14А',312003769,'Михаил К.А.'),(3,'Тайота Центр Нижний Новогород','г. Нижний Новгород, Московское шоссе, 94А',322350920,'Александр Д.А.');
/*!40000 ALTER TABLE `dealer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `patronymic` varchar(50) NOT NULL,
  `numberService` int NOT NULL,
  `dealer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_dealer1_idx` (`dealer_id`),
  CONSTRAINT `fk_employee_dealer1` FOREIGN KEY (`dealer_id`) REFERENCES `dealer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Вадим','Долихин','Александрович',101,1),(2,'Юлия','Пыжикова','Алексеевна',102,3),(3,'Тимофей','Избалович','Вадимович',103,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `engine`
--

DROP TABLE IF EXISTS `engine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `engine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `engine`
--

LOCK TABLES `engine` WRITE;
/*!40000 ALTER TABLE `engine` DISABLE KEYS */;
INSERT INTO `engine` VALUES (1,'1JZ-GE (Бензин)'),(2,'1JZ-GTE (Бензин)'),(3,'1JZ-FSE (Бензин)'),(4,'2JZ-GE (Бензин)'),(5,'2JZ-GTE (Бензин)'),(6,'EJ (Бензин)');
/*!40000 ALTER TABLE `engine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,'Базовая'),(3,'Средняя'),(4,'Премиум'),(5,'Люкс');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instanceengine`
--

DROP TABLE IF EXISTS `instanceengine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instanceengine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `power` varchar(50) NOT NULL,
  `volume` varchar(50) NOT NULL,
  `km` varchar(50) NOT NULL,
  `engine_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instanceEngine_engine1_idx` (`engine_id`),
  CONSTRAINT `fk_instanceEngine_engine1` FOREIGN KEY (`engine_id`) REFERENCES `engine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instanceengine`
--

LOCK TABLES `instanceengine` WRITE;
/*!40000 ALTER TABLE `instanceengine` DISABLE KEYS */;
INSERT INTO `instanceengine` VALUES (1,'180 л.с.','2492 см3','235 Нм',1),(2,'280 л.с.','2492 см3','360 Нм',2),(3,'200 л.с.','2492 см3','250 Нм',3),(4,'215 - 230 л.с.','2997 см3','280 - 300 Нм',4),(5,'275 - 330 л.с.','2997 см3','425 - 440 Нм',5),(6,'260 л.с.','2832 см3','240 Hм',6);
/*!40000 ALTER TABLE `instanceengine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instancemodel`
--

DROP TABLE IF EXISTS `instancemodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instancemodel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `color` varchar(50) NOT NULL,
  `cost` decimal(19,3) NOT NULL,
  `image` varchar(100) NOT NULL,
  `equipment_id` int NOT NULL,
  `model_id` int NOT NULL,
  `pts_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instanceModel_equipment1_idx` (`equipment_id`),
  KEY `fk_instanceModel_model1_idx` (`model_id`),
  KEY `fk_instancemodel_pts1_idx` (`pts_id`),
  CONSTRAINT `fk_instanceModel_equipment1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`),
  CONSTRAINT `fk_instanceModel_model1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`),
  CONSTRAINT `fk_instancemodel_pts1` FOREIGN KEY (`pts_id`) REFERENCES `pts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instancemodel`
--

LOCK TABLES `instancemodel` WRITE;
/*!40000 ALTER TABLE `instancemodel` DISABLE KEYS */;
INSERT INTO `instancemodel` VALUES (1,'AS214234234','Белый',400.000,'image/1.png',1,1,1),(2,'FG252523355','Белый',900.000,'image/2.png',3,4,2),(3,'DS238567356','Чёрный',580.000,'image/3.png',1,3,3),(4,'JK827346256','Серый',480.000,'image/4.png',3,2,4),(5,'TR234623656','Белый',500.000,'image/5.png',3,5,5),(6,'OP832883955','Чёрный',120.000,'image/6.png',1,6,6);
/*!40000 ALTER TABLE `instancemodel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instancetransmission`
--

DROP TABLE IF EXISTS `instancetransmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instancetransmission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numberGears` int NOT NULL,
  `transmission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instanceTransmission_transmission1_idx` (`transmission_id`),
  CONSTRAINT `fk_instanceTransmission_transmission1` FOREIGN KEY (`transmission_id`) REFERENCES `transmission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instancetransmission`
--

LOCK TABLES `instancetransmission` WRITE;
/*!40000 ALTER TABLE `instancetransmission` DISABLE KEYS */;
INSERT INTO `instancetransmission` VALUES (1,5,1),(2,5,1),(3,6,2),(4,6,2),(5,7,4),(6,5,1);
/*!40000 ALTER TABLE `instancetransmission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marka`
--

DROP TABLE IF EXISTS `marka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marka` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marka`
--

LOCK TABLES `marka` WRITE;
/*!40000 ALTER TABLE `marka` DISABLE KEYS */;
INSERT INTO `marka` VALUES (1,'Toyota'),(2,'Nissan'),(3,'Mitsubishi'),(4,'Subaru'),(5,'Mazda'),(6,'Lada'),(7,'BMW');
/*!40000 ALTER TABLE `marka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `marka_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_model_marka_idx` (`marka_id`),
  CONSTRAINT `fk_model_marka` FOREIGN KEY (`marka_id`) REFERENCES `marka` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,'Mark II',1),(2,'AE86',1),(3,'Silvia S13',2),(4,'240SX',2),(5,'Miata MX5',5),(6,'ВАЗ 2115',6);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `equipment_id` int NOT NULL,
  `instanceTransmission_id` int NOT NULL,
  `instanceEngine_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_options_equipment1_idx` (`equipment_id`),
  KEY `fk_options_instanceTransmission1_idx` (`instanceTransmission_id`),
  KEY `fk_options_instanceEngine1_idx` (`instanceEngine_id`),
  CONSTRAINT `fk_options_equipment1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`id`),
  CONSTRAINT `fk_options_instanceEngine1` FOREIGN KEY (`instanceEngine_id`) REFERENCES `instanceengine` (`id`),
  CONSTRAINT `fk_options_instanceTransmission1` FOREIGN KEY (`instanceTransmission_id`) REFERENCES `instancetransmission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pts`
--

DROP TABLE IF EXISTS `pts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `VIN` varchar(50) NOT NULL,
  `seria` int NOT NULL,
  `number` int NOT NULL,
  `maxWeight` varchar(50) NOT NULL,
  `weightNorma` varchar(50) NOT NULL,
  `bodytype_id` int NOT NULL,
  `engine_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_PTS_bodytype1_idx` (`bodytype_id`),
  KEY `fk_pts_engine1_idx` (`engine_id`),
  CONSTRAINT `fk_PTS_bodytype1` FOREIGN KEY (`bodytype_id`) REFERENCES `bodytype` (`id`),
  CONSTRAINT `fk_pts_engine1` FOREIGN KEY (`engine_id`) REFERENCES `engine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pts`
--

LOCK TABLES `pts` WRITE;
/*!40000 ALTER TABLE `pts` DISABLE KEYS */;
INSERT INTO `pts` VALUES (1,'JN1WNYD21U',77,666213,'1420','800',1,2),(2,'SJNBAAN16UO',53,123799,'1500','920',1,3),(3,'HKSAYSG17SK',66,321344,'1200','760',1,4),(4,'TASDDUI42SD',87,234323,'1300','800',1,5),(5,'ASDUIAA67UI',54,546744,'1450','830',4,6),(6,'KJASPFSS65PK',23,623346,'1260','960',1,1);
/*!40000 ALTER TABLE `pts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transmission`
--

DROP TABLE IF EXISTS `transmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transmission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transmission`
--

LOCK TABLES `transmission` WRITE;
/*!40000 ALTER TABLE `transmission` DISABLE KEYS */;
INSERT INTO `transmission` VALUES (1,'МКПП'),(2,'АКПП'),(3,'РКПП'),(4,'Вариатор');
/*!40000 ALTER TABLE `transmission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','666',1),(2,'user','123',0);
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

-- Dump completed on 2021-05-24 21:21:55
