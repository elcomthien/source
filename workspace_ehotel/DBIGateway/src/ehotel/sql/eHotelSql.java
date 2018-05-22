package ehotel.sql;

public class eHotelSql {

	public static String sqlAddMenu = "{call ePMS.addMenu(?,?,?,?,?,?,?,?)}";
	public static String sqlEditMenu = "{call ePMS.editMenu(?,?,?,?,?,?,?,?,?)}";
	public static String sqlRemoMenu = "{call ePMS.removeMenu(?,?,?,?)}";
	public static String sqlGetMainMenu = "{call ePMS.getMainMenu(?,?,?)}";
	public static String sqlGetSubMenu = "{call ePMS.getSubMenu(?,?,?,?,?)}";
	public static String sqlgetSubMenuInfo = "{call ePMS.getSubMenuInfo(?,?,?)}";
	public static String sqlGetMenuInfo = "{call ePMS.getMenuInfo(?,?,?,?)}";
	public static String sqlCountMenu = "{call ePMS.countMenu(?,?,?)}";

	public static String sqlGetItemOfHotelInfo = "{call ePMS.getItemsOfHotelInfo(?,?,?,?,?)}";
	public static String sqlAddItemOfHotelInfo = "{call ePMS.addItemOfHotelInfo(?,?,?,?,?,?)}";
	public static String sqlGetItemInfo_Hotel = "{call ePMS.getItemInfo_Hotel(?,?,?)}";
	public static String sqlRemoveItemOfHotelInfo = "{call ePMS.removeItemOfHotelInfo(?,?)}";
	public static String sqlRemoveItems_hotel = "{call ePMS.removeItems_hotel(?,?)}";
	public static String sqlCountItemOfHotel = "{call ePMS.countItemOfHotel(?,?)}";
	public static String sqlchangeSubjectOfItem = "{call ePMS.changeSubjectOfItem_Hotel(?,?,?)}";
	public static String sqlEditItemOfHotelInfo = "{call ePMS.editItemOfHotelInfo(?,?,?,?,?,?,?)}";
	public static String sqlGetSubjectInOut = "{call ePMS.getSubjectInOut(?,?,?,?)}";
	public static String sqlChangeStatus = "{call ePMS.changeStatus(?,?)}";

	// phan attraction
	public static String sqlAddItemOfAttraction = "{call ePMS.addItemOfAttraction(?,?,?,?,?)}";
	public static String sqlEditItemOfAttraction = "{call ePMS.editItemOfAttraction(?,?,?,?,?,?)}";
	public static String sqlRemoveItemOfAttraction = "{call ePMS.removeItemOfAttraction(?,?)}";
	public static String sqlGetItemInfo_Attraction = "{call ePMS.getItemInfo_Attraction(?,?,?)}";
	public static String sqlGetItemsOfAttraction = "{call ePMS.getItemsOfAttraction(?,?,?,?,?)}";
	public static String sqlCountItemOfAttraction = "{call ePMS.countItemOfAttraction(?,?)}";
	public static String sqlChangeSubjectOfItem_Attraction = "{call ePMS.changeSubjectOfItem_Attraction(?,?,?)}";
	public static String sqlChangeStatus_Attraction = "{call ePMS.changeStatus_Attraction(?,?)}";
	public static String sqlSetMapLocation = "{call ePMS.setMapLocation(?,?,?,?)}";
	public static String sqlGetMapLocation = "{call ePMS.getMapLocation(?,?)}";
	// phan activity
	public static String sqlGetItemOfActi = "{call ePMS.getItemInfo_Acti(?,?,?)}";
	public static String sqlGetItemsOfActi = "{call ePMS.getItemsOfActi(?,?,?,?,?)}";
	public static String sqlAddItemOfActi = "{call ePMS.addItemOfActi(?,?,?,?)}";
	public static String sqlRemoveItemOfActi = "{call ePMS.removeItemOfActi(?,?)}";
	public static String sqlEditItemOfActi = "{call ePMS.editItemOfActi(?,?,?,?,?)}";
	public static String sqlCountItemOfActi = "{call ePMS.countItemOfActi(?,?)}";
	public static String sqlChangeStatus_Acti = "{call ePMS.changeStatus_Acti(?,?)}";
	public static String sqlChangeSubjectOfItem_Acti = "{call ePMS.changeSubjectOfItem_Acti(?,?,?)}";
	// phan promotion
	public static String sqlGetPromotions = "{call ePMS.getPromotions(?,?,?,?)}";
	public static String sqlGetPromotionInfo = "{call ePMS.getPromotionInfo(?,?,?)}";
	public static String sqlAddPromotion = "{call ePMS.addPromotion(?,?,?,?,?)}";
	public static String sqlEditPromotion = "{call ePMS.editPromotion(?,?,?,?,?,?,?)}";
	public static String sqlRemovePromotion = "{call ePMS.removePromotion(?,?)}";
	public static String sqlChangeStatusPromotion = "{call ePMS.changeStatusPromotion(?,?)}";
	public static String sqlCountPromotions = "{call ePMS.countPromotions(?)}";
	// exchange rate
	public static String sqlGetExchangeRates = "{call ePMS.getExchangeRates(?,?,?)}";
	public static String sqlGetExchangeRateInfo = "{call ePMS.getExchangeRateInfo(?,?)}";
	public static String sqlAddExchangeRate = "{call ePMS.addExchangeRate(?,?,?,?,?,?,?,?,?)}";
	public static String sqlRemoveExchange = "{call ePMS.removeExchange(?,?)}";
	public static String sqlEditExchangeRate = "{call ePMS.editExchangeRate(?,?,?,?,?,?,?,?,?,?)}";
	public static String sqlChangeStatusExchange = "{call ePMS.changeStatusExchange(?,?)}";
	public static String sqlCountExchangeRates = "{call ePMS.countExchangeRates(?)}";
	// transportation
	public static String sqlAddUrlAirline = "{call ePMS.addUrlAirline(?,?,?,?)}";
	public static String sqlGetUrlAirlines = "{call ePMS.getUrlAirlines(?,?,?)}";
	public static String sqlRemoveUrlAirline = "{call ePMS.removeUrlAirline(?,?)}";
	public static String sqlEditUrlAirline = "{call ePMS.editUrlAirline(?,?,?,?,?)}";
	public static String sqlGetUrlAirlineInfo = "{call ePMS.getUrlAirlineInfo(?,?)}";
	public static String sqlChangeStaus = "{call ePMS.changeStausUrlAirline(?,?)}";
	public static String sqlCountUrlAirline = "{call ePMS.countUrlAirline(?)}";
	// advertise
	public static String sqlGetAdvTypeList = "{call ePMS.getAdvTypeList(?,?)}";
	public static String sqlgetAdvertiseInfo = "{call ePMS.getAdvertiseInfo(?,?,?)}";
	public static String sqlGetImageAdverties = "{call ePMS.getImageAdverties(?,?,?,?)}";
	public static String sqlAddAdvertise = "{call ePMS.addAdvertise(?,?,?,?,?)}";
	public static String sqleditAdvertise = "{call ePMS.editAdvertise(?,?,?,?,?,?,?)}";
	public static String sqlremoveAdverties = "{call ePMS.removeAdvertie(?,?)}";
	public static String sqlChangeStatusAdvertise = "{call ePMS.changeStatusAdvertise(?,?)}";
	public static String sqlCountAdvertise = "{call ePMS.countAdvertise(?)}";

	// Housekeeping
	public static String sqlgetItemsOfHousekeeping = "{call ePMS.getItemsOfHousekeeping(?,?,?,?,?)}";
	public static String sqlgetItemHousekeepingInfo = "{call ePMS.getItemHousekeepingInfo(?,?,?)}";
	public static String sqlAddHousekeepingItem = "{call ePMS.addHousekeepingItem(?,?,?,?,?,?)}";
	public static String sqlremoveHousekeepingItem = "{call ePMS.removeHousekeepingItem(?,?)}";
	public static String sqlcountHousekeepingItem = "{call ePMS.countHousekeepingItem(?,?)}";
	public static String sqleditHousekeepingItem = "{call ePMS.editHousekeepingItem(?,?,?,?,?,?,?)}";
	public static String sqlChangeStatusHousekeeping = "{call ePMS.changeStatusHousekeeping(?,?)}";
	// Dining-roomservice
	public static String sqladdMenuDining = "{call ePMS.addMenuDining(?,?,?,?,?)}";
	public static String sqlremoveMenuDining = "{call ePMS.removeMenuDining(?,?)}";
	public static String sqlgetRoomSvcItemInfo = "{call ePMS.getRoomSvcItemInfo(?,?,?)}";
	public static String sqlgetRoomSvcItems = "{call ePMS.getRoomSvcItems(?,?,?,?,?)}";
	public static String sqlAddItemDining = "{call ePMS.addRoomSvcItem(?,?,?,?,?,?,?,?,?)}";
	public static String sqleditItemDining = "{call ePMS.editRoomSvcItem(?,?,?,?,?,?,?,?,?,?)}";
	public static String sqlRemoveItemDining = "{call ePMS.removeRoomSvcItem(?,?)}";
	public static String sqlcountItemDining = "{call ePMS.countRoomSvcItem(?,?)}";
	public static String sqlChangeStatusRoomsvcItem = "{call ePMS.changeStatusRoomsvcItem(?,?)}";
	// ---Dining-Restaurant
	public static String sqlgetRestaurantItems = "{call ePMS.getRestaurantItems(?,?,?,?,?)}";
	public static String sqlAddRestaurantItem = "{call ePMS.adddRestaurantItem(?,?,?,?,?)}";
	public static String sqlremoveRestaurantItem = "{call ePMS.removeRestaurantItem(?,?)}";
	public static String sqleditRestaurantItem = "{call ePMS.editRestaurantItem(?,?,?,?,?,?)}";
	public static String sqlcountRestaurantItem = "{call ePMS.countRestaurantItem(?,?)}";
	public static String sqlChangeStatusResItem = "{call ePMS.changeStatusResItem(?,?)}";
	public static String sqlchangeSubjResItem = "{call ePMS.changeSubjResItem(?,?,?)}";
	public static String sqlgetResItemInfo = "{call ePMS.getResItemInfo(?,?,?)}";

	// map and direction
	public static String sqlgetMyLocation = "{call ePMS.getMyLocation(?,?)}";
	public static String sqlremoveLocationMap = "{call ePMS.removeLocationMap(?,?)}";
	public static String sqladdLocaltionMap = "{call ePMS.addLocaltionMap(?,?,?,?,?,?,?,?)}";
	public static String sqleditLocationMap = "{call ePMS.editLocationMap(?,?,?,?,?,?,?)}";
	public static String sqlGetItemsOfMap = "{call ePMS.getItemsOfMap(?,?,?,?,?)}";
	public static String sqlgetLocationMapInfo = "{call ePMS.getLocationMapInfo(?,?,?)}";
	public static String sqleditCoordinate = "{call ePMS.editCoordinate(?,?,?,?)}";
	public static String sqlCountLocationMap = "{call ePMS.countLocationMap(?,?)}";
	public static String sqlgetLocations = "{call ePMS.getLocations(?,?,?,?,?)}";
	public static String sqlsearchLocation = "{call ePMS.searchLocation(?,?,?,?,?,?)}";
	public static String sqlCountSearchLocationMap = "{call ePMS.countSearchLocation(?,?,?)}";

	// Folio
	public static String sqlcheckOutGuest = "{call ePMS.checkOutGuest(?,?)}";
	public static String sqlGetGuests = "{call ePMS.getGuests(?,?)}";
	public static String sqlresetPincode = "{call ePMS.resetPincode(?,?,?,?)}";

	public static String sqlGetFolioList = "{call ePMS.getFolioList(?,?,?)}";
	public static String sqlsearchFolio = "{call ePMS.searchFolio(?,?,?,?)}";
	public static String sqlCountFolio = "{call ePMS.countFolio(?)}";

	public static String sqlCountSearchFolio = "{call ePMS.countSearchFolio(?,?)}";
	public static String sqlcountBill = "{call ePMS.countBill(?,?)}";
	public static String sqlcheckOutFolio = "{call ePMS.checkOutFolio(?,?)}";
	public static String sqlgetBillCharges = "{call ePMS.getBillCharges(?,?,?,?)}";
	public static String sqlgetMessages = "{call ePMS.getMessages(?,?,?,?)}";

	public static String sqlremoveSTBOutFolio = "{call ePMS.removeSTBOutFolio(?,?)}";
	public static String sqladdSBTIntoFolio = "{call ePMS.addSBTIntoFolio(?,?,?)}";
	public static String sqlEditSBTIntoFolio = "{call ePMS.editSBTIntoFolio(?,?,?)}";
	public static String sqlGetSTBList = "{call ePMS.getSTBList(?,?,?,?,?)}";
	public static String sqlsearchSTB = "{call ePMS.searchSTB(?,?,?,?,?)}";
	public static String sqlCountSTB = "{call ePMS.countSTB(?,?)}";
	public static String sqlcountSearchSTB = "{call ePMS.countSearchSTB(?,?,?)}";
	// phan quan ly user,group
	public static String sqladdGroup = "{call eUser.addGroup(?,?)}";
	public static String sqleditGroup = "{call eUser.editGroup(?,?,?,?)}";
	public static String sqlremoveGroup = "{call eUser.removeGroup(?,?)}";
	public static String sqlgetGroupUserList = "{call eUser.getGroupList (?,?)}";
	public static String sqlgetGroupInfo = "{call eUser.getGroupInfo(?,?,?)}";
	public static String sqladdRoleIntoGroup = "{call eUser.addRoleIntoGroup(?,?,?)}";
	public static String sqlgetRolesInOutGroup = "{call eUser.getRolesInOutGroup(?,?,?,?)}";

	public static String sqlgetUsers = "{call eUser.getUsers(?,?,?,?)}";
	public static String sqlgetUserInfo = "{call eUser.getUserInfo(?,?)}";
	public static String sqlremoveUser = "{call eUser.removeUser(?,?,?)}";
	public static String sqlchangeActive = "{call eUser.changeActive(?,?)}";
	public static String sqlchangeGroup = "{call eUser.changeGroup(?,?,?)}";
	public static String sqlchangePassword = "{call eUser.changePassword(?,?,?)}";
	public static String sqladdUser = "{call eUser.addUser(?,?,?,?,?,?,?)}";
	public static String sqleditUser = "{call eUser.editUser(?,?,?,?,?)}";
	public static String sqladdRoleIntoUser = "{call eUser.addRoleIntoUser(?,?,?)}";
	public static String sqlgetRolesInOutUser = "{call eUser.getRolesInOutUser(?,?,?,?)}";
	public static String sqlgetGroupsInOutUser = "{call eUser.getGroupsInOutUser(?,?,?,?)}";
	public static String sqlCountUser = "{call eUser.countUsers(?,?)}";

	public static String sqlgetCategoryList = "{call eUser.getCategoryList(?,?)}";
	public static String sqlgetCategoryInfo = "{call eUser.getCategoryInfo(?,?,?)}";
	public static String sqlremoveCategory = "{call eUser.removeCategory(?,?)}";
	public static String sqladdCategory = "{call eUser.addCategory(?,?,?)}";
	public static String sqleditCategory = "{call eUser.editCategory(?,?,?,?)}";
	public static String sqlgetRolesInOutCate = "{call eUser.getRolesInOutCate(?,?,?,?)}";
	public static String sqladdRoleIntoCate = "{call eUser.addRoleIntoCate(?,?,?)}";
	public static String sqlgetRoleList = "{call eUser.getRoleList(?,?)}";
	// check quyen
	public static String sqllogin = "{call eUser.login(?,?,?,?)}";
	public static String sqlisadmin = "{call eUser.isadmin(?,?)}";
	public static String sqllogout = "{call eUser.logout(?,?)}";
	public static String sqlcheckRoleUser = "{call eUser.checkRoleUser(?,?,?)}";
	public static String sqlisLogedinByAnotherUser = "{call eUser.isLogedinByAnotherUser(?,?)}";
	public static String sqlgetLoginUserName = "{call eUser.getLoginUserName(?,?)}";
	public static String sqlgetHrefPageOfUser = "{call eUser.getHrefPageOfUser(?,?)}";
	// report
	public static String sqlgetUsedFrequency_monthly = "{call ereport.getUsedFrequency_monthly(?,?)}";
	public static String sqlgetUsedFrequency_name = "{call ereport.getUsedFrequency_name(?,?,?,?,?,?)}";
	public static String sqlgetUsedFrequency_gener = "{call ereport.getUsedFrequency_gener(?,?,?,?)}";
	public static String sqlgetRoomserviceRpt = "{call ereport.getRoomserviceRpt(?,?,?,?)}";
	public static String sqlgetTransportationRpt = "{call ereport.getTransportationRpt(?,?,?,?)}";
	// system main
	public static String sqlgetAPKCodes = "{call eMain.getAPKCodes(?)}";
	public static String sqlgetAllServices = "{call eMain.getAllServices(?,?)}";
	public static String sqlupdateServiceMain = "{call eMain.updateServiceMain(?,?,?,?,?)}";
	public static String sqlchangeVisbleService = "{call eMain.changeVisbleService(?,?)}";
	public static String sqlsetValueApkcode = "{call eMain.setValueApkcode(?,?,?)}";
	public static String sqlorderByService = "{call eMain.orderByService(?,?,?)}";
	public static String sqlgetApkPath = "{call eMain.getApkPath(?,?)}";
	public static String sqlgetServiceInfo = "{call eMain.getServiceInfo(?,?,?)}";
}
