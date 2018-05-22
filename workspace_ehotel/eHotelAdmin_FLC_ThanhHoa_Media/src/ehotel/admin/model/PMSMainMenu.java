package ehotel.admin.model;

public class PMSMainMenu {
	private String menuid = "";
	private String menuname = "";
	private String menuimage = "";
	private String menubackground = "";
	private String menurvcserviceid = "";
	private String menutype = "";
	private String menuinvisible = "";

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuimage() {
		return menuimage;
	}

	public void setMenuimage(String menuimage) {
		this.menuimage = menuimage;
	}

	public String getMenubackground() {
		return menubackground;
	}

	public void setMenubackground(String menubackground) {
		this.menubackground = menubackground;
	}

	public String getMenurvcserviceid() {
		return menurvcserviceid;
	}

	public void setMenurvcserviceid(String menurvcserviceid) {
		this.menurvcserviceid = menurvcserviceid;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getMenuinvisible() {
		return menuinvisible;
	}

	public void setMenuinvisible(String menuinvisible) {
		this.menuinvisible = menuinvisible;
	}

	@Override
	public String toString() {
		return "PMSMainMenu [menuid=" + menuid + ", menuname=" + menuname
				+ ", menuimage=" + menuimage + ", menubackground="
				+ menubackground + ", menurvcserviceid=" + menurvcserviceid
				+ ", menutype=" + menutype + ", menuinvisible=" + menuinvisible
				+ "]";
	}

}
