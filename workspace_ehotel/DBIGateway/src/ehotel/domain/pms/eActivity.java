package ehotel.domain.pms;

public class eActivity {
	private int Id;
	private String name;
	private String def;
	private int invisible;

	public eActivity() {

	}

	public String toString() {
		return "eActivity[id=" + Id + ",name=" + name + ",def=" + def + "]";
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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public int getInvisible() {
		return invisible;
	}

	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}

}
