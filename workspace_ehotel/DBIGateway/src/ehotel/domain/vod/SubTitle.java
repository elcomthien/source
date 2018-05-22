package ehotel.domain.vod;

/**
 * Subtitle class provides necessary properties to create and contain Movie's
 * Subtitle information.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public class SubTitle implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subId;
	private String name;
	private String url;
	private int langId;

	/**
	 * Constructor used to create this object.
	 */
	public SubTitle() {

	}

	public SubTitle(String url, int langid) {
		this.url = url;
		this.langId = langid;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public String getName() {
		return name;
	}

	public void setName(String subtitle) {
		this.name = subtitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String toString() {
		return "SubTitle[url=" + url + ",langid=" + langId + "]";
	}
}
