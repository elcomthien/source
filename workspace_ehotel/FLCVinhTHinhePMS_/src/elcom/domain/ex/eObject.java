package elcom.domain.ex;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eObject {
	private int Id;
	private String name;
	private String def;

	public eObject() {

	}

	@javax.xml.bind.annotation.XmlAttribute(name = "id")
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

}
