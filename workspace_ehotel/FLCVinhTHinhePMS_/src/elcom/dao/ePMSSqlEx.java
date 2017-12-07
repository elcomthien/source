package elcom.dao;

public class ePMSSqlEx {
	public static final String sqlGetItemOfAttractions = "{call PMSAPP.getItemOfAttractions(?,?,?)}";
	public static final String sqlGetLocalAttractionInfo = "{call PMSAPP.getLocalAttractionInfo(?,?,?)}";
	public static final String sqlGetItemOfHousekeeping = "{call PMSAPP.getItemOfHousekeeping(?,?,?)}";
	public static final String sqlPostedOnetHouseKeeping = "{call PMSAPP.requestOneItemHouseKeeping(?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlPostedOneHKService = "{call PMSAPP.requestServiceHousekeeping(?,?,?,?,?,?,?,?,?)}";

	// TRANSPORTATION
	public static final String sqlTaxiList = "{call PMSAPP.getTaxiList(?,?)}";
	public static final String sqlRequestTaxi = "{call PMSAPP.requestTaxi(?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static final String sqlGetMenuOfAirlineTransport = "{call PMSAPP.getMenuOfAirlineTransport(?,?,?)}";
	public static final String sqlRequestTransportation = "{call PMSAPP.requestTransportation(?,?,?,?,?,?,?,?)}";
	// ACTIVITY
	public static final String sqlGetItemOfScheduleActivity = "{call PMSAPP.getItemOfScheduleActivity(?,?,?)}";
	// Map
	public static final String sqlGetLocations = "{call PMSAPP.getLocations(?,?,?)}";
	public static final String sqlCheckCoordinatesChange = "{call PMSAPP.checkCoordinatesChange(?,?)}";
	public static final String sqlGetMapLocationsChange = "{call PMSAPP.getMapCoordinatesChange(?,?)}";
	public static final String sqlsetLocationSynStatus = "{call PMSAPP.setLocationSynStatus(?,?)}";
	// logo
	public static final String sqlGetLogo = "{call PMSAPP.getlogo(?,?)}";
	// game
	public static final String sqlgetGames = "{call PMSAPP.getGames(?,?)}";
	public static final String sqlgetInternets = "{call PMSAPP.getInternets(?)}";
	// version
	public static final String sqlgetVersion = "{call PMSAPP.getVersions(?)}";
}
