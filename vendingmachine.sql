-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2020 at 10:09 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vendingmachine`
--
CREATE DATABASE IF NOT EXISTS `vendingmachine` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci;
USE `vendingmachine`;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'makanan'),
(2, 'minuman');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) COLLATE utf8_czech_ci NOT NULL,
  `harga` double NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `foto` varchar(100) COLLATE utf8_czech_ci NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `nama`, `harga`, `quantity`, `foto`, `category_id`) VALUES
(1, 'CocaCola', 8000, 8, 'src/com/tubes/img/cocacola.png', 2),
(2, 'Sprite', 8000, 10, 'src/com/tubes/img/sprite.png', 2),
(3, 'Fanta', 8000, 10, 'src/com/tubes/img/fantaorange.png', 2),
(4, 'Pepsi', 10000, 10, 'src/com/tubes/img/pepsi.png', 2),
(5, 'Minute Pulpy Orange', 12000, 10, 'src/com/tubes/img/minutemaidpulpy.png', 2),
(6, 'Minute Pulpy Grape', 12000, 10, 'src/com/tubes/img/minutemaidpulpygrape.png', 2),
(7, 'Minute Pulpy Apple', 12000, 10, 'src/com/tubes/img/minutemaidpulpyapple.png', 2),
(8, 'Ades', 3000, 10, 'src/com/tubes/img/ades.png', 2),
(9, 'Lays Honey BBQ', 10000, 10, 'src/com/tubes/img/layshoney.png', 1),
(10, 'Lays Salad', 10000, 10, 'src/com/tubes/img/layssalad.png', 1),
(11, 'Lays Cheddar Sour Cream', 10000, 10, 'src/com/tubes/img/lays.png', 1),
(12, 'Lays Salt Vinegar', 10000, 10, 'src/com/tubes/img/layssalt.png', 1),
(13, 'SilverQueen Almond', 15000, 9, 'src/com/tubes/img/silverqueen2.png', 1),
(14, 'SilverQueen Fruit Nut', 15000, 10, 'src/com/tubes/img/silverqueen.png', 1),
(15, 'Beng-Beng', 4000, 10, 'src/com/tubes/img/bengbeng.png', 1),
(16, 'Oreo', 7000, 10, 'src/com/tubes/img/oreo.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `log_item`
--

DROP TABLE IF EXISTS `log_item`;
CREATE TABLE `log_item` (
  `idlog` int(11) NOT NULL,
  `tgl_Masuk` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `item_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `item_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `tanggal`, `item_id`) VALUES
(1, '2020-01-08 09:08:04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role` varchar(45) COLLATE utf8_czech_ci DEFAULT NULL,
  `username` varchar(100) COLLATE utf8_czech_ci DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_czech_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role`, `username`, `password`) VALUES
(1, 'admin', '1772003', '1772003'),
(2, 'admin', '1772009', '1772009');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_item_category_idx` (`category_id`);

--
-- Indexes for table `log_item`
--
ALTER TABLE `log_item`
  ADD PRIMARY KEY (`idlog`),
  ADD KEY `fk_log_item_item1_idx` (`item_id`),
  ADD KEY `fk_log_item_user1_idx` (`user_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_transaction_item1_idx` (`item_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `log_item`
--
ALTER TABLE `log_item`
  MODIFY `idlog` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_item_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `log_item`
--
ALTER TABLE `log_item`
  ADD CONSTRAINT `fk_log_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_log_item_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_transaction_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
