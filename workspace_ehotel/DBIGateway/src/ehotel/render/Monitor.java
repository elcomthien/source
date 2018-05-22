package ehotel.render;

import ehotel.impl.MonitorImp;
import ehotel.inter.IMonitor;

public class Monitor {
	public IMonitor getMonitor() {
		return new MonitorImp();
	}
}
