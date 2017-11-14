package ehotel.admin.model;

public class LuxuryModel {
	private String id = "";
	private String name = "";
	private String def = "";
	private String type = "";
	private String langid = "";

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

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	@Override
	public String toString() {
		return "LuxuryModel [id=" + id + ", name=" + name + ", def=" + def + ", type=" + type + ", langid=" + langid + "]";
	}

}
