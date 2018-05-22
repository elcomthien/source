package ehotel.utils;

import java.util.Date;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.eod.util.UnicodeConverter;

import ehotel.domain.group.eCategory;
import ehotel.domain.group.eGroup;
import ehotel.domain.group.eRole;
import ehotel.domain.group.eUser;
import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.liveTV.LiveTVServer;
import ehotel.domain.menu.Group;
import ehotel.domain.menu.Menu;
import ehotel.domain.mod.Mod;
import ehotel.domain.pms.eAPKCode;
import ehotel.domain.pms.eActivity;
import ehotel.domain.pms.eAdvertise;
import ehotel.domain.pms.eAttraction;
import ehotel.domain.pms.eBillCharge;
import ehotel.domain.pms.eExchangeRate;
import ehotel.domain.pms.eFolio;
import ehotel.domain.pms.eGuest;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eLocation;
import ehotel.domain.pms.eLocationMap;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eMessage;
import ehotel.domain.pms.ePromotion;
import ehotel.domain.pms.eRestaurant;
import ehotel.domain.pms.eSTB;
import ehotel.domain.pms.eService;
import ehotel.domain.pms.eUrlAirline;
import ehotel.domain.report.DataReport;
import ehotel.domain.vod.EServer;
import ehotel.domain.vod.EVSStorage;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;

public class DataUtils {
	private static Logger log = Logger.getLogger(DataUtils.class);

	public static Vector<eAPKCode> LoadApkcodes(Vector<String> vTmp) {
		Vector<eAPKCode> ret = new Vector<eAPKCode>();
		eAPKCode group = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				group = new eAPKCode();
				group.setName((String) vTmp.get(i));
				group.setPath((String) vTmp.get(i + 1));
				group.setVersion((String) vTmp.get(i + 2));
				ret.add(group);
			}
		}
		return ret;
	}

	public static Vector<eService> LoadServices(Vector<String> vTmp) {
		Vector<eService> ret = new Vector<eService>();
		eService group = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 5) {
				group = new eService();
				group.setServiceId(Integer.parseInt((String) vTmp.get(i)));
				group.setServiceName((String) vTmp.get(i + 1));
				group.setUrlImage((String) vTmp.get(i + 2));
				group.setAction((String) vTmp.get(i + 3));
				group.setInvisble(Integer.parseInt((String) vTmp.get(i + 4)));
				ret.add(group);
			}
		}
		return ret;
	}

	// Phan user
	public static Vector<eGroup> LoadGroups(Vector<String> vTmp) {
		Vector<eGroup> ret = new Vector<eGroup>();
		eGroup group = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 2) {
				group = new eGroup();
				group.setGroupId(Integer.parseInt((String) vTmp.get(i)));
				group.setName((String) vTmp.get(i + 1));
				ret.add(group);
			}
		}
		return ret;
	}

	public static Vector<eUser> LoadUsers(Vector<String> vTmp) {
		Vector<eUser> ret = new Vector<eUser>();
		eUser user = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 7) {
				user = new eUser();
				user.setId(Integer.parseInt((String) vTmp.get(i)));
				user.setName((String) vTmp.get(i + 1));
				user.setAddress((String) vTmp.get(i + 2));
				user.setDepartment((String) vTmp.get(i + 3));
				user.setAccount((String) vTmp.get(i + 4));
				user.setCreateDate((String) vTmp.get(i + 5));
				user.setActive(Integer.parseInt((String) vTmp.get(i + 6)));
				ret.add(user);
			}
		}
		return ret;
	}

	public static Vector<eRole> LoadRoles(Vector<String> vTmp) {
		Vector<eRole> ret = new Vector<eRole>();
		eRole arole = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				arole = new eRole();
				arole.setId(Integer.parseInt((String) vTmp.get(i)));
				arole.setName((String) vTmp.get(i + 1));
				arole.setValue((String) vTmp.get(i + 2));

				ret.add(arole);
			}
		}
		return ret;
	}

	public static Vector<eCategory> LoadCategories(Vector<String> vTmp) {
		Vector<eCategory> ret = new Vector<eCategory>();
		eCategory item = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				item = new eCategory();
				item.setCateId(Integer.parseInt((String) vTmp.get(i)));
				item.setName((String) vTmp.get(i + 1));
				item.setParentId(Integer.parseInt((String) vTmp.get(i + 2)));
				item.setLevel(Integer.parseInt((String) vTmp.get(i + 3)));
				ret.add(item);
			}
		}
		return ret;
	}

	// PHAN PMS
	public static Vector<eHousekeeping> LoadItemHousekeeping(Vector<String> vTmp) {
		Vector<eHousekeeping> ret = new Vector<eHousekeeping>();
		eHousekeeping item = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				item = new eHousekeeping();
				item.setId(Integer.parseInt((String) vTmp.get(i)));
				item.setName((String) vTmp.get(i + 1));
				item.setPrice((String) vTmp.get(i + 2));
				item.setIunit((String) vTmp.get(i + 3));
				item.setUrlImage((String) vTmp.get(i + 4));
				item.setInvisible(Integer.parseInt((String) vTmp.get(i + 5)));
				ret.add(item);
			}
		}
		return ret;
	}

	public static Vector<eRestaurant> LoadItemRestauarnt(Vector<String> vTmp) {
		Vector<eRestaurant> ret = new Vector<eRestaurant>();
		eRestaurant item = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 5) {
				item = new eRestaurant();
				item.setId(Integer.parseInt((String) vTmp.get(i)));
				item.setName((String) vTmp.get(i + 1));
				item.setDef((String) vTmp.get(i + 2));
				item.setUrlImage((String) vTmp.get(i + 3));
				item.setInvisible(Integer.parseInt((String) vTmp.get(i + 4)));
				ret.add(item);
			}
		}
		return ret;
	}

	public static Vector<eItem> LoadItemDining(Vector<String> vTmp) {
		Vector<eItem> ret = new Vector<eItem>();
		eItem item = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 9) {
				item = new eItem();
				item.setICode(Integer.parseInt((String) vTmp.get(i)));
				item.setName((String) vTmp.get(i + 1));
				item.setDef((String) vTmp.get(i + 2));
				item.setCurrency((String) vTmp.get(i + 3));
				item.setCurrency_large((String) vTmp.get(i + 4));
				item.setCurrency_small((String) vTmp.get(i + 5));
				item.setIUnit((String) vTmp.get(i + 6));
				item.setUrlImage((String) vTmp.get(i + 7));
				item.setInvisible(Integer.parseInt((String) vTmp.get(i + 8)));
				ret.add(item);
			}
		}
		return ret;
	}

	// Phan map
	public static Vector<eLocationMap> LoadMapDirectionLocation(
			Vector<String> vTmp) {
		Vector<eLocationMap> ret = new Vector<eLocationMap>();
		eLocationMap aLoc = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 7) {
				aLoc = new eLocationMap();
				aLoc.setId(Integer.parseInt((String) vTmp.get(i)));
				aLoc.setName((String) vTmp.get(i + 1));
				aLoc.setAddress((String) vTmp.get(i + 2));
				aLoc.setPhone((String) vTmp.get(i + 3));
				aLoc.setDistance((String) vTmp.get(i + 4));
				aLoc.setX((String) vTmp.get(i + 5));
				aLoc.setY((String) vTmp.get(i + 6));
				ret.add(aLoc);
			}
		}
		return ret;
	}

	// phan message
	public static Vector<eMessage> LoadMessages(Vector<String> vTmp) {
		Vector<eMessage> ret = new Vector<eMessage>();
		eMessage mess = null;
		if (vTmp == null) {
			log.info("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
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
		}
		return ret;
	}

	public static Vector<eBillCharge> LoadBillCharges(Vector<String> vTmp) {
		Vector<eBillCharge> ret = new Vector<eBillCharge>();
		eBillCharge abill = null;
		if (vTmp == null) {
			System.out.println("Not have data");
			return ret;
		}
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				abill = new eBillCharge();
				abill.setId(Integer.parseInt((String) vTmp.get(i)));
				abill.setCode((String) vTmp.get(i + 1));
				abill.setPrice((String) vTmp.get(i + 2));
				abill.setQuantity(Integer.parseInt((String) vTmp.get(i + 3)));
				String amount = (String) vTmp.get(i + 4);

				Date startDate = null;
				if ((String) vTmp.get(i + 5) != null)
					startDate = Utils.parseDate((String) vTmp.get(i + 5));
				else
					startDate = new Date();

				try {
					abill.setDateTime(Utils.format(startDate, "dd/MM/yy"));
				} catch (Exception ex) {
					abill.setDateTime((String) vTmp.get(i + 5));
				}
				String recordType = (String) vTmp.get(i + 6);
				abill.setRecordType(recordType);

				String unit = (String) vTmp.get(i + 7);
				if (unit == null)
					unit = "USD";
				abill.setIUnit(unit);

				String money = amount;
				if (recordType != null && recordType.equals("USER"))
					money = Utils.processAmountOfUser(money);
				else
					money = Utils.convertMoney(money, unit);

				abill.setAmount(money);

				ret.add(abill);
			}

		}
		return ret;
	}

	public static Vector<String> LoadAdvTypeList(Vector<String> vTmp) {
		Vector<String> ret = new Vector<String>();
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 1) {
				ret.add((String) vTmp.get(i));
			}
		}
		return ret;
	}

	public static Vector<eAdvertise> LoadImageAdverties(Vector<String> vTmp) {
		Vector<eAdvertise> ret = new Vector<eAdvertise>();
		eAdvertise adv = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				adv = new eAdvertise();
				adv.setId(Utils.parseInt((String) vTmp.get(i)));
				adv.setName((String) vTmp.get(i + 1));
				adv.setUrlImage((String) vTmp.get(i + 2));
				adv.setUrlBg((String) vTmp.get(i + 3));
				adv.setUrlIcon((String) vTmp.get(i + 3));
				adv.setType((String) vTmp.get(i + 4));
				adv.setInvisibe(Utils.parseInt((String) vTmp.get(i + 5)));
				ret.add(adv);
			}
		}
		return ret;
	}

	public static Vector<eGuest> LoadGuests(Vector<String> vTmp) {
		Vector<eGuest> ret = new Vector<eGuest>();
		eGuest adv = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				adv = new eGuest();
				adv.setGuestId(Utils.parseInt((String) vTmp.get(i)));
				adv.setName((String) vTmp.get(i + 1));
				adv.setArrivalDate((String) vTmp.get(i + 2));
				adv.setDepartmentDate((String) vTmp.get(i + 3));
				adv.setRoomshare(Utils.parseInt((String) vTmp.get(i + 4)));
				adv.setStatus(Utils.parseInt((String) vTmp.get(i + 5)));
				ret.add(adv);
			}
		}
		return ret;

	}

	public static Vector<eFolio> LoadFolios(Vector<String> vTmp) {
		Vector<eFolio> vFolios = new Vector<eFolio>();
		eFolio folio = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				folio = new eFolio();
				folio.setFolioNum(Utils.parseInt((String) vTmp.get(i)));
				folio.setFolioCode((String) vTmp.get(i + 1));
				folio.setFolioType((String) vTmp.get(i + 2));
				// folio.setSmartCard((String) vTmp.get(i + 3));
				folio.setStatus(Utils.parseInt((String) vTmp.get(i + 3)));
				vFolios.add(folio);
			}
		}
		return vFolios;

	}

	public static Vector<eSTB> LoadSTBList(Vector<String> vTmp) {
		Vector<eSTB> ret = new Vector<eSTB>();
		eSTB stb = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				stb = new eSTB();
				stb.setId(Utils.parseInt((String) vTmp.get(i)));
				stb.setKeyCode((String) vTmp.get(i + 1));
				stb.setIP((String) vTmp.get(i + 2));
				stb.setRoomCode((String) vTmp.get(i + 3));
				stb.setCreatedate((String) vTmp.get(i + 4));
				stb.setStatus(Utils.parseInt((String) vTmp.get(i + 5)));
				ret.add(stb);
			}
		}
		return ret;
	}

	public static Vector<eUrlAirline> LoadUrlAirlines(Vector<String> vTmp) {
		Vector<eUrlAirline> ret = new Vector<eUrlAirline>();
		eUrlAirline urline = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 5) {
				urline = new eUrlAirline();
				urline.setId(Utils.parseInt((String) vTmp.get(i)));
				urline.setName((String) vTmp.get(i + 1));
				urline.setUrlImage((String) vTmp.get(i + 2));
				urline.setUrlLink((String) vTmp.get(i + 3));
				urline.setInvisble(Utils.parseInt((String) vTmp.get(i + 4)));
				ret.add(urline);
			}
		}
		return ret;
	}

	public static Vector<eExchangeRate> LoadExchangeRates(Vector<String> vTmp) {
		Vector<eExchangeRate> ret = new Vector<eExchangeRate>();
		eExchangeRate exchange = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 10) {
				exchange = new eExchangeRate();
				exchange.setId(Utils.parseInt((String) vTmp.get(i)));
				exchange.setCode((String) vTmp.get(i + 1));
				exchange.setName((String) vTmp.get(i + 2));
				exchange.setBuyRate((String) vTmp.get(i + 3));
				exchange.setSellRate((String) vTmp.get(i + 4));
				exchange.setTransferRate((String) vTmp.get(i + 5));
				exchange.setBankingName((String) vTmp.get(i + 6));
				exchange.setUrlImage((String) vTmp.get(i + 7));
				exchange.setUrlBg((String) vTmp.get(i + 8));
				exchange.setInvisibel(Utils.parseInt((String) vTmp.get(i + 9)));
				ret.add(exchange);
			}
		}
		return ret;
	}

	public static eLocation LoadMapLocation(Vector<String> vTmp) {
		eLocation location = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				location = new eLocation();
				location.setId(Utils.parseInt((String) vTmp.get(i)));
				location.setX((String) vTmp.get(i + 1));
				location.setY((String) vTmp.get(i + 2));
			}
		}
		return location;

	}

	public static Vector<eMenu> LoadMenuPms(Vector<String> vTmp) {
		Vector<eMenu> ret = new Vector<eMenu>();
		eMenu amenu = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 7) {
				amenu = new eMenu();
				amenu.setMenuId(Utils.parseInt((String) vTmp.get(i)));
				amenu.setMenuName((String) vTmp.get(i + 1));
				amenu.setUrlImage((String) vTmp.get(i + 2));
				amenu.setUrlBg((String) vTmp.get(i + 3));
				amenu.setUrlIcon((String) vTmp.get(i + 4));
				amenu.setParentId(Utils.parseInt((String) vTmp.get(i + 5)));
				amenu.setType((String) vTmp.get(i + 6));
				ret.add(amenu);
			}
		}
		return ret;
	}

	public static Vector<ePromotion> LoadPromotions(Vector<String> vTmp) {
		Vector<ePromotion> ret = new Vector<ePromotion>();
		ePromotion promotion = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				promotion = new ePromotion();
				promotion.setId(Utils.parseInt((String) vTmp.get(i)));
				promotion.setName((String) vTmp.get(i + 1));
				promotion.setContent((String) vTmp.get(i + 2));
				promotion.setUrlImage((String) vTmp.get(i + 3));
				promotion.setLinkWeb((String) vTmp.get(i + 4));
				promotion
						.setInvisible(Utils.parseInt((String) vTmp.get(i + 5)));
				ret.add(promotion);
			}
		}
		return ret;
	}

	public static Vector<eActivity> LoadItemOfActi(Vector<String> vTmp) {
		Vector<eActivity> ret = new Vector<eActivity>();
		eActivity acti = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				acti = new eActivity();
				acti.setId(Utils.parseInt((String) vTmp.get(i)));
				acti.setName((String) vTmp.get(i + 1));
				acti.setDef((String) vTmp.get(i + 2));
				acti.setInvisible(Utils.parseInt((String) vTmp.get(i + 3)));
				ret.add(acti);
			}
		}
		return ret;
	}

	public static Vector<eAttraction> LoadItemOfAttraction(Vector<String> vTmp) {
		Vector<eAttraction> ret = new Vector<eAttraction>();
		eAttraction attraction = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 5) {
				attraction = new eAttraction();
				attraction.setId(Utils.parseInt((String) vTmp.get(i)));
				attraction.setName((String) vTmp.get(i + 1));
				attraction.setDef((String) vTmp.get(i + 2));
				attraction.setAddress((String) vTmp.get(i + 3));
				attraction
						.setInvisible(Utils.parseInt((String) vTmp.get(i + 4)));
				ret.add(attraction);
			}
		}
		return ret;
	}

	public static Vector<eImage> LoadItemOfHotel(Vector<String> vTmp) {
		return LoadOutletImages(vTmp);
	}

	private static Vector<eImage> LoadOutletImages(Vector<String> vTmp) {
		Vector<eImage> ret = new Vector<eImage>();
		eImage aimage = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 7) {
				aimage = new eImage();
				aimage.setId(Utils.parseInt((String) vTmp.get(i)));
				aimage.setName((String) vTmp.get(i + 1));
				aimage.setDef((String) vTmp.get(i + 2));
				aimage.setUrlImage((String) vTmp.get(i + 3));
				aimage.setUrlBg((String) vTmp.get(i + 4));
				aimage.setUrlIcon((String) vTmp.get(i + 5));
				aimage.setInvisible(Utils.parseInt((String) vTmp.get(i + 6)));
				ret.add(aimage);
			}
		}
		return ret;
	}

	// PHAN MEDIA
	public static Vector<Vod> LoadVod(Vector<String> vTmp) {
		Vector<Vod> ret = new Vector<Vod>();
		Vod aVod = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 12) {
				aVod = new Vod();
				aVod.setId(Utils.parseInt((String) vTmp.get(i)));
				String title = (String) vTmp.get(i + 1);
				aVod.setTitle(title);

				String director = (String) vTmp.get(i + 2);
				aVod.setDirector(director);

				String actor = (String) vTmp.get(i + 3);
				aVod.setActors(actor);

				String plot = (String) vTmp.get(i + 4);
				aVod.setPlot(plot);

				aVod.setPoster((String) vTmp.get(i + 5));
				aVod.setCurrency((String) vTmp.get(i + 6));
				aVod.setIUnit((String) vTmp.get(i + 7));
				aVod.setReleased(Utils.parseInt((String) vTmp.get(i + 8)));
				aVod.setIstrailer(Utils.parseInt((String) vTmp.get(i + 9)));
				aVod.setIssubtile(Utils.parseInt((String) vTmp.get(i + 10)));
				aVod.setStatus(Utils.parseInt((String) vTmp.get(i + 11)));
				ret.add(aVod);
			}
		}
		return ret;
	}

	public static Vector<Vod> LoadVod_syn(Vector<String> vTmp) {
		Vector<Vod> ret = new Vector<Vod>();
		Vod aVod = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 10) {
				aVod = new Vod();
				aVod.setId(Utils.parseInt((String) vTmp.get(i)));
				String title = (String) vTmp.get(i + 1);
				aVod.setTitle(title);

				String director = (String) vTmp.get(i + 2);
				aVod.setDirector(director);

				String actor = (String) vTmp.get(i + 3);
				aVod.setActors(actor);

				String plot = (String) vTmp.get(i + 4);
				aVod.setPlot(plot);

				aVod.setPoster((String) vTmp.get(i + 5));
				aVod.setCurrency((String) vTmp.get(i + 6));
				aVod.setIUnit((String) vTmp.get(i + 7));
				aVod.setStatus(Utils.parseInt((String) vTmp.get(i + 8)));

				aVod.setSessionId((String) vTmp.get(i + 9));
				ret.add(aVod);
			}
		}
		return ret;
	}

	public static Vector<SubTitle> LoadSubtitle(Vector<String> vTmp) {
		Vector<SubTitle> ret = new Vector<SubTitle>();
		SubTitle aSubtitle = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				aSubtitle = new SubTitle();
				aSubtitle.setSubId(Utils.parseInt((String) vTmp.get(i)));
				aSubtitle.setUrl((String) vTmp.get(i + 1));
				aSubtitle.setLangId(Utils.parseInt((String) vTmp.get(i + 2)));
				ret.add(aSubtitle);
			}
		}
		return ret;
	}

	public static Vector<Subject> LoadSubject(Vector<String> vTmp) {
		Vector<Subject> ret = new Vector<Subject>();
		Subject aSubject = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				aSubject = new Subject();
				aSubject.setId(Utils.parseInt((String) vTmp.get(i)));
				String name = (String) vTmp.get(i + 1);
				aSubject.setName(name);
				aSubject.setUrlImage((String) vTmp.get(i + 2));
				aSubject.setParentId(Utils.parseInt((String) vTmp.get(i + 3)));
				ret.add(aSubject);
			}
		}
		return ret;
	}

	public static Vector<Subject> LoadSubjectMod(Vector<String> vTmp) {
		Vector<Subject> ret = new Vector<Subject>();
		Subject aSubject = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 5) {
				aSubject = new Subject();
				aSubject.setId(Utils.parseInt((String) vTmp.get(i)));
				String name = (String) vTmp.get(i + 1);
				aSubject.setName(name);
				aSubject.setUrlImage((String) vTmp.get(i + 2));
				aSubject.setUrlBg((String) vTmp.get(i + 3));
				aSubject.setParentId(Utils.parseInt((String) vTmp.get(i + 4)));
				ret.add(aSubject);
			}
		}
		return ret;
	}

	public static Vector<Mod> LoadMod(Vector<String> vTmp) {
		Vector<Mod> ret = new Vector<Mod>();
		if (vTmp == null) {
			System.out.println("vTmp is null");
		}

		Mod aMod = null;
		if (vTmp != null && vTmp.size() > 0) {
			System.out.println("vTmp is not null");
			for (int i = 0; i < vTmp.size(); i = i + 9) {
				aMod = new Mod();
				aMod.setId(Utils.parseInt((String) vTmp.get(i)));

				String title = (String) vTmp.get(i + 1);
				aMod.setTitle(title);

				aMod.setDuration((String) vTmp.get(i + 2));

				aMod.setSinger((String) vTmp.get(i + 3));
				aMod.setComposer(decodeHtml((String) vTmp.get(i + 4)));
				aMod.setLyric((String) vTmp.get(i + 5));
				aMod.setUrl((String) vTmp.get(i + 6));
				aMod.setFileName((String) vTmp.get(i + 6));// =url
				aMod.setAlbum((String) vTmp.get(i + 7));
				aMod.setLength(Utils.parseInt((String) vTmp.get(i + 8)));
				ret.add(aMod);
			}
		}
		return ret;
	}

	public static Vector<Group> LoadMenuGroup(Vector<String> vTmp) {
		Vector<Group> ret = new Vector<Group>();
		Group aGroup = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				aGroup = new Group();
				aGroup.setGroupId(Utils.parseInt((String) vTmp.get(i)));
				aGroup.setGroupName((String) vTmp.get(i + 1));
				aGroup.setParentId(Utils.parseInt((String) vTmp.get(i + 2)));
				aGroup.setRoleValue((String) vTmp.get(i + 3));
				ret.add(aGroup);
			}
		}
		return ret;
	}

	public static Vector<Menu> LoadMenu(Vector<String> vTmp) {
		Vector<Menu> ret = new Vector<Menu>();
		Menu aMenu = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 6) {
				aMenu = new Menu();
				aMenu.setMenuId(Utils.parseInt((String) vTmp.get(i)));
				aMenu.setMenuName((String) vTmp.get(i + 1));
				aMenu.setHref((String) vTmp.get(i + 2));
				aMenu.setRole((String) vTmp.get(i + 3));
				aMenu.setParentId(Utils.parseInt((String) vTmp.get(i + 4)));
				aMenu.setGroupId(Utils.parseInt((String) vTmp.get(i + 5)));
				ret.add(aMenu);
			}
		}
		return ret;
	}

	public static Vector<LiveTVServer> LoadOVSServer(Vector<String> vTmp) {
		Vector<LiveTVServer> ret = new Vector<LiveTVServer>();
		LiveTVServer aSubject = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 2) {
				aSubject = new LiveTVServer();
				aSubject.setOvsId(Utils.parseInt((String) vTmp.get(i)));
				aSubject.setServerName((String) vTmp.get(i + 1));
				ret.add(aSubject);
			}
		}
		return ret;
	}

	public static Vector<LiveTV> LoadLiveTV(Vector<String> vTmp) {
		Vector<LiveTV> ret = new Vector<LiveTV>();
		LiveTV aLV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 8) {
				aLV = new LiveTV();
				aLV.setChannelid(Utils.parseInt((String) vTmp.get(i)));
				aLV.setServerid(Utils.parseInt((String) vTmp.get(i + 1)));
				aLV.setChannelname((String) vTmp.get(i + 2));
				aLV.setChannelcode(Utils.parseInt((String) vTmp.get(i + 3)));
				aLV.setPhysical_address((String) vTmp.get(i + 4));
				aLV.setServicename((String) vTmp.get(i + 5));
				aLV.setActive(Utils.parseInt((String) vTmp.get(i + 6)));
				aLV.setCoreId(Utils.parseInt((String) vTmp.get(i + 7)));
				ret.add(aLV);
			}
		}
		return ret;
	}

	public static Vector<EVSStorage> LoadEVSStorage(Vector<String> vTmp) {
		Vector<EVSStorage> ret = new Vector<EVSStorage>();
		EVSStorage aLV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				aLV = new EVSStorage();
				aLV.setId(Utils.parseInt((String) vTmp.get(i)));
				aLV.setHost((String) vTmp.get(i + 1));
				aLV.setPath((String) vTmp.get(i + 2));
				aLV.setType((String) vTmp.get(i + 3));
				ret.add(aLV);
			}
		}
		return ret;
	}

	public static Vector<EServer> LoadEServer(Vector<String> vTmp) {
		Vector<EServer> ret = new Vector<EServer>();
		EServer aLV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 3) {
				aLV = new EServer();
				aLV.setIp((String) vTmp.get(i));
				aLV.setSrvmonitor_port((String) vTmp.get(i + 1));
				aLV.setSrvmonitor_name((String) vTmp.get(i + 2));
				ret.add(aLV);
			}
		}
		return ret;
	}

	public static int[] LoadIDSubtitle(Vector<String> vTmp) {
		int[] id = new int[0];
		if (vTmp != null && vTmp.size() > 0) {
			int index = 0;
			id = new int[vTmp.size()];
			for (int i = 0; i < vTmp.size(); i = i + 1) {
				id[index] = Utils.parseInt((String) vTmp.get(i));
				index++;
			}
		}
		return id;
	}

	// LIVETV
	public static Vector<LiveTV> LoadChannels(Vector<String> vTmp) {
		Vector<LiveTV> ret = new Vector<LiveTV>();
		LiveTV liveTV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 9) {
				liveTV = new LiveTV();
				liveTV.setChannelid(Utils.parseInt((String) vTmp.get(i)));
				liveTV.setServerid(Utils.parseInt((String) vTmp.get(i + 1)));
				liveTV.setChannelname((String) vTmp.get(i + 2));
				liveTV.setChannelcode(Utils.parseInt((String) vTmp.get(i + 3)));
				liveTV.setPhysical_address((String) vTmp.get(i + 4));
				liveTV.setServicename((String) vTmp.get(i + 5));
				liveTV.setActive(Utils.parseInt((String) vTmp.get(i + 6)));
				liveTV.setCoreId(Utils.parseInt((String) vTmp.get(i + 7)));
				liveTV.setUrlImage((String) vTmp.get(i + 8));
				ret.add(liveTV);
			}
		}
		return ret;
	}

	// REPORT
	public static Vector<DataReport> LoadDataReport(Vector<String> vTmp) {
		Vector<DataReport> ret = new Vector<DataReport>();
		DataReport aLV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 4) {
				aLV = new DataReport();
				aLV.setName((String) vTmp.get(i));
				aLV.setQuanlity(Utils.parseInt((String) vTmp.get(i + 1)));
				aLV.setPrice((String) vTmp.get(i + 2));
				aLV.setAmount((String) vTmp.get(i + 3));
				ret.add(aLV);
			}
		}
		return ret;
	}

	public static Vector<DataReport> LoadReport(Vector<String> vTmp) {
		Vector<DataReport> ret = new Vector<DataReport>();
		DataReport aLV = null;
		if (vTmp != null && vTmp.size() > 0) {
			for (int i = 0; i < vTmp.size(); i = i + 2) {
				aLV = new DataReport();
				aLV.setName((String) vTmp.get(i));
				aLV.setQuanlity(Utils.parseInt((String) vTmp.get(i + 1)));
				ret.add(aLV);
			}
		}
		return ret;
	}

	private static String decodeHtml(String original) {
		return UnicodeConverter.decodeUnicode(original);
	}

}
