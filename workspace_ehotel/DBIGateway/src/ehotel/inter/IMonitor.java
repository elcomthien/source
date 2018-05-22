package ehotel.inter;

import java.util.Vector;

import ehotel.domain.vod.EServer;

public interface IMonitor {
	public Vector<EServer> getServers();

	public EServer getEServer(String ip);

	public boolean addServer(EServer server);
}
