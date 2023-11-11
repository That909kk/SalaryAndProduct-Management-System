package entity;

<<<<<<< HEAD
=======
import java.time.LocalDate;

>>>>>>> main
public class TaiKhoan {
	private String maTK;
	private String taiKhoan;
	private String matKhau;
	private NhanVien nv;
<<<<<<< HEAD
=======
	private LocalDate ngayDNCuoi;
>>>>>>> main

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD
	public TaiKhoan(String maTK, String taiKhoan, String matKhau, NhanVien nv) {
=======
	public TaiKhoan(String maTK, String taiKhoan, String matKhau, NhanVien nv, LocalDate ngayDNCuoi) {
>>>>>>> main
		super();
		this.maTK = maTK;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.nv = nv;
<<<<<<< HEAD
=======
		this.ngayDNCuoi = ngayDNCuoi;
>>>>>>> main
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

<<<<<<< HEAD
	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", nv=" + nv + "]";
=======
	public LocalDate getNgayDNCuoi() {
		return ngayDNCuoi;
	}

	public void setNgayDNCuoi(LocalDate ngayDNCuoi) {
		this.ngayDNCuoi = ngayDNCuoi;
	}

	@Override
	public String toString() {
		return "";
>>>>>>> main
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
