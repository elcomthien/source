package ehotel.domain.mod;

public class AnalysicMusic {
	private String content_id;
	private String song_name;
	private String url_req;
	private String song_type;
	private String song_type_name;
	private String url_play;
	private int des_page;

	public AnalysicMusic() {

	}

	public String toString() {
		return "AnalysicMusic[content_id=" + content_id + ",song_name="
				+ song_name + ",url_req=" + url_req + ",song_type_name="
				+ song_type_name + ",urlplay=" + url_play + "]";
	}

	public String getSong_type_name() {
		return song_type_name;
	}

	public void setSong_type_name(String song_type_name) {
		this.song_type_name = song_type_name;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getSong_type() {
		return song_type;
	}

	public void setSong_type(String song_type) {
		this.song_type = song_type;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getUrl_req() {
		return url_req;
	}

	public void setUrl_req(String url_req) {
		this.url_req = url_req;
	}

	public String getUrl_play() {
		return url_play;
	}

	public void setUrl_play(String url_play) {
		this.url_play = url_play;
	}

	public int getDes_page() {
		return des_page;
	}

	public void setDes_page(int des_page) {
		this.des_page = des_page;
	}

}
