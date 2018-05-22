package ehotel.admin.Vod;

import java.util.UUID;

public class VodPercent {
	private String filename;
	private int seq;
	private String nameview;
	private UUID uid;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}


	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	

	public VodPercent(String filename, int seq, String nameview, UUID uid) {
		super();
		this.filename = filename;
		this.seq = seq;
		this.nameview = nameview;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "VodPercent [filename=" + filename + ", seq=" + seq + ", nameview=" + nameview + ", uid=" + uid + "]";
	}

	public VodPercent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNameview() {
		return nameview;
	}

	public void setNameview(String nameview) {
		this.nameview = nameview;
	}
	
}
