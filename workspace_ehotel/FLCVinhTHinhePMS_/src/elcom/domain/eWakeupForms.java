package elcom.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eWakeupForms {
	private int id;
	private String name;

	public eWakeupForms() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "eWakeupForms[id=" + id + ",name=" + name + "]";
	}
}
