use master
go

drop database QLLSP
go

CREATE DATABASE QLLSP ON
(NAME = QQLSP_dat,
    FILENAME = 'T:\QLLSP\QLLSP.mdf',
    SIZE = 10 MB,
    MAXSIZE = 512 MB,
    FILEGROWTH = 5 MB)
LOG ON
(NAME = QQLSP_log,
    FILENAME = 'T:\QLLSP\QLLSP.ldf',
    SIZE = 5 MB,
    MAXSIZE = 25 MB,
    FILEGROWTH = 5 MB)
GO

use QLLSP
go

create table Xuong (
	maXuong nvarchar(3) not null primary key,
	tenXuong nvarchar(40) not null,
	diaChi nvarchar(50) not null,
)

create table CongNhan (
	maCN nvarchar(8) not null primary key,
	anhDaiDien varbinary(max),
	ho nvarchar (60) not null,
	ten nvarchar (10) not null,
	gioiTinh bit,
	ngaySinh date,
	cCCD nvarchar(12) not null,
	diaChi nvarchar (120),
	soDienThoai nvarchar(10),
	chuyenMon nvarchar(20),
	caLamViec int,
	phuCap float,
	luongCoBan float,
	ngayBatDauLamViec date not null,
	maXuong nvarchar(3),
	constraint FK_Xuong foreign key (maXuong)
	references Xuong(maXuong)
)

create table BangLuongCongNhan (
	maLuongCN nvarchar(12) not null primary key,
	maCN nvarchar(8) not null,
	thang int,
	nam int,
	sanLuongTong int,
	soNgayNghiKhongPhep int,
	tienPhat float,
	bhxh float,
	luongTong float,
	constraint FK_BLCN foreign key (maCN)
	references CongNhan(maCN)
)

create table BangChamCongCongNhan (
	maCC nvarchar(13) not null primary key,
	ngayCham date not null,
	maCN nvarchar(8) not null,
	vangMat bit,
	coPhep bit,
	soGioTangCa int,
	sanLuong int not null,
	ghiChu nvarchar(100),
	constraint FK_CCCN foreign key (maCN)
	references CongNhan(maCN)
)

create table HopDong (
	maHopDong nvarchar(8) not null primary key,
	tenDoiTac nvarchar(100),
	ngayKi date,
	ngayThanhLyHopDong date,
	trangThai bit
)

create table SanPham (
	maSP nvarchar(10) not null primary key,
	tenSP nvarchar (50) not null,
	soLuong int,
	soLuongCongDoan int,
	trangThai bit,
	maHopDong nvarchar(8) not null,
	constraint FK_HD foreign key (maHopDong)
	references HopDong(maHopDong)
)

create table CongDoan (
	maCongDoan nvarchar(11) not null primary key,
	tenCongDoan nvarchar(20),
	soLuongCongNhanDuKien int,
	soLuongSanPham int not null,
	trangThai bit,
	giaTien float,
	ngayBatDau date,
	ngayKetThucDuKien date,
	congDoanTienQuyet nvarchar(12),
	maSP nvarchar(10) not null,
	constraint FK_SP foreign key (maSP)
	references SanPham(maSP)
)

create table BangPhanCongCN (
	maPCCN nvarchar(17) not null primary key,
	trangThai bit,
	ngayPhanCong date,
	soLuongSP int,
	maCN nvarchar(8),
	constraint FK_PCCN foreign key (maCN)
	references CongNhan(maCN),
	maCD nvarchar(11),
	constraint FK_PCCD foreign key (maCD)
	references CongDoan(maCongDoan)
)

create table BoPhan (
	maBoPhan nvarchar(4) not null primary key,
	tenBoPhan nvarchar(20),
	sDTBoPhan nvarchar(10)
)

create table NhanVien (
	maNV nvarchar(8) not null primary key,
	anhDaiDien varbinary(max),
	ho nvarchar (60) not null,
	ten nvarchar (10) not null,
	gioiTinh bit,
	soDienThoai nvarchar(10),
	diaChi nvarchar (120),
	cCCD nvarchar(12) not null,
	ngaySinh date,
	ngayBatDauLamViec date not null,
	caLamViec int,
	luongCoBan float,
	thangBacLuong int,
	heSoLuong float,
	phuCap float,
	maBP nvarchar(4) not null,
	constraint FK_BP foreign key (maBP)
	references BoPhan(maBoPhan)
)

create table BangLuongNhanVien (
	maLuongNV nvarchar(12) not null primary key,
	maNV nvarchar(8) not null,
	thang int,
	nam int,
	soNgayDiLam int,
	soNgayNghiKhongPhep int,
	tienPhat float,
	bhxh float,
	luongTong float,
	constraint FK_BLNV foreign key (maNV)
	references NhanVien(maNV)
)

create table BangChamCongNhanVien (
	maChamCong nvarchar(14) not null primary key,
	maNV nvarchar(8) not null,
	ngayCham date not null,
	soGioTangCa int,
	caLam int,
	coMat bit,
	vangMat bit,
	coPhep bit,
	ghiChu nvarchar(100),
	constraint FK_CCNV foreign key (maNV)
	references NhanVien(maNV)
)

create table BangPhanCongQLXuong (
	maPCX nvarchar(11) not null primary key,
	ngayNhanXuong date not null,
	maXuong nvarchar(3) not null,
	constraint FK_XuongPC foreign key (maXuong)
	references Xuong(maXuong),
	maNV nvarchar(8) not null,
	constraint FK_NVPC foreign key (maNV)
	references NhanVien(maNV),
	ngayKetThucQLXuong date
)

create table TaiKhoan (
	maTK nvarchar (8) not null primary key,
	taiKhoan nvarchar(20),
	matKhau nvarchar(16),
	maNV nvarchar(8) not null,
	ngayDNCuoi date
	constraint FK_TKNV foreign key (maNV)
	references NhanVien(maNV)
)
go

insert into BoPhan values 
	('BPNS', N'Bộ phận nhân sự', '0812808080'),
	('BPKT', N'Bộ phận kế toán', '0812818181'),
	('QLXU', N'Quản lý Xưởng', '0812828282')
go

insert into Xuong values
	('MA1', N'Xưởng may 1', ''),
	('NH1', N'Xưởng nhuộm 1', ''),
	('NH2', N'Xưởng nhuộm 2', ''),
	('GC1', N'Xưởng gia công 1', ''),
	('DG1', N'Xưởng đóng gói 1', '')
go

INSERT INTO NhanVien (maNV, ho, ten, gioiTinh, cCCD, ngaySinh, diaChi, maBP, soDienThoai, ngayBatDauLamViec, thangBacLuong, caLamViec, luongCoBan, heSoLuong, phuCap, anhDaiDien)
VALUES 
    (N'NV220001', N'Nguyễn Văn', N'An', 1, N'12323456789', '1996-01-15', N'1 Nguyễn Văn Bảo, Quận Gò Vấp', 'QLXU', N'0123456789', '2022-03-01', 3, 1, 3000000, 2.0, 500000, null),
    (N'NV220002', N'Trần Thị', N'Bình', 0, N'98762354321', '1995-05-20', N'2 Nguyễn Văn Bảo, Quận Gò Vấp', 'BPKT', N'0987654321', '2022-02-15', 2, 2, 3500000, 1.8, 400000, null),
    (N'NV220003', N'Lê Văn', N'Cường', 1, N'55665623555', '1996-12-05', N'7 Hoàng Hoa Thám, Quận Tân Bình', 'QLXU', N'0112233445', '2022-01-10', 4, 1, 3200000, 2.2, 600000, null),
    (N'NV220004', N'Phạm Thị', N'Mai', 0, N'992336999569', '1997-04-25', N'101 Nguyễn Hữu Tiến, Quận Tân Phú', 'BPKT', N'0678912345', '2022-04-05', 3, 1, 3800000, 1.6, 300000, null),
    (N'NV220005', N'Vũ Ngọc', N'Thủy', 0, N'888836188888', '2002-09-15', N'12 Nguyễn Hữu Tiến, Quận Tân Phú', 'BPKT', N'0123789456', '2022-03-15', 2, 1, 3100000, 2.4, 700000, null),
    (N'NV220006', N'Hồ Văn', N'Long', 1, N'771772737777', '2001-06-30', N'456 Tân Kỳ Tân Quý, Quận Tân Phú', 'BPNS', N'0987654321', '2022-02-15', 4, 2, 3600000, 1.9, 450000, null),
    (N'NV220007', N'Nguyễn Thị', N'Hoa', 0, N'166566666666', '2000-07-20', N'89 Trường Chinh, Quận Tân Bình', 'BPNS', N'0112233445', '2022-01-10', 3, 1, 3400000, 2.1, 550000, null),
    (N'NV220008', N'Trần Văn', N'Tùng', 1, N'444449449484', '2003-04-05', N'101 Cộng Hoà, Quận Tân Bình', 'BPKT', N'0678912345', '2022-04-05', 5, 1, 3700000, 1.7, 370000, null),
    (N'NV220009', N'Lê Thị', N'Trang', 0, N'222222202922', '1998-03-10', N'123 Đường Tây Thạnh, Quận Tân Phú', 'BPNS', N'0123789456', '2022-03-15', 6, 2, 3150000, 2.3, 630000, null),
    (N'NV220010', N'Hoàng Văn', N'Hùng', 1, N'111411511611', '1996-08-25', N'6 Lê Trọng Tấn, Quận Tân Phú', 'BPNS', N'0987654321', '2022-02-15', 4, 1, 3800000, 1.6, 370000, null),
    (N'NV230001', N'Phạm Văn', N'Đạt', 1, N'123765456789', '2000-01-17', N'123 Nguyễn Hữu Tiến, Quận Tân Phú', 'BPKT', N'0125436789', '2023-03-01', 3, 1, 3000000, 2.0, 500000, null),
    (N'NV230002', N'Nguyễn Thị', N'Mỹ', 0, N'297976554321', '1995-05-01', N'45 Nguyễn Đỗ Cung, Quận Tân Phú', 'BPKT', N'0954354321', '2023-02-15', 2, 2, 3500000, 1.8, 400000, null),
    (N'NV230003', N'Trần Văn', N'Công', 1, N'551255555555', '1993-12-09', N'79 Nguyễn Thái Sơn, Quận Gò Vấp', 'BPNS', N'0123533445', '2023-01-10', 4, 1, 3200000, 2.2, 600000, null),
    (N'NV230004', N'Lê Thị', N'Hồng', 0, N'999929996999', '1997-04-28', N'101 Lê Trọng Tấn, Quận Tân Phú', 'BPNS', N'0678967345', '2023-04-05', 3, 1, 3800000, 1.6, 300000, null),
    (N'NV230005', N'Hồ Văn', N'Nam', 1, N'888088882838', '1994-09-12', N'123 Nguyễn Thái Sơn, Quận Gò Vấp', 'QLXU', N'0123240456', '2023-03-15', 2, 1, 3100000, 2.4, 700000, null),
    (N'NV230006', N'Trần Thị', N'Linh', 0, N'700077777777', '2001-06-03', N'45 Lê Lợi, Quận Gò Vấp', 'BPNS', N'0987204321', '2023-02-15', 4, 2, 3600000, 1.9, 450000, null),
    (N'NV230007', N'Nguyễn Văn', N'Dũng', 1, N'660660626666', '2000-07-02', N'115 Quang Trung, Quận Gò Vấp', 'QLXU', N'0113673445', '2023-01-10', 3, 1, 3400000, 2.1, 550000, null),
    (N'NV230008', N'Vũ Thị', N'Yên', 0, N'444449448442', '2003-04-18', N'10 Nguyễn Văn Bảo, Quận Gò Vấp', 'BPKT', N'0678945745', '2023-04-05', 5, 1, 3700000, 1.7, 370000, null),
    (N'NV230009', N'Lê Văn', N'Đức', 1, N'222122223222', '1994-03-20', N'56 Chế Lan Viên, Quận Tân Phú', 'QLXU', N'0123079456', '2023-03-15', 6, 2, 3150000, 2.3, 630000, null),
    (N'NV230010', N'Hoàng Thị', N'Diệu', 0, N'013114111111', '2002-08-05', N'20 Nguyễn Văn Bảo, Quận Gò Vấp', 'BPKT', N'0947605021', '2023-02-01', 4, 1, 3550000, 1.8, 430000, null)
go

insert into TaiKhoan (maTK, taiKhoan, matKhau, maNV, ngayDNCuoi)
values
	(N'TK230004', '230004', CONVERT(varchar(16), HASHBYTES('MD5', '230004'), 2), N'NV230004', '03/11/2023'),
	(N'TK230010', '230010', CONVERT(varchar(16), HASHBYTES('MD5', '230010'), 2), N'NV230010', '03/11/2023'),
	(N'TK220001', '220001', CONVERT(varchar(16), HASHBYTES('MD5', '220001'), 2), N'NV220001', '03/11/2023')
go

insert into HopDong 
values 
	(N'14102301', N'Công ty Sản xuất áo sơ mi SMI', '10-14-2023', '11-13-2023', 1),
	(N'07102301', N'Cửa hàng bán áo thun Z-shirt', '10-07-2023', '11-06-2023', 1),
	(N'14082301', N'Shop bán quần jeans Jeanist', '08-14-2023', '09-13-2023', 1),
	(N'30092301', N'Shop bán quần short Short-T', '09-30-2023', '10-29-2023', 1),
	(N'15102301', N'Công ty sản xuất áo thun S-Z', '10-15-2023', '11-30-2023', 0),
	(N'15112301', N'Shop bán quần áo gen-Z', '11-15-2023', '12-22-2023', 0),
	(N'13102301', N'Công ty xuất khẩu quần kaki trắng Kaki-W', '11-13-2023', '12-11-2023', 0),
	(N'15112302', N'Cửa hàng chuyên bán quần tây Limen', '11-15-2023', '12-30-2023', 0)
go

insert into SanPham
values
	(N'1410230101', N'Áo sơ mi trắng', 10000, 2, 1, N'14102301'),
	(N'0710230101', N'Áo thun đen thời thượng', 20000, 3, 1, N'07102301'),
	(N'1408230101', N'Quần jeans màu xanh dương', 12000, 3, 1, N'14082301'),
	(N'3009230101', N'Quần short thể thao màu xanh lam', 30000, 3, 0, N'30092301')
go

insert into CongDoan (maSP, maCongDoan, tenCongDoan, soLuongSanPham, soLuongCongNhanDuKien, giaTien, trangThai, ngayBatDau, ngayKetThucDuKien, congDoanTienQuyet)
values
	('1410230101', '14102301011', N'May vá', 10000, 50, 5000, 1, '10-14-2023', '10-30-2023', ''),
	('1410230101', '14102301012', N'Đóng gói', 10000, 100, 3000, 1, '10-31-2023', '11-10-2023', '14102301011'),
	('0710230101', '07102301011', N'May vá', 20000, 200, 5000, 1, '10-07-2023', '11-04-2023', ''),
	('0710230101', '07102301012', N'Nhuộm', 20000, 100, 6000, 1, '10-07-2023', '11-04-2023', '07102301011'),
	('0710230101', '07102301013', N'Đóng gói', 20000, 200, 3000, 1, '10-07-2023', '11-04-2023', '07102301012'),
	('1408230101', '14082301011', N'May vá', 12000, 100, 4000, 1, '08-14-2023', '09-13-2023', ''),
	('1408230101', '14082301012', N'Nhuộm', 12000, 100, 6000, 1, '08-14-2023', '09-13-2023', '14082301011'),
	('1408230101', '14082301013', N'Đóng gói', 12000, 100, 4000, 1, '08-14-2023', '09-13-2023', '14082301011'),
	('3009230101', '30092301011', N'May vá', 30000, 300, 4000, 0, '09-30-2023', '10-29-2023', ''),
	('3009230101', '30092301012', N'Nhuộm', 30000, 180, 6000, 0, '09-30-2023', '10-29-2023', '30092301011'),
	('3009230101', '30092301013', N'Đóng gói', 30000, 100, 5000, 0, '09-30-2023', '10-29-2023', '30092301011')
go

INSERT INTO CongNhan (maCN, ho, ten, gioiTinh, ngaySinh, cCCD, soDienThoai, diaChi, ngayBatDauLamViec, maXuong, chuyenMon, caLamViec, phuCap, luongCoBan)
VALUES
	(N'CN220001', N'Trần', N'Quốc', 1, '08-20-1989', '111122223333', '0123456789', N'23 Đường GHI, Quận 3', '02-15-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220002', N'Phạm', N'Thịnh', 1, '09-25-1994', '444455556666', '0123456789', N'56 Đường JKL, Quận 12', '02-20-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220003', N'Lê', N'Hương', 0, '10-10-1989', '646566676869', '0909090909', N'78 Đường MNO, Quận Bình Thạnh', '02-25-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220004', N'Nguyễn', N'Thành', 1, '11-15-1994', '707172737475', '0123456789', N'90 Đường PQR, Quận Tân Bình', '03-01-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220005', N'Hồ', N'Đức', 1, '12-20-1989', '767778798081', '0909090909', N'12 Đường STU, Quận Tân Phú', '03-05-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220006', N'Võ', N'Anh', 0, '01-25-1994', '828384858687', '0123456789', N'34 Đường VWX, Quận Gò Vấp', '03-10-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220007', N'Trần', N'Mỹ', 0, '02-10-1989', '888990919293', '0909090909', N'56 Đường YZ, Quận Bình Tân', '03-15-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220008', N'Nguyễn', N'Phước', 1, '03-15-1994', '949596979899', '0123456789', N'78 Đường ABC, Quận 1', '03-20-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220009', N'Trần', N'Thùy', 0, '04-20-1989', '000102030405', '0909090909', N'90 Đường DEF, Quận 2', '03-25-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220010', N'Phạm', N'Trí', 1, '05-25-1994', '060708091011', '0123456789', N'23 Đường GHI, Quận 3', '03-30-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220011', N'Nguyễn', N'Hải', 1, '06-10-1990', '111122223333', '0123456789', N'23 Đường GHI, Quận 3', '04-05-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220012', N'Trần', N'Hương', 0, '07-15-1991', '444455556666', '0123456789', N'56 Đường JKL, Quận 12', '04-10-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220013', N'Phạm', N'Thành', 1, '08-20-1992', '646566676869', '0909090909', N'78 Đường MNO, Quận Bình Thạnh', '04-15-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220014', N'Lê', N'Anh', 0, '09-25-1993', '707172737475', '0123456789', N'90 Đường PQR, Quận Tân Bình', '04-20-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220015', N'Hồ', N'Minh', 1, '10-30-1994', '767778798081', '0909090909', N'12 Đường STU, Quận Tân Phú', '04-25-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220016', N'Võ', N'Phương', 0, '11-15-1995', '828384858687', '0123456789', N'34 Đường VWX, Quận Gò Vấp', '05-01-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220017', N'Trần', N'Hòa', 0, '12-20-1996', '888990919293', '0909090909', N'56 Đường YZ, Quận Bình Tân', '05-05-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220018', N'Nguyễn', N'Thắng', 1, '01-25-1997', '949596979899', '0123456789', N'78 Đường ABC, Quận 1', '05-10-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220019', N'Trần', N'Hà', 0, '02-10-1998', '000102030405', '0909090909', N'90 Đường DEF, Quận 2', '05-15-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN220020', N'Phạm', N'Thành', 1, '03-15-1999', '060708091011', '0123456789', N'23 Đường GHI, Quận 3', '05-20-2022', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230001', N'Trần Thị', N'Mai', 0, '05-15-1995', '202034512345', '0456789012', N'34 Đường ABC, Quận 3', '04-09-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230002', N'Phạm Văn', N'An', 1, '06-20-1990', '303045612345', '0567890123', N'56 Đường XYZ, Quận 4', '04-18-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230003', N'Lê Thị', N'Bình', 0, '07-25-1990', '404056712345', '0678901234', N'78 Đường KLM, Quận 5', '05-20-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230004', N'Nguyễn Văn', N'Hào', 1, '09-30-1995', '505067812345', '0789012345', N'90 Đường EFG, Quận 6', '05-23-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230005', N'Hồ Thị', N'Dương', 0, '05-09-1988', '606078912345', '0890123456', N'12 Đường HIJ, Quận 7', '06-25-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230006', N'Võ Văn', N'Tuấn', 1, '10-10-1992', '707089012345', '0901234567', N'23 Đường KLM, Quận 8', '07-28-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230007', N'Trần Thị', N'Hoài', 0, '11-15-1993', '808091123456', '0123456789', N'45 Đường XYZ, Quận 9', '07-30-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230008', N'Phạm Văn', N'Trí', 1, '12-20-1998', '909102234567', '0234567890', N'67 Đường EFG, Quận 10', '08-10-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230009', N'Lê Thị', N'Thảo', 0, '01-25-1990', '101112345678', '0345678901', N'89 Đường HIJ, Quận 11', '08-10-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230010', N'Nguyễn Văn', N'Thành', 1, '10-25-1990', '303045612345', '0567890123', N'56 Đường XYZ, Quận 4', '04-10-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230011', N'Phạm Thị', N'Hương', 0, '11-15-1985', '404056712345', '0678901234', N'78 Đường KLM, Quận 5', '04-15-2023', 'DG1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230012', N'Lê Văn', N'An', 1, '12-10-1990', '505067812345', '0789012345', N'90 Đường EFG, Quận 6', '04-20-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230013', N'Nguyễn Thị', N'Mỹ', 0, '01-05-1995', '606078912345', '0890123456', N'12 Đường HIJ, Quận 7', '04-25-2023', 'DG1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230014', N'Hồ Văn', N'Đức', 1, '02-20-1988', '707089012345', '0901234567', N'23 Đường KLM, Quận 8', '04-30-2023', 'DG1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230015', N'Võ Thị', N'Thùy', 0, '03-25-1983', '808091123456', '0123456789', N'45 Đường XYZ, Quận 9', '05-05-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230016', N'Trần Văn', N'Quý', 1, '04-10-1998', '909102234567', '0234567890', N'67 Đường EFG, Quận 10', '05-10-2023', 'DG1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230017', N'Phạm Thị', N'Hà', 0, '05-25-1980', '101112345678', '0345678901', N'89 Đường HIJ, Quận 11', '05-15-2023', 'DG1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230018', N'Lê Thị', N'Quỳnh', 0, '06-10-1983', '121314567890', '0456789012', N'34 Đường ABC, Quận 3', '05-20-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230019', N'Nguyễn Văn', N'Thịnh', 1, '07-15-1992', '111122223333', '0987654321', N'56 Đường GHI, Quận 12', '06-01-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230020', N'Trần Thị', N'Thủy', 0, '08-20-1987', '444455556666', '0123456789', N'78 Đường JKL, Quận Bình Thạnh', '06-05-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230021', N'Hồ Văn', N'Hải', 1, '09-25-1992', '777788889999', '0909090909', N'90 Đường MNO, Quận Tân Bình', '06-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230022', N'Nguyễn Thị', N'Trinh', 0, '10-10-1987', '101112131415', '0123456789', N'12 Đường PQR, Quận Tân Phú', '06-15-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230023', N'Lê Văn', N'Tuấn', 1, '11-15-1992', '161718192021', '0909090909', N'34 Đường STU, Quận Gò Vấp', '06-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230024', N'Hồ Thị', N'Diệu', 0, '12-20-1987', '222324252627', '0123456789', N'56 Đường VWX, Quận Bình Tân', '06-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230025', N'Võ Văn', N'Phúc', 1, '01-25-1992', '282930313233', '0909090909', N'78 Đường YZ, Quận Thủ Đức', '07-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230026', N'Trần Thị', N'Hương', 0, '02-10-1987', '343536373839', '0123456789', N'90 Đường ABC, Quận 1', '07-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230027', N'Phạm Văn', N'Trọng', 1, '03-15-1992', '404142434445', '0909090909', N'23 Đường DEF, Quận 2', '07-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230028', N'Lê Thị', N'Thảo', 0, '04-20-1987', '464748495051', '0123456789', N'45 Đường GHI, Quận 3', '07-15-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
	(N'CN230029', N'Trần Thị', N'Thùy', 0, '01-25-1994', '000102030405', '0909090909', N'23 Đường DEF, Quận 2', '08-25-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230030', N'Phạm Văn', N'Trí', 1, '02-10-1989', '060708091011', '0123456789', N'45 Đường GHI, Quận 3', '08-30-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230031', N'Lê Thị', N'Thảo', 0, '03-15-1994', '121314151617', '0909090909', N'56 Đường JKL, Quận 4', '09-01-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230032', N'Nguyễn Văn', N'Thắng', 1, '04-20-1989', '181920212223', '0123456789', N'78 Đường MNO, Quận 5', '09-05-2023', 'NH2', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230033', N'Trần Thị', N'Hương', 0, '05-25-1994', '242526272829', '0909090909', N'90 Đường PQR, Quận 6', '09-10-2023', 'NH1', N'Nhuộm', 1, 500000, 2000000),
    (N'CN230034', N'Hồ Văn', N'Quân', 1, '06-10-1989', '303132333435', '0123456789', N'12 Đường STU, Quận 7', '09-15-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230035', N'Nguyễn Thị', N'Thủy', 0, '07-15-1994', '363738394041', '0909090909', N'34 Đường VWX, Quận 8', '09-20-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230036', N'Lê Văn', N'Thành', 1, '08-20-1989', '424344454647', '0123456789', N'56 Đường YZ, Quận 9', '09-25-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230037', N'Hồ Thị', N'Mai', 0, '09-25-1994', '484950515253', '0909090909', N'78 Đường ABC, Quận 10', '09-30-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230038', N'Võ Văn', N'Thắng', 1, '10-10-1989', '545556575859', '0123456789', N'90 Đường DEF, Quận 11', '10-01-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230039', N'Nguyễn Thị', N'Thảo', 0, '11-15-1994', '606162636465', '0909090909', N'23 Đường GHI, Quận 12', '10-05-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230040', N'Trần Văn', N'Quân', 1, '12-20-1989', '666768697071', '0123456789', N'45 Đường JKL, Quận Bình Thạnh', '04-10-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230041', N'Nguyễn Văn', N'Hải', 1, '05-25-1994', '111122223333', '0987654321', N'56 Đường GHI, Quận 12', '04-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230042', N'Trần Thị', N'Trâm', 0, '06-10-1989', '444455556666', '0123456789', N'78 Đường JKL, Quận Bình Thạnh', '04-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230043', N'Hồ Thị', N'Hoàng', 0, '07-15-1994', '646566676869', '0909090909', N'90 Đường MNO, Quận Tân Bình', '04-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230044', N'Nguyễn Văn', N'Thế', 1, '08-20-1989', '707172737475', '0123456789', N'12 Đường PQR, Quận Tân Phú', '04-30-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230045', N'Lê Thị', N'Phương', 0, '09-25-1994', '767778798081', '0909090909', N'34 Đường STU, Quận Gò Vấp', '05-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230046', N'Hồ Văn', N'Đức', 1, '10-10-1989', '828384858687', '0123456789', N'56 Đường VWX, Quận Bình Tân', '05-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230047', N'Nguyễn Thị', N'Yến', 0, '11-15-1994', '888990919293', '0909090909', N'78 Đường YZ, Quận Thủ Đức', '05-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230048', N'Võ Văn', N'Hào', 1, '12-20-1989', '949596979899', '0123456789', N'90 Đường ABC, Quận 1', '05-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230049', N'Trần Thị', N'Thảo', 0, '01-25-1994', '000102030405', '0909090909', N'23 Đường DEF, Quận 2', '05-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230050', N'Phạm Văn', N'Thọ', 1, '02-10-1989', '060708091011', '0123456789', N'45 Đường GHI, Quận 3', '06-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230051', N'Lê Thị', N'Ngọc', 0, '03-15-1994', '121314151617', '0909090909', N'56 Đường JKL, Quận 4', '06-30-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230052', N'Nguyễn Văn', N'Bảo', 1, '04-20-1989', '181920212223', '0123456789', N'78 Đường MNO, Quận 5', '06-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230053', N'Trần Thị', N'Hương', 0, '05-25-1994', '242526272829', '0909090909', N'90 Đường PQR, Quận 6', '06-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230054', N'Hồ Văn', N'Thạch', 1, '06-10-1989', '303132333435', '0123456789', N'12 Đường STU, Quận 7', '06-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230055', N'Nguyễn Thị', N'Thủy', 0, '07-15-1994', '363738394041', '0909090909', N'34 Đường VWX, Quận 8', '06-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230056', N'Lê Văn', N'Thành', 1, '08-20-1989', '424344454647', '0123456789', N'56 Đường YZ, Quận 9', '06-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230057', N'Hồ Thị', N'Mai', 0, '09-25-1994', '484950515253', '0909090909', N'78 Đường ABC, Quận 10', '05-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230058', N'Võ Văn', N'Thắng', 1, '10-10-1989', '545556575859', '0123456789', N'90 Đường DEF, Quận 11', '01-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230059', N'Nguyễn Thị', N'Thảo', 0, '11-15-1994', '606162636465', '0909090909', N'23 Đường GHI, Quận 12', '01-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230060', N'Trần Văn', N'Quân', 1, '12-20-1989', '666768697071', '0123456789', N'45 Đường JKL, Quận Bình Thạnh', '01-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230061', N'Nguyễn Văn', N'Thắng', 1, '02-10-1989', '727374757677', '0123456789', N'67 Đường MNO, Quận Tân Bình', '01-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230062', N'Trần Thị', N'Hương', 0, '03-15-1994', '787980818283', '0909090909', N'89 Đường PQR, Quận Tân Phú', '01-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230063', N'Hồ Văn', N'Anh', 1, '04-20-1989', '848586878889', '0123456789', N'12 Đường STU, Quận Gò Vấp', '01-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230064', N'Nguyễn Thị', N'Mỹ', 0, '05-25-1994', '909192939495', '0909090909', N'67 Đường XYZ, Quận Bình Tân', '01-30-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230065', N'Võ Văn', N'Thành', 1, '06-10-1989', '919293949596', '0123456789', N'78 Đường ABC, Quận 1', '02-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230066', N'Nguyễn Thị', N'Tâm', 0, '07-15-1994', '959697989900', '0909090909', N'89 Đường DEF, Quận 2', '02-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230067', N'Trần Văn', N'Quốc', 1, '08-20-1989', '000102030405', '0123456789', N'23 Đường GHI, Quận 3', '02-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230068', N'Nguyễn Văn Anh', N'Thành', 1, '09-25-1994', '111122223333', '0987654321', N'56 Đường GHI, Quận 12', '02-20-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230069', N'Trần Thị Bích', N'Thủy', 0, '10-10-1989', '444455556666', '0123456789', N'78 Đường JKL, Quận Bình Thạnh', '02-25-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230070', N'Hồ Thị Hoàng', N'Dương', 0, '11-15-1994', '646566676869', '0909090909', N'90 Đường MNO, Quận Tân Bình', '03-01-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230071', N'Nguyễn Văn Hoàng', N'Thành', 1, '12-20-1989', '707172737475', '0123456789', N'12 Đường PQR, Quận Tân Phú', '03-05-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230072', N'Lê Thị Minh', N'Bình', 0, '01-25-1994', '767778798081', '0909090909', N'34 Đường STU, Quận Gò Vấp', '03-10-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230073', N'Hồ Văn Đức', N'Anh', 1, '02-10-1989', '828384858687', '0123456789', N'56 Đường VWX, Quận Bình Tân', '03-15-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230074', N'Nguyễn Thị Ngọc', N'Mỹ', 0, '03-15-1994', '888990919293', '0909090909', N'78 Đường YZ, Quận Thủ Đức', '03-20-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230075', N'Võ Văn Phước', N'Thành', 1, '04-20-1989', '949596979899', '0123456789', N'90 Đường ABC, Quận 1', '03-25-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230076', N'Trần Nguyễn Thị', N'Thùy', 0, '05-25-1994', '000102030405', '0909090909', N'23 Đường DEF, Quận 2', '03-30-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230077', N'Phạm Văn Minh', N'Trí', 1, '06-10-1989', '060708091011', '0123456789', N'45 Đường GHI, Quận 3', '04-01-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230078', N'Lê Thị Lan', N'Thảo', 0, '07-15-1994', '121314151617', '0909090909', N'56 Đường JKL, Quận 4', '04-05-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230079', N'Nguyễn Văn Nam', N'Thắng', 1, '08-20-1989', '181920212223', '0123456789', N'78 Đường MNO, Quận 5', '04-10-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230080', N'Trần Thị Ngọc', N'Hương', 0, '09-25-1994', '242526272829', '0909090909', N'90 Đường PQR, Quận 6', '04-15-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230081', N'Hồ Văn Nam', N'Quân', 1, '10-10-1989', '303132333435', '0123456789', N'12 Đường STU, Quận 7', '04-20-2023', 'DG1', N'Đóng gói', 1, 500000, 2000000),
    (N'CN230082', N'Trần', N'Quốc', 1, '08-20-1989', '111122223333', '0987654321', N'23 Đường GHI, Quận 3', '02-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230083', N'Phạm', N'Thịnh', 1, '09-25-1994', '444455556666', '0123456789', N'56 Đường JKL, Quận 12', '02-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230084', N'Lê', N'Hương', 0, '10-10-1989', '646566676869', '0909090909', N'78 Đường MNO, Quận Bình Thạnh', '02-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230085', N'Nguyễn', N'Thành', 1, '11-15-1994', '707172737475', '0123456789', N'90 Đường PQR, Quận Tân Bình', '03-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230086', N'Hồ', N'Đức', 1, '12-20-1989', '767778798081', '0909090909', N'12 Đường STU, Quận Tân Phú', '03-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230087', N'Võ', N'Anh', 0, '01-25-1994', '828384858687', '0123456789', N'34 Đường VWX, Quận Gò Vấp', '03-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230088', N'Trần', N'Mỹ', 0, '02-10-1989', '888990919293', '0909090909', N'56 Đường YZ, Quận Bình Tân', '03-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230089', N'Nguyễn', N'Phước', 1, '03-15-1994', '949596979899', '0123456789', N'78 Đường ABC, Quận 1', '03-20-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230090', N'Trần', N'Thùy', 0, '04-20-1989', '000102030405', '0909090909', N'90 Đường DEF, Quận 2', '03-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230091', N'Phạm', N'Trí', 1, '05-25-1994', '060708091011', '0123456789', N'23 Đường GHI, Quận 3', '03-30-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230092', N'Lê', N'Thảo', 0, '06-10-1989', '121314151617', '0909090909', N'45 Đường JKL, Quận 4', '04-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230093', N'Nguyễn', N'Thắng', 1, '07-15-1994', '181920212223', '0123456789', N'56 Đường MNO, Quận 5', '04-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230094', N'Trần', N'Hương', 0, '08-20-1989', '242526272829', '0909090909', N'78 Đường PQR, Quận 6', '04-10-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230095', N'Hồ', N'Quân', 1, '09-25-1994', '303132333435', '0123456789', N'90 Đường STU, Quận 7', '04-15-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230096', N'Nguyễn', N'Hương', 0, '10-10-1989', '363738394041', '0909090909', N'12 Đường VWX, Quận 8', '04-20-2023', 'MA1', 'May', 1, 500000, 2000000),
	(N'CN230097', N'Lê', N'Thành', 1, '11-15-1994', '424344454647', '0123456789', N'34 Đường YZ, Quận 9', '04-25-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230098', N'Hồ', N'Nam', 1, '12-20-1989', '484950515253', '0909090909', N'56 Đường ABC, Quận 10', '05-01-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230099', N'Võ', N'Thịnh', 1, '01-25-1994', '545556575859', '0123456789', N'78 Đường DEF, Quận 11', '05-05-2023', 'MA1', 'May', 1, 500000, 2000000),
    (N'CN230100', N'Trần', N'Đức', 1, '02-10-1989', '606162636465', '0909090909', N'90 Đường GHI, Quận 12', '05-10-2023', 'MA1', 'May', 1, 500000, 2000000)
go


select bpccn.* from CongNhan cn join Xuong x
on cn.maXuong = x.maXuong left join BangPhanCongCN bpccn
on cn.maCN = bpccn.maCN
where maPCCN is not null and maCD = '07102301013'

select * from BangPhanCongCN where maCD = '30092301011'

insert into BangLuongNhanVien (maLuongNV,maNV,thang,nam,soNgayDiLam,soNgayNghiKhongPhep,tienPhat,bhxh,luongTong)
values
('MT21236134','NV220003',6,2022,20,10,20000,10000,400000000),
('MT21989723','NV220003',4,2022,20,10,20000,10000,400000000),
('MT25632133','NV220010',7,2022,20,10,20000,10000,400000000);
go

select distinct thang, nam, maBP from BangLuongNhanVien join NhanVien on BangLuongNhanVien.maNV = NhanVien.maNV 