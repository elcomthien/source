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

	@WebMethod
	String getLocation(String object);

	String getLanguage(String object);

	String getInfo(String object);

	String getSubjRating(String object);

	String register(String object);

	String setRating(String object);

	String setComment(String object);

	String login(String object);

	String getPmsSubject(String object);

	String getPmsSubjectDetail(String object);

	String setSmile(String object);

	String getSubjectInfoHotel(String object);

	String getItemBySubjectID(String object);

	String getSurvey(String object);

	String postSurvey(String object);

	String getSubjectDining(String object);

	String getItemDiningBySubjectID(String object);

	String getListFoodByItem(String object);

	String getShipSchedule(String object);

	String editSmile(String object);

	String editRating(String object);

	String checkNotifyRating(String object);

	String getNotify(String object);

	String confirmRating(String object);

	String deleteNotify(String object);

	String setRatingGuest(String object);

	String setCommentGuest(String object);

	String getMenu(String object);

	String getExchange(String object);
	
	String getFaculty(String object);

	String checkKey(String object);
	
	String getKey(String object);
	
	String setLang(String object);
	
	String checkKeyMobile(String object);
	
	String postImage(String object);
}
