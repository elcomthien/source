package elcom.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import elcom.connect.eCMD;

public class ePMS extends ePMSProcess {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ePMS.class);

	public ePMS() {
		super();
		eCMD map = new eCMD();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String method = req.getParameter("method");
		smartcard = req.getParameter("smartcard");
		String smartcard_in = req.getParameter("smartcard");

		int command = eCMD.getValue(method);
		log.info("PROCESS METHOD=" + method + ",VALUE=" + command + " | CONTEXT: DEAWOO HOTEL");
		switch (command) {
		case 0:
			getHomeService2(req, res);
			break;
		case 1:
			getLogo(req, res);
			break;
		case 2:// checkPinCode
			checkPinCode(req, res);
			break;
		case 3:
			getWelcomeMessage(req, res, smartcard_in);
			break;
		case 6:
			getFolioNum(req, res);
			break;
		case 5:
			getPromotions(req, res);
			break;
		case 7:
			countTotalBilling(req, res);
			break;
		case 8:
			countMessage(req, res);
			break;
		case 9:
			countNewMessage(req, res);
			break;
		case 10:
			countCurrency(req, res);
			break;
		case 11:
			countItem(req, res);
			break;
		case 12:
			getCheckNo(req, res);
			break;
		case 13:
			checkCoordinatesChange(req, res);
			break;
		case 14:
			setLocationSynStatus(req, res);
			break;
		case 15:
			setMessageStatus(req, res);
			break;
		case 16:
			closedWakeup(req, res);
			break;
		case 17:
			postedWakeup(req, res);
			break;
		case 18:
			getFolioLanguage(req, res);
			break;
		case 19:
			getMessages(req, res);
			break;
		case 20:
			getAdverImages(req, res);
			break;
		case 21:
			getItemByCheckNo(req, res);
			break;
		case 22:
			getBills(req, res);
			break;
		case 23:
			getFolioMessages(req, res);
			break;
		case 24:
			getMessageInfo(req, res);
			break;
		case 25:
			getHomeService(req, res);
			break;
		case 26:
			getMainMenu(req, res);
			break;
		case 27:
			getSubMenu(req, res);
			break;
		case 28:
			getItemOfHotel(req, res);
			break;
		case 29:
			getItemOfService(req, res);
			break;
		case 30:
			getItemOfWeb(req, res);
			break;
		case 31:
			getCountries(req, res);
			break;
		case 32:
			getWeatherCommonToday(req, res);
			break;
		case 33:
			getWeathersInWeek(req, res);
			break;
		case 34:
			getWeatherTodays(req, res);
			break;
		case 35:
			getFolioWakeup(req, res);
			break;
		case 36:
			getWakeupForms(req, res);
			break;
		case 37:
			getExchangeRates(req, res);
			break;
		case 38:
			getAdverImages(req, res);
			break;
		case 39:
			getItemOfAttractions(req, res);
			break;
		case 40:
			getLocalAttractionInfo(req, res);
			break;
		case 41:
			getItemOfRestaurants(req, res);
			break;
		case 42:
			getItemOfScheduleActivity(req, res);
			break;
		case 43:
			getItemOfHousekeeping(req, res);
			break;
		case 44:
			getTaxiList(req, res);
			break;
		case 45:
			getMenuOfAirlineTransport(req, res);
			break;
		case 46:
			getMapLocations(req, res);
			break;
		case 47:
			getMapCoordinatesChange(req, res);
			break;
		case 48:
			setLocationSynStatus(req, res);
			break;
		case 49:
			checkCoordinatesChange(req, res);
			break;
		case 50:
			requestTransportation(req, res);
			break;
		case 51:
			requestTaxi(req, res);
			break;
		case 52:
			orderRoomService(req, res);
			break;
		case 53:
			bookHotelTable(req, res);
			break;
		case 54:
			requestServiceHousekeeping(req, res);
			break;
		case 55:
			requestItemHouseKeeping(req, res);
			break;
		case 56:
			getItemContainer(req, res);
			break;
		case 57:
			getService(req, res);
			break;
		}

	}
}
