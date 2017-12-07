package elcom.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name = "item")
public class eWakeup {
	private int id;
	private String hours;
	private String minutes;
	private int isOn; // 1=on, 0=off
	private int isRepeat;// 1=on, 0=off
	private String wakeupDate;// for telegry
	private String wakeupForms;// for telegry (hinh thuc danh thuc)

	public eWakeup() {
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public int getIsOn() {
		return isOn;
	}

	public void setIsOn(int isOn) {
		this.isOn = isOn;
	}

	public int getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getWakeupDate() {
		return wakeupDate;
	}

	public void setWakeupDate(String wakeupDate) {
		this.wakeupDate = wakeupDate;
	}

	public String getWakeupForms() {
		return wakeupForms;
	}

	public void setWakeupForms(String wakeupForms) {
		this.wakeupForms = wakeupForms;
	}

	public String toString() {
		return "eWakeup[hourId=" + id + ",hours=" + hours + ",minutes="
				+ minutes + "]";
	}
}
