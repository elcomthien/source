package ehotel.impl;

import java.util.Vector;

import ehotel.dao.eMonitorDao;
import ehotel.domain.vod.EServer;
import ehotel.inter.IMonitor;

public class MonitorImp implements IMonitor {
	eMonitorDao eDao;

	public MonitorImp() {
		eDao = new eMonitorDao();
	}

	@Override
	public Vector<EServer> getServers() {
		// TODO Auto-generated method stub
		return eDao.getServers();
	}

	@Override
	public EServer getEServer(String ip) {
		// TODO Auto-generated method stub
		return eDao.getEServerInfo(ip);
	}

	@Override
	public boolean addServer(EServer server) {
		// TODO Auto-generated method stub
		return eDao.addServer(server);
	}

}
