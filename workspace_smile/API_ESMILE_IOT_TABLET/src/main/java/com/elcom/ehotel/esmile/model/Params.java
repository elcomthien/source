package com.elcom.ehotel.esmile.model;

public class Params {
	String username = "";
	String password = "";
	String role_id = "";
	String user_id = "";
	String name_layout = "";
	String layout_direction = "";
	String table_id = "";
	String posx = "";
	String posy = "";
	String width = "";
	String height = "";
	String button_id = "";
	String emp_id = "";
	String from_date = "";
	String to_date = "";
	String area_id = "";
	String store_id = "";
	String from = "";
	String to = "";
	String service_name = "";
	String status = "";
	String device_id = "";

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName_layout() {
		return name_layout;
	}

	public void setName_layout(String name_layout) {
		this.name_layout = name_layout;
	}

	public String getLayout_direction() {
		return layout_direction;
	}

	public void setLayout_direction(String layout_direction) {
		this.layout_direction = layout_direction;
	}

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	public String getPosx() {
		return posx;
	}

	public void setPosx(String posx) {
		this.posx = posx;
	}

	public String getPosy() {
		return posy;
	}

	public void setPosy(String posy) {
		this.posy = posy;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getButton_id() {
		return button_id;
	}

	public void setButton_id(String button_id) {
		this.button_id = button_id;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	@Override
	public String toString() {
		return "Params [username=" + username + ", password=" + password + ", role_id=" + role_id + ", user_id=" + user_id
				+ ", name_layout=" + name_layout + ", layout_direction=" + layout_direction + ", table_id=" + table_id + ", posx=" + posx
				+ ", posy=" + posy + ", width=" + width + ", height=" + height + ", button_id=" + button_id + ", emp_id=" + emp_id
				+ ", from_date=" + from_date + ", to_date=" + to_date + ", area_id=" + area_id + ", store_id=" + store_id + ", from="
				+ from + ", to=" + to + ", service_name=" + service_name + ", status=" + status + ", device_id=" + device_id + "]";
	}

}
