package com.elcom.ehotel.esmile.model;

public class ObjItem {
	// INVISIBLE_ number,
	// OUTLET_TYPE_ varchar2(2000),
	// NAME_ varchar2(2000),
	// DEF_ varchar2(2000),
	// LANG_ID_ number,
	// URL_IMAGE_ varchar2(2000),
	// URL_BACKGROUND_ varchar2(2000),
	// URL_PICICON_ varchar2(2000)
	String id, invisible, menutype, name, def, langid, urlimage, urlbackground, urlpicicon;

	public ObjItem(String id, String invisible, String menutype, String name, String def, String langid, String urlimage,
			String urlbackground, String urlpicicon) {
		this.id=id;
		this.invisible = invisible;
		this.menutype = menutype;
		this.name = name;
		this.def = def;
		this.langid = langid;
		this.urlimage = urlimage;
		this.urlbackground = urlbackground;
		this.urlpicicon = urlpicicon;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public String getUrlbackground() {
		return urlbackground;
	}

	public void setUrlbackground(String urlbackground) {
		this.urlbackground = urlbackground;
	}

	public String getUrlpicicon() {
		return urlpicicon;
	}

	public void setUrlpicicon(String urlpicicon) {
		this.urlpicicon = urlpicicon;
	}

}
