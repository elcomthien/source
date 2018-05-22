package ehotel.domain.pms;

public class eLocationMap {
	private int Id;
	private String name;
	private String address;
	private String phone;
	private String distance;
	private String X;
	private String Y;

	public eLocationMap() {

	}

	public String toString() {
		return "eLocationMap[id=" + Id + ",name=" + name + ",address="
				+ address + ",phone=" + phone + ",distance=" + distance + ",X="
				+ X + ",Y=" + Y + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getX() {
		return X;
	}

	public void setX(String x) {
		X = x;
	}

	public String getY() {
		return Y;
	}

	public void setY(String y) {
		Y = y;
	}

}
