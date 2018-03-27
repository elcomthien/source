package com.elcom.eodapp.ehotel.core;


import org.apache.log4j.Logger;

import com.elcom.ehotel.smile.CommandVariables.CMD_GUESTBILL;
import com.elcom.ehotel.smile.FieldVariables.FID;
import com.elcom.eodapp.ehotel.common.xsd.OBill;
import com.elcom.eodapp.ehotel.common.xsd.OPost;
import com.elcom.eodapp.ehotel.utils.CommandProfile;


public class CommandBuilder {
	static Logger log = Logger.getLogger(CommandBuilder.class);
	
	public String GetBillCommand(OBill bill) {		
		String roomNum = bill.getROOMNUMER().getValue();
		String guestNum = bill.getRESERVATIONNUMER().getValue(); //Use Reservation Number for Guest Number
		CommandProfile profile = new CommandProfile();
		profile.setCommand(CMD_GUESTBILL.GUEST_BILL_REQ.value);
		profile.addFieldValue(FID.ROOM_NUMBER, roomNum);		
		profile.addFieldValue(FID.GUEST_NUMBER, guestNum);				
		return profile.toCommandString();
	}
	
	public String GetPostCommand(OPost post) {
		CommandProfile profile = new CommandProfile();
		/*profile.setCommand(CMD_POSTING.POSTING_SIMPLE.value);
		profile.addFieldValue(FID.ROOM_NUMBER, post.getROOM_NUMBER());
		profile.addFieldValue(FID.DATE, post.getDATE());
		profile.addFieldValue(FID.TIME, post.getTIME());
		profile.addFieldValue(FID.TOTAL_POST_AMOUNT, post.getTOTAL_AMOUNT());
		profile.addFieldValue(FID.CURRENCY, post.getCURRENCY_TYPE());
		profile.addFieldValue(FID.POST_ID, post.getPOST_SEQ_NUMBER());	
		profile.addFieldValue(FID.POST_TEXT, post.getPOST_TEXT());
		*/
		return profile.toCommandString();
	}
	
	
}
