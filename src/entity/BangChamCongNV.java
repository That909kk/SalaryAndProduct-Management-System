package entity;

import java.sql.Date;
import java.time.LocalDate;

public class BangChamCongNV {
	private String maCCNV;
	private NhanVien nv;
	private Date ngayCham;
	private int soGioTangCa;
	private int caLam;
	private boolean coPhep;
	private boolean vangMat;
	private String ghiChu;

	public BangChamCongNV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangChamCongNV(String maCCNV, NhanVien nv, Date ngayCham, int soGioTangCa, int caLam, boolean coPhep, boolean vangMat, String ghiChu) {
		super();
		this.maCCNV = maCCNV;
		this.nv = nv;
		this.ngayCham = ngayCham;
		this.soGioTangCa = soGioTangCa;
		this.caLam = caLam;

		this.coPhep = coPhep;
		this.vangMat = vangMat;
		this.ghiChu = ghiChu;
	}

	public BangChamCongNV(String maCCNV) {
		super();
		this.maCCNV = maCCNV;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public Date getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(Date date) {
		this.ngayCham = date;
	}

	public int getSoGioTangCa() {
		return soGioTangCa;
	}

	public void setSoGioTangCa(int soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}

	public int getCaLam() {
		return caLam;
	}

	public void setCaLam(int caLam) {
		this.caLam = caLam;
	}


	public boolean isCoPhep() {
		return coPhep;
	}

	public void setCoPhep(boolean coPhep) {
		this.coPhep = coPhep;
	}

	public boolean isVangMat() {
		return vangMat;
	}

	public void setVangMat(boolean vangMat) {
		this.vangMat = vangMat;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public void setMaCCNV(String maCCNV) {
		this.maCCNV = maCCNV; 
	}
	
	public String getMaCCNV() {
		return maCCNV;
	}

	@Override
	public String toString() {
		return "BangLuongNhanVien [maLuongNV=" + maCCNV + ", nv=" + nv + ", ngayCham=" + ngayCham + ", soGioTangCa="
				+ soGioTangCa + ", caLam=" + caLam + ", coMat=" + ", coPhep=" + coPhep + ", vangMat=" + vangMat
				+ ", ghiChu=" + ghiChu + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCCNV == null) ? 0 : maCCNV.hashCode());
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
		BangChamCongNV other = (BangChamCongNV) obj;
		if (maCCNV == null) {
			if (other.maCCNV != null)
				return false;
		} else if (!maCCNV.equals(other.maCCNV))
			return false;
		return true;
	}
}
