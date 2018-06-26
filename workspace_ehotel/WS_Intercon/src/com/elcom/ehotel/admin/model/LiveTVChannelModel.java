package com.elcom.ehotel.admin.model;

public class LiveTVChannelModel {

	private String idChannel = "";
	private String name = "";
	private String code = "";
	private String link = "";
	private String status = "";
	private String image = "";
	private String maxindex = "";
	private String language = "";
	private String subtitle = "";
	private String idgroup = "";

	public String getIdChannel() {
		return idChannel;
	}

	public void setIdChannel(String idChannel) {
		this.idChannel = idChannel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LiveTVChannelModel [idChannel=" + idChannel + ", name=" + name + ", code=" + code + ", link=" + link + ", status=" + status
				+ ", image=" + image + ", maxindex=" + maxindex + ", language=" + language + ", subtitle=" + subtitle + ", idgroup="
				+ idgroup + "]";
	}

	public String getMaxindex() {
		return maxindex;
	}

	public void setMaxindex(String maxindex) {
		this.maxindex = maxindex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(String idgroup) {
		this.idgroup = idgroup;
	}

}
