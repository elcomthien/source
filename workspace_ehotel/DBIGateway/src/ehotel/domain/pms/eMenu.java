package ehotel.domain.pms;

public class eMenu {
	private int menuId;
	private String menuName;
	private String urlImage;
	private String urlBg;
	private String urlIcon;
	private int parentId;
	private String type;
	private int level;

	public eMenu() {

	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		return "eMenu[menuId=" + menuId + ",menuName=" + menuName
				+ ",urlImage=" + urlImage + ",urlBg=" + urlBg + ",parentId="
				+ parentId + ",type=" + type + "]";
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
