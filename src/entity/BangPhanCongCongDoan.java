package entity;

import java.time.LocalDate;

public class BangPhanCongCongDoan {
	private String maPC;
	private String trangThai;
	private LocalDate ngayPhanCong;
	private int soLuongSanPham;
	private CongNhan congNhan;
	private CongDoan congDoan;

	public BangPhanCongCongDoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangPhanCongCongDoan(String maPC, String trangThai, LocalDate ngayPhanCong, int soLuongSanPham,
			CongNhan congNhan, CongDoan congDoan) {
		super();
		this.maPC = maPC;
		this.trangThai = trangThai;
		this.ngayPhanCong = ngayPhanCong;
		this.soLuongSanPham = 0;
		this.congNhan = congNhan;
		this.congDoan = congDoan;
	}

	public BangPhanCongCongDoan(String maPC) {
		super();
		this.maPC = maPC;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDate getNgayPhanCong() {
		return ngayPhanCong;
	}

	public void setNgayPhanCong(LocalDate ngayPhanCong) {
		this.ngayPhanCong = ngayPhanCong;
	}

	public int getSoLuongSanPham() {
		return soLuongSanPham;
	}

	public void setSoLuongSanPham(int soLuongSanPham) {
		this.soLuongSanPham = soLuongSanPham;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public CongDoan getCongDoan() {
		return congDoan;
	}

	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}

	public String getMaPC() {
		return maPC;
	}

	@Override
	public String toString() {
		return "BangPhanCongCongDoan [maPC=" + maPC + ", trangThai=" + trangThai + ", ngayPhanCong=" + ngayPhanCong
				+ ", soLuongSanPham=" + soLuongSanPham + ", congNhan=" + congNhan + ", congDoan=" + congDoan + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPC == null) ? 0 : maPC.hashCode());
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
		BangPhanCongCongDoan other = (BangPhanCongCongDoan) obj;
		if (maPC == null) {
			if (other.maPC != null)
				return false;
		} else if (!maPC.equals(other.maPC))
			return false;
		return true;
	}
}
