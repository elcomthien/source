package ehotel.domain.vod;

/**
 * Vod class provides necessary properties to create and contain Movie
 * information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
@SuppressWarnings("serial")
public class Vod implements java.io.Serializable {
	private int id;
	private String title;
	private String director;
	private String writer;
	private String actors;
	private String plot;// description
	private String poster;// urlimage
	private String duration;// duration
	private String votes;
	private int Year;

	private String filePath;
	private String evsStorage;
	private String currency;

	/**
	 * units of money (USD,VND)
	 */
	private String IUnit;
	private boolean isAudio;// video thuoc mp4(clip)
	private int released;
	private int istrailer;// 1=have;0=not have
	private int issubtile;// 1=have;0=not have
	private int status;

	// phan dongbo ve stb
	private String sessionId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Constructor used to create this object.
	 */
	public Vod() {

	}

	public Vod(String title, String actors, String plot, String poster,
			String director, String currency, String iunit) {
		this.title = title;
		this.actors = actors;
		this.plot = plot;
		this.poster = poster;
		this.director = director;
		this.currency = currency;
		this.IUnit = iunit;
	}

	public String toString() {
		return "Vod[id=" + id + ",Title=" + title + ",Director=" + director
				+ ",Writer=" + writer + ",Actors=" + actors + ",Plot=" + plot
				+ ",Poster=" + poster + ",released=" + released + ",isTrailer="
				+ istrailer + ",issubtile=" + issubtile + "]";
	}

	/**
	 * get price of movie
	 */
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * get units of money
	 * 
	 */
	public String getIUnit() {
		return IUnit;
	}

	public void setIUnit(String iUnit) {
		this.IUnit = iUnit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getVotes() {
		return votes;
	}

	public void setVotes(String votes) {
		this.votes = votes;
	}

	public int getReleased() {
		return released;
	}

	public void setReleased(int released) {
		this.released = released;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getEvsStorage() {
		return evsStorage;
	}

	public void setEvsStorage(String evsStorage) {
		this.evsStorage = evsStorage;
	}

	public boolean isAudio() {
		return isAudio;
	}

	public void setAudio(boolean isAudio) {
		this.isAudio = isAudio;
	}

	public int getIstrailer() {
		return istrailer;
	}

	public void setIstrailer(int istrailer) {
		this.istrailer = istrailer;
	}

	public int getIssubtile() {
		return issubtile;
	}

	public void setIssubtile(int issubtile) {
		this.issubtile = issubtile;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
