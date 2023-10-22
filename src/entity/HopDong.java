package entity;

import java.time.LocalDate;

public class HopDong {
	private String maHopDong;
	private String tenHopDong;
	private LocalDate ngayKy;
	private LocalDate ngayThanhLyHopDong;

	public HopDong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HopDong(String maHopDong, String tenHopDong, LocalDate ngayKy, LocalDate ngayThanhLyHopDong) {
		super();
		this.maHopDong = maHopDong;
		this.tenHopDong = tenHopDong;
		this.ngayKy = ngayKy;
		this.ngayThanhLyHopDong = ngayThanhLyHopDong;
	}

	public HopDong(String maHopDong) {
		super();
		this.maHopDong = maHopDong;
	}

	public String getTenHopDong() {
		return tenHopDong;
	}

	public void setTenHopDong(String tenHopDong) {
		this.tenHopDong = tenHopDong;
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

	public String getMaHopDong() {
		return maHopDong;
	}

	@Override
	public String toString() {
		return "HopDong [maHopDong=" + maHopDong + ", tenHopDong=" + tenHopDong + ", ngayKy=" + ngayKy
				+ ", ngayThanhLyHopDong=" + ngayThanhLyHopDong + "]";
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
