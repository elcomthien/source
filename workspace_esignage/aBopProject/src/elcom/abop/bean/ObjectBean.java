package elcom.abop.bean;

import java.util.ArrayList;
import java.util.Map;

public class ObjectBean {
	private String data;
	private ArrayList<ObjectABOP> layout;
	private ArrayList<ObjectABOP> layoutItem;
	private ArrayList<ObjectABOP> group;
	private ArrayList<ObjectABOP> player;
	private ArrayList<ObjectABOP> playerOutGroup;
	private ArrayList<ObjectABOP> content;
	private ArrayList<ObjectABOP> contentOutGroup;
	private ArrayList<ObjectABOP> playlist;
	private ArrayList<ObjectABOP> scheduleDaily;
	private ArrayList<ObjectABOP> schedulePeriodic;
	private ArrayList<ObjectABOP> subject;
	private ArrayList<User> users;
	private ArrayList<ObjectABOP> role;
	private ArrayList<ObjectQMS> contentQMS; 

	public ArrayList<ObjectABOP> getRole() {
		return role;
	}

	public void setRole(ArrayList<ObjectABOP> role) {
		this.role = role;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<ObjectABOP> getSubject() {
		return subject;
	}

	public void setSubject(ArrayList<ObjectABOP> subject) {
		this.subject = subject;
	}

	private ObjectConfig config;

	public ArrayList<ObjectABOP> getContentOutGroup() {
		return contentOutGroup;
	}

	public void setContentOutGroup(ArrayList<ObjectABOP> contentOutGroup) {
		this.contentOutGroup = contentOutGroup;
	}

	public ArrayList<ObjectABOP> getSchedulePeriodic() {
		return schedulePeriodic;
	}

	public void setSchedulePeriodic(ArrayList<ObjectABOP> schedulePeriodic) {
		this.schedulePeriodic = schedulePeriodic;
	}

	public ArrayList<ObjectABOP> getLayoutItem() {
		return layoutItem;
	}

	public void setLayoutItem(ArrayList<ObjectABOP> layoutItem) {
		this.layoutItem = layoutItem;
	}

	public ArrayList<ObjectABOP> getScheduleDaily() {
		return scheduleDaily;
	}

	public void setScheduleDaily(ArrayList<ObjectABOP> scheduleDaily) {
		this.scheduleDaily = scheduleDaily;
	}

	public ArrayList<ObjectABOP> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(ArrayList<ObjectABOP> playlist) {
		this.playlist = playlist;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ArrayList<ObjectABOP> getPlayerOutGroup() {
		return playerOutGroup;
	}

	public void setPlayerOutGroup(ArrayList<ObjectABOP> playerOutGroup) {
		this.playerOutGroup = playerOutGroup;
	}

	public ArrayList<ObjectABOP> getGroup() {
		return group;
	}

	public void setGroup(ArrayList<ObjectABOP> group) {
		this.group = group;
	}

	public ArrayList<ObjectABOP> getPlayer() {
		return player;
	}

	public void setPlayer(ArrayList<ObjectABOP> player) {
		this.player = player;
	}

	public ArrayList<ObjectABOP> getLayout() {
		return layout;
	}

	public void setLayout(ArrayList<ObjectABOP> layout) {
		this.layout = layout;
	}

	public ArrayList<ObjectABOP> getContent() {
		return content;
	}

	public void setContent(ArrayList<ObjectABOP> content) {
		this.content = content;
	}

	public ObjectConfig getConfig() {
		return config;
	}

	public void setConfig(ObjectConfig config) {
		this.config = config;
	}

	public ArrayList<ObjectQMS> getContentQMS() {
		return contentQMS;
	}

	public void setContentQMS(ArrayList<ObjectQMS> contentQMS) {
		this.contentQMS = contentQMS;
	}

}