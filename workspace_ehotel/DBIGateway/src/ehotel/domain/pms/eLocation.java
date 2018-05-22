package ehotel.domain.pms;

public class eLocation {
	private int Id;
	private String X;
	private String Y;

	public eLocation() {

	}

	public String toString() {
		return "eLocation[id=" + Id + ",X=" + X + ",Y=" + Y + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
