package ehotel.admin.Report;

public class RoomServiceMenuModel {
	private String foodItem = "";
	private String updateDate = "";
	private String rate = "";
	private String currency = "";
	private String updateBy = "";

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return "RoomServiceMenuModel [foodItem=" + foodItem + ", updateDate="
				+ updateDate + ", rate=" + rate + ", currency=" + currency
				+ ", updateBy=" + updateBy + "]";
	}

}
