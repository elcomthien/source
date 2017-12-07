package elcom.com.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "id", "EN", "VN", "image", "bgimage", "url", "action",
		"desc_vn", "desc_en", "load_type", "act_load" }, name = "item_menu")
@XmlRootElement(name = "item")
public class ehotelItem {
	private String id;
	private String EN;
	private String VN;
	private String image;
	private String bgimage;
	private String url;
	private String desc_vn;
	private String desc_en;
	private int act_load = 0;
	private String action = "app";
	private String load_type = "0";
	private String menuid;

	public ehotelItem() {

	}

	public String toString() {
		return "Item[id=" + id + ",EN=" + EN + ",VN=" + VN + "\n image="
				+ image + ",imagebg=" + bgimage + "\n url=" + url
				+ "\n desc_vn=" + desc_vn + ",desc_en=" + desc_en
				+ "\n action=" + action + "]";
	}

	public ehotelItem(String id, String EN, String VN, String image,
			String imagebg, String url, String des_vn, String des_en) {
		this.id = id;
		this.EN = EN;
		this.VN = VN;
		this.image = image;
		this.bgimage = imagebg;
		this.url = url;
		this.desc_en = des_en;
		this.desc_vn = des_vn;
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

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDesc_vn() {
		return desc_vn;
	}

	public void setDesc_vn(String desc_vn) {
		this.desc_vn = desc_vn;
	}

	// @XmlJavaTypeAdapter(value = Adapter.class)
	public String getDesc_en() {
		return desc_en;
	}

	public void setDesc_en(String desc_en) {
		this.desc_en = desc_en;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getAct_load() {
		return act_load;
	}

	public void setAct_load(int act_load) {
		this.act_load = act_load;
	}

	public String getLoad_type() {
		return load_type;
	}

	public void setLoad_type(String load_type) {
		this.load_type = load_type;
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
