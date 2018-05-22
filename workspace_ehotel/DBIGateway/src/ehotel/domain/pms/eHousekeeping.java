package ehotel.domain.pms;

public class eHousekeeping {
	private int id;
	private String name;
	private String def;
	private String price;
	private String iunit;
	private String urlImage;
	public int invisible;

	public eHousekeeping() {

	}

	public eHousekeeping(String name, String price, String urlImage) {
		this.name = name;
		this.price = price;
		this.urlImage = urlImage;
	}

	public String toString() {
		return "eHousekeeping[id=" + id + ",name=" + name + ",(price=" + price
				+ ",unit=" + iunit + "),urlImage=" + urlImage + "]";
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public int getInvisible() {
		return invisible;
	}

	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}

}
