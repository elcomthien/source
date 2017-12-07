package elcom.domain.ex;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import elcom.domain.Adapter;

public class eAttraction extends Adapter {
	private int ICode;
	private String name;
	private String address;
	private String def;
	private String urlImage;
	private String urlBg;
	private String urlIcon;
	private int menuId;
	private int status;// 0=new,1=edit,2=updated

	public eAttraction() {

	}

	public eAttraction(int Icode, String name, String address, String def,
			String urlimage) {
		this.ICode = Icode;
		this.name = name;
		this.address = address;
		this.def = def;
		this.urlImage = urlimage;
	}

	public String toString() {
		return "eAttraction[Id=" + ICode + ",name=" + name + ",urlImage="
				+ urlImage + ",address=" + address + ",def=" + def + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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

	@javax.xml.bind.annotation.XmlAttribute(name = "menuid")
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@javax.xml.bind.annotation.XmlAttribute(name = "ICode")
	public int getICode() {
		return ICode;
	}

	public void setICode(int id) {
		ICode = id;
	}
}
