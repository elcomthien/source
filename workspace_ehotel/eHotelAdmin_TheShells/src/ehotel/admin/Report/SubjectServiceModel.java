package ehotel.admin.Report;

public class SubjectServiceModel {
	private String id = "";
	private String name = "";
	private String count = "";

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "SubjectServiceModel [id=" + id + ", name=" + name + ", count="
				+ count + "]";
	}
}
