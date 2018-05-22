package ehotel.domain.liveTV;

public class AnalysiLiveTV {
	private String channelId;
	private String channelName;
	private String channelUrl;

	public AnalysiLiveTV() {

	}

	public AnalysiLiveTV(String channelId, String channelName, String channelUrl) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelUrl = channelUrl;
	}

	public String toString() {
		return "AnalysiLiveTV[channelId=" + channelId + ",channelName="
				+ channelName + ",channelUrl=" + channelUrl + "]";
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

}
