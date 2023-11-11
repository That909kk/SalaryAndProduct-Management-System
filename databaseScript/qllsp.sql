use master
go

drop database QLLSP
go

CREATE DATABASE QLLSP ON
(NAME = QQLSP_dat,
    FILENAME = 'D:\HK5\PTUD\QLLLSP\QLLSP.mdf',
    SIZE = 10 MB,
    MAXSIZE = 512 MB,
    FILEGROWTH = 5 MB)
LOG ON
(NAME = QQLSP_log,
    FILENAME = 'D:\HK5\PTUD\QLLLSP\QLLSP.ldf',
    SIZE = 5 MB,
    MAXSIZE = 25 MB,
    FILEGROWTH = 5 MB);
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

create table hopDong (
	maHopDong nvarchar(6) not null primary key,
	tenDoiTac nvarchar(30),
	ngayThanhLyHopDong date,
	ngayKi date
)

create table SanPham (
	maSP nvarchar(11) not null primary key,
	tenSP nvarchar (50) not null,
	soLuong int,
	soLuongCongDoan int,
	maHopDong nvarchar(6) not null,
	constraint FK_HD foreign key (maHopDong)
	references HopDong(maHopDong)
)

create table CongDoan (
	maCongDoan nvarchar(10) not null primary key,
	tenCongDoan nvarchar(20),
	soLuongSanPham int not null,
	trangThai nvarchar(12),
	giaTien float,
	ngayBatDau date,
	ngayKetThucDuKien date,
	congDoanTienQuyet nvarchar(12),
	maSP nvarchar(11) not null,
	constraint FK_SP foreign key (maSP)
	references SanPham(maSP)
)

create table BangPhanCongCN (
	maPCCN nvarchar(11) not null primary key,
	trangThai bit,
	ngayPhanCong date,
	soLuongSP int,
	maCN nvarchar(8),
	constraint FK_PCCN foreign key (maCN)
	references CongNhan(maCN),
	maCD nvarchar(10),
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
	soNgayDiLam date,
	soNgayNghiKhongPhep int,
	tienPhat float,
	luongTong float,
	constraint FK_BLNV foreign key (maNV)
	references NhanVien(maNV)
)

create table BangChamCongNhanVien (
	maChamCong nvarchar(12) not null primary key,
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
	(N'TK220001', '220001', CONVERT(varchar(16), HASHBYTES('MD5', '220001'), 2), N'NV230001', '03/11/2023')
go

select * from TaiKhoan