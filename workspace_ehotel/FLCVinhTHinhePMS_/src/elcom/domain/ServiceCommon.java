package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "item")
public class ServiceCommon {
	private int Id;
	private String name;
	private String name_vn;
	private String url; // urlImageicon
	private String urlBg;
	private String urlIcon;
	private String urlLink;// link web
	private String def;
	private String apkcode;
	private String act_load;
	private String service_code;

	public String toString() {
		return "ServiceCommon[serId=" + Id + ",name=" + name + ",urlLink="
				+ urlLink + ",urlImage=" + url + ",apkcode=" + apkcode + "]";
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getName_vn() {
		return name_vn;
	}

	public void setName_vn(String name_vn) {
		this.name_vn = name_vn;
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getApkcode() {
		return apkcode;
	}

	public void setApkcode(String apkcode) {
		this.apkcode = apkcode;
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

	public ServiceCommon() {
	}
	
	@XmlJavaTypeAdapter(value = Adapter.class)
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

	public String getUrlIcon() {
		return urlIcon;
	}

	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public void setUrlIcon(String urlIcon) {
		this.urlIcon = urlIcon;
	}

	public String getAct_load() {
		return act_load;
	}

	public void setAct_load(String act_load) {
		this.act_load = act_load;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

}
