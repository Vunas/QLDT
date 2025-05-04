-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2025 at 04:57 PM
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
-- Database: `qldt`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `machitiethoadon` int(11) NOT NULL,
  `mahoadon` int(11) NOT NULL,
  `mabaohanh` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` int(11) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`machitiethoadon`, `mahoadon`, `mabaohanh`, `masanpham`, `soluong`, `dongia`, `trangthai`) VALUES
(8, 8, 0, 1, 5, 28000000, 1),
(9, 9, 0, 3, 4, 20000000, 1),
(10, 10, 0, 6, 2, 14000000, 1),
(11, 11, 0, 2, 6, 26000000, 1),
(12, 12, 0, 8, 3, 22000000, 1),
(13, 13, 0, 8, 1, 22000000, 1),
(14, 13, 0, 9, 1, 25000000, 1),
(15, 14, 0, 10, 1, 10000000, 1),
(16, 15, 0, 10, 1, 9800000, 1),
(17, 16, 0, 10, 1, 10000000, 0),
(18, 17, 0, 10, 1, 10000000, 0),
(19, 18, 0, 10, 1, 55860000, 1),
(20, 18, 0, 9, 1, 55860000, 1),
(21, 18, 0, 8, 1, 55860000, 1),
(22, 19, 0, 9, 1, 24500000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieubaohanh`
--

CREATE TABLE `chitietphieubaohanh` (
  `MaChiTiet` int(11) NOT NULL,
  `MaPhieuBH` int(11) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL,
  `maIMEI` varchar(20) DEFAULT NULL,
  `NgayBatDauBH` date DEFAULT NULL,
  `NgayKetThucBH` date DEFAULT NULL,
  `TrangThai` tinyint(4) DEFAULT NULL,
  `GhiChu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieubaohanh`
--

INSERT INTO `chitietphieubaohanh` (`MaChiTiet`, `MaPhieuBH`, `MaSanPham`, `maIMEI`, `NgayBatDauBH`, `NgayKetThucBH`, `TrangThai`, `GhiChu`) VALUES
(1, 1, 6, '123123123123100', '2025-04-27', '2026-04-27', 1, ''),
(2, 2, 2, '999111222333101', '2025-04-27', '2026-04-27', 0, ''),
(3, 3, 9, '111222333444502', '2025-04-27', '2027-04-27', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maCTPhieuNhap` int(11) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `donGia` int(11) NOT NULL,
  `maPhieuNhap` int(11) NOT NULL,
  `maSanPham` int(11) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maCTPhieuNhap`, `soLuong`, `donGia`, `maPhieuNhap`, `maSanPham`, `trangthai`) VALUES
(1, 23, 22000000, 1, 9, 1),
(2, 2, 8000000, 1, 10, 1),
(3, 12, 23000000, 2, 2, 1),
(4, 6, 12000000, 2, 6, 1),
(5, 22, 19000000, 3, 8, 1),
(6, 50, 25000000, 4, 1, 0),
(7, 60, 19000000, 5, 8, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ctsanpham`
--

CREATE TABLE `ctsanpham` (
  `maimei` varchar(20) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `maphieunhap` int(11) NOT NULL,
  `mahoadon` int(11) DEFAULT NULL,
  `tinhtrang` int(11) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctsanpham`
--

INSERT INTO `ctsanpham` (`maimei`, `masanpham`, `maphieunhap`, `mahoadon`, `tinhtrang`, `trangthai`) VALUES
('111222111333000', 10, 1, 18, 1, 1),
('111222111333001', 10, 1, 16, 1, 1),
('111222333444500', 9, 1, NULL, 0, 1),
('111222333444501', 9, 1, 13, 1, 1),
('111222333444502', 9, 1, NULL, 0, 1),
('111222333444503', 9, 1, 18, 1, 1),
('111222333444504', 9, 1, NULL, 0, 1),
('111222333444505', 9, 1, NULL, 0, 1),
('111222333444506', 9, 1, NULL, 0, 1),
('111222333444507', 9, 1, 19, 1, 1),
('111222333444508', 9, 1, NULL, 0, 1),
('111222333444509', 9, 1, NULL, 0, 1),
('111222333444510', 9, 1, NULL, 0, 1),
('111222333444511', 9, 1, NULL, 0, 1),
('111222333444512', 9, 1, NULL, 0, 1),
('111222333444513', 9, 1, NULL, 0, 1),
('111222333444514', 9, 1, NULL, 0, 1),
('111222333444515', 9, 1, NULL, 0, 1),
('111222333444516', 9, 1, NULL, 0, 1),
('111222333444517', 9, 1, NULL, 0, 1),
('111222333444518', 9, 1, NULL, 0, 1),
('111222333444519', 9, 1, NULL, 0, 1),
('111222333444520', 9, 1, NULL, 0, 1),
('111222333444521', 9, 1, NULL, 0, 1),
('111222333444522', 9, 1, NULL, 0, 1),
('123123123123100', 6, 2, NULL, 0, 1),
('123123123123101', 6, 2, NULL, 0, 1),
('123123123123102', 6, 2, NULL, 0, 1),
('123123123123103', 6, 2, NULL, 0, 1),
('123123123123104', 6, 2, NULL, 0, 1),
('123123123123105', 6, 2, NULL, 0, 1),
('123123123123311', 8, 3, NULL, 0, 1),
('123123123123312', 8, 3, NULL, 0, 1),
('123123123123313', 8, 3, 13, 1, 1),
('123123123123314', 8, 3, NULL, 0, 1),
('123123123123315', 8, 3, NULL, 0, 1),
('123123123123316', 8, 3, NULL, 0, 1),
('123123123123317', 8, 3, NULL, 0, 1),
('123123123123318', 8, 3, 18, 1, 1),
('123123123123319', 8, 3, NULL, 0, 1),
('123123123123320', 8, 3, NULL, 0, 1),
('123123123123321', 8, 3, NULL, 0, 1),
('123123123123322', 8, 3, NULL, 0, 1),
('123123123123323', 8, 3, NULL, 0, 1),
('123123123123324', 8, 3, NULL, 0, 1),
('123123123123325', 8, 3, NULL, 0, 1),
('123123123123326', 8, 3, NULL, 0, 1),
('123123123123327', 8, 3, NULL, 0, 1),
('123123123123328', 8, 3, NULL, 0, 1),
('123123123123329', 8, 3, NULL, 0, 1),
('123123123123330', 8, 3, NULL, 0, 1),
('123123123123331', 8, 3, NULL, 0, 1),
('123123123123332', 8, 3, NULL, 0, 1),
('123456787901234', 1, 4, NULL, 0, 1),
('123456787901235', 1, 4, 8, 1, 1),
('123456787901236', 1, 4, NULL, 0, 1),
('123456787901237', 1, 4, 8, 1, 1),
('123456787901238', 1, 4, NULL, 0, 1),
('123456787901239', 1, 4, 8, 1, 1),
('123456787901240', 1, 4, 8, 1, 1),
('123456787901241', 1, 4, NULL, 0, 1),
('123456787901242', 1, 4, 8, 1, 1),
('123456787901243', 1, 4, NULL, 0, 1),
('123456787901244', 1, 4, NULL, 0, 1),
('123456787901245', 1, 4, NULL, 0, 1),
('123456787901246', 1, 4, NULL, 0, 1),
('123456787901247', 1, 4, NULL, 0, 1),
('123456787901248', 1, 4, NULL, 0, 1),
('123456787901249', 1, 4, NULL, 0, 1),
('123456787901250', 1, 4, NULL, 0, 1),
('123456787901251', 1, 4, NULL, 0, 1),
('123456787901252', 1, 4, NULL, 0, 1),
('123456787901253', 1, 4, NULL, 0, 1),
('123456787901254', 1, 4, NULL, 0, 1),
('123456787901255', 1, 4, NULL, 0, 1),
('123456787901256', 1, 4, NULL, 0, 1),
('123456787901257', 1, 4, NULL, 0, 1),
('123456787901258', 1, 4, NULL, 0, 1),
('123456787901259', 1, 4, NULL, 0, 1),
('123456787901260', 1, 4, NULL, 0, 1),
('123456787901261', 1, 4, NULL, 0, 1),
('123456787901262', 1, 4, NULL, 0, 1),
('123456787901263', 1, 4, NULL, 0, 1),
('123456787901264', 1, 4, NULL, 0, 1),
('123456787901265', 1, 4, NULL, 0, 1),
('123456787901266', 1, 4, NULL, 0, 1),
('123456787901267', 1, 4, NULL, 0, 1),
('123456787901268', 1, 4, NULL, 0, 1),
('123456787901269', 1, 4, NULL, 0, 1),
('123456787901270', 1, 4, NULL, 0, 1),
('123456787901271', 1, 4, NULL, 0, 1),
('123456787901272', 1, 4, NULL, 0, 1),
('123456787901273', 1, 4, NULL, 0, 1),
('123456787901274', 1, 4, NULL, 0, 1),
('123456787901275', 1, 4, NULL, 0, 1),
('123456787901276', 1, 4, NULL, 0, 1),
('123456787901277', 1, 4, NULL, 0, 1),
('123456787901278', 1, 4, NULL, 0, 1),
('123456787901279', 1, 4, NULL, 0, 1),
('123456787901280', 1, 4, NULL, 0, 1),
('123456787901281', 1, 4, NULL, 0, 1),
('123456787901282', 1, 4, NULL, 0, 1),
('123456787901283', 1, 4, NULL, 0, 1),
('123456798901235', 8, 5, NULL, 0, 1),
('123456798901236', 8, 5, NULL, 0, 1),
('123456798901237', 8, 5, NULL, 0, 1),
('123456798901238', 8, 5, NULL, 0, 1),
('123456798901239', 8, 5, NULL, 0, 1),
('123456798901240', 8, 5, NULL, 0, 1),
('123456798901241', 8, 5, NULL, 0, 1),
('123456798901242', 8, 5, NULL, 0, 1),
('123456798901243', 8, 5, NULL, 0, 1),
('123456798901244', 8, 5, NULL, 0, 1),
('123456798901245', 8, 5, NULL, 0, 1),
('123456798901246', 8, 5, NULL, 0, 1),
('123456798901247', 8, 5, NULL, 0, 1),
('123456798901248', 8, 5, NULL, 0, 1),
('123456798901249', 8, 5, NULL, 0, 1),
('123456798901250', 8, 5, NULL, 0, 1),
('123456798901251', 8, 5, NULL, 0, 1),
('123456798901252', 8, 5, NULL, 0, 1),
('123456798901253', 8, 5, NULL, 0, 1),
('123456798901254', 8, 5, NULL, 0, 1),
('123456798901255', 8, 5, NULL, 0, 1),
('123456798901256', 8, 5, NULL, 0, 1),
('123456798901257', 8, 5, NULL, 0, 1),
('123456798901258', 8, 5, NULL, 0, 1),
('123456798901259', 8, 5, NULL, 0, 1),
('123456798901260', 8, 5, NULL, 0, 1),
('123456798901261', 8, 5, NULL, 0, 1),
('123456798901262', 8, 5, NULL, 0, 1),
('123456798901263', 8, 5, NULL, 0, 1),
('123456798901264', 8, 5, NULL, 0, 1),
('123456798901265', 8, 5, NULL, 0, 1),
('123456798901266', 8, 5, NULL, 0, 1),
('123456798901267', 8, 5, NULL, 0, 1),
('123456798901268', 8, 5, NULL, 0, 1),
('123456798901269', 8, 5, NULL, 0, 1),
('123456798901270', 8, 5, NULL, 0, 1),
('123456798901271', 8, 5, NULL, 0, 1),
('123456798901272', 8, 5, NULL, 0, 1),
('123456798901273', 8, 5, NULL, 0, 1),
('123456798901274', 8, 5, NULL, 0, 1),
('123456798901275', 8, 5, NULL, 0, 1),
('123456798901276', 8, 5, NULL, 0, 1),
('123456798901277', 8, 5, NULL, 0, 1),
('123456798901278', 8, 5, NULL, 0, 1),
('123456798901279', 8, 5, NULL, 0, 1),
('123456798901280', 8, 5, NULL, 0, 1),
('123456798901281', 8, 5, NULL, 0, 1),
('123456798901282', 8, 5, NULL, 0, 1),
('123456798901283', 8, 5, NULL, 0, 1),
('123456798901284', 8, 5, NULL, 0, 1),
('123456798901285', 8, 5, NULL, 0, 1),
('123456798901286', 8, 5, NULL, 0, 1),
('123456798901287', 8, 5, NULL, 0, 1),
('123456798901288', 8, 5, NULL, 0, 1),
('123456798901289', 8, 5, NULL, 0, 1),
('123456798901290', 8, 5, NULL, 0, 1),
('123456798901291', 8, 5, NULL, 0, 1),
('123456798901292', 8, 5, NULL, 0, 1),
('123456798901293', 8, 5, NULL, 0, 1),
('123456798901294', 8, 5, NULL, 0, 1),
('999111222333100', 2, 2, NULL, 0, 1),
('999111222333101', 2, 2, NULL, 0, 1),
('999111222333102', 2, 2, NULL, 0, 1),
('999111222333103', 2, 2, NULL, 0, 1),
('999111222333104', 2, 2, NULL, 0, 1),
('999111222333105', 2, 2, NULL, 0, 1),
('999111222333106', 2, 2, NULL, 0, 1),
('999111222333107', 2, 2, NULL, 0, 1),
('999111222333108', 2, 2, NULL, 0, 1),
('999111222333109', 2, 2, NULL, 0, 1),
('999111222333110', 2, 2, NULL, 0, 1),
('999111222333111', 2, 2, NULL, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongram`
--

CREATE TABLE `dungluongram` (
  `madlram` int(11) NOT NULL,
  `kichthuocram` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dungluongram`
--

INSERT INTO `dungluongram` (`madlram`, `kichthuocram`, `trangthai`) VALUES
(1, 128, 0),
(2, 64, 1),
(3, 18, 1),
(4, 256, 1);

-- --------------------------------------------------------

--
-- Table structure for table `dungluongrom`
--

CREATE TABLE `dungluongrom` (
  `madlrom` int(11) NOT NULL,
  `kichthuocrom` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dungluongrom`
--

INSERT INTO `dungluongrom` (`madlrom`, `kichthuocrom`, `trangthai`) VALUES
(1, 8, 0),
(2, 256, 1),
(3, 124, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `mahoadon` int(11) NOT NULL,
  `ngayxuat` date NOT NULL,
  `makhachhang` int(11) NOT NULL,
  `manhanvien` int(11) NOT NULL,
  `makhuyenmai` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`mahoadon`, `ngayxuat`, `makhachhang`, `manhanvien`, `makhuyenmai`, `trangthai`) VALUES
(8, '2025-04-09', 1, 1, 0, 1),
(9, '2025-04-17', 3, 2, 1, 1),
(10, '2025-04-08', 5, 3, 1, 1),
(11, '2025-04-24', 4, 1, 0, 1),
(12, '2025-04-15', 6, 2, 1, 1),
(13, '2025-04-29', 1, 1, 0, 1),
(14, '2025-04-30', 1, 1, 0, 1),
(15, '2025-04-30', 1, 1, 0, 1),
(16, '2025-04-30', 1, 1, 0, 0),
(17, '2025-04-30', 1, 1, 0, 0),
(18, '2025-04-30', 1, 1, 0, 1),
(19, '2025-04-30', 1, 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `maKH` int(11) NOT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `diaChi` varchar(100) DEFAULT NULL,
  `sdt` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`maKH`, `ten`, `diaChi`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Nguyen Van A', '123 Đường ABC, Hà Nội', '0901234567', 'nguyenvana@example.com', 1),
(2, 'Tran Thi B', '456 Đường DEF, TP.HCM', '0912345678', 'tranthib@example.com', 1),
(3, 'Le Van C', '789 Đường GHI, Đà Nẵng', '0923456789', 'levanc@example.com', 1),
(4, 'Pham Thi D', '321 Đường JKL, Hải Phòng', '0934567890', 'phamthid@example.com', 1),
(5, 'Hoang Van E', '654 Đường MNO, Cần Thơ', '0945678901', 'hoangvane@example.com', 1),
(6, 'Vu Thi F', '987 Đường PQR, Huế', '0956789012', 'vuthif@example.com', 1),
(7, 'Nguyen Van G', '147 Đường STU, Biên Hòa', '0967890123', 'nguyenvang@example.com', 1),
(8, 'Tran Thi H', '258 Đường VWX, Vũng Tàu', '0978901234', 'tranthih@example.com', 1),
(9, 'Le Van I', '369 Đường YZ, Nha Trang', '0989012345', 'levani@example.com', 1),
(10, 'Pham Thi J', '741 Đường ABCD, Buôn Ma Thuột', '0990123456', 'phamthij@example.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `makhuyenmai` int(11) NOT NULL,
  `tenkhuyenmai` varchar(100) NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `ngaybatdau` date DEFAULT NULL,
  `ngayketthuc` date DEFAULT NULL,
  `apdungchohoadontu` int(11) DEFAULT NULL,
  `giatri` int(11) DEFAULT NULL,
  `hinhthuc` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) NOT NULL DEFAULT 1,
  `mota` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`makhuyenmai`, `tenkhuyenmai`, `soluong`, `ngaybatdau`, `ngayketthuc`, `apdungchohoadontu`, `giatri`, `hinhthuc`, `trangthai`, `mota`) VALUES
(1, 'Khuyến Mãi Đầu Tháng 5', 100, '2025-05-01', '2025-05-15', 500000, 10, 1, 0, 'Giảm 10% cho hóa đơn từ 500000'),
(2, 'Khuyến Mãi Đầu Tháng 6', 50, '2025-06-01', '2025-06-15', 300000, 50000, 2, 0, 'Giảm 50000 Cho Hóa Đơn Từ 300000'),
(3, 'Khuyến Mãi Đại Lễ 30/4 - 1/5', 195, '2025-04-25', '2025-05-05', 100000, 2, 0, 1, 'Giảm 2% Cho Hóa Đơn Từ 100000'),
(4, 'Khuyến Mãi Tháng 6', 30, '2025-06-01', '2025-06-30', 1000000, 150000, 2, 0, 'Giảm 150000 Cho Hóa Đơn Từ 1000000'),
(5, 'Khuyến Mãi Tháng 4', 100, '2025-04-01', '2025-04-30', 1000000, 100000, 2, 0, 'Giảm 100000 Cho Hóa Đơn Từ 1000000'),
(6, 'Khuyến Mãi Đại Lễ 30/4 - 1/5', 100, '2025-04-27', '2025-05-03', 500000, 10, 1, 0, 'Giảm 10% Cho Hóa Đơn từ 500000'),
(7, 'Khuyến Mãi Tết Nguyên Đán', 200, '2025-02-20', '2025-03-31', 10000000, 200000, 2, 0, 'Giảm 200000 Cho Hóa Đơn Từ 10000000'),
(26, '', NULL, NULL, NULL, NULL, NULL, NULL, 0, ''),
(27, 'Khuyến Mãi Cuối Tháng 6', 50, '2025-06-15', '2025-06-30', 2000000, 10, 1, 0, 'Giảm 10% Cho Hóa Đơn Từ 2000000'),
(28, 'Khuyến Mãi Đầu Tháng 5', 99, '2025-04-01', '2025-05-15', 500000, 2, 1, 1, 'Giảm 2% Cho Hóa Đơn Từ 500000'),
(29, 'Khuyến Mãi Tháng 2', 100, '2025-02-01', '2025-02-28', 1000000, 5, 1, 0, 'Giảm 5% Cho Hóa Đơn Từ 1000000'),
(30, 'Khuyến Mãi Cuối Tháng 2', 200, '2025-02-15', '2025-02-28', 10000000, 150000, 2, 0, 'Giảm 150000 Cho Hóa Đơn Từ 10000000');

-- --------------------------------------------------------

--
-- Table structure for table `mausac`
--

CREATE TABLE `mausac` (
  `mamau` int(11) NOT NULL,
  `tenmau` varchar(50) NOT NULL DEFAULT '0',
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mausac`
--

INSERT INTO `mausac` (`mamau`, `tenmau`, `trangthai`) VALUES
(1, 'Yellow', 1),
(2, 'White', 1),
(3, 'Red', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(11) NOT NULL,
  `HoTen` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` int(11) DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `NgaySinh`, `GioiTinh`, `SDT`, `trangthai`) VALUES
(1, 'Nguyen Van A', '1990-01-01', 1, '0901234567', 1),
(2, 'Tran Thi B', '1985-03-10', 0, '0912345678', 1),
(3, 'Le Van C', '1992-07-20', 1, '0938765432', 1),
(4, 'Pham Minh D', '1988-09-15', 1, '0981234567', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nha_cung_cap`
--

CREATE TABLE `nha_cung_cap` (
  `maNCC` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nha_cung_cap`
--

INSERT INTO `nha_cung_cap` (`maNCC`, `ten`, `diaChi`, `sdt`, `trangthai`) VALUES
(1, 'Công Ty TNHH ABC', '123 Đường A, Quận 1, TP. HCM', '0912345678', 1),
(2, 'Công Ty CP DEF', '456 Đường B, Quận 2, TP. HCM', '0923456789', 1),
(3, 'Nhà Cung Cấp GHI', '789 Đường C, Hà Nội', '0934567890', 1),
(4, 'Công Ty TNHH JKL', '321 Đường D, Đà Nẵng', '0945678901', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `maPhieuBH` int(11) NOT NULL,
  `ngayLap` date DEFAULT NULL,
  `maKhachHang` int(11) DEFAULT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `ghiChu` text DEFAULT NULL,
  `trangThai` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieubaohanh`
--

INSERT INTO `phieubaohanh` (`maPhieuBH`, `ngayLap`, `maKhachHang`, `MaNhanVien`, `ghiChu`, `trangThai`) VALUES
(1, '2025-04-27', 1, 1, '', 1),
(2, '2025-04-27', 1, 1, '', 0),
(3, '2025-04-27', 1, 1, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maPhieuNhap` int(11) NOT NULL,
  `ngayNhap` date NOT NULL,
  `maNhaCungCap` int(11) NOT NULL,
  `maNhanVien` int(11) NOT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maPhieuNhap`, `ngayNhap`, `maNhaCungCap`, `maNhanVien`, `trangthai`) VALUES
(1, '2025-04-05', 1, 1, 1),
(2, '2025-04-05', 3, 1, 1),
(3, '2025-04-05', 4, 1, 1),
(4, '2025-04-09', 1, 1, 1),
(5, '2025-04-09', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieusuachua`
--

CREATE TABLE `phieusuachua` (
  `maPhieuSC` int(11) NOT NULL,
  `maPhieuBH` int(11) DEFAULT NULL,
  `maSanPham` int(11) DEFAULT NULL,
  `maIMEI` varchar(50) DEFAULT NULL,
  `ngayNhan` date DEFAULT NULL,
  `tinhTrang` text DEFAULT NULL,
  `xuLy` text DEFAULT NULL,
  `trangThai` varchar(50) DEFAULT NULL,
  `ghiChu` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieusuachua`
--

INSERT INTO `phieusuachua` (`maPhieuSC`, `maPhieuBH`, `maSanPham`, `maIMEI`, `ngayNhan`, `tinhTrang`, `xuLy`, `trangThai`, `ghiChu`) VALUES
(9, 1, 6, '123123123123100', '2025-04-27', 'hư pin ', 'thay pin', 'Đã sửa xong', '');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `maQuyen` int(11) NOT NULL,
  `tenQuyen` varchar(100) NOT NULL,
  `danhSachChucNang` text DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`maQuyen`, `tenQuyen`, `danhSachChucNang`, `trangthai`) VALUES
(1, 'admin', 'Sản Phẩm:rcfd /Thuộc tính:rcfd /Phiếu Nhập:rcfd /Hóa đơn:rcfd /Khách Hàng:rcfd /Nhà Cung Cấp:rcfd /Nhân Viên:rcfd /Tài Khoản:rcfd /Bảo Hành:rcfd /Khuyến Mãi:rcfd /Phân Quyền:rcfd /Thống kê:rcfd /', 1),
(2, 'quản lý', 'Sản Phẩm:rcfd /Thuộc tính: /Phiếu Nhập: /Hóa đơn:rcfd /Khách Hàng:rcfd /Nhà Cung Cấp:rcfd /Nhân Viên: /Tài Khoản: /Bảo Hành: /Khuyến Mãi: rcfd/Phân Quyền: /Thống kê: rcfd/', 1),
(3, 'nhân viên', 'Sản Phẩm:rcfd /Thuộc tính: /Phiếu Nhập: /Hóa đơn:rcfd /Khách Hàng: /Nhà Cung Cấp: /Nhân Viên: /Tài Khoản: /Bảo Hành: /Khuyến Mãi: rcfd/Phân Quyền: /Thống kê: /', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `maSP` int(11) NOT NULL,
  `tenSP` varchar(50) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `giaNhap` int(11) DEFAULT NULL,
  `giaBan` int(11) DEFAULT NULL,
  `mauSac` varchar(100) DEFAULT NULL,
  `thuongHieu` varchar(100) DEFAULT NULL,
  `Ram` int(11) DEFAULT NULL,
  `Rom` int(11) DEFAULT NULL,
  `Chip` varchar(100) DEFAULT NULL,
  `thoiGianBaoHanh` float DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`maSP`, `tenSP`, `img`, `soLuong`, `giaNhap`, `giaBan`, `mauSac`, `thuongHieu`, `Ram`, `Rom`, `Chip`, `thoiGianBaoHanh`, `trangthai`) VALUES
(1, 'iPhone 14 Pro Max', 'src/resources/img/phone7.jpeg', 55, 25000000, 28000000, 'Đen', 'Apple', 6, 128, 'A16 Bionic', 12, 1),
(2, 'Samsung Galaxy S23 Ultra', 'src/resources/img/phone4.jpeg', 8, 23000000, 26000000, 'Xanh', 'Samsung', 12, 256, 'Snapdragon 8 Gen 2', 12, 1),
(3, 'Xiaomi 13 Pro', 'src/resources/img/phone6.jpeg', 15, 18000000, 20000000, 'Trắng', 'Xiaomi', 12, 256, 'Snapdragon 8 Gen 2', 12, 1),
(4, 'Oppo Find X5 Pro', 'src/resources/img/phone8.jpeg', 12, 19000000, 22000000, 'Đen', 'Oppo', 8, 256, 'Snapdragon 8 Gen 1', 12, 1),
(5, 'Vivo X90 Pro', 'src/resources/img/phone9.jpeg', 7, 17000000, 19500000, 'Đỏ', 'Vivo', 12, 512, 'Dimensity 9200', 12, 1),
(6, 'Realme GT 3', 'src/resources/img/phone2.jpeg', 20, 12000000, 14000000, 'Tím', 'Realme', 8, 256, 'Snapdragon 8+ Gen 1', 12, 1),
(7, 'Google Pixel 7 Pro', 'src/resources/img/phone9.jpeg', 6, 21000000, 24000000, 'Xám', 'Google', 12, 256, 'Google Tensor G2', 12, 1),
(8, 'iPhone 13', 'src/resources/img/phone1.jpeg', 76, 19000000, 22000000, 'Xanh', 'Apple', 4, 128, 'A15 Bionic', 12, 1),
(9, 'Samsung Galaxy Z Flip4', 'src/resources/img/phone3.jpeg', 7, 22000000, 25000000, 'Hồng', 'Samsung', 8, 256, 'Snapdragon 8+ Gen 1', 12, 1),
(10, 'Xiaomi Redmi Note 12 Pro', 'src/resources/img/phone4.jpg', 20, 8000000, 10000000, 'Xanh dương', 'Xiaomi', 8, 128, 'Dimensity 1080', 12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaNV` int(11) NOT NULL,
  `TenDangNhap` varchar(50) DEFAULT NULL,
  `MatKhau` varchar(100) DEFAULT NULL,
  `MaQuyen` int(11) DEFAULT NULL,
  `trangthai` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MaNV`, `TenDangNhap`, `MatKhau`, `MaQuyen`, `trangthai`) VALUES
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 1),
(2, 'quanly', '34a378e876712e095ed1059a4ee44ea2ac7c93831dd4f58e5e980853a6b7999e', 2, 1),
(3, 'nhanvien', 'fa5a1d3e67d2193b86bc68c7db41bd84f242fe4e41146ef4a4a5441254d2a3f7', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL,
  `tenthuonghieu` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`, `trangthai`) VALUES
(1, 'Apple', 1),
(2, 'samsung', 1),
(3, 'xiaomi', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`machitiethoadon`),
  ADD KEY `FK_chitiethoadon_hoadon` (`mahoadon`),
  ADD KEY `FK_chitiethoadon_sanpham` (`masanpham`);

--
-- Indexes for table `chitietphieubaohanh`
--
ALTER TABLE `chitietphieubaohanh`
  ADD PRIMARY KEY (`MaChiTiet`),
  ADD KEY `MaSanPham` (`MaSanPham`),
  ADD KEY `chitietphieubaohanh_ibfk_1` (`MaPhieuBH`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`maCTPhieuNhap`),
  ADD KEY `maPhieuNhap` (`maPhieuNhap`),
  ADD KEY `maSanPham` (`maSanPham`);

--
-- Indexes for table `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD PRIMARY KEY (`maimei`),
  ADD KEY `FK_imei_sanpham` (`masanpham`),
  ADD KEY `FK_imei_phieunhap` (`maphieunhap`);

--
-- Indexes for table `dungluongram`
--
ALTER TABLE `dungluongram`
  ADD PRIMARY KEY (`madlram`);

--
-- Indexes for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  ADD PRIMARY KEY (`madlrom`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahoadon`),
  ADD KEY `FK_hoadon_taikhoan` (`manhanvien`),
  ADD KEY `FK_hoadon_khachhang` (`makhachhang`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`maKH`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`makhuyenmai`);

--
-- Indexes for table `mausac`
--
ALTER TABLE `mausac`
  ADD PRIMARY KEY (`mamau`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  ADD PRIMARY KEY (`maNCC`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maPhieuNhap`),
  ADD KEY `maNhaCungCap` (`maNhaCungCap`),
  ADD KEY `maNhanVien` (`maNhanVien`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`maQuyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`maSP`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `FK_TaiKhoan_Quyen` (`MaQuyen`);

--
-- Indexes for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `machitiethoadon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `maCTPhieuNhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `dungluongram`
--
ALTER TABLE `dungluongram`
  MODIFY `madlram` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dungluongrom`
--
ALTER TABLE `dungluongrom`
  MODIFY `madlrom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahoadon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `maKH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `makhuyenmai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `mausac`
--
ALTER TABLE `mausac`
  MODIFY `mamau` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nha_cung_cap`
--
ALTER TABLE `nha_cung_cap`
  MODIFY `maNCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maPhieuNhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `quyen`
--
ALTER TABLE `quyen`
  MODIFY `maQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `FK_cthoadon_hoadon` FOREIGN KEY (`mahoadon`) REFERENCES `hoadon` (`mahoadon`);

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`maPhieuNhap`) REFERENCES `phieunhap` (`maPhieuNhap`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSP`);

--
-- Constraints for table `ctsanpham`
--
ALTER TABLE `ctsanpham`
  ADD CONSTRAINT `FK_imei_phieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maPhieuNhap`),
  ADD CONSTRAINT `FK_imei_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`maSP`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`maNhaCungCap`) REFERENCES `nha_cung_cap` (`maNCC`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TaiKhoan_NhanVien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `FK_TaiKhoan_Quyen` FOREIGN KEY (`MaQuyen`) REFERENCES `quyen` (`maQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
