package com.elcom.ehotel.esmile.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.BindingType;

@WebService
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@SOAPBinding(style = Style.RPC)
public interface eSmileService {

	// param: {"username":"admin","password":"123"}
	@WebMethod
	String getLogin(String object);

	@WebMethod
	String getLocation(String object);

	@WebMethod
	String getBackground(String object);

	// param: {"value":"1491894178543.jpg"}
	@WebMethod
	String addBackground(String object);

	// param: {"id":"123","value":"1491894178543.jpg"}
	@WebMethod
	String editBackground(String object);

	// param: {"id":"123"}
	@WebMethod
	String deleteBackground(String object);

	// param: {"from":"01-05-2017","to":"03-05-2017","location":"1,2,3"}
	@WebMethod
	String getStatistic(String object);

	// param: {"from":"01-05-2017","to":"03-05-2017","location":"1,2,3","smileid":"1","langid":"2"}
	@WebMethod
	String getRating(String object);

	// param: {"from":"01-05-2017","to":"03-05-2017","location":"1,2,3","langid":"2"}
	@WebMethod
	String getSmile(String object);

	@WebMethod
	String getPromotion(String object);

	// param: {"value":"http://172.16.9.100:8383/html/promotion.html"}
	@WebMethod
	String addPromotion(String object);

	// param: {"id":"123","value":"http://172.16.9.100:8383/html/promotion.html"}
	@WebMethod
	String editPromotion(String object);

	// param: {"id":"123"}
	@WebMethod
	String deletePromotion(String object);

	// param: {"from":"01-05-2017","to":"03-05-2017","location":"1,2,3","langid":"2"}
	@WebMethod
	String getAllRating(String object);

	// param: {"from":"01-05-2017","to":"03-05-2017","location":"1,2,3","langid":"2"}
	@WebMethod
	String getRatingDivision(String object);

	@WebMethod
	String getLanguage(String object);

	// param: {"langid":"2"}
	@WebMethod
	String getSubjectInfo(String object);

	// param: {"name":"abc","image":"45678904567890.png"}
	@WebMethod
	String addSubjectInfo(String object);

	// param: {"id":"1","name":"abc","image":"45678904567890.png","invisible":"0","langid":"1"}
	@WebMethod
	String editSubjectInfo(String object);

	// param: {"id":"1"}
	@WebMethod
	String deleteSubjectInfo(String object);

	// param: {"id":"1","langid":"2"}
	@WebMethod
	String getContentInfo(String object);

	// param: {"name":"abc","image":"45678904567890.png"}
	@WebMethod
	String addContentInfo(String object);

	// param: {"id":"1","name":"abc","image":"45678904567890.png","invisible":"0","langid":"1"}
	@WebMethod
	String editContentInfo(String object);

	// param: {"id":"1"}
	@WebMethod
	String deleteContentInfo(String object);

	@WebMethod
	String getSubjectDining(String object);

	@WebMethod
	String addSubjectDining(String object);

	@WebMethod
	String editSubjectDining(String object);

	@WebMethod
	String deleteSubjectDining(String object);

	@WebMethod
	String getItemDining(String object);

	@WebMethod
	String addItemDining(String object);

	@WebMethod
	String editItemDining(String object);

	@WebMethod
	String deleteItemDining(String object);

	@WebMethod
	String getSpeedBoatSchedule(String object);

	@WebMethod
	String addSpeedBoat(String object);

	@WebMethod
	String editSpeedBoat(String object);

	@WebMethod
	String deleteSpeedBoat(String object);

	@WebMethod
	String addBoatTime(String object);

	@WebMethod
	String editBoatTime(String object);

	@WebMethod
	String deleteBoatTime(String object);

	@WebMethod
	String getSurvey(String object);

	@WebMethod
	String addSurvey(String object);

	@WebMethod
	String editSurvey(String object);

	@WebMethod
	String deleteSurvey(String object);

	@WebMethod
	String getRoom(String object);
	
	@WebMethod
	String editSmile(String object);
	
	@WebMethod
	String getService(String object);

	@WebMethod
	String addService(String object);

	@WebMethod
	String editService(String object);

	@WebMethod
	String deleteService(String object);
	
	@WebMethod
	String editRating(String object);
	
	@WebMethod
	String getVoteSurvey(String object);
	
	@WebMethod
	String getListWelcome(String object);
	
	@WebMethod
	String editWelcome(String object);
}
