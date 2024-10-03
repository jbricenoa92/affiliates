-- MySQL dump 10.13  Distrib 9.0.1, for macos14 (arm64)
--
-- Host: localhost    Database: afiliados_db
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Afiliado`
--

DROP TABLE IF EXISTS `Afiliado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Afiliado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(20) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `genero` enum('M','F') DEFAULT NULL,
  `idtipoiden` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ucemail` (`email`),
  KEY `idtipoiden` (`idtipoiden`),
  CONSTRAINT `afiliado_ibfk_1` FOREIGN KEY (`idtipoiden`) REFERENCES `Tipoidentific` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Afiliado`
--

LOCK TABLES `Afiliado` WRITE;
/*!40000 ALTER TABLE `Afiliado` DISABLE KEYS */;
INSERT INTO `Afiliado` VALUES (2,'123456789','Juan','Pérez','juan.perez@example.com','Calle 123','5551234','M',1),(3,'234567890','Ana','García','ana.garcia@example.com','Avenida Siempre Viva 456','5556789','F',1),(4,'A1234567','Carlos','Rodríguez','carlos.rodriguez@example.com','Calle Principal 789','5559876','M',2),(5,'DL987654321','Beatriz','Martínez','beatriz.martinez@example.com','Carrera 45 #12-34','5553214','F',3),(6,'TI56789012','Juan','López','juan.lopez@example.com','Calle 10 #20-30','5554567','M',4),(12,'101234567','José','Péras','jose.peras@example.com','Calle Falsa 456','3112345678','M',1),(13,'101234568','Sofía','Pérez','sofia.perez@example.com','Calle Falsa 789','3123456789','F',2),(14,'201234567','Andrés','Gómez','andres.gomez@example.com','Avenida Siempre Viva 800','3134567890','M',3),(15,'201234568','Luisa','Gómez','luisa.gomez@example.com','Avenida Siempre Viva 801','3145678901','F',4),(16,'301234567','Carlos','López','carlos.lopez2@example.com','Carrera 15 #40-50','3156789012','M',1),(17,'301234568','María','López','maria.lopez@example.com','Carrera 15 #60-70','3167890123','F',2),(18,'201234569','Fernando','Gómez','fernando.gomez@example.com','Transversal 9 #20-30','3178901234','M',3),(19,'201234570','Claudia','Gómez','claudia.gomez@example.com','Transversal 9 #30-40','3189012345','F',4),(20,'101234569','Diego','Pérez','diego.perez@example.com','Calle Falsa 234','3190123456','M',1),(21,'101234570','Carolina','Pérez','carolina.perez@example.com','Calle Falsa 345','3201234567','F',2);
/*!40000 ALTER TABLE `Afiliado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tipoidentific`
--

DROP TABLE IF EXISTS `Tipoidentific`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tipoidentific` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tipoidentific`
--

LOCK TABLES `Tipoidentific` WRITE;
/*!40000 ALTER TABLE `Tipoidentific` DISABLE KEYS */;
INSERT INTO `Tipoidentific` VALUES (1,'CC'),(2,'CE'),(3,'RC'),(4,'TI');
/*!40000 ALTER TABLE `Tipoidentific` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-02 23:17:08
