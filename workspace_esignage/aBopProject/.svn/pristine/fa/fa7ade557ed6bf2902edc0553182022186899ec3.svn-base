package elcom.abop.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import org.apache.axis2.AxisFault;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub;

import elcom.abop.action.ScheduledTask;
import elcom.abop.action.Task;
import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.ObjectSchedule;
import elcom.abop.bean.User;

public class Constant {
	private static User user;
	private static AdcenterAdminServiceStub adcenter;
	private static ObjectBean bean;
	private static ArrayList<ObjectSchedule> queue;
	private static ScheduledTask scheduledTask;
	private static Timer timer;
	private static Task<ObjectSchedule> task;

	public static void setObjectBean(ObjectBean bean) {
		Constant.bean = bean;
	}
	public static Timer getTimerConstant(){
		if(timer == null){
			timer = new Timer();
		}
		return timer;
	}
	public static Task<ObjectSchedule> getTaskConstant(){
		if(task == null){
			task = new Task<ObjectSchedule>();
		}
		return task;
	}
	public static ScheduledTask getScheduledTaskConstant(){
		if(scheduledTask == null){
			Timer time = new Timer();
			scheduledTask = new ScheduledTask();
			time.schedule(scheduledTask, 0, 5000);// run every 5 seconds
		}
		return scheduledTask;
	}
	public static ArrayList<ObjectSchedule> getQueueConstant(){
		if(queue == null){
			queue = new ArrayList<ObjectSchedule>();
		}
		return queue;
	}
	public static User getUserConstant() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public static AdcenterAdminServiceStub getServiceStub() {
		if (adcenter == null) {
			try {
				adcenter = new AdcenterAdminServiceStub(ConstantProperties.END_POINT);
			} catch (AxisFault e) {
				e.printStackTrace();
			}
		}
		return adcenter;
	}

	public static ObjectBean getObjectBean() {
		if (bean == null) {
			bean = new ObjectBean();
		}
		return bean;
	}

}
