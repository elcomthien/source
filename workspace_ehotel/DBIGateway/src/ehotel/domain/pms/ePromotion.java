package ehotel.domain.pms;

public class ePromotion {
	private int Id;
	private String name;
	private String content;
	private String urlImage;
	private String urlBg;
	private String urlIcon;
	private String linkWeb;
	private int invisible;

	public ePromotion() {
	}

	public String toString() {
		return "ePromotion[id=" + Id + ",name=" + name + ",content=" + content
				+ ",urlImage=" + urlImage + "]";
	}

	public String getLinkWeb() {
		return linkWeb;
	}

	public void setLinkWeb(String linkWeb) {
		this.linkWeb = linkWeb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getInvisible() {
		return invisible;
	}

	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}

}
