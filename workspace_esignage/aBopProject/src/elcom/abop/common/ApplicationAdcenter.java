package elcom.abop.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationAdcenter implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Application shutdown");
		Constant.getScheduledTaskConstant().cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Application start");
		Constant.getScheduledTaskConstant();
	}

}
