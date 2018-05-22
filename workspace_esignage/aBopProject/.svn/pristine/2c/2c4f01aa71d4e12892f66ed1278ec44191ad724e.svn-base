package elcom.abop.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import elcom.abop.bean.ObjectSchedule;
import elcom.abop.common.Constant;
import elcom.abop.util.ModelService;
import elcom.abop.util.UtilBasic;
import elcom.abop.util.XmlService;

public class ScheduledTask extends TimerTask {
	public void run() {
		Date date = new Date();
		int total = date.getHours()*3600 + date.getMinutes()*60 + date.getSeconds();
		Task<ObjectSchedule> task = Constant.getTaskConstant();
		ArrayList<ObjectSchedule> list = task.readItem();
		int length = list.size();
		for(int  i = 0; i < length; i++){
			System.out.println("Task " + (i+1) + ": Time wait " + (list.get(i).getTimer() - total) + " seconds");
			if(total >= list.get(i).getTimer()){
				System.out.println("Start upload file");
				// Push content into settopbox
				String XMLCREATE = XmlService.xmlPushContentIntoSTB(list.get(i).getIdDaily(), list.get(i).getIdGroup());
				try {
					ModelService.pushContentIntoSTBAuto(XMLCREATE);
					//remove item  in file
					task.removeItem(list.get(i).getIdPeriodic());					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
