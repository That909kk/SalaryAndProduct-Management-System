package entity;

import java.time.LocalDate;
import java.util.Arrays;

public class CongNhan {
	private String maCN;
	private byte[] anhDaiDien;
	private String ho;
	private String ten;
	private boolean gioiTinh;
	private String cCCD;
	private String diaChi;
	private String soDienThoai;
	private String chuyenMon;
	private int caLamViec;
	private double phuCap;
	private LocalDate ngayBatDauLamViec;

	public CongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CongNhan(String maCN, byte[] anhDaiDien, String ho, String ten, boolean gioiTinh, String cCCD, String diaChi,
			String soDienThoai, String chuyenMon, int caLamViec, double phuCap, LocalDate ngayBatDauLamViec) {
		super();
		this.maCN = maCN;
		this.anhDaiDien = anhDaiDien;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.cCCD = cCCD;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.chuyenMon = chuyenMon;
		this.caLamViec = caLamViec;
		this.phuCap = phuCap;
		this.ngayBatDauLamViec = ngayBatDauLamViec;
	}

	public CongNhan(String maCN) {
		super();
		this.maCN = maCN;
	}

	public byte[] getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(byte[] anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(String chuyenMon) {
		this.chuyenMon = chuyenMon;
	}

	public int getCaLamViec() {
		return caLamViec;
	}

	public void setCaLamViec(int caLamViec) {
		this.caLamViec = caLamViec;
	}

	public double getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}

	public LocalDate getNgayBatDauLamViec() {
		return ngayBatDauLamViec;
	}

	public void setNgayBatDauLamViec(LocalDate ngayBatDauLamViec) {
		this.ngayBatDauLamViec = ngayBatDauLamViec;
	}

	public String getMaCN() {
		return maCN;
	}

	@Override
	public String toString() {
		return "CongNhan [maCN=" + maCN + ", anhDaiDien=" + Arrays.toString(anhDaiDien) + ", ho=" + ho + ", ten=" + ten
				+ ", gioiTinh=" + gioiTinh + ", cCCD=" + cCCD + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai
				+ ", chuyenMon=" + chuyenMon + ", caLamViec=" + caLamViec + ", phuCap=" + phuCap
				+ ", ngayBatDauLamViec=" + ngayBatDauLamViec + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCN == null) ? 0 : maCN.hashCode());
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
		CongNhan other = (CongNhan) obj;
		if (maCN == null) {
			if (other.maCN != null)
				return false;
		} else if (!maCN.equals(other.maCN))
			return false;
		return true;
	}
}
