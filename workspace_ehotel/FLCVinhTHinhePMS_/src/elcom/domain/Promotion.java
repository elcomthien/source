package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "id", "name", "name_vn", "def", "url", "urlLink",
		"urlBg", "urlIcon" }, name = "item_pro")
@XmlRootElement(name = "item")
public class Promotion {
	private String id;
	private String name;
	private String name_vn;
	private String def;
	private String urlLink;// link web to promtion
	private String url;
	private String urlBg;
	private String urlIcon;

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getName_vn() {
		return name_vn;
	}

	public void setName_vn(String name_vn) {
		this.name_vn = name_vn;
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

	public Promotion() {
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDef() {
		return def;
	}
	
	public void setDef(String def) {
		this.def = def;
	}
	
	//@XmlJavaTypeAdapter(value = Adapter.class)
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Promotion[name=" + name + ",def=" + def + ",url=" + url
				+ ",urlLink=" + urlLink + "]";
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	// --------------
	private static class Adapter extends XmlAdapter {
		@Override
		public Object unmarshal(Object v) throws Exception {
			// TODO Auto-generated method stub
			return v;
		}

		@Override
		public Object marshal(Object v) throws Exception {
			// TODO Auto-generated method stub
			if (v == null)
				return "<![CDATA[  ]]>";
			else
				return "<![CDATA[" + v + "]]>";
		}

	}
}
