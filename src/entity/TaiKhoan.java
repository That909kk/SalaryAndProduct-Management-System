package entity;

import java.time.LocalDate;

public class TaiKhoan {
	private String maTK;
	private String taiKhoan;
	private String matKhau;
	private NhanVien nv;
	private LocalDate ngayDNCuoi;

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaiKhoan(String maTK, String taiKhoan, String matKhau, NhanVien nv, LocalDate ngayDNCuoi) {
		super();
		this.maTK = maTK;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.nv = nv;
		this.ngayDNCuoi = ngayDNCuoi;
	}

	public TaiKhoan(String maTK) {
		super();
		this.maTK = maTK;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getMaTK() {
		return maTK;
	}

	public LocalDate getNgayDNCuoi() {
		return ngayDNCuoi;
	}

	public void setNgayDNCuoi(LocalDate ngayDNCuoi) {
		this.ngayDNCuoi = ngayDNCuoi;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTK == null) ? 0 : maTK.hashCode());
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
		TaiKhoan other = (TaiKhoan) obj;
		if (maTK == null) {
			if (other.maTK != null)
				return false;
		} else if (!maTK.equals(other.maTK))
			return false;
		return true;
	}
}
