-- UPDATE 30/4/2024 (16:20)
--use quanlikhohang
-- drop database QL_VanPhongPham
-- CREATE DATABASE QL_VanPhongPham
USE QL_VanPhongPham

-- Tạo bảng NhaCungCap
CREATE TABLE NhaCungCap (
  MaNCC INT PRIMARY KEY IDENTITY(1,1),
  TenNCC NVARCHAR(255) NOT NULL,
  DiaChi NVARCHAR(255),
  Email VARCHAR(255) NOT NULL,
  SDT VARCHAR(20) NOT NULL,
  TrangThai INT
);

-- Tạo bảng LoaiSanPham
CREATE TABLE LoaiSanPham (
  MaLoai INT PRIMARY KEY IDENTITY(1,1),
  TenLoai NVARCHAR(255) NOT NULL,
  TrangThai INT
);

-- Tạo bảng SanPham
CREATE TABLE SanPham (
  MaSP INT PRIMARY KEY IDENTITY(1,1),
  TenSP NVARCHAR(255) NOT NULL,
  XuatXu NVARCHAR(255),
  HSD DATE,
  GiaNhap DECIMAL(10, 2),
  GiaXuat DECIMAL(10, 2),
  SoLuong INT,
  HinhAnh VARCHAR(255),
  DonViTinh NVARCHAR(50),
  MaLoai INT NOT NULL,
  MaNCC INT NOT NULL,
  TrangThai INT,
  FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai),
  FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC)
);

-- Tạo bảng KhachHang
CREATE TABLE KhachHang (
  MaKH INT PRIMARY KEY IDENTITY(1,1),
  TenKH NVARCHAR(255) NOT NULL,
  DiaChi NVARCHAR(255),
  SDT VARCHAR(20) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  --Password VARCHAR(255) NOT NULL,
  TrangThai INT
);

-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
  MaNV INT PRIMARY KEY IDENTITY(1,1),
  TenNV NVARCHAR(255) NOT NULL,
  GioiTinh NVARCHAR(10),
  NgaySinh DATE,
  SDT VARCHAR(20),
  Email VARCHAR(255),
  Password VARCHAR(255) NOT NULL,
  TrangThai INT,
  QuyenTruyCap NVARCHAR(10)
);

-- Tạo bảng PhieuNhap
CREATE TABLE PhieuNhap (
  MaPN INT PRIMARY KEY IDENTITY(1,1),
  NgayLap DATE,
  ThanhTienPN DECIMAL(10, 2),
  MaNCC INT NOT NULL,
  MaNV INT NOT NULL,
  TrangThai INT NOT NULL,
  FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC),
  FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
);

-- Tạo bảng CTPN
CREATE TABLE CTPN (
  MaPN INT NOT NULL,
  MaSP INT NOT NULL,
  SL INT,
  DonGia DECIMAL(10, 2),
  PRIMARY KEY (MaPN, MaSP),
  FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN),
  FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- Tạo bảng HoaDon
CREATE TABLE HoaDon (
  MaHD INT PRIMARY KEY IDENTITY(1,1),  
  ThanhTienHD DECIMAL(10, 2),
  NgayLapHD DATE,
  MaNV INT NOT NULL,
  MaKH INT NOT NULL,
  TrangThai INT NOT NULL,
  FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
  FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);

-- Tạo bảng CTHD
CREATE TABLE CTHD (
  MaHD INT NOT NULL,
  MaSP INT NOT NULL,
  SL INT,
  DonGia DECIMAL(10, 2),
  PRIMARY KEY (MaHD, MaSP),
  FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
  FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

------------Ràng buộc------------
--NhanVien
alter table NhanVien
add constraint CK_NhanVien_NgaySinh check ((year(getdate()) - year(NgaySinh)) >= 18)

--SanPham
alter table SanPham
add constraint CK_SanPham_GiaBan check (GiaXuat > 0)

--HoaDon
alter table HoaDon
add constraint DF_PhieuXuat_NgayLap default getdate() for NgayLapHD

--CTPN
alter table CTPN
add constraint CK_CTPN_SoLuong check (SL > 0)

--CTHD
alter table CTHD
add constraint CK_CTPX_SoLuong check (SL > 0)

------------Nhập liệu-------------
INSERT INTO LoaiSanPham (TenLoai, TrangThai) VALUES
		(N'BÚT VIẾT' , 1),
		(N'VĂN PHÒNG PHẨM', 1),
		(N'DỤNG CỤ HỌC TẬP', 1),
		(N'MỸ THUẬT', 1),
		(N'GIẤY IN', 1),
		(N'BÚT CAO CẤP', 1),
		(N'SÁCH', 1),
		(N'SẢN PHẨM RẠN ĐÔNG', 1)


INSERT INTO NhaCungCap (TenNCC, DiaChi, Email, SDT, TrangThai) VALUES
		(N'Thiên Long', N'Tân Phú, TP.HCM', 'thienlong@gmail.com', '092992911', 1),
		(N'IK Copy', N'Tân Bình,  TP.HCM', 'ikcopy@gmail.com', '02334555', 1),
		(N'IK Yellow', N'Cầm Giờ,  TP.HCM', 'ikyellow@gmail.com', '02334552', 1),
		(N'Supreme', N'Hội An', 'supreme@gmail.com', '092992912', 1),
		(N'Ford', N'Tây Ninh', 'ford@gmail.com', '092992918', 1),
		(N'Flexoffice', N'Bến Tre', 'flexoffice@gmail.com', '092992913', 1),
		(N'IK Signature', N'Bình Thạnh,  TP.HCM', 'iksignature@gmail.com', '092992914', 1),
		(N'Plus', N'Thủ Đức,  TP.HCM', 'plus@gmail.com', '092992915', 1),
		(N'Paper One', N'Tân Phú, TP.HCM', 'paperone@gmail.com', '092992916', 1),
		(N'IPRINT GREEN', N'Tân Phú, TP.HCM', 'iprintgreen@gmail.com', '092992917', 1);

INSERT INTO SanPham (TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC, TrangThai) VALUES
    --LSP001
	(N'Bút mực gel khô nhanh - nét viết êm mượt Flexgel Thiên Long GEL-042 - Dành cho Văn Phòng Sinh Viên Học sinh', N'Việt Nam', '12-12-2025', 3,  5, 200, 'artboard_4_copy_6a82797db0084f9db27ae1cec9ff01cf.jpg', N'cây', 1, 1, 1),
	(N'Hộp 20 Bút Bi Thiên Long TL-027', N'Việt Nam', '12-12-2025', 80,  100, 2300, '50000069cb20_ca9de1e1a98a477db28cafcc097180c0.jpg', N'Hộp', 1, 2,1),
	(N'Bút chì bấm Thiên Long PC-024', N'Việt Nam', '12-12-2025', 17.2,  19, 300, 'daa_3274e0a355114ad99e2598b6f772419f.jpg', N'cây', 1, 3,1),
	(N'Bút chì mỹ thuật Thiên Long 6B GP-025', N'Việt Nam', '12-12-2025', 7.2,  8, 100, '42f_7c6164f1a0b6422c86b5c76288ef4099.jpg', N'cây', 1, 4,1),
	(N'Bút lông bảng cao cấp Bizner BIZ-WB02', N'Việt Nam', '12-12-2025', 33.2,  37, 210, 'biz-wb02_7e5e0b82a223487db6f7bed46a75c2d7.jpg', N'cây', 1, 5,1),
	(N'Bút bi cao cấp Bizner BIZ-09 - thân màu Pastel', N'Việt Nam', '12-12-2025', 39.2,  60, 290, 'biz-09_7_copy2_d0a905b6ce6446c7949cddbd99d3e980.jpg', N'cây', 1, 6,1),
	(N'Bút chì gỗ cao cấp Bizner BIZ-P03', N'Việt Nam', '12-12-2025', 9,  11, 2, 'j_246c932c75da4fe6aa13e44e2ebfbbde.jpg', N'cây', 1, 7,1),
	(N'Bút chì bấm Neon Thiên Long Colokit PC-C002', N'Việt Nam', '12-12-2025', 5.2,  6, 1000, 'artboard_1_copy_472c9ba38a7c46369b92e74e05840c26.jpg', N'cây', 1, 8,1),
	(N'Bút gel xóa được Thiên Long Điểm 10 TP-GELE002', N'Việt Nam', '12-12-2025', 12.2,  14, 900, 'artboard_3-2_23fcecb216454c9abe2e55490e09ac43.jpg', N'cây', 1, 9,1),
	(N'Bộ ngòi bút máy Iridium Điểm 10 TP-FPN08/KIT', N'Việt Nam', '12-12-2025', 22.5,  25, 122, '423asdhgf_7cad06afdfca4eb08dcf9e15c8c859aa.jpg', N'cây', 1, 10,1),

    --LSP02
    (N'Combo 5 Băng keo đục Flexoffice BKD06/FO - Tặng 02 Cuộn keo đục 60 yards', N'Việt Nam', '02-02-2030', 57.2, 71, 1000, 'scebe268c5e554e33a0a7406fb36fe038e_54f9524971d042fd8abff9e93d4baf59.jpg', N'cái', 2, 1,1),
    (N'Bấm kim số 3 Thiên Long Flexoffice FO-ST005', N'Việt Nam', '02-02-2030', 57, 64, 1000, 'fo-st005_vn_copy_9640a234bf7945af9ac99012855c8fe5.jpg', N'cái', 2, 2,1),
    (N'Bút xóa kéo - Correction Tape Thiên Long - Khô siêu nhanh Viết được ngay sau khi xóa', N'Việt Nam', '02-02-2030', 17, 19, 1000, 'ct-007_ct-008_ct-009_3_ceb349acdd9a480db7776b3ce28e13ee.jpg', N'cái', 2, 3,1),
    (N'Ống cắm bút Thiên Long - Đa dụng tiện lợi Thiết kế trẻ trung', N'Việt Nam', '02-02-2030', 43, 48, 1000, 'ps-001_ps-002_2_836c6c00b2414075ab0cb016d1da8791.jpg', N'cái', 2,4,1),
    (N'Keo khô - Glue Stick Thiên Long G-026/AK - Nhân vật Akooland thế giới học cụ thần kỳ', N'Việt Nam', '02-02-2030', 7, 8, 1000, 'artboard_10_copy_b84501a1090f432c80acf18e020c6a43.jpg', N'cái', 2, 5,1),
    (N'Sổ lò xo đường kẻ ngang A5 Thiên Long', N'Việt Nam', '02-02-2030', 27, 33, 1000, '02_5ac675a7091840dc92c7797ca97f7857.jpg', N'cái', 2, 6,1),
    (N'Máy hút bụi mini Thiên Long Flexio MVE-001 - Không bao gồm pin', N'Việt Nam', '02-02-2030', 150, 176, 1000, 'artboard_6_copy_fcf05a1ae9bc4da8bac714cf33b92c88.jpg', N'cái', 2, 7,1),
    (N'Băng keo - Băng dính kéo 2 mặt Thiên Long GT-001 (Màu ngẫu nhiên)', N'Việt Nam', '02-02-2030', 27, 31, 1000, 'artboard_1_87416f1daf9d4ef3b304b02319663053.jpg', N'cái', 2, 8,1),
    (N'Pin Alkaline AA Thiên Long Flexio', N'Việt Nam', '02-02-2030', 19, 22, 1000, 'artboard_3_copy_2_44a81837e9664be4936148e2c3fba91b.jpg', N'cái', 2, 9,1),
    (N'Sổ lò xo đường kẻ caro A5 Thiên Long MB-015 - Màu ngẫu nhiên', N'Việt Nam', '02-02-2030', 27, 30, 1000, 'artboard_2_8aa64124eb7f4afa832f849cf004d33c.jpg', N'cái', 2, 10,1),
    (N'Bìa học sinh 30 lá A4 Thiên Long DB-002 - Sức chứa 150 tờ A4', N'Việt Nam', '02-02-2030', 45, 50, 1000, 'thumbnail_web_807e62df361745e48d9443d53e699463.jpg', N'cái', 2, 1,1),
    
    --LSP03
    (N'Keo sữa - White Glue Thiên Long G-022 - Độ dính cao', N'Việt Nam', '03-04-2033', 9, 10, 500, 'g-022_3_48c36a6a73b14c3da35a58e360100c0c.jpg', N'cái', 3,1,1),
    (N'Bảng học sinh 2 mặt Thiên Long TP-B09/AK - Nhân vật Akooland thế giới học cụ thần kỳ', N'Việt Nam', '03-04-2033', 47, 56, 500, 'artboard_1_copy_e2532caeb2af48779271de6ad5ff77aa.jpg', N'cái', 3,2,1),
    (N'Thước thẳng 20cm Thiên Long TP-SR011/AK - Nhân vật Akooland thế giới học cụ thần kỳ', N'Việt Nam', '03-04-2033', 4.5, 5.3, 500, 'artboard_10_3f3227c1757944b6921bfac245b75998.jpg', N'cái', 3,3,1),
    (N'Tập học sinh 48 trang 4 ô ly vuông 2.5mm 100gsm Thiên Long TP-NB002/AK - Akooland thế giới học cụ thần kỳ', N'Việt Nam', '03-04-2033', 12, 15, 500, 'tp-nb002_ak_2_305cd65ad6c642e8978db3ed3c6dfa3e.jpg', N'cái', 3,4,1),
    (N'Tập học sinh 96 trang 4 ô ly vuông 2mm 100gsm Thiên Long TP-NB061/AK - Akooland thế giới học cụ thần kỳ', N'Việt Nam', '03-04-2033', 14, 17, 500, 'artboard_1_copy_eb58e910ccb649a59e904ac2436755a0.jpg', N'cái', 3,5,1),
    (N'Lõi - Ruột gôm Thiên Long Flexio EER-009 - Dùng cho gôm điện tự động EE-001', N'Việt Nam', '03-04-2033', 17, 19, 500, 'Lõi - Ruột gôm Thiên Long Flexio EER-009 - Dùng cho gôm điện tự động EE-001.jpg', N'cái', 3,6,1),
    (N'Gôm điện tự động Thiên Long Flexio EE-001 - Không bao gồm pin', N'Việt Nam', '03-04-2033', 62, 90, 500, 'artboard_1_copy_944254baf7d04b25b99ca485ac2e78eb.jpg', N'cái', 3,7,1),
    (N'Bút xóa nước Thiên Long CP-015', N'Việt Nam', '03-04-2033', 7, 9, 500, 'cp-015_5436fc65d2284506bf34740641074c40.jpg', N'cái', 3,8,1),
    (N'Combo 10 Tập học sinh 96 trang Điểm 10 TP-NB053 (hình ngẫu nhiên)', N'Việt Nam', '03-04-2033', 160, 290, 500, 'tp-nb053_copy_bf1dc81d14f049a2b6a65b905f0c0190_38b3adff3dde4cc986f7a7338bddf9a3.jpg', N'cái', 3,9,1),
    (N'Gôm tẩy nhân vật hoạt hình Công chúa Ariel Disney Thiên Long E-033/PR', N'Việt Nam', '03-04-2033', 4.8, 6, 500, 'e-033pr_4_8ae0fceb1f0f48048e14ea98769362b6.jpg', N'cái', 3,10,1),

    --LSP04
    (N'Sáp 12/18/24 màu rửa được - Washable Crayons Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ', N'Việt Nam', '04-04-2043', 24000, 29000, 1200, 'artboard_10_copy_b6cb8bfaa2564674af8b6714a7c13eb4.jpg', N'cái', 4,1,1),
    (N'Hộp 6/12 màu nước rửa được - Washable Poster Color Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ', N'Việt Nam', '04-04-2043', 45000, 53000, 1200, 'artboard_2_copy_2093628c49aa4217888175ed9f183d06.jpg', N'cái', 4,2,1),
    (N'Hộp bút lông 12/ 24 màu rửa được - Washable Brush Fiber Pen Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ', N'Việt Nam', '04-04-2043', 39000, 46000, 1200, 'artboard_1_copy_2c010b76abc7448f8fbc372da5e964a4.jpg', N'cái', 4,3,1),
    (N'Sáp nhựa 12 màu xóa được - Erasable Crayons Thiên Long Colokit - Nhân vật Futy Akooland thế giới học cụ thần kỳ', N'Việt Nam', '04-04-2043', 62000, 75000, 1200, 'artboard_1_copy_f04e8ac91b2049d0874d269ab7ff7fee.jpg', N'cái', 4,4,1),
    (N'Màu nước rửa được - Washable Poster Color Thiên Long Colokit - Rửa sạch bằng nước dễ dàng', N'Việt Nam', '04-04-2043', 59000, 66000, 1200, 'hz8a1956_5c405f084b89408eaf7db3e65ab8cfa2.jpg', N'cái', 4,5,1),
    (N'Bút sáp lụa vặn - Silky Crayons Thiên Long Colokit - Mềm khô nhanh không lem', N'Việt Nam', '04-04-2043', 97000, 110000, 1200, 'img_8184_5dbd8c420ac943328daf252a6ce5b47e.jpg', N'cái', 4,6,1),
    (N'Bộ 6 màu nước Neon Colokit WACO-C011', N'Việt Nam', '04-04-2043', 69000, 116000, 1200, 'artboard_8_c9bb6f6d1bbb4ba0afbc319d4a02b579.jpg', N'cái', 4,7,1),
    (N'Bút dạ màu - bút marker 24 màu Thiên Long Colokit ART MARKER AM-C001 - Tặng sổ Sketch', N'Việt Nam', '04-04-2043', 193000, 222000, 1200, 'thumnail_2_2_2b3445f0ebcd4142824a1a8ed6434cd4.jpg', N'cái', 4,8,1),
    (N'ở vẽ Sketch Book for Art Thiên Long Colokit nhiều kích cỡ', N'Việt Nam', '04-04-2043', 31000, 39000, 1200, 'a27_92367701dd434146b8c8e1c301a507db.jpg', N'cái', 4,9,1),
    (N'Bút dạ màu - bút marker 12 màu Thiên Long Colokit ART MARKER AM-C002 - Tặng sổ Sketch', N'Việt Nam', '04-04-2043', 87000, 125000, 1200, 'thumbnail_c001_2_4591c851866847af80b05b95f4cdd3b7.jpg', N'cái', 4,10,1),
    (N'Bộ 12 Màu nước dạng nén Thiên Long Colokit WACO-C001', N'Việt Nam', '04-04-2043', 62000, 70000, 1200, 'artboard_3_copy_5cabb4a7b1a94858a32e6af649ef3403.jpg', N'cái', 4,1,1),

  --LSP05
    (N'Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 39000, 44000, 900, 'artboard_21_copy_f5f56bfe1fd54258a0e69ea84bf88fc8.jpg', N'/500 tờ', 5,1,1),
    (N'Ream giấy A4 80 gsm IK Natural-02 (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 76000, 87000, 900, 'ik_natural-02_6f610eb42a7b4b1b9b9a4f2d98e66b22.jpg', N'/500 tờ', 5,2,1),
    (N'Ream giấy A4 80 gsm IK Plus-02 (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 80000, 89000, 900, 'ik_plus-02_a4_80_2_41552256b8bb42f68671cfecc53b12ce.jpg', N'/500 tờ', 5,3,1),
    (N'Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 90000, 101000, 900, 'new27_371cc1386217469d8fae4da74cfdd95b.jpg', N'/500 tờ', 5,4,1),
    (N'Ream giấy A3 70 gsm IK Copy (500 tờ)', N'Việt Nam', '05-05-2033', 156000, 175000, 900, 'artboard_18_copy_cc4a3b8d5a2a48e0bd220cbcdf15e368.jpg', N'/500 tờ', 5,5,1),
    (N'Combo 5 Ream giấy A4 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 369000, 455000, 900, '60002238cb5_3df1d327a2fc4aac9bd22d7c5551b53a.jpg', N'/500 tờ', 5,6,1),
    (N'Ream giấy A3 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 180000, 210000, 900, 'new28_02992d2d2ccf4239ad02d44f65f09813.jpg', N'/500 tờ', 5,7,1),
    (N'Combo 5 Ream giấy A5 70 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 189000, 240000, 900, 'artboard_1_copy_7a25c52744424d58a5d66026d20256ae.jpg', N'/500 tờ', 5,8,1),
    (N'Combo 10 Ream giấy A4 80 gsm IK Copy (500 tờ) - Hàng nhập khẩu Indonesia', N'Việt Nam', '05-05-2033', 858000, 1000000, 900, '60002241cb10_097c5a6e1676461db0c932ece9b04949.jpg', N'/500 tờ', 5,9,1),
    (N'Combo giấy photo văn phòng IK tiết kiệm 2', N'Việt Nam', '05-05-2033', 839000, 988000, 900, '321fsdghgf_3624cb34f65349e8823ff6da5553d8f1.jpg', N'/500 tờ', 5,10,1),


  --LSP06
    (N'Bút lông bi cao cấp Parker Sonnet X-Black GT TB-1950787 Mạ Vàng 18K', N'Việt Nam', '06-06-2073', 4174000, 4879000, 2300, '60001603_3_copy_0df83c7483a944cfac6e94383d5313ca.jpg', N'cây', 6,1,1),
    (N'Bút lông bi cao cấp Parker Sonnet X-ST Steel CT TB-1950873', N'Việt Nam', '06-06-2073', 3376000, 3456000, 2300, '543safhgf_ac31ab8150154ba2830c185d71492092.jpg', N'cây', 6,2,1),
    (N'Bút lông bi cao cấp Parker Sonnet X-M Black GT TB-1950878 - Mạ Vàng 18K', N'Việt Nam', '06-06-2073', 4000000, 5000000, 2300, 'te56_958db628936645bdb6cf5e28d7df853f.jpg', N'cây', 6,3,1),
    (N'Bút lông bi cao cấp Parker IM PRM X-BWN CT TB4-1975585', N'Việt Nam', '06-06-2073', 2221000, 2338000, 2300, 'im_premium_brown_ct_2a703759f9f245b18f99d225e4ab1da8_02003349aa074d8c92b344126cc99c09.jpg', N'cây', 6,4,1),
    (N'Bút lông bi cao cấp Parker IM PRM X-D ES CT TB4-1975583', N'Việt Nam', '06-06-2073', 2221000, 2338000, 2300, 'im_premium_dark_espresso_chiselled_ct_34ac73f625c94785b2dc9e635cfbb42f_acd159ff60f54db08cbad261722019e8.jpg', N'cây', 6, 5,1),
    (N'Bút lông bi cao cấp Parker IM X-DK ESPSO CT TB4-1975579', N'Việt Nam', '06-06-2073', 1210000, 1274000, 2300, 'newellrubbermaid_1931664_m_imgps_20_6c1b0c7636ba424d969c0444a95e67e4_454de69139264ef182fd3285dd68be65.jpg', N'cây', 6,6,1),
    (N'Bút máy cao cấp Parker IM SE Metallic GB4-2074141', N'Việt Nam', '06-06-2073', 3553000, 3741000, 2300, 'im_metallic_pursuit_ct_6276bf01371945f7920ee62a774e8295_6a5f858e11114582a062946533c1a9cf.jpg', N'cây', 6,7,1),
    (N'Bút lông bi cao cấp Parker IM PRM X-D VI CT TB4-1975584', N'Việt Nam', '06-06-2073', 2221000, 2338000, 2300, 'newellrubbermaid_1931639_m_imgps_20_3ddc63469fc54d55a31c0e688330c03b_e9fd446fa77d488385a3c8a49341e9ee.jpg', N'cây', 6,8,1),
    (N'Bút lông bi cao cấp Parker IM PRM X-Blue CT TB4-1975589', N'Việt Nam', '06-06-2073', 2221000, 2338000, 2300, 'im_premium_blue_ct_1c97785366dd4c369f47da4fe4307d65_12d0703e8fde467794bfb757a6fda968.jpg', N'cây', 6,9,1),
    (N'Bút lông bi cao cấp Parker Sonnet X-ST Steel GT TB-1950800', N'Việt Nam', '06-06-2073', 3376000, 3554000, 2300, '7653sa_57382daea80342ca8eaff74104be2a86.jpg', N'cây', 6,10,1),


  --LSP07
    (N'Life (BrE) (2 Ed.) (VN Ed.) A1-A2: Student Book with Code Online', N'Việt Nam', '07-06-2073', 234000, 261000, 300, '9ac7baecb3d8064564a0a447b7ff574d_615ef97d9c524b42bea384538e662832.jpg', N'cuốn', 7,1,1),
    (N'Reading Explorer 2E Level 1 Student Book with Online Workbook Access Code', N'Việt Nam', '07-06-2073', 320000, 356000, 300, 'efcc3404597fc91e1eeeee04a2e22aec_0cae4c2c2f5e48d397ca5dc3bb9509c3.jpg', N'cuốn', 7,1,1),
    (N'Reading Explorer 2E Level 2 Student Book with Online Workbook Access Code', N'Việt Nam', '07-06-2073', 340000, 378000, 300, 'f96581bafb68dff0661fe8e98cbe38ef_b06eefa046e044c78a2f521974d23b33.jpg', N'cuốn', 7,3,1),
    (N'Pathways (VN Ed.) (2 Ed.) Reading, Writing 2: Student Book with Online Workbook', N'Việt Nam', '07-06-2073', 287000, 319000, 300, '52491eba08e50b95b0713c16af49aaff_82c23113fa724923b1ba09340a43ee23.jpg', N'cuốn', 7,4,1),
    (N'Gateway (2 Ed.) B1+: Student Book Pack', N'Việt Nam', '07-06-2073', 227000, 253000, 300, '3cf54c1f904cb74b0943588b660752ed_f7787a4aeb2d446fb8d519b7270733a9_a57f3135625746509a88bfc703db5ee7.jpg', N'cuốn', 7,5,1),
    (N'GREAT WRITING SB 2 & ONLINE WORKBOOK STICKER CODE', N'Việt Nam', '07-06-2073', 340000, 378000, 300, '4ee93e45a471d54a4c1f2634fe798b44_4e8af98cb9b247fe9b052d7091c0513d.jpg', N'cuốn', 7,6,1),
    (N'Bài tập Tiếng Anh 9 Tập 1 (Không đáp án - Chương trình mới của Bộ GD&ĐT)', N'Việt Nam', '07-06-2073', 37000, 42000, 300, 'upload_5d7a1bdfa7124d47bea54c128c63ba3e.jpg', N'cuốn', 7,7,1),
    (N'Pathways (VN Ed.) (2 Ed.) Listening, Speaking 2: Student Book with Online Workbook', N'Việt Nam', '07-06-2073', 287000, 319000, 300, '09d7e68ed031771e7219668a73e3c8ad_2f4a144922f1459bbb15a79ae6d64ebd.jpg', N'cuốn', 7,8,1),
    (N'7 câu hỏi thần kỳ của mọi sếp giỏi', N'Việt Nam', '07-06-2073', 135000, 150000, 300, '980f5537-0ece-41fe-b1a3-5830ad00fe43_a9467f7a40e1462599b4c2ffd74edac6_1024x1024.jpg.jpg', N'cuốn', 7,9,1),
    (N'Pathways (VN Ed.) (2 Ed.) Listening, Speaking Foundations: Student book with Online Workbook', N'Việt Nam', '07-06-2073', 187000, 289000, 300, '939b31bced3ad9815282a17ff9999ad3_ac960c5cc7d84db281667b56a7b6352b.jpg', N'cuốn', 7, 10,1),
    (N'Sống ở thể chủ động (TB)', N'Việt Nam', '07-06-2073', 99000, 110000, 300, 'de30bc2d-96fc-40c2-989b-b770a6366af0_4be551df4b4543c8818ef576d2b711fe.jpg', N'cuốn', 7, 1,1),
    (N'Tâm tình với Đất Mẹ (TB)', N'Việt Nam', '07-06-2073', 90000, 100000, 300, '65533925-de9f-4f58-8ef1-05d6d2dc3782_723ab5ecaf0c447d8d7c9d4b67fd5c59.jpg', N'cuốn', 7,2,1),

  --LSP08
    (N'Bình Nước Cầm Tay 0.45L Rd-045G1 - Ngẫu nhiên', N'Việt Nam', '07-07-2034', 116000, 129000, 300, '045g1_0fec236c1b254ff4b558f50589d17d18.jpg', N'cái', 8,1,1),
    (N'Bình Nước Cầm Tay 0.5L Rd-05P1', N'Việt Nam', '07-07-2034', 126000, 141000, 300, 'upload_1f7bcc8d660c401686c6a8454e00bad6.jpg', N'cái', 8,2,1),
    (N'Bình Nước Cầm Tay Rd-05P2', N'Việt Nam', '07-07-2034', 93000, 110000, 300, 'rd-05p2-4_d90d1180127b4c3793ba26dd97f47463.jpg', N'cái', 8,3,1),
    (N'Bình Nước Rd-05P2.Qt (Hộp Quà Tặng & Dây Treo) - Ngẫu nhiên', N'Việt Nam', '07-07-2034', 118000, 251000, 300, '05p2.qt_7ea9ecae60ee497aab17d978a941e7b0.jpg', N'cái', 8,4,1),
    (N'Đèn Bàn Led Cảm Ứng Rd-Rl-21 6W - Ánh sáng đổi màu', N'Việt Nam', '07-07-2034', 596000, 663000, 300, 'upload_a9d2ad7878bd46b891f2353c46cdd61f.jpg', N'cái', 8,5,1),
    (N'Đèn Led Bàn Học Cảm Ứng 6W Rd-Rl-38.Plus - Ánh sáng đổi màu', N'Việt Nam', '07-07-2034', 361000, 402000, 300, 'upload_b8bd549db5874ca3ad5f4c8e1ec5aca9.jpg', N'cái', 8, 6,1),
    (N'Đèn Bàn Led Cảm Ứng Rd-Rl-45 6W - Ánh sáng đổi màu', N'Việt Nam', '07-07-2034', 338000, 376000, 300, 'upload_00772386944f4e20a60c17330d613fa5.jpg.jpg', N'cái', 8,7,1),
    (N'Đèn Led Bàn Học Cảm Ứng 6W Rd-Rl-41 - Ánh sáng đổi màu', N'Việt Nam', '07-07-2034', 361000, 402000, 300, 'upload_398d73c3a2d341fb87041ceb125dd4dc.jpg', N'cái', 8,8,1),
    (N'Đèn Led Bàn Học Chống Cận 5W Rd-Rl-24.V2', N'Việt Nam', '07-07-2034', 221000, 246000, 300, 'upload_8f5a87b0ba2a44efaafa44d6798cc4b9.jpg', N'cái', 8,9,1),
    (N'Đèn Led Bàn Học Cảm Ứng 8W Rd-Rl-60 Ánh sáng đổi màu', N'Việt Nam', '07-07-2034', 534000, 594000, 300, 'upload_df2613a73a1b411990bc64e280fbd5de.jpg', N'cái', 8,10,1);


INSERT INTO KhachHang (TenKH, DiaChi, SDT, Email, TrangThai)
VALUES
  (N'Nguyễn Văn Anh', N'Hà Nội', '0123456789', 'vananh@gmail.com', 1),
  (N'Trần Thị Binh', N'Hồ Chí Minh', '0987654321', 'thibinh@gmail.com',  1),
  (N'Lê Văn Cường', N'Đà Nẵng', '0365478962', 'vancuong@gmail.com',  1),
  (N'Phạm Thị Dinh', N'Hải Phòng', '0912345678', 'thidinh@gmail.com',  1),
  (N'Hoàng Văn Em', N'Cần Thơ', '0845632197', 'vanem@gmail.com',  1),
  (N'Vũ Thị Kiều', N'Hải Dương', '0357918642', 'thikieu@gmail.com',  1),
  (N'Ngô Văn Giang', N'Bình Dương', '0975318642', 'vangiang@gmail.com',  1),
  (N'Lý Thị Hà', N'Quảng Ninh', '0932147856', 'thiha@gmail.com',  1),
  (N'Trương Văn Inh', N'Lâm Đồng', '0909090909', 'vaninh@gmail.com',  1),
  (N'Đặng Thị Kim', N'Đồng Nai', '0888888888', 'thikim@gmail.com',  1);


INSERT INTO NhanVien (TenNV, GioiTinh, NgaySinh, SDT, Email, Password, TrangThai, QuyenTruyCap)
VALUES
  (N'Nguyễn Văn Lợi', N'Nam', '1990-01-01', '0123456789', 'vanloi@gmail.com', '12345', 1, N'Admin'),
  (N'Phạm Thị Thu Phương', N'Nữ', '1995-05-10', '0987654321', 'thuphuong@gmail.com', '12345', 1, N'User'),
  (N'Đỗ Công Tôn Sách', N'Nam', '1988-12-15', '0365478962', 'tonsach@gmail.com', '12345', 1,  N'User'),
  (N'Nguyễn Anh Trọng', N'Nam', '1992-08-20', '0912345678', 'anhtrong@gmail.com', '12345', 0,  N'User');


INSERT INTO PhieuNhap (NgayLap, ThanhTienPN, MaNCC, MaNV, TrangThai)
VALUES
  ('2022-01-01', null, 1,1, 1),
  ('2022-02-05', null, 2,2,1),
  ('2022-03-10', null, 3,3,1),
  ('2022-04-15', null, 4,4,1),
  ('2022-05-20', null, 5,1,1),
  ('2022-06-25', null, 6,3,1),
  ('2022-07-30', null, 7,1,1),
  ('2022-08-05', null, 8,4,1),
  ('2022-09-10', null, 9,3, 0),
  ('2022-10-15', null, 10, 2, 0);


INSERT INTO CTPN (MaPN, MaSP, SL, DonGia)
VALUES
  (1,1, 10, null),
  (1,2, 5, null),
  (2,3, 8, null),
  (2,4, 3, null),
  (3,5, 12, null),
  (3,6, 6, null),
  (4,7, 7, null),
  (4,8, 4, null),
  (5,9, 9, null),
  (5,10, 2, null),
  (6,11, 5, null),
  (6,12, 3, null),
  (7,13, 7, null),
  (7,14, 4, null),
  (8,15, 8, null),
  (8,16, 5, null),
  (9,17, 6, null),
  (9,18, 3, null),
  (10,19, 9, null),
  (10,20, 4, null);

 

INSERT INTO HoaDon (MaNV, ThanhTienHD, NgayLapHD, MaKH, TrangThai)
VALUES
  (1, null, '2022-01-01', 1, 1),
  (2, null, '2022-02-05', 2, 1),
  (3, null, '2022-03-10', 3, 1),
  (4, null, '2022-04-15', 4, 1),
  (2, null, '2022-05-20', 5, 0);

   
INSERT INTO CTHD (MaHD, MaSP, SL, DonGia)
VALUES
  (1,1, 1, NULL),
  (1,2, 2, NULL),
  (2,3, 3, NULL),
  (2,4, 3, NULL),
  (3,5, 1, NULL),
  (3,6, 1, NULL),
  (4,7, 1, NULL),
  (4,8, 1, NULL),
  (5,9, 1, NULL),
  (5,10, 2, NULL);
-- TRIGGER
--Cập nhật thông tin số lượng SanPham, ThanhTienHD trong Bang HoaDon tương ứng khi có một SP mới được thêm vào bảng CTHD.
GO
--drop TRIGGER TG_CapNhapSoLuong_Kho
CREATE TRIGGER TG_CapNhapSoLuong_Kho ON CTHD
FOR INSERT
AS
BEGIN
    DECLARE @MAPX CHAR(5), @MASP CHAR(5), @SLX INT, @GIA DECIMAL(10, 2), @SLConLai INT
    
    BEGIN TRY
        SELECT @MAPX = MaHD, @MASP = MASP, @SLX = SL, @GIA = DonGia FROM inserted
        
        SET @SLConLai = (SELECT SoLuong FROM SanPham WHERE MaSP = @MASP) - @SLX
        
        IF @SLConLai >= 0
        BEGIN
            UPDATE SanPham
            SET SoLuong = @SLConLai
            WHERE MaSP = @MASP
            
            UPDATE HoaDon
            SET ThanhTienHD = ThanhTienHD + (@SLX * @GIA)
            WHERE MaHD = @MAPX
        END
        ELSE
        BEGIN
            PRINT N'Số lượng xuất vượt quá số lượng kho'
            RAISERROR('Số lượng xuất vượt quá số lượng kho', 16, 1)
        END
    END TRY
    BEGIN CATCH
        PRINT 'Lỗi trong Trigger TG_CapNhapSoLuong_Kho: ' + ERROR_MESSAGE()
        
    END CATCH
END
--INSERT INTO CTHD (MaHD, MaSP, SL, DonGia)
--VALUES
--(5,80, 3, NULL);

--drop TRIGGER UpdateSLSP
CREATE TRIGGER UpdateSLSP
ON PhieuNhap
AFTER UPDATE
AS
BEGIN
    -- Ensure we only process when needed
    IF UPDATE(TrangThai)
    BEGIN
        -- Update the quantity in SanPham based on the changes in PhieuNhap
        UPDATE SanPham
        SET SoLuong = SanPham.SoLuong + (
                        SELECT SUM(CTPN.SL)
                        FROM CTPN
                        WHERE CTPN.MaPN IN (SELECT DISTINCT INSERTED.MaPN FROM INSERTED)
                    )
        FROM INSERTED
        WHERE SanPham.MaSP IN (SELECT DISTINCT CTPN.MaSP FROM CTPN WHERE CTPN.MaPN IN (SELECT DISTINCT INSERTED.MaPN FROM INSERTED))
              AND EXISTS (
                      SELECT 1
                      FROM CTPN
                      WHERE CTPN.MaPN IN (SELECT DISTINCT INSERTED.MaPN FROM INSERTED)
                            AND CTPN.MaSP = SanPham.MaSP
              );
    END
END;

  select*from NhanVien
  Select*from KhachHang
  select*from HoaDon
  select*from CTHD
  select*from PhieuNhap
  select*from CTPN
  select*from SanPham
  select*from NhaCungCap
  select*from LoaiSanPham
------------------------------------------------ CRUD ------------------------------------------------

--INSERT INTO nhanvien (MaNV, TenNV, GioiTinh,NgaySinh, SDT, Email, Password, TrangThai ) VALUES (?,?,?,?,?,?,?,?)
--UPDATE NhanVien SET TenNV=?,GioiTinh=?,NgaySinh=?,SDT=?, Email=?, Password=?, TrangThai=?  WHERE MaNV=?
--UPDATE NhanVien SET TrangThai = 0 WHERE MaNV = ?
--SELECT * FROM NhanVien WHERE TrangThai = 1
--SELECT * FROM NhanVien WHERE Email = ? AND Password = ?
--INSERT INTO SanPham (TenSP, XuatXu, HSD, GiaNhap, GiaXuat, SoLuong, HinhAnh, DonViTinh, MaLoai, MaNCC) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?),
--UPDATE SanPham SET TenSP = ?, XuatXu = ?, HSD = ?, GiaNhap = ?, GiaXuat = ?, SoLuong = ?, HinhAnh = ?, DonViTinh = , MaLoai = ?, MaNCC = ? WHERE MaSP = ?
--SELECT*FROM SANPHAM WHERE TRANGTHAI = 1
--UPDATE SanPham SET TrangThai = 0 WHERE MaSP = ? 
--SELECT * FROM SANPHAM 
--UPDATE SanPham SET SoLuong=? WHERE MaSP = ?
--SELECT*FROM NhaCungCap WHERE TRANGTHAI = 1
--INSERT INTO NhaCungCap(TenNCC, DiaChi, Email, SDT) VALUES(?,?,?,?)
--UPDATE NhaCungCap SET TenNCC = ?, DiaChi = ?, Email = ?, SDT = ?
--UPDATE NhaCungCap SET TRANGTHAI = 0 WHERE MaNCC = ?
--SELECT*FROM LoaiSanPham WHERE TRANGTHAI = 1
--UPDATE LoaiSanPham SET TenLoai = ?, TrangThai = ?
--SELECT*FROM LoaiSanPham WHERE MaLoai = 1
--INSERT INTO KhachHang (TenKH, DiaChi, SDT, Email, TrangThai) VALUES (?,?,?,?,?)
--UPDATE KhachHang SET TenKH = ?, DiaChi = ?, SDT = ?, Email = ? WHERE MaKH = ?
--UPDATE KhachHang SET TrangThai = 0 WHERE MaKH = ?
--SELECT * FROM KhachHang WHERE TrangThai = 1
--SELECT*FROM KhachHang WHERE MaKH = ?
--SELECT*FROM KhachHang WHERE Email = ?
--use QL_VanPhongPham

--select*from SanPham where trangthai = 1
--SELECT MaNCC FROM NhaCungCap WHERE TenNCC = 'Thiên Long'
--SELECT * FROM LoaiSanPham WHERE TRANGTHAI = 1
--select*from KhachHang