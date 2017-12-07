package elcom.domain;

public class FolioMessage {
	private int id;
	private String subject;
	private String profile;// ngan gon
	private String content_top;
	private String content_bottom;
	private String sender;
	private String ICompany;
	private String IPhone;
	private String postedDate;
	private String postedTime;
	private int isRead;
	private int isConfirm;
	private String type;// CONFIRM, NORMAL,ORDER
	private String checkNo;

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public FolioMessage() {
	}

	public String toString() {
		return "FolioMessage[messageId=" + id + ",messageTxt=" + content_top
				+ ",isRead=" + isRead + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getICompany() {
		return ICompany;
	}

	public void setICompany(String iCompany) {
		ICompany = iCompany;
	}

	public String getIPhone() {
		return IPhone;
	}

	public void setIPhone(String iPhone) {
		IPhone = iPhone;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent_top() {
		return content_top;
	}

	public void setContent_top(String content_top) {
		this.content_top = content_top;
	}

	public String getContent_bottom() {
		return content_bottom;
	}

	public void setContent_bottom(String content_bottom) {
		this.content_bottom = content_bottom;
	}

	public String getPostedTime() {
		return postedTime;
	}

	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}

}
