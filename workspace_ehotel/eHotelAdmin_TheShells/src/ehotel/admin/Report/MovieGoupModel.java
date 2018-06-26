package ehotel.admin.Report;

public class MovieGoupModel {
	private String id = "";
	private String title = "";
	private String upload = "";
	private String countsub = "";
	private String langsub = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getCountsub() {
		return countsub;
	}

	public void setCountsub(String countsub) {
		this.countsub = countsub;
	}

	public String getLangsub() {
		return langsub;
	}

	public void setLangsub(String langsub) {
		this.langsub = langsub;
	}

	@Override
	public String toString() {
		return "MovieGoupModel [id=" + id + ", title=" + title + ", upload="
				+ upload + ", countsub=" + countsub + ", langsub=" + langsub
				+ "]";
	}

}
