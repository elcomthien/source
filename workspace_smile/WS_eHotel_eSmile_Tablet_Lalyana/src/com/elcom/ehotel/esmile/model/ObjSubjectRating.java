package com.elcom.ehotel.esmile.model;

public class ObjSubjectRating {
	// ID_ number,
	// SUBJNAME_ varchar2(2000),
	// SYSDATESUBJ_ varchar2(100),
	// LANGID_ number,
	// DEF_ varchar2(2000)
	String id, subjectname, sysdate, langid, def;

	public ObjSubjectRating(String id, String subjectname, String sysdate, String langid, String def) {
		this.id = id;
		this.subjectname = subjectname;
		this.sysdate = sysdate;
		this.langid = langid;
		this.def = def;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "Subject Rating Object [id=" + id + ", langid=" + langid + ", subjname=" + subjectname + ", sysdate="
				+ sysdate + ", def=" + def + "]";
	}

}
