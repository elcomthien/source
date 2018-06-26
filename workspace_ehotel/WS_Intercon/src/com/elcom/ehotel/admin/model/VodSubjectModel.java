package com.elcom.ehotel.admin.model;

public class VodSubjectModel {
	private String id = "";
	private String name = "";
	private String createdate = "";
	private String parent = "";
	private String invisible = "";
	private String image = "";
	private String imageIC = "";
	private String langid = "";
	private String index = "";
	private String listadd = "";
	private String listremove = "";
	private String type = "";
	private String idGroup = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "VodSubjectModel [id=" + id + ", name=" + name + ", createdate=" + createdate + ", parent=" + parent + ", invisible="
				+ invisible + ", image=" + image + ", imageIC=" + imageIC + ", langid=" + langid + ", index=" + index + ", listadd="
				+ listadd + ", listremove=" + listremove + ", type=" + type + ", idGroup=" + idGroup + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getInvisible() {
		return invisible;
	}

	public void setInvisible(String invisible) {
		this.invisible = invisible;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getListadd() {
		return listadd;
	}

	public void setListadd(String listadd) {
		this.listadd = listadd;
	}

	public String getListremove() {
		return listremove;
	}

	public void setListremove(String listremove) {
		this.listremove = listremove;
	}

	public String getLangid() {
		return langid;
	}

	public void setLangid(String langid) {
		this.langid = langid;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageIC() {
		return imageIC;
	}

	public void setImageIC(String imageIC) {
		this.imageIC = imageIC;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

}
