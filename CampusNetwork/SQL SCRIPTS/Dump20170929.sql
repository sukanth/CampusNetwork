-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: campusnetwork
-- ------------------------------------------------------
-- Server version	5.6.37-log

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

drop schema IF EXISTS campusnetwork;

create schema campusnetwork;

USE campusnetwork;

--
-- Table structure for table `tbl_appointmentrequests`
--

DROP TABLE IF EXISTS `tbl_appointmentrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_appointmentrequests` (
  `AppointmentId` int(11) NOT NULL AUTO_INCREMENT,
  `SSO` int(11) DEFAULT NULL,
  `AppointmentDate` date DEFAULT NULL,
  `InstructorName` varchar(50) DEFAULT NULL,
  `AppointmentTime` varchar(50) DEFAULT NULL,
  `AppointmentType` varchar(50) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `AppointmentStatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AppointmentId`),
  KEY `srkey1` (`SSO`),
  CONSTRAINT `srkey1` FOREIGN KEY (`SSO`) REFERENCES `tbl_student` (`SSO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_appointmentrequests`
--

LOCK TABLES `tbl_appointmentrequests` WRITE;
/*!40000 ALTER TABLE `tbl_appointmentrequests` DISABLE KEYS */;
INSERT INTO `tbl_appointmentrequests` VALUES (2,16186322,'2017-10-06','111','01:00:00 to 03:00:00','Question','Test','Pending');
/*!40000 ALTER TABLE `tbl_appointmentrequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_course`
--

DROP TABLE IF EXISTS `tbl_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_course` (
  `CourseId` varchar(20) NOT NULL,
  `CourseName` varchar(100) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Credits` int(11) DEFAULT NULL,
  `CourseDepartment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CourseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_course`
--

LOCK TABLES `tbl_course` WRITE;
/*!40000 ALTER TABLE `tbl_course` DISABLE KEYS */;
INSERT INTO `tbl_course` VALUES ('CS550SE','Advanced Software Engineering','2017-09-01','2018-02-25',3,'Computer Science And Electrical'),('CS551MT','Software Meathods and Tools','2017-09-01','2018-02-25',3,'Computer Science And Electrical'),('CS551NA','Network Architecture','2017-09-01','2018-02-25',3,'Computer Science And Electrical');
/*!40000 ALTER TABLE `tbl_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_courseallotment`
--

DROP TABLE IF EXISTS `tbl_courseallotment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_courseallotment` (
  `CourseId` varchar(20) DEFAULT NULL,
  `InstructorId` int(11) DEFAULT NULL,
  KEY `srkey5` (`CourseId`),
  KEY `srkey6` (`InstructorId`),
  CONSTRAINT `srkey5` FOREIGN KEY (`CourseId`) REFERENCES `tbl_course` (`CourseId`),
  CONSTRAINT `srkey6` FOREIGN KEY (`InstructorId`) REFERENCES `tbl_instructor` (`InstructorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_courseallotment`
--

LOCK TABLES `tbl_courseallotment` WRITE;
/*!40000 ALTER TABLE `tbl_courseallotment` DISABLE KEYS */;
INSERT INTO `tbl_courseallotment` VALUES ('CS551MT',111),('CS550SE',112),('CS551NA',111);
/*!40000 ALTER TABLE `tbl_courseallotment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_courseenrollment`
--

DROP TABLE IF EXISTS `tbl_courseenrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_courseenrollment` (
  `SSO` int(11) DEFAULT NULL,
  `CourseId` varchar(20) DEFAULT NULL,
  KEY `srkey11` (`SSO`),
  KEY `srkey12` (`CourseId`),
  CONSTRAINT `srkey11` FOREIGN KEY (`SSO`) REFERENCES `tbl_student` (`SSO`),
  CONSTRAINT `srkey12` FOREIGN KEY (`CourseId`) REFERENCES `tbl_course` (`CourseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_courseenrollment`
--

LOCK TABLES `tbl_courseenrollment` WRITE;
/*!40000 ALTER TABLE `tbl_courseenrollment` DISABLE KEYS */;
INSERT INTO `tbl_courseenrollment` VALUES (12426434,'CS551MT'),(16186322,'CS551MT'),(16186322,'CS551NA');
/*!40000 ALTER TABLE `tbl_courseenrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_courseplan`
--

DROP TABLE IF EXISTS `tbl_courseplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_courseplan` (
  `UniqueId` int(11) NOT NULL AUTO_INCREMENT,
  `CourseId` varchar(20) DEFAULT NULL,
  `TopicDate` date DEFAULT NULL,
  `TopicName` varchar(100) DEFAULT NULL,
  `Assignment` varchar(50) DEFAULT NULL,
  `AssignmentDeadline` date DEFAULT NULL,
  PRIMARY KEY (`UniqueId`),
  KEY `srkey8` (`CourseId`),
  CONSTRAINT `srkey8` FOREIGN KEY (`CourseId`) REFERENCES `tbl_course` (`CourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_courseplan`
--

LOCK TABLES `tbl_courseplan` WRITE;
/*!40000 ALTER TABLE `tbl_courseplan` DISABLE KEYS */;
INSERT INTO `tbl_courseplan` VALUES (1,'CS551MT','2017-09-01','Course Introduction','','2017-09-02'),(2,'CS551MT','2017-09-03','Software Development Process and Activities','',NULL),(3,'CS551MT','2017-09-08','Lab 1','Assignment 1','2017-09-09'),(4,'CS551MT','2017-09-10','UML Modelling 1','',NULL),(5,'CS551MT','2017-09-15','Lab 2','Assignment 2','2017-09-16'),(6,'CS551MT','2017-09-17','UML Modelling 2','',NULL),(7,'CS551MT','2017-09-22','Lab 3','Assignment 3','2017-09-23'),(8,'CS551MT','2017-09-24','Software Architecture and Design 1','',NULL),(9,'CS551MT','2017-09-29','Software Architecture and Design 2','',NULL),(10,'CS551MT','2017-10-01','Arch Studio','',NULL),(11,'CS551MT','2017-10-06','Lab 4','Assignment 4','2017-10-07'),(12,'CS551MT','2017-10-08','IDE and Eclipse','',NULL),(13,'CS551MT','2017-10-13','Eclipse Plug ins 1','',NULL),(14,'CS551MT','2017-10-15','Guest Lecture','',NULL),(15,'CS551MT','2017-10-20','Eclipse Plug ins 2','',NULL),(16,'CS551MT','2017-10-22','Lab 5','Assignment 5','2017-10-23'),(17,'CS551MT','2017-10-27','MidTerm Review','',NULL),(18,'CS551MT','2017-10-29','Midterm','',NULL),(19,'CS551MT','2017-11-10','Assignment Demo','',NULL),(20,'CS551MT','2017-11-12','Emacs','',NULL),(21,'CS551MT','2017-11-17','Lab 6','',NULL),(22,'CS551MT','2017-11-19','Testing','',NULL),(23,'CS551MT','2017-11-24','Junit','',NULL),(24,'CS551MT','2017-11-26','Lab 7','Assignment 7',NULL),(25,'CS551MT','2017-12-01','Version Control','',NULL),(26,'CS551MT','2017-12-03','Sub Version','',NULL),(27,'CS551MT','2017-12-08','Lab 8','Assignment 8',NULL),(28,'CS551MT','2017-12-10','GIT','',NULL),(29,'CS551MT','2017-12-15','Lab 9','Assignment 9',NULL),(30,'CS551MT','2017-12-17','Course Review','',NULL),(31,'CS551MT','2017-12-21','Final Exam','',NULL);
/*!40000 ALTER TABLE `tbl_courseplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_instructor`
--

DROP TABLE IF EXISTS `tbl_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_instructor` (
  `InstructorId` int(11) NOT NULL,
  `InstructorName` varchar(50) DEFAULT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `ContactNumber` bigint(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Passwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`InstructorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_instructor`
--

LOCK TABLES `tbl_instructor` WRITE;
/*!40000 ALTER TABLE `tbl_instructor` DISABLE KEYS */;
INSERT INTO `tbl_instructor` VALUES (111,'Sam','M','sam@mail.ucm.edu',9999999999,'1983-07-30','sam'),(112,'kamal','M','kamal@mail.ucm.edu',9999999999,'1983-07-30','kamal');
/*!40000 ALTER TABLE `tbl_instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_setappointments`
--

DROP TABLE IF EXISTS `tbl_setappointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_setappointments` (
  `InstructorId` int(11) NOT NULL DEFAULT '0',
  `AppointmentFromDate` date NOT NULL DEFAULT '0000-00-00',
  `AppointmentToDate` date NOT NULL DEFAULT '0000-00-00',
  `FromTime` time NOT NULL DEFAULT '00:00:00',
  `ToTime` time NOT NULL DEFAULT '00:00:00',
  `AppointmentDuration` int(11) DEFAULT NULL,
  `MaxAppointments` int(11) DEFAULT NULL,
  PRIMARY KEY (`InstructorId`,`AppointmentFromDate`,`AppointmentToDate`,`FromTime`,`ToTime`),
  CONSTRAINT `srkey` FOREIGN KEY (`InstructorId`) REFERENCES `tbl_instructor` (`InstructorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_setappointments`
--

LOCK TABLES `tbl_setappointments` WRITE;
/*!40000 ALTER TABLE `tbl_setappointments` DISABLE KEYS */;
INSERT INTO `tbl_setappointments` VALUES (111,'2017-10-02','2017-10-20','01:00:00','03:00:00',50,5),(111,'2017-10-10','2017-10-17','01:00:00','03:00:00',50,5);
/*!40000 ALTER TABLE `tbl_setappointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_student`
--

DROP TABLE IF EXISTS `tbl_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_student` (
  `SSO` int(11) NOT NULL,
  `StudentName` varchar(50) DEFAULT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Degree` varchar(10) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `ContactNumber` bigint(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Passwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SSO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_student`
--

LOCK TABLES `tbl_student` WRITE;
/*!40000 ALTER TABLE `tbl_student` DISABLE KEYS */;
INSERT INTO `tbl_student` VALUES (12426434,'Sukanth Gunda','M','Masters','sgnw8@mail.ucm.edu',8166823758,'1993-07-30','Sukanth'),(16186322,'Mohammed Zubair','M','Masters','mmz@mail.ucm.edu',8166823463,'1991-10-23','Zubair');
/*!40000 ALTER TABLE `tbl_student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
