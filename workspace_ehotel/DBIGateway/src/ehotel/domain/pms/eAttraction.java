package ehotel.domain.pms;

public class eAttraction {
	private int Id;
	private String name;
	private String def;
	private String address;
	private int invisible;

	public eAttraction() {
	}

	public String toString() {
		return "eAttraction[id=" + Id + ",name=" + name + ",def=" + def
				+ "address=" + address + ",invisible=" + invisible + "]";
	}

	public int getId() {
		return Id;
	}

	public int getInvisible() {
		return invisible;
	}

	public void setInvisible(int invisible) {
		this.invisible = invisible;
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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
