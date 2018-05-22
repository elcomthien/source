package ehotel.domain.pms;

public class eRestaurant {
	private int Id;
	private String name;
	private String def;
	private String urlImage;
	private int invisible;

	public eRestaurant() {

	}

	public String toString() {
		return "eRestaurant[id=" + Id + ",name=" + name + ",def=" + def + "]";
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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
