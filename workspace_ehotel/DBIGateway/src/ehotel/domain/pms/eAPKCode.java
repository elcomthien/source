package ehotel.domain.pms;

public class eAPKCode {
	private String name;

	private String path;
	private String version;

	public eAPKCode() {

	}

	public eAPKCode(String name, String path, String version) {
		this.name = name;
		this.path = path;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
