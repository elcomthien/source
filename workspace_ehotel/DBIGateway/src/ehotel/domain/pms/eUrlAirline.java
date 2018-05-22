package ehotel.domain.pms;

public class eUrlAirline {
	private int Id;
	private String name;
	private String urlImage;
	private String urlLink;
	private int invisble;

	public eUrlAirline() {

	}

	public String toString() {
		return "eUrlAirline[id=" + Id + ",name=" + name + ",urlImage="
				+ urlImage + ",urlLink=" + urlLink + "]";
	}

	public int getInvisble() {
		return invisble;
	}

	public void setInvisble(int invisble) {
		this.invisble = invisble;
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

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

}
