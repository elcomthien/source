package ehotel.admin.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.elcom.DBI.SubProParam;

import ehotel.admin.dbi.IMBroker;
import ehotel.admin.model.MinibarModel;
import ehotel.admin.model.RoomStatusModel;
import ehotel.admin.model.UserHouseKeepingModel;
import ehotel.utils.Utils;

public class HouseKeepingDao {
	static IMBroker broker = IMBroker.getInstance();
	public static final String GET_LIST_USER_HOUSEKEEPING = "BEGIN EPMS.getListUserHouseKeeping(?,?,?); END;";
	public static final String COUNT_USER_HOUSEKEEPING = "BEGIN EPMS.countUserHouseKeeping(?); END;";
	public static final String ADD_USER_HOUSEKEEPING = "BEGIN EPMS.addUserHouseKeeping(?,?,?,?,?); END;";
	public static final String CHANGE_STATUS_USER_HOUSEKEEPING = "BEGIN EPMS.changeStatusUserHouseKeeping(?); END;";
	public static final String DELETE_USER_HOUSEKEEPING = "BEGIN EPMS.deleteUserHouseKeeping(?); END;";
	public static final String EDIT_USER_HOUSEKEEPING = "BEGIN EPMS.editUserHouseKeeping(?,?,?,?,?,?); END;";
	
	public static final String GET_LIST_MINIBAR = "BEGIN EPMS.getListMinibar(?,?,?); END;";
	public static final String COUNT_MINIBAR = "BEGIN EPMS.countMinibar(?); END;";
	public static final String ADD_ITEM_MINIBAR = "BEGIN EPMS.addItemMinibar(?,?,?,?,?,?); END;";
	public static final String EDIT_ITEM_MINIBAR = "BEGIN EPMS.editItemMinibar(?,?,?,?,?,?,?); END;";
	public static final String DELETE_ITEM_MINIBAR = "BEGIN EPMS.deleteItemMinibar(?); END;";
	public static final String CHANGE_STATUS_ITEM_MINIBAR = "BEGIN EPMS.changeStatusItemMinibar(?); END;";
	
	public static final String GET_LIST_ROOM_STATUS = "BEGIN EPMS.getListRoomStatus(?,?,?); END;";
	public static final String COUNT_ROOM_STATUS = "BEGIN EPMS.countRoomStatus(?); END;";
	public static final String ADD_ROOM_STATUS = "BEGIN EPMS.addRoomStatus(?,?,?); END;";
	public static final String EDIT_ROOM_STATUS = "BEGIN EPMS.editRoomStatus(?,?,?,?); END;";
	public static final String DELETE_ROOM_STATUS= "BEGIN EPMS.deleteRoomStatus(?); END;";
	public static final String CHANGE_STATUS_ROOM_STATUS= "BEGIN EPMS.changeStatusRoomStatus(?); END;";
	

	@SuppressWarnings("unchecked")
	public List<UserHouseKeepingModel> getListUserHouseKeeping(int from, int to) {
		List<UserHouseKeepingModel> list = new ArrayList<UserHouseKeepingModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_USER_HOUSEKEEPING, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			UserHouseKeepingModel user = new UserHouseKeepingModel();
			user.setId(outParam.get(i));
			user.setUsername(outParam.get(i + 1));
			user.setPassword(outParam.get(i + 2));
			user.setStatus(outParam.get(i + 3));
			user.setModifydate(outParam.get(i + 4));
			user.setAddress(outParam.get(i + 5));
			user.setUsermodify(outParam.get(i + 6));
			user.setFullname(outParam.get(i + 7));
			list.add(user);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int countUserHouseKeeping() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2
		try {
			params = broker.executeSubPro(COUNT_USER_HOUSEKEEPING, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public int addUserHousekeeping(String account, String password, String name, String user){
		int id = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(account), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(ADD_USER_HOUSEKEEPING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(4);
				id = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	public boolean changeStatusUserHousekeeping(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(CHANGE_STATUS_USER_HOUSEKEEPING, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteUserHousekeeping(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(DELETE_USER_HOUSEKEEPING, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public int editUserHousekeeping(int id, String account, String password, String name, String user){
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(account), 0);
		params.add(in);
		in = new SubProParam(new String(password), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(EDIT_USER_HOUSEKEEPING, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				seq = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return seq;
	}
	
	@SuppressWarnings("unchecked")
	public List<MinibarModel> getListMinibar(int from, int to){
		List<MinibarModel> list = new ArrayList<MinibarModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_MINIBAR, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 8) {
			MinibarModel mini = new MinibarModel();
			mini.setId(outParam.get(i));
			mini.setName(outParam.get(i + 1));
			mini.setCode(outParam.get(i + 2));
			mini.setAmount(outParam.get(i + 3));
			mini.setPrice(outParam.get(i + 4));
			mini.setStatus(outParam.get(i + 5));
			mini.setModifydate(outParam.get(i + 6));
			mini.setUsermodify(outParam.get(i + 7));
			list.add(mini);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int countMinibar() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);// 2
		try {
			params = broker.executeSubPro(COUNT_MINIBAR, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public int addItemMinibar(String name, String code, String amount, String price, String user){
		int id= -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(amount), 0);
		params.add(in);
		in = new SubProParam(new String(price), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		in = new SubProParam(new String(code), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(ADD_ITEM_MINIBAR, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(5);
				id = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public int editItemMinibar(int id, String name, String code, String amount, String price, String user){
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(amount), 0);
		params.add(in);
		in = new SubProParam(new String(price), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		in = new SubProParam(new String(code), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(EDIT_ITEM_MINIBAR, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(6);
				seq = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return seq;
	}
	
	public boolean deleteItemMiniBar(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(DELETE_ITEM_MINIBAR, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean changeStatusItemMiniBar(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(CHANGE_STATUS_ITEM_MINIBAR, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public List<RoomStatusModel> getListRoomStatus(int from, int to){
		List<RoomStatusModel> list = new ArrayList<RoomStatusModel>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(from), 0);
		params.add(in);
		in = new SubProParam(new BigDecimal(to), 0);
		params.add(in);
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(GET_LIST_ROOM_STATUS, params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(2);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i = i + 5) {
			RoomStatusModel status = new RoomStatusModel();
			status.setId(outParam.get(i));
			status.setStatusName(outParam.get(i + 1));
			status.setInvisible(outParam.get(i + 2));
			status.setModifyDate(outParam.get(i + 3));
			status.setUserModify(outParam.get(i + 4));
			list.add(status);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public int countRoomStatus() {
		int count = 0;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(new String(), SubProParam.OUT);
		params.add(subOut);
		try {
			params = broker.executeSubPro(COUNT_ROOM_STATUS, params);
			if (params != null & params.size() > 0) {
				SubProParam paramOUT = (SubProParam) params.get(0);
				count = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public int addRoomStatus(String name, String user){
		int id= -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(ADD_ROOM_STATUS, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(2);
				id = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public int editRoomStatus(int id, String name, String user){
		int seq = -1;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		in = new SubProParam(new String(name), 0);
		params.add(in);
		in = new SubProParam(new String(user), 0);
		params.add(in);
		SubProParam subOut = new SubProParam(new String(), 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(EDIT_ROOM_STATUS, params);
			if ((params != null) & (params.size() > 0)) {
				SubProParam paramOUT = (SubProParam) params.get(3);
				seq = Utils.parseInt(paramOUT.getString().trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return seq;
	}
	
	public boolean deleteRoomStatus(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(DELETE_ROOM_STATUS, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean changeStatusRoomStatus(int id){
		boolean flag = true;
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam in = new SubProParam(new BigDecimal(id), 0);
		params.add(in);
		try {
			broker.executeSubPro(CHANGE_STATUS_ROOM_STATUS, params);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
		HouseKeepingDao h = new HouseKeepingDao();
		System.out.println(h.getListRoomStatus(1,6));
	}
}
