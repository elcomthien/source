package ehotel.domain.vod;

/**
 * Provides necessary properties to create an subject
 * Subject[Id,name,urlImage,urlLink,def]
 * 
 */
public class Subject {
	private int Id;
	private String name;
	private String urlImage;
	private String urlBg;
	private String urlLink;
	private String def;
	private int parentId;

	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * Constructor used to create this object.
	 */
	public Subject() {

	}

	/**
	 * Constructor used to create this object.
	 */
	public Subject(String name, String urlImage) {
		this.name = name;
		this.urlImage = urlImage;
	}

	public String toString() {
		return "Subject[id=" + Id + ",name=" + name + ",urlImage=" + urlImage
				+ ",urlBg=" + urlBg + ",parentId=" + parentId + "]";
	}

	/**
	 * get sequence of the desired subject
	 */
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	/**
	 * get name of subject
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get an absolute URL giving the base location of image
	 */
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	/**
	 * get desciption of subject
	 */
	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

}
