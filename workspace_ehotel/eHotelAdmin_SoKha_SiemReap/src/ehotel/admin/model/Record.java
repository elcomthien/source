package ehotel.admin.model;

public class Record {
	private int id;
	private String channalname;
	private String urlrecord;
	private String sernumber;
	private String starttime;
	private String stoptime;
	private int status;
	private String privatechannelname;
	private int sizeinkb;
	private String urlpicture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannalname() {
		return channalname;
	}

	public void setChannalname(String channalname) {
		this.channalname = channalname;
	}

	public String getUrlrecord() {
		return urlrecord;
	}

	public void setUrlrecord(String urlrecord) {
		this.urlrecord = urlrecord;
	}

	public String getSernumber() {
		return sernumber;
	}

	public void setSernumber(String sernumber) {
		this.sernumber = sernumber;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPrivatechannelname() {
		return privatechannelname;
	}

	public void setPrivatechannelname(String privatechannelname) {
		this.privatechannelname = privatechannelname;
	}

	public int getSizeinkb() {
		return sizeinkb;
	}

	public void setSizeinkb(int sizeinkb) {
		this.sizeinkb = sizeinkb;
	}

	public String getUrlpicture() {
		return urlpicture;
	}

	public void setUrlpicture(String urlpicture) {
		this.urlpicture = urlpicture;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", channalname=" + channalname + ", urlrecord=" + urlrecord + ", sernumber=" + sernumber
				+ ", starttime=" + starttime + ", stoptime=" + stoptime + ", status=" + status + ", privatechannelname="
				+ privatechannelname + ", sizeinkb=" + sizeinkb + ", urlpicture=" + urlpicture + "]";
	}

	public Record(int id, String channalname, String urlrecord, String sernumber, String starttime, String stoptime, int status,
			String privatechannelname, int sizeinkb, String urlpicture) {
		super();
		this.id = id;
		this.channalname = channalname;
		this.urlrecord = urlrecord;
		this.sernumber = sernumber;
		this.starttime = starttime;
		this.stoptime = stoptime;
		this.status = status;
		this.privatechannelname = privatechannelname;
		this.sizeinkb = sizeinkb;
		this.urlpicture = urlpicture;
	}

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channalname == null) ? 0 : channalname.hashCode());
		result = prime * result + id;
		result = prime * result + ((privatechannelname == null) ? 0 : privatechannelname.hashCode());
		result = prime * result + ((sernumber == null) ? 0 : sernumber.hashCode());
		result = prime * result + sizeinkb;
		result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
		result = prime * result + status;
		result = prime * result + ((stoptime == null) ? 0 : stoptime.hashCode());
		result = prime * result + ((urlpicture == null) ? 0 : urlpicture.hashCode());
		result = prime * result + ((urlrecord == null) ? 0 : urlrecord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (channalname == null) {
			if (other.channalname != null)
				return false;
		} else if (!channalname.equals(other.channalname))
			return false;
		if (id != other.id)
			return false;
		if (privatechannelname == null) {
			if (other.privatechannelname != null)
				return false;
		} else if (!privatechannelname.equals(other.privatechannelname))
			return false;
		if (sernumber == null) {
			if (other.sernumber != null)
				return false;
		} else if (!sernumber.equals(other.sernumber))
			return false;
		if (sizeinkb != other.sizeinkb)
			return false;
		if (starttime == null) {
			if (other.starttime != null)
				return false;
		} else if (!starttime.equals(other.starttime))
			return false;
		if (status != other.status)
			return false;
		if (stoptime == null) {
			if (other.stoptime != null)
				return false;
		} else if (!stoptime.equals(other.stoptime))
			return false;
		if (urlpicture == null) {
			if (other.urlpicture != null)
				return false;
		} else if (!urlpicture.equals(other.urlpicture))
			return false;
		if (urlrecord == null) {
			if (other.urlrecord != null)
				return false;
		} else if (!urlrecord.equals(other.urlrecord))
			return false;
		return true;
	}

}
