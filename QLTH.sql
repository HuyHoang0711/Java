USE master
GO
IF EXISTS (SELECT name
		   FROM sys.databases
		   WHERE name = 'QLThucHanh'
		   )
DROP DATABASE QLThucHanh
GO
-- Tạo CSDL QLThucHanh
CREATE DATABASE QLThucHanh
GO
-- Sử dụng CSDL QLThucHanh
USE QLThucHanh
GO
-- Tạo bảng NGUOIQUANTRI
CREATE TABLE NGUOIQUANTRI
(
MaNhanVien char(4) NOT NULL,
HoTen nvarchar(50),
TaiKhoan varchar(100),
MatKhau varchar(100),
GioiTinh nvarchar(3),
SoDienThoai char(10),
NganhQuanLy nvarchar(50),
CONSTRAINT PK_NGUOIQUANTRI PRIMARY KEY(MaNhanVien),
CONSTRAINT UNI_TaiKhoanQT UNIQUE(TaiKhoan)
)
GO
-- Thêm dữ liệu cho bảng NGUOIQUANTRI
INSERT INTO NGUOIQUANTRI VALUES
('QT01', N'Lê Ngọc Mạnh', 'NguyenManh123', 'Manh123', 'Nam', '0866195511', N'Khoa học máy tính'),
('QT02', N'Nguyễn Thành Nam', 'ThanhNam236', 'Nam236', 'Nam', '0867779321', N'Công nghệ thông tin'),
('QT03', N'Đinh Như Ngọc', 'NhuNgoc479', 'Ngoc479', N'Nữ', '0869554732', N'Hệ thống thông tin'),
('QT04', N'Hà Tuấn Hoàng', 'TuanHoang983', 'Hoang83', 'Nam', '0868771099', N'Kĩ thuật phần mềm')
GO
-- Tạo bảng GIANGVIEN
CREATE TABLE GIANGVIEN
(
MaGiangVien char(4) NOT NULL,
HoTen nvarchar(50),
TaiKhoan varchar(100),
MatKhau varchar(100),
GioiTinh nvarchar(3),
SoDienThoai char(10),
GiangVienNganh nvarchar(50),
MaNhanVien char(4),
CONSTRAINT PK_GIANGVIEN PRIMARY KEY(MaGiangVien),
CONSTRAINT FK_NHANVIEN FOREIGN KEY(MaNhanVien) REFERENCES NGUOIQUANTRI(MaNhanVien)
ON DELETE CASCADE,
CONSTRAINT UNI_TaiKhoanGV UNIQUE(TaiKhoan)
)
GO
-- Thêm dữ liệu cho bảng GIANGVIEN
INSERT INTO GIANGVIEN VALUES
('GV01', N'Nguyễn Thị Nhung', 'Nhungnt', 'Nhung123', N'Nữ', '0869523991', N'Hệ thống thông tin', 'QT03'),
('GV02', N'Phạm Thế Anh', 'Anhtp', 'TheAnh123', N'Nam', '0868123391', N'Khoa học máy tính', 'QT01'),
('GV03', N'Vũ Ngọc Linh', 'Linhnv', 'Linh123', N'Nữ', '0866333997', N'Công nghệ thông tin', 'QT02')
GO
-- Tạo bảng LOPDOCLAP
CREATE TABLE LOPDOCLAP
(
MaLop char(15) NOT NULL,
TenMonHoc nvarchar(100),
HeDaoTao nvarchar(20),
KhoaDaoTao char(3),
LichLyThuyet char(2),
PhongLyThuyet varchar(10),
SoLuongSV int,
MaGiangVien char(4),
CONSTRAINT PK_LOPDOCLAP PRIMARY KEY(MaLop),
CONSTRAINT FK_GIANGVIEN FOREIGN KEY(MaGiangVien) REFERENCES GIANGVIEN(MaGiangVien)
ON DELETE CASCADE
)
GO
-- Thêm dữ liệu cho bảng LOPDOCLAP
INSERT INTO LOPDOCLAP VALUES
('202020503175001', N'Lập trình Java', N'Đại học', 'K13', 'S6', 'P302-A9', 70, 'GV03'),
('202020503175002', N'Lập trình Java', N'Đại học', 'K13', 'S2', 'P301-A9', 70, 'GV03'),
('202020503175003', N'Lập trình Java', N'Đại học', 'K13', 'C5', 'P302-A9', 80, 'GV03'),
('202020503175004', N'Lập trình Java', N'Đại học', 'K13', 'C3', 'P301-A9', 70, 'GV03'),
('202020503175005', N'Lập trình Java', N'Đại học', 'K13', 'C2', 'P301-A9', 70, 'GV03'),
('202020503175006', N'Lập trình Java', N'Đại học', 'K13', 'S7', 'P302-A9', 80, 'GV03'),
('202020503175007', N'Lập trình Java', N'Đại học', 'K13', 'S4', 'P302-A9', 80, 'GV03'),
--
('20201IT6029002', N'Lập trình Android', N'Đại học', 'K12', 'S3', 'P307-A9', 70, 'GV03'),
('20201IT6029003', N'Lập trình Android', N'Đại học', 'K12', 'C4', 'P307-A9', 70, 'GV03'),
('20201IT6029004', N'Lập trình Android', N'Đại học', 'K12', 'S5', 'P307-A9', 70, 'GV03'),
('20201IT6029005', N'Lập trình Android', N'Đại học', 'K12', 'C6', 'P307-A9', 70, 'GV03'),
('20201IT6029006', N'Lập trình Android', N'Đại học', 'K12', 'C7', 'P307-A9', 70, 'GV03'),
('20201IT6029007', N'Lập trình Android', N'Đại học', 'K12', 'S8', 'P307-A9', 70, 'GV03'),
('20201IT6029008', N'Lập trình Android', N'Đại học', 'K12', 'C8', 'P307-A9', 70, 'GV03'),
--
('202020503123011', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'C2', 'P402-A9', 70, 'GV01'),
('202020503123012', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'C3', 'P402-A9', 70, 'GV01'),
('202020503123013', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'S2', 'P402-A9', 70, 'GV01'),
('202020503123014', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'C7', 'P401-A9', 70, 'GV01'),
('202020503123015', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'S7', 'P401-A9', 70, 'GV01'),
('202020503123016', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'C6', 'P401-A9', 70, 'GV01'),
('202020503123017', N'Hệ quản trị cơ sở dữ liệu SQL Server', N'Đại học', 'K13', 'S5', 'P401-A9', 70, 'GV01'),
--
('202020503113011', N'Mạng máy tính', N'Đại học', 'K13', 'S2', 'P305-A9', 70, 'GV02'),
('202020503113012', N'Mạng máy tính', N'Đại học', 'K13', 'S3', 'P305-A9', 70, 'GV02'),
('202020503113013', N'Mạng máy tính', N'Đại học', 'K13', 'S4', 'P305-A9', 70, 'GV02'),
('202020503113014', N'Mạng máy tính', N'Đại học', 'K13', 'S5', 'P305-A9', 70, 'GV02'),
('202020503113015', N'Mạng máy tính', N'Đại học', 'K13', 'S6', 'P305-A9', 70, 'GV02'),
('202020503113016', N'Mạng máy tính', N'Đại học', 'K13', 'S7', 'P305-A9', 70, 'GV02'),
('202020503113017', N'Mạng máy tính', N'Đại học', 'K13', 'S8', 'P305-A9', 70, 'GV02'),
--
('202010503153007', N'Quản trị mạng', N'Đại học', 'K13', 'C7', 'P303-A9', 70, 'GV02'),
('202010503153008', N'Quản trị mạng', N'Đại học', 'K13', 'C6', 'P303-A9', 70, 'GV02'),
('202010503153009', N'Quản trị mạng', N'Đại học', 'K13', 'C5', 'P303-A9', 70, 'GV02'),
('202010503153010', N'Quản trị mạng', N'Đại học', 'K13', 'C2', 'P303-A9', 70, 'GV02'),
('202010503153011', N'Quản trị mạng', N'Đại học', 'K13', 'C4', 'P303-A9', 69, 'GV02'),
('202010503153012', N'Quản trị mạng', N'Đại học', 'K13', 'C3', 'P303-A9', 65, 'GV02'),
('202010503153013', N'Quản trị mạng', N'Đại học', 'K13', 'C8', 'P303-A9', 65, 'GV02')
GO
-- Tạo bảng PHONGMAY
CREATE TABLE PHONGMAY
(
MaPhong char(4) NOT NULL,
TenPhong nvarchar(50),
ViTriPhong nvarchar(50),
SoLuongMay int,
CoSoVatChat nvarchar(20),
CONSTRAINT PK_PHONGMAY PRIMARY KEY(MaPhong)
)
GO
-- Thêm dữ liệu cho bảng PHONGMAY
INSERT INTO PHONGMAY VALUES
('P001', N'Phòng máy 01', N'Tầng 7 - A1 - Cơ sở 1', 35, N'Tốt'),
('P002', N'Phòng máy 02', N'Tầng 7 - A1 - Cơ sở 1', 35, N'Tốt'),
('P003', N'Phòng máy 03', N'Tầng 7 - A1 - Cơ sở 1', 35, N'Tốt'),
('P004', N'Phòng máy 04', N'Tầng 7 - A1 - Cơ sở 1', 35, N'Tốt'),
('P005', N'Phòng máy 05', N'Tầng 8 - A1 - Cơ sở 1', 35, N'Tốt'),
('P006', N'Phòng máy 06', N'Tầng 8 - A1 - Cơ sở 1', 35, N'Tốt'),
('P007', N'Phòng máy 07', N'Tầng 8 - A1 - Cơ sở 1', 35, N'Tốt'),
('P008', N'Phòng máy 08', N'Tầng 8 - A1 - Cơ sở 1', 35, N'Tốt'),
('P009', N'Phòng máy 09', N'Tầng 9 - A1 - Cơ sở 1', 35, N'Tốt'),
('P010', N'Phòng máy 10', N'Tầng 9 - A1 - Cơ sở 1', 35, N'Tốt'),
('P011', N'Phòng máy 11', N'Tầng 9 - A1 - Cơ sở 1', 35, N'Tốt'),
('P012', N'Phòng máy CLC 01', N'Tầng 9 - A1 - Cơ sở 1', 35, N'Tốt'),
('P013', N'Phòng máy 03 (Hà Nam)', N'Tầng 3 - C8 - Cơ sở 3', 35, N'Tốt'),
('P014', N'Phòng máy 04 (Hà Nam)', N'Tầng 3 - C8 - Cơ sở 3', 35, N'Tốt'),
('P015', N'Phòng máy 05 (Hà Nam)', N'Tầng 3 - C8 - Cơ sở 3', 35, N'Tốt')
GO

-- Tạo bảng DANGKYPHONG
CREATE TABLE DANGKYPHONG
(
MaGiangVien char(4) NOT NULL,
MaPhong char(4) NOT NULL,
CaThucHanh char(1) NOT NULL,
ThoiGian datetime NOT NULL,
MaLop char(15) NOT NULL,
Nhom char(2),
CONSTRAINT PK_DANGKYPHONG PRIMARY KEY(MaGiangVien, MaPhong, CaThucHanh, ThoiGian),
CONSTRAINT FK_PHONGMAY FOREIGN KEY(MaPhong) REFERENCES PHONGMAY(MaPhong)
ON DELETE CASCADE,
CONSTRAINT FK_GIANGVIENY FOREIGN KEY(MaGiangVien) REFERENCES GIANGVIEN(MaGiangVien)
ON DELETE CASCADE,
CONSTRAINT UNIQUE_MP_CTH_TG UNIQUE(MaPhong, CaThucHanh, ThoiGian)
)
GO
-- Thêm dữ liệu cho bảng DANGKYPHONG
INSERT INTO DANGKYPHONG VALUES
('GV01', 'P001', 'S', '2021-04-01', '202020503123011', '2N'),
('GV01', 'P001', 'S', '2021-04-02', '202020503123012', '2N'),
('GV01', 'P001', 'S', '2021-04-03', '202020503123013', '2N'),
('GV01', 'P001', 'S', '2021-04-04', '202020503123014', '2N'),
('GV01', 'P001', 'S', '2021-04-05', '202020503123015', '2N'),
('GV01', 'P001', 'S', '2021-04-06', '202020503123016', '2N'),
('GV01', 'P001', 'S', '2021-04-07', '202020503123017', '2N'),
--
('GV01', 'P001', 'S', '2021-04-08', '202020503123011', '2N'),
('GV01', 'P002', 'S', '2021-04-09', '202020503123012', '2N'),
('GV01', 'P003', 'S', '2021-04-10', '202020503123013', '2N'),
('GV01', 'P004', 'S', '2021-04-11', '202020503123014', '2N'),
('GV01', 'P005', 'S', '2021-04-12', '202020503123015', '2N'),
('GV01', 'P006', 'S', '2021-04-13', '202020503123016', '2N'),
('GV01', 'P001', 'S', '2021-04-14', '202020503123017', '2N'),
--
('GV01', 'P007', 'S', '2021-04-15', '202020503123011', '2N'),
('GV01', 'P008', 'S', '2021-04-16', '202020503123012', '2N'),
('GV01', 'P009', 'S', '2021-04-17', '202020503123013', '2N'),
('GV01', 'P010', 'S', '2021-04-18', '202020503123014', '2N'),
('GV01', 'P011', 'S', '2021-04-19', '202020503123015', '2N'),
('GV01', 'P012', 'S', '2021-04-20', '202020503123016', '2N'),
('GV01', 'P012', 'S', '2021-04-21', '202020503123017', '2N'),
--
('GV02', 'P001', 'C', '2021-04-01', '202020503113011', '2N'),
('GV02', 'P003', 'C', '2021-04-02', '202020503113012', '2N'),
('GV02', 'P004', 'S', '2021-04-03', '202020503113013', '2N'),
('GV02', 'P005', 'C', '2021-04-04', '202020503113014', '2N'),
('GV02', 'P006', 'S', '2021-04-05', '202020503113015', '2N'),
('GV02', 'P006', 'S', '2021-04-06', '202020503113016', '2N'),
('GV02', 'P006', 'S', '2021-04-07', '202020503113017', '2N'),
--
('GV02', 'P001', 'C', '2021-04-08', '202020503113011', '2N'),
('GV02', 'P003', 'C', '2021-04-09', '202020503113012', '2N'),
('GV02', 'P004', 'S', '2021-04-10', '202020503113013', '2N'),
('GV02', 'P005', 'C', '2021-04-11', '202020503113014', '2N'),
('GV02', 'P006', 'S', '2021-04-12', '202020503113015', '2N'),
('GV02', 'P007', 'S', '2021-04-13', '202020503113016', '2N'),
('GV02', 'P006', 'S', '2021-04-14', '202020503113017', '2N'),
--
('GV02', 'P002', 'C', '2021-04-15', '202020503113011', '2N'),
('GV02', 'P003', 'C', '2021-04-16', '202020503113012', '2N'),
('GV02', 'P004', 'S', '2021-04-17', '202020503113013', '2N'),
('GV02', 'P005', 'C', '2021-04-18', '202020503113014', '2N'),
('GV02', 'P006', 'S', '2021-04-19', '202020503113015', '2N'),
('GV02', 'P006', 'S', '2021-04-20', '202020503113016', '2N'),
('GV02', 'P006', 'S', '2021-04-21', '202020503113017', '2N'),
--
('GV02', 'P002', 'S', '2021-04-01', '202010503153007', '2N'),
('GV02', 'P003', 'S', '2021-04-02', '202010503153008', '2N'),
('GV02', 'P004', 'C', '2021-04-03', '202010503153009', '2N'),
('GV02', 'P005', 'S', '2021-04-04', '202010503153010', '2N'),
('GV02', 'P006', 'C', '2021-04-05', '202010503153011', '2N'),
('GV02', 'P006', 'C', '2021-04-06', '202010503153012', '2N'),
('GV02', 'P006', 'C', '2021-04-07', '202010503153013', '2N'),
--
('GV02', 'P002', 'S', '2021-04-08', '202010503153007', '2N'),
('GV02', 'P003', 'S', '2021-04-09', '202010503153008', '2N'),
('GV02', 'P004', 'C', '2021-04-10', '202010503153009', '2N'),
('GV02', 'P005', 'S', '2021-04-11', '202010503153010', '2N'),
('GV02', 'P006', 'C', '2021-04-12', '202010503153011', '2N'),
('GV02', 'P006', 'C', '2021-04-13', '202010503153012', '2N'),
('GV02', 'P006', 'C', '2021-04-14', '202010503153013', '2N'),
--
('GV02', 'P002', 'S', '2021-04-15', '202010503153007', '2N'),
('GV02', 'P003', 'S', '2021-04-16', '202010503153008', '2N'),
('GV02', 'P004', 'C', '2021-04-17', '202010503153009', '2N'),
('GV02', 'P005', 'S', '2021-04-18', '202010503153010', '2N'),
('GV02', 'P006', 'C', '2021-04-19', '202010503153011', '2N'),
('GV02', 'P006', 'C', '2021-04-20', '202010503153012', '2N'),
('GV02', 'P006', 'C', '2021-04-21', '202010503153013', '2N'),
--
('GV03', 'P003', 'S', '2021-04-01', '202020503175001', '2N'),
('GV03', 'P004', 'C', '2021-04-02', '202020503175002', '2N'),
('GV03', 'P007', 'S', '2021-04-03', '202020503175003', '2N'),
('GV03', 'P006', 'C', '2021-04-04', '202020503175004', '2N'),
('GV03', 'P005', 'S', '2021-04-05', '202020503175005', '2N'),
('GV03', 'P005', 'S', '2021-04-06', '202020503175006', '2N'),
('GV03', 'P005', 'S', '2021-04-07', '202020503175007', '2N'),
--
('GV03', 'P003', 'S', '2021-04-08', '202020503175001', '2N'),
('GV03', 'P004', 'C', '2021-04-09', '202020503175002', '2N'),
('GV03', 'P007', 'S', '2021-04-10', '202020503175003', '2N'),
('GV03', 'P006', 'C', '2021-04-11', '202020503175004', '2N'),
('GV03', 'P008', 'S', '2021-04-12', '202020503175005', '2N'),
('GV03', 'P005', 'S', '2021-04-13', '202020503175006', '2N'),
('GV03', 'P005', 'S', '2021-04-14', '202020503175007', '2N'),
--
('GV03', 'P003', 'S', '2021-04-15', '202020503175001', '2N'),
('GV03', 'P004', 'C', '2021-04-16', '202020503175002', '2N'),
('GV03', 'P007', 'S', '2021-04-17', '202020503175003', '2N'),
('GV03', 'P006', 'C', '2021-04-18', '202020503175004', '2N'),
('GV03', 'P005', 'S', '2021-04-19', '202020503175005', '2N'),
('GV03', 'P005', 'S', '2021-04-20', '202020503175006', '2N'),
('GV03', 'P005', 'S', '2021-04-21', '202020503175007', '2N'),
--
('GV03', 'P009', 'C', '2021-04-01', '20201IT6029002', '2N'),
('GV03', 'P009', 'S', '2021-04-02', '20201IT6029003', '2N'),
('GV03', 'P009', 'C', '2021-04-03', '20201IT6029004', '2N'),
('GV03', 'P007', 'S', '2021-04-04', '20201IT6029005', '2N'),
('GV03', 'P007', 'C', '2021-04-05', '20201IT6029006', '2N'),
('GV03', 'P007', 'C', '2021-04-06', '20201IT6029007', '2N'),
('GV03', 'P007', 'C', '2021-04-07', '20201IT6029008', '2N'),
--
('GV03', 'P009', 'C', '2021-04-08', '20201IT6029002', '2N'),
('GV03', 'P009', 'S', '2021-04-09', '20201IT6029003', '2N'),
('GV03', 'P009', 'C', '2021-04-10', '20201IT6029004', '2N'),
('GV03', 'P007', 'S', '2021-04-11', '20201IT6029005', '2N'),
('GV03', 'P007', 'C', '2021-04-12', '20201IT6029006', '2N'),
('GV03', 'P007', 'C', '2021-04-13', '20201IT6029007', '2N'),
('GV03', 'P007', 'C', '2021-04-14', '20201IT6029008', '2N'),
--
('GV03', 'P009', 'C', '2021-04-15', '20201IT6029002', '2N'),
('GV03', 'P009', 'S', '2021-04-16', '20201IT6029003', '2N'),
('GV03', 'P009', 'C', '2021-04-17', '20201IT6029004', '2N'),
('GV03', 'P007', 'S', '2021-04-18', '20201IT6029005', '2N'),
('GV03', 'P007', 'C', '2021-04-19', '20201IT6029006', '2N'),
('GV03', 'P007', 'C', '2021-04-20', '20201IT6029007', '2N'),
('GV03', 'P007', 'C', '2021-04-21', '20201IT6029008', '2N')
--
GO

-- Xem dữ liệu từ 5 bảng
SELECT * FROM NGUOIQUANTRI
SELECT * FROM GIANGVIEN
SELECT * FROM LOPDOCLAP
SELECT * FROM PHONGMAY
SELECT * FROM DANGKYPHONG

