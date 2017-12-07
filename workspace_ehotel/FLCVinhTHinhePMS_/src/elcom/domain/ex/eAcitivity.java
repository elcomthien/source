package elcom.domain.ex;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import elcom.domain.Adapter;

public class eAcitivity {
	private int ICode;
	private String name;
	private String def;

	public eAcitivity() {

	}

	@javax.xml.bind.annotation.XmlAttribute(name = "ICode")
	public int getICode() {
		return ICode;
	}

	public void setICode(int iCode) {
		ICode = iCode;
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlJavaTypeAdapter(value = Adapter.class)
	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String toString() {
		return " eAcitivity[ICode=" + ICode + ",name=" + name + ",def=" + def
				+ "]";
	}
}
