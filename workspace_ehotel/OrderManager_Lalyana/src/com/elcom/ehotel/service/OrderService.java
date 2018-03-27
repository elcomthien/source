package com.elcom.ehotel.service;

import com.elcom.ehotel.dao.OrderDao;
import com.elcom.ehotel.model.DiningModel;

import java.util.List;

public class OrderService {

	public OrderService() {
		orderDao = new OrderDao();
	}

	public List<DiningModel> getNewOrder(String role) {
		return orderDao.getNewOrder(role);
	}

	@SuppressWarnings("rawtypes")
	public List getNewHousekeeping() {
		return orderDao.getNewHousekeeping();
	}

	@SuppressWarnings("rawtypes")
	public List getNewWakup() {
		return orderDao.getNewWakup();
	}

	public boolean confirmOrder(int id, int folionum, String service, String user) {
		boolean flag = true;
		flag = orderDao.confirmOrder(id);
		flag = orderDao.insertMessageFolioConfirm(folionum, service);
//		flag = orderDao.addUserActivities((new StringBuilder("Confirmed order of room ")).append(folionum).toString(), user);
		return flag;
	}

	public boolean denyOrder(int id, int folionum, String service, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusOrder(id, 2);
		flag = orderDao.insertMessageFolioDeny(folionum, service);
		flag = orderDao.addUserActivities((new StringBuilder("Denied order of room ")).append(folionum).toString(), user);
		return flag;
	}

	public boolean deleteOrder(int id) {
		return orderDao.updateStatusDeleteOrder(id);
	}

	@SuppressWarnings("rawtypes")
	public List filterOrder(String service, String folio, String role) {
		return orderDao.filterOrder(service, folio,role);
	}

	public boolean deleteListOrder(String listid) {
		boolean flag = false;
		String arr[] = listid.split(",");
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]);
			flag = deleteOrder(id);
		}

		return flag;
	}

	public boolean confirmHousekeeping(int id, int folionum, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusHousekeeping(id, 1);
		flag = orderDao.addUserActivities((new StringBuilder("Confirmed housekeeping of room ")).append(folionum).toString(), user);
		return flag;
	}

	public boolean deleteHousekeeping(int id, int folionum, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusRemoveHousekeeping(id);
		return flag;
	}

	public boolean confirmWakeup(int id, int folionum, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusWakeup(id, 1);
		flag = orderDao.insertMessageFolioConfirm(folionum, "WAKEUP");
		flag = orderDao.addUserActivities((new StringBuilder("Confirmed wakeup of room ")).append(folionum).toString(), user);
		return flag;
	}

	public boolean deleteWakeup(int id, int folionum, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusRemoveWakeup(id);
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public List checkNewOrder() {
		return orderDao.checkOrder();
	}

	@SuppressWarnings("rawtypes")
	public List getListRoomStatus() {
		return orderDao.getListRoomStatus();
	}

	public boolean confirmRoomStatus(int folionum, String user) {
		boolean flag = true;
		flag = orderDao.updateStatusRoomStatus(folionum, 1);
		flag = orderDao.insertMessageFolioConfirm(folionum, "ROOMSTATUS");
		flag = orderDao.addUserActivities((new StringBuilder("Confirmed room status of room ")).append(folionum).toString(), user);
		return flag;
	}

	private OrderDao orderDao;
}
