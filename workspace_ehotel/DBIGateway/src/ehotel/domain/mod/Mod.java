package ehotel.domain.mod;

/**
 * Mod class provides necessary properties to create and contain Song
 * information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
@SuppressWarnings("serial")
public class Mod implements java.io.Serializable {
	private int Id;
	/**
	 * song name
	 */
	private String title;
	private String singer;
	private String duration;
	private String composer;
	private String lyric;

	private String url;
	private int length = 0;
	private String album;
	/**
	 * mp3 file name
	 */
	private String fileName;
	private boolean isClip;
	private int vodNum = 0;// the number which link to vod. when mod is audio
							// clip

	public Mod() {
	}

	public Mod(String title, String fileName, boolean isClip) {
		this.title = title;
		this.fileName = fileName;
		this.isClip = isClip;
	}

	public Mod(String title, String singer, String album, String duration,
			String composer, String lyric, String url, int length) {
		this.title = title;
		this.singer = singer;
		this.album = album;
		this.duration = duration;
		this.composer = composer;
		this.lyric = lyric;
		this.length = length;
	}

	public String toString() {
		return "Mod[id=" + Id + ",title=" + title + ",singer=" + singer
				+ ",album=" + album + ",duration=" + duration + ",composer="
				+ composer + ",lyric=" + lyric + ",length=" + length + ",url="
				+ url + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isClip() {
		return isClip;
	}

	public void setClip(boolean isClip) {
		this.isClip = isClip;
	}

	public int getVodNum() {
		return vodNum;
	}

	public void setVodNum(int vodNum) {
		this.vodNum = vodNum;
	}

}
