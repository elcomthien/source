package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "item")
public class ServiceItem extends Adapter {
	private String ICode;
	private String name;
	private String printName;
	private String def; // thong tin mo ta
	private String IMenu;
	private String currency;
	private String currency_small;
	private String currency_large;
	private String iunit;
	private String address;
	private String phone;
	private String url;// urlImage
	private String urlBg;
	private String picIcon;

	public String toString() {
		return "ServiceItem[ICode=" + ICode + ",name=" + name + ",url=" + url
				+ "]";
	}

	@XmlAttribute(name = "icode")
	public String getICode() {
		return ICode;
	}

	@XmlElement(name = "imagebg")
	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public void setICode(String iCode) {
		ICode = iCode;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "printname")
	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}

	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	@XmlAttribute(name = "menuid")
	public String getIMenu() {
		return IMenu;
	}

	public void setIMenu(String iMenu) {
		IMenu = iMenu;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(name = "image")
	public String getUrl() {
		return url;
	}

	public void setUrl(String urlImage) {
		this.url = urlImage;
	}

	@XmlElement(name = "imageicon")
	public String getPicIcon() {
		return picIcon;
	}

	public void setPicIcon(String picIcon) {
		this.picIcon = picIcon;
	}

	public ServiceItem() {
	}

	public String getCurrency_small() {
		return currency_small;
	}

	public void setCurrency_small(String currency_small) {
		this.currency_small = currency_small;
	}

	public String getCurrency_large() {
		return currency_large;
	}

	public void setCurrency_large(String currency_large) {
		this.currency_large = currency_large;
	}

}
