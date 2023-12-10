package entity;

public class BangLuongCN {
	private String maLuongCN;
	private CongNhan cn;
	private int thang;
	private int nam;
	private int tongSanLuong;
	private int soNgayNghiKhongPhep;
	private double tienPhat;
	private double bhxh;
	private double luongTong;

	public BangLuongCN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangLuongCN(String maLuongCN) {
		super();
		this.maLuongCN = maLuongCN;
	}

	public BangLuongCN(String maLuongCN, CongNhan cn, int thang, int nam, int tongSanLuong, int soNgayNghiKhongPhep,
			double tienPhat , double bhxh, double luongTong) {
		super();
		this.maLuongCN = maLuongCN;
		this.cn = cn;
		this.thang = thang;
		this.nam = nam;
		this.tongSanLuong = tongSanLuong;
		this.soNgayNghiKhongPhep = soNgayNghiKhongPhep;
		this.tienPhat = tienPhat;
		this.bhxh = bhxh;
		this.luongTong = luongTong;

	}

	public String getMaLuongCN() {
		return maLuongCN;
	}

	public void setMaLuongCN(String maLuongCN) {
		this.maLuongCN = maLuongCN;
	}

	public CongNhan getCn() {
		return cn;
	}

	public void setCn(CongNhan cn) {
		this.cn = cn;
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

	public int getTongSanLuong() {
		return tongSanLuong;
	}

	public void setTongSanLuong(int tongSanLuong) {
		this.tongSanLuong = tongSanLuong;
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

	public double getBhxh() {
		return bhxh;
	}
	public void setBhxh(double bhxh) {
		this.bhxh = bhxh;
	}
	public double getLuongTong() {
		return luongTong;
	}
	public void setLuongTong(double luongTong) {
		this.luongTong = luongTong;
	}
	
	
	@Override
	public String toString() {
		return "BangLuongCN [maLuongCN=" + maLuongCN + ", cn=" + cn + ", thang=" + thang + ", nam=" + nam
				+ ", tongSanLuong=" + tongSanLuong + ", soNgayNghiKhongPhep=" + soNgayNghiKhongPhep + ", tienPhat="
				+ tienPhat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLuongCN == null) ? 0 : maLuongCN.hashCode());
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
		BangLuongCN other = (BangLuongCN) obj;
		if (maLuongCN == null) {
			if (other.maLuongCN != null)
				return false;
		} else if (!maLuongCN.equals(other.maLuongCN))
			return false;
		return true;
	}
}
