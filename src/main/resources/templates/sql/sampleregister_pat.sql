-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 09, 2025 at 11:26 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `patientreg_dbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `register_pat`
--

CREATE TABLE `register_pat` (
  `id` int(11) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `hospital_record_no` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `permanent_address` varchar(255) DEFAULT NULL,
  `permanent_zip_code` varchar(255) DEFAULT NULL,
  `place_of_birth` varchar(255) DEFAULT NULL,
  `present_address` varchar(255) DEFAULT NULL,
  `present_zip_code` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `register_pat`
--

INSERT INTO `register_pat` (`id`, `birthdate`, `contact_info`, `created_at`, `deleted_at`, `first_name`, `hospital_record_no`, `last_name`, `middle_name`, `permanent_address`, `permanent_zip_code`, `place_of_birth`, `present_address`, `present_zip_code`, `religion`, `sex`, `status`, `updated_at`) VALUES
(1, '1990-01-01', '1234567890', '2025-02-09 13:11:50.000000', '2025-02-09 14:33:27.000000', 'patient1', '0000000001', 'patient1', 'patient1', 'mags', '12345', 'cebu', 'mags', '12345', 'inc', 'Male', 'Single', '2025-02-09 13:55:56.000000'),
(2, '2000-01-01', '09108410072', '2025-02-09 13:27:12.000000', '2025-02-09 16:38:10.000000', 'patient2', '0000000002', 'patient2', 'patient2', 'PUROK 08', '8604', 'cebu', 'PUROK 08', '8604', 'inc', 'Male', 'Single', NULL),
(3, '2000-01-01', '1234567890', '2025-02-09 13:56:58.000000', '2025-02-09 16:47:31.000000', 'sample1', '0000000003', 'sample1', 'sample1', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '8604', 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '8604', 'Catholic', 'Male', 'Single', '2025-02-09 15:12:22.000000'),
(4, '2002-02-02', '1234567890', '2025-02-09 13:57:14.000000', '2025-02-09 16:47:51.000000', 'sample2', '0000000004', 'sample2', 'sample2', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '', 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '', 'Catholic', 'Male', 'Single', '2025-02-09 15:12:47.000000'),
(5, '2003-03-03', '1234567890', '2025-02-09 13:57:25.000000', '2025-02-09 16:47:02.000000', 'sample3', '0000000005', 'sample3', 'sample3', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '123456', 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', '123456', 'Catholic', 'Male', 'Single', '2025-02-09 16:46:50.000000'),
(6, NULL, '1234567890', '2025-02-09 13:57:46.000000', '2025-02-09 16:50:15.000000', 'sample5', '0000000006', 'sample5', 'sample5', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(7, NULL, '1234567890', '2025-02-09 13:58:18.000000', '2025-02-09 16:50:46.000000', 'sample6', '0000000007', 'sample6', 'sample6', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(8, NULL, '1234567890', '2025-02-09 13:58:32.000000', '2025-02-09 16:50:53.000000', 'sample7', '0000000008', 'sample7', 'sample7', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(9, NULL, '1234567890', '2025-02-09 13:58:43.000000', '2025-02-09 16:50:59.000000', 'sample8', '0000000009', 'sample8', 'sample8', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(10, NULL, '1234567890', '2025-02-09 13:58:53.000000', '2025-02-09 16:51:05.000000', 'sample9', '0000000010', 'sample9', 'sample9', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(11, NULL, '1234567890', '2025-02-09 14:04:34.000000', '2025-02-09 16:51:11.000000', 'sample10', '0000000011', 'sample10', 'sample10', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(12, NULL, '1234567890', '2025-02-09 14:04:44.000000', '2025-02-09 16:51:18.000000', 'sample11', '0000000012', 'sample11', 'sample11', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Cebu', 'Purok-1 Brgy. Sto. Rosario, Magallanes, ADN', NULL, 'Catholic', 'Male', 'Single', NULL),
(13, '1999-07-08', '09108410072', '2025-02-09 18:22:19.000000', NULL, 'Kenneth', '0000000013', 'casipong', 'pocong', 'PUROK 08', '8604', 'cebu city', 'PUROK 08', '8604', 'Iglesia ni Cristo', 'Male', 'Single', NULL),
(14, '1999-01-01', '09108410072', '2025-02-09 18:23:00.000000', NULL, 'KenCharles', '0000000014', 'Palma', 'sample', 'PUROK 08', '12345', 'butuan', 'PUROK 08', '12345', 'sample', 'Male', 'Single', '2025-02-09 18:24:45.000000'),
(15, '2000-01-01', '1234567890', '2025-02-09 18:23:49.000000', NULL, 'JamaicaNikki', '0000000015', 'Villamor', 'sample', 'sample', '1452', 'butuan', 'sample', '1452', 'sample', 'Female', 'Single', '2025-02-09 18:24:59.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `register_pat`
--
ALTER TABLE `register_pat`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `register_pat`
--
ALTER TABLE `register_pat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
