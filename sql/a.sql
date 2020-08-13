-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 120.78.185.128    Database: classrepair
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `build` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '建筑物',
  `floor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '楼层',
  `room` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课室号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='课室表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (10001,1580125849,0,1,0,'A1','1','02'),(10002,1580125849,0,1,0,'A1','1','03'),(10003,1580125849,0,1,0,'A1','1','04'),(10004,1580125849,0,1,0,'A1','1','05'),(10005,1580125849,0,1,0,'A1','1','07'),(10006,1580125849,0,1,0,'A1','1','08'),(10007,1580125849,0,1,0,'A1','1','09'),(10008,1580125849,0,1,0,'A1','1','10'),(10009,1580125849,0,1,0,'A1','2','02'),(10010,1580125849,0,1,0,'A1','2','03'),(10011,1580125849,0,1,0,'A1','2','04'),(10012,1580125849,0,1,0,'A1','2','05'),(10013,1580125849,0,1,0,'A1','2','07'),(10014,1580125849,0,1,0,'A1','2','08'),(10015,1580125849,0,1,0,'A1','2','09'),(10016,1580125849,0,1,0,'A1','2','10'),(10017,1580125849,0,1,0,'A1','3','02'),(10018,1580125849,0,1,0,'A1','3','03'),(10019,1580125849,0,1,0,'A1','3','04'),(10020,1580125849,0,1,0,'A1','3','05'),(10021,1580125849,0,1,0,'A1','3','07'),(10022,1580125849,0,1,0,'A1','3','08'),(10023,1580125849,0,1,0,'A1','3','09'),(10024,1580125850,0,1,0,'A1','3','10'),(10025,1580125850,0,1,0,'A1','4','02'),(10026,1580125850,0,1,0,'A1','4','03'),(10027,1580125850,0,1,0,'A1','4','04'),(10028,1580125850,0,1,0,'A1','4','05'),(10029,1580125850,0,1,0,'A1','4','08'),(10030,1580125850,0,1,0,'A1','4','09'),(10031,1580125850,0,1,0,'A1','4','10'),(10032,1580125850,0,1,0,'A2','1','02'),(10033,1580125850,0,1,0,'A2','1','03'),(10034,1580125850,0,1,0,'A2','1','04'),(10035,1580125850,0,1,0,'A2','1','05'),(10036,1580125850,0,1,0,'A2','1','07'),(10037,1580125850,0,1,0,'A2','1','09'),(10038,1580125850,0,1,0,'A2','1','10'),(10039,1580125850,0,1,0,'A2','2','02'),(10040,1580125850,0,1,0,'A2','2','03'),(10041,1580125850,0,1,0,'A2','2','04'),(10042,1580125850,0,1,0,'A2','2','05'),(10043,1580125850,0,1,0,'A2','2','07'),(10044,1580125850,0,1,0,'A2','2','08'),(10045,1580125850,0,1,0,'A2','2','09'),(10046,1580125850,0,1,0,'A2','2','10'),(10047,1580125850,0,1,0,'A2','3','02'),(10048,1580125850,0,1,0,'A2','3','03'),(10049,1580125850,0,1,0,'A2','3','04'),(10050,1580125850,0,1,0,'A2','3','05'),(10051,1580125850,0,1,0,'A2','3','07'),(10052,1580125850,0,1,0,'A2','3','08'),(10053,1580125850,0,1,0,'A2','3','09'),(10054,1580125850,0,1,0,'A2','3','10'),(10055,1580125850,0,1,0,'A2','4','02'),(10056,1580125850,0,1,0,'A2','4','03'),(10057,1580125850,0,1,0,'A2','4','04'),(10058,1580125850,0,1,0,'A2','4','05'),(10059,1580125850,0,1,0,'A2','4','08'),(10060,1580125850,0,1,0,'A2','4','09'),(10061,1580125850,0,1,0,'A2','4','10'),(10062,1580125850,0,1,0,'A3','1','01'),(10063,1580125850,0,1,0,'A3','1','02'),(10064,1580125850,0,1,0,'A3','1','03'),(10065,1580125850,0,1,0,'A3','1','04'),(10066,1580125850,0,1,0,'A3','1','05'),(10067,1580125850,0,1,0,'A3','1','08'),(10068,1580125850,0,1,0,'A3','1','09'),(10069,1580125850,0,1,0,'A3','1','10'),(10070,1580125850,0,1,0,'A3','2','01'),(10071,1580125850,0,1,0,'A3','2','02'),(10072,1580125850,0,1,0,'A3','2','03'),(10073,1580125850,0,1,0,'A3','2','04'),(10074,1580125850,0,1,0,'A3','2','05'),(10075,1580125850,0,1,0,'A3','2','08'),(10076,1580125850,0,1,0,'A3','2','09'),(10077,1580125850,0,1,0,'A3','2','10'),(10078,1580125850,0,1,0,'A3','3','01'),(10079,1580125850,0,1,0,'A3','3','02'),(10080,1580125850,0,1,0,'A3','3','03'),(10081,1580125850,0,1,0,'A3','3','04'),(10082,1580125850,0,1,0,'A3','3','05'),(10083,1580125850,0,1,0,'A3','3','08'),(10084,1580125850,0,1,0,'A3','3','09'),(10085,1580125850,0,1,0,'A3','3','10'),(10086,1580125850,0,1,0,'A3','4','02'),(10087,1580125850,0,1,0,'A3','4','03'),(10088,1580125850,0,1,0,'A3','4','04'),(10089,1580125850,0,1,0,'A3','4','09'),(10090,1580125850,0,1,0,'A3','4','10'),(10091,1580125850,0,1,0,'A4','2','01'),(10092,1580125851,0,1,0,'A4','2','02'),(10093,1580125851,0,1,0,'A4','2','03'),(10094,1580125851,0,1,0,'A4','2','04'),(10095,1580125851,0,1,0,'A4','2','05'),(10096,1580125851,0,1,0,'A4','2','08'),(10097,1580125851,0,1,0,'A4','2','09'),(10098,1580125851,0,1,0,'A4','2','10'),(10099,1580125851,0,1,0,'A4','3','01'),(10100,1580125851,0,1,0,'A4','3','02'),(10101,1580125851,0,1,0,'A4','3','03'),(10102,1580125851,0,1,0,'A4','3','04'),(10103,1580125851,0,1,0,'A4','3','05'),(10104,1580125851,0,1,0,'A4','3','08'),(10105,1580125851,0,1,0,'A4','3','09'),(10106,1580125851,0,1,0,'A4','3','10'),(10107,1580125851,0,1,0,'A4','4','01'),(10108,1580125851,0,1,0,'A4','4','02'),(10109,1580125851,0,1,0,'A4','4','03'),(10110,1580125851,0,1,0,'A4','4','04'),(10111,1580125851,0,1,0,'A4','4','05'),(10112,1580125851,0,1,0,'A4','4','08'),(10113,1580125851,0,1,0,'A4','4','09'),(10114,1580125851,0,1,0,'A4','4','10'),(10115,1580125851,0,1,0,'A5','1','01'),(10116,1580125851,0,1,0,'A5','1','02'),(10117,1580125851,0,1,0,'A5','1','03'),(10118,1580125851,0,1,0,'A5','1','05'),(10119,1580125851,0,1,0,'A5','1','07'),(10120,1580125851,0,1,0,'A5','1','08'),(10121,1580125851,0,1,0,'A5','1','09'),(10122,1580125851,0,1,0,'A5','1','10'),(10123,1580125851,0,1,0,'A5','1','11'),(10124,1580125851,0,1,0,'A5','1','12'),(10125,1580125851,0,1,0,'A5','2','01'),(10126,1580125851,0,1,0,'A5','2','02'),(10127,1580125851,0,1,0,'A5','2','03'),(10128,1580125851,0,1,0,'A5','2','04'),(10129,1580125851,0,1,0,'A5','2','05'),(10130,1580125851,0,1,0,'A5','2','06'),(10131,1580125851,0,1,0,'A5','3','01'),(10132,1580125851,0,1,0,'A5','3','03'),(10133,1580125851,0,1,0,'A5','3','04'),(10134,1580125851,0,1,0,'A5','3','05'),(10135,1580125851,0,1,0,'A5','3','06'),(10136,1580125851,0,1,0,'A5','4','01'),(10137,1580125851,0,1,0,'A5','4','03'),(10138,1580125851,0,1,0,'A5','4','04'),(10139,1580125851,0,1,0,'A5','4','05'),(10140,1580125851,0,1,0,'A5','4','06'),(10141,1580125851,0,1,0,'A6','1','01'),(10142,1580125851,0,1,0,'A6','3','01'),(10143,1580125851,0,1,0,'A6','3','03'),(10144,1580125851,0,1,0,'A6','3','04'),(10145,1580125851,0,1,0,'A6','3','05'),(10146,1580125851,0,1,0,'A6','3','08'),(10147,1580125851,0,1,0,'A6','3','11'),(10148,1580125851,0,1,0,'A6','3','12'),(10149,1580125851,0,1,0,'A6','3','15'),(10150,1580125851,0,1,0,'A6','3','18'),(10151,1580125851,0,1,0,'A6','4','01'),(10152,1580125851,0,1,0,'A6','4','03'),(10153,1580125851,0,1,0,'A6','4','04'),(10154,1580125851,0,1,0,'A6','4','05'),(10155,1580125851,0,1,0,'A6','4','08'),(10156,1580125851,0,1,0,'A6','4','11'),(10157,1580125851,0,1,0,'A6','4','12'),(10158,1580125851,0,1,0,'A6','4','15'),(10159,1580125851,0,1,0,'A6','4','18'),(10160,1580125851,0,1,0,'A6','5','01'),(10161,1580125851,0,1,0,'A6','5','02'),(10162,1580125851,0,1,0,'A6','5','03'),(10163,1580125851,0,1,0,'A6','5','08A'),(10164,1580125851,0,1,0,'A6','5','08B'),(10165,1580125851,0,1,0,'A1','1','02'),(10166,1580125851,0,1,0,'A1','1','03'),(10167,1580125851,0,1,0,'A1','1','04'),(10168,1580125851,0,1,0,'A1','1','05'),(10169,1580125851,0,1,0,'A1','1','07'),(10170,1580125851,0,1,0,'A1','1','08'),(10171,1580125852,0,1,0,'A1','1','09'),(10172,1580125852,0,1,0,'A1','1','10'),(10173,1580125852,0,1,0,'A1','2','02'),(10174,1580125852,0,1,0,'A1','2','03'),(10175,1580125852,0,1,0,'A1','2','04'),(10176,1580125852,0,1,0,'A1','2','05'),(10177,1580125852,0,1,0,'A1','2','07'),(10178,1580125852,0,1,0,'A1','2','08'),(10179,1580125852,0,1,0,'A1','2','09'),(10180,1580125852,0,1,0,'A1','2','10'),(10181,1580125852,0,1,0,'A1','3','02'),(10182,1580125852,0,1,0,'A1','3','03'),(10183,1580125852,0,1,0,'A1','3','04'),(10184,1580125852,0,1,0,'A1','3','05'),(10185,1580125852,0,1,0,'A1','3','07'),(10186,1580125852,0,1,0,'A1','3','08'),(10187,1580125852,0,1,0,'A1','3','09'),(10188,1580125852,0,1,0,'A1','3','10'),(10189,1580125852,0,1,0,'A1','4','02'),(10190,1580125852,0,1,0,'A1','4','03'),(10191,1580125852,0,1,0,'A1','4','04'),(10192,1580125852,0,1,0,'A1','4','05'),(10193,1580125852,0,1,0,'A1','4','08'),(10194,1580125852,0,1,0,'A1','4','09'),(10195,1580125852,0,1,0,'A1','4','10'),(10196,1580125852,0,1,0,'A2','1','02'),(10197,1580125852,0,1,0,'A2','1','03'),(10198,1580125852,0,1,0,'A2','1','04'),(10199,1580125852,0,1,0,'A2','1','05'),(10200,1580125852,0,1,0,'A2','1','07'),(10201,1580125852,0,1,0,'A2','1','09'),(10202,1580125852,0,1,0,'A2','1','10'),(10203,1580125852,0,1,0,'A2','2','02'),(10204,1580125852,0,1,0,'A2','2','03'),(10205,1580125852,0,1,0,'A2','2','04'),(10206,1580125852,0,1,0,'A2','2','05'),(10207,1580125852,0,1,0,'A2','2','07'),(10208,1580125852,0,1,0,'A2','2','08'),(10209,1580125852,0,1,0,'A2','2','09'),(10210,1580125852,0,1,0,'A2','2','10'),(10211,1580125852,0,1,0,'A2','3','02'),(10212,1580125852,0,1,0,'A2','3','03'),(10213,1580125852,0,1,0,'A2','3','04'),(10214,1580125852,0,1,0,'A2','3','05'),(10215,1580125852,0,1,0,'A2','3','07'),(10216,1580125852,0,1,0,'A2','3','08'),(10217,1580125852,0,1,0,'A2','3','09'),(10218,1580125852,0,1,0,'A2','3','10'),(10219,1580125852,0,1,0,'A2','4','02'),(10220,1580125852,0,1,0,'A2','4','03'),(10221,1580125852,0,1,0,'A2','4','04'),(10222,1580125852,0,1,0,'A2','4','05'),(10223,1580125852,0,1,0,'A2','4','08'),(10224,1580125852,0,1,0,'A2','4','09'),(10225,1580125852,0,1,0,'A2','4','10'),(10226,1580125852,0,1,0,'A3','1','01'),(10227,1580125852,0,1,0,'A3','1','02'),(10228,1580125852,0,1,0,'A3','1','03'),(10229,1580125852,0,1,0,'A3','1','04'),(10230,1580125852,0,1,0,'A3','1','05'),(10231,1580125852,0,1,0,'A3','1','08'),(10232,1580125852,0,1,0,'A3','1','09'),(10233,1580125852,0,1,0,'A3','1','10'),(10234,1580125852,0,1,0,'A3','2','01'),(10235,1580125852,0,1,0,'A3','2','02'),(10236,1580125852,0,1,0,'A3','2','03'),(10237,1580125852,0,1,0,'A3','2','04'),(10238,1580125852,0,1,0,'A3','2','05'),(10239,1580125852,0,1,0,'A3','2','08'),(10240,1580125852,0,1,0,'A3','2','09'),(10241,1580125852,0,1,0,'A3','2','10'),(10242,1580125852,0,1,0,'A3','3','01'),(10243,1580125852,0,1,0,'A3','3','02'),(10244,1580125852,0,1,0,'A3','3','03'),(10245,1580125853,0,1,0,'A3','3','04'),(10246,1580125853,0,1,0,'A3','3','05'),(10247,1580125853,0,1,0,'A3','3','08'),(10248,1580125853,0,1,0,'A3','3','09'),(10249,1580125853,0,1,0,'A3','3','10'),(10250,1580125853,0,1,0,'A3','4','02'),(10251,1580125853,0,1,0,'A3','4','03'),(10252,1580125853,0,1,0,'A3','4','04'),(10253,1580125853,0,1,0,'A3','4','09'),(10254,1580125853,0,1,0,'A3','4','10'),(10255,1580125853,0,1,0,'A4','2','01'),(10256,1580125853,0,1,0,'A4','2','02'),(10257,1580125853,0,1,0,'A4','2','03'),(10258,1580125853,0,1,0,'A4','2','04'),(10259,1580125853,0,1,0,'A4','2','05'),(10260,1580125853,0,1,0,'A4','2','08'),(10261,1580125853,0,1,0,'A4','2','09'),(10262,1580125853,0,1,0,'A4','2','10'),(10263,1580125853,0,1,0,'A4','3','01'),(10264,1580125853,0,1,0,'A4','3','02'),(10265,1580125853,0,1,0,'A4','3','03'),(10266,1580125853,0,1,0,'A4','3','04'),(10267,1580125853,0,1,0,'A4','3','05'),(10268,1580125853,0,1,0,'A4','3','08'),(10269,1580125853,0,1,0,'A4','3','09'),(10270,1580125853,0,1,0,'A4','3','10'),(10271,1580125853,0,1,0,'A4','4','01'),(10272,1580125853,0,1,0,'A4','4','02'),(10273,1580125853,0,1,0,'A4','4','03'),(10274,1580125853,0,1,0,'A4','4','04'),(10275,1580125853,0,1,0,'A4','4','05'),(10276,1580125853,0,1,0,'A4','4','08'),(10277,1580125853,0,1,0,'A4','4','09'),(10278,1580125853,0,1,0,'A4','4','10'),(10279,1580125853,0,1,0,'A5','1','01'),(10280,1580125853,0,1,0,'A5','1','02'),(10281,1580125853,0,1,0,'A5','1','03'),(10282,1580125853,0,1,0,'A5','1','05'),(10283,1580125853,0,1,0,'A5','1','07'),(10284,1580125853,0,1,0,'A5','1','08'),(10285,1580125853,0,1,0,'A5','1','09'),(10286,1580125853,0,1,0,'A5','1','10'),(10287,1580125853,0,1,0,'A5','1','11'),(10288,1580125853,0,1,0,'A5','1','12'),(10289,1580125853,0,1,0,'A5','2','01'),(10290,1580125853,0,1,0,'A5','2','02'),(10291,1580125853,0,1,0,'A5','2','03'),(10292,1580125853,0,1,0,'A5','2','04'),(10293,1580125853,0,1,0,'A5','2','05'),(10294,1580125853,0,1,0,'A5','2','06'),(10295,1580125853,0,1,0,'A5','3','01'),(10296,1580125853,0,1,0,'A5','3','03'),(10297,1580125853,0,1,0,'A5','3','04'),(10298,1580125853,0,1,0,'A5','3','05'),(10299,1580125853,0,1,0,'A5','3','06'),(10300,1580125853,0,1,0,'A5','4','01'),(10301,1580125853,0,1,0,'A5','4','03'),(10302,1580125853,0,1,0,'A5','4','04'),(10303,1580125853,0,1,0,'A5','4','05'),(10304,1580125853,0,1,0,'A5','4','06'),(10305,1580125853,0,1,0,'A6','1','01'),(10306,1580125853,0,1,0,'A6','3','01'),(10307,1580125853,0,1,0,'A6','3','03'),(10308,1580125853,0,1,0,'A6','3','04'),(10309,1580125853,0,1,0,'A6','3','05'),(10310,1580125853,0,1,0,'A6','3','08'),(10311,1580125853,0,1,0,'A6','3','11'),(10312,1580125853,0,1,0,'A6','3','12'),(10313,1580125853,0,1,0,'A6','3','15'),(10314,1580125853,0,1,0,'A6','3','18'),(10315,1580125853,0,1,0,'A6','4','01'),(10316,1580125853,0,1,0,'A6','4','03'),(10317,1580125853,0,1,0,'A6','4','04'),(10318,1580125853,0,1,0,'A6','4','05'),(10319,1580125853,0,1,0,'A6','4','08'),(10320,1580125853,0,1,0,'A6','4','11'),(10321,1580125853,0,1,0,'A6','4','12'),(10322,1580125853,0,1,0,'A6','4','15'),(10323,1580125853,0,1,0,'A6','4','18'),(10324,1580125853,0,1,0,'A6','5','01'),(10325,1580125853,0,1,0,'A6','5','02'),(10326,1580125853,0,1,0,'A6','5','03'),(10327,1580125853,0,1,0,'A6','5','08A'),(10328,1580125853,0,1,0,'A6','5','08B'),(20009,1580125849,0,1,0,'A1','1','02');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(25) NOT NULL COMMENT '材料名',
  `amount` int(11) NOT NULL DEFAULT '100' COMMENT '材料数量',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '材料类型',
  `price` double unsigned NOT NULL COMMENT '价格',
  `sort` tinyint(3) unsigned NOT NULL COMMENT '排序',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-正常,2-已删除',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'sads',100,1,12,1,1580109006,0,1,0),(2,'123543',500,1,12.56,1,1595861330,0,1,0),(3,'123543',500,1,12.56,1,1595861503,0,1,0),(4,'laborum id',56657158,1,25224801.658753067,1,1595861616,1595862476,2,1595862018),(5,'123',100,1,123,1,1595861616,0,1,0),(6,'id labore',23694305,1,76852570.49991839,1,1596102571,0,1,0),(7,'ad cillum id quis',-80360652,1,53757875.29454544,1,1596107169,0,1,0),(8,'Excepteu',2824,1,91290225.10586143,1,1596111391,0,1,0),(9,'est in',48346676,1,4596094.431876853,1,1596181377,0,1,0);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_order_relation`
--

DROP TABLE IF EXISTS `material_order_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_order_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-正常，2-已删除',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `material_id` bigint(20) unsigned NOT NULL COMMENT '材料id号',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单id号',
  `amount` int(11) NOT NULL DEFAULT '1' COMMENT '使用数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_order_relation`
--

LOCK TABLES `material_order_relation` WRITE;
/*!40000 ALTER TABLE `material_order_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_order_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qa_answer`
--

DROP TABLE IF EXISTS `qa_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qa_answer` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `qa_type_id` smallint(5) unsigned NOT NULL COMMENT '对应qa_type表的id',
  `sort` tinyint(3) unsigned NOT NULL COMMENT ' 顺序，在同一目录时显示的先后顺序',
  `question` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '故障问题',
  `answer_public` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '解决方案公开版',
  `answer_private` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '解决方案内部版',
  `good_num` smallint(5) unsigned NOT NULL COMMENT '采纳次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64533 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='故障详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qa_answer`
--

LOCK TABLES `qa_answer` WRITE;
/*!40000 ALTER TABLE `qa_answer` DISABLE KEYS */;
INSERT INTO `qa_answer` VALUES (414,1580109006,0,1,1581929421,10003,1,'鹅颈麦没有声音','检查鹅颈麦是否有打开（灯是否亮）','<span></span><span></span><span>（1）</span><span>&nbsp;</span><span>检查鹅颈麦是否有打开（灯是否亮）</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>检查功放是否将鹅颈麦的声音调到太小</span><br />\r\n<span>（3）</span><span>&nbsp;</span><span>检查鹅颈麦和功放之间的线是否有插好</span><br />\r\n<span>（4）</span><span>&nbsp;</span><span>对鹅颈麦的部件进行跟换，找出损坏部件进行更换</span>\r\n</p>\r\n<span></span><span></span>',4),(420,1580109006,0,1,0,10009,99,'其它问题','请填写报修单','',0),(611,1580109006,0,1,1581930195,10001,6,'电脑无法播放在线视频','<p>卸载原有的flash，下载最新的flash，关闭浏览器后安装</p>','<p>卸载原有的flash，下载最新的flash，关闭浏览器后安装</p>',9),(616,1580109006,0,1,0,10007,99,'其它问题','请填写报修单','',0),(619,1580109006,0,1,0,10008,99,'其它问题','请填写报修单','',0),(621,1580109006,0,1,1581929334,10002,1,'无线麦没有声音','<span>（1）</span><span>&nbsp;</span><span>检查无线麦是否有打开（高频率闪红色和蓝色是在对频率，低频率闪蓝色光则代表已经对频成功）</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>将无线麦靠近讲台下方进行对频</span>','',6),(731,1580109006,0,1,0,10002,2,'无线麦有杂音','请填写报修单','<span>（1）</span><span>&nbsp;</span><span>调节功放的无线麦接口旋钮和麦克风总音量旋钮</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>将无线麦的插线插到功放的其他接口上，或者将有线麦的接口和无线麦的接口进行交换，再进行第一步调音</span>',0),(1114,1580109006,0,1,0,10004,2,'投影仪颜色不对','请填写报修单','<span>（1）</span><span>&nbsp;</span><span>调整讲台下方投影仪的接口，查看是否有插紧</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>如果是投影仪泛黄，可能是投影仪灯泡问题，报告老师进行维修</span>',0),(1213,1580109005,0,1,0,10001,3,'office无法打开或字体缺失','请填写报修单','',0),(1515,1580109006,0,1,0,10003,2,'鹅颈麦有杂音','请填写报修单','<span>（1）</span><span>&nbsp;</span><span>摇晃鹅颈麦的接线，检查是否有杂音（有的话进行更换）</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>摇晃鹅颈麦上半部分的麦把，检查是否有杂音（有的话进行更换）</span><br />\r\n<span>（3）</span><span>&nbsp;</span><span>参考无线麦有杂音解决方案</span>',0),(1516,1580109006,0,1,0,10006,99,'其它问题','请填写报修单','',0),(1533,1580109006,0,1,0,10004,3,'投影仪不清晰','请填写报修单','调整讲台下方投影仪的接口，查看是否有插紧',0),(1666,1580109006,0,1,0,10005,99,'其它问题','请填写报修单','',0),(2021,1580109005,0,1,1581595245,10001,2,'没有网络','（1）	检查网络接口有没有接好','',2),(2272,1580109006,0,1,0,10003,99,'其它问题','请填写报修单','',0),(2497,1580109006,0,1,1581929357,10001,4,'电脑声音太小','调节电脑音量','<span>（1）</span><span>&nbsp;</span><span>调节电脑音量</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>调节功放的 Music vol</span>',1),(4112,1580109006,0,1,0,10002,4,'无线麦无法控制PPT翻页','请填写报修单','检查电脑后面或者前面的u盘接收器是否有闪（如果没有闪，换一下接口插上或者找老师进行更换）',0),(5641,1580109006,0,1,0,10003,3,'鹅颈麦声音太小','请填写报修单','参考无线麦声音太小',0),(6161,1580109006,0,1,0,10001,8,'电脑、显示屏、投影仪正常，不输出画面','请填写报修单','<span>(1) 中控断电15s后开启</span><br /><span>(2) 中控上面台式电脑的接线直接接显示屏，看是否有输出，逐渐排除线路，看哪条线坏了</span>',0),(7545,1580109005,0,1,0,10001,1,'电脑打不开','<span>（1）</span><span>&nbsp;</span><span>检查中控是否有开启</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>检查电脑是否能手动开机（如果可以，则有可能电脑与中控间插线没有插好）</span><br />\r\n<span>（3）</span><span>&nbsp;</span><span>检查电脑电源是否有插好</span><br />\r\n<span>（4）</span><span>&nbsp;</span><span>检查电脑硬件</span><span></span>','',0),(10213,1580109006,0,1,0,10001,5,'U盘插上没有反应','使用讲台下方的USB延长线或者插在讲台下方电脑主机上','',0),(13326,1580109006,0,1,0,10004,1,'投影仪不亮/打不开','<span>（1）</span><span>&nbsp;</span><span>检查中控是否打开</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>使用抽屉里的遥控对投影仪进行开机</span>','<span>（1）</span><span>&nbsp;</span><span><span>检查中控是否打开</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>使用抽屉里的遥控对投影仪进行开机</span></span><span></span><br />\r\n<span>（3）</span><span>&nbsp;</span><span>检查投影仪与中控间的接线是否有接好</span>',0),(16717,1580109006,0,1,1581595827,10001,7,'电脑没有声音','<p>检查电脑是否调成耳机输出</p>','<p>(1)检查电脑是否调成耳机输出</p><p>(2)将电脑后方音频输出线拔出再插紧</p>',5),(17177,1580109006,0,1,0,10004,8,'电脑、显示屏、投影仪正常，不输出画面','请填写报修单','<span>(1) 中控断电15s后开启</span><br /><span>(2) 中控上面台式电脑的接线直接接显示屏，看是否有输出，逐渐排除线路，看哪条线坏了</span>',0),(21754,1580109006,0,1,0,10004,99,'其它问题','请填写报修单','',0),(24146,1580109006,0,1,0,10001,99,'其它问题','请填写报修单','',0),(41461,1580109006,0,1,0,10002,3,'无线麦声音太小','请填写报修单','<span>（1）</span><span>&nbsp;</span><span>调节功放的无线麦接口旋钮和麦克风总音量旋钮</span><br />\r\n<span>（2）</span><span>&nbsp;</span><span>将无线麦的插线插到功放的其他接口上，或者将有线麦的接口和无线麦的接口进行交换，再进行第一步调音</span>',0),(64532,1580109006,0,1,0,10002,99,'其它问题','请填写报修单','',0);
/*!40000 ALTER TABLE `qa_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qa_type`
--

DROP TABLE IF EXISTS `qa_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qa_type` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `sort` tinyint(3) unsigned NOT NULL COMMENT '顺序，显示的先后顺序',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '显示的标题',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='常见故障分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qa_type`
--

LOCK TABLES `qa_type` WRITE;
/*!40000 ALTER TABLE `qa_type` DISABLE KEYS */;
INSERT INTO `qa_type` VALUES (10001,1580100644,0,1,1595594747,1,'电脑',''),(10002,1580100644,0,1,0,2,'无线麦',''),(10003,1580100644,0,1,0,3,'鹅颈麦',''),(10004,1580100644,0,1,0,4,'投影',''),(10005,1580100644,0,1,0,5,'灯',''),(10006,1580100644,0,1,0,6,'风扇',''),(10007,1580100644,0,1,0,7,'空调',''),(10008,1580100644,0,1,0,8,'其他问题','');
/*!40000 ALTER TABLE `qa_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_item`
--

DROP TABLE IF EXISTS `repair_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '0-异常订单 1-待处理 2-处理中 3-已处理 4-已取消',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `repair_item_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743',
  `receiver` smallint(5) unsigned NOT NULL COMMENT ' 接单人(表id)',
  `orderer` smallint(5) unsigned NOT NULL COMMENT ' 报修人(表id)',
  `classroom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课室',
  `equipment_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '故障设备',
  `problem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题描述',
  `orderer_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报修人手机号',
  `urgent` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是紧急订单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='报修表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_item`
--

LOCK TABLES `repair_item` WRITE;
/*!40000 ALTER TABLE `repair_item` DISABLE KEYS */;
INSERT INTO `repair_item` VALUES (1,1584851145,0,4,1585544591,'202003225351962',2,2,'A1-102','10001','电脑无法播放在线视频','13924387832',1),(2,1584851217,0,4,1585545199,'202003227239107',2,2,'A1-105','10002','无线麦有杂音','13924387832',1),(3,1584851520,0,4,1586188260,'20200322233829',2,2,'A1-302','10001','没有网络','13924387832',1),(4,1585548383,0,4,1586314238,'202003303805842',6,2,'A1-105','10001','office无法打开或字体缺失','13924387832',1),(5,1585548418,0,3,1586314200,'202003308548946',2,2,'A1-105','10002','无线麦有杂音','13924387832',1),(6,1585548888,0,3,1586314396,'202003308184158',2,2,'A1-105','10001','没有网络','13924387832',1),(8,1586186708,0,4,1586187782,'202004068095155',34,34,'A2-102','10001','测试接口','-',0),(9,1586187855,0,3,1586187922,'202004065900938',34,34,'A6-401','10001','测试接口','-',0),(10,1586313908,0,4,1590317653,'202004088320508',34,2,'A3-101','10002','无线麦有杂音','13924387832',1),(11,1586315184,0,1,0,'202004084406567',0,2,'A1-202','10003','鹅颈麦有杂音','13924387832',1),(12,1590298709,0,1,0,'202005249737144',0,6,'A1-202','10001','wV)SV','',1),(13,1590298752,0,1,0,'202005242467464',0,6,'A1-202','10001','BsoZSR','',0),(14,1590298758,0,1,0,'202005248684285',0,6,'A1-202','10001','BsoZSR','',1),(15,1590300630,1590317527,5,1590317527,'20200524731846',0,34,'A1-102','10001','电脑无法播放在线视频','-',1),(16,1590300667,1590300757,5,1590300757,'202005247964986',0,34,'A1-102','10001','电脑无法播放在线视频','-',1),(17,1590317298,1595123073,5,1595123073,'202005248035956',0,34,'A1-102','10001','电脑无法播放在线视频','123456',1),(18,1590317298,0,4,1590317407,'202005248130631',34,34,'A1-102','10001','电脑无法播放在线视频','123456',1),(19,1590317486,1595090761,5,1595090761,'202005246539184',0,34,'A1-102','10001','office无法打开或字体缺失','123456',0),(20,1590317774,1595090754,5,1595090754,'202005244661437',0,34,'A1-102','10001','office无法打开或字体缺失','123456',1),(21,1590318209,0,4,1590318330,'202005249295561',6,34,'A1-102','10001','电脑打不开','111111',1),(23,1594973826,0,1,0,'202007176520739',0,75,'A1-202','10001','SBy','',0),(33,1594994474,0,1,0,'202007174000194',0,75,'A1-202','10001','q47#J%^','',0),(40,1595090696,1595090740,5,1595090740,'202007196653556',0,34,'A1-102','10001','电脑无法播放在线视频','111111',0),(48,1595123000,1595123059,5,1595123059,'20200719786892',0,34,'A1-102','10001','电脑无法播放在线视频','111111',1),(49,1595123328,0,3,1595123339,'202007198169672',34,34,'A1-102','10001','电脑无法播放在线视频','111111',1),(54,1595234131,0,1,0,'202007201900700',0,75,'A1-202','10001','xHLWn','',1),(55,1595236095,0,1,0,'202007205130767',0,34,'A1-102','10001','电脑无法播放在线视频','111111',1),(56,1595238167,0,3,1595238187,'202007207157140',34,34,'A1-102','10001','电脑无法播放在线视频','111111',0),(57,1595238269,0,1,0,'202007209088270',0,34,'A1-102','10001','电脑无法播放在线视频','111111',0),(58,1595245965,1596378930,5,1596378930,'202007205478993',0,34,'A1-102','10001','电脑无法播放在线视频','111111',0),(60,1596036424,0,1,0,'202007294109545',0,75,'A1-202','10001','Z[6ueRo','',0),(61,1596377308,1596377655,5,1596377655,'202008028768214',0,75,'A1-202','10001','A0S$','',1);
/*!40000 ALTER TABLE `repair_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_log`
--

DROP TABLE IF EXISTS `repair_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `log_content` longtext NOT NULL COMMENT '维护日志',
  `repair_item_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='报修表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_log`
--

LOCK TABLES `repair_log` WRITE;
/*!40000 ALTER TABLE `repair_log` DISABLE KEYS */;
INSERT INTO `repair_log` VALUES (1,1582726385,0,1,0,'do ut dolore','202002244814418'),(2,1582727210,0,1,0,'111','202002245796304'),(3,1582727436,0,1,0,'1213','202002264965758'),(4,1582727503,0,1,0,'543252346','20200226353903'),(5,1582727545,0,1,0,'1231241','202002264750698'),(6,1582727612,0,1,0,'3413','202002262469835'),(7,1590317641,0,1,0,'测试','202004088320508');
/*!40000 ALTER TABLE `repair_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_table` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '1-签到 2-签退',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `user_id` mediumint(8) unsigned NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='值班表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_table`
--

LOCK TABLES `time_table` WRITE;
/*!40000 ALTER TABLE `time_table` DISABLE KEYS */;
INSERT INTO `time_table` VALUES (1,1582715968,0,2,1582715969,29),(3,1583208362,0,2,1584860100,2),(4,1585548877,0,2,1590314215,2),(5,1590300648,0,2,1590317756,34),(6,1590318578,0,1,0,6),(7,1590318640,0,2,1595090617,34),(8,1595090618,0,2,1595177919,34),(9,1595177921,0,1,0,34);
/*!40000 ALTER TABLE `time_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_feedback` (
  `id` mediumint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建日期',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '修改日期',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除日期',
  `state` tinyint(4) unsigned NOT NULL COMMENT '状态',
  `orderer` mediumint(5) unsigned NOT NULL COMMENT '反馈人',
  `receiver` mediumint(5) unsigned NOT NULL COMMENT '处理人',
  `question` varchar(255) NOT NULL COMMENT '问题描述',
  `order_phone` varchar(255) NOT NULL COMMENT '反馈人电话',
  `feedback_id` varchar(255) NOT NULL COMMENT '反馈id，自动生成，规则同报修表的repair_item_id相同',
  `answer` varchar(255) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户反馈表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedback`
--

LOCK TABLES `user_feedback` WRITE;
/*!40000 ALTER TABLE `user_feedback` DISABLE KEYS */;
INSERT INTO `user_feedback` VALUES (1,1590318826,0,0,2,34,75,'我的反馈意见','0','202005246969673','aliquip'),(2,1595061433,0,0,1,75,75,'esse Excepteur deserunt ut','0','202007183391275','culpa ut'),(3,1595061440,0,0,1,75,75,'反馈2','0','20200718792647','id ut laboris'),(4,1595061448,0,0,1,75,75,'反馈3','0','202007188303737','id ut laboris'),(5,1595085516,0,0,1,75,75,'ullamco sunt','0','202007186682852','culpa ut'),(6,1595085624,0,0,2,75,75,'ullamco sunt','0','202007184166269','aliquip'),(7,1595085795,0,0,1,75,75,'ullamco sunt','0','202007185590861','culpa ut'),(8,1595085903,0,0,1,75,75,'ullamco sunt','0','202007183772273','culpa ut'),(9,1595085955,0,0,1,75,75,'ullamco sunt','0','202007185701271','culpa ut'),(10,1595085986,0,0,1,75,75,'ullamco sunt','0','202007186177547','culpa ut'),(11,1595086018,0,0,1,75,0,'ullamco sunt','0','202007188595422',NULL),(12,1595086146,0,0,1,75,0,'ullamco sunt','0','202007186290227',NULL),(13,1595086251,0,0,1,75,0,'ullamco sunt','0','202007181669786',NULL),(14,1595087717,0,0,1,75,0,'ullamco sunt','0','202007187186465',NULL),(15,1595087823,0,0,1,34,0,'如家度假酒店','0','202007183938690',NULL),(16,1595087839,0,0,1,75,0,'ullamco sunt','0','202007189900276',NULL);
/*!40000 ALTER TABLE `user_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
  `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
  `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
  `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳',
  `openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信小程序登录后返回的字段',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信头像',
  `identity_id` bigint(20) NOT NULL COMMENT '学号/工号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注意加密后再保存',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设置成varchar防止一些特殊电话',
  `role` tinyint(4) unsigned NOT NULL COMMENT '1-普通人员, 4-维护人员, 5-课室团队负责人, 6-课室管理员 , 7-老师, 9-超级管理员',
  `session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信小程序登录后返回的字段',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_info_id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (2,1582553979,0,1,1582708330,'o0Dsd5I_qoHh1P_BRI_uHaSQVF3U','https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK0kjI5zo1HzSLnqictVdjibvDpQWNdG7ce4cThqicL7kMe0U3lYjmeu8mia5MBVkGBp7SZ0f746MvPCA/132',201710096059,'张裕虎','TinsFox','$argon2i$v=19$m=65536,t=10,p=1$2i2Jcd8Zh+XMClVRW1vMTQ$hsmCvnN7ImwSAA93Xa7X/GDhaXRmjZ31oMzXRYPOjJ4','13924387832',9,'tPP6RuOM9fle2o5ztqCSeQ=='),(3,1582605453,0,1,1584036806,'o0Dsd5GOA0Qk9vV02Uclo0kuoHmk','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$bqIF1jPsSRcjzGGqO+tTvQ$BJo5Oe/bNFEyKgtZnfbi/8COzJ00apFJzvxNeDy4vS8','',1,'3QfH8elDKh7QOzYY94y3fA=='),(4,1582605453,0,1,1584036924,'o0Dsd5GOA0Qk9vV02Uclo0kuoHmk','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$N323u0mqkuzk7GWvU2pYlQ$KiMFX8uzG2tfyaqsrzVWmNLxV2hEZegFsehwvqF8DfI','',1,'3QfH8elDKh7QOzYY94y3fA=='),(5,1582610211,0,1,0,'o0Dsd5FL-U_aV1VfzWLW-7TWc9UM','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$oKj/uzXz19cpkJq8cHnXIg$ELW8MYUPpBw4JAJRUvkW/qEn+ZF9KnJytya8dBrFRZU','',1,'LGHr853zjpFfMtWJWPrk7Q=='),(7,1582637773,0,1,1582713174,'o0Dsd5IaNJI7ph1F5NmsFQjMz6nU','',201710089161,'陈金辉','','$argon2i$v=19$m=65536,t=10,p=1$pPiZKCbSoL18K5rz7oZ+YQ$AISOb5F2qxEyUMfAA0CYsYCjmXeu3fx7d4foMzGE3GQ','13149046256',4,'OtDYOWOObLne7wl8chh5ZQ=='),(8,1582637820,0,1,0,'o0Dsd5FYXfVPtB8FohFeum4NznVk','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$DtymZdfg5cFREErxQPoHKg$330htokQyKOxUgPduq0AYc2KcVk/llP2+uC+LE9BkBw','',1,'bWwU0kEWYOXgZYPU/8o4jw=='),(9,1582638308,0,1,1583995884,'o0Dsd5D89mgtXFwRn279S9iIHDAM','',201910098218,'周显龙','','$argon2i$v=19$m=65536,t=10,p=1$SIU8TsiQbz326zDk4znKwg$rPMPh/LijhVuv79uF8+dmvKt8ZMZ4WE/fNfXvIxMwtY','15768150291',1,'bcrasmWMsIOQZeIoChHClQ=='),(10,1582638314,0,1,1583995226,'o0Dsd5Ox_R77iF8PTHeWSwsQyJj8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$fPy3fmWcWDngIdJ/zwuQpA$Uzucoy/VrhPI1r2Gb8ab9SZ1VV06ng6d3G147CDLr0o','',1,'fvv9LTj2xXyDco+bgF6B7A=='),(11,1582638319,0,1,1584036732,'o0Dsd5Gw2xZkAZhUpSCTpnpEgAPQ','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$ou5b/PB5XrscLqMszWfxuw$HCBHd7GAMqOzgKRjHmxWdBAo7ucZPbrkigfDjBE0RBo','',1,'kKABryA4kxUXaB43d8RPMQ=='),(12,1582638418,0,1,1582713332,'o0Dsd5FXDkvVw3F5XtGyR2Odgd4k','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$TTs7YwkOzRN/BIqmpga3Hw$n6yWUqN3e1/BHAvQyoPpAgXOAMZG6VABMn+pQAp/UHc','',4,'gAFZfgPbhhf3U7VptEncdA=='),(13,1582638446,0,1,1582714650,'o0Dsd5CnjFZ6R4IV_VNCGIQ_0-5A','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$PuUgi3mkD7Wjs/gfXkxvnA$tJPfbkE6Pl7nKIdl3UWL/5uqaRAb64qNgUn6iw/0MQI','',4,'fkFwxEh43jKlmB/Zr6aHhQ=='),(14,1582638881,0,1,0,'o0Dsd5HB1eWVnjPaeAjvN8dyklF8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$vhpCXVybiIEqLNl3LORfBw$R1FmODm1i79fKngY5Z0dl+i4WY2IRly0DK0Zc4ymTOo','',1,'yPLd7N9syjuzEQUIu2rtRA=='),(15,1582639329,0,1,0,'o0Dsd5HPyoBb6YqDocqxO5vVbffM','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$S2/AIvjszguUfjrqziTTXQ$vhXwBZ9xeIEbUM9Dkja7teI0jBnR5byoDAQgJyVs4yY','',1,'EEYlbIBAbxSzdCoy3JBnWw=='),(16,1582639396,0,1,0,'o0Dsd5HzZdPqtNIn2w-G-amIfbZE','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$Aa7KzyZhDNQgmQLGroTgSQ$UJOxjrvsT/9kq+BXgwhwwsixm4U0bYMQBY3kvLotrXI','',1,'H+tSgj9GYB5Y+5886+R9rQ=='),(17,1582647014,0,1,0,'o0Dsd5CD0zHxnbaKshuDBoefAOPU','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$jg8N5ObDHr1fqwYzsd0zRw$SMlcGGkP4NKGE7TOyuj8dWdxZ1JePvq9awyio4aiNzY','',1,'u+WCIyknfW6nzFyW2RWxWQ=='),(18,1582647015,0,1,1582719667,'o0Dsd5CD0zHxnbaKshuDBoefAOPU','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$P2ClGa5t8tyzx+ugK+CFbg$8bJwMKLd5/D3P02pR2/EMd6aMkPnE66R0+hW9RoqGv8','',4,'u+WCIyknfW6nzFyW2RWxWQ=='),(19,1582651047,0,1,1582714980,'o0Dsd5BFl-LtJI9BaG8pZPpfkIL0','',201910089094,'陈同学','','$argon2i$v=19$m=65536,t=10,p=1$rx/NGko0yF6neqWYqHNNUA$TCsi+PIUj4qKjIorJbkMgJ0oiELuiY3zwyRK8VJ7LKY','',4,'9twBKJmoex7Xubgx5YrrIQ=='),(20,1582680709,0,1,1584048920,'o0Dsd5Hc-nQz8RBZm6OmErUbp54c','https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132',0,'','黄秀雄','$argon2i$v=19$m=65536,t=10,p=1$xAFp+43ExH8VC0jenMwBtg$5d2fFsJleD9v4p68I9WQrTLB0dHUBwk1lSoEqO6lBdw','',1,'msdfQ+aWuDwmTW4bhAOy2Q=='),(21,1582709481,0,1,0,'o0Dsd5K5UuiX_bcz8RIFppgVpuus','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$baF+5I0SEWGyD/dcSkVZCw$fy3mJk1tJzJRfrMxaOp0Pwg/bOFj6sYczpmH+HKVz/g','',1,'NjOaP+XlHgpF/RI81WDLwA=='),(22,1582710539,0,1,0,'o0Dsd5EctJcteETuiuV1JR4GAF6o','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$RahggBOBFItbPvz+VEYwDQ$HGoQ8I9xoUUaRup2aPY5OcYHGAUc6KUbW7sfhogXyo0','',1,'YyIQGR+M+OEVKQ2CKTszYw=='),(23,1582714380,0,1,1582716254,'o0Dsd5KXBWppMeRrhNDQDtDH_Cug','',201910089135,'姚冠祺','','$argon2i$v=19$m=65536,t=10,p=1$meJBPFePDes94IINL1MLvQ$7FeR8HakccuZM4QMJx2aWTLEc9mAag4OdPlbvpemZB0','13421858256',4,'AE2mQK8nOurplar3QCteFQ=='),(24,1582714382,0,1,1582716419,'o0Dsd5FF-EGhVCSAd9pmxV-U0I14','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$H4qPwiXVSmDchS6CZ/EN4g$AZREWIEIAl1y0odKDOIik+DmYw5bVAYp9VbYcJo5cfw','',4,'I90d2+onFNVmcuORHqteBA=='),(25,1582714710,0,1,1582714773,'o0Dsd5Gxgf1HtvSF6vwkRr8pcu24','',201910098239,'张锦烽','','$argon2i$v=19$m=65536,t=10,p=1$RjGnB01fZDk/OPtOmWilvQ$2a7znWR9wD2zisYHX8fKmZraGSrN38jKC4Vt5ntDsvA','17607676417',4,'t3nrLY/PFEJfdQyJBBVWZA=='),(26,1582714924,0,1,1582715132,'o0Dsd5Djalxl75LlVelAE-WLXip8','',201910102382,'张梓韩','','$argon2i$v=19$m=65536,t=10,p=1$QNn7EgnMiZ+aVj4c5ol4hA$2s3hVAEjgMVSCp4Bsmd0nWauGmG+hFXNVyfvqempsK0','18823900856',4,'l77Q4sddVdyuITKF8KOi1Q=='),(27,1582715181,0,1,1582715213,'o0Dsd5CUnlnMfz5TbdLMNb5Pj2Z8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$byhpsoQ5Vdn2FSLCSuvJAw$1V7s7ESsvhhtth255u54Zx5xMVapUr+Ss4vUvL5EsMQ','',4,'dBfk3AJW7jDAiKlqMsLopg=='),(28,1582715341,0,1,1582715397,'o0Dsd5HEoC0rePDv4tNBHheT5Y0E','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$XluUx8vt2Z6v3Q5lS6Hjew$Qhiyl4vJaF+pH+QKah2A/gs3RYeMHza4564zPXY24Js','',4,'fWGRehmbsXuzuyqPhMJtgg=='),(29,1582715518,0,1,1582715912,'o0Dsd5Nc00dDLlH-fTZdI_ON-2Qk','',202910102113,'18829961238','','$argon2i$v=19$m=65536,t=10,p=1$M9StVtD5phfbw6r/zFA0mQ$ZNkyCnXr2gyoxu4zxgpS6IJ7HJbZ9SDa9Otbeg5izno','18829961238',4,'Aj2VyJAuy6V/8PsP/+PXnA=='),(30,1582719563,0,1,0,'o0Dsd5Esbj_tFVCNWGE8NgLZqMGg','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$7sjbJ451NESdwPVGwn1BoQ$ZPW3QGB2PqxpKcqjRAw32Shf8hfdjHNlduxkBZTCRSQ','',1,'zgKfWGaOC6HdL3CBOfkssA=='),(31,1582805383,0,1,0,'o0Dsd5PQdbUWI1Hx1X3rkW7Uxaqc','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$1sCCEpg0Ivz3Vf6JdnkWAA$39nWmdhk7rzkCnj8Z5zWES+y37sEO52WDF4Si2SuxTM','',1,'3FQ6wjsiL86xcdY64K6t/g=='),(32,1582870092,0,1,0,'o0Dsd5Ax64WtXv8L4TTLMZcFT8mM','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$iGCnjTofQ+wT+W8oY8zZ1g$gVC+Ug3yB1PJSt82u6hzntUIANMwrj+zL9YZv+5jkSA','',1,'wnoJhvPbtnKtLvhIXaaXRQ=='),(33,1582880143,0,1,0,'o0Dsd5DgOe61y5mykeFQEAMQtRDo','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$VTCREfI5lNr240hKNKGJqw$N6JoZmnzu51BKPjYvGNeQ1zxXqOmsVE4P0qLRwcYvlA','',1,'S3ScZBe5FWbT8IWC3CN26Q=='),(34,1582889523,0,1,1595082612,'o0Dsd5IoeVW7FS1kwcgSpm6AwD7M','https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELXuPagh2OzcMpQsEOJnicwlgNDu8TdfAF3eoQq97AqjXiawpBgQXoMpZXX6d9tIQMXIzEIg8cyej9A/132',201810098236,'陈锐','魔法はまだ解けない','$argon2i$v=19$m=65536,t=10,p=1$dqy4NdnNFQGYJvsPzhtNUg$TeZPTrQcgSy7uxrnaO8P+WU2C16Tuu91qvv6MMLMJFQ','111111',9,'BLcBmCJlk3NkbzgTQyBXgA=='),(35,1582952019,0,1,0,'o0Dsd5BZ4ICHe_XxW8DmQaLE9wqg','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$dGDS2zZa24RUaWJrho9IYQ$k4AFc7oBn+FfQF5jdWcpwosmUwVy+oLHD94H4vCrzUk','',1,'tUngpE+CDPucNBmLd7aVPQ=='),(36,1582952698,0,1,0,'o0Dsd5KOnUEOTbjU-KtW-sxOy_zM','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$8X6wm7TXtoRnANjnj5e09A$2cr1/80+wZCXImmD6225lu1IxkjgXYcSXxO9o8vZre8','',1,'SGeXvK0GKxTdH5dzVRjMkg=='),(37,1582956462,0,1,0,'o0Dsd5L2IagwK5ZC3cP2KAZB-4Us','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$OEYy9rhrhzikrnDvYF0x/A$0SJo3Fzceo5J1MP6Ji3XbUhvp1Cw6fYNqGCr4+mAaLk','',1,'gH0u3RHnp0KW7WTMNLJMVg=='),(38,1582984926,0,1,0,'o0Dsd5KGPqfUT6h5n90rLbCI0Jxo','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$HVkWhvVQLX1CgY02NCABng$6cs6ggN6Gj4CoMh44BjE6UXad+Kz9jhfywccIu3mdhk','',1,'GQ1ZIwtgobnVVB6jQj1iyg=='),(39,1583020901,0,1,1583021045,'o0Dsd5P8DPNjN18RodiMv4o-hS6s','https://wx.qlogo.cn/mmhead/b9yyXYENSe2N1Kf3e9PYeFibkp7829I0S5cBXrt2mYRw/132',0,'','陈静维','$argon2i$v=19$m=65536,t=10,p=1$uhi7uQMY/rZPc/1ommKCgA$jPkymGlrbWoBwu+CanrG4CUYiKq1MFkn+XVCOQhTKY0','',1,'7l2Hz/hiFnvhREa4AvbAVA=='),(40,1583053789,0,1,0,'o0Dsd5Eq6u1p85UlAXFVDI90CHvw','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$wlkLG5rY4TDMRkxuJegGsQ$kdJrCOQ/jAud+YNy339QY4apszdDfeNNQH3xtMv34Ww','',1,'hDvVyDzW9OZ6rU6vUDJmuw=='),(41,1583079298,0,1,0,'o0Dsd5AVsMdTzzAViJe4-dP3ILqI','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$Wgn4W1uefjppxXG7v3Qoug$egLBU7BJYotnI9aoHm2irgb9Um9Rc0nP1aA7kRCDIy4','',1,'IC8Ef9Qa+dMI39D2svID1w=='),(42,1583082026,0,1,0,'o0Dsd5MiGX1BAo0h71c1bL6WQCP8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$njmQUt6QyShRJfB93LpZCg$rUxHo850GFoFFG8gWmJCfFvt/9y7P9yUzdrSQFaijVI','',1,'nmeFn/GojuriAzAlB+Gp1w=='),(43,1583150871,0,1,0,'o0Dsd5N1si6XbibZlFDk_5RjBLUY','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$DjJn9MypBaBckurNPEbr3w$86Voq9v8ZFbr+jmBUm0Fzr8NJbvCZzkg7gCZkIFgJ4M','',1,'rOKbr0IP2NY6Isv9TKqd3Q=='),(44,1583252497,0,1,0,'o0Dsd5BK5bZ6-_Hc1dasU8VPwppA','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$HI7tdiFIY3ULDHosdk4N+w$80Jc+Nl0th7WFRptZChSyPWghJscnCghNynZSnXY/NE','',1,'V12Um2pX6qT7MSMHoXzqyg=='),(45,1583305173,0,1,0,'o0Dsd5HtX6GeO2WmIndCrfQ6LF7s','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$c9ieWlqIct7isqaH7l0tow$WeclPip7zpevZcGxUPTWFvDvDDgEkDo9Uf7iabXTRi8','',1,'wQ2Mh/FfNXJFOpFv5ybH2w=='),(46,1583307629,0,1,0,'o0Dsd5KyEX-kEqoa1rk1aRZVa2lo','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$SZ1+z1A5T3pQfYxhBIfQkQ$T+HO/Jlr71mQ77qw+fALoAbMTC+brUIlFePe+8NxX9s','',1,'bPSy7L4M+CFCXONDzQbDiA=='),(47,1583307638,0,1,0,'o0Dsd5GnzHclaVpaO30w_Bj-Pz4w','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$sQl6oX184quFJvYxPRu4LQ$gXaCtTKyQo0mqdMN7f0SbgwWtiaDnfimZkhqzaxRfwg','',1,'9VVgHUvTJF51nzwZV93jsQ=='),(48,1583374582,0,1,1583374729,'o0Dsd5CC2X0mfg-RX0LzTFSfyJyw','https://wx.qlogo.cn/mmhead/flS6dJAtuf0ibGGRo2CeE12mBicMgNbx90MdE5RWDRPA0/132',0,'','蒋维茜','$argon2i$v=19$m=65536,t=10,p=1$dyrk2pEJ26zWPqpW2Dg3qw$uBuxIy1Yz5M8TSGYd2pahEiLQX/DM+Few2b3Y5VyFfs','',1,'YVmwnD1GQ3k/5WXjqKKizQ=='),(49,1583402263,0,1,0,'o0Dsd5Pa0R2f4u6uGwLd60v4J1ZM','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$AuGkZAfIJcvaHBGeVpG+4w$C1Uk1tydtCm1Zx/j75HeDl9+5qadY8VmuRP8Kl7Fdzg','',1,'sqHwfNFv/CxZcHtQ5zj9pA=='),(50,1583504765,0,1,0,'o0Dsd5C1k9ewHNybklzN1byREaEo','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$RAH/keYUKWoJILwBYigDog$UD1P5JaWNgmpOY90v/1gA7mPQFb4VaPKJ01zHElK0OY','',1,'EeLST1FherVQ3IZ2yt5+hQ=='),(51,1583570218,0,1,0,'o0Dsd5HGeJQLtf4nWw3D1KQLvZ4A','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$nucvCuNDglcIfIarM689mw$iX7zuzYARsc3iSqOnNaozG9sFr5Gy1gQtNSUT5UY8uA','',1,'9j6xOGJt1NJ9a+jRhc+v8A=='),(52,1583590323,0,1,0,'o0Dsd5D03eCXT1_6d1rc5vy7wBUE','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$VinidyPODy2BxjISQyV39w$bocvP8mUpOuXkIFup9zWcORkkmvTyfJXEVnZCtZs3GY','',1,'pFY0NagOlPy0bnMtHofbDA=='),(53,1583930747,0,1,0,'o0Dsd5MWqb83LfN_sHj01Bg_s0yQ','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$NcrN5JcJchnqMARC91mXUw$qskF8aYFEi4xNca50CIpyipN6d+ZwBUUifk7aNEhDUE','',1,'jwfEUutLRm0S9yvuPO9C2w=='),(54,1583977779,0,1,0,'o0Dsd5GQ3FlyV4gBbA1pMgsR6xjw','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$8LlsetX5RshSGtbWsuYCzg$6m0FOk2GMckiDP4vl9vpjG0ZUeM8ahcvnYVe2aU5HDk','',1,'r0p6y/cfFcH2/VPoG50Wmw=='),(55,1583977779,0,1,0,'o0Dsd5GQ3FlyV4gBbA1pMgsR6xjw','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$dYNK1+lFPrunavbF0QQwrQ$F1xrW10CDxDjJWRwk1Zf6MB3STsUOuG1zQbASGfI7wo','',1,'r0p6y/cfFcH2/VPoG50Wmw=='),(56,1583977780,0,1,0,'o0Dsd5GQ3FlyV4gBbA1pMgsR6xjw','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$/fwshDCCmqjQ/TqpNYQFcg$dk10spGdNf7nl9GFIaygGVVCWfVvonpkgXkYMz4xXeA','',1,'r0p6y/cfFcH2/VPoG50Wmw=='),(57,1583981047,0,1,0,'o0Dsd5ITGGofFuO3HpXkWfQq2l_0','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$fLD5328BU6prWDErUb/qRA$03jGCF3IfFUezIAgT4ecMR6d9LKybzXbZYdq9liuDLc','',1,'t8CYPQexHF1AdXJa/ve13Q=='),(58,1583981857,0,1,0,'o0Dsd5DZBYx2ptFm3NyiiBkhFBbc','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$fq5cZ0WSZOJiOU6u5+CsdA$pa8m4afWO1SSTb+oKPby9v7+XqgMIT/ROPSVXJDycAQ','',1,'wc4pRj1+I86RGjs6ES5ubA=='),(59,1584021982,0,1,0,'o0Dsd5KGryG167oySBOhllSvI2H4','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$aae6VJiJpsW4EGqPcJU1aQ$etjgICF7ehBH8nzJVisGoduq4RlnqLVZeGeZgFS5qQU','',1,'lbABn3yuKXH0WJ8WpepXJA=='),(60,1584058072,0,1,0,'o0Dsd5CYMQAfnDs73fx3HVcTgaEs','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$nCp1zhiWUEaxP/cTndeS8A$yJmoLg1sDSK8Txovqy5WOZJiutsISrbHhXXpFYqnXqI','',1,'ZG+lDCHFkJ0mAZHUYb1VPQ=='),(61,1584460534,0,1,0,'o0Dsd5C4AtLAHgOyWACUpV84cDd8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$ypW+SfmMXAZlSyXQP9DNMQ$nOJJxav23WDmrsrS7y0B97QWSUlDycFy1XBr/Z/wRlQ','',1,'Ol2P/sMNsOssZU81TFOksg=='),(62,1584689224,0,1,0,'o0Dsd5I935Pmfu-uOj5f5Wr_mnYs','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$D5Q+bZ8Euios64GQQlwQfA$NCvsOktut/qMQIv9zjH8jaYdRIGnu7pdytvi1xKA0zg','',1,'4IaXGYaMwwgqnNZz+AQpCQ=='),(63,1584804715,0,1,0,'o0Dsd5Bqn1GovGMS570n-SUTKnfw','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$zhVJCeHkhZp2F+w9KGdgag$Cl8gygHMSblFuYpKM3rgeH4OvR5SxTQak8MfntthxOg','',1,'AEPxU9G9hdDmlKVbJa2Vgg=='),(64,1584891992,0,1,0,'o0Dsd5I4oh72VtDBQuHNlBBapRlg','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$JfSoqb9edNyn2pi2vMQAcQ$j+a2LJB8DbeYEoytHrQXq3HWchPaXdvxghwbZBvt/gA','',1,'un23L3fmoXhikYNPun2Sug=='),(65,1584905046,0,1,0,'o0Dsd5MesAcLoRDC0WiUJezQWcoU','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$YI5zCZdS6Ak3yIJQBG39+A$YQwOKVhoMLCLA0qTfDGBczXduTxBDGZMrYKrW0ZHfqs','',1,'HEcje4RDIJyYyv2exzLyOA=='),(66,1584932601,0,1,0,'o0Dsd5ERFSPbWKcxIV2PUn0Koxd8','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$/xSO+rmc7gOY3dqQ7wNziw$ohkUpSRZE6QximPb6q3mdc4n+pXINKl4tY2UI5uL3R8','',1,'TuC6NY5/00hY7vEIgVLMYg=='),(67,1584955462,0,1,0,'o0Dsd5I-U2BeDfqt-tfk79MuArjI','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$XN2hMqTMJbBbULcaRxM0UA$ZjpXAJ4ZnWQZkVBS9NOVJwMC708leHRdUZOH3xURa04','',1,'m3dgKGVC254NvgoQh8NniA=='),(68,1585031466,0,1,0,'o0Dsd5B-P9190qNXpuGLRuapO_BY','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$mgzkG0ZbLO5ggk/80BNxpg$UzFemi/UV0oBWCZWMi8ZXAavuNCYDRfrnFdfR/P3UA4','',1,'79utSurog3O3W43hML5tHg=='),(69,1585176779,0,1,1585176926,'o0Dsd5KvoDH1e6Cc-Aeg2v5JKV8s','https://wx.qlogo.cn/mmhead/Aia5pFwBiaGeatR2wUYBqN6OqpXpXQV7j9BDY6wq8vODE/132',0,'','袁俊宏','$argon2i$v=19$m=65536,t=10,p=1$GRKT7Q3/eckG6lfcy/HkTw$hXrKCT7yLCbSH35g6a23jY+kOX+eUwlgoGbLFhhUJb0','',1,'MG6dnx/dQGiuozPnhN5twQ=='),(70,1585234954,0,1,0,'o0Dsd5PRT9EYAp_tS9QaoaYHrUS4','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$DozMP6TSyANC+sx9Mqcjjg$F3BnvjgHGNDOqEcblgChzAEXuwg2Hx+ID0pyWvBI3nw','',1,'EKQb9AOQ5dt8wLGHMUC4Lw=='),(71,1585289678,0,1,0,'o0Dsd5F49ns0A9IhWH9U0Pn4t9PQ','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$zzViD1XItYI+uN6r6Zz/2A$fzFDIlGd/365Z6e5qglRgj3AUU4u+cultt9YzzLZXmU','',1,'xQC6HzJGPGTjYI7IcoCXGg=='),(72,1585567740,0,1,0,'o0Dsd5O0z4LzRaLrWxJhKx24reo0','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$inCR8Ps6KrZv3cG8JkFwHA$/fVy0UAyyHugUrYOpRfnlKykna23Ka+9FeVS+vmos1Y','',1,'/HAxp2NmS5YngkYvDdkbpQ=='),(73,1585803234,0,1,0,'o0Dsd5C6H3UK3aYr5AQF3u0gCzkc','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$qNqlBjMmJVLr9hNrNv2FIw$nmKTj9bU0fYPVt1txfPU+yV4WluQ7asVAFQYfaPOY9k','',1,'ZZCv1I3S2yllfV9dWUzxqA=='),(74,1585883359,0,1,0,'o0Dsd5OT4PEtXweT8BLXSpGPB8pU','',0,'','','$argon2i$v=19$m=65536,t=10,p=1$4g7im++JDzdr0x16PNAtDA$ADwrid/ECH/7sbvdHEKr/oWxH+5pMN76+d4kkc0iTnc','',1,'HnhZIX3OfsmNnnnNhLtlQg=='),(75,1594604568,0,1,0,'','',2020,'','','$argon2i$v=19$m=65536,t=10,p=1$vRmfOs0CqfXoKZQ85tZ6Og$UI/RUyJB23kB0CKPSjI4K6BCvmbhdWxqogZI3xU08qg','',9,'');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-07 18:28:50
