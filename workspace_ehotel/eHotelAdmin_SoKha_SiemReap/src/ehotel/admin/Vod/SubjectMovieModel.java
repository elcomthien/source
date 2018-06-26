package ehotel.admin.Vod;

public class SubjectMovieModel {
	private String id = "";
	private String name = "";
	private String parent = "";
	private String image = "";
	private String type = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SubjectMovieModel [id=" + id + ", name=" + name + ", parent="
				+ parent + ", image=" + image + ", type=" + type + "]";
	}

}
