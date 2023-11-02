package entity;

public class BangLuongNV {
	private String maLuongNV;
	private NhanVien nv;
	private int thang;
	private int nam;
	private int soNgayDiLam;
	private int soNgayNghiKhongPhep;
	private double tienPhat;

	public BangLuongNV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangLuongNV(String maLuongNV) {
		super();
		this.maLuongNV = maLuongNV;
	}

	public BangLuongNV(String maLuongNV, NhanVien nv, int thang, int nam, int soNgayDiLam, int soNgayNghiKhongPhep,
			double tienPhat) {
		super();
		this.maLuongNV = maLuongNV;
		this.nv = nv;
		this.thang = thang;
		this.nam = nam;
		this.soNgayDiLam = soNgayDiLam;
		this.soNgayNghiKhongPhep = soNgayNghiKhongPhep;
		this.tienPhat = tienPhat;
	}

	public String getMaLuongNV() {
		return maLuongNV;
	}

	public void setMaLuongNV(String maLuongNV) {
		this.maLuongNV = maLuongNV;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public int getSoNgayDiLam() {
		return soNgayDiLam;
	}

	public void setSoNgayDiLam(int soNgayDiLam) {
		this.soNgayDiLam = soNgayDiLam;
	}

	public int getSoNgayNghiKhongPhep() {
		return soNgayNghiKhongPhep;
	}

	public void setSoNgayNghiKhongPhep(int soNgayNghiKhongPhep) {
		this.soNgayNghiKhongPhep = soNgayNghiKhongPhep;
	}

	public double getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(double tienPhat) {
		this.tienPhat = tienPhat;
	}

	private double luongTong() {
		return 0.0;
	}
	
	@Override
	public String toString() {
		return "BangLuongNV [maLuongNV=" + maLuongNV + ", nv=" + nv + ", thang=" + thang + ", nam=" + nam
				+ ", soNgayDiLam=" + soNgayDiLam + ", soNgayNghiKhongPhep=" + soNgayNghiKhongPhep + ", tienPhat="
				+ tienPhat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLuongNV == null) ? 0 : maLuongNV.hashCode());
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
		BangLuongNV other = (BangLuongNV) obj;
		if (maLuongNV == null) {
			if (other.maLuongNV != null)
				return false;
		} else if (!maLuongNV.equals(other.maLuongNV))
			return false;
		return true;
	}
}
