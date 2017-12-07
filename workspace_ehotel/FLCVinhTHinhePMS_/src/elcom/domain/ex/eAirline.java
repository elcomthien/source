package elcom.domain.ex;

public class eAirline {
	private int id;
	private String name;
	private String urlLink;
	private String url;// urlimage
	private String urlBg;
	private String urlIcon;

	public eAirline() {

	}

	public String toString() {
		return "eAirline[id=" + id + ",name=" + name + ",urlLink=" + urlLink
				+ ",UrlImage=" + url + "]";
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

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}
