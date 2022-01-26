-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2020 at 04:56 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga_pokok` double(10,2) NOT NULL,
  `harga_jual` double(10,2) NOT NULL,
  `ppn` int(11) NOT NULL,
  `diskon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `stok`, `harga_pokok`, `harga_jual`, `ppn`, `diskon`) VALUES
('000000001', 'Gas Elpiji 3kg', 20, 500000.00, 550000.00, 10, 0),
('000000002', 'Gas Elpiji 12kg', 20, 150000.00, 165000.00, 10, 0),
('000000003', 'Indomie Goreng', 100, 3000.00, 3300.00, 10, 0),
('000000004', 'Sarimi', 100, 2000.00, 2200.00, 10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE `kasir` (
  `id_kasir` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(150) NOT NULL,
  `telepon` varchar(14) NOT NULL,
  `status` varchar(12) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `akses` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nota_konsumen`
--

CREATE TABLE `nota_konsumen` (
  `id` int(11) NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga_barang` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nota_konsumen`
--

INSERT INTO `nota_konsumen` (`id`, `kode_barang`, `nama_barang`, `harga_barang`) VALUES
(1, '000000002', 'Gas Elpiji 12kg', 165000.00),
(2, '000000003', 'Indomie Goreng', 2200.00),
(3, '000000001', 'Gas Elpiji 3kg', 55000.00),
(4, '000000002', 'Gas Elpiji 12kg', 165000.00),
(5, '000000002', 'Gas Elpiji 12kg', 165000.00),
(6, '000000001', 'Gas Elpiji 3kg', 55000.00),
(7, '000000003', 'Indomie Goreng', 2200.00),
(8, '000000003', 'Indomie Goreng', 2200.00),
(9, '000000001', 'Gas Elpiji 3kg', 55000.00),
(10, '000000002', 'Gas Elpiji 12kg', 165000.00),
(11, '000000002', 'Gas Elpiji 12kg', 165000.00),
(12, '000000003', 'Indomie Goreng', 2200.00),
(13, '000000002', 'Gas Elpiji 12kg', 165000.00),
(14, '000000001', 'Gas Elpiji 3kg', 55000.00),
(15, '000000002', 'Gas Elpiji 12kg', 165000.00),
(16, '000000003', 'Indomie Goreng', 2200.00),
(17, '000000002', 'Gas Elpiji 12kg', 165000.00),
(18, '000000002', 'Gas Elpiji 12kg', 165000.00),
(19, '000000003', 'Indomie Goreng', 2200.00),
(20, '000000001', 'Gas Elpiji 3kg', 55000.00),
(21, '000000003', 'Indomie Goreng', 2200.00),
(22, '000000002', 'Gas Elpiji 12kg', 165000.00),
(23, '000000002', 'Gas Elpiji 12kg', 165000.00),
(24, '000000002', 'Gas Elpiji 12kg', 165000.00);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` int(11) NOT NULL,
  `id_barang` varchar(20) NOT NULL,
  `id_kasir` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` double(10,2) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `stok`
--

CREATE TABLE `stok` (
  `id_stok` int(11) NOT NULL,
  `id_barang` varchar(20) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indexes for table `nota_konsumen`
--
ALTER TABLE `nota_konsumen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- Indexes for table `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`id_stok`),
  ADD KEY `id_barang` (`id_barang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kasir`
--
ALTER TABLE `kasir`
  MODIFY `id_kasir` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT for table `nota_konsumen`
--
ALTER TABLE `nota_konsumen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `stok`
--
ALTER TABLE `stok`
  MODIFY `id_stok` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`),
  ADD CONSTRAINT `penjualan_ibfk_3` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `stok`
--
ALTER TABLE `stok`
  ADD CONSTRAINT `stok_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
