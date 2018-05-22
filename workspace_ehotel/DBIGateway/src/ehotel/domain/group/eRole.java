package ehotel.domain.group;

public class eRole {
	private int Id;
	private String name;
	private String value;

	public eRole() {

	}

	public String toString() {
		return "eRole[id=" + Id + ",name=" + name + ",value=" + value + "]";
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
