-- ============================================================
--  HaUI Tuyển Sinh – KHỞI TẠO & DỮ LIỆU MẪU
--  Ngày cập nhật: 2024-08-01
--  Phiên bản: 2.0  (utf8mb4 | utf8mb4_unicode_ci)
-- ============================================================

CREATE DATABASE IF NOT EXISTS hau_tuyensinh
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE hau_tuyensinh;

-- ============================================================
--  BẢNG CẤU TRÚC
-- ============================================================

CREATE TABLE IF NOT EXISTS Truong (
    maTruong  VARCHAR(10)  PRIMARY KEY,
    tenTruong VARCHAR(255) NOT NULL,
    moTa      TEXT,
    thanhPho  VARCHAR(255),
    quan      VARCHAR(255),
    duong     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ChuongTrinhHoc (
    id          VARCHAR(10) PRIMARY KEY,
    thoiGianHoc VARCHAR(255),
    loai        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS NganhHoc (
    maNganh VARCHAR(10)  PRIMARY KEY,
    tenNganh VARCHAR(255) NOT NULL,
    moTa    TEXT,
    coSo    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS NganhNghe (
    maNghe           VARCHAR(10)  PRIMARY KEY,
    tenNghe          VARCHAR(255),
    mucLuong         VARCHAR(255),
    tinhTrang        VARCHAR(255),
    NganhHocmaNganh  VARCHAR(10),
    FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh)
);

CREATE TABLE IF NOT EXISTS ChiTieu (
    maChiTieu       VARCHAR(10)  PRIMARY KEY,
    nam             VARCHAR(255),
    soLuong         VARCHAR(255),
    phuongThuc      VARCHAR(255),
    NganhHocmaNganh VARCHAR(10),
    FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh)
);

CREATE TABLE IF NOT EXISTS TinChi (
    id              VARCHAR(10)  PRIMARY KEY,
    giaTien         VARCHAR(255),
    NganhHocmaNganh VARCHAR(10),
    FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh)
);

CREATE TABLE IF NOT EXISTS YeuCauDauVao (
    id              VARCHAR(10)  PRIMARY KEY,
    khoi            VARCHAR(255),
    diemTong        VARCHAR(255),
    NganhHocmaNganh VARCHAR(10),
    FOREIGN KEY (NganhHocmaNganh) REFERENCES NganhHoc(maNganh)
);

CREATE TABLE IF NOT EXISTS QuyTrinhNhapHoc (
    id           VARCHAR(10)  PRIMARY KEY,
    noiDung      TEXT,
    TruongmaTruong VARCHAR(10),
    FOREIGN KEY (TruongmaTruong) REFERENCES Truong(maTruong)
);

CREATE TABLE IF NOT EXISTS HocPhi (
    id              VARCHAR(10)  PRIMARY KEY,
    soTien          VARCHAR(255),
    namHoc          VARCHAR(255),
    ChuongTrinhHocid VARCHAR(10),
    FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id)
);

CREATE TABLE IF NOT EXISTS HocBong (
    id               VARCHAR(10)  PRIMARY KEY,
    loaiHb           VARCHAR(255),
    diemYc           VARCHAR(255),
    hanhKiemYc       VARCHAR(255),
    ChuongTrinhHocid VARCHAR(10),
    FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id)
);

CREATE TABLE IF NOT EXISTS KhoaHoc (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    ChuongTrinhHocid VARCHAR(10),
    NganhHocmaNganh  VARCHAR(10),
    FOREIGN KEY (ChuongTrinhHocid) REFERENCES ChuongTrinhHoc(id),
    FOREIGN KEY (NganhHocmaNganh)  REFERENCES NganhHoc(maNganh)
);

CREATE TABLE IF NOT EXISTS TaiKhoan (
    id            VARCHAR(10)  PRIMARY KEY,
    username      VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role          VARCHAR(20)  DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS NguoiDung (
    id         VARCHAR(10)  PRIMARY KEY,
    ten        VARCHAR(255),
    avatar     VARCHAR(255),
    email      VARCHAR(255),
    vaiTro     VARCHAR(255),
    TaiKhoanid VARCHAR(10),
    FOREIGN KEY (TaiKhoanid) REFERENCES TaiKhoan(id)
);

CREATE TABLE IF NOT EXISTS TinTuc (
    id            VARCHAR(10)  PRIMARY KEY,
    tieuDe        VARCHAR(255),
    anh           VARCHAR(255),
    noiDung       TEXT,
    moTa          VARCHAR(255),
    TruongmaTruong VARCHAR(10),
    FOREIGN KEY (TruongmaTruong) REFERENCES Truong(maTruong)
);

-- ============================================================
--  XÓA DỮ LIỆU CŨ (không dấu) – CHẠY LẠI SẠCH
--  Tắt FK check để tránh lỗi ràng buộc khóa ngoại
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE KhoaHoc;
TRUNCATE TABLE TinTuc;
TRUNCATE TABLE NguoiDung;
TRUNCATE TABLE TaiKhoan;
TRUNCATE TABLE HocBong;
TRUNCATE TABLE HocPhi;
TRUNCATE TABLE QuyTrinhNhapHoc;
TRUNCATE TABLE TinChi;
TRUNCATE TABLE ChiTieu;
TRUNCATE TABLE YeuCauDauVao;
TRUNCATE TABLE NganhNghe;
TRUNCATE TABLE NganhHoc;
TRUNCATE TABLE ChuongTrinhHoc;
TRUNCATE TABLE Truong;

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================
--  DỮ LIỆU: TRƯỜNG
-- ============================================================

INSERT IGNORE INTO Truong VALUES (
    'DCN',
    'Đại học Công nghiệp Hà Nội (HaUI)',
    'Trường Đại học Công nghiệp Hà Nội là cơ sở giáo dục đại học công lập có hơn 120 năm lịch sử hình thành và phát triển. Trường đào tạo đa ngành: kỹ thuật – công nghệ, kinh tế, ngoại ngữ. Là một trong những trường đại học kỹ thuật hàng đầu miền Bắc Việt Nam với hơn 40.000 sinh viên đang theo học.',
    'Hà Nội',
    'Bắc Từ Liêm',
    'Số 298 đường Cầu Diễn'
);

-- ============================================================
--  DỮ LIỆU: CHƯƠNG TRÌNH HỌC
-- ============================================================

INSERT IGNORE INTO ChuongTrinhHoc VALUES
    ('CTH001', '4 năm', 'Đại trà'),
    ('CTH002', '4 năm', 'Chất lượng cao'),
    ('CTH003', '4 năm', 'Tiên tiến'),
    ('CTH004', '4,5 năm', 'Đại trà (Kiến trúc)'),
    ('CTH005', '3 năm', 'Cao đẳng liên thông');

-- ============================================================
--  DỮ LIỆU: NGÀNH HỌC  (13 ngành)
-- ============================================================

INSERT IGNORE INTO NganhHoc VALUES

-- Khối Công nghệ thông tin & Máy tính
('7480201', 'Công nghệ thông tin',
 'Đào tạo kỹ sư CNTT với kiến thức nền tảng vững chắc về lập trình, cơ sở dữ liệu, mạng máy tính và phát triển ứng dụng. Chương trình tập trung vào lập trình web, di động, trí tuệ nhân tạo và hệ thống thông tin doanh nghiệp. Sinh viên tốt nghiệp có khả năng làm việc tại các tập đoàn công nghệ lớn như FPT, VNG, Samsung, Viettel.',
 'Cơ sở Cầu Diễn'),

('7480101', 'Khoa học máy tính',
 'Đào tạo chuyên sâu về trí tuệ nhân tạo, học máy, xử lý ngôn ngữ tự nhiên, thị giác máy tính, điện toán đám mây và phân tích dữ liệu lớn (Big Data). Đây là ngành có tỉ lệ việc làm cao nhất và mức lương khởi điểm hấp dẫn nhất trong lĩnh vực công nghệ.',
 'Cơ sở Cầu Diễn'),

('7480202', 'Kỹ thuật phần mềm',
 'Đào tạo kỹ sư phần mềm chuyên nghiệp với kiến thức về quy trình phát triển phần mềm Agile/Scrum, kiểm thử phần mềm, đảm bảo chất lượng (QA/QC), DevOps, CI/CD và quản trị dự án công nghệ. Sinh viên được thực hành thực tế tại các công ty phần mềm ngay từ năm 2.',
 'Cơ sở Cầu Diễn'),

('7480104', 'Hệ thống thông tin',
 'Đào tạo chuyên gia thiết kế, triển khai và quản trị hệ thống thông tin doanh nghiệp (ERP, CRM, SCM). Chương trình kết hợp kiến thức CNTT và quản trị kinh doanh, phù hợp với xu hướng chuyển đổi số đang bùng nổ tại Việt Nam.',
 'Cơ sở Cầu Diễn'),

-- Khối Điện – Điện tử
('7510301', 'Công nghệ kỹ thuật điện, điện tử',
 'Đào tạo kỹ sư điện – điện tử với khả năng thiết kế, lắp ráp và vận hành hệ thống điện công suất lớn, điện tử công nghiệp và tự động hóa. Sinh viên được học về lập trình PLC, biến tần, robot công nghiệp và hệ thống điện thông minh (Smart Grid).',
 'Cơ sở Cầu Diễn'),

('7520203', 'Kỹ thuật điều khiển và tự động hóa',
 'Đào tạo kỹ sư tự động hóa với kiến thức chuyên sâu về robot công nghiệp, hệ thống điều khiển thông minh, IoT công nghiệp (IIoT) và sản xuất thông minh (Smart Manufacturing). Đây là ngành cốt lõi của cuộc Cách mạng Công nghiệp 4.0.',
 'Cơ sở Cầu Diễn'),

('7520207', 'Kỹ thuật cơ điện tử',
 'Chương trình liên ngành kết hợp cơ khí – điện tử – tin học, đào tạo kỹ sư cơ điện tử có khả năng thiết kế robot, máy tự động thông minh và hệ thống cơ khí điều khiển số (CNC). Là cầu nối giữa cơ khí truyền thống và tự động hóa hiện đại.',
 'Cơ sở Cầu Diễn'),

-- Khối Cơ khí – Công nghệ
('7520116', 'Công nghệ kỹ thuật cơ khí',
 'Đào tạo kỹ sư cơ khí với kỹ năng thiết kế và chế tạo máy, thành thạo CAD/CAM/CAE, vật liệu kỹ thuật, gia công cắt gọt, công nghệ hàn và quản lý sản xuất. Sinh viên được thực hành trên các thiết bị CNC, trung tâm gia công tiên tiến.',
 'Cơ sở Cầu Diễn'),

('7520103', 'Kỹ thuật nhiệt',
 'Đào tạo kỹ sư nhiệt với kiến thức về hệ thống điều hòa không khí (HVAC), máy lạnh công nghiệp, lò hơi, turbine và các hệ thống nhiệt trong nhà máy điện. Nhu cầu tuyển dụng tăng mạnh trong bối cảnh phát triển hạ tầng công nghiệp.',
 'Cơ sở Cầu Diễn'),

-- Khối Kinh tế
('7340101', 'Quản trị kinh doanh',
 'Đào tạo cử nhân kinh doanh có khả năng quản lý doanh nghiệp, lập kế hoạch kinh doanh chiến lược, phân tích tài chính và marketing số (Digital Marketing). Chương trình học bằng tiếng Anh một phần, sinh viên được học kỹ năng khởi nghiệp (Startup Ecosystem).',
 'Cơ sở Cầu Diễn'),

('7340301', 'Kế toán',
 'Đào tạo cử nhân kế toán với kỹ năng hạch toán kế toán, kiểm toán nội bộ, lập báo cáo tài chính theo chuẩn mực Việt Nam (VAS) và quốc tế (IFRS). Sinh viên được ôn luyện chứng chỉ ACCA, CPA ngay trong chương trình học.',
 'Cơ sở Cầu Diễn'),

-- Khối Ngoại ngữ
('7220201', 'Ngôn ngữ Anh',
 'Đào tạo cử nhân tiếng Anh chuyên ngành với kỹ năng giao tiếp, phiên dịch, biên dịch văn bản kỹ thuật – kinh tế. Sinh viên được học kỹ năng mềm, đàm phán thương mại quốc tế và văn hóa doanh nghiệp toàn cầu.',
 'Cơ sở Cầu Diễn'),

-- Khối Công nghệ vật liệu
('7540101', 'Công nghệ vật liệu',
 'Đào tạo kỹ sư vật liệu với kiến thức về vật liệu kim loại, polymer, composite, vật liệu nano và vật liệu chức năng. Sinh viên có cơ hội nghiên cứu khoa học, hợp tác với các phòng lab đẳng cấp quốc tế.',
 'Cơ sở Cầu Diễn');

-- ============================================================
--  DỮ LIỆU: NGÀNH NGHỀ  (35 nghề)
-- ============================================================

INSERT IGNORE INTO NganhNghe VALUES

-- Ngành CNTT (7480201)
('NN01', 'Lập trình viên phần mềm (Software Developer)',   '15 – 40 triệu đồng/tháng', 'Nhu cầu tuyển dụng rất cao, thị trường cạnh tranh mạnh', '7480201'),
('NN02', 'Lập trình viên di động (iOS / Android)',           '18 – 45 triệu đồng/tháng', 'Nhu cầu cao, đặc biệt Flutter và Kotlin/Swift',           '7480201'),
('NN03', 'Kỹ sư DevOps / Cloud Engineer',                   '20 – 55 triệu đồng/tháng', 'Khan hiếm, lương cạnh tranh toàn cầu',                    '7480201'),
('NN04', 'Chuyên gia An ninh mạng (Cyber Security)',         '22 – 60 triệu đồng/tháng', 'Rất khan hiếm, nhu cầu tăng mạnh sau các vụ tấn công mạng','7480201'),
('NN05', 'Kỹ sư Back-end (Java / Node.js / Go)',             '17 – 42 triệu đồng/tháng', 'Nhu cầu ổn định và cao',                                  '7480201'),

-- Ngành Khoa học máy tính (7480101)
('NN06', 'Kỹ sư AI – Machine Learning Engineer',            '25 – 70 triệu đồng/tháng', 'Rất khan hiếm, lương cao nhất ngành công nghệ',           '7480101'),
('NN07', 'Kỹ sư dữ liệu (Data Engineer)',                   '20 – 55 triệu đồng/tháng', 'Nhu cầu tuyển dụng rất cao từ các ngân hàng, fintech',    '7480101'),
('NN08', 'Nhà khoa học dữ liệu (Data Scientist)',            '22 – 65 triệu đồng/tháng', 'Rất khan hiếm, cần nền tảng toán thống kê mạnh',          '7480101'),
('NN09', 'Kỹ sư xử lý ngôn ngữ tự nhiên (NLP Engineer)',    '25 – 70 triệu đồng/tháng', 'Công nghệ AI nóng nhất hiện tại (ChatGPT, LLM)',          '7480101'),
('NN10', 'Kỹ sư thị giác máy tính (Computer Vision)',       '25 – 65 triệu đồng/tháng', 'Ứng dụng rộng trong camera giám sát, xe tự lái, y tế',   '7480101'),

-- Ngành Kỹ thuật phần mềm (7480202)
('NN11', 'Kỹ sư kiểm thử phần mềm (QA/QC Engineer)',        '12 – 30 triệu đồng/tháng', 'Nhu cầu ổn định, đặc biệt kiểm thử tự động (Selenium)',   '7480202'),
('NN12', 'Scrum Master / Agile Coach',                       '25 – 60 triệu đồng/tháng', 'Nhu cầu tăng mạnh tại công ty công nghệ quy mô lớn',      '7480202'),
('NN13', 'Kỹ sư Full-stack (React + Spring Boot)',           '20 – 50 triệu đồng/tháng', 'Nhu cầu rất cao tại các startup và công ty outsourcing',   '7480202'),

-- Ngành Hệ thống thông tin (7480104)
('NN14', 'Tư vấn triển khai ERP (SAP / Oracle)',             '20 – 50 triệu đồng/tháng', 'Nhu cầu cao trong chuyển đổi số doanh nghiệp',            '7480104'),
('NN15', 'Phân tích kinh doanh (Business Analyst – BA)',     '15 – 35 triệu đồng/tháng', 'Nhu cầu cao, cầu nối giữa khách hàng và lập trình viên',  '7480104'),

-- Ngành Điện – Điện tử (7510301)
('NN16', 'Kỹ sư điện công trình (Điện dân dụng / công nghiệp)', '12 – 28 triệu đồng/tháng', 'Nhu cầu ổn định trong xây dựng hạ tầng',             '7510301'),
('NN17', 'Kỹ sư hệ thống điện mặt trời (Solar Engineer)',   '15 – 35 triệu đồng/tháng', 'Tăng trưởng mạnh mẽ theo xu hướng năng lượng xanh',       '7510301'),
('NN18', 'Kỹ sư PLC / SCADA',                               '15 – 32 triệu đồng/tháng', 'Nhu cầu cao trong khu công nghiệp FDI',                   '7510301'),

-- Ngành Tự động hóa (7520203)
('NN19', 'Kỹ sư lập trình robot công nghiệp',               '18 – 45 triệu đồng/tháng', 'Rất khan hiếm, tăng trưởng mạnh theo Industry 4.0',       '7520203'),
('NN20', 'Kỹ sư tự động hóa nhà máy thông minh (Smart Factory)', '18 – 40 triệu đồng/tháng', 'Xu hướng số hóa sản xuất bùng nổ',               '7520203'),
('NN21', 'Kỹ sư IoT công nghiệp (IIoT Engineer)',            '20 – 50 triệu đồng/tháng', 'Công nghệ kết nối máy móc – nền tảng Industry 4.0',       '7520203'),

-- Ngành Cơ điện tử (7520207)
('NN22', 'Kỹ sư thiết kế robot (Robotics Engineer)',        '20 – 55 triệu đồng/tháng', 'Rất khan hiếm, cơ hội làm việc tại Nhật Bản, Hàn Quốc',  '7520207'),
('NN23', 'Kỹ sư lập trình CNC',                             '15 – 30 triệu đồng/tháng', 'Nhu cầu ổn định trong ngành cơ khí chính xác',            '7520207'),

-- Ngành Cơ khí (7520116)
('NN24', 'Kỹ sư thiết kế cơ khí CAD/CAM (SolidWorks/AutoCAD)', '12 – 28 triệu đồng/tháng', 'Nhu cầu ổn định trong ngành sản xuất công nghiệp',   '7520116'),
('NN25', 'Kỹ sư công nghệ chế tạo máy',                     '13 – 25 triệu đồng/tháng', 'Nhu cầu cao tại các khu công nghiệp Bắc Ninh, Hà Nam',    '7520116'),
('NN26', 'Chuyên viên giám sát chất lượng sản phẩm (QC)',   '12 – 22 triệu đồng/tháng', 'Nhu cầu ổn định, đặc biệt trong doanh nghiệp FDI',        '7520116'),

-- Ngành Kỹ thuật nhiệt (7520103)
('NN27', 'Kỹ sư HVAC (Điều hòa không khí công trình)',      '14 – 30 triệu đồng/tháng', 'Nhu cầu ổn định trong thi công công trình dân dụng',      '7520103'),
('NN28', 'Kỹ sư vận hành nhà máy điện (Nhiệt điện / Điện gió)', '16 – 38 triệu đồng/tháng', 'Nhu cầu tăng mạnh với năng lượng tái tạo',          '7520103'),

-- Ngành Quản trị kinh doanh (7340101)
('NN29', 'Chuyên viên phân tích kinh doanh (Business Analyst)', '10 – 25 triệu đồng/tháng', 'Nhu cầu cao tại doanh nghiệp FDI và tập đoàn lớn',  '7340101'),
('NN30', 'Chuyên viên Marketing số (Digital Marketing)',     '10 – 28 triệu đồng/tháng', 'Tăng mạnh theo bùng nổ thương mại điện tử',               '7340101'),
('NN31', 'Chuyên viên quản lý chuỗi cung ứng (Supply Chain)', '14 – 32 triệu đồng/tháng', 'Nhu cầu rất cao trong logistics và sản xuất',          '7340101'),

-- Ngành Kế toán (7340301)
('NN32', 'Kế toán viên doanh nghiệp',                        '9 – 18 triệu đồng/tháng',  'Nhu cầu ổn định tại mọi loại hình doanh nghiệp',          '7340301'),
('NN33', 'Kiểm toán viên (ACCA / CPA)',                      '15 – 45 triệu đồng/tháng', 'Lương rất cao với chứng chỉ quốc tế ACCA, CPA',           '7340301'),
('NN34', 'Chuyên viên tư vấn thuế',                          '12 – 30 triệu đồng/tháng', 'Nhu cầu ổn định tại công ty kiểm toán Big4',              '7340301'),

-- Ngành Ngôn ngữ Anh (7220201)
('NN35', 'Phiên dịch / Biên dịch kỹ thuật – kinh tế',       '12 – 35 triệu đồng/tháng', 'Nhu cầu cao trong doanh nghiệp FDI có kỹ sư nước ngoài',  '7220201');

-- ============================================================
--  DỮ LIỆU: YÊU CẦU ĐẦU VÀO
-- ============================================================

INSERT IGNORE INTO YeuCauDauVao VALUES
-- CNTT
('YC001', 'A00 (Toán, Lý, Hóa)',   '22.0', '7480201'),
('YC002', 'A01 (Toán, Lý, Anh)',   '23.5', '7480201'),
('YC003', 'D01 (Toán, Văn, Anh)',  '21.0', '7480201'),
-- Khoa học máy tính
('YC004', 'A00 (Toán, Lý, Hóa)',   '24.0', '7480101'),
('YC005', 'A01 (Toán, Lý, Anh)',   '24.5', '7480101'),
-- Kỹ thuật phần mềm
('YC006', 'A00 (Toán, Lý, Hóa)',   '22.5', '7480202'),
('YC007', 'A01 (Toán, Lý, Anh)',   '23.0', '7480202'),
('YC008', 'D01 (Toán, Văn, Anh)',  '21.5', '7480202'),
-- Hệ thống thông tin
('YC009', 'A00 (Toán, Lý, Hóa)',   '21.5', '7480104'),
('YC010', 'A01 (Toán, Lý, Anh)',   '22.0', '7480104'),
-- Điện, điện tử
('YC011', 'A00 (Toán, Lý, Hóa)',   '19.5', '7510301'),
('YC012', 'A01 (Toán, Lý, Anh)',   '20.0', '7510301'),
-- Tự động hóa
('YC013', 'A00 (Toán, Lý, Hóa)',   '21.0', '7520203'),
('YC014', 'A01 (Toán, Lý, Anh)',   '21.5', '7520203'),
-- Cơ điện tử
('YC015', 'A00 (Toán, Lý, Hóa)',   '20.5', '7520207'),
('YC016', 'A01 (Toán, Lý, Anh)',   '21.0', '7520207'),
-- Cơ khí
('YC017', 'A00 (Toán, Lý, Hóa)',   '18.5', '7520116'),
('YC018', 'A01 (Toán, Lý, Anh)',   '19.0', '7520116'),
-- Kỹ thuật nhiệt
('YC019', 'A00 (Toán, Lý, Hóa)',   '18.0', '7520103'),
('YC020', 'A01 (Toán, Lý, Anh)',   '18.5', '7520103'),
-- Quản trị kinh doanh
('YC021', 'D01 (Toán, Văn, Anh)',  '20.0', '7340101'),
('YC022', 'A00 (Toán, Lý, Hóa)',   '19.5', '7340101'),
-- Kế toán
('YC023', 'D01 (Toán, Văn, Anh)',  '20.5', '7340301'),
('YC024', 'A00 (Toán, Lý, Hóa)',   '19.0', '7340301'),
-- Ngôn ngữ Anh
('YC025', 'D01 (Toán, Văn, Anh)',  '23.0', '7220201'),
('YC026', 'D15 (Toán, Văn, KHTN)', '22.5', '7220201'),
-- Công nghệ vật liệu
('YC027', 'A00 (Toán, Lý, Hóa)',   '17.5', '7540101'),
('YC028', 'B00 (Toán, Hóa, Sinh)', '17.0', '7540101');

-- ============================================================
--  DỮ LIỆU: CHỈ TIÊU TUYỂN SINH (2022 – 2024)
-- ============================================================

INSERT IGNORE INTO ChiTieu VALUES
-- CNTT – 2024
('CT001', '2024', '350', 'Xét tuyển điểm thi THPT',        '7480201'),
('CT002', '2024', '100', 'Xét tuyển học bạ THPT',           '7480201'),
('CT003', '2024',  '80', 'Xét tuyển kỳ thi đánh giá năng lực (ĐGNL ĐHQG HN)', '7480201'),
-- KHMT – 2024
('CT004', '2024', '200', 'Xét tuyển điểm thi THPT',        '7480101'),
('CT005', '2024',  '50', 'Xét tuyển học bạ THPT',           '7480101'),
-- KT phần mềm – 2024
('CT006', '2024', '250', 'Xét tuyển điểm thi THPT',        '7480202'),
('CT007', '2024',  '80', 'Xét tuyển học bạ THPT',           '7480202'),
-- HTTT – 2024
('CT008', '2024', '200', 'Xét tuyển điểm thi THPT',        '7480104'),
('CT009', '2024',  '60', 'Xét tuyển học bạ THPT',           '7480104'),
-- Điện – điện tử – 2024
('CT010', '2024', '300', 'Xét tuyển điểm thi THPT',        '7510301'),
('CT011', '2024', '100', 'Xét tuyển học bạ THPT',           '7510301'),
-- Tự động hóa – 2024
('CT012', '2024', '250', 'Xét tuyển điểm thi THPT',        '7520203'),
-- Cơ điện tử – 2024
('CT013', '2024', '200', 'Xét tuyển điểm thi THPT',        '7520207'),
-- Cơ khí – 2024
('CT014', '2024', '250', 'Xét tuyển điểm thi THPT',        '7520116'),
('CT015', '2024', '100', 'Xét tuyển học bạ THPT',           '7520116'),
-- Kỹ thuật nhiệt – 2024
('CT016', '2024', '150', 'Xét tuyển điểm thi THPT',        '7520103'),
-- QTKD – 2024
('CT017', '2024', '250', 'Xét tuyển điểm thi THPT',        '7340101'),
('CT018', '2024', '100', 'Xét tuyển học bạ THPT',           '7340101'),
-- Kế toán – 2024
('CT019', '2024', '300', 'Xét tuyển điểm thi THPT',        '7340301'),
('CT020', '2024', '100', 'Xét tuyển học bạ THPT',           '7340301'),
-- Ngôn ngữ Anh – 2024
('CT021', '2024', '150', 'Xét tuyển điểm thi THPT',        '7220201'),
-- Vật liệu – 2024
('CT022', '2024', '100', 'Xét tuyển điểm thi THPT',        '7540101'),

-- Dữ liệu lịch sử 2023
('CT023', '2023', '320', 'Xét tuyển điểm thi THPT',        '7480201'),
('CT024', '2023', '180', 'Xét tuyển điểm thi THPT',        '7480101'),
('CT025', '2023', '280', 'Xét tuyển điểm thi THPT',        '7510301'),
('CT026', '2023', '240', 'Xét tuyển điểm thi THPT',        '7520116'),
('CT027', '2023', '230', 'Xét tuyển điểm thi THPT',        '7340101'),
('CT028', '2023', '280', 'Xét tuyển điểm thi THPT',        '7340301'),

-- Dữ liệu lịch sử 2022
('CT029', '2022', '300', 'Xét tuyển điểm thi THPT',        '7480201'),
('CT030', '2022', '150', 'Xét tuyển điểm thi THPT',        '7480101'),
('CT031', '2022', '260', 'Xét tuyển điểm thi THPT',        '7510301');

-- ============================================================
--  DỮ LIỆU: TÍN CHỈ
-- ============================================================

INSERT IGNORE INTO TinChi VALUES
('TC001', '385.000 đồng/tín chỉ',            '7480201'),
('TC002', '450.000 đồng/tín chỉ (CLC)',       '7480201'),
('TC003', '385.000 đồng/tín chỉ',            '7480101'),
('TC004', '450.000 đồng/tín chỉ (CLC)',       '7480101'),
('TC005', '385.000 đồng/tín chỉ',            '7480202'),
('TC006', '385.000 đồng/tín chỉ',            '7480104'),
('TC007', '355.000 đồng/tín chỉ',            '7510301'),
('TC008', '355.000 đồng/tín chỉ',            '7520203'),
('TC009', '355.000 đồng/tín chỉ',            '7520207'),
('TC010', '355.000 đồng/tín chỉ',            '7520116'),
('TC011', '340.000 đồng/tín chỉ',            '7520103'),
('TC012', '340.000 đồng/tín chỉ',            '7340101'),
('TC013', '340.000 đồng/tín chỉ',            '7340301'),
('TC014', '360.000 đồng/tín chỉ',            '7220201'),
('TC015', '340.000 đồng/tín chỉ',            '7540101');

-- ============================================================
--  DỮ LIỆU: HỌC PHÍ
-- ============================================================

INSERT IGNORE INTO HocPhi VALUES
('HP001', '14.000.000 – 16.000.000 đồng/năm',  '2024-2025', 'CTH001'),
('HP002', '22.000.000 – 28.000.000 đồng/năm',  '2024-2025', 'CTH002'),
('HP003', '30.000.000 – 36.000.000 đồng/năm',  '2024-2025', 'CTH003'),
('HP004', '13.000.000 – 15.000.000 đồng/năm',  '2023-2024', 'CTH001'),
('HP005', '20.000.000 – 26.000.000 đồng/năm',  '2023-2024', 'CTH002'),
('HP006', '28.000.000 – 33.000.000 đồng/năm',  '2023-2024', 'CTH003'),
('HP007', '12.000.000 – 14.000.000 đồng/năm',  '2022-2023', 'CTH001');

-- ============================================================
--  DỮ LIỆU: HỌC BỔNG
-- ============================================================

INSERT IGNORE INTO HocBong VALUES
('HB001', 'Học bổng Khuyến khích học tập loại Xuất sắc (100% học phí)',    '3.7 / 4.0',                          'Tốt',              'CTH001'),
('HB002', 'Học bổng Khuyến khích học tập loại Giỏi (50% học phí)',         '3.5 / 4.0',                          'Tốt',              'CTH001'),
('HB003', 'Học bổng Khuyến khích học tập loại Khá (25% học phí)',          '3.2 / 4.0',                          'Khá trở lên',      'CTH001'),
('HB004', 'Học bổng Tân sinh viên xuất sắc đầu vào (5 triệu đồng/sinh viên)', 'Điểm thi THPT từ 27,0 trở lên', 'Tốt',              'CTH001'),
('HB005', 'Học bổng Chính phủ (dành cho sinh viên dân tộc thiểu số)',      'GPA từ 2.5 trở lên',                 'Trung bình khá',   'CTH001'),
('HB006', 'Học bổng Vượt khó học giỏi (dành cho hoàn cảnh khó khăn)',     'GPA từ 3.0 trở lên',                 'Khá trở lên',      'CTH001'),
('HB007', 'Học bổng Doanh nghiệp Samsung – Kỹ thuật (15 triệu/sinh viên)', '3.4 / 4.0',                         'Tốt',              'CTH002'),
('HB008', 'Học bổng FPT Software – Đối tác tài năng (20 triệu/sinh viên)', '3.5 / 4.0',                         'Tốt',              'CTH002'),
('HB009', 'Học bổng Viettel – Nhân tài số (25 triệu/sinh viên + thực tập)', '3.6 / 4.0',                        'Tốt',              'CTH002'),
('HB010', 'Học bổng Sunflower – Hỗ trợ nữ sinh viên kỹ thuật',            'GPA từ 3.2 trở lên',                 'Tốt',              'CTH001'),
('HB011', 'Học bổng ACCA – Sinh viên ngành Kế toán xuất sắc',             'GPA từ 3.4 trở lên',                 'Tốt',              'CTH001'),
('HB012', 'Học bổng Nghiên cứu khoa học HaUI (5 – 20 triệu)',             'Có đề tài NCKH được duyệt',           'Không yêu cầu',    'CTH001');

-- ============================================================
--  DỮ LIỆU: QUY TRÌNH NHẬP HỌC
-- ============================================================

INSERT IGNORE INTO QuyTrinhNhapHoc VALUES
('QT001',
 'Bước 1 – Đăng ký xét tuyển: Thí sinh nộp hồ sơ trực tuyến qua Cổng thông tin tuyển sinh tại địa chỉ tuyensinh.haui.edu.vn trong thời gian quy định (thường từ 01/07 đến 31/08 hàng năm). Thí sinh điền đầy đủ thông tin cá nhân, lựa chọn nguyện vọng và tải lên các tài liệu theo yêu cầu.',
 'DCN'),
('QT002',
 'Bước 2 – Kiểm tra điều kiện: Nhà trường xét duyệt hồ sơ, kiểm tra điều kiện đầu vào đối chiếu với điểm thi THPT quốc gia hoặc học bạ. Danh sách trúng tuyển được công bố chính thức trên website và gửi thông báo qua email/SMS đến thí sinh.',
 'DCN'),
('QT003',
 'Bước 3 – Xác nhận nhập học: Sinh viên trúng tuyển xác nhận nhập học trực tuyến trên hệ thống và hoàn thành thanh toán học phí học kỳ đầu tiên trong thời hạn quy định (thường 5 ngày làm việc sau khi có kết quả).',
 'DCN'),
('QT004',
 'Bước 4 – Chuẩn bị hồ sơ gốc: Sinh viên chuẩn bị đầy đủ bộ hồ sơ gốc gồm: (1) Bản sao công chứng Bằng tốt nghiệp THPT; (2) Học bạ THPT (bản gốc hoặc công chứng); (3) Căn cước công dân (CCCD); (4) Ảnh thẻ 3×4 (06 ảnh nền trắng); (5) Giấy khai sinh bản sao; (6) Giấy khám sức khỏe theo mẫu Bộ Y tế (issued trong vòng 6 tháng).',
 'DCN'),
('QT005',
 'Bước 5 – Nộp hồ sơ tại trường: Sinh viên đến nộp hồ sơ gốc trực tiếp tại Phòng Đào tạo – A103, Cơ sở 298 Cầu Diễn, Bắc Từ Liêm, Hà Nội. Sau khi hoàn tất, sinh viên nhận Thẻ sinh viên tạm thời và lịch học khai giảng.',
 'DCN'),
('QT006',
 'Bước 6 – Tuần sinh hoạt đầu khóa: Tham gia Tuần sinh hoạt công dân – sinh viên (khoảng 1 tuần đầu khóa học) để làm quen môi trường đại học, học nội quy – quy chế, và nhận thông tin về các câu lạc bộ, học bổng.',
 'DCN');

-- ============================================================
--  DỮ LIỆU: TÀI KHOẢN & NGƯỜI DÙNG
-- ============================================================

INSERT IGNORE INTO TaiKhoan VALUES
    ('TK001', 'admin',       'P@ssw0rd123',  'ADMIN'),
    ('TK002', 'sinhvien01',  'sv123456',     'USER'),
    ('TK003', 'sinhvien02',  'sv654321',     'USER'),
    ('TK004', 'sinhvien03',  'sv112233',     'USER'),
    ('TK005', 'sinhvien04',  'sv445566',     'USER');

INSERT IGNORE INTO NguoiDung VALUES
    ('ND001', 'Quản trị viên HaUI',    'default_admin.png',  'admin@haui.edu.vn',                    'Quản trị hệ thống', 'TK001'),
    ('ND002', 'Nguyễn Văn An',         'default_sv.png',     'nva2025@student.haui.edu.vn',          'Sinh viên',         'TK002'),
    ('ND003', 'Trần Thị Bình',         'default_sv.png',     'ttb2025@student.haui.edu.vn',          'Sinh viên',         'TK003'),
    ('ND004', 'Lê Hoàng Nam',          'default_sv.png',     'lhn2025@student.haui.edu.vn',          'Sinh viên',         'TK004'),
    ('ND005', 'Phạm Thị Khánh Linh',   'default_sv.png',     'ptkl2025@student.haui.edu.vn',         'Sinh viên',         'TK005');

-- ============================================================
--  DỮ LIỆU: TIN TỨC (10 bài)
-- ============================================================

INSERT IGNORE INTO TinTuc VALUES
('TT001',
 'Thông báo tuyển sinh đại học chính quy năm 2024 – HaUI',
 'https://haui.edu.vn/img/ts2024.jpg',
 'Trường Đại học Công nghiệp Hà Nội thông báo kế hoạch tuyển sinh đại học chính quy năm 2024. Tổng chỉ tiêu 6.500 sinh viên với 52 ngành đào tạo. Thời gian đăng ký: 01/07 đến 31/08/2024. Thí sinh đăng ký qua Cổng tuyển sinh quốc gia (thisinh.thitotnghiepthpt.edu.vn) và cổng riêng của trường tại tuyensinh.haui.edu.vn.',
 'HaUI thông báo tuyển sinh 6.500 chỉ tiêu với 52 ngành đào tạo năm 2024.',
 'DCN'),

('TT002',
 'Điểm chuẩn trúng tuyển đại học chính quy 2023 – HaUI chính thức công bố',
 'https://haui.edu.vn/img/dc2023.jpg',
 'HaUI công bố điểm chuẩn trúng tuyển 2023: Công nghệ thông tin 24,5 điểm (A01) – tăng 1,0 điểm so với 2022; Khoa học máy tính 25,0 điểm (A01); Kỹ thuật phần mềm 24,0 điểm (A01); Kỹ thuật điện 22,0 điểm (A00); Quản trị kinh doanh 22,5 điểm (D01); Kế toán 22,0 điểm (D01). Điểm chuẩn tăng đồng loạt ở các ngành kỹ thuật – công nghệ phản ánh sức hút ngày càng tăng.',
 'Công bố điểm chuẩn tuyển sinh 2023 – CNTT 24,5; KHMT 25,0 điểm.',
 'DCN'),

('TT003',
 'HaUI tổ chức Ngày hội tư vấn tuyển sinh mở 2024 – Miễn phí cho học sinh THPT cả nước',
 'https://haui.edu.vn/img/ngayhoi2024.jpg',
 'Ngày 15/03/2024, HaUI tổ chức Ngày hội tư vấn tuyển sinh mở lần thứ 12 tại Cơ sở 298 Cầu Diễn. Hơn 3.000 học sinh THPT từ 25 tỉnh thành tham dự. Các em được gặp gỡ trực tiếp giảng viên, tham quan phòng lab thực hành hiện đại, trải nghiệm demo AI – Robot – IoT và nhận tư vấn 1-1 về ngành học, học bổng và cơ hội việc làm.',
 'HaUI tổ chức ngày hội tư vấn tuyển sinh lần thứ 12 ngày 15/03/2024 cho THPT cả nước.',
 'DCN'),

('TT004',
 'Học bổng Samsung 2024 – 50 suất dành cho sinh viên ngành Kỹ thuật HaUI',
 'https://haui.edu.vn/img/samsung2024.jpg',
 'Tập đoàn Samsung Electronics Việt Nam phối hợp với HaUI trao học bổng năm 2024. 50 sinh viên xuất sắc nhất ngành Kỹ thuật – Công nghệ nhận học bổng trị giá 15 triệu đồng/sinh viên, kèm cơ hội thực tập hưởng lương tại Samsung Bắc Ninh và Samsung HCMC. Điều kiện: GPA từ 3,4/4,0; xếp loại Tốt. Hạn nộp hồ sơ: 30/09/2024.',
 'Samsung trao 50 học bổng 15 triệu đồng + thực tập tại Samsung cho sinh viên HaUI ngành kỹ thuật.',
 'DCN'),

('TT005',
 'HaUI đạt chuẩn kiểm định chất lượng AUN-QA 2024 cho 5 chương trình đào tạo',
 'https://haui.edu.vn/img/aunqa2024.jpg',
 'Trường Đại học Công nghiệp Hà Nội vừa được công nhận đạt chuẩn kiểm định chất lượng khu vực AUN-QA (ASEAN University Network – Quality Assurance) năm 2024 cho 5 chương trình đào tạo trọng điểm: Công nghệ thông tin, Kỹ thuật điện – điện tử, Công nghệ kỹ thuật cơ khí, Quản trị kinh doanh và Kế toán. Đây là minh chứng rõ ràng cho cam kết chất lượng đào tạo ngang tầm khu vực ASEAN.',
 'HaUI đạt chuẩn AUN-QA 2024 cho 5 chương trình đào tạo trọng điểm – ngang tầm khu vực ASEAN.',
 'DCN'),

('TT006',
 'HaUI ký kết hợp tác đào tạo với FPT Software và Viettel Technology 2024',
 'https://haui.edu.vn/img/hopTacFPT_Viettel.jpg',
 'Ngày 20/04/2024, Trường Đại học Công nghiệp Hà Nội ký kết biên bản hợp tác chiến lược với FPT Software và Viettel Technology. Theo thỏa thuận, cả hai doanh nghiệp sẽ đồng hành đào tạo kỹ năng thực tế, cử chuyên gia sang giảng dạy trực tiếp, tài trợ thiết bị phòng lab và ưu tiên tuyển dụng sinh viên HaUI. Đây là bước tiến quan trọng, rút ngắn khoảng cách giữa đào tạo và thực tiễn.',
 'HaUI ký kết hợp tác chiến lược với FPT Software và Viettel Technology – đào tạo gắn với doanh nghiệp.',
 'DCN'),

('TT007',
 'Kết quả kỳ thi Olympic Tin học sinh viên toàn quốc 2024 – HaUI giành 3 Huy chương Vàng',
 'https://haui.edu.vn/img/olympic2024.jpg',
 'Đoàn sinh viên HaUI tham dự kỳ thi Olympic Tin học sinh viên toàn quốc 2024 tại Đại học Bách khoa Hà Nội. Kết quả xuất sắc: 3 Huy chương Vàng (lập trình ACM/ICPC), 2 Huy chương Bạc và 1 Huy chương Đồng. Đây là thành tích cao nhất của HaUI trong 5 năm qua, khẳng định chất lượng đào tạo ngành CNTT ngày càng nâng cao.',
 'HaUI giành 3 Huy chương Vàng Olympic Tin học sinh viên toàn quốc 2024 – thành tích cao nhất 5 năm.',
 'DCN'),

('TT008',
 'Mở ngành đào tạo mới – Trí tuệ nhân tạo (AI) bậc đại học từ năm 2025',
 'https://haui.edu.vn/img/nganhAI2025.jpg',
 'Trường Đại học Công nghiệp Hà Nội chính thức được Bộ Giáo dục và Đào tạo phê duyệt mở ngành Trí tuệ nhân tạo (mã 7480107) từ năm tuyển sinh 2025. Chương trình được thiết kế bởi các chuyên gia AI hàng đầu, tích hợp kiến thức về học máy, học sâu, xử lý ngôn ngữ tự nhiên, thị giác máy tính và AI đạo đức. Chỉ tiêu dự kiến: 150 sinh viên/khóa.',
 'HaUI mở ngành Trí tuệ nhân tạo (AI) từ năm 2025 – chỉ tiêu 150 sinh viên/khóa.',
 'DCN'),

('TT009',
 'Chương trình thực tập doanh nghiệp Hè 2024 – 500 vị trí tại 80 công ty đối tác',
 'https://haui.edu.vn/img/thucTap2024.jpg',
 'Phòng Hợp tác Doanh nghiệp HaUI phối hợp với 80 công ty đối tác công bố 500 vị trí thực tập hè 2024 dành cho sinh viên năm 3 và năm 4. Các đơn vị tiêu biểu: FPT Software, Viettel, Samsung, VNPT, Bosch Việt Nam, Hanwha Life, Aeon Vietnam, KPMG, Deloitte. Sinh viên được hưởng phụ cấp thực tập từ 3 – 12 triệu đồng/tháng tùy vị trí. Đăng ký qua portal.haui.edu.vn.',
 'HaUI kết nối 500 vị trí thực tập hè 2024 tại 80 công ty đối tác hàng đầu – phụ cấp tới 12 triệu đồng.',
 'DCN'),

('TT010',
 'HaUI xếp hạng Top 10 đại học uy tín nhất Việt Nam theo bảng xếp hạng VNUR 2024',
 'https://haui.edu.vn/img/vnur2024.jpg',
 'Theo bảng xếp hạng Đại học Việt Nam (VNUR) năm 2024, Trường Đại học Công nghiệp Hà Nội xuất sắc lọt vào Top 10 đại học uy tín nhất cả nước, xếp vị trí đặc biệt trong nhóm đại học kỹ thuật – công nghệ. Các tiêu chí được đánh giá cao bao gồm: tỉ lệ việc làm sau tốt nghiệp (97,5%), chỉ số nghiên cứu khoa học, mức độ hợp tác doanh nghiệp và chất lượng cơ sở vật chất.',
 'HaUI lọt Top 10 đại học uy tín nhất Việt Nam theo bảng xếp hạng VNUR 2024 – tỉ lệ việc làm 97,5%.',
 'DCN');

-- ============================================================
--  DỮ LIỆU: KHÓA HỌC (ngành × chương trình)
-- ============================================================

INSERT IGNORE INTO KhoaHoc (ChuongTrinhHocid, NganhHocmaNganh) VALUES
    ('CTH001', '7480201'), ('CTH002', '7480201'),            -- CNTT: Đại trà + CLC
    ('CTH001', '7480101'), ('CTH002', '7480101'),            -- KHMT: Đại trà + CLC
    ('CTH001', '7480202'), ('CTH002', '7480202'),            -- KT PM: Đại trà + CLC
    ('CTH001', '7480104'),                                   -- HTTT: Đại trà
    ('CTH001', '7510301'), ('CTH002', '7510301'),            -- Điện ĐT: Đại trà + CLC
    ('CTH001', '7520203'),                                   -- Tự động hóa
    ('CTH001', '7520207'),                                   -- Cơ điện tử
    ('CTH001', '7520116'), ('CTH002', '7520116'),            -- Cơ khí: Đại trà + CLC
    ('CTH001', '7520103'),                                   -- Kỹ thuật nhiệt
    ('CTH001', '7340101'), ('CTH002', '7340101'),            -- QTKD: Đại trà + CLC
    ('CTH001', '7340301'),                                   -- Kế toán
    ('CTH001', '7220201'),                                   -- Ngôn ngữ Anh
    ('CTH001', '7540101');                                   -- Công nghệ vật liệu

-- ============================================================
--  KIỂM TRA KẾT QUẢ NẠP DỮ LIỆU
-- ============================================================

SELECT 'DỮ LIỆU HaUI NẠP THÀNH CÔNG! 🎉' AS KetQua,
       COUNT(*) AS SoNganh
FROM NganhHoc;