package elcom.connect;

import java.util.HashMap;

public class eCMD {
	public static HashMap<String, String> CMD = new HashMap<String, String>();

	public eCMD() {
		init();
	}

	public static int getValue(String method) {
		if (CMD.get(method) != null) {
			return Integer.parseInt(CMD.get(method));
		} else {
			return -1;
		}
	}

	public void init() {
		CMD.put("getHomeService2", "0");
		CMD.put("getLogo", "1");
		CMD.put("checkPinCode", "2");
		CMD.put("getWelcomeMessage", "3");
		CMD.put("getGuestsInRoom", "4");
		CMD.put("getPromotions", "5");
		CMD.put("getFolioNum", "6");
		CMD.put("countTotalBilling", "7");
		CMD.put("countMessage", "8");
		CMD.put("countNewMessage", "9");
		CMD.put("countCurrency", "10");
		CMD.put("countItem", "11");
		CMD.put("getCheckNo", "12");
		CMD.put("checkCoordinatesChange", "13");
		CMD.put("setLocationSynStatus", "14");
		CMD.put("setMessageStatus", "15");
		CMD.put("closedWakeup", "16");
		CMD.put("postedWakeup", "17");
		CMD.put("getFolioLanguage", "18");
		CMD.put("getMessages", "19");
		CMD.put("getAdverImages", "20");
		CMD.put("getItemByCheckNo", "21");
		CMD.put("getBills", "22");
		CMD.put("getFolioMessages", "23");
		CMD.put("getMessageInfo", "24");
		CMD.put("getHomeService", "25");
		CMD.put("getMainMenu", "26");
		CMD.put("getSubMenu", "27");
		CMD.put("getItemOfHotel", "28");
		CMD.put("getItemOfService", "29");
		CMD.put("getItemOfWeb", "30");
		CMD.put("getCountries", "31");
		CMD.put("getWeatherCommonToday", "32");
		CMD.put("getWeathersInWeek", "33");
		CMD.put("getWeatherTodays", "34");
		CMD.put("getFolioWakeup", "35");
		CMD.put("getWakeupForms", "36");
		CMD.put("getExchangeRates", "37");
		CMD.put("getAdverImages", "38");
		CMD.put("getItemOfAttractions", "39");
		CMD.put("getLocalAttractionInfo", "40");
		CMD.put("getItemOfRestaurants", "41");
		CMD.put("getItemOfScheduleActivity", "42");
		CMD.put("getItemOfHousekeeping", "43");
		CMD.put("getTaxiList", "44");
		CMD.put("getMenuOfAirlineTransport", "45");
		CMD.put("getMapLocations", "46");
		CMD.put("getMapCoordinatesChange", "47");
		CMD.put("setLocationSynStatus", "48");
		CMD.put("checkCoordinatesChange", "49");
		CMD.put("requestTransportation", "50");
		CMD.put("requestTaxi", "51");
		CMD.put("orderRoomService", "52");
		CMD.put("bookHotelTable", "53");
		CMD.put("requestServiceHousekeeping", "54");
		CMD.put("requestItemHouseKeeping", "55");
		CMD.put("getItemContainer", "56");
		CMD.put("getService", "57");

	}

	public static void main(String[] args) {
		eCMD cmd = new eCMD();
		System.out.println(cmd.getValue("getLogo"));
	}
}
