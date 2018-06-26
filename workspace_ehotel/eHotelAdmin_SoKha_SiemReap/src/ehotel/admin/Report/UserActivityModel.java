package ehotel.admin.Report;

public class UserActivityModel {
	private String id = "";
	private String description = "";
	private String date = "";
	private String user = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserActivityModel [id=" + id + ", description=" + description
				+ ", date=" + date + ", user=" + user + "]";
	}

}
