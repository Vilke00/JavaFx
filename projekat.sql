-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2020 at 11:47 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('Nemanja', 'Adminp@ssw0rd');

-- --------------------------------------------------------

--
-- Table structure for table `filmovi`
--

CREATE TABLE `filmovi` (
  `id_filma` int(11) NOT NULL,
  `ime` varchar(25) NOT NULL,
  `zanr` varchar(40) NOT NULL,
  `tehnologija` varchar(30) NOT NULL,
  `trajanje` int(3) NOT NULL,
  `ocena` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `filmovi`
--

INSERT INTO `filmovi` (`id_filma`, `ime`, `zanr`, `tehnologija`, `trajanje`, `ocena`) VALUES
(1, 'Poslednji', 'Triler', 'Digital 3D', 110, 6),
(2, 'Joker', 'Horor', 'MX4D 2D', 139, 9),
(3, 'Marinko', 'Porodicni', 'MX4D 2D', 105, 12),
(4, 'Vatra', 'Ratni', 'MX4D 2D', 120, 7),
(5, 'Poslednji dan', 'Akcija, Krimi, Triler', 'Digital 2D', 120, 9),
(6, 'Artemis ptica', 'Avantura', 'Digital 2D', 132, 9),
(7, 'Izgubljen metak', 'Akcija, Krimi, Triler', 'Digital 2D', 112, 8.5),
(8, 'Oseti ritam', 'Komedija, Drama', 'MX4D 2D', 143, 7),
(9, 'Sonik', 'Akcija, Komedija', 'Digital 3D', 124, 8.7),
(10, 'Trebao si da odes', 'Horor', 'MX4D 3D', 128, 9),
(13, 'Viking Vik', 'Animirani, AKCIJA', 'Digital 2D', 110, 6),
(14, 'Probudjen', 'Horor', 'Digital 3D', 148, 7.8),
(15, 'Gospodin Zoo:Nestali VIP', 'Komedija', 'Digital 2D', 121, 7),
(16, 'Volim te imbecilu', 'Komedija', 'Digital 2D', 120, 8),
(17, 'Ciganka', 'Avantura, Drama', 'Digital 2D', 131, 10),
(18, 'Yao', 'Ratni', 'Digital 2D', 118, 6);

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL,
  `ime` varchar(25) NOT NULL,
  `prezime` varchar(25) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id`, `ime`, `prezime`, `username`, `password`) VALUES
(1, 'Nemanja', 'Vilic', 'vilke', '1234'),
(2, 'Marko', 'Peti', 'maki', '123');

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE `mesto` (
  `id_mesta` int(11) NOT NULL,
  `broj_sale` int(11) NOT NULL,
  `red` char(1) NOT NULL,
  `sediste` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`id_mesta`, `broj_sale`, `red`, `sediste`) VALUES
(1, 1, 'A', 1),
(2, 1, 'B', 3),
(3, 3, 'A', 5),
(4, 2, 'A', 3),
(5, 2, 'A', 4),
(6, 2, 'C', 3),
(7, 2, 'D', 4),
(8, 2, 'C', 3);

-- --------------------------------------------------------

--
-- Table structure for table `projekcije`
--

CREATE TABLE `projekcije` (
  `id_projekcije` int(11) NOT NULL,
  `id_filma` int(11) NOT NULL,
  `lokacija` varchar(25) NOT NULL,
  `datum` date NOT NULL,
  `vreme` time NOT NULL,
  `broj_sale` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projekcije`
--

INSERT INTO `projekcije` (`id_projekcije`, `id_filma`, `lokacija`, `datum`, `vreme`, `broj_sale`) VALUES
(1, 4, 'Beo', '2020-06-25', '16:00:00', 3),
(2, 2, 'Beo', '2020-06-22', '16:00:00', 1),
(3, 5, 'Big', '2020-06-22', '16:00:00', 2),
(4, 4, 'Big', '2020-07-01', '15:30:00', 1),
(5, 5, 'Delta', '2020-06-09', '00:00:16', 1),
(7, 1, 'Usce', '2020-06-27', '19:50:00', 1),
(8, 1, 'Big', '2020-06-30', '19:50:00', 3),
(9, 3, 'Beo', '2020-06-27', '19:42:00', 4),
(10, 6, 'Beo', '2020-06-29', '14:00:00', 3),
(11, 7, 'Delta', '2020-06-25', '13:00:00', 3),
(12, 8, 'Delta', '2020-06-26', '19:50:00', 1),
(15, 9, 'Delta', '2020-06-22', '16:00:00', 3),
(16, 10, 'Big', '2020-06-26', '15:00:00', 1),
(17, 13, 'Beo', '2020-06-29', '23:00:00', 2),
(18, 14, 'Usce', '2020-07-01', '21:00:00', 2),
(19, 15, 'Usce', '2020-07-01', '19:57:00', 3),
(20, 16, 'Big', '2020-06-29', '12:58:00', 4),
(21, 17, 'Delta', '2020-06-24', '18:00:00', 4);

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE `rezervacija` (
  `id_rezervacije` int(11) NOT NULL,
  `id_filma` int(11) NOT NULL,
  `id_projekcije` int(11) NOT NULL,
  `id_mesta` int(11) NOT NULL,
  `id_korisnika` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `rezervacija` (`id_rezervacije`, `id_filma`, `id_projekcije`, `id_mesta`, `id_korisnika`) VALUES
(2, 5, 3, 4, 1),
(3, 5, 3, 5, 1),
(4, 5, 3, 6, 1),
(5, 5, 3, 7, 1),
(6, 5, 3, 6, 1),
(7, 5, 3, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `broj_sale` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sala`
--

INSERT INTO `sala` (`broj_sale`) VALUES
(1),
(2),
(3),
(4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `filmovi`
--
ALTER TABLE `filmovi`
  ADD PRIMARY KEY (`id_filma`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mesto`
--
ALTER TABLE `mesto`
  ADD PRIMARY KEY (`id_mesta`),
  ADD KEY `broj_sale` (`broj_sale`);

--
-- Indexes for table `projekcije`
--
ALTER TABLE `projekcije`
  ADD PRIMARY KEY (`id_projekcije`),
  ADD KEY `id_filma` (`id_filma`),
  ADD KEY `broj_sale` (`broj_sale`);

--
-- Indexes for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD PRIMARY KEY (`id_rezervacije`),
  ADD KEY `id_filma` (`id_filma`),
  ADD KEY `id_projekcije` (`id_projekcije`),
  ADD KEY `id_mesta` (`id_mesta`),
  ADD KEY `id_korisnika` (`id_korisnika`);

--
-- Indexes for table `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`broj_sale`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `filmovi`
--
ALTER TABLE `filmovi`
  MODIFY `id_filma` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `mesto`
--
ALTER TABLE `mesto`
  MODIFY `id_mesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `projekcije`
--
ALTER TABLE `projekcije`
  MODIFY `id_projekcije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `rezervacija`
--
ALTER TABLE `rezervacija`
  MODIFY `id_rezervacije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `sala`
--
ALTER TABLE `sala`
  MODIFY `broj_sale` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mesto`
--
ALTER TABLE `mesto`
  ADD CONSTRAINT `mesto_ibfk_1` FOREIGN KEY (`broj_sale`) REFERENCES `sala` (`broj_sale`);

--
-- Constraints for table `projekcije`
--
ALTER TABLE `projekcije`
  ADD CONSTRAINT `projekcije_ibfk_1` FOREIGN KEY (`id_filma`) REFERENCES `filmovi` (`id_filma`),
  ADD CONSTRAINT `projekcije_ibfk_2` FOREIGN KEY (`broj_sale`) REFERENCES `sala` (`broj_sale`);

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`id_filma`) REFERENCES `filmovi` (`id_filma`),
  ADD CONSTRAINT `rezervacija_ibfk_2` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`),
  ADD CONSTRAINT `rezervacija_ibfk_3` FOREIGN KEY (`id_mesta`) REFERENCES `mesto` (`id_mesta`),
  ADD CONSTRAINT `rezervacija_ibfk_4` FOREIGN KEY (`id_projekcije`) REFERENCES `projekcije` (`id_projekcije`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
