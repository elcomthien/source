package elcom.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.eod.util.UnicodeConverter;

import elcom.domain.Guest;
import elcom.domain.Logo;
import elcom.domain.OutletImage;
import elcom.domain.OutletMovie;
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
import elcom.domain.eWakeup;
import elcom.domain.eWakeupForms;
import elcom.domain.eWeather;
import elcom.domain.ex.eAcitivity;
import elcom.domain.ex.eAttraction;
import elcom.domain.ex.eHouseKeeping;
import elcom.domain.ex.eObject;
import elcom.utils.Utils;

public class DataUtils {
	private static Logger log = Logger.getLogger(DataUtils.class);
	public static String path = "/ePMS2.0/elcom/resources/image/";

	public static Vector<Logo> LoadLogo(Vector<String> vTmp) {
		Vector<Logo> ret = new Vector<Logo>();
		Logo item = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 2) {
				item = new Logo();
				item.setLogo_welcome((String) vTmp.get(i));
				item.setLogo_service((String) vTmp.get(i + 1));
				ret.add(item);
			}
		}
		return ret;
	}

	public static Vector<eContainer> LoadGames(Vector<String> vTmp) {
		Vector<eContainer> ret = new Vector<eContainer>();
		eContainer item = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				item = new eContainer();
				item.setId(Integer.parseInt((String) vTmp.get(i)));
				item.setEN((String) vTmp.get(i + 1));
				item.setVN((String) vTmp.get(i + 2));
				item.setImage(path + (String) vTmp.get(i + 3));
				item.setUrl((String) vTmp.get(i + 4));
				item.setAction((String) vTmp.get(i + 5));
				item.setService("E10");
				ret.add(item);
			}
		}
		return ret;
	}

	public static Vector<eMapLocation> LoadMapLocation(Vector<String> vTmp) {
		Vector<eMapLocation> ret = new Vector<eMapLocation>();
		eMapLocation item = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 10) {
				item = new eMapLocation();
				item.setLocationid(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				item.setName(decodeHtml(name));

				String address = (String) vTmp.get(i + 2);
				item.setAddress(decodeHtml(address));

				String def = (String) vTmp.get(i + 3);
				item.setDef(decodeHtml(def));

				item.setDistance((String) vTmp.get(i + 4));
				item.setX((String) vTmp.get(i + 5));
				item.setY((String) vTmp.get(i + 6));
				item.setStatus(Utils.parseInt((String) vTmp.get(i + 7)));
				item.setMenuId(Utils.parseInt((String) vTmp.get(i + 8)));
				item.setLangId(Utils.parseInt((String) vTmp.get(i + 9)));
				ret.add(item);
			}
		}
		return ret;
	}

	public static Vector<eAcitivity> LoadActivity(Vector<String> vTmp) {
		Vector<eAcitivity> ret = new Vector<eAcitivity>();
		eAcitivity activity = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				activity = new eAcitivity();
				activity.setICode(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				activity.setName(decodeHtml(name));

				String def = (String) vTmp.get(i + 2);
				activity.setDef(decodeHtml(def));
				ret.add(activity);
			}
		}
		return ret;
	}

	public static Vector<ServiceCommon> LoadArilneMenu(Vector<String> vTmp) {
		Vector<ServiceCommon> ret = new Vector<ServiceCommon>();
		ServiceCommon airline = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				airline = new ServiceCommon();
				airline.setId(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				airline.setName(decodeHtml(name));

				airline.setUrlLink((String) vTmp.get(i + 2));
				airline.setUrl(path + (String) vTmp.get(i + 3));
				airline.setUrlBg(path + (String) vTmp.get(i + 4));
				airline.setUrlIcon(path + (String) vTmp.get(i + 5));
				ret.add(airline);
			}
		}
		return ret;
	}

	public static List<eAttraction> LoadAttraction(Vector<String> vTmp) {
		List<eAttraction> ret = new ArrayList<eAttraction>();
		eAttraction attraction = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				attraction = new eAttraction();
				attraction.setICode(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				attraction.setName(decodeHtml(name));

				String def = (String) vTmp.get(i + 2);
				attraction.setDef(decodeHtml(def));

				String address = (String) vTmp.get(i + 3);
				attraction.setAddress(decodeHtml(address));

				attraction.setUrlImage(path + (String) vTmp.get(i + 4));
				attraction.setUrlBg(path + (String) vTmp.get(i + 5));
				attraction.setUrlIcon(path + (String) vTmp.get(i + 6));
				attraction.setMenuId(Utils.parseInt((String) vTmp.get(i + 7)));
				ret.add(attraction);
			}
		}
		return ret;
	}

	public static Vector<eHouseKeeping> LoadHouseKeeping(Vector<String> vTmp) {
		Vector<eHouseKeeping> ret = new Vector<eHouseKeeping>();
		eHouseKeeping hkeeping = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				hkeeping = new eHouseKeeping();
				hkeeping.setICode(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				hkeeping.setName(decodeHtml(name));

				hkeeping.setCurrency((String) vTmp.get(i + 2));
				hkeeping.setIUnit((String) vTmp.get(i + 3));
				hkeeping.setUrl(path + (String) vTmp.get(i + 4));
				hkeeping.setUrlBg(path + (String) vTmp.get(i + 5));
				hkeeping.setUrlIcon(path + (String) vTmp.get(i + 6));
				hkeeping.setMenuId(Utils.parseInt((String) vTmp.get(i + 7)));
				ret.add(hkeeping);
			}
		}
		return ret;
	}

	public static Guest LoadGuestInfo(Vector<String> vTmp) {
		Guest guest = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 10) {
				guest = new Guest();
				guest.setGuestId((String) vTmp.get(i));
				guest.setName((String) vTmp.get(i + 1));
				// Utils.outScreen(log,"-->returnValue[nameGuest=" +(String)
				// vTmp.get(i + 1) + "]", false);
				log.info("returnValue[nameGuest=" + (String) vTmp.get(i + 1)
						+ "]");
				guest.setPhone((String) vTmp.get(i + 2));
				guest.setArrival((String) vTmp.get(i + 3));
				guest.setDepature((String) vTmp.get(i + 4));
				guest.setRoomCode((String) vTmp.get(i + 5));
				guest.setLangCode((String) vTmp.get(i + 6));
				guest.setGuestTitle((String) vTmp.get(i + 7));
				guest.setFolioName((String) vTmp.get(i + 9));
				String isNew = (String) vTmp.get(i + 8);
				
				System.out.println("IsNew=" + isNew);
				if (isNew != null && isNew.equals("1")) { // khach da co pincode
															// do chinh khach
															// cung cap
					// guest.setIsNew(false);
					// Utils.outScreen(log,"-->returnValue[isNew=false || Khach da co pincode]",
					// false);
				}
				if (isNew != null && isNew.equals("2")) { // khach da co pincode
															// nhung duoc admin
															// reset lai
					// guest.setIsNew(false);
					// Utils.outScreen(log,"-->returnValue[isNew=true || Khach da co pincode.Nhung admin da reset lai.]",
					// false);
				} else if (isNew != null && isNew.equals("0")) { // khach lan
																	// dau tien
																	// check-in
																	// vao phong
					// guest.setIsNew(true);
					// Utils.outScreen(log,"-->returnValue[isNew=true || Khach moi hoan toan.]",
					// false);
				}

			}
		}
		return guest;
	}

	public static Vector<Guest> LoadGuests(Vector<String> vTmp) {
		Guest guest = null;
		Vector<Guest> tmp_vRs = new Vector<Guest>();

		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 9) {
				guest = new Guest();
				guest.setGuestId((String) vTmp.get(i));
				guest.setName((String) vTmp.get(i + 1));
				// Utils.outScreen(log,"-->returnValue[nameGuest=" +(String)
				// vTmp.get(i + 1) + "]", false);
				log.info("returnValue[nameGuest=" + (String) vTmp.get(i + 1)
						+ "]");
				guest.setPhone((String) vTmp.get(i + 2));
				guest.setArrival((String) vTmp.get(i + 3));
				guest.setDepature((String) vTmp.get(i + 4));
				guest.setRoomCode((String) vTmp.get(i + 5));
				guest.setLangCode((String) vTmp.get(i + 6));
				guest.setGuestTitle((String) vTmp.get(i + 7));
				String isNew = (String) vTmp.get(i + 8);
				System.out.println("IsNew=" + isNew);
				if (isNew != null && isNew.equals("1")) { // khach da co pincode
															// do chinh khach
															// cung cap
					// guest.setIsNew(false);
					// Utils.outScreen(log,"-->returnValue[isNew=false || Khach da co pincode]",
					// false);
				}
				if (isNew != null && isNew.equals("2")) { // khach da co pincode
															// nhung duoc admin
															// reset lai
					// guest.setIsNew(false);
					// Utils.outScreen(log,"-->returnValue[isNew=true || Khach da co pincode.Nhung admin da reset lai.]",
					// false);
				} else if (isNew != null && isNew.equals("0")) { // khach lan
																	// dau tien
																	// check-in
																	// vao phong
					// guest.setIsNew(true);
					// Utils.outScreen(log,"-->returnValue[isNew=true || Khach moi hoan toan.]",
					// false);
				}
				tmp_vRs.add(guest);

			}
		}
		// Utils.outScreen(log,"-->getGuestListInRoom : returnValue(size=" +
		// tmp_vRs.size() +")]", false);
		return tmp_vRs;
	}

	public static Vector<Promotion> LoadPromotion(Vector<String> vTmp) {
		Vector<Promotion> ret = new Vector<Promotion>();
		Promotion promotion = null;

		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				promotion = new Promotion();
				promotion.setId((String) vTmp.get(i));
				String name = (String) vTmp.get(i + 1);
				promotion.setName(decodeHtml(name));

				promotion.setName_vn(decodeHtml((String) vTmp.get(i + 2)));

				String def = (String) vTmp.get(i + 3);
				promotion.setDef(decodeHtml(def));

				promotion.setUrl(path + (String) vTmp.get(i + 4));
				promotion.setUrlBg(path + (String) vTmp.get(i + 5));
				promotion.setUrlIcon(path + (String) vTmp.get(i + 6));

				promotion.setUrlLink((String) vTmp.get(i + 7));

				ret.add(promotion);
			}
		} else {
			System.out.println("vTmp is null");
		}

		return ret;

	}

	public static Vector<ServiceCommon> LoadHomeService(Vector<String> vRs) {
		ServiceCommon service;
		Vector<ServiceCommon> tmp_vRs = new Vector<ServiceCommon>();

		if (vRs != null && vRs.size() > 0) {
			for (int i = 0; i < vRs.size(); i = i + 7) {
				service = new ServiceCommon();
				service.setId(Integer.parseInt((String) vRs.get(i)));
				String name = (String) vRs.get(i + 1);
				
				name = decodeHtml((String) vRs.get(i + 1));
				service.setName(name);
				System.out.println(name + " - " + path + (String) vRs.get(i + 3));
				service.setName_vn(decodeHtml((String) vRs.get(i + 2)));
				service.setUrl(path + (String) vRs.get(i + 3));
				service.setDef((String) vRs.get(i + 4));
				service.setUrlLink((String) vRs.get(i + 5));
				service.setApkcode((String) vRs.get(i + 4));
				service.setService_code((String) vRs.get(i + 6));
				tmp_vRs.add(service);
			}
		}

		return tmp_vRs;
	}

	public static Vector<ServiceCommon> LoadServiceMenu(Vector<String> vTmp) {
		Vector<ServiceCommon> serSubjV = new Vector<ServiceCommon>();
		if (vTmp == null) {
			log.info("Not have data");
			return serSubjV;
		}
		ServiceCommon subj = null;
		for (int i = 0; i < vTmp.size(); i = i + 5) {
			subj = new ServiceCommon();
			subj.setId(Integer.parseInt((String) vTmp.get(i)));
			String name = (String) vTmp.get(i + 1);
			name = decodeHtml(name);
			System.out.println(name + " - " + path + (String) vTmp.get(i + 2));
			subj.setName(name);
			subj.setUrl(path + (String) vTmp.get(i + 2));
			subj.setUrlBg(path + (String) vTmp.get(i + 3));
			subj.setUrlIcon(path + (String) vTmp.get(i + 4));
			// subj.setUrlLink((String) vTmp.get(i + 5));
			serSubjV.add(subj);
		}

		return serSubjV;
	}

	public static Vector<ServiceItem> LoadServiceItem(Vector<String> vTmp) {
		Vector<ServiceItem> ret = new Vector<ServiceItem>();
		ServiceItem service = null;
		if (vTmp == null) {
			return ret;
		}
		for (int i = 0; i < vTmp.size(); i = i + 12) {
			service = new ServiceItem();
			service.setICode((String) vTmp.get(i));
			String name = (String) vTmp.get(i + 1);
			service.setName(decodeHtml(name));

			String printName = (String) vTmp.get(i + 2);
			service.setPrintName(decodeHtml(printName));

			String def = (String) vTmp.get(i + 3);
			service.setDef(decodeHtml(def));

			service.setIMenu((String) vTmp.get(i + 4));
			String currency = (String) vTmp.get(i + 5);
			String curency_small = (String) vTmp.get(i + 6);
			String curency_large = (String) vTmp.get(i + 7);
			String units = (String) vTmp.get(i + 8);
			service.setIunit(units);
			String money = Utils.convertMoney(currency, units);
			service.setCurrency(money);
			curency_small = Utils.convertMoney(curency_small, units);
			service.setCurrency_small(curency_small);
			curency_large = Utils.convertMoney(curency_large, units);
			service.setCurrency_large(curency_large);
			service.setUrl(path + (String) vTmp.get(i + 9));
			service.setUrlBg(path + (String) vTmp.get(i + 10));
			service.setPicIcon(path + (String) vTmp.get(i + 11));

			ret.add(service);
		}

		return ret;
	}

	public static Vector<eObject> LoadWeb(Vector<String> vTmp) {
		eObject web = null;
		Vector<eObject> vWeb = new Vector<eObject>();
		if (vTmp == null) {
			System.out.println("Not have data");
			return vWeb;
		}

		for (int i = 0; i < vTmp.size(); i = i + 3) {
			web = new eObject();
			web.setId(Integer.parseInt((String) vTmp.get(i)));

			String name = (String) vTmp.get(i + 1);
			web.setName(decodeHtml(name));

			web.setDef((String) vTmp.get(i + 2));
			System.out.println("url=" + (String) vTmp.get(i + 2));
			vWeb.add(web);
		}
		return vWeb;

	}

	public static Vector<ServiceCharge> LoadBills(Vector<String> vTmp) {
		ServiceCharge charge = null;
		Vector<ServiceCharge> chargeV = new Vector<ServiceCharge>();
		if (vTmp == null) {
			System.out.println("Not have data");
			return chargeV;
		}

		for (int i = 0; i < vTmp.size(); i = i + 11) {
			charge = new ServiceCharge();
			charge.setId(Integer.parseInt((String) vTmp.get(i)));
			charge.setFolio((String) vTmp.get(i + 1));
			String code = (String) vTmp.get(i + 2);
			charge.setCode(decodeHtml(code));
			charge.setPrice((String) vTmp.get(i + 3));
			charge.setQuanlity((String) vTmp.get(i + 4));
			String amount = (String) vTmp.get(i + 5);

			Date startDate = null;
			if ((String) vTmp.get(i + 6) != null) {
				startDate = Utils.parseDate((String) vTmp.get(i + 6));

			} else {
				startDate = new Date();
			}
            System.out.println("startDate=" + (String) vTmp.get(i + 6) + " - code: " + code + " - Price: " + (String) vTmp.get(i + 3));
			log.info("startDate=" + (String) vTmp.get(i + 6));
			try {
				charge.setStartDate(Utils.format(startDate, "dd/MM/yy"));
			} catch (Exception ex) {
				charge.setStartDate((String) vTmp.get(i + 6));
			}
			charge.setStartTime((String) vTmp.get(i + 7));
			String recordType = (String) vTmp.get(i + 8);
			charge.setServiceType(recordType);
			charge.setCashCard((String) vTmp.get(i + 9));

			String unit = (String) vTmp.get(i + 10);
			if (unit == null) {
				unit = "USD";
			}
			charge.setUnit(unit);

			String money = amount;
			if (recordType != null && recordType.equals("USER")) {
				money = Utils.processAmountOfUser(money);
			} else {
				money = Utils.convertMoney(money, unit);
			}
			System.out.println("startDate=" + (String) vTmp.get(i + 6) + " - code: " + code + " - Price: " + money);
			charge.setAmount(money);

			chargeV.add(charge);
		}
		return chargeV;
	}

	public static Vector<eCountry> LoadCountries(Vector<String> vTmp) {
		Vector<eCountry> ret = new Vector<eCountry>();
		eCountry country = null;
		if (vTmp == null) {
			return ret;
		}
		int count = 0;
		for (int i = 0; i < vTmp.size(); i = i + 6) {
			country = new eCountry();
			country.setId((String) vTmp.get(i));
			String name = (String) vTmp.get(i + 1);
			country.setName(decodeHtml(name));
			String def = (String) vTmp.get(i + 2);
			country.setDef(decodeHtml(def));
			country.setUrl((String) vTmp.get(i + 3));
			country.setUrlBg((String) vTmp.get(i + 4));
			country.setUrlICon((String) vTmp.get(i + 5));
			ret.add(country);
			count++;
		}
		System.out.println("[PMSCtnDAO.getCountries:returnValue(size=" + count
				+ ")]");
		return ret;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<eWeather> LoadWeathers(Vector<String> vTmp) {
		Vector<eWeather> ret = new Vector<eWeather>();
		if (vTmp == null) {
			log.info("Not have data at LoadWeather");
			return ret;
		}
		eWeather weather = null;
		// order by

		Map map = new HashMap<Integer, eWeather>();
		for (int i = 0; i < vTmp.size(); i = i + 8) {
			weather = new eWeather();
			weather.setId(Integer.parseInt((String) vTmp.get(i)));
			weather.setPostedDate((String) vTmp.get(i + 1));
			weather.setTemperature(Integer.parseInt((String) vTmp.get(i + 2)));
			weather.setMinTemperature(Integer.parseInt((String) vTmp.get(i + 3)));
			weather.setMaxTemperature(Integer.parseInt((String) vTmp.get(i + 4)));
			String sday = (String) vTmp.get(i + 5);
			weather.setSday(sday);
			weather.setPicImage((String) vTmp.get(i + 6));
			weather.setDef((String) vTmp.get(i + 7));
			// ret.add(weather);
			map.put(new Integer(sday), weather);
		}
		// tinh toan sday
		ret = FilterData(map);
		return ret;
	}

	private static Vector<eWeather> FilterData(Map<Integer, eWeather> vMap) {
		Vector<eWeather> ret = new Vector<eWeather>();
		Date today = new Date();
		Integer[] sdayArray = new Integer[5];
		int thuHienTai = today.getDay() + 1;
		if (thuHienTai == 1)
			thuHienTai = 8;

		switch (thuHienTai) {
		case 2:
			sdayArray = new Integer[] { 2, 3, 4, 5, 6 };
			break;
		case 3:
			sdayArray = new Integer[] { 3, 4, 5, 6, 7 };
			break;
		case 4:
			sdayArray = new Integer[] { 4, 5, 6, 7, 8 };
			break;
		case 5:
			sdayArray = new Integer[] { 5, 6, 7, 8, 2 };
			break;
		case 6:
			sdayArray = new Integer[] { 6, 7, 8, 2, 3 };
			break;
		case 7:
			sdayArray = new Integer[] { 7, 8, 2, 3, 4 };
			break;
		case 8:
			sdayArray = new Integer[] { 8, 2, 3, 4, 5 };
			break;
		default:
			break;
		}
		eWeather weather = null;
		for (int i = 0; i < sdayArray.length; i++) {
			if (vMap.get(sdayArray[i]) != null) {
				weather = vMap.get(sdayArray[i]);
				ret.add(weather);
			}
		}
		return ret;
	}

	public static Vector<eWakeup> LoadWakeups(Vector<String> vTmp) {
		eWakeup wakeup = null;
		Vector<eWakeup> sV = new Vector<eWakeup>();
		if (vTmp == null || vTmp.size() == 0) {
			log.info("Not have data at LoadWakeups");
			return sV;
		}
		for (int i = 0; i < vTmp.size(); i = i + 5) {
			wakeup = new eWakeup();
			String id = (String) vTmp.get(i);
			if (id == null) {
				id = "-1";
			}
			wakeup.setId(Integer.parseInt(id));
			wakeup.setHours((String) vTmp.get(i + 1));
			wakeup.setMinutes((String) vTmp.get(i + 2));
			wakeup.setWakeupDate((String) vTmp.get(i + 3));
			wakeup.setWakeupForms((String) vTmp.get(i + 4));
			sV.add(wakeup);
		}
		return sV;

	}

	public static Vector<eWakeupForms> LoadWakeupForms(Vector<String> vTmp) {
		eWakeupForms forms = null;
		Vector<eWakeupForms> sV = new Vector<eWakeupForms>();
		if (vTmp == null || vTmp.size() == 0) {
			log.info("Not have data at LoadWakeupForms");
			return sV;
		}
		for (int i = 0; i < vTmp.size(); i = i + 2) {
			forms = new eWakeupForms();
			forms.setId(Integer.parseInt((String) vTmp.get(i)));
			String name = (String) vTmp.get(i + 1);
			forms.setName(decodeHtml(name));
			sV.add(forms);
		}
		return sV;

	}

	public static Vector<eCurrency> LoadCurrencies(Vector<String> vTmp) {
		Vector<eCurrency> ret = new Vector<eCurrency>();
		eCurrency currency = null;
		for (int i = 0; i < vTmp.size(); i = i + 11) {
			currency = new eCurrency();
			currency.setId((String) vTmp.get(i));
			currency.setSymbol((String) vTmp.get(i + 1));
			currency.setName((String) vTmp.get(i + 2));
			currency.setCode((String) vTmp.get(i + 3));

			String buyRate = (String) vTmp.get(i + 4);
			if (buyRate == null || buyRate.equals("")) {
				buyRate = "0";
			}
			currency.setBuyRate(buyRate);
			String sellRate = (String) vTmp.get(i + 5);
			if (sellRate == null || sellRate.equals("")) {
				sellRate = "0";
			}
			currency.setSellRate(sellRate);
			String TransferRate = (String) vTmp.get(i + 6);
			if (TransferRate == null || TransferRate.equals("")) {
				TransferRate = "0";
			}
			currency.setTransferRate(TransferRate);

			String exRateVN = (String) vTmp.get(i + 7);
			if (exRateVN == null || exRateVN.equals("")) {
				exRateVN = "0";
			}
			currency.setExRateVN(exRateVN);

			currency.setUrl((String) vTmp.get(i + 8));
			currency.setUrlBg((String) vTmp.get(i + 9));
			currency.setUrlICon((String) vTmp.get(i + 10));

			ret.add(currency);
		}

		return ret;

	}

	public static Vector<OutletImage> LoadOutletImages(Vector<String> vTmp) {
		Vector<OutletImage> ret = new Vector<OutletImage>();
		OutletImage image = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				image = new OutletImage();
				image.setId((String) vTmp.get(i));
				String name = (String) vTmp.get(i + 1);
				image.setName(decodeHtml(name));
				String def = (String) vTmp.get(i + 2);
				image.setDef(decodeHtml(def));
				image.setUrl(path + (String) vTmp.get(i + 3));
				image.setUrlBg(path + (String) vTmp.get(i + 4));
				image.setUrlIcon(path + (String) vTmp.get(i + 5));
				ret.add(image);
			}
		}

		return ret;
	}

	public static OutletMovie LoadOutletMovie(Vector<String> vTmp) {
		OutletMovie movie = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				movie = new OutletMovie();
				movie.setId((String) vTmp.get(i));
				movie.setName((String) vTmp.get(i + 1));
				movie.setUrl((String) vTmp.get(i + 2));
				movie.setDef((String) vTmp.get(i + 3));
			}
		}
		return movie;
	}

	public static Vector<OutletImage> LoadHotelInfo(Vector<String> vTmp) {
		Vector<OutletImage> ret = new Vector<OutletImage>();
		OutletImage image = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				image = new OutletImage();
				image.setId((String) vTmp.get(i));
				String name = (String) vTmp.get(i + 1);
				image.setName(decodeHtml(name));
				System.out.println(name);
				String def = (String) vTmp.get(i + 2);
				image.setDef(decodeHtml(def));
				System.out.println(decodeHtml(def));

				image.setUrl(path + (String) vTmp.get(i + 3));
				image.setUrlBg(path + (String) vTmp.get(i + 4));
				image.setUrlIcon(path + (String) vTmp.get(i + 5));
				ret.add(image);
			}
		}
		return ret;
	}

	public static Vector<VersionData> LoadVersions(Vector<String> vTmp) {
		Vector<VersionData> vv = new Vector<VersionData>();
		VersionData version = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return vv;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				version = new VersionData();
				version.setCode((String) vTmp.get(i));
				version.setName((String) vTmp.get(i + 1));
				version.setVersion((String) vTmp.get(i + 2));
				version.setPath((String) vTmp.get(i + 3));

				vv.add(version);
			}
		}
		return vv;
	}

	public static Vector<eObject> LoadObject(Vector<String> vTmp) {
		Vector<eObject> ret = new Vector<eObject>();
		eObject obj = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				obj = new eObject();
				obj.setId(Utils.parseInt((String) vTmp.get(i)));

				String name = (String) vTmp.get(i + 1);
				obj.setName(decodeHtml(name));

				String def = (String) vTmp.get(i + 2);
				obj.setDef(decodeHtml(def));

				ret.add(obj);
			}
		}
		return ret;

	}

	public static Vector<eMessage> LoadMessages(Vector<String> vTmp, int numStr) {
		Vector<eMessage> ret = new Vector<eMessage>();
		eMessage mess = null;
		for (int i = 0; i < vTmp.size(); i = i + 12) {
			mess = new eMessage();
			mess.setId(Integer.parseInt((String) vTmp.get(i)));
			String subject = (String) vTmp.get(i + 1);
			mess.setSubject(decodeHtml(subject));

			String content = (String) vTmp.get(i + 2);
			mess.setContent(decodeHtml(content));

			String content_top = (String) vTmp.get(i + 3);
			mess.setContent_top(decodeHtml(content_top));

			String content_bottom = (String) vTmp.get(i + 4);
			mess.setContent_bottom(decodeHtml(content_bottom));

			String isRead = (String) vTmp.get(i + 5);
			if (isRead == null || isRead.equals("")) {
				isRead = "0";
			}
			mess.setIsRead(Integer.parseInt(isRead));

			mess.setCheckNo(Utils.parseInt((String) vTmp.get(i + 6)));

			String sender = (String) vTmp.get(i + 7);
			mess.setSender(decodeHtml(sender));

			String type = (String) vTmp.get(i + 8);
			if (type == null) {
				type = "NORMAL";
			}
			mess.setType(type);

			mess.setEnterDate((String) vTmp.get(i + 9));
			mess.setEnterTime((String) vTmp.get(i + 10));
			mess.setFolioNum(Utils.parseInt((String) vTmp.get(i + 11)));
			ret.add(mess);
		}

		return ret;
	}

	/**
	 * splitStrMessageProfile ;cat chuoi theo may khaong trang Example: str=
	 * vuong kieu hoa .--> subStrMessageProfile(str,2)--> vuong kieu
	 * 
	 * @param str
	 *            String
	 * @param numStr
	 *            int
	 * @return String
	 */
	private static String splitStrMessageProfile(String str, int symbolNum) {
		String ret = "";
		if (str == null || str.equals("")) {
			return "";
		}
		int emptyNum = getEmptyNumOfStr(str, symbolNum);
		// System.out.println("so khoang trang cua str=" + emptyNum +
		// "symbolNum =" + symbolNum);
		StringTokenizer strToken = new StringTokenizer(str);
		int i = 0;
		while (strToken.hasMoreTokens()) {

			if (i == (emptyNum - 1)) {
				break;
			}
			ret += strToken.nextToken() + " ";
			i++;
		}
		// System.out.println("split profile FolioMessage=" + ret);
		return ret;
	}

	/**
	 * xac dinh so khoang trang cua chuoi
	 * 
	 * @param str
	 *            String
	 * @param symbolNum
	 *            int
	 * @return int
	 */
	private static int getEmptyNumOfStr(String str, int symbolNum) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		String tmp = str.substring(0, symbolNum);
		// System.out.println("chuoi cat tu vi tri 0-" + symbolNum + " la=" +
		// tmp);
		StringTokenizer strToken = new StringTokenizer(tmp);
		int i = 0;
		while (strToken.hasMoreTokens()) {
			strToken.nextToken();
			i++;
		}
		return i;
	}

	private static String decodeHtml(String original) {
		return UnicodeConverter.decodeUnicode(original);
	}

}
