package elcom.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name = "item")
public class eWeb {
	private int Id;
	private String name;
	private String url;

	public eWeb() {
	}

	@XmlAttribute
	public int getId() {
		return Id;
	}

	public void setId(int webId) {
		this.Id = webId;
	}

	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		return "eWeather[webId=" + Id + ",url=" + url + "]";
	}
}
