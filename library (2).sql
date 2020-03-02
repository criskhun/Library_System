-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2020 at 04:40 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

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
(12, 'Ababon, Sheenn Otaza', 'Log-out', 'Admin', '03/02/2020', '11:31 PM');

-- --------------------------------------------------------

--
-- Table structure for table `borrowed_tbl`
--

CREATE TABLE `borrowed_tbl` (
  `ID` int(11) NOT NULL,
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

INSERT INTO `borrowed_tbl` (`ID`, `Library_ID`, `Full_Name`, `Status`, `Book_title`, `Book_Price`, `Classification`, `Fines`, `Quantity`, `Borrowed_Date`, `Return_Date`, `Remarks`, `Payable`) VALUES
(12, 'LIB-ID-4', 'Crisyy, Cris', 'Faculty', 'BOOK-4', '213', 'Computer Science, Information and General Works', '5', '2', '3/2/2020', '3/9/2020', 'Damage', '426'),
(13, 'LIB-ID-4', 'Crisyy, Cris', 'Faculty', 'BOOK-3', '245', 'Language', '5', '1', '3/2/2020', '3/9/2020', 'Loss', '245.0');

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
(13, 'Crisyy, Cris', 'Faculty', 'BOOK-3', 'Language', '3/2/2020', 'Ababon, Sheenn Otaza', '03/02/2020');

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
  `Encoder` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `damage_tbl`
--

INSERT INTO `damage_tbl` (`ID`, `ISBN_No`, `Book_title`, `Classification`, `Status`, `Quantity`, `Encoder`, `Date`, `Time`) VALUES
(13, 'ISBN3', 'BOOK-3', 'Language', '--Select Status--', '-1', '', '', ''),
(14, 'ISBN5', 'BOOK-5', 'Language', '--Select Status--', '0', '', '', '');

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
(12, 'ISBN-4', 'BOOK-4', 'Computer Science, Information and General Works', '8', '-2', '4', '6', '', '', ''),
(13, 'ISBN3', 'BOOK-3', 'Language', '4', '1', '0', '5', '', '', ''),
(14, 'ISBN5', 'BOOK-5', 'Language', '10', '0', '0', '10', '', '', '');

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
('', '', '', 'Ababon, Sheenn Otaza', '03/02/2020', '9:48 PM', 'Stock-in');

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
(45, '1PO-12', '03-02-2020', 'BOOKBUYPH', 'BOOK-4', 'dasda4', 'Computer Science, Information and General Works', '213', '5', '1065');

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
(12, 'PO-12');

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
(11, 'ISBN-4', 'BOOK-4', 'dasda4', '8', 'Callnumber4', 'Computer Science, Information and General Works', 'C&E', '45', '2020', '213', 'Ababon, Sheenn Otaza', '03/01/2020'),
(12, 'ISBN3', 'BOOK-3', 'dasda3', '4', 'Callnumber3', 'Language', 'C&E', '45', '2020', '245', 'Ababon, Sheenn Otaza', '03/01/2020'),
(13, 'ISBN5', 'BOOK-5', 'sdad', '10', 'Callnumber5', 'Language', 'Thompsons', '45th', '2020', '500', 'Ababon, Sheenn Otaza', '03/02/2020');

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
(1, 'Ababon, Sheenn Otaza', 'Receive Book Arrived', 'Admin', 'BOOK-4', '03/02/2020', '9:48 PM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_log_tbl`
--
ALTER TABLE `account_log_tbl`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `borrowed_tbl`
--
ALTER TABLE `borrowed_tbl`
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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `borrowed_tbl`
--
ALTER TABLE `borrowed_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `brrdlog_tbl`
--
ALTER TABLE `brrdlog_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `po_log_tbl`
--
ALTER TABLE `po_log_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `po_num_tbl`
--
ALTER TABLE `po_num_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
-- AUTO_INCREMENT for table `requested_tbl`
--
ALTER TABLE `requested_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

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
-- AUTO_INCREMENT for table `useraccount_tbl`
--
ALTER TABLE `useraccount_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user_log_tbl`
--
ALTER TABLE `user_log_tbl`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
