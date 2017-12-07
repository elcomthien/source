package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "code", "name", "version", "path", "path4" }, name = "version")
@XmlRootElement(name = "item")
public class VersionData {
	private String code;
	private String name;
	private String version;
	private String path;
	private String path4;

	public VersionData() {

	}

	public VersionData(String code, String name, String version, String path,
			String path4) {
		this.code = code;
		this.name = name;
		this.version = version;
		this.path = path;
		this.path4 = path4;
	}

	@XmlAttribute
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@XmlAttribute
	public String getPath4() {
		return path4;
	}

	public void setPath4(String path4) {
		this.path4 = path4;
	}

}
