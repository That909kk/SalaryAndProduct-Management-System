package entity;

import java.time.LocalDate;

public class CongDoan {
	private String maCongDoan;
	private String tenCongDoan;
	private int soLuongSanPham;
	private double giaTien;
	private LocalDate ngayBatDau;
	private String trangThai;
	private String congDoanTienQuyet;
	private SanPham sanPham;

	public CongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CongDoan(String maCongDoan, String tenCongDoan, int soLuongSanPham, double giaTien, LocalDate ngayBatDau,
			String trangThai, String congDoanTienQuyet, SanPham sanPham) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.soLuongSanPham = soLuongSanPham;
		this.giaTien = giaTien;
		this.ngayBatDau = ngayBatDau;
		this.trangThai = trangThai;
		this.congDoanTienQuyet = congDoanTienQuyet;
		this.sanPham = sanPham;
	}

	public CongDoan(String maCongDoan) {
		super();
		this.maCongDoan = maCongDoan;
	}

	public String getTenCongDoan() {
		return tenCongDoan;
	}

	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}

	public int getSoLuongSanPham() {
		return soLuongSanPham;
	}

	public void setSoLuongSanPham(int soLuongSanPham) {
		this.soLuongSanPham = soLuongSanPham;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getCongDoanTienQuyet() {
		return congDoanTienQuyet;
	}

	public void setCongDoanTienQuyet(String congDoanTienQuyet) {
		this.congDoanTienQuyet = congDoanTienQuyet;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public String getMaCongDoan() {
		return maCongDoan;
	}

	@Override
	public String toString() {
		return "CongDoan [maCongDoan=" + maCongDoan + ", tenCongDoan=" + tenCongDoan + ", soLuongSanPham="
				+ soLuongSanPham + ", giaTien=" + giaTien + ", ngayBatDau=" + ngayBatDau + ", trangThai=" + trangThai
				+ ", congDoanTienQuyet=" + congDoanTienQuyet + ", sanPham=" + sanPham + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCongDoan == null) ? 0 : maCongDoan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongDoan other = (CongDoan) obj;
		if (maCongDoan == null) {
			if (other.maCongDoan != null)
				return false;
		} else if (!maCongDoan.equals(other.maCongDoan))
			return false;
		return true;
	}
}
