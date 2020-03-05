-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2020 at 01:20 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_log_tbl`
--

CREATE TABLE `account_log_tbl` (
  `ID` int(11) NOT NULL,
  `User_Name` varchar(225) NOT NULL,
  `Activity` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account_log_tbl`
--

INSERT INTO `account_log_tbl` (`ID`, `User_Name`, `Activity`, `Status`, `Date`, `Time`) VALUES
(1, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:21 PM'),
(2, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:22 PM'),
(3, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:25 PM'),
(4, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:25 PM'),
(5, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:25 PM'),
(6, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:25 PM'),
(7, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:26 PM'),
(8, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:27 PM'),
(9, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:29 PM'),
(10, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:29 PM'),
(11, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/02/2020', '11:30 PM'),
(12, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:31 PM'),
(13, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:22 PM'),
(14, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:24 PM'),
(15, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:29 PM'),
(16, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:39 PM'),
(17, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:58 PM'),
(18, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '10:58 PM'),
(19, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:00 PM'),
(20, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/03/2020', '11:00 PM'),
(21, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:01 PM'),
(22, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:13 PM'),
(23, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:14 PM'),
(24, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:16 PM'),
(25, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:16 PM'),
(26, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:17 PM'),
(27, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:25 PM'),
(28, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:27 PM'),
(29, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:38 PM'),
(30, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:40 PM'),
(31, 'Name', 'Log-in', 'Level', '03/03/2020', '11:56 PM'),
(32, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/03/2020', '11:57 PM'),
(33, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '03/10/2020', '12:01 AM'),
(34, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/10/2020', '12:05 AM'),
(35, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/10/2020', '12:07 AM'),
(36, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/10/2020', '12:11 AM'),
(37, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10/03/2020', '12:33 AM'),
(38, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '10/03/2020', '12:33 AM'),
(39, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '04/03/2020', '12:34 AM'),
(40, 'Name', 'Log-in', 'Level', '04/03/2020', '12:36 AM'),
(41, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '04/03/2020', '12:36 AM'),
(42, 'Name', 'Log-in', 'Level', '4-Mar-2020', '12:48 AM'),
(43, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '4-Mar-2020', '12:48 AM'),
(44, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '4-Mar-2020', '12:50 AM'),
(45, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '4-Mar-2020', '12:54 AM'),
(46, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '4-Mar-2020', '12:54 AM'),
(47, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:56 AM'),
(48, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:57 AM'),
(49, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:58 AM'),
(50, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:59 AM'),
(51, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/10/2020', '1:01 AM'),
(52, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '3/10/2020', '1:01 AM'),
(53, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/10/2020', '1:06 AM'),
(54, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '3/10/2020', '1:06 AM'),
(55, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/15/2020', '1:07 AM'),
(56, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '3/15/2020', '1:08 AM'),
(57, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/16/2020', '1:08 AM'),
(58, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/16/2020', '1:11 AM'),
(59, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '3/16/2020', '1:12 AM'),
(60, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:16 PM'),
(61, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '3/4/2020', '12:34 PM'),
(62, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '3/4/2020', '12:34 PM'),
(63, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:34 PM', '3/4/2020'),
(64, 'Name', 'Log-out', 'Level', '3/4/2020', '1:35 PM'),
(65, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:38 PM', '3/4/2020'),
(66, 'Name', 'Log-out', 'Level', '3/4/2020', '1:38 PM'),
(67, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:45 PM', '3/4/2020'),
(68, 'Name', 'Log-out', 'Level', '3/4/2020', '1:45 PM'),
(69, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:45 PM', '3/4/2020'),
(70, 'Name', 'Log-out', 'Level', '3/4/2020', '1:45 PM'),
(71, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:53 PM', '3/4/2020'),
(72, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:32 PM', '3/4/2020'),
(73, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:32 PM', '3/4/2020'),
(74, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:32 PM', '3/4/2020'),
(75, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:33 PM', '3/4/2020'),
(76, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:33 PM', '3/4/2020'),
(77, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:33 PM', '3/4/2020'),
(78, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:34 PM', '3/4/2020'),
(79, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:34 PM', '3/4/2020'),
(80, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:35 PM', '3/4/2020'),
(81, 'Name', 'Log-out', 'Level', '3/4/2020', '5:36 PM'),
(82, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:37 PM', '3/4/2020'),
(83, 'Name', 'Log-out', 'Level', '3/4/2020', '5:38 PM'),
(84, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:49 PM', '3/4/2020'),
(85, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '5:59 PM', '3/4/2020'),
(86, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:04 PM', '3/4/2020'),
(87, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:12 PM', '3/4/2020'),
(88, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:29 PM', '3/4/2020'),
(89, 'Name', 'Log-out', 'Admin', '3/4/2020', '6:30 PM'),
(90, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:31 PM', '3/4/2020'),
(91, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:34 PM', '3/4/2020'),
(92, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:38 PM', '3/4/2020'),
(93, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:43 PM', '3/4/2020'),
(94, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:44 PM', '3/4/2020'),
(95, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:46 PM', '3/4/2020'),
(96, 'Name', 'Log-out', 'Admin', '3/4/2020', '6:46 PM'),
(97, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '6:49 PM', '3/4/2020'),
(98, 'Name', 'Log-out', 'Admin', '3/4/2020', '6:50 PM'),
(99, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '8:10 PM', '3/4/2020'),
(100, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '8:14 PM', '3/4/2020'),
(101, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '8:21 PM', '3/4/2020'),
(102, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:10 PM', '3/4/2020'),
(103, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:12 PM', '3/4/2020'),
(104, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:33 PM', '3/4/2020'),
(105, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:39 PM', '3/4/2020'),
(106, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:48 PM', '3/4/2020'),
(107, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:55 PM', '3/4/2020'),
(108, 'Name', 'Log-out', 'Admin', '3/4/2020', '10:55 PM'),
(109, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '10:56 PM', '3/4/2020'),
(110, 'Name', 'Log-out', 'Admin', '3/4/2020', '11:12 PM'),
(111, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '11:21 PM', '3/4/2020'),
(112, 'jLabel2', 'Log-in', 'jLabel3', '11:27 PM', '3/4/2020'),
(113, 'Name', 'Log-out', 'jLabel3', '3/4/2020', '11:27 PM'),
(114, 'jLabel2', 'Log-in', 'jLabel3', '11:28 PM', '3/4/2020'),
(115, 'Name', 'Log-out', 'jLabel3', '3/4/2020', '11:28 PM'),
(116, 'jLabel2', 'Log-in', 'jLabel3', '11:28 PM', '3/4/2020'),
(117, 'Name', 'Log-out', 'jLabel3', '3/4/2020', '11:28 PM'),
(118, 'jLabel2', 'Log-in', 'jLabel3', '11:32 PM', '3/4/2020'),
(119, 'jLabel2', 'Log-in', 'jLabel3', '11:32 PM', '3/4/2020'),
(120, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '11:32 PM', '3/4/2020'),
(121, 'Name', 'Log-out', 'Admin', '3/4/2020', '11:35 PM'),
(122, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '11:37 PM', '3/4/2020'),
(123, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '12:14 AM', '3/5/2020'),
(124, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '12:38 AM', '3/5/2020'),
(125, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:04 AM', '3/5/2020'),
(126, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:08 AM', '3/5/2020'),
(127, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:14 AM', '3/5/2020'),
(128, 'Name', 'Log-out', 'Admin', '3/5/2020', '1:14 AM'),
(129, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:17 AM', '3/5/2020'),
(130, 'Name', 'Log-out', 'Admin', '3/5/2020', '1:18 AM'),
(131, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:19 AM', '3/5/2020'),
(132, 'Name', 'Log-out', 'Admin', '3/5/2020', '1:24 AM'),
(133, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '1:37 AM', '3/5/2020'),
(134, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:06 AM', '3/5/2020'),
(135, 'jLabel2', 'Log-in', 'jLabel3', '2:08 AM', '3/5/2020'),
(136, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:08 AM', '3/5/2020'),
(137, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:17 AM', '3/5/2020'),
(138, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:28 AM', '3/5/2020'),
(139, 'Ababon, Sheenn Otaza', 'Log-in', 'Admin', '2:35 AM', '3/5/2020'),
(140, 'Name', 'Log-out', 'Admin', '3/5/2020', '2:37 AM');

-- --------------------------------------------------------

--
-- Table structure for table `author_tbl`
--

CREATE TABLE `author_tbl` (
  `ID` int(11) NOT NULL,
  `Author_ID` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `borrowed_tbl`
--

CREATE TABLE `borrowed_tbl` (
  `Trans_Code` varchar(225) NOT NULL,
  `Library_ID` varchar(225) NOT NULL,
  `Full_Name` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Book_Price` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Fines` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Borrowed_Date` varchar(225) NOT NULL,
  `Return_Date` varchar(225) NOT NULL,
  `Remarks` varchar(225) NOT NULL,
  `Payable` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrowed_tbl`
--

INSERT INTO `borrowed_tbl` (`Trans_Code`, `Library_ID`, `Full_Name`, `Status`, `Book_title`, `Book_Price`, `Classification`, `Fines`, `Quantity`, `Borrowed_Date`, `Return_Date`, `Remarks`, `Payable`) VALUES
('Transaction-20', 'LIB-ID-1', 'RABUSA, ANGELIE', 'Student', 'BOOK-3', '245', 'Language', '5', '1', '3/9/2020', '3/12/2020', 'Borrowed', '40.0'),
('Transaction-21', 'LIB-ID-1', 'RABUSA, ANGELIE', 'Student', 'BOOK-4', '213', 'Computer Science, Information and General Works', '5', '1', '3/4/2020', '3/7/2020', 'Borrowed', '15.0');

-- --------------------------------------------------------

--
-- Table structure for table `borrower_tbl`
--

CREATE TABLE `borrower_tbl` (
  `Library_ID` varchar(225) NOT NULL,
  `First_Name` varchar(225) NOT NULL,
  `Middle_Name` varchar(225) NOT NULL,
  `Surname` varchar(225) NOT NULL,
  `ID_Type` varchar(225) NOT NULL,
  `ID_No` varchar(225) NOT NULL,
  `Course` varchar(225) NOT NULL,
  `Year` varchar(225) NOT NULL,
  `Address` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Birth_Date` varchar(225) NOT NULL,
  `Photo` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrower_tbl`
--

INSERT INTO `borrower_tbl` (`Library_ID`, `First_Name`, `Middle_Name`, `Surname`, `ID_Type`, `ID_No`, `Course`, `Year`, `Address`, `Status`, `Birth_Date`, `Photo`) VALUES
('LIB-ID-1', 'ANGELIE', 'MARIE', 'RABUSA', 'Student ID', '01912312323', 'BSC', '3rd Year', '345VAWDFA DSFASDFSDFADSF', 'Student', '02-27-2000', NULL),
('LIB-ID-4', 'Cris', 'C ', 'Crisyy', 'Employee ID', '1511600080', 'BSIT', '3rd Year', 'Polomolok South Cotabato', 'Faculty', '02-11-2016', NULL),
('LIB-ID-3', 'Junenor', 'Otaza', 'Ababon', 'Student ID', '15116990087', 'BSCS', '4th Year', 'Polomolok South Cotabato', 'Student', '02-11-2009', NULL),
('LIB-ID-2', 'Lee Ann', ' C.', 'Pacheco', 'Student ID', '15116000078', 'BSIT', '4th Year', 'Polomolok South Cotabato', 'Student', '02-10-2000', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `brrdlog_tbl`
--

CREATE TABLE `brrdlog_tbl` (
  `ID` int(11) NOT NULL,
  `Full_Name` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Borrowed_Date` varchar(225) NOT NULL,
  `Encoder` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brrdlog_tbl`
--

INSERT INTO `brrdlog_tbl` (`ID`, `Full_Name`, `Status`, `Book_title`, `Classification`, `Borrowed_Date`, `Encoder`, `Date`) VALUES
(9, 'Crisyy, Cris', 'Faculty', 'SYSTEMS ANALYSIS AND DESIGN', 'Computer Science, Information and General Works', '3/1/2020', 'Ababon, Sheenn Otaza', '03/01/2020'),
(10, 'Ababon, Junenor', 'Student', 'SOFTWARE ENGINEERING', 'Computer Science, Information and General Works', '3/1/2020', 'Ababon, Sheenn Otaza', '03/01/2020'),
(11, 'Crisyy, Cris', 'Faculty', 'BOOK-1', 'Computer Science, Information and General Works', '3/2/2020', 'Ababon, Sheenn Otaza', '03/02/2020'),
(12, 'Crisyy, Cris', 'Faculty', 'BOOK-4', 'Computer Science, Information and General Works', '3/2/2020', 'Ababon, Sheenn Otaza', '03/02/2020'),
(13, 'Crisyy, Cris', 'Faculty', 'BOOK-3', 'Language', '3/2/2020', 'Ababon, Sheenn Otaza', '03/02/2020'),
(14, 'RABUSA, ANGELIE', 'Student', 'BOOK-3', 'Language', '3/3/2020', 'Ababon, Sheenn Otaza', '03/03/2020'),
(15, 'Ababon, Junenor', 'Student', 'BOOK-5', 'Language', '3/3/2020', 'Ababon, Sheenn Otaza', '03/03/2020'),
(16, 'RABUSA, ANGELIE', 'Student', 'BOOK-3', 'Language', '3/3/2020', 'Ababon, Sheenn Otaza', '04/03/2020'),
(17, 'Crisyy, Cris', 'Faculty', 'BOOK-4', 'Computer Science, Information and General Works', '3/3/2020', 'Ababon, Sheenn Otaza', '4-Mar-2020'),
(18, 'Pacheco, Lee Ann', 'Student', 'BOOK-5', 'Language', '3/3/2020', 'Ababon, Sheenn Otaza', '4-Mar-2020'),
(19, 'Crisyy, Cris', 'Faculty', 'BOOK-3', 'Language', '3/9/2020', 'Ababon, Sheenn Otaza', '3/10/2020'),
(20, 'RABUSA, ANGELIE', 'Student', 'BOOK-3', 'Language', '3/9/2020', 'Ababon, Sheenn Otaza', '3/10/2020'),
(21, 'RABUSA, ANGELIE', 'Student', 'BOOK-4', 'Computer Science, Information and General Works', '3/4/2020', 'Name', '3/4/2020'),
(22, 'RABUSA, ANGELIE', 'Student', 'BOOK-5', 'Language', '3/4/2020', 'Name', '3/4/2020');

-- --------------------------------------------------------

--
-- Table structure for table `cancel_tbl`
--

CREATE TABLE `cancel_tbl` (
  `ID` int(11) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Purchase_No` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Station` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `classification_tbl`
--

CREATE TABLE `classification_tbl` (
  `ID` int(11) NOT NULL,
  `Class_Number` varchar(225) NOT NULL,
  `Classname` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classification_tbl`
--

INSERT INTO `classification_tbl` (`ID`, `Class_Number`, `Classname`) VALUES
(11, '000', 'Computer Science, Information and General Works'),
(12, '200', 'Religion'),
(13, '300', 'Social Sciences'),
(14, '400', 'Language'),
(15, '500', 'Pure Science'),
(16, '600', 'Technology'),
(17, '700', 'Arts and Recreation'),
(18, '900', 'History and Geography');

-- --------------------------------------------------------

--
-- Table structure for table `course_tbl`
--

CREATE TABLE `course_tbl` (
  `ID` int(11) NOT NULL,
  `Course_Title` varchar(225) DEFAULT NULL,
  `Course` varchar(225) NOT NULL,
  `Description` varchar(225) DEFAULT NULL,
  `Years` varchar(225) DEFAULT NULL,
  `Status` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `damage_tbl`
--

CREATE TABLE `damage_tbl` (
  `ID` int(11) NOT NULL,
  `ISBN_No` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Remarks` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `damage_tbl`
--

INSERT INTO `damage_tbl` (`ID`, `ISBN_No`, `Book_title`, `Classification`, `Status`, `Quantity`, `Remarks`, `Date`, `Time`) VALUES
(13, 'ISBN3', 'BOOK-3', 'Language', '--Select Status--', '-1', 'Tear apart', '3/4/2020', '10:12 PM'),
(14, 'ISBN5', 'BOOK-5', 'Language', '--Select Status--', '0', 'wet', '3/4/2020', '10:13 PM');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_tbl`
--

CREATE TABLE `delivery_tbl` (
  `ID` int(11) NOT NULL,
  `Delivery_No` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Purchase_No` varchar(225) NOT NULL,
  `Purchase_Date` varchar(225) NOT NULL,
  `Delivery_Date` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `delivery_tbl`
--

INSERT INTO `delivery_tbl` (`ID`, `Delivery_No`, `Book_title`, `Author`, `Classification`, `Quantity`, `Price`, `Purchase_No`, `Purchase_Date`, `Delivery_Date`) VALUES
(5, 'DR000111', 'BOOK-1', 'dasda', 'Computer Science, Information and General Works', '5', '500', 'PO-6', '03/01/2020', '03/01/2020'),
(6, 'DR0000222', 'BOOK-2', 'dasdag', 'Religion', '10', '222', 'PO-6', '03/01/2020', '03/01/2020'),
(7, 'DR09380', 'BOOK-3', 'dasda3', 'Language', '5', '245', 'PO-6', '03/01/2020', '03/01/2020'),
(8, 'DR123983', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '5', '213', 'PO-6', '03/01/2020', '03/01/2020'),
(9, 'wqeqweqw', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '5', '200', 'PO-7', '03/01/2020', '03/01/2020'),
(10, 'DR0111111', 'BOOK-2', 'dasda3', 'Social Sciences', '5', '255', 'PO-8', '03/01/2020', '03/01/2020'),
(11, 'DR000111', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '5', '200', 'PO-9', '03/01/2020', '03/01/2020'),
(12, 'DR00111', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '5', '200', 'PO-10', '03/01/2020', '03/01/2020'),
(13, 'dasdasda', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '5', '200', 'PO-11', '03/02/2020', '03/02/2020'),
(14, 'dsadadas', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '5', '213', 'PO-12', '03/02/2020', '03/02/2020');

-- --------------------------------------------------------

--
-- Table structure for table `holding_tbl`
--

CREATE TABLE `holding_tbl` (
  `ID` int(11) NOT NULL,
  `ISBN_No` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `On_Hand` varchar(225) NOT NULL,
  `Borrowed` varchar(225) NOT NULL,
  `Damage` varchar(225) NOT NULL,
  `Total_Holding` varchar(225) NOT NULL,
  `Encoder` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `holding_tbl`
--

INSERT INTO `holding_tbl` (`ID`, `ISBN_No`, `Book_title`, `Classification`, `On_Hand`, `Borrowed`, `Damage`, `Total_Holding`, `Encoder`, `Date`, `Time`) VALUES
(12, 'ISBN-4', 'BOOK-4', 'Computer Science, Information and General Works', '6', '0', '4', '6', '', '', ''),
(13, 'ISBN3', 'BOOK-3', 'Language', '7', '5', '-1', '5', '', '', ''),
(14, 'ISBN5', 'BOOK-5', 'Language', '0', '-3', '0', '10', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `invlog_tbl`
--

CREATE TABLE `invlog_tbl` (
  `ISBN_No` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Encoder` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL,
  `Action` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invlog_tbl`
--

INSERT INTO `invlog_tbl` (`ISBN_No`, `Book_title`, `Classification`, `Encoder`, `Date`, `Time`, `Action`) VALUES
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '9:02 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '9:02 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '10:12 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '10:15 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '10:21 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/01/2020', '10:22 PM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/02/2020', '10:54 AM', 'Stock-in'),
('', '', '', 'Ababon, Sheenn Otaza', '03/02/2020', '9:48 PM', 'Stock-in'),
('ISBN3', 'BOOK-3', 'Language', 'Name', '3/4/2020', '10:11 PM', 'Stock-out'),
('ISBN3', 'BOOK-3', 'Language', 'Name', '3/4/2020', '10:12 PM', 'Stock-out'),
('ISBN5', 'BOOK-5', 'Language', 'Name', '3/4/2020', '10:13 PM', 'Stock-out');

-- --------------------------------------------------------

--
-- Table structure for table `order_tbl`
--

CREATE TABLE `order_tbl` (
  `No` int(11) NOT NULL,
  `Purchase_No` varchar(225) NOT NULL,
  `Purchase_Date` varchar(225) NOT NULL,
  `Request_Date` varchar(225) NOT NULL,
  `Supplier` varchar(225) NOT NULL,
  `Book_Title` varchar(225) NOT NULL,
  `Book_Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Total` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_tbl`
--

INSERT INTO `order_tbl` (`No`, `Purchase_No`, `Purchase_Date`, `Request_Date`, `Supplier`, `Book_Title`, `Book_Author`, `Classification`, `Price`, `Quantity`, `Total`, `Status`) VALUES
(1, 'PO-16', '03-31-2020', '3/5/2020', 'BOOKBUYPH', 'BOOK-5', 'sdad', 'Language', '500', '1', '500', 'Existing');

-- --------------------------------------------------------

--
-- Table structure for table `po_log_tbl`
--

CREATE TABLE `po_log_tbl` (
  `ID` int(11) NOT NULL,
  `Purchase_code` varchar(225) NOT NULL,
  `Purchase_Date` varchar(225) NOT NULL,
  `Supplier` varchar(225) NOT NULL,
  `Book_Title` varchar(225) NOT NULL,
  `Book_Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Total` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `po_log_tbl`
--

INSERT INTO `po_log_tbl` (`ID`, `Purchase_code`, `Purchase_Date`, `Supplier`, `Book_Title`, `Book_Author`, `Classification`, `Price`, `Quantity`, `Total`) VALUES
(28, '1PO-1', '02-27-2020', 'c & E', 'PYTHON FOR BEGINNERS', 'JIM CAVIEZEL', 'Computer Science, Information and General Works', '566', '0', '566'),
(29, '1PO-2', '02-27-2020', 'c & E', 'SYSTEMS ANALYSIS AND DESIGN', 'KENDALL WYETH', 'Computer Science, Information and General Works', '340', '100', '34000.0'),
(30, '2PO-2', '02-27-2020', 'c & E', 'SYSTEMS ANALYSIS AND DESIGN', 'KENDALL WYETH', 'Computer Science, Information and General Works', '340', '10', '3400'),
(31, '1Generate PO Number', '02-27-2020', 'c & E', 'SYSTEMS ANALYSIS AND DESIGN', 'KENDALL WYETH', 'Computer Science, Information and General Works', '340', '100', '34000'),
(32, '2Generate PO Number', '02-27-2020', 'c & E', 'PYTHON FOR BEGINNERS', 'JIM CAVIEZEL', 'Computer Science, Information and General Works', '566', '70', '39620'),
(33, '3PO-4', '02-27-2020', 'c & E', 'SYSTEMS ANALYSIS AND DESIGN', 'KENDALL WYETH', 'Computer Science, Information and General Works', '340', '100', '34000'),
(34, '4PO-4', '02-27-2020', 'c & E', 'PYTHON FOR BEGINNERS', 'JIM CAVIEZEL', 'Computer Science, Information and General Works', '566', '100', '56600'),
(35, '1PO-5', '02-28-2020', 'c & E', 'SOFTWARE ENGINEERING', 'WHITEN BENTLEY HO', 'Computer Science, Information and General Works', '500', '4', '2000'),
(36, '1PO-6', '03-01-2020', 'c & E', 'BOOK-1', 'dasda', 'Computer Science, Information and General Works', '500', '5', '2500'),
(37, '2PO-6', '03-01-2020', 'c & E', 'BOOK-2', 'dasdag', 'Religion', '222', '10', '2220'),
(38, '3PO-6', '03-01-2020', 'c & E', 'BOOK-3', 'dasda3', 'Language', '245', '5', '1225'),
(39, '4PO-6', '03-01-2020', 'c & E', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '213', '5', '1065'),
(40, '1PO-7', '03-01-2020', 'c & E', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '200', '5', '1000'),
(41, '1PO-8', '03-01-2020', 'BOOKBUYPH', 'BOOK-2', 'dasda3', 'Social Sciences', '255', '5', '1275'),
(42, '1PO-9', '03-01-2020', 'BOOKBUYPH', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '200', '5', '1000'),
(43, '1PO-10', '03-01-2020', 'BOOKBUYPH', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '200', '5', '1000'),
(44, '1PO-11', '03-02-2020', 'BOOKBUYPH', 'BOOK-1', 'dasda1', 'Computer Science, Information and General Works', '200', '5', '1000'),
(45, '1PO-12', '03-02-2020', 'BOOKBUYPH', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '213', '5', '1065'),
(46, '1PO-13', '03-04-2020', 'c & E', 'BOOK-5', 'sdad', 'Language', '500', '2', '1000'),
(47, '1PO-14', '03-05-2020', 'c & E', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '213', '1', '213'),
(48, '1PO-15', '03-05-2020', 'c & E', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '213', '1', '213'),
(49, '1PO-16', '03-31-2020', 'BOOKBUYPH', 'BOOK-5', 'sdad', 'Language', '500', '1', '500');

-- --------------------------------------------------------

--
-- Table structure for table `po_num_tbl`
--

CREATE TABLE `po_num_tbl` (
  `ID` int(11) NOT NULL,
  `Purchase_No` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `po_num_tbl`
--

INSERT INTO `po_num_tbl` (`ID`, `Purchase_No`) VALUES
(1, 'PO-1'),
(2, 'PO-2'),
(3, 'PO-3'),
(4, 'PO-4'),
(5, 'PO-5'),
(6, 'PO-6'),
(7, 'PO-7'),
(8, 'PO-8'),
(9, 'PO-9'),
(10, 'PO-10'),
(11, 'PO-11'),
(12, 'PO-12'),
(13, 'PO-13'),
(14, 'PO-14'),
(15, 'PO-15'),
(16, 'PO-16');

-- --------------------------------------------------------

--
-- Table structure for table `publisher_tbl`
--

CREATE TABLE `publisher_tbl` (
  `ID` int(11) NOT NULL,
  `Publisher_ID` varchar(225) NOT NULL,
  `Publisher` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publisher_tbl`
--

INSERT INTO `publisher_tbl` (`ID`, `Publisher_ID`, `Publisher`) VALUES
(2, '1', 'C&E'),
(3, '2', 'Thompsons'),
(4, '3', 'Rex');

-- --------------------------------------------------------

--
-- Table structure for table `purchased_tbl`
--

CREATE TABLE `purchased_tbl` (
  `ID` int(11) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `remarks_tbl`
--

CREATE TABLE `remarks_tbl` (
  `ID` int(11) NOT NULL,
  `Remarks` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `remarks_tbl`
--

INSERT INTO `remarks_tbl` (`ID`, `Remarks`) VALUES
(1, 'Tear apart'),
(3, 'wet');

-- --------------------------------------------------------

--
-- Table structure for table `requested_tbl`
--

CREATE TABLE `requested_tbl` (
  `ID` int(11) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Purchase_No` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Station` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requested_tbl`
--

INSERT INTO `requested_tbl` (`ID`, `Book_title`, `Author`, `Classification`, `Quantity`, `Price`, `Purchase_No`, `Date`, `Station`) VALUES
(40, 'BOOK-5', 'sdad', 'Language', '2', '500', 'PO-13', '3/4/2020', 'Existing'),
(41, 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '1', '213', 'PO-14', '3/5/2020', 'Existing'),
(42, 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '1', '213', 'PO-15', '3/5/2020', 'Existing'),
(43, 'BOOK-5', 'sdad', 'Language', '1', '500', 'PO-16', '3/5/2020', 'Existing');

-- --------------------------------------------------------

--
-- Table structure for table `sales_tbl`
--

CREATE TABLE `sales_tbl` (
  `ID` int(11) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Borrower` varchar(225) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stockin_tbl`
--

CREATE TABLE `stockin_tbl` (
  `Book_ID` int(11) NOT NULL,
  `ISBN_No` varchar(225) NOT NULL,
  `Book_title` varchar(225) NOT NULL,
  `Author` varchar(225) NOT NULL,
  `Quantity` varchar(225) NOT NULL,
  `Call_Number` varchar(225) NOT NULL,
  `Classification` varchar(225) NOT NULL,
  `Publisher` varchar(225) NOT NULL,
  `Edition` varchar(225) NOT NULL,
  `Copy_Right_Year` varchar(225) NOT NULL,
  `Price` varchar(225) NOT NULL,
  `Encoder` varchar(225) NOT NULL,
  `Date_Arrival` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockin_tbl`
--

INSERT INTO `stockin_tbl` (`Book_ID`, `ISBN_No`, `Book_title`, `Author`, `Quantity`, `Call_Number`, `Classification`, `Publisher`, `Edition`, `Copy_Right_Year`, `Price`, `Encoder`, `Date_Arrival`) VALUES
(11, 'ISBN-4', 'BOOK-4', 'dasda4', '6', 'Callnumber4', 'Computer Science, Information and General Works', 'C&E', '45', '2020', '213', 'Ababon, Sheenn Otaza', '03/01/2020'),
(12, 'ISBN3', 'BOOK-3', 'dasda3', '0', 'Callnumber3', 'Language', 'C&E', '45', '2020', '245', 'Ababon, Sheenn Otaza', '03/01/2020'),
(13, 'ISBN5', 'BOOK-5', 'sdad', '0', 'Callnumber5', 'Language', 'Thompsons', '45th', '2020', '500', 'Ababon, Sheenn Otaza', '03/02/2020');

-- --------------------------------------------------------

--
-- Table structure for table `supplier_tbl`
--

CREATE TABLE `supplier_tbl` (
  `Supplier_ID` int(11) NOT NULL,
  `Supplier_Name` varchar(225) NOT NULL,
  `Address` varchar(225) NOT NULL,
  `Contact` varchar(225) NOT NULL,
  `Email` varchar(225) NOT NULL,
  `Contact_Person` varchar(225) NOT NULL,
  `Date_Encoded` varchar(225) NOT NULL,
  `Encoder` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier_tbl`
--

INSERT INTO `supplier_tbl` (`Supplier_ID`, `Supplier_Name`, `Address`, `Contact`, `Email`, `Contact_Person`, `Date_Encoded`, `Encoder`) VALUES
(2, 'BOOKBUYPH', 'CEBU CITY', '02342344', 'bookbuyph@gmailcom', 'remalyn oscop', '02/27/2020', 'Ababon, Sheenn Otaza'),
(3, 'c & E', 'Manila ', '02342343', 'cepublishing@ce.com', 'sheen ababon', '02/27/2020', 'Ababon, Sheenn Otaza');

-- --------------------------------------------------------

--
-- Table structure for table `trans_code_tbl`
--

CREATE TABLE `trans_code_tbl` (
  `ID` int(11) NOT NULL,
  `Transcode` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trans_code_tbl`
--

INSERT INTO `trans_code_tbl` (`ID`, `Transcode`) VALUES
(1, 'Transaction-1'),
(2, 'Transaction-2'),
(3, 'Transaction-3'),
(4, 'Transaction-4'),
(5, 'Transaction-5'),
(6, 'Transaction-6'),
(7, 'Transaction-7'),
(8, 'Transaction-8'),
(9, 'Transaction-9'),
(10, 'Transaction-10'),
(11, 'Transaction-11'),
(12, 'Transaction-12'),
(13, 'Transaction-13'),
(14, 'Transaction-14'),
(15, 'Transaction-15'),
(16, 'Transaction-16'),
(17, 'Transaction-17'),
(18, 'Transaction-18'),
(19, 'Transaction-19'),
(20, 'Transaction-20'),
(21, 'Transaction-21'),
(22, 'Transaction-22');

-- --------------------------------------------------------

--
-- Table structure for table `useraccount_tbl`
--

CREATE TABLE `useraccount_tbl` (
  `ID` int(11) NOT NULL,
  `First_Name` varchar(225) NOT NULL,
  `Middle_Name` varchar(225) NOT NULL,
  `Surname` varchar(225) NOT NULL,
  `Username` varchar(225) NOT NULL,
  `Employee_ID` varchar(225) NOT NULL,
  `Position` varchar(225) NOT NULL,
  `Mobile_Number` varchar(225) NOT NULL,
  `Password` varchar(225) NOT NULL,
  `Level` varchar(225) NOT NULL,
  `Photo` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount_tbl`
--

INSERT INTO `useraccount_tbl` (`ID`, `First_Name`, `Middle_Name`, `Surname`, `Username`, `Employee_ID`, `Position`, `Mobile_Number`, `Password`, `Level`, `Photo`) VALUES
(7, 'Sheenn', 'Otaza', 'Ababon', 'shin', '123445', 'Admin', '09300034973', '1234', 'Admin', NULL),
(8, 'ANGELIE', 'MARIE', 'RABUSA', 'RABUSA', '', '', '', '1234', 'Student', NULL),
(9, 'Lee Ann', ' C.', 'Pacheco', 'Pacheco', '', '', '', '1234', 'Student', NULL),
(10, 'Junenor', 'Otaza', 'Ababon', 'Ababon', '', '', '', '1234', 'Student', NULL),
(11, 'Cris', 'Cri', 'Crisyyy', 'crisyy', '151112346', 'Teacher', '097643678', 'open', 'Faculty', NULL),
(12, 'Cris', 'C ', 'Crisyy', 'Crisyy', '', '', '', '1234', 'Faculty', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_log_tbl`
--

CREATE TABLE `user_log_tbl` (
  `ID` int(11) NOT NULL,
  `User_Name` varchar(225) NOT NULL,
  `Activity` varchar(255) NOT NULL,
  `Status` varchar(225) NOT NULL,
  `Book_Title` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_log_tbl`
--

INSERT INTO `user_log_tbl` (`ID`, `User_Name`, `Activity`, `Status`, `Book_Title`, `Date`, `Time`) VALUES
(1, 'Ababon, Sheenn Otaza', 'Receive Book Arrived', 'Admin', 'BOOK-4', '03/02/2020', '9:48 PM'),
(2, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'RABUSA, ANGELIE (BOOK-3)', '03/03/2020', '11:38 PM'),
(3, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'Ababon, Junenor (BOOK-5)', '03/03/2020', '11:57 PM'),
(4, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'Ababon, Junenor (BOOK-4)', '03/03/2020', '11:57 PM'),
(5, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'RABUSA, ANGELIE (BOOK-3)', '04/03/2020', '12:34 AM'),
(6, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'Crisyy, Cris (BOOK-4)', '4-Mar-2020', '12:49 AM'),
(7, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'Pacheco, Lee Ann (BOOK-5)', '4-Mar-2020', '12:50 AM'),
(8, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'Crisyy, Cris (BOOK-3)', '3/10/2020', '1:01 AM'),
(9, 'Ababon, Sheenn Otaza', 'Process Book Loan', 'Admin', 'RABUSA, ANGELIE (BOOK-3)', '3/10/2020', '1:06 AM'),
(10, 'Name', 'Save Author', 'Admin', 'Cris desu', '3/4/2020', '6:06 PM'),
(11, 'Name', 'Save Publisher', 'Admin', '', '3/4/2020', '6:06 PM'),
(12, 'Name', 'Update Author', 'Admin', 'Cris desu test', '3/4/2020', '6:12 PM'),
(13, 'Name', 'Delete Publisher', 'Admin', '', '3/4/2020', '6:13 PM'),
(14, 'Name', 'Process Book Loan', 'Admin', 'RABUSA, ANGELIE (BOOK-4)', '3/4/2020', '8:15 PM'),
(15, 'Name', 'Add Purchase Request', 'Admin', 'BOOK-5 (PO-13)', '3/4/2020', '8:55 PM'),
(16, 'Name', 'Stock Out', 'Admin', 'BOOK-3 (0)', '3/4/2020', '10:11 PM'),
(17, 'Name', 'Stock Out', 'Admin', 'BOOK-3 (0)', '3/4/2020', '10:12 PM'),
(18, 'Name', 'Stock Out', 'Admin', 'BOOK-5 (0)', '3/4/2020', '10:13 PM'),
(19, 'Name', 'Process Book Loan', 'Admin', 'RABUSA, ANGELIE (BOOK-5)', '3/4/2020', '10:52 PM'),
(20, 'Name', 'Add Purchase Request', 'Admin', 'BOOK-4 (PO-14)', '3/5/2020', '1:17 AM'),
(21, 'Name', 'Add Purchase Request', 'Admin', 'BOOK-4 (PO-15)', '3/5/2020', '1:38 AM'),
(22, 'Name', 'Add Purchase Request', 'Admin', 'BOOK-5 (PO-16)', '3/5/2020', '2:29 AM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_log_tbl`
--
ALTER TABLE `account_log_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `author_tbl`
--
ALTER TABLE `author_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `borrower_tbl`
--
ALTER TABLE `borrower_tbl`
  ADD UNIQUE KEY `Full_Name` (`First_Name`),
  ADD UNIQUE KEY `ID_No` (`ID_No`);

--
-- Indexes for table `brrdlog_tbl`
--
ALTER TABLE `brrdlog_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `classification_tbl`
--
ALTER TABLE `classification_tbl`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Classname` (`Classname`);

--
-- Indexes for table `course_tbl`
--
ALTER TABLE `course_tbl`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Course` (`Course`);

--
-- Indexes for table `damage_tbl`
--
ALTER TABLE `damage_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `delivery_tbl`
--
ALTER TABLE `delivery_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `holding_tbl`
--
ALTER TABLE `holding_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `order_tbl`
--
ALTER TABLE `order_tbl`
  ADD PRIMARY KEY (`No`);

--
-- Indexes for table `po_log_tbl`
--
ALTER TABLE `po_log_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `po_num_tbl`
--
ALTER TABLE `po_num_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `publisher_tbl`
--
ALTER TABLE `publisher_tbl`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Publisher` (`Publisher`);

--
-- Indexes for table `purchased_tbl`
--
ALTER TABLE `purchased_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `remarks_tbl`
--
ALTER TABLE `remarks_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `requested_tbl`
--
ALTER TABLE `requested_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sales_tbl`
--
ALTER TABLE `sales_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `stockin_tbl`
--
ALTER TABLE `stockin_tbl`
  ADD PRIMARY KEY (`Book_ID`),
  ADD UNIQUE KEY `ISBN_No` (`ISBN_No`),
  ADD UNIQUE KEY `Call_Number` (`Call_Number`);

--
-- Indexes for table `supplier_tbl`
--
ALTER TABLE `supplier_tbl`
  ADD PRIMARY KEY (`Supplier_ID`),
  ADD UNIQUE KEY `Supplier_Name` (`Supplier_Name`);

--
-- Indexes for table `trans_code_tbl`
--
ALTER TABLE `trans_code_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `useraccount_tbl`
--
ALTER TABLE `useraccount_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user_log_tbl`
--
ALTER TABLE `user_log_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account_log_tbl`
--
ALTER TABLE `account_log_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT for table `author_tbl`
--
ALTER TABLE `author_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `brrdlog_tbl`
--
ALTER TABLE `brrdlog_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `classification_tbl`
--
ALTER TABLE `classification_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `course_tbl`
--
ALTER TABLE `course_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `damage_tbl`
--
ALTER TABLE `damage_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `delivery_tbl`
--
ALTER TABLE `delivery_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `holding_tbl`
--
ALTER TABLE `holding_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `order_tbl`
--
ALTER TABLE `order_tbl`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `po_log_tbl`
--
ALTER TABLE `po_log_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `po_num_tbl`
--
ALTER TABLE `po_num_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `publisher_tbl`
--
ALTER TABLE `publisher_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `purchased_tbl`
--
ALTER TABLE `purchased_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `remarks_tbl`
--
ALTER TABLE `remarks_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `requested_tbl`
--
ALTER TABLE `requested_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `sales_tbl`
--
ALTER TABLE `sales_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `stockin_tbl`
--
ALTER TABLE `stockin_tbl`
  MODIFY `Book_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `supplier_tbl`
--
ALTER TABLE `supplier_tbl`
  MODIFY `Supplier_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `trans_code_tbl`
--
ALTER TABLE `trans_code_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `useraccount_tbl`
--
ALTER TABLE `useraccount_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user_log_tbl`
--
ALTER TABLE `user_log_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
