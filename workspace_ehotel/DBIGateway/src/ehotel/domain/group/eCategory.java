package ehotel.domain.group;

public class eCategory {
	private int cateId;
	private String name;
	private int level;
	private int parentId;

	public eCategory() {

	}

	public String toString() {
		return "eCategory[cateId=" + cateId + ",name=" + name + "]";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
