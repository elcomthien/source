package ehotel.domain.pms;

public class eSTB {
	private int id;
	private String keyCode;
	private String IP;
	private String createdate;
	private int status;
	private String roomCode;

	public eSTB() {
	}

	public String toString() {
		return "eSTB[id=" + id + ",keyCode=" + keyCode + ",IP=" + IP
				+ ",roomCode=" + roomCode + "]";
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

}
