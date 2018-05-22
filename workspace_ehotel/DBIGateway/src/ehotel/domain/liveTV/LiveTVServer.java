package ehotel.domain.liveTV;

public class LiveTVServer implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String serverName;

	public LiveTVServer() {

	}

	public int getOvsId() {
		return Id;
	}

	public void setOvsId(int ovsId) {
		this.Id = ovsId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
