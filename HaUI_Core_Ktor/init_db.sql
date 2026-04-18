CREATE DATABASE IF NOT EXISTS hau_tuyensinh CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hau_tuyensinh;

CREATE TABLE IF NOT EXISTS Truong (maTruong VARCHAR(10) PRIMARY KEY, tenTruong VARCHAR(255) NOT NULL, moTa TEXT, thanhPho VARCHAR(255), quan VARCHAR(255), duong VARCHAR(255));
CREATE TABLE IF NOT EXISTS ChuongTrinhHoc (id VARCHAR(10) PRIMARY KEY, thoiGianHoc VARCHAR(255), loai VARCHAR(255));
CREATE TABLE IF NOT EXISTS NganhHoc (maNganh VARCHAR(10) PRIMARY KEY, tenNganh VARCHAR(255) NOT NULL, moTa TEXT, coSo VARCHAR(255));
CREATE TABLE IF NOT EXISTS NganhNghe (maNghe VARCHAR(10) PRIMARY KEY, tenNghe VARCHAR(255), mucLuong VARCHAR(255), tinhTrang VARCHAR(255), NganhHocmaNganh VARCHAR(10), FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh));
CREATE TABLE IF NOT EXISTS ChiTieu (maChiTieu VARCHAR(10) PRIMARY KEY, nam VARCHAR(255), soLuong VARCHAR(255), phuongThuc VARCHAR(255), NganhHocmaNganh VARCHAR(10), FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh));
CREATE TABLE IF NOT EXISTS TinChi (id VARCHAR(10) PRIMARY KEY, giaTien VARCHAR(255), NganhHocmaNganh VARCHAR(10), FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh));
CREATE TABLE IF NOT EXISTS YeuCauDauVao (id VARCHAR(10) PRIMARY KEY, khoi VARCHAR(255), diemTong VARCHAR(255), NganhHocmaNganh VARCHAR(10), FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh));
CREATE TABLE IF NOT EXISTS QuyTrinhNhapHoc (id VARCHAR(10) PRIMARY KEY, noiDung TEXT, TruongmaTruong VARCHAR(10), FOREIGN KEY (TruongmaTruong) REFERENCES Truong(maTruong));
CREATE TABLE IF NOT EXISTS HocPhi (id VARCHAR(10) PRIMARY KEY, soTien VARCHAR(255), namHoc VARCHAR(255), ChuongTrinhHocid VARCHAR(10), FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id));
CREATE TABLE IF NOT EXISTS HocBong (id VARCHAR(10) PRIMARY KEY, loaiHb VARCHAR(255), diemYc VARCHAR(255), hanhKiemYc VARCHAR(255), ChuongTrinhHocid VARCHAR(10), FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id));
CREATE TABLE IF NOT EXISTS KhoaHoc (id INT AUTO_INCREMENT PRIMARY KEY, ChuongTrinhHocid VARCHAR(10), NganhHocmaNganh VARCHAR(10), FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id), FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh));
CREATE TABLE IF NOT EXISTS TaiKhoan (id VARCHAR(10) PRIMARY KEY, username VARCHAR(255) UNIQUE NOT NULL, password_hash VARCHAR(255) NOT NULL, role VARCHAR(20) DEFAULT 'USER');
CREATE TABLE IF NOT EXISTS NguoiDung (id VARCHAR(10) PRIMARY KEY, ten VARCHAR(255), avatar VARCHAR(255), email VARCHAR(255), vaiTro VARCHAR(255), TaiKhoanid VARCHAR(10), FOREIGN KEY (TaiKhoanid) REFERENCES TaiKhoan(id));
CREATE TABLE IF NOT EXISTS TinTuc (id VARCHAR(10) PRIMARY KEY, tieuDe VARCHAR(255), anh VARCHAR(255), noiDung TEXT, moTa VARCHAR(255), TruongmaTruong VARCHAR(10), FOREIGN KEY (TruongmaTruong) REFERENCES Truong(maTruong));

INSERT IGNORE INTO Truong VALUES ('DCN','Dai hoc Cong nghiep Ha Noi (HaUI)','Truong Dai hoc Cong nghiep Ha Noi la co so giao duc dai hoc cong lap voi hon 120 nam lich su. Truong dao tao da nganh: ky thuat - cong nghe, kinh te, ngoai ngu. Day la mot trong nhung truong dai hoc ky thuat hang dau mien Bac Viet Nam.','Ha Noi','Bac Tu Liem','So 298 duong Cau Dien');

INSERT IGNORE INTO ChuongTrinhHoc VALUES ('CTH001','4 nam','Dai tra'),('CTH002','4 nam','Chat luong cao'),('CTH003','4 nam','Tien tien');

INSERT IGNORE INTO NganhHoc VALUES
('7480201','Cong nghe thong tin','Dao tao ky su CNTT co kien thuc nen tang vung chac ve lap trinh, co so du lieu, mang may tinh va phat trien ung dung. Chuong trinh tap trung vao lap trinh web, di dong, AI va he thong thong tin.','Co so Cau Dien'),
('7480101','Khoa hoc may tinh','Dao tao chuyen sau ve tri tue nhan tao, hoc may, xu ly ngon ngu tu nhien, dien toan dam may va phan tich du lieu lon.','Co so Cau Dien'),
('7510301','Cong nghe ky thuat dien, dien tu','Dao tao ky su dien - dien tu voi kha nang thiet ke, lap rap va van hanh he thong dien cong suat lon, dien tu va tu dong hoa.','Co so Cau Dien'),
('7520116','Cong nghe ky thuat co khi','Dao tao ky su co khi voi ky nang thiet ke va che tao may, CAD/CAM, vat lieu ky thuat, cong nghe che tao hien dai.','Co so Cau Dien'),
('7340101','Quan tri kinh doanh','Dao tao cu nhan kinh doanh co kha nang quan ly doanh nghiep, lap ke hoach kinh doanh, phan tich tai chinh va marketing chien luoc.','Co so Cau Dien'),
('7340301','Ke toan','Dao tao cu nhan ke toan voi ky nang hach toan ke toan, kiem toan, lap bao cao tai chinh theo chuan Viet Nam va quoc te.','Co so Cau Dien');

INSERT IGNORE INTO NganhNghe VALUES
('NN01','Lap trinh vien phan mem','15 - 40 trieu dong/thang','Nhu cau tuyen dung cao','7480201'),
('NN02','Ky su AI - Machine Learning','20 - 60 trieu dong/thang','Rat khan hiem, luong cao','7480101'),
('NN03','Ky su du lieu (Data Engineer)','18 - 50 trieu dong/thang','Nhu cau tuyen dung rat cao','7480101'),
('NN04','Ky su dien cong trinh','12 - 25 trieu dong/thang','Nhu cau on dinh','7510301'),
('NN05','Ky thuat vien tu dong hoa','15 - 30 trieu dong/thang','Nganh san xuat dang tang truong manh','7510301'),
('NN06','Ky su thiet ke co khi CAD/CAM','12 - 28 trieu dong/thang','Nhu cau on dinh trong nganh san xuat','7520116'),
('NN07','Chuyen vien phan tich kinh doanh','10 - 25 trieu dong/thang','Nhu cau cao trong doanh nghiep FDI','7340101'),
('NN08','Kiem toan vien','12 - 30 trieu dong/thang','On dinh, chung chi quoc te luong rat cao','7340301');

INSERT IGNORE INTO YeuCauDauVao VALUES
('YC001','A00 (Toan, Ly, Hoa)','22.0','7480201'),('YC002','A01 (Toan, Ly, Anh)','23.5','7480201'),
('YC003','D01 (Toan, Van, Anh)','21.0','7480201'),('YC004','A00 (Toan, Ly, Hoa)','24.0','7480101'),
('YC005','A01 (Toan, Ly, Anh)','24.5','7480101'),('YC006','A00 (Toan, Ly, Hoa)','19.5','7510301'),
('YC007','A01 (Toan, Ly, Anh)','20.0','7510301'),('YC008','A00 (Toan, Ly, Hoa)','18.5','7520116'),
('YC009','D01 (Toan, Van, Anh)','20.0','7340101'),('YC010','A00 (Toan, Ly, Hoa)','19.0','7340301');

INSERT IGNORE INTO ChiTieu VALUES
('CT001','2024','350','Xet tuyen diem thi THPT','7480201'),('CT002','2024','100','Xet tuyen hoc ba THPT','7480201'),
('CT003','2024','200','Xet tuyen diem thi THPT','7480101'),('CT004','2024','300','Xet tuyen diem thi THPT','7510301'),
('CT005','2024','250','Xet tuyen diem thi THPT','7520116'),('CT006','2024','200','Xet tuyen hoc ba THPT','7340101'),
('CT007','2023','320','Xet tuyen diem thi THPT','7480201'),('CT008','2023','180','Xet tuyen diem thi THPT','7480101');

INSERT IGNORE INTO TinChi VALUES
('TC001','385.000 dong/tin chi','7480201'),('TC002','450.000 dong/tin chi (CLC)','7480201'),
('TC003','385.000 dong/tin chi','7480101'),('TC004','450.000 dong/tin chi (CLC)','7480101'),
('TC005','355.000 dong/tin chi','7510301'),('TC006','355.000 dong/tin chi','7520116'),
('TC007','340.000 dong/tin chi','7340101'),('TC008','340.000 dong/tin chi','7340301');

INSERT IGNORE INTO HocPhi VALUES
('HP001','14.000.000 - 16.000.000 dong/nam','2024-2025','CTH001'),
('HP002','22.000.000 - 28.000.000 dong/nam','2024-2025','CTH002'),
('HP003','30.000.000 - 36.000.000 dong/nam','2024-2025','CTH003'),
('HP004','13.000.000 - 15.000.000 dong/nam','2023-2024','CTH001');

INSERT IGNORE INTO HocBong VALUES
('HB001','Hoc bong Khuyen khich hoc tap loai Gioi','3.6 / 4.0','Tot','CTH001'),
('HB002','Hoc bong Khuyen khich hoc tap loai Kha','3.2 / 4.0','Kha tro len','CTH001'),
('HB003','Hoc bong Tan sinh vien xuat sac dau vao','Diem thi THPT tu 27.0 tro len','Tot','CTH001'),
('HB004','Hoc bong Chinh phu (Danh cho sinh vien dan toc)','GPA tu 2.5 tro len','Trung binh kha','CTH001'),
('HB005','Hoc bong Doanh nghiep Samsung FPT Viettel','3.4 / 4.0','Tot','CTH002');

INSERT IGNORE INTO QuyTrinhNhapHoc VALUES
('QT001','Buoc 1 - Dang ky xet tuyen: Thi sinh nop ho so qua cong thong tin tai tuyensinh.haui.edu.vn trong thoi gian quy dinh.','DCN'),
('QT002','Buoc 2 - Kiem tra dieu kien: Nha truong xet duyet ho so, kiem tra dieu kien dau vao va thong bao danh sach trung tuyen.','DCN'),
('QT003','Buoc 3 - Xac nhan nhap hoc: Sinh vien trung tuyen xac nhan nhap hoc truc tuyen va hoan thanh thanh toan hoc phi hoc ky dau tien.','DCN'),
('QT004','Buoc 4 - Chuan bi ho so goc: Sinh vien chuan bi: Bang tot nghiep THPT, Hoc ba cong chung, CCCD, anh 3x4, Giay khai sinh, Giay kham suc khoe.','DCN'),
('QT005','Buoc 5 - Nop ho so va nhap hoc: Sinh vien den nop ho so goc tai Phong A103 Co so 298 Cau Dien, nhan the sinh vien va hoan tat thu tuc nhap hoc.','DCN');

INSERT IGNORE INTO TaiKhoan VALUES ('TK001','admin','P@ssw0rd123','ADMIN'),('TK002','sinhvien1','sv123456','USER'),('TK003','sinhvien2','sv654321','USER');
INSERT IGNORE INTO NguoiDung VALUES ('ND001','Quan tri vien HaUI','default_admin.png','admin@haui.edu.vn','Quan tri he thong','TK001'),('ND002','Nguyen Van An','default_sv.png','nva2024@student.haui.edu.vn','Sinh vien','TK002'),('ND003','Tran Thi Binh','default_sv.png','ttb2024@student.haui.edu.vn','Sinh vien','TK003');

INSERT IGNORE INTO TinTuc VALUES
('TT001','Thong bao tuyen sinh dai hoc chinh quy nam 2024 - HaUI','https://haui.edu.vn/img/ts2024.jpg','HaUI thong bao ke hoach tuyen sinh 2024. Tong chi tieu 6500 sinh vien voi 52 nganh dao tao. Thoi gian dang ky: 01/07 den 31/08/2024.','HaUI thong bao tuyen sinh 6.500 chi tieu voi 52 nganh dao tao nam 2024.','DCN'),
('TT002','Diem chuan trung tuyen dai hoc chinh quy 2023 - HaUI','https://haui.edu.vn/img/dc2023.jpg','HaUI cong bo diem chuan 2023: CNTT 24.5 (A01), Khoa hoc may tinh 25.0 (A01), Ky thuat dien 22.0 (A00), QTKD 22.5 (D01).','Cong bo diem chuan tuyen sinh 2023 - CNTT 24.5, KHMT 25.0 diem.','DCN'),
('TT003','HaUI to chuc Ngay hoi tu van tuyen sinh mo 2024','https://haui.edu.vn/img/ngayhoi.jpg','Ngay 15/03/2024, HaUI to chuc ngay hoi tu van tuyen sinh mo cho hoc sinh THPT. Hoc sinh gap truc tiep giang vien, tham quan phong lab hien dai.','HaUI to chuc ngay hoi tu van tuyen sinh ngay 15/03/2024 cho THPT ca nuoc.','DCN'),
('TT004','Hoc bong Samsung 2024 danh cho sinh vien nganh Ky thuat - Cong nghe','https://haui.edu.vn/img/samsung.jpg','Samsung trao 50 hoc bong cho sinh vien xuat sac HaUI nganh Ky thuat. Hoc bong tri gia 15 trieu dong/sinh vien kem co hoi thuc tap tai Samsung. Dieu kien: GPA tu 3.4/4.0.','Samsung trao 50 hoc bong 15 trieu dong cho sinh vien HaUI nganh ky thuat.','DCN'),
('TT005','HaUI dat chuan kiem dinh chat luong AUN-QA 2024','https://haui.edu.vn/img/aunqa.jpg','HaUI duoc cong nhan dat chuan AUN-QA 2024 cho 5 chuong trinh dao tao: CNTT, Ky thuat dien, Co khi, QTKD va Ke toan.','HaUI dat chuan AUN-QA 2024 cho 5 chuong trinh dao tao trong diem.','DCN');

INSERT IGNORE INTO KhoaHoc (ChuongTrinhHocid, NganhHocmaNganh) VALUES ('CTH001','7480201'),('CTH002','7480201'),('CTH001','7480101'),('CTH002','7480101'),('CTH001','7510301'),('CTH001','7520116'),('CTH001','7340101'),('CTH001','7340301');

SELECT 'SEED DATA HAUI LOAD THANH CONG!' AS KetQua, COUNT(*) AS SoNganh FROM NganhHoc;