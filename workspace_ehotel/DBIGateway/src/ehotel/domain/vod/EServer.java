package ehotel.domain.vod;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EServer implements Serializable {
	private String ip;
	private String srvmonitor_port;
	private String srvmonitor_name;

	public EServer() {

	}

	public EServer(String ip, String srvmonitor_port, String srvmonitor_name) {
		this.ip = ip;
		this.srvmonitor_port = srvmonitor_port;
		this.srvmonitor_name = srvmonitor_name;

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSrvmonitor_port() {
		return srvmonitor_port;
	}

	public void setSrvmonitor_port(String srvmonitor_port) {
		this.srvmonitor_port = srvmonitor_port;
	}

	public String getSrvmonitor_name() {
		return srvmonitor_name;
	}

	public void setSrvmonitor_name(String srvmonitor_name) {
		this.srvmonitor_name = srvmonitor_name;
	}

	public String toString() {
		return "EServer[ip=" + ip + ",srvmonitor_port=" + srvmonitor_port
				+ ",srvmonitor_name=" + srvmonitor_name + "]";
	}
}
