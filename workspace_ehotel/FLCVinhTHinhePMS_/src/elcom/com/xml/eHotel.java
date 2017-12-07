package elcom.com.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

@XmlRootElement(name = "ehotel")
public class eHotel {

	private String version = "1.0";

	private String code = "A0002";

	private String name = "ELCOM-HCM";

	private int cache = 1;

	private List<ehotelMenu> tag_menus = new ArrayList<ehotelMenu>();
	private List<ehotelItem> tag_items = new ArrayList<ehotelItem>();
	private List<Guest> tag_guests = new ArrayList<Guest>();
	private List<Promotion> tag_promotions = new ArrayList<Promotion>();
	private List<ServiceCharge> tag_charges = new ArrayList<ServiceCharge>();
	private List<eMessage> tag_messages = new ArrayList<eMessage>();
	private List<ServiceCommon> tag_service = new ArrayList<ServiceCommon>();
	private List<OutletImage> tag_images = new ArrayList<OutletImage>();
	private List<ServiceItem> tag_serviceitems = new ArrayList<ServiceItem>();
	private List<eCountry> tag_countries = new ArrayList<eCountry>();
	private List<eObject> tag_objects = new ArrayList<eObject>();
	private List<eWeather> tag_weathers = new ArrayList<eWeather>();
	private List<eWakeup> tag_wakeups = new ArrayList<eWakeup>();
	private List<eWakeupForms> tag_wakeupform = new ArrayList<eWakeupForms>();
	private List<eCurrency> tag_currencies = new ArrayList<eCurrency>();
	private List<eAttraction> tag_attractions = new ArrayList<eAttraction>();
	private List<eHouseKeeping> tag_hk = new ArrayList<eHouseKeeping>();
	private List<eAcitivity> tag_activities = new ArrayList<eAcitivity>();
	private List<eMapLocation> tag_locations = new ArrayList<eMapLocation>();
	private List<eContainer> tag_games = new ArrayList<eContainer>();
	private List<VersionData> tag_versions = new ArrayList<VersionData>();

	public eHotel() {

	}

	public eHotel(String version, String code, int cache) {
		this.version = version;
		this.code = code;
	}

	@XmlAttribute
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlAttribute
	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "item")
	public List<ehotelMenu> getTag_menus() {
		return tag_menus;
	}

	public void setTag_menus(List<ehotelMenu> tag_menus) {
		this.tag_menus = tag_menus;
	}

	@XmlElement(name = "item")
	public List<ehotelItem> getTag_items() {
		return tag_items;
	}

	public void setTag_items(List<ehotelItem> tag_items) {
		this.tag_items = tag_items;
	}

	@XmlElement(name = "item")
	public List<Guest> getTag_guests() {
		return tag_guests;
	}

	public void setTag_guests(List<Guest> tag_guests) {
		this.tag_guests = tag_guests;
	}

	@XmlElement(name = "item")
	public List<Promotion> getTag_promotions() {
		return tag_promotions;
	}

	public void setTag_promotions(List<Promotion> tag_promotions) {
		this.tag_promotions = tag_promotions;
	}

	@XmlElement(name = "item")
	public List<ServiceCharge> getTag_charges() {
		return tag_charges;
	}

	public void setTag_charges(List<ServiceCharge> tag_charges) {
		this.tag_charges = tag_charges;
	}

	@XmlElement(name = "item")
	public List<eMessage> getTag_messages() {
		return tag_messages;
	}

	public void setTag_messages(List<eMessage> tag_messages) {
		this.tag_messages = tag_messages;
	}

	@XmlElement(name = "item")
	public List<ServiceCommon> getTag_service() {
		return tag_service;
	}

	public void setTag_service(List<ServiceCommon> tag_service) {
		this.tag_service = tag_service;
	}

	@XmlElement(name = "item")
	public List<OutletImage> getTag_images() {
		return tag_images;
	}

	public void setTag_images(List<OutletImage> tag_images) {
		this.tag_images = tag_images;
	}

	@XmlElement(name = "item")
	public List<ServiceItem> getTag_serviceitems() {
		return tag_serviceitems;
	}

	public void setTag_serviceitems(List<ServiceItem> tag_serviceitems) {
		this.tag_serviceitems = tag_serviceitems;
	}

	@XmlElement(name = "item")
	public List<eCountry> getTag_countries() {
		return tag_countries;
	}

	public void setTag_countries(List<eCountry> tag_countries) {
		this.tag_countries = tag_countries;
	}

	@XmlElement(name = "item")
	public List<eObject> getTag_objects() {
		return tag_objects;
	}

	public void setTag_objects(List<eObject> tag_objects) {
		this.tag_objects = tag_objects;
	}

	@XmlElement(name = "item")
	public List<eWeather> getTag_weathers() {
		return tag_weathers;
	}

	public void setTag_weathers(List<eWeather> tag_weathers) {
		this.tag_weathers = tag_weathers;
	}

	@XmlElement(name = "item")
	public List<eWakeup> getTag_wakeups() {
		return tag_wakeups;
	}

	public void setTag_wakeups(List<eWakeup> tag_wakeups) {
		this.tag_wakeups = tag_wakeups;
	}

	@XmlElement(name = "item")
	public List<eWakeupForms> getTag_wakeupform() {
		return tag_wakeupform;
	}

	public void setTag_wakeupform(List<eWakeupForms> tag_wakeupform) {
		this.tag_wakeupform = tag_wakeupform;
	}

	@XmlElement(name = "item")
	public List<eCurrency> getTag_currencies() {
		return tag_currencies;
	}

	public void setTag_currencies(List<eCurrency> tag_currencies) {
		this.tag_currencies = tag_currencies;
	}

	@XmlElement(name = "item")
	public List<eAttraction> getTag_attractions() {
		return tag_attractions;
	}

	public void setTag_attractions(List<eAttraction> tag_attractions) {
		this.tag_attractions = tag_attractions;
	}

	@XmlElement(name = "item")
	public List<eHouseKeeping> getTag_hk() {
		return tag_hk;
	}

	public void setTag_hk(List<eHouseKeeping> tag_hk) {
		this.tag_hk = tag_hk;
	}

	@XmlElement(name = "item")
	public List<eAcitivity> getTag_activities() {
		return tag_activities;
	}

	public void setTag_activities(List<eAcitivity> tag_activities) {
		this.tag_activities = tag_activities;
	}

	@XmlElement(name = "item")
	public List<eMapLocation> getTag_locations() {
		return tag_locations;
	}

	public void setTag_locations(List<eMapLocation> tag_locations) {
		this.tag_locations = tag_locations;
	}

	@XmlElement(name = "item")
	public List<eContainer> getTag_games() {
		return tag_games;
	}

	public void setTag_games(List<eContainer> tag_games) {
		this.tag_games = tag_games;
	}

	@XmlElement(name = "item")
	public List<VersionData> getTag_versions() {
		return tag_versions;
	}

	public void setTag_versions(List<VersionData> tag_versions) {
		this.tag_versions = tag_versions;
	}

}
