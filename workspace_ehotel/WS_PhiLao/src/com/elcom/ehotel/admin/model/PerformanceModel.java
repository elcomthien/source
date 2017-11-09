package com.elcom.ehotel.admin.model;

public class PerformanceModel {
	private String name = "";
	private String total = "";
	private String used = "";
	private String free = "";
	private String usedPercent = "";
	private String freePercent = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getUsedPercent() {
		return usedPercent;
	}

	public void setUsedPercent(String usedPercent) {
		this.usedPercent = usedPercent;
	}

	public String getFreePercent() {
		return freePercent;
	}

	public void setFreePercent(String freePercent) {
		this.freePercent = freePercent;
	}

	@Override
	public String toString() {
		return "PerformanceModel [name=" + name + ", total=" + total + ", used=" + used + ", free=" + free + ", usedPercent=" + usedPercent
				+ ", freePercent=" + freePercent + "]";
	}

}
