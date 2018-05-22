package ehotel.domain.pms;

public class eExchangeRate {
	private int Id;
	private String code;
	private String name;
	private String buyRate;
	private String transferRate;
	private String sellRate;
	private String urlImage;
	private String urlBg;
	private String urlIcon;
	private String bankingName;
	private int invisibel;

	public eExchangeRate() {

	}

	public String toString() {
		return "eExchangeRate[id=" + Id + ",code=" + code + ",name=" + name
				+ ",buyRate=" + buyRate + ",transferRate=" + transferRate
				+ ",sellRate=" + sellRate + ",urlImage=" + urlImage + "]";
	}

	public String getBankingName() {
		return bankingName;
	}

	public void setBankingName(String bankingName) {
		this.bankingName = bankingName;
	}

	public int getInvisibel() {
		return invisibel;
	}

	public void setInvisibel(int invisibel) {
		this.invisibel = invisibel;
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(String buyRate) {
		this.buyRate = buyRate;
	}

	public String getTransferRate() {
		return transferRate;
	}

	public void setTransferRate(String transferRate) {
		this.transferRate = transferRate;
	}

	public String getSellRate() {
		return sellRate;
	}

	public void setSellRate(String sellRate) {
		this.sellRate = sellRate;
	}

}
