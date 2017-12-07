package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class OutletMovie {
	private String Id;
	private String name;
	private String url;
	private String def;

	public OutletMovie() {
	}

	public String toString() {
		return "OutletMovie[movewId=" + Id + ",urlMovie=" + url + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

}
