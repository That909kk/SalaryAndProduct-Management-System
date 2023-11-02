package entity;

import java.time.LocalDate;

public class BangPhanCongXuong {
	private String maPCX;
	private LocalDate ngayNhanXuong;
	private Xuong xuong;
	private NhanVien nv;

	public BangPhanCongXuong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangPhanCongXuong(String maPCX, LocalDate ngayNhanXuong, Xuong xuong, NhanVien nv) {
		super();
		this.maPCX = maPCX;
		this.ngayNhanXuong = ngayNhanXuong;
		this.xuong = xuong;
		this.nv = nv;
	}

	public BangPhanCongXuong(String maPCX) {
		super();
		this.maPCX = maPCX;
	}

	public LocalDate getNgayNhanXuong() {
		return ngayNhanXuong;
	}

	public void setNgayNhanXuong(LocalDate ngayNhanXuong) {
		this.ngayNhanXuong = ngayNhanXuong;
	}

	public Xuong getXuong() {
		return xuong;
	}

	public void setXuong(Xuong xuong) {
		this.xuong = xuong;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getMaPCX() {
		return maPCX;
	}

	@Override
	public String toString() {
		return "BangPhanCongXuong [maPCX=" + maPCX + ", ngayNhanXuong=" + ngayNhanXuong + ", xuong=" + xuong + ", nv="
				+ nv + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPCX == null) ? 0 : maPCX.hashCode());
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
		BangPhanCongXuong other = (BangPhanCongXuong) obj;
		if (maPCX == null) {
			if (other.maPCX != null)
				return false;
		} else if (!maPCX.equals(other.maPCX))
			return false;
		return true;
	}
}
