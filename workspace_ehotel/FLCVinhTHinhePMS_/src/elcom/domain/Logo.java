package elcom.domain;

public class Logo {
	private String logo_welcome;
	private String logo_service;

	public Logo() {

	}

	public String toString() {
		return "Logo[logo_welcome=" + logo_welcome + ",logo_service="
				+ logo_service + "]";
	}

	public String getLogo_welcome() {
		return logo_welcome;
	}

	public void setLogo_welcome(String logo_welcome) {
		this.logo_welcome = logo_welcome;
	}

	public String getLogo_service() {
		return logo_service;
	}

	public void setLogo_service(String logo_service) {
		this.logo_service = logo_service;
	}

}
