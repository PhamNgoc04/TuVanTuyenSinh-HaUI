CREATE DATABASE IF NOT EXISTS hau_tuyensinh CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hau_tuyensinh;

-- Table: ChiTieu
CREATE TABLE IF NOT EXISTS `ChiTieu` (
`maChiTieu` VARCHAR(255) PRIMARY KEY,
`nam` VARCHAR(255),
`soLuong` VARCHAR(255),
`phuongThuc` VARCHAR(255),
`NganhHocmaNganh` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: ChuongTrinhHoc
CREATE TABLE IF NOT EXISTS `ChuongTrinhHoc` (
`id` VARCHAR(255) PRIMARY KEY,
`thoiGianHoc` VARCHAR(255),
`loai` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: HocBong
CREATE TABLE IF NOT EXISTS `HocBong` (
`id` VARCHAR(255) PRIMARY KEY,
`loaiHb` VARCHAR(255),
`diemYc` VARCHAR(255),
`hanhKiemYc` VARCHAR(255),
`ChuongTrinhHocid` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: HocPhi
CREATE TABLE IF NOT EXISTS `HocPhi` (
`id` VARCHAR(255) PRIMARY KEY,
`soTien` VARCHAR(255),
`namHoc` VARCHAR(255),
`ChuongTrinhHocid` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: NganhHoc
CREATE TABLE IF NOT EXISTS `NganhHoc` (
`maNganh` VARCHAR(255) PRIMARY KEY,
`tenNganh` VARCHAR(255),
`coSo` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: NganhNghe
CREATE TABLE IF NOT EXISTS `NganhNghe` (
`maNghe` VARCHAR(255) PRIMARY KEY,
`tenNghe` VARCHAR(255),
`mucLuong` VARCHAR(255),
`tinhTrang` VARCHAR(255),
`NganhHocmaNganh` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: NguoiDung
CREATE TABLE IF NOT EXISTS `NguoiDung` (
`id` VARCHAR(255) PRIMARY KEY,
`ten` VARCHAR(255),
`avatar` VARCHAR(255),
`email` VARCHAR(255),
`vaiTro` VARCHAR(255),
`TaiKhoanid` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: QuyTrinhNhapHoc
CREATE TABLE IF NOT EXISTS `QuyTrinhNhapHoc` (
`id` VARCHAR(255) PRIMARY KEY,
`TruongmaTruong` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: TaiKhoan
CREATE TABLE IF NOT EXISTS `TaiKhoan` (
`id` VARCHAR(255) PRIMARY KEY,
`username` VARCHAR(255),
`password_hash` VARCHAR(255),
`role` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: TinChi
CREATE TABLE IF NOT EXISTS `TinChi` (
`id` VARCHAR(255) PRIMARY KEY,
`giaTien` VARCHAR(255),
`NganhHocmaNganh` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: TinTuc
CREATE TABLE IF NOT EXISTS `TinTuc` (
`id` VARCHAR(255) PRIMARY KEY,
`tieuDe` VARCHAR(255),
`anh` VARCHAR(255),
`moTa` VARCHAR(255),
`TruongmaTruong` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: Truong
CREATE TABLE IF NOT EXISTS `Truong` (
`maTruong` VARCHAR(255) PRIMARY KEY,
`tenTruong` VARCHAR(255),
`thanhPho` VARCHAR(255),
`quan` VARCHAR(255),
`duong` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table: YeuCauDauVao
CREATE TABLE IF NOT EXISTS `YeuCauDauVao` (
`id` VARCHAR(255) PRIMARY KEY,
`khoi` VARCHAR(255),
`diemTong` VARCHAR(255),
`NganhHocmaNganh` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

