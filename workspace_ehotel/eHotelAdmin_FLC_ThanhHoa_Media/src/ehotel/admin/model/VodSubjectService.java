package ehotel.admin.model;

public class VodSubjectService {
	private String subjectid = "";
	private String subjectname = "";
	private String subjectimage = "";
	private String subjectparent = "";
	private String subjectinvisible = "";

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectparent() {
		return subjectparent;
	}

	public void setSubjectparent(String subjectparent) {
		this.subjectparent = subjectparent;
	}

	public String getSubjectinvisible() {
		return subjectinvisible;
	}

	public void setSubjectinvisible(String subjectinvisible) {
		this.subjectinvisible = subjectinvisible;
	}

	public String getSubjectimage() {
		return subjectimage;
	}

	public void setSubjectimage(String subjectimage) {
		this.subjectimage = subjectimage;
	}
}
