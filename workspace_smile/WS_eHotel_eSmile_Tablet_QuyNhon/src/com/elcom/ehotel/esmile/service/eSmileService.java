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

	String postRegister(String object);

	String getLocations(String object);

	String getInfos(String object);

	String postLogin(String object);

	String postLogout(String object);

	String checkLogin(String object);

	String postSmile(String object);

	String postRating(String object);

	String postRatingChild(String object);

	String getEmployee(String object);

	String postReRegister(String object);

	String getMobileNotify(String object);

	String getMobileNotifyAll(String object);

	String confirmMobileNotify(String object);

	String deleteMobileNotify(String object);

}
