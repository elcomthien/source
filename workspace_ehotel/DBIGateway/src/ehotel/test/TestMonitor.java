package ehotel.test;

import ehotel.domain.vod.EServer;
import ehotel.inter.IMonitor;
import ehotel.render.DBIGateway;
import ehotel.render.Monitor;

public class TestMonitor {
	IMonitor m;

	public TestMonitor() {
		Monitor dBI = DBIGateway.getAMDMonitor();
		m = dBI.getMonitor();
	}

	public void testAddServer() {
		EServer s = new EServer("192.168.0.140", "1023", "hehehe");
		m.addServer(s);
	}

	public void testGetServer() {
		EServer s = m.getEServer("192.168.0.140");
		System.out.println(s.toString());
	}

	public static void main(String[] arg) {
		TestMonitor mm = new TestMonitor();
		mm.testGetServer();
	}
}
