package elcom.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ebop.com.zip.FolderZiper;
import elcom.com.xml.eHotelXML;
import elcom.config.PMSConstant;
import elcom.config.ReaderProperties;
import elcom.connect.ConfigReader;
import elcom.dao.ePMSDao;
import elcom.dao.ePMSDaoEx;
import elcom.domain.CMNShopbill;
import elcom.domain.Guest;
import elcom.domain.Logo;
import elcom.domain.OrderRes;
import elcom.domain.OutletImage;
import elcom.domain.Promotion;
import elcom.domain.ServiceCharge;
import elcom.domain.ServiceCommon;
import elcom.domain.ServiceItem;
import elcom.domain.VersionData;
import elcom.domain.eContainer;
import elcom.domain.eCountry;
import elcom.domain.eCurrency;
import elcom.domain.eMapLocation;
import elcom.domain.eMessage;
import elcom.domain.eReservation;
import elcom.domain.eWakeup;
import elcom.domain.eWakeupForms;
import elcom.domain.eWeather;
import elcom.domain.ex.eAcitivity;
import elcom.domain.ex.eAttraction;
import elcom.domain.ex.eHouseKeeping;
import elcom.domain.ex.eObject;
import elcom.utils.eHotelUtils;

/**
 * Servlet implementation class ePMSProcess
 */
public class ePMSProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ePMSProcess.class);
	public String smartcard = null;

	public ePMSProcess() {
		super();
		readProperties();
	}

	static {
		try {
			PropertyConfigurator.configure(ConfigReader.getLog4jConfigReader()
					.getProperties());
		} catch (IOException ex) {
			log.error(null, ex);
		}
	}

	public void readProperties() {
		ReaderProperties prop;
		try {
			prop = new ReaderProperties();
			PMSConstant.PATH_SERVER = prop.getProperty("ehotel.home.path",  //tam thoi comment lai de test 
					"/home/app/apache-tomcat-7.0.22/webapps/pms/");
			//PMSConstant.PATH_SERVER = "/ehotel/service/app/apache-tomcat-7.0.34/webapps/ePMSDW/";
		} catch (Exception e) {
			log.error(e);
		}

	}

	public boolean checkSynData(String smartcard) {
		log.info("----------------------------------------------------------");
		log.info("--------CheckSynData[smartcard=" + smartcard + "]");
		// version db
		String ver_db = eHotelUtils
				.getVersionOfService(
						getServletContext().getResourceAsStream(
								"config/server_version.xml"), "E00", "3.0");

		String ver_ehotel = eHotelUtils
				.getVersionOfService(
						getServletContext().getResourceAsStream(
								"config/ehotel_version.xml"), "E00", "2.0");
		log.info("VER_REAL=" + ver_db + "- VER_HOTEL=" + ver_ehotel);

		if (ver_db != null && ver_ehotel != null) {
			if (!ver_db.equals(ver_ehotel)) {
				return true;
			}
		}
		return false;
	}

	public void getLogo(HttpServletRequest req, HttpServletResponse res) {
		Logo logo = ePMSDaoEx.getLogo(smartcard);
		StringBuffer buffer = new StringBuffer();
		buffer.append(logo.getLogo_welcome());
		buffer.append(logo.getLogo_service());
		outData(buffer.toString(), res);
	}

	public void getWelcomeMessage(HttpServletRequest req,
			HttpServletResponse res,String smartcard_in) {
		Guest guest = ePMSDao.getWelcomeMessage(smartcard_in);
		System.out.println("KHANCH HANG: " + guest.toString());
		List<Guest> vGuests = new ArrayList<Guest>();
		vGuests.add(guest);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/guest/"
				+ smartcard_in + ".xml");
		hotel.exportGuest(vGuests, smartcard_in);

		// version
		log.info("export update version server");
		List<VersionData> vVersions = ePMSDaoEx.getVersions();
		hotel = new eHotelXML("config/server_version.xml");
		hotel.exportVersionServer(vVersions, smartcard_in);
		try {
			outXML(smartcard_in,
					"elcom/resources/xml/guest/" + smartcard_in + ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getGuestsInRoom(HttpServletRequest req, HttpServletResponse res) {
		log.info("Processing getGuestsInRoom[smartcard=" + smartcard);
		List<Guest> vGuests = ePMSDao.getGuestsInRoom(smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/guest/"
				+ smartcard + ".xml");
		hotel.exportGuest(vGuests, smartcard);
		try {
			outXML(smartcard,
					"elcom/resources/xml/guest/" + smartcard + ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getPromotions(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getPromotions[smartcard=" + smartcard
				+ "]");
		try {
			if (checkSynData(smartcard)) {
				List<Promotion> vPromotions = ePMSDao.getPromotions(smartcard);
				eHotelXML hotel = new eHotelXML(
						"elcom/resources/xml/promotion/promotion.xml");
				hotel.exportPromotions(vPromotions, smartcard);

				// zip
				String inPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/promotion";
				String outPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/zip/promotion.zip";
				log.info("OutPath zip=" + outPath);
				FolderZiper.zipFolder(inPath, outPath);
				outXML(smartcard, "elcom/resources/xml/zip/promotion.zip", res);

			} else {
				outXML(smartcard, "elcom/resources/xml/zip/promotion.zip", res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		log.info("----------End Processing getPromotions[smartcard="
				+ smartcard);
	}

	public void getHomeService(HttpServletRequest req, HttpServletResponse res) {
		log.info("----------------------------------------------------------");
		log.info("--------Processing getHomeService[smartcard=" + smartcard
				+ "]");

		try {
			if (checkSynData(smartcard)) {

				List<ServiceCommon> vServices = ePMSDao
						.getHomeService(smartcard);
				eHotelXML hotel = new eHotelXML(
						"elcom/resources/xml/home/home.xml");
				hotel.exportServices(vServices, smartcard);

				// zip
				String inPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/home";
				String outPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/zip/home.zip";
				log.info("OutPath zip=" + outPath);
				FolderZiper.zipFolder(inPath, outPath);
				outXML(smartcard, "elcom/resources/xml/zip/home.zip", res);
			} else {
				outXML(smartcard, "elcom/resources/xml/zip/home.zip", res);
			}
		} catch (Exception e) {
			log.error(e);
		}

	}

	//----------------------------------------------------------
	public void getService(HttpServletRequest req, HttpServletResponse res) {
		log.info("----------------------------------------------------------");
		log.info("--------Processing getService[smartcard=" + smartcard
				+ "]");
		String serviceid = req.getParameter("serviceid");
		try {
			if (checkSynData(smartcard)) {

				List<ServiceCommon> vServices = ePMSDao
						.getService(smartcard,serviceid);
				eHotelXML hotel = new eHotelXML(
						"elcom/resources/xml/home/service.xml");
				hotel.exportServices(vServices, smartcard);

				// zip
				String inPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/home";
				String outPath = PMSConstant.PATH_SERVER
						+ "elcom/resources/xml/zip/service.zip";
				log.info("OutPath zip=" + outPath);
				FolderZiper.zipFolder(inPath, outPath);
				outXML(smartcard, "elcom/resources/xml/home/service.xml", res);
			} else {
				outXML(smartcard, "elcom/resources/xml/home/service.xml", res);
			}
		} catch (Exception e) {
			log.error(e);
		}

	}
	//---------------------------------------------------------------------------
	private static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            // directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
                System.out.println("Directoryeleted : "
                        + file.getAbsolutePath());
            } else {
                // list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    // construct the file structure
                    File fileDelete = new File(file, temp);
                    // recursive delete
                    delete(fileDelete);
                }
                // check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directoryeleted : "
                            + file.getAbsolutePath());
                }
            }
        } else {
            // if file, then delete it
            file.delete();
            System.out.println("Fileeleted : " + file.getAbsolutePath());
        }
    }
	
	//---------------------------------------------------------------------------
	public void getHomeService2(HttpServletRequest req, HttpServletResponse res) {
		log.info("----------------------------------------------------------");
		log.info("--------Processing getHomeService2[smartcard=" + smartcard
				+ "]");
		List<ServiceCommon> vServices = ePMSDao.getHomeService(smartcard);
		
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/home/"+smartcard+"home.xml");
		hotel.exportServices(vServices, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/home/"+smartcard+"home.xml", res);
		} catch (Exception e) {
			log.error(e);
		}

	}

	public void getMainMenu(HttpServletRequest req, HttpServletResponse res) {
		List<ServiceCommon> vmenus_service = ePMSDao.getHomeService(smartcard);
		
		log.info("--------Processing getMainMenu[smartcard=" + smartcard + "]");
		int serviceId = Integer.parseInt(req.getParameter("serviceid"));
		List<ServiceCommon> vmenus = ePMSDao.getMainMenu(serviceId, smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/menu/" + serviceId
				+ ".xml");
		
		hotel.exportServices(vmenus, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/menu/" + serviceId + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getSubMenu(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getSubMenu[smartcard=" + smartcard + "]");
		int mmenuid = Integer.parseInt(req.getParameter("mmenuid"));
		List<ServiceCommon> vSubmenus = ePMSDao.getSubMenu(mmenuid, smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/menu/" + mmenuid
				+ ".xml");
		hotel.exportServices(vSubmenus, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/menu/" + mmenuid + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getAdverImages(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getAdverImages[smartcard=" + smartcard
				+ "]");
		String serviceType = req.getParameter("servicetype");
		List<OutletImage> vimages = ePMSDao.getAdverImages(serviceType,
				smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/advertise/"
				+ serviceType + ".xml");
		hotel.exportOutletImage(vimages, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/advertise/" + serviceType
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getMessages(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getMessages[smartcard=" + smartcard + "]");
		List<eMessage> vmessages = ePMSDao.getMessages(smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/message/"
				+ smartcard + ".xml");
		hotel.exportMessages(vmessages, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/message/" + smartcard
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getFolioMessages(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getFolioMessages[smartcard=" + smartcard
				+ "]");
		int fromRow = 0;
		if (req.getParameter("fromrow") != null)
			fromRow = Integer.parseInt(req.getParameter("fromrow"));
		int noRow = 0;
		if (req.getParameter("norow") != null)
			noRow = Integer.parseInt(req.getParameter("norow"));
		int numStr = 0;
		if (req.getParameter("numstr") != null)
			noRow = Integer.parseInt(req.getParameter("numstr"));
		List<eMessage> vmessages = ePMSDao.getFolioMessages(smartcard, fromRow,
				noRow, numStr);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/message/"
				+ smartcard + ".xml");
		hotel.exportMessages(vmessages, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/message/" + smartcard
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getMessageInfo(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getMessageInfo[smartcard=" + smartcard
				+ "]");
		int messId = Integer.parseInt(req.getParameter("messageid"));
		eMessage m = ePMSDao.getMessageInfo(messId, smartcard);
		List<eMessage> vmessages = new ArrayList<eMessage>();
		vmessages.add(m);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/message/"
				+ smartcard + ".xml");
		hotel.exportMessages(vmessages, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/message/" + smartcard
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfHotel(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getItemOfHotel[smartcard=" + smartcard
				+ "]");
		int submenuid = Integer.parseInt(req.getParameter("submenuid"));
		List<OutletImage> vImages = ePMSDao.getOutletImage(submenuid,
				smartcard, "M");
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/hotel/"
				+ submenuid + ".xml");
		hotel.exportOutletImage(vImages, smartcard);
		try {
			outXML(smartcard,
					"elcom/resources/xml/hotel/" + submenuid + ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfService(HttpServletRequest req, HttpServletResponse res) {
		int submenuid = Integer.parseInt(req.getParameter("menuid"));
		int from = Integer.parseInt(req.getParameter("fromrow"));
		int tto = Integer.parseInt(req.getParameter("norow"));
		log.info("--------Processing getItemOfService[smartcard=" + smartcard
				+ ",menuid=" + submenuid + "]");
		List<ServiceItem> vitems = ePMSDao.getItemOfService(submenuid,
				smartcard, from, tto);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/dining/"
				+ submenuid + ".xml");
		hotel.exportServiceItem(vitems, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/dining/" + submenuid
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemByCheckNo(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getItemByCheckNo[smartcard=" + smartcard
				+ "]");
		int checkNo = Integer.parseInt(req.getParameter("checkno"));
		List<ServiceCharge> vCharges = ePMSDao.getItemByCheckNo(checkNo,
				smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/bill/" + smartcard
				+ ".xml");
		hotel.exportBillCharges(vCharges, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/bill/" + smartcard + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getBills(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getBills[smartcard=" + smartcard + "]");
		Vector<ServiceCharge> vCharges = ePMSDao.getBills(smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/bill/" + smartcard
				+ ".xml");
		hotel.exportBillCharges(vCharges, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/bill/" + smartcard + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getServiceItemInfo(HttpServletRequest req,
			HttpServletResponse res) {
		log.info("--------Processing getPromotions[smartcard=" + smartcard
				+ "]");
		int itemId = Integer.parseInt(req.getParameter("itemid"));
		ServiceItem item = ePMSDao.getServiceItemInfo(itemId, smartcard);
		List<ServiceItem> vitems = new ArrayList<ServiceItem>();
		vitems.add(item);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/dining/" + itemId
				+ ".xml");
		hotel.exportServiceItem(vitems, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/dining/" + itemId + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void orderService(String smartcard, String pinCode,
			Vector<CMNShopbill> itemOrders, String orderTime, String orderDate,
			String guestName, HttpServletResponse res) {
		log.info("--------Processing orderService[smartcard=" + smartcard + "]");
		OrderRes result = ePMSDao.orderService(smartcard, pinCode, itemOrders,
				orderTime, orderDate, guestName);
		StringBuffer buffer = new StringBuffer();
		buffer.append(result.getAlertFlag());
		buffer.append("@");
		buffer.append(result.getCheckNo());
		outData(buffer.toString(), res);
	}

	public void orderRoomService(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing orderRoomService[smartcard=" + smartcard
				+ "]");
		String pinCode = req.getParameter("pincode");
		int menuId = Integer.parseInt(req.getParameter("mmenuid"));
		int icode = Integer.parseInt(req.getParameter("icode"));
		int IQty = Integer.parseInt(req.getParameter("IQty"));
		String orderTime = req.getParameter("ordertime");
		String orderDate = req.getParameter("orderdate");
		String guestName = req.getParameter("guestname");
		int checkNo = -1;
		if (req.getParameter("checkno") != null)
			checkNo = Integer.parseInt(req.getParameter("checkno"));
		log.info("--------Param Input[pin=" + pinCode + ",menuid=" + menuId
				+ ",icode=" + icode + "IQty=" + IQty + ",ordertime="
				+ orderTime + ",checkno=" + checkNo + "]");
		// muc dich ke thua ham da co
		CMNShopbill aitem = new CMNShopbill();
		aitem.setICode(icode);
		aitem.setMenuID(menuId);
		aitem.setIQty(IQty);
		aitem.setCheckNo(checkNo);
		Vector<CMNShopbill> itemOrders = new Vector<CMNShopbill>();
		itemOrders.add(aitem);
		OrderRes result = ePMSDao.orderService(smartcard, pinCode, itemOrders,
				orderTime, orderDate, guestName);
		StringBuffer buffer = new StringBuffer();
		buffer.append(result.getAlertFlag());
		buffer.append("@");
		buffer.append(result.getCheckNo());
		outData(buffer.toString(), res);
	}

	public void checkPinCode(HttpServletRequest req, HttpServletResponse res) {
		String pinCode = req.getParameter("pincode");
		log.info("checkPinCode[pincode=" + pinCode + ",smartcard=" + smartcard
				+ "]");
		boolean checkPinCode = ePMSDao.checkPinCode(smartcard, pinCode);
		outData(String.valueOf(checkPinCode), res);
	}

	public void getFolioNum(HttpServletRequest req, HttpServletResponse res) {
		log.info("--------Processing getPromotions[smartcard=" + smartcard
				+ "]");
		outData(ePMSDao.getFolioNum(smartcard), res);
	}

	public void getFolioLanguage(HttpServletRequest req, HttpServletResponse res) {
		outData(ePMSDao.getFolioLanguage(smartcard), res);
	}

	public void getCountries(HttpServletRequest req, HttpServletResponse res) {
		int level = Integer.parseInt(req.getParameter("level"));
		List<eCountry> vCountries = ePMSDao.getCountries(level);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/country/countries.xml");
		hotel.exportCountries(vCountries, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/country/countries.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * get all url of web
	 * 
	 * @param menuId
	 * @param smartcard
	 *            : If(smartcard!=null)--> get all url which is in (history or
	 *            bookmark) subject if(smartcard==null)--> get all url which is
	 *            not in (history or bookmark) subject
	 * @param from
	 * @param tto
	 * @return Vector<eSoap>
	 */
	public void getItemOfWeb(HttpServletRequest req, HttpServletResponse res) {
		int mmenuId = Integer.parseInt(req.getParameter("mmenuid"));
		int from = Integer.parseInt(req.getParameter("fromrow"));
		int tto = Integer.parseInt(req.getParameter("norow"));
		List<eObject> vWebs = ePMSDao.getItemOfWeb(mmenuId, smartcard, from,
				tto);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/internet/internet.xml");
		hotel.exportWebs(vWebs, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/internet/internet.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getWeatherCommonToday(HttpServletRequest req,
			HttpServletResponse res) {
		eWeather weather = ePMSDao.getWeatherCommonToday();
		List<eWeather> vWeathers = new ArrayList<eWeather>();
		vWeathers.add(weather);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/weather/weather.xml");
		hotel.exportWeather(vWeathers, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/weather/weather.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * get weather of countryId in a week
	 * 
	 * @param countryId
	 * @param langId
	 * @return List<eWeather>
	 */
	public void getWeathersInWeek(HttpServletRequest req,
			HttpServletResponse res) {
		int countryId = Integer.parseInt(req.getParameter("countryid"));
		List<eWeather> vWeathers = ePMSDao.getWeathersInWeek(countryId,
				smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/weather/"
				+ countryId + ".xml");
		hotel.exportWeather(vWeathers, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/weather/" + countryId
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * get weather in current(today) of country or city
	 * 
	 * @param countryId
	 * @return eWeather
	 */
	public void getWeatherTodays(HttpServletRequest req, HttpServletResponse res) {
		int countryId = Integer.parseInt(req.getParameter("countryid"));
		eWeather weather = ePMSDao.getWeatherToday(countryId);
		List<eWeather> vWeathers = new ArrayList<eWeather>();
		vWeathers.add(weather);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/weather/today"
				+ countryId + ".xml");
		hotel.exportWeather(vWeathers, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/weather/today" + countryId
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * get Wake-up in the folio
	 * 
	 * @param smartcard
	 * @return List<eWakeup>
	 */
	public void getFolioWakeup(HttpServletRequest req, HttpServletResponse res) {
		List<eWakeup> vWakeup = ePMSDao.getFolioWakeup(smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/wakeup/"
				+ smartcard + ".xml");
		hotel.exportWakeup(vWakeup, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/wakeup/" + smartcard
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * get wakeup form in system which supports.
	 * 
	 * @param langId
	 * @return List<eWakeupForms>
	 */
	public void getWakeupForms(HttpServletRequest req, HttpServletResponse res) {
		List<eWakeupForms> vforms = ePMSDao.getWakeupForms(smartcard);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/wakeup/wakeupform.xml");
		hotel.exportWakeupForm(vforms, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/wakeup/wakeupform.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getExchangeRates(HttpServletRequest req, HttpServletResponse res) {
		int fromRow = Integer.parseInt(req.getParameter("fromrow"));
		int noRows = Integer.parseInt(req.getParameter("norow"));
		List<eCurrency> vCurrencies = ePMSDao.getExchangeRates(fromRow, noRows);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/exchange/exchange.xml");
		hotel.exportExchangeRates(vCurrencies, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/exchange/exchange.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfAttractions(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		List<eAttraction> vattration = ePMSDaoEx.getItemOfAttractions(menuId,
				smartcard);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/attraction/attraction.xml");
		hotel.exportAttraction(vattration, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/attraction/attraction.xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getLocalAttractionInfo(HttpServletRequest req,
			HttpServletResponse res) {
		int attractionId = Integer.parseInt(req.getParameter("attractionid"));
		eAttraction attraction = ePMSDaoEx.getLocalAttractionInfo(attractionId,
				smartcard);
		List<eAttraction> vattration = new ArrayList<eAttraction>();
		vattration.add(attraction);

		eHotelXML hotel = new eHotelXML("elcom/resources/xml/attraction/info_"
				+ attractionId + ".xml");
		hotel.exportAttraction(vattration, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/attraction/info_"
					+ attractionId + ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfRestaurants(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		log.info("-----------Processing getItemOfRestaurants[smartcard="
				+ smartcard + ",menuid=" + menuId + "])");
		List<OutletImage> vImages = ePMSDao.getOutletImage(menuId, smartcard,
				"I");
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/restaurant/"
				+ menuId + ".xml");
		hotel.exportOutletImage(vImages, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/restaurant/" + menuId
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfHousekeeping(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		log.info("-----------Processing getItemOfHousekeeping[smartcard="
				+ smartcard + ",menuid=" + menuId + "])");
		List<eHouseKeeping> vhk = ePMSDaoEx.getItemOfHousekeeping(menuId,
				smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/housekeeping/"
				+ menuId + ".xml");
		hotel.exportHouseKeeping(vhk, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/housekeeping/" + menuId
					+ ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getItemOfScheduleActivity(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		log.info("-----------Processing getItemOfScheduleActivity[smartcard="
				+ smartcard + ",menuid=" + menuId + "])");
		List<eAcitivity> vActivities = ePMSDaoEx.getItemOfScheduleActivity(
				menuId, smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/activity/"
				+ menuId + ".xml");
		hotel.exportActivities(vActivities, smartcard);
		try {
			outXML(smartcard,
					"elcom/resources/xml/activity/" + menuId + ".xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getTaxiList(HttpServletRequest req, HttpServletResponse res) {
		List<eObject> vtaxis = ePMSDaoEx.getTaxiList(smartcard);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/transportation/taxi.xml");
		hotel.exportWebs(vtaxis, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/transportation/taxi.xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getMenuOfAirlineTransport(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		List<ServiceCommon> vairlines = ePMSDaoEx.getMenuOfAirlineTransport(
				smartcard, menuId);
		eHotelXML hotel = new eHotelXML(
				"elcom/resources/xml/transportation/airline.xml");
		hotel.exportServices(vairlines, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/transportation/airline.xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getMapCoordinatesChange(HttpServletRequest req,
			HttpServletResponse res) {
		List<eMapLocation> vlocations = ePMSDaoEx
				.getMapCoordinatesChange(smartcard);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/map/map.xml");
		hotel.exportLocations(vlocations, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/map/map.xml", res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getMapLocations(HttpServletRequest req, HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		Vector<eMapLocation> vlocations = ePMSDaoEx.getMapLocations(smartcard,
				menuId);
		eHotelXML hotel = new eHotelXML("elcom/resources/xml/map/map_" + menuId
				+ ".xml");
		hotel.exportLocations(vlocations, smartcard);
		try {
			outXML(smartcard, "elcom/resources/xml/map/map_" + menuId + ".xml",
					res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void bookHotelTable(HttpServletRequest req, HttpServletResponse res) {
		eReservation reservation = new eReservation();
		int isbook = ePMSDao.bookTableHotel(reservation);
	}

	public void countTotalBilling(HttpServletRequest req,
			HttpServletResponse res) {
		log.info("--------Processing getPromotions[smartcard=" + smartcard);
		outData(ePMSDao.countBillTotal(smartcard), res);
	}

	public void countMessage(HttpServletRequest req, HttpServletResponse res) {
		outData(String.valueOf(ePMSDao.countOther(smartcard, "Message")), res);
	}

	public void countNewMessage(HttpServletRequest req, HttpServletResponse res) {
		outData(String.valueOf(ePMSDao.countNewMessage(smartcard)), res);
	}

	public void countCurrency(HttpServletRequest req, HttpServletResponse res) {
		outData(String.valueOf(ePMSDao.countOther("-1", "Currency")), res);
	}

	public void countItem(HttpServletRequest req, HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		String type = req.getParameter("type");
		int count = ePMSDao.countItem(menuId, type);
		outData(String.valueOf(count), res);
	}

	public void getCheckNo(HttpServletRequest req, HttpServletResponse res) {
		String serviceId = req.getParameter("serviceid");
		int checkNo = ePMSDao.getCheckNo(Integer.parseInt(serviceId));
		outData(String.valueOf(checkNo), res);
	}

	public void checkCoordinatesChange(HttpServletRequest req,
			HttpServletResponse res) {
		boolean checkCor = ePMSDaoEx.checkCoordinatesChange(smartcard);
		outData(String.valueOf(checkCor), res);
	}

	public void setLocationSynStatus(HttpServletRequest req,
			HttpServletResponse res) {
		String locationId = req.getParameter("locationId");
		boolean isSet = ePMSDaoEx.setLocationSynStatus(locationId);
		outData(String.valueOf(isSet), res);
	}

	public void setMessageStatus(HttpServletRequest req, HttpServletResponse res) {
		int messageId = Integer.parseInt(req.getParameter("messageid"));
		int status = Integer.parseInt(req.getParameter("status"));
		boolean isSet = ePMSDao.setMessageStatus(messageId, status);
		outData(String.valueOf(isSet), res);
	}

	public void closedWakeup(HttpServletRequest req, HttpServletResponse res) {
		boolean isSet = ePMSDao.closedWakeup(smartcard);
		outData(String.valueOf(isSet), res);
	}

	public void postedWakeup(HttpServletRequest req, HttpServletResponse res) {
		String hours = req.getParameter("hours");
		String minutes = req.getParameter("minutes");
		String wakeupDate = req.getParameter("wakeupdate");
		String phone = req.getParameter("phone");
		String outScreen = "[postedWakeup with params smartcard=" + smartcard
				+ ",hours" + hours + ",minutes=" + minutes + ",wakeupdate="
				+ wakeupDate + ",phone=" + phone + "]";
		log.info(outScreen);
		boolean isSet = ePMSDao.postedWakeup(smartcard, hours, minutes,
				wakeupDate, phone);
		outData(String.valueOf(isSet), res);
	}

	public void requestTaxi(HttpServletRequest req, HttpServletResponse res) {
		String pincode = req.getParameter("pincode");
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		int taxiId = Integer.parseInt(req.getParameter("taxid"));
		int iQty = Integer.parseInt(req.getParameter("iqty"));
		int seatNum = Integer.parseInt(req.getParameter("seatnum"));
		String orderTime = req.getParameter("ordertime");
		String orderDate = req.getParameter("orderdate");
		String phone = req.getParameter("phone");
		String whereTo = req.getParameter("whereto");
		String guestName = req.getParameter("guestname");
		log.info("--------Processing requestTaxi[smartcard=" + smartcard
				+ ",pincode=" + pincode + ",orderdate" + orderDate
				+ ",orderTime=" + orderTime);
		OrderRes result = ePMSDaoEx.requestTaxi(smartcard, pincode, taxiId,
				iQty, seatNum, orderTime, orderDate, phone, whereTo, guestName);
		if (result != null)
			outData(String.valueOf(true), res);
		else
			outData(String.valueOf(false), res);
		log.info("--------End Processing requestTaxi----------------------------");
	}

	public void requestTransportation(HttpServletRequest req,
			HttpServletResponse res) {
		String pinCode = req.getParameter("pincode");
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		String orderTime = req.getParameter("ordertime");
		String orderDate = req.getParameter("orderdate");
		String guestName = req.getParameter("guestname");
		log.info("--------Processing requestTransportation[smartcard="
				+ smartcard + ",pincode=" + pinCode + ",orderdate" + orderDate
				+ ",orderTime=" + orderTime);
		OrderRes result = ePMSDaoEx.requestTransportation(smartcard, pinCode,
				menuId, orderTime, orderDate, guestName);
		if (result != null)
			outData(String.valueOf(true), res);
		else
			outData(String.valueOf(false), res);
		log.info("--------End Processing requestTransportation----------------------------");
	}

	public void requestServiceHousekeeping(HttpServletRequest req,
			HttpServletResponse res) {
		String pinCode = req.getParameter("pincode");
		int menuId = Integer.parseInt(req.getParameter("menuid"));
		String orderTime = req.getParameter("ordertime");
		String orderDate = req.getParameter("orderdate");
		String guestName = req.getParameter("guestname");
		int HKType = Integer.parseInt(req.getParameter("hktype"));
		log.info("--------Processing requestServiceHousekeeping[smartcard="
				+ smartcard + ",pincode=" + pinCode + ",orderdate" + orderDate
				+ ",orderTime=" + orderTime);
		OrderRes result = ePMSDaoEx.requestHouseKeeping(smartcard, pinCode,
				menuId, orderTime, orderDate, guestName, HKType);
		if (result != null)
			outData(String.valueOf(true), res);
		else
			outData(String.valueOf(false), res);
		log.info("--------End Processing requestServiceHousekeeping----------------------------");
	}

	public void requestItemHouseKeeping(HttpServletRequest req,
			HttpServletResponse res) {
		int menuId = Integer.parseInt(req.getParameter("mmenuid"));
		int icode = Integer.parseInt(req.getParameter("icode"));
		int IQty = Integer.parseInt(req.getParameter("IQty"));
		String pinCode = req.getParameter("pincode");
		String orderTime = req.getParameter("ordertime");
		String orderDate = req.getParameter("orderdate");
		String guestName = req.getParameter("guestname");
		int checkNo = Integer.parseInt(req.getParameter("checkno"));
		int HKType = Integer.parseInt(req.getParameter("hktype"));
		log.info("--------Processing requestItemHouseKeeping[smartcard="
				+ smartcard + ",pincode=" + pinCode + ",orderdate" + orderDate
				+ ",orderTime=" + orderTime);

		Vector<CMNShopbill> itemOrders = new Vector<CMNShopbill>();
		CMNShopbill aHk = new CMNShopbill();
		aHk.setICode(icode);
		aHk.setIQty(IQty);
		aHk.setMenuID(menuId);
		aHk.setOrderTime(orderTime);
		aHk.setOrderDate(orderDate);
		aHk.setGuestName(guestName);
		aHk.setCheckNo(checkNo);
		itemOrders.add(aHk);
		ePMSDaoEx dao = new ePMSDaoEx();

		OrderRes result = dao.requestItemHouseKeeping(itemOrders, smartcard,
				pinCode, orderDate, orderTime, guestName);
		StringBuffer buffer = new StringBuffer();
		buffer.append(result.getAlertFlag());
		buffer.append("@");
		buffer.append(result.getCheckNo());
		outData(buffer.toString(), res);
		log.info("--------End Processing requestItemHouseKeeping----------------------------");
	}

	public void getItemContainer(HttpServletRequest req, HttpServletResponse res) {
		Vector<eContainer> vGames = ePMSDaoEx.getGames(smartcard);
		String service = req.getParameter("service");
		String path_export = null;
		if (service.equals("E10")) {// game
			path_export = "elcom/resources/xml/game/game.xml";
			eHotelXML hotel = new eHotelXML(path_export);
			hotel.exportGames(vGames, smartcard);
		}
		try {
			outXML(smartcard, path_export, res);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void getDataXml(HttpServletRequest req, HttpServletResponse res) {
		String service = req.getParameter("service");
		String android = req.getParameter("android");
		try {
			log.info("\r\n----------------------------------------------------------------------");
			log.info("*|--BEGIN STB[KEY=" + smartcard + ",service=" + service
					+ "] SEND REQUEST SYNDATA");
			String path = eHotelUtils.getPathOfService(getServletContext()
					.getResourceAsStream("config/ehotel_version.xml"), service,
					android);
			log.info("FILE SOURCE ON SERVER=" + path);
			outXML(smartcard, path, res);
		} catch (Exception ex) {
			log.error(ex);
			outError(res, 500, ex.getMessage());
			log.info("-->STB=" + smartcard + " IS NOT SYNDATA SUCCESS.");
			outError(res, 401, "STB=" + smartcard + "@" + service
					+ " is not syndata success");
		}

	}

	public static void outData(String content, HttpServletResponse res) {
		PrintWriter out = null;
		try {
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(),
					"UTF8"), true);
			out.write("HTTP/1.0 200 OK");
			out.write("Connection: close\r\n\r\n");
			out.write(content);
			out.flush();
		} catch (MalformedURLException mue) {
		} catch (IOException ioe) {
			log.info(ioe.getMessage());
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void outError(HttpServletResponse res, int errorNo,
			String errorText) {
		PrintWriter ps;
		try {
			res.setContentType("text/plain");
			res.setHeader("HOAVK", "HTTP/1.0 " + errorNo);
			ps = res.getWriter();
			ps.write("HTTP/1.0 " + errorNo);
			ps.write("Error:" + errorText);
			ps.write("Connection: close");
			ps.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void outXML(String serinum, String fileName, HttpServletResponse res)
			throws IOException, ServletException {
		PrintWriter out = null;
		if (fileName == null) {
			return;
		}
		BufferedInputStream buf = null;
		try {
			out = res.getWriter();
			res.setContentType("text/xml");
			buf = new BufferedInputStream(getServletContext()
					.getResourceAsStream(fileName));
			int readBytes = 0;
			while ((readBytes = buf.read()) != -1)
				out.write(readBytes);
		} catch (Exception ioe) {
			log.info("STB=" + serinum + " IS NOT SYNDATA SUCCESS.Cause[NOT ["
					+ fileName + "] ON SERVER" + "]");
			outError(res, 401, "NOT [" + fileName + "] ON SERVER");
		} finally {
			if (out != null)
				out.close();
			if (buf != null)
				buf.close();
		}
		log.info("THE END STB[" + serinum + "] processOutXML[FILE=" + fileName
				+ "]");
		log.info("--------------------------------------------------------------");
	}
	
	public static void main(String[] args) {
		ePMSProcess e = new ePMSProcess();
		System.out.println(e.checkSynData("20021"));
	}
}
