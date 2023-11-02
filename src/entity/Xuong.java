package entity;

public class Xuong {
	private String maXuong;
	private String tenXuong;
	private String diaChi;

	public Xuong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Xuong(String maXuong, String tenXuong, String diaChi) {
		super();
		this.maXuong = maXuong;
		this.tenXuong = tenXuong;
		this.diaChi = diaChi;
	}

	public Xuong(String maXuong) {
		super();
		this.maXuong = maXuong;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maXuong == null) ? 0 : maXuong.hashCode());
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
		Xuong other = (Xuong) obj;
		if (maXuong == null) {
			if (other.maXuong != null)
				return false;
		} else if (!maXuong.equals(other.maXuong))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Xuong [maXuong=" + maXuong + ", tenXuong=" + tenXuong + ", diaChi=" + diaChi + "]";
	}

	public String getTenXuong() {
		return tenXuong;
	}

	public void setTenXuong(String tenXuong) {
		this.tenXuong = tenXuong;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMaXuong() {
		return maXuong;
	}
}
