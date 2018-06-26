package ehotel.admin.LiveTV;

public class LiveModel {
	private String channelId = "";
	private String channelName = "";
	private String url = "";
	private String image = "";

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LiveModel [channelId=" + channelId + ", channelName=" + channelName + ", url=" + url + ", image=" + image + "]";
	}

	public LiveModel(String channelId, String channelName, String url, String image) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.url = url;
		this.image = image;
	}

	public LiveModel() {
		super();
	}

}
