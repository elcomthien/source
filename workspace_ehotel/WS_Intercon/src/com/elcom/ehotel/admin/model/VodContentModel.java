package com.elcom.ehotel.admin.model;

public class VodContentModel {
	private String idSubject = "";
	private String idContent = "";
	private String name = "";
	private String iunit = "";
	private String productor = "";
	private String director = "";
	private String actor = "";
	private String poster = "";
	private String plot = "";
	private String price = "";
	private String langid = "";
	private String url = "";
	private String invisible = "";
	private String subtitle = "";
	private String isnew = "";
	private String listSub = "";
	private String idgroup = "";

	public String getIdSubject() {
		return idSubject;
	}

	public String getIsnew() {
		return isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getListSub() {
		return listSub;
	}

	public void setListSub(String listSub) {
		this.listSub = listSub;
	}

	public String getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(String idgroup) {
		this.idgroup = idgroup;
	}

	@Override
	public String toString() {
		return "VodContentModel [idSubject=" + idSubject + ", idContent=" + idContent + ", name=" + name + ", iunit=" + iunit
				+ ", productor=" + productor + ", director=" + director + ", actor=" + actor + ", poster=" + poster + ", plot=" + plot
				+ ", price=" + price + ", langid=" + langid + ", url=" + url + ", invisible=" + invisible + ", subtitle=" + subtitle
				+ ", isnew=" + isnew + ", listSub=" + listSub + ", idgroup=" + idgroup + "]";
	}
}
