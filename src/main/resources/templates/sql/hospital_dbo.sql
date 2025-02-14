-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 14, 2025 at 03:07 PM
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
-- Database: `hospital_dbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient_enctr`
--

CREATE TABLE `patient_enctr` (
  `id` int(11) NOT NULL,
  `brought_by` varchar(255) DEFAULT NULL,
  `chief_complaint` varchar(255) DEFAULT NULL,
  `consultation_type` varchar(255) DEFAULT NULL,
  `consulting_doctor` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `hospital_record_no` varchar(255) DEFAULT NULL,
  `logged_at` datetime(6) DEFAULT NULL,
  `type_of_service` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `patient_enctr`
--

INSERT INTO `patient_enctr` (`id`, `brought_by`, `chief_complaint`, `consultation_type`, `consulting_doctor`, `created_at`, `deleted_at`, `hospital_record_no`, `logged_at`, `type_of_service`, `updated_at`) VALUES
(1, 'Walk-in', 'sample complaint', 'OPD', 'dr. sample er', '2025-02-14 13:48:34.000000', NULL, '0000000001', '2025-02-14 21:48:00.000000', 'MED', NULL),
(2, 'FamMem', 'sample adm complaint', 'ADM', 'dr. sample adm', '2025-02-14 14:07:13.000000', NULL, '0000000001', '2025-02-14 22:06:00.000000', 'MED', NULL);

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
(1, '1999-07-08', '09108410072', '2025-02-14 21:10:45.000000', NULL, 'Kenneth', '0000000001', 'Casipong', 'Pocong', 'purok 08 Magallanes ADN', '8604', 'cebu city', 'Purok 08 Magallanes ADN', '8604', 'Iglesia Ni Cristo', 'Male', 'Single', '2025-02-14 21:47:44.000000'),
(2, '1997-10-10', '091548762', '2025-02-14 21:13:28.000000', NULL, 'Ken Charles', '0000000002', 'Palma', 'Madlos', 'PUROK 08 Ampayon BXU', '8600', 'Ampayon Butuan City', 'PUROK 08 Ampayon BXU', '8600', 'Born Again', 'Male', 'Single', NULL),
(3, '1999-09-08', '0945825244', '2025-02-14 21:15:10.000000', NULL, 'Jamaika Nikki', '0000000003', 'Villamor', 'Sandro', 'Barangay 1 Butuan City', '8600', 'Cagayan City', 'Barangay 1 Butuan City', '8600', 'Catholic', 'Male', 'Single', NULL),
(4, '1999-06-05', '091084100724', '2025-02-14 22:04:20.000000', '2025-02-14 22:06:23.000000', 'Rex', '0000000004', 'Cabutaje', 'Saligue', 'PUROK 08 Poblacion Magallanes ADN', '8604', 'Magallanes', 'PUROK 08 Poblacion Magallanes ADN', '8604', 'Catholic', 'Male', 'Single', '2025-02-14 22:06:14.000000'),
(5, '1997-02-25', '0954448767', '2025-02-14 22:05:48.000000', NULL, 'Kevin Cliff', '0000000005', 'Pomar', 'Manfa', 'Saband Trento ADS', '8603', 'Trento ADS', 'Saband Trento ADS', '8603', 'Catholic', 'Male', 'Single', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient_enctr`
--
ALTER TABLE `patient_enctr`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register_pat`
--
ALTER TABLE `register_pat`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patient_enctr`
--
ALTER TABLE `patient_enctr`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `register_pat`
--
ALTER TABLE `register_pat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
