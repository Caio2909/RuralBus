-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ruralbus
-- ------------------------------------------------------
-- Server version	8.0.38

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
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'caio','0000'),(2,'Pedro','1234');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assento`
--

DROP TABLE IF EXISTS `assento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `viagem_id` int DEFAULT NULL,
  `numero` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `viagem_id` (`viagem_id`),
  CONSTRAINT `assento_ibfk_1` FOREIGN KEY (`viagem_id`) REFERENCES `viagem` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assento`
--

LOCK TABLES `assento` WRITE;
/*!40000 ALTER TABLE `assento` DISABLE KEYS */;
INSERT INTO `assento` VALUES (1,53,12),(2,53,1),(3,53,15),(4,64,10),(5,64,11);
/*!40000 ALTER TABLE `assento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `senha` varchar(50) DEFAULT NULL,
  `CPF` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Pedro','ppinheiro@gmail.com','12345','123.456.789-01'),(2,'Kauã','ksantos@gmail.com','54321','123.456.789-02'),(3,'Caio','crodrigues@gmail.com','0000','123.456.789-03'),(6,'teste da Silva','teste@teste.com','caio123','123.131.312-31'),(7,'caio','testando@test','caio','123.123.321-31'),(9,'mensage registro','teste@registro','0000','123.132.331-32'),(10,'Pedro Henrique','phenriquesalles@gmail.com','12345','231.312.313-23');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passagem`
--

DROP TABLE IF EXISTS `passagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passagem` (
  `id` varchar(50) NOT NULL DEFAULT (uuid()),
  `cliente_id` bigint DEFAULT NULL,
  `viagem_id` int DEFAULT NULL,
  `assento_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `viagem_id` (`viagem_id`),
  KEY `assento_id` (`assento_id`),
  CONSTRAINT `passagem_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `passagem_ibfk_2` FOREIGN KEY (`viagem_id`) REFERENCES `viagem` (`id`),
  CONSTRAINT `passagem_ibfk_3` FOREIGN KEY (`assento_id`) REFERENCES `assento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passagem`
--

LOCK TABLES `passagem` WRITE;
/*!40000 ALTER TABLE `passagem` DISABLE KEYS */;
INSERT INTO `passagem` VALUES ('0788cf54-b40f-11ef-a411-04d9f50a1039',10,53,3),('0f0fad86-b4f9-11ef-8c7b-04d9f50a1039',3,64,4),('29a909d8-b4f9-11ef-8c7b-04d9f50a1039',3,64,5),('2bf491c6-b374-11ef-8eda-04d9f50a1039',3,53,1),('53db7f02-b377-11ef-8eda-04d9f50a1039',3,53,2);
/*!40000 ALTER TABLE `passagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(10) NOT NULL,
  `capacidade` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (1,'ABC1234',50),(2,'DEF5678',40),(3,'GHI9012',60),(4,'JKL3456',35),(5,'MNO7890',45);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viagem`
--

DROP TABLE IF EXISTS `viagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `partida` varchar(50) NOT NULL,
  `destino` varchar(50) NOT NULL,
  `data_partida` datetime NOT NULL,
  `data_chegada` datetime NOT NULL,
  `veiculo_id` int DEFAULT NULL,
  `preco` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `veiculo_id` (`veiculo_id`),
  CONSTRAINT `viagem_ibfk_1` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viagem`
--

LOCK TABLES `viagem` WRITE;
/*!40000 ALTER TABLE `viagem` DISABLE KEYS */;
INSERT INTO `viagem` VALUES (53,'São Paulo - SP','Rio de Janeiro - RJ','2024-12-10 08:00:00','2024-12-10 18:00:00',1,150.00),(54,'Rio de Janeiro - RJ','Belo Horizonte - MG','2024-12-11 07:00:00','2024-12-11 13:00:00',2,120.00),(55,'Belo Horizonte - MG','São Paulo - SP','2024-12-12 09:00:00','2024-12-12 17:00:00',3,140.00),(56,'São Paulo - SP','Curitiba - PR','2024-12-13 10:00:00','2024-12-13 15:00:00',4,80.00),(57,'Curitiba - PR','Florianópolis - SC','2024-12-14 06:00:00','2024-12-14 08:00:00',5,50.00),(58,'Salvador - BA','Recife - PE','2024-12-15 08:30:00','2024-12-15 13:00:00',1,130.00),(59,'Fortaleza - CE','Natal - RN','2024-12-16 09:00:00','2024-12-16 12:30:00',2,100.00),(60,'Porto Alegre - RS','Florianópolis - SC','2024-12-17 11:00:00','2024-12-17 14:00:00',3,110.00),(61,'Manaus - AM','Belém - PA','2024-12-18 14:00:00','2024-12-18 18:00:00',4,200.00),(62,'Goiania - GO','Brasília - DF','2024-12-19 16:00:00','2024-12-19 18:00:00',5,90.00),(63,'Seropédica - RJ','Xique-Xique - BA','2024-12-13 00:00:00','2024-12-18 00:00:00',1,269.99),(64,'Xique-Xique - BA','Seropédica - RJ','2024-12-18 21:07:00','2024-12-22 21:07:00',1,269.99);
/*!40000 ALTER TABLE `viagem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-07 21:36:37
