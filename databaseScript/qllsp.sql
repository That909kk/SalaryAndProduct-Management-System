use master
go

drop database QLLSP
go

CREATE DATABASE QLLSP ON
(NAME = QQLSP_dat,
    FILENAME = 'T:\QLLLSP\QLLSP.mdf',
    SIZE = 10 MB,
    MAXSIZE = 512 MB,
    FILEGROWTH = 5 MB)
LOG ON
(NAME = QQLSP_log,
    FILENAME = 'T:\QLLLSP\QLLSP.ldf',
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
	ho nvarchar (100) not null,
	ten nvarchar (10) not null,
	gioiTinh bit,
	cCCD nvarchar(12) not null,
	diaChi nvarchar (100),
	soDienThoai nvarchar(11),
	chuyenMon nvarchar(20),
	caLamViec int,
	phuCap float,
	ngayBatDauLamViec date not null,
	maXuong nvarchar(3),
	constraint FK_Xuong foreign key (maXuong)
	references Xuong(maXuong)
)

create table BangLuongCongNhan (
	maLuongCN nvarchar(12) not null primary key,
	maCN nvarchar(8),
	thang int,
	nam int,
	sanLuong int,
	soNgayNghiKhongPhep int,
	tienPhat float,
	luongTong float,
	constraint FK_BLCN foreign key (maCN)
	references CongNhan(maCN)
)

create table ChamCongCongNhan (
	maCC nvarchar(12) not null primary key,
	ngayCham date not null,
	maCN nvarchar(8),
	soGioTangCa int,
	sanLuong int,
	ghiChu nvarchar(100),
	constraint FK_CCCN foreign key (maCN)
	references CongNhan(maCN)
)

create table hopDong (
	maHopDong nvarchar(5) not null primary key,
	tenHopDong nvarchar(20),
	ngayThanhLyHopDong date,
	ngayKi date
)

create table SanPham (
	maSP nvarchar(5) not null primary key,
	tenSP nvarchar (20) not null,
	soLuong int,
	soLuongCongDoan int,
	maHopDong nvarchar(5),
	constraint FK_HD foreign key (maHopDong)
	references HopDong(maHopDong)
)

create table CongDoan (
	maCongDoan nvarchar(12) not null primary key,
	tenCongDoan nvarchar(20),
	soLuongSanPham int not null,
	trangThai nvarchar(12),
	giaTien float,
	ngayBatDau date,
	congDoanTienQuyet nvarchar(12),
	maSP nvarchar(5),
	constraint FK_SP foreign key (maSP)
	references SanPham(maSP)
)

create table BangPhanCongCN (
	maPCCN nvarchar(11) not null primary key,
	trangThai nvarchar(20),
	ngayPhanCong date,
	soLuongSP int,
	maCN nvarchar(8),
	constraint FK_PCCN foreign key (maCN)
	references CongNhan(maCN),
	maCD nvarchar(12),
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
	ho nvarchar (100) not null,
	ten nvarchar (10) not null,
	gioiTinh bit,
	soDienThoai nvarchar(11),
	diaChi nvarchar (100),
	cCCD nvarchar(12) not null,
	ngaySinh date,
	ngayBatDauLamViec date not null,
	caLamViec int,
	luongCoBan float,
	thangBacLuong int,
	heSoLuong float,
	phuCap float,
	maBP nvarchar(4),
	constraint FK_BP foreign key (maBP)
	references BoPhan(maBoPhan)
)

create table BangLuongNhanVien (
	maLuongNV nvarchar(12) not null primary key,
	maNV nvarchar(8),
	thang int,
	nam int,
	soNgayDiLam date,
	soNgayNghiKhongPhep int,
	tienPhat float,
	luongTong float,
	constraint FK_BLNV foreign key (maNV)
	references NhanVien(maNV)
)

create table ChamCongNhanVien (
	maChamCong nvarchar(12) not null primary key,
	maNV nvarchar(8),
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

create table BangPhanCongXuong (
	maPCX nvarchar(11) not null primary key,
	ngayNhanXuong date,
	maXuong nvarchar(3),
	constraint FK_XuongPC foreign key (maXuong)
	references Xuong(maXuong),
	maNV nvarchar(8),
	constraint FK_NVPC foreign key (maNV)
	references NhanVien(maNV)
)

create table TaiKhoan (
	maTK nvarchar (2) not null primary key,
	taiKhoan nvarchar(20),
	matKhau nvarchar(16),
	maNV nvarchar(8),
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
	('NH2', N'Xưởng nhuộm 1', ''),
	('GC1', N'Xưởng gia công 1', ''),
	('DG1', N'Xưởng đóng gói 1', '')
go

