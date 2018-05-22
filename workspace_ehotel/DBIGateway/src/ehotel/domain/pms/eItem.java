package ehotel.domain.pms;

public class eItem {
	private int ICode;
	private String name;
	private String def;
	private String printName;
	private String currency;
	private String currency_large;
	private String currency_small;
	private String IUnit;
	private String urlImage;
	private int invisible;

	public eItem() {
	}

	public String toString() {
		return "eItem[icode=" + ICode + ",name=" + name + ",currency="
				+ currency + ",IUnit=" + IUnit + ",invisible=" + invisible
				+ "]";
	}

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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
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

	public String getCurrency_large() {
		return currency_large;
	}

	public void setCurrency_large(String currency_large) {
		this.currency_large = currency_large;
	}

	public String getCurrency_small() {
		return currency_small;
	}

	public void setCurrency_small(String currency_small) {
		this.currency_small = currency_small;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public int getInvisible() {
		return invisible;
	}

	public void setInvisible(int invisible) {
		this.invisible = invisible;
	}

}
