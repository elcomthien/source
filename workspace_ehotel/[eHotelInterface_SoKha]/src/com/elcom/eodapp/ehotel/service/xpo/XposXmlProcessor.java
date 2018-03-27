package com.elcom.eodapp.ehotel.service.xpo;

import org.apache.log4j.Logger;

import com.elcom.eodapp.ehotel.processor.PostXML;
import com.elcom.eodapp.ehotel.processor.SOAPParser_SoKha;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.ACK_SOKHA;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.OPERATION_NAME;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_ACK;
import com.elcom.eodapp.ehotel.utils.ConstantVariable.XPOS_IN_PARAM;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;

public class XposXmlProcessor implements XposInterface {

	Logger logger = Logger.getLogger(XposXmlProcessor.class);

	PostXML postXML = DAOFactory.getInstance().getPostXML();
	SOAPParser_SoKha parser = DAOFactory.getInstance().getSoapParser_SK();

	@Override
	public Integer guestMessage(String GUEST_ROOM, String GUEST_RESERVATION) {
		postXML.AddParam(XPOS_IN_PARAM.GUEST_ROOM.value, "xsd:string", GUEST_ROOM);
		postXML.AddParam(XPOS_IN_PARAM.GUEST_RESERVATION.value, "xsd:string", GUEST_RESERVATION);
		String xmldata = postXML.CreateXML(OPERATION_NAME.GUEST_MESSAGE.value);
		String outData = postXML.PostData(xmldata);
		if (outData.length() == 0)
			return XPOS_ACK.FAIL.value;

		int index = outData.indexOf("SOAP-ENV");
		String soapObjectString = outData.substring(index - 1);
		Integer rs = parser.ParseSoapData_GuestMessage(soapObjectString);
		logger.info("guestMessage(RN:" + GUEST_ROOM + ", G#:" + GUEST_RESERVATION + ") finish");
		return rs;
	}

	@Override
	public Integer guestPost(String GUEST_ROOM, String GUEST_RESERVATION, String POST_AMOUNT, String CHARGE_CODE) {
		postXML.AddParam(XPOS_IN_PARAM.GUEST_ROOM.value, "xsd:string", GUEST_ROOM);
		postXML.AddParam(XPOS_IN_PARAM.GUEST_RESERVATION.value, "xsd:string", GUEST_RESERVATION);
		postXML.AddParam(XPOS_IN_PARAM.POST_AMOUNT.value, "xsd:double", POST_AMOUNT);
		postXML.AddParam(XPOS_IN_PARAM.CHARGE_CODE.value, "xsd:string", CHARGE_CODE);
		String xmldata = postXML.CreateXML(OPERATION_NAME.POST_CHARGE.value);
		String outData = postXML.PostData(xmldata);
		if (outData.length() == 0)
			return ACK_SOKHA.INVALID.value;

		int index = outData.indexOf("SOAP-ENV");
		String soapObjectString = outData.substring(index - 1);
		Integer rs = parser.ParseSOAPData_Post(soapObjectString);
		logger.info("guestBill(RN:" + GUEST_ROOM + ", G#:" + GUEST_RESERVATION + ", Amount:" + POST_AMOUNT + ") finish");
		return rs;
	}

	@Override
	public Integer dbSwap() {
		String xmldata = postXML.CreateXML(OPERATION_NAME.DB_SWAP.value);
		String outData = postXML.PostData(xmldata);
		if (outData.length() == 0)
			return ACK_SOKHA.INVALID.value;
		int index = outData.indexOf("SOAP-ENV");
		String soapObjectString = outData.substring(index - 1);
		Integer rs = parser.ParseSOAPData_DBSwap(soapObjectString);
		return null;
	}

}
