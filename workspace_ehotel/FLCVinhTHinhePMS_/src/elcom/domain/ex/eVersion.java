package elcom.domain.ex;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "code", "name", "version", "path", "path4" }, name = "version")
@XmlRootElement(name = "item")
public class eVersion {
	private String code;
	private String name;
	private String version;
	private String path;

	public eVersion() {

	}

	public eVersion(String code, String name, String version, String path) {
		this.code = code;
		this.name = name;
		this.version = version;
		this.path = path;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
