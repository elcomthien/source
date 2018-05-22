package ehotel.domain.pms;

public class eAdvertise {
	private int Id;
	private String name;
	private String urlImage;
	private String urlBg;
	private String urlIcon;
	private String type;
	private int invisibe;

	public eAdvertise() {

	}

	public String toString() {
		return "eAdvertise[id=" + Id + ",name=" + name + ",urlImage="
				+ urlImage + ",urlBg=" + urlBg + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public String getUrlIcon() {
		return urlIcon;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public int getInvisibe() {
		return invisibe;
	}

	public void setInvisibe(int invisibe) {
		this.invisibe = invisibe;
	}

}
