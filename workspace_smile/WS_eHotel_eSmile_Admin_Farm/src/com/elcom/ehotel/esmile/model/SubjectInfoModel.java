package com.elcom.ehotel.esmile.model;

public class SubjectInfoModel {
private String id = "";
private String name = "";
private String langid = "";
private String invisible = "";
private String image = "";
private String index = "";
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getIndex() {
	return index;
}
public void setIndex(String index) {
	this.index = index;
}
@Override
public String toString() {
	return "SubjectInfoModel [id=" + id + ", name=" + name + ", langid=" + langid + ", invisible=" + invisible + ", image=" + image
			+ ", index=" + index + "]";
}

}
