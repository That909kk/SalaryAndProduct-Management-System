package entity;

import java.time.LocalDate;

public class BangChamCongCN {
	private static int idCounter = 0;
	private String maCCCN;
	private CongNhan cn;
	private LocalDate ngayCham;
	private int soGioTangCa;
	private int caLam;
	private int sanLuong;
	private boolean vangMat;
	private boolean coPhep;
	private String ghiChu;

	public BangChamCongCN() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BangChamCongCN(String maCCCN, CongNhan cn, LocalDate ngayCham, int soGioTangCa, int caLam,
			int sanLuong, boolean vangMat, boolean coPhep, String ghiChu) {
		super();
		this.maCCCN = maCCCN;
		this.cn = cn;
		this.ngayCham = ngayCham;
		this.soGioTangCa = soGioTangCa;
		this.caLam = caLam;
		this.sanLuong = sanLuong;
		this.vangMat = vangMat;
		this.coPhep = coPhep;
		this.ghiChu = ghiChu;
	}

	public BangChamCongCN(String maCCCN) {
		super();
		idCounter += 1;
        this.maCCCN = "CN" + String.format("%09d", idCounter);
	}

	public String getMaCCCN() {
		return maCCCN;
	}

	public void setMaCCCN(String maCCCN) {
		this.maCCCN = maCCCN;
	}

	public CongNhan getCn() {
		return cn;
	}

	public void setCn(CongNhan cn) {
		this.cn = cn;
	}

	public LocalDate getNgayCham() {
		return ngayCham;
	}

	public void setNgayCham(LocalDate ngayCham) {
		if (ngayCham != null) {
            this.ngayCham = ngayCham;
        } else {
            throw new IllegalArgumentException("Ngày chấm công không được để trống");
        }
	}

	public int getSoGioTangCa() {
		return soGioTangCa;
	}

	public void setSoGioTangCa(int soGioTangCa) {
		if (soGioTangCa >= 0) {
            this.soGioTangCa = soGioTangCa;
        } else {
            throw new IllegalArgumentException("Số giờ tăng ca phải lớn hơn hoặc bằng 0");
        }
	}

	public int getCaLam() {
		return caLam;
	}

	public void setCaLam(int caLam) {
		this.caLam = caLam;
	}

	public int getSanLuong() {
		return sanLuong;
	}

	public void setSanLuong(int sanLuong) {
		if (sanLuong >= 0) {
            this.sanLuong = sanLuong;
        } else {
            throw new IllegalArgumentException("Sản lượng phải lớn hơn hoặc bằng 0");
        }
	}
	private boolean isVangMat;
	public boolean isVangMat() {
		return vangMat;
	}

	public void setVangMat(boolean vangMat) {
		this.vangMat = vangMat;
	}

	public boolean isCoPhep() {
		return coPhep;
	}

	public void setCoPhep(boolean coPhep) {
		if (isVangMat) {
            this.coPhep = coPhep;
        } else {
            throw new IllegalArgumentException("Không thể đặt trạng thái không phép nếu công nhân không vắng mặt");
        }
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getMaLuongCN() {
		return maCCCN;
	}

	@Override
	public String toString() {
		return "BangLuongCongNhan [maLuongCN=" + maCCCN + ", cn=" + cn + ", ngayCham=" + ngayCham + ", soGioTangCa="
				+ soGioTangCa + ", caLam=" + caLam + ", sanLuong=" + sanLuong + ", vangMat=" + vangMat + ", coPhep="
				+ coPhep + ", ghiChu=" + ghiChu + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCCCN == null) ? 0 : maCCCN.hashCode());
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
		BangChamCongCN other = (BangChamCongCN) obj;
		if (maCCCN == null) {
			if (other.maCCCN != null)
				return false;
		} else if (!maCCCN.equals(other.maCCCN))
			return false;
		return true;
	}
}
