package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eCountry {
	private String Id;
	private String name;
	private String def;
	private String url;
	private String urlBg;
	private String urlICon;
	private String urlLink;// option

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public eCountry() {
	}

	@XmlAttribute
	public String getId() {
		return Id;
	}

	public void setId(String countryId) {
		this.Id = countryId;
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

	public void setDef(String code) {
		this.def = code;
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

	public String getUrlICon() {
		return urlICon;
	}

	public void setUrlICon(String urlICon) {
		this.urlICon = urlICon;
	}

	public String toString() {
		return "eCountry[countryId=" + Id + ",name=" + name + ",url=" + url
				+ "]";
	}

}
