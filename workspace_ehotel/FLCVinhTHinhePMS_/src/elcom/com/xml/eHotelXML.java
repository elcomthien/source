package elcom.com.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import elcom.config.PMSConstant;
import elcom.domain.Guest;
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
import elcom.domain.eWakeup;
import elcom.domain.eWakeupForms;
import elcom.domain.eWeather;
import elcom.domain.ex.eAcitivity;
import elcom.domain.ex.eAttraction;
import elcom.domain.ex.eHouseKeeping;
import elcom.domain.ex.eObject;

public class eHotelXML extends eBaseXML {
	private static Logger log = Logger.getLogger(eHotelXML.class);

	public eHotelXML(String fileName) {
		super(fileName);
	}

	@Override
	public void export_xml() {

	}

	public void exportGuest(List<Guest> Vguest, String smartcard) {
		List<Guest> vItems = new ArrayList<Guest>();
		for (Guest g : Vguest) {
			vItems.add(g);
		}
		if (vItems.size() > 0) {
			eHotel hotel = new eHotel();
			hotel.setTag_guests(vItems);
			this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
			this.createFolder(this.xmlFile);
			createNew(hotel);
		}
	}

	public void exportPromotions(List<Promotion> vPromotions, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_promotions(vPromotions);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportMessages(List<eMessage> vmessages, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_messages(vmessages);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportOutletImage(List<OutletImage> vImages, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_images(vImages);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportBillCharges(List<ServiceCharge> vCharges, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_charges(vCharges);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportServices(List<ServiceCommon> vServices, String smartcard) {
		eHotel hotel = new eHotel();
		try {
			hotel.setTag_service(vServices);
			this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
			this.createFolder(this.xmlFile);
			createNew(hotel);
		} catch (Exception ex) {
			createNew(hotel);
		}

	}

	public void exportServiceItem(List<ServiceItem> vItems, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_serviceitems(vItems);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportCountries(List<eCountry> vCountries, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_countries(vCountries);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportWebs(List<eObject> vWebs, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_objects(vWebs);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportWeather(List<eWeather> vWeathers, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_weathers(vWeathers);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportWakeup(List<eWakeup> vWakeup, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_wakeups(vWakeup);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportWakeupForm(List<eWakeupForms> vforms, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_wakeupform(vforms);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportAttraction(List<eAttraction> vattration, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_attractions(vattration);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportHouseKeeping(List<eHouseKeeping> vhk, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_hk(vhk);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportActivities(List<eAcitivity> vActivities, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_activities(vActivities);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportLocations(List<eMapLocation> vlocations, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_locations(vlocations);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportExchangeRates(List<eCurrency> vCurrencies,
			String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_currencies(vCurrencies);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportGames(List<eContainer> games, String smartcard) {
		eHotel hotel = new eHotel();
		hotel.setTag_games(games);
		this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
		this.createFolder(this.xmlFile);
		createNew(hotel);
	}

	public void exportVersionServer(List<VersionData> vVersions,
			String smartcard) {
		eHotel hotel = new eHotel();
		try {
			hotel.setTag_versions(vVersions);
			this.xmlFile = PMSConstant.PATH_SERVER + this.xmlFile;
			log.info("HOAVK@-ver=" + this.xmlFile);
			this.createFolder(this.xmlFile);
			createNew(hotel);
		} catch (Exception ex) {
			createNew(hotel);
		}
	}
}
