package com.elcom.ehotel.model;

public class DiningModel {

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public int getMaorder() {
		return maorder;
	}

	public void setMaorder(int maorder) {
		this.maorder = maorder;
	}

	public String getDichvu() {
		return dichvu;
	}

	public void setDichvu(String dichvu) {
		this.dichvu = dichvu;
	}

	public String getTenmonan() {
		return tenmonan;
	}

	public void setTenmonan(String tenmonan) {
		this.tenmonan = tenmonan;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getPhong() {
		return phong;
	}

	public void setPhong(int phong) {
		this.phong = phong;
	}

	public String getNgaydat() {
		return ngaydat;
	}

	public void setNgaydat(String ngaydat) {
		this.ngaydat = ngaydat;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public int getCheckout() {
		return checkout;
	}

	public void setCheckout(int checkout) {
		this.checkout = checkout;
	}

	public String getNgaythuchien() {
		return ngaythuchien;
	}

	public void setNgaythuchien(String ngaythuchien) {
		this.ngaythuchien = ngaythuchien;
	}

	@Override
	public String toString() {
		return "DiningModel [maorder=" + maorder + ", dichvu=" + dichvu + ", tenmonan=" + tenmonan + ", soluong=" + soluong + ", phong="
				+ phong + ", ngaydat=" + ngaydat + ", trangthai=" + trangthai + ", gia=" + gia + ", checkout=" + checkout
				+ ", ngaythuchien=" + ngaythuchien + ", role=" + role + "]";
	}

	public DiningModel(int maorder, String dichvu, String tenmonan, int soluong, int phong, String ngaydat, int trangthai, String gia,
			int checkout, String ngaythuchien) {
		this.maorder = maorder;
		this.dichvu = dichvu;
		this.tenmonan = tenmonan;
		this.soluong = soluong;
		this.phong = phong;
		this.ngaydat = ngaydat;
		this.trangthai = trangthai;
		this.gia = gia;
		this.checkout = checkout;
		this.ngaythuchien = ngaythuchien;
	}

	public DiningModel() {
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiningModel other = (DiningModel) obj;
		if (checkout != other.checkout)
			return false;
		if (dichvu == null) {
			if (other.dichvu != null)
				return false;
		} else if (!dichvu.equals(other.dichvu))
			return false;
		if (gia != other.gia)
			return false;
		if (maorder != other.maorder)
			return false;
		if (ngaydat == null) {
			if (other.ngaydat != null)
				return false;
		} else if (!ngaydat.equals(other.ngaydat))
			return false;
		if (ngaythuchien == null) {
			if (other.ngaythuchien != null)
				return false;
		} else if (!ngaythuchien.equals(other.ngaythuchien))
			return false;
		if (phong != other.phong)
			return false;
		if (soluong != other.soluong)
			return false;
		if (tenmonan == null) {
			if (other.tenmonan != null)
				return false;
		} else if (!tenmonan.equals(other.tenmonan))
			return false;
		return trangthai == other.trangthai;
	}

	private int maorder;
	private String dichvu;
	private String tenmonan;
	private int soluong;
	private int phong;
	private String ngaydat;
	private int trangthai;
	private String gia;
	private int checkout;
	private String ngaythuchien;
	private String role = "";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
