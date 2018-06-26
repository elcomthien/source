package ehotel.admin.model;

public class DiningPromotionModel {
	private String id = "";
	private String subject = "";
	private String name = "";
	private String def = "";
	private String newPrice = "";
	private String oldPrice = "";
	private String iunit = "";
	private String image = "";
	private String invisible = "";
	private String langId = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}

	public String getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	@Override
	public String toString() {
		return "DiningPromotionModel [id=" + id + ", subject=" + subject
				+ ", name=" + name + ", def=" + def + ", newPrice=" + newPrice
				+ ", oldPrice=" + oldPrice + ", iunit=" + iunit + ", image="
				+ image + ", invisible=" + invisible + ", langId=" + langId
				+ "]";
	}

}
