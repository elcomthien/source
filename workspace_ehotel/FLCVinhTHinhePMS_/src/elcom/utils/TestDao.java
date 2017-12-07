package elcom.utils;

import java.util.ArrayList;
import java.util.List;

import ebop.com.zip.FolderZiper;
import elcom.com.xml.eHotelXML;
import elcom.dao.ePMSDao;
import elcom.dao.ePMSDaoEx;
import elcom.domain.Guest;
import elcom.domain.Logo;
import elcom.domain.OutletImage;
import elcom.domain.Promotion;
import elcom.domain.ServiceCharge;
import elcom.domain.ServiceCommon;
import elcom.domain.ServiceItem;
import elcom.domain.VersionData;
import elcom.domain.eContainer;
import elcom.domain.eCurrency;
import elcom.domain.eMessage;
import elcom.domain.eWeather;
import elcom.domain.ex.eAttraction;

public class TestDao {

	public static void testGuest() {
		eHotelXML xml = new eHotelXML("pms/guest/2001.xml");
		Guest guest = ePMSDao.getWelcomeMessage("2001");
		guest.setRoomCode("203");
		guest.setGuestTitle("Mr.");
		List<Guest> vG = new ArrayList<Guest>();
		vG.add(guest);
		xml.exportGuest(vG, "2001");
	}

	public static void testPromotions() {
		eHotelXML xml = new eHotelXML("elcome/resources/xml/2003.xml");
		List<Promotion> vPromotions = ePMSDao.getPromotions("2003");
		xml.exportPromotions(vPromotions, "2003");
	}

	public static void getItemByCheckNo() {
		eHotelXML xml = new eHotelXML("pms/bill/2003.xml");
		List<ServiceCharge> vCharges = ePMSDao.getItemByCheckNo(70000402,
				"2003");
		xml.exportBillCharges(vCharges, "2003");
	}

	public static void getLogo() {
		Logo logo = ePMSDaoEx.getLogo("2001");
		System.out.println(logo.toString());
	}

	public static void getMessages() {
		eHotelXML xml = new eHotelXML("pms/message/2001.xml");
		List<eMessage> vmessages = ePMSDao.getMessages("2001");
		xml.exportMessages(vmessages, "2001");
	}

	public static void getFolioMessage() {
		List<eMessage> vmessages = ePMSDao
				.getFolioMessages("2001", -1, -1, 100);
		eHotelXML hotel = new eHotelXML("pms/message/" + "2001" + ".xml");
		hotel.exportMessages(vmessages, "2001");
	}

	public static void exportBillCharges() {
		eHotelXML xml = new eHotelXML("pms/bill/2001.xml");
		List<ServiceCharge> vmessages = ePMSDao.getItemByCheckNo(70000543,
				"2001");
		xml.exportBillCharges(vmessages, "2001");
	}

	public static void getHomeService() {
		eHotelXML xml = new eHotelXML("elcom/resources/xml/home/home.xml");
		List<ServiceCommon> vServices = ePMSDao.getHomeService("2001");
		xml.exportServices(vServices, "2001");
	}

	public static void getMainMenu() {
		eHotelXML xml = new eHotelXML("pms/home/mmenu.xml");
		List<ServiceCommon> vmenus = ePMSDao.getMainMenu(8, "2001");
		xml.exportServices(vmenus, "2001");
	}

	public static void getSubMenu() {
		eHotelXML xml = new eHotelXML("pms/home/menu.xml");
		List<ServiceCommon> vSubmenus = ePMSDao.getSubMenu(10, "2001");
		xml.exportServices(vSubmenus, "2001");
	}

	public static void getItemOfHotel() {
		List<OutletImage> vImages = ePMSDao.getOutletImage(1, "2001", "M");
		eHotelXML hotel = new eHotelXML("pms/hotel/1.xml");
		hotel.exportOutletImage(vImages, "2001");
	}

	public static void getItemOfService() {
		List<ServiceItem> vitems = ePMSDao.getItemOfService(5, "2001", 1, 10);
		eHotelXML hotel = new eHotelXML("pms/dining/" + 5 + ".xml");
		hotel.exportServiceItem(vitems, "2001");
	}

	public static void getServiceItemInfo() {
		ServiceItem item = ePMSDao.getServiceItemInfo(4, "2001");
		List<ServiceItem> vitems = new ArrayList<ServiceItem>();
		vitems.add(item);
		eHotelXML hotel = new eHotelXML("pms/dining/item" + 4 + ".xml");
		hotel.exportServiceItem(vitems, "2001");
	}

	public static void getExchangeRates() {
		List<eCurrency> vCurrencies = ePMSDao.getExchangeRates(-1, -1);
		eHotelXML hotel = new eHotelXML("pms/exchange/exchange.xml");
		hotel.exportExchangeRates(vCurrencies, "2001");
	}

	public static void getOutlet() {
		List<OutletImage> vimages = ePMSDao.getAdverImages("MUSIC", "2001");
		eHotelXML hotel = new eHotelXML("pms/advertise/MUSIC.xml");
		hotel.exportOutletImage(vimages, "2001");
	}

	public static void getItemOfAttractions() {
		List<eAttraction> vattration = ePMSDaoEx.getItemOfAttractions(10,
				"2001");
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/attraction/attraction.xml");
		hotel.exportAttraction(vattration, "2001");
	}

	public static void getWeathers() {
		List<eWeather> vWeathers = ePMSDao.getWeathersInWeek(2, "2001");
		eHotelXML hotel = new eHotelXML("pms/weather/" + 2 + ".xml");
		hotel.exportWeather(vWeathers, "2001");
	}

	public static void countTotalBilling() {
		System.out.println(ePMSDao.countBillTotal("2001"));
	}

	public static void getOutletImage() {
		List<OutletImage> vImages = ePMSDao.getOutletImage(6, "2001", "M");
		System.out.println(vImages.size());
	}

	public static void getGames() {
		List<eContainer> vWeathers = ePMSDaoEx.getGames("2001");
		eHotelXML hotel = new eHotelXML("elcome/resources/xml/game/game.xml");
		hotel.exportGames(vWeathers, "2001");
	}

	public static void zipfile() {
		try {
			FolderZiper.zipFolder("elcom/resources/xml/promotion",
					"elcom/resources/xml/zip/promotion.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testversion() {
		List<VersionData> vVersions = ePMSDaoEx.getVersions();
		eHotelXML hotel = new eHotelXML("Config/server_version.xml");
		hotel.exportVersionServer(vVersions, "2001");
	}

	public static void main(String[] args) {
		TestDao.testversion();
	}
}
