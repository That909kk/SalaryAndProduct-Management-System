package entity;

import java.time.LocalDate;
import java.util.Arrays;

public class CongNhan {
	private String maCN;
	private static int idCounter = 0;
	private byte[] anhDaiDien;
	private String ho;
	private String ten;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String cCCD;
	private String diaChi;
	private String soDienThoai;
	private String chuyenMon;
	private int caLamViec;
	private double phuCap;
	private double luongCoBan;
	private LocalDate ngayBatDauLamViec;
	private Xuong xuong;

	public CongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CongNhan(String maCN, byte[] anhDaiDien, String ho, String ten, boolean gioiTinh, LocalDate ngaySinh,
			String cCCD, String diaChi, String soDienThoai, String chuyenMon, int caLamViec, double phuCap,
			double luongCoBan, LocalDate ngayBatDauLamViec, Xuong xuong) {
		super();
		this.maCN = maCN;
		this.anhDaiDien = anhDaiDien;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.cCCD = cCCD;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.chuyenMon = chuyenMon;
		this.caLamViec = caLamViec;
		this.phuCap = phuCap;
		this.luongCoBan = luongCoBan;
		this.xuong = xuong;
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
		this.ho = capitalizeFirstLetterOfEachWord(ho);
	}

	private String capitalizeFirstLetterOfEachWord(String input) {
        String[] words = input.split("\\s");
        StringBuilder output = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                output.append(Character.toUpperCase(word.charAt(0)));
                output.append(word.substring(1));
                output.append(" ");
            }
        }
        return output.toString().trim();
    }

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = capitalizeFirstLetterOfEachWord(ten);
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}
	boolean Nam = true;
	boolean Nữ = false;
	public void setGioiTinh(boolean gioiTinh) {
		if(gioiTinh)
			this.gioiTinh = true;
		else
			this.gioiTinh = false;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		if (cCCD.matches("\\d{12}")) {
            this.cCCD = cCCD;
        } else {
            throw new IllegalArgumentException("CCCD phải là một chuỗi gồm 12 ký tự số");
        }
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
		 if (soDienThoai.matches("0\\d{9}")) {
	            this.soDienThoai = soDienThoai;
	        } else {
	            throw new IllegalArgumentException("Số điện thoại phải là một chuỗi gồm 10 ký số bắt đầu bằng số 0");
	        }
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
		if (caLamViec == 1 || caLamViec == 2) {
            this.caLamViec = caLamViec;
        } else {
            throw new IllegalArgumentException("Ca làm việc phải là 1 (Sáng) hoặc 2 (Tối)");
        }
	}

	public double getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(double phuCap) {
		if (phuCap >= 0) {
            this.phuCap = phuCap;
        } else {
            throw new IllegalArgumentException("Phụ cấp phải lớn hơn hoặc bằng 0");
        }
	}

	public LocalDate getNgayBatDauLamViec() {
		return ngayBatDauLamViec;
	}

	public void setNgayBatDauLamViec(LocalDate ngayBatDauLamViec) {
		if (ngayBatDauLamViec != null) {
            this.ngayBatDauLamViec = ngayBatDauLamViec;
        } else {
            throw new IllegalArgumentException("Ngày bắt đầu làm việc không được để trống");
        }
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public double getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(double luongCoBan) {
		if (luongCoBan > 0) {
            this.luongCoBan = luongCoBan;
        } else {
            throw new IllegalArgumentException("Lương cơ bản phải lớn hơn 0");
        }
	}

	public String getMaCN() {
		return maCN;
	}
	
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}

	public Xuong getXuong() {
		return xuong;
	}

	public void setXuong(Xuong xuong) {
		this.xuong = xuong;
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
