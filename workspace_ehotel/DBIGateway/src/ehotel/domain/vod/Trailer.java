package ehotel.domain.vod;

/**
 * Trailer class provides necessary properties to create and contain Trailer
 * information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
@SuppressWarnings("serial")
public class Trailer implements java.io.Serializable {
	private int trailerId;
	private String trailerUrl;
	private int duration = 0;
	private String OVSServer;

	public Trailer() {

	}

	public Trailer(String trailerUrl, int duration) {
		this.trailerUrl = trailerUrl;
		this.duration = duration;
	}

	public int getTrailerId() {
		return trailerId;
	}

	public void setTrailerId(int trailerId) {
		this.trailerId = trailerId;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getOVSServer() {
		return OVSServer;
	}

	public void setOVSServer(String oVSServer) {
		OVSServer = oVSServer;
	}

	public String toString() {
		return "Trailer[trailerUrl=" + trailerUrl + ",duration=" + duration
				+ ",OVSServer=" + OVSServer + "]";
	}

}
