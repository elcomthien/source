package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eMapLocation {
	private int locationid;
	private String name;
	private String name_vn;
	private String address;
	private String def;
	private String distance;
	private String x;
	private String y;
	private int status;// 0=new;1=edit,2=delete
	private int menuId;
	private int langId;// phuc vu dong bo ve sqlLite

	public eMapLocation() {
	}

	public eMapLocation(String name, String address, String def,
			String distance, String x, String y) {
		this.name = name;
		this.address = address;
		this.def = def;
		this.distance = distance;
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "eMapLocation[locationid=" + locationid + ",name=" + name
				+ ",address=" + address + ",distance=" + distance + ",(X,Y)=("
				+ x + "," + y + ") ,menuId=" + menuId + "]";
	}

	@XmlAttribute(name = "id")
	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_vn() {
		return name_vn;
	}

	public void setName_vn(String name_vn) {
		this.name_vn = name_vn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@XmlAttribute(name = "menuid")
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

}
