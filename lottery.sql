-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema lottery
--

CREATE DATABASE IF NOT EXISTS lottery;
USE lottery;

--
-- Definition of table `lt_activity`
--

DROP TABLE IF EXISTS `lt_activity`;
CREATE TABLE `lt_activity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activityName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lt_activity`
--

/*!40000 ALTER TABLE `lt_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `lt_activity` ENABLE KEYS */;


--
-- Definition of table `lt_prizeitem`
--

DROP TABLE IF EXISTS `lt_prizeitem`;
CREATE TABLE `lt_prizeitem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prizeName` varchar(45) NOT NULL,
  `num` int(10) unsigned NOT NULL,
  `remainNum` int(10) unsigned NOT NULL,
  `aid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lt_prizeitem`
--

/*!40000 ALTER TABLE `lt_prizeitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `lt_prizeitem` ENABLE KEYS */;


--
-- Definition of table `lt_user`
--

DROP TABLE IF EXISTS `lt_user`;
CREATE TABLE `lt_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `idcard` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `flag` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `pid` int(10) DEFAULT '-1',
  `priceTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_2` (`idcard`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lt_user`
--

/*!40000 ALTER TABLE `lt_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `lt_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
