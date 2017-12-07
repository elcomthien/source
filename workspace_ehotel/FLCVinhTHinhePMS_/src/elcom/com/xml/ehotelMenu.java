package elcom.com.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "EN", "VN", "image", "bgimage", "icon", "action", "url",
		"act_load", "load_type", "service" }, name = "items")
@XmlRootElement(name = "item")
public class ehotelMenu implements Serializable {
	private String id;
	private String EN;
	private String VN;
	private String image;
	private String bgimage;
	private String icon;
	private String action;
	private String def;
	private String url;
	private String act_load = "0";
	private String load_type;
	private String menuid = "-1";
	private String service;

	public ehotelMenu() {

	}

	public ehotelMenu(String id, String EN, String VN, String image,
			String imagebg, String action, String url) {
		this.id = id;
		this.EN = EN;
		this.VN = VN;
		this.image = image;
		this.bgimage = imagebg;
		this.url = url;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getEN() {
		return EN;
	}

	public void setEN(String eN) {
		EN = eN;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getVN() {
		return VN;
	}

	public void setVN(String vN) {
		VN = vN;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBgimage() {
		return bgimage;
	}

	public void setBgimage(String bgimage) {
		this.bgimage = bgimage;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAct_load() {
		return act_load;
	}

	public void setAct_load(String act_load) {
		this.act_load = act_load;
	}

	public String getLoad_type() {
		return load_type;
	}

	public void setLoad_type(String load_type) {
		this.load_type = load_type;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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
