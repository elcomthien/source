package elcom.dao;

public class ePMSSql {
	// common
	public static final String sqlCheckPinCode = "{call PMSAPP.checkPinCode(?,?,?)}";
	public static final String sqlGetFoliNum = "{call PMSAPP.getFolioNum(?,?)}";
	public static final String sqlGetFolioLanguage = "{call PMSAPP.getFolioLanguage(?,?)}";
	public static final String sqlGetPromotions = "{call PMSAPP.getPromotions(?,?)}";
	public static final String sqlGetWelcomeMessage = "{call PMSAPP.getWelcomeMessage(?,?)}";
	public static final String sqlGetGuestsInRoom = "{call PMSAPP.getGuestsInRoom(?,?)}";
	// Service
	public static final String sqlGetHomeService = "{call PMSAPP.getHomeService(?,?)}";
	public static final String sqlGetService = "{call PMSAPP.getService_(?,?,?)}";
	public static final String sqlGetMainMenu = "{call PMSAPP.getMainMenu(?,?,?)}";
	public static final String sqlGetSubMenu = "{call PMSAPP.getSubMenu(?,?,?)}";
	public static final String sqlGetItemOfHotel = "{call PMSAPP.getOultetImage(?,?,?,?)}";

	public static final String sqlGetItemOfService = "{call PMSAPP.getItemOfService(?,?,?,?,?)}";
	public static final String sqlGetItemByCheckNo = "{call PMSAPP.getItemByCheckNo(?,?,?)}";
	public static final String sqlGetServiceItemInfo = "{call PMSAPP.getServiceItemInfo(?,?,?)}";
	public static final String sqlPostedItemToBill = "{call PMSAPP.postedItemToBill(?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlBookTableHotel = "{call PMSAPP.bookTableHotel(?,?,?,?,?,?,?,?,?,?,?)}";
	// web
	public static final String sqlGetItemOfWeb = "{call PMSAPP.getItemOfWeb(?,?,?,?,?)}";
	public static final String sqSaveUrlWeb = "{call PMSAPP.saveUrlWeb(?,?,?,?)}";
	public static final String sqDelelteUrlWeb = "{call PMSAPP.delelteUrlWeb (?,?)}";
	// bill
	public static final String sqlGetBills = "{call PMSAPP.getBills(?,?)}";
	public static final String sqlCountBillTotal = "{call PMSAPP.countTotalBilling(?,?)}";
	public static final String sqlGetCheckNo = "{call PMSAPP.getCheckNo(?,?)}";
	// weather
	public static final String sqlGetCountries = "{call PMSAPP.getCountries(?,?)}";
	public static final String sqlGetWeatherCommonToday = "{call PMSAPP.getWeatherCommonToday(?,?)}";
	public static final String sqlGetWeathersInWeek = "{call PMSAPP.getWeathersInWeek(?,?,?)}";
	public static final String sqlGetWeatherToday = "{call PMSAPP.getWeatherToday(?,?)}";
	public static final String sqlGetWeatherTodayById = "{call PMSAPP.getWeatherTodayById(?,?,?)}";
	// wake-up
	public static final String sqlGetWakeupForms = "{call PMSAPP.getWakeupForms(?,?)}";
	public static final String sqlGetFolioWakeup = "{call PMSAPP.getFolioWakeup(?,?)}";
	public static final String sqlClosedWakeup = "{call PMSAPP.closedWakeup(?)}";
	public static final String sqlPostedWakeup = "{call PMSAPP.postedWakeup(?,?,?,?,?)}";
	// --Advertise
	public static final String sqlGetAdverImages = "{call PMSAPP.getAdverImages(?,?,?)}";
	public static final String sqlGetAdvertiseMovie = "{call PMSAPP.getAdvertiseMovie(?,?)}";
	// --other
	public static final String sqlGetExchangeRates = "{call PMSAPP.getExchangeRate(?,?,?)}";
	public static final String sqlGetFolioMessages = "{call PMSAPP.getFolioMessages(?,?,?,?)}";
	public static final String sqlSetMessageStatus = "{call PMSAPP.setMessageStatus(?,?)}";
	public static final String sqlGetMessageInfo = "{call PMSAPP.getMessageInfo(?,?,?)}";
	public static final String sqlGetMessages = "{call PMSAPP.getMessages(?,?)}";
	// count
	public static final String sqlCountNewMessage = "{call PMSAPP.countNewMessage(?,?)}";
	public static final String sqlCountItem = "{call PMSAPP.countItem(?,?,?)}";
	public static final String sqlcountOther = "{call PMSAPP.countOther(?,?,?)}";
	public static final String sqlCountTotalByCheckNo = "{call PMSAPP.countTotalByCheckNo(?,?)}";

}
