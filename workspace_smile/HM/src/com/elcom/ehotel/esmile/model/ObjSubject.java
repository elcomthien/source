package com.elcom.ehotel.esmile.model;

public class ObjSubject {

	// MMENUNO_ number,
	// MMENUNAME_ varchar2(2000),
	// MMENUACTIVE_ number,
	// RVCSERVICE_ID_ number,
	// LANG_ID_ number,
	// INVISIBLE_ number,
	// ORDERBY_ number,
	// ISRVC_ number,
	// IMAGE_ID_ number,
	// MENUTYPE_ varchar2(50),
	// URL_BACKGROUND_ varchar2(500),
	// URL_IMAGE_ varchar2(500),
	// URL_PICICON_ varchar2(500)

	String menuno, menuname, menuactive, rvcservice, langid, invisible, orderby, isrvs, imageid, menutype, urlbg,
			urlimage, urlicon;

	public ObjSubject(String menuno, String menuname, String menuactive, String rvcservice, String langid,
			String invisible, String orderby, String isrvs, String imageid, String menutype, String urlbg,
			String urlimage, String urlicon) {
		this.menuno = menuno;
		this.menuname = menuname;
		this.menuactive = menuactive;
		this.rvcservice = rvcservice;
		this.langid = langid;
		this.invisible = invisible;
		this.orderby = orderby;
		this.isrvs = isrvs;
		this.imageid = imageid;
		this.menutype = menutype;
		this.urlbg = urlbg;
		this.urlimage = urlimage;
		this.urlicon = urlicon;
	}

	public String getMenuno() {
		return menuno;
	}

	public void setMenuno(String menuno) {
		this.menuno = menuno;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuactive() {
		return menuactive;
	}

	public void setMenuactive(String menuactive) {
		this.menuactive = menuactive;
	}

	public String getRvcservice() {
		return rvcservice;
	}

	public void setRvcservice(String rvcservice) {
		this.rvcservice = rvcservice;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getIsrvs() {
		return isrvs;
	}

	public void setIsrvs(String isrvs) {
		this.isrvs = isrvs;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getUrlbg() {
		return urlbg;
	}

	public void setUrlbg(String urlbg) {
		this.urlbg = urlbg;
	}

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public String getUrlicon() {
		return urlicon;
	}

	public void setUrlicon(String urlicon) {
		this.urlicon = urlicon;
	}
	
	
}
