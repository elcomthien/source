package ehotel.domain.pms;

public class eService {
	private int serviceId;
	private String serviceName;
	private String urlImage;
	private String action;
	private int invisble;

	public eService() {

	}

	public eService(String serviceName, String urlimage, String action) {
		this.serviceName = serviceName;
		this.urlImage = urlimage;
		this.action = action;
	}

	public String toString() {
		return "eService[id=" + serviceId + ",name=" + serviceName + ",image="
				+ urlImage + ",action=" + action + ",status=" + invisble + "]";
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getInvisble() {
		return invisble;
	}

	public void setInvisble(int invisble) {
		this.invisble = invisble;
	}

}
