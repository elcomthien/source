package ehotel.domain.liveTV;

/**
 * LiveTV class provides necessary properties to create and contain channel
 * information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public class LiveTV implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int channelid;
	private int serverid;
	private String channelname;
	private int channelcode;
	private String servicename;
	private String physical_address;
	private String createDate;
	private int active;
	private int coreId;
	private String urlImage;
	private String urlBg;

	public LiveTV() {
	}

	public String toString() {
		return "LiveTV[channelid=" + channelid + ",channelName=" + channelname
				+ ",servicename=" + servicename + ",urlImage=" + urlImage
				+ ",ative=" + active + "]";
	}

	public int getCoreId() {
		return coreId;
	}

	public void setCoreId(int coreId) {
		this.coreId = coreId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getChannelid() {
		return channelid;
	}

	public void setChannelid(int channelid) {
		this.channelid = channelid;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public int getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(int channelcode) {
		this.channelcode = channelcode;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getPhysical_address() {
		return physical_address;
	}

	public void setPhysical_address(String physical_address) {
		this.physical_address = physical_address;
	}

	public int getServerid() {
		return serverid;
	}

	public void setServerid(int serverid) {
		this.serverid = serverid;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlBg() {
		return urlBg;
	}

	public void setUrlBg(String urlBg) {
		this.urlBg = urlBg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
