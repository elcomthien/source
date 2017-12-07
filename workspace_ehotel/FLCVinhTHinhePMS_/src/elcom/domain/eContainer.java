package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eContainer {
	private int id;

	private String EN;
	private String VN;
	private String image;
	private String bgimage;
	private String action;
	private String service = "E10";
	private String url;
	private String act_load = "0";

	public eContainer() {

	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEN() {
		return EN;
	}

	public void setEN(String eN) {
		EN = eN;
	}

	public String getVN() {
		return VN;
	}

	public void setVN(String vN) {
		VN = vN;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBgimage() {
		return bgimage;
	}

	public void setBgimage(String bgimage) {
		this.bgimage = bgimage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAct_load() {
		return act_load;
	}

	public void setAct_load(String act_load) {
		this.act_load = act_load;
	}

}
