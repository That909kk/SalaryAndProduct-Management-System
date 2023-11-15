package entity;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int soLuong;
	private int soLuongCongDoan;
	private boolean trangThai;
	private HopDong hopDong;

	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String maSP, String tenSP, int soLuong, int soLuongCongDoan, boolean trangThai, HopDong hopDong) throws Exception {
		super();
		this.maSP = maSP;
		setTenSP(tenSP);
		setSoLuong(soLuong);
		this.soLuongCongDoan = soLuongCongDoan;
		this.trangThai = trangThai;
		this.hopDong = hopDong;
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) throws Exception {
		if (tenSP.trim().length() > 0) 
			this.tenSP = tenSP;
		else
			throw new Exception("tenSP khong duoc rong");
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) throws Exception {
		if (soLuong >= 0)
			this.soLuong = soLuong;
		else
			throw new Exception("soLuong khong duoc < 0");
	}

	public int getSoLuongCongDoan() {
		return soLuongCongDoan;
	}

	public void setSoLuongCongDoan(int soLuongCongDoan) {
		this.soLuongCongDoan = soLuongCongDoan >= 0 ? soLuongCongDoan : 0;
	}
	
	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public HopDong getHopDong() {
		return hopDong;
	}

	public void setHopDong(HopDong hopDong) {
		this.hopDong = hopDong;
	}

	public String getMaSP() {
		return maSP;
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", soLuongCongDoan="
				+ soLuongCongDoan + ", hopDong=" + hopDong + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSP == null) ? 0 : maSP.hashCode());
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
		SanPham other = (SanPham) obj;
		if (maSP == null) {
			if (other.maSP != null)
				return false;
		} else if (!maSP.equals(other.maSP))
			return false;
		return true;
	}
}
