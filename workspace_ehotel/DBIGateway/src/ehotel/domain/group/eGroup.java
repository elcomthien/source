package ehotel.domain.group;

public class eGroup {
	private int groupId;
	private String name;

	public eGroup() {

	}

	public String toString() {
		return "eGroup[id=" + groupId + ",name=" + name + "]";
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
