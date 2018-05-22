package ehotel.domain.vod;

public class AnalysicVideo {
	private String src_id;
	private String title;
	private String description;
	private String url_video;
	private String url_play_sd;
	private String actors;
	private String directors;
	private String duration;
	private String types;
	private String country;
	private String languages;
	private String subtitle;
	private String levels;
	private String price;
	private String url_trailer;
	private String url_request;// url de send request lay thong tin
	private int despage;// src_id o page thu may
	private String columnid;
	private String vodType;// HD,SD

	public AnalysicVideo() {

	}

	public String toString() {
		return "AnalysicVideo[  vasc_id=" + src_id + ",title=" + title
				+ ",types=" + types + "\n" + "urlplay_hd=" + url_video + "\n"
				+ "url_trailer=" + url_trailer + "\n urlplay_sd=" + url_play_sd
				+ "\n" + "vodType=" + vodType + "\n" + "actors=" + actors
				+ "\n" + "url_request=" + url_request + "]";
	}

	public AnalysicVideo(String src_id, String title, String description,
			String actors, String directors, String duration, String types,
			String country, String languages, String subtitle, String levels) {
		this.src_id = src_id;
		this.title = title;
		this.description = description;
		this.actors = actors;
		this.directors = directors;
		this.duration = duration;
		this.types = types;
		this.country = country;
		this.languages = languages;
		this.subtitle = subtitle;
		this.levels = levels;
	}

	public String getVodType() {
		return vodType;
	}

	public void setVodType(String vodType) {
		this.vodType = vodType;
	}

	public String getUrl_play_sd() {
		return url_play_sd;
	}

	public void setUrl_play_sd(String url_play_sd) {
		this.url_play_sd = url_play_sd;
	}

	public String getUrl_trailer() {
		return url_trailer;
	}

	public void setUrl_trailer(String url_trailer) {
		this.url_trailer = url_trailer;
	}

	public String getUrl_request() {
		return url_request;
	}

	public void setUrl_request(String url_request) {
		this.url_request = url_request;
	}

	public int getDespage() {
		return despage;
	}

	public void setDespage(int despage) {
		this.despage = despage;
	}

	public String getPrice() {
		return price;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSrc_id() {
		return src_id;
	}

	public void setSrc_id(String src_id) {
		this.src_id = src_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl_video() {
		return url_video;
	}

	public void setUrl_video(String url_video) {
		this.url_video = url_video;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

}
