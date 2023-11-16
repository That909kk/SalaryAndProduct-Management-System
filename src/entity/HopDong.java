package entity;

import java.time.LocalDate;

public class HopDong {
	private String maHopDong;
	private String tenDoiTac;
	private LocalDate ngayKy;
	private LocalDate ngayThanhLyHopDong;
	private boolean trangThai;

	public HopDong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HopDong(String maHopDong, String tenDoiTac, LocalDate ngayKy, LocalDate ngayThanhLyHopDong, boolean trangThai) {
		super();
		this.maHopDong = maHopDong;
		this.tenDoiTac = tenDoiTac;
		this.ngayKy = ngayKy;
		this.ngayThanhLyHopDong = ngayThanhLyHopDong;
		this.trangThai = trangThai;
	}

	public HopDong(String maHopDong) {
		super();
		this.maHopDong = maHopDong;
	}

	public String getTenDoiTac() {
		return tenDoiTac;
	}

	public void setTenDoiTac(String tenHopDong) {
		this.tenDoiTac = tenHopDong;
	}

	public LocalDate getNgayKy() {
		return ngayKy;
	}

	public void setNgayKy(LocalDate ngayKy) {
		this.ngayKy = ngayKy;
	}

	public LocalDate getNgayThanhLyHopDong() {
		return ngayThanhLyHopDong;
	}

	public void setNgayThanhLyHopDong(LocalDate ngayThanhLyHopDong) {
		this.ngayThanhLyHopDong = ngayThanhLyHopDong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaHopDong() {
		return maHopDong;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHopDong == null) ? 0 : maHopDong.hashCode());
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
		HopDong other = (HopDong) obj;
		if (maHopDong == null) {
			if (other.maHopDong != null)
				return false;
		} else if (!maHopDong.equals(other.maHopDong))
			return false;
		return true;
	}
}
