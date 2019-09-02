-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 02, 2019 at 10:49 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `password` mediumtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `username`, `password`) VALUES
(1, 'admin', '1212');

-- --------------------------------------------------------

--
-- Table structure for table `arif`
--

CREATE TABLE `arif` (
  `name` varchar(80) NOT NULL,
  `roll` int(10) NOT NULL,
  `bangla` int(10) NOT NULL,
  `english` int(10) NOT NULL,
  `math` int(10) NOT NULL,
  `gk` int(10) NOT NULL,
  `religion` int(10) NOT NULL,
  `science` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arif`
--

INSERT INTO `arif` (`name`, `roll`, `bangla`, `english`, `math`, `gk`, `religion`, `science`) VALUES
('Shurid Hossain', 2, 78, 98, 75, 89, 87, 87);

-- --------------------------------------------------------

--
-- Table structure for table `reema`
--

CREATE TABLE `reema` (
  `name` varchar(80) NOT NULL,
  `roll` int(10) NOT NULL,
  `bangla` int(10) NOT NULL,
  `english` int(10) NOT NULL,
  `math` int(10) NOT NULL,
  `gk` int(10) NOT NULL,
  `religion` int(10) NOT NULL,
  `science` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reema`
--

INSERT INTO `reema` (`name`, `roll`, `bangla`, `english`, `math`, `gk`, `religion`, `science`) VALUES
('Rifat Hossain', 2, 78, 98, 75, 89, 87, 87);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `name` varchar(80) NOT NULL,
  `password` mediumtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `name`, `password`) VALUES
(1, 'arif', 'Arif Istiaq', '1'),
(2, 'reema', 'Reema Akhtar', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `arif`
--
ALTER TABLE `arif`
  ADD PRIMARY KEY (`roll`);

--
-- Indexes for table `reema`
--
ALTER TABLE `reema`
  ADD PRIMARY KEY (`roll`),
  ADD UNIQUE KEY `roll` (`roll`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
