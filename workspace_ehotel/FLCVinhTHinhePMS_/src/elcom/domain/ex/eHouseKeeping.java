package elcom.domain.ex;

import javax.xml.bind.annotation.XmlAttribute;

public class eHouseKeeping {
	private int ICode;
	private String name;
	private String currency;
	private String IUnit;
	private String url;
	private String urlBg;
	private String urlIcon;
	private int menuId;

	public eHouseKeeping() {

	}

	public String toString() {
		return "eHouseKeeping[ICode=" + ICode + ",name=" + name + ",urlImage="
				+ url + "urlBg=" + urlBg + "]";
	}

	@XmlAttribute(name = "menuid")
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@XmlAttribute
	public int getICode() {
		return ICode;
	}

	public void setICode(int iCode) {
		ICode = iCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIUnit() {
		return IUnit;
	}

	public void setIUnit(String iUnit) {
		IUnit = iUnit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}
