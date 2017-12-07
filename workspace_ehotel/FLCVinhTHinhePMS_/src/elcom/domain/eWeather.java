package elcom.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class eWeather {
	private int id;
	private String sday;
	private int temperature;// outside
	private int seatemp;// sea
	private int minTemperature;
	private int maxTemperature;
	private String postedDate;
	private String picImage;
	private String def;

	public eWeather() {
	}

	public String toString() {
		return "eWeather[sday=" + sday + ",temperature=" + temperature
				+ ",picImage=" + picImage + "]";
	}

	public int getSeatemp() {
		return seatemp;
	}

	public void setSeatemp(int seatemp) {
		this.seatemp = seatemp;
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public int getMinTemperature() {
		return minTemperature;
	}

	public String getSday() {
		return sday;
	}

	public int getTemperature() {
		return temperature;
	}

	public String getPostedDate() {
		return postedDate;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public String getPicImage() {
		return picImage;
	}

	public String getDef() {
		return def;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}

	public void setSday(String sday) {
		this.sday = sday;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPicImage(String picImage) {
		this.picImage = picImage;
	}

	public void setDef(String def) {
		this.def = def;
	}

}
