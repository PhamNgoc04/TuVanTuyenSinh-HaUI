-- FILE SỬA LỖI THEO ĐÚNG CẤU TRÚC DATABASE CỦA BẠN (hau_tuyensinh)

-- DỮ LIỆU TEST CHO NGÀNH: CÔNG NGHỆ THÔNG TIN (Mã: 7480201)
-- 1. Thêm nghề nghiệp
INSERT INTO nganhnghe (maNghe, tenNghe, mucLuong, tinhTrang, NganhHocmaNganh) VALUES
('NN_01', 'Kỹ sư Kỹ thuật phần mềm', '15 - 30 Triệu VNĐ', 'Nhu cầu cao', '7480201'),
('NN_02', 'Chuyên viên phân tích dữ liệu', '12 - 25 Triệu VNĐ', 'Thiếu hụt cục bộ', '7480201');

-- 2. Thêm chỉ tiêu tuyển sinh
INSERT INTO chitieu (maChiTieu, nam, soLuong, phuongThuc, NganhHocmaNganh) VALUES
('CT_01', '2024', '650', 'Thi Tốt nghiệp THPT', '7480201'),
('CT_02', '2024', '150', 'Tuyển thẳng/IELTS', '7480201');

-- 3. Thêm yêu cầu đầu vào
INSERT INTO yeucaudauvao (id, khoi, diemTong, NganhHocmaNganh) VALUES
('YC_01', 'A00 (Toán, Lý, Hóa)', '25.40', '7480201'),
('YC_02', 'A01 (Toán, Lý, Anh)', '24.85', '7480201');

-- 4. Thêm tín chỉ & học phí
INSERT INTO tinchi (id, giaTien, NganhHocmaNganh) VALUES
('TC_01', '350.000 VNĐ / Tín chỉ', '7480201');

-- =========================================================================

-- DỮ LIỆU TEST CHO NGÀNH: CÔNG NGHỆ KỸ THUẬT Ô TÔ (Mã: 7510205)
-- 1. Thêm nghề nghiệp
INSERT INTO nganhnghe (maNghe, tenNghe, mucLuong, tinhTrang, NganhHocmaNganh) VALUES
('NN_03', 'Kỹ sư thi công Ô tô điện', '18 - 40 Triệu VNĐ', 'Ngành hot đón đầu', '7510205'),
('NN_04', 'Kiểm định tự động hóa', '14 - 20 Triệu VNĐ', 'Ổn định 100%', '7510205');

-- 2. Thêm chỉ tiêu tuyển sinh
INSERT INTO chitieu (maChiTieu, nam, soLuong, phuongThuc, NganhHocmaNganh) VALUES
('CT_03', '2024', '800', 'Thi Tốt nghiệp THPT', '7510205');

-- 3. Thêm yêu cầu đầu vào
INSERT INTO yeucaudauvao (id, khoi, diemTong, NganhHocmaNganh) VALUES
('YC_03', 'A00 (Toán, Lý, Hóa)', '25.10', '7510205');

-- 4. Thêm tín chỉ & học phí
INSERT INTO tinchi (id, giaTien, NganhHocmaNganh) VALUES
('TC_02', '350.000 VNĐ / Tín chỉ', '7510205');
