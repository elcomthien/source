package com.elcom.eodapp.ehotel.service.xpo;

public interface XposInterface {	
	public Integer guestMessage(String GUEST_ROOM, String GUEST_RESERVATION);
	
	public Integer guestPost(String GUEST_ROOM, String GUEST_RESERVATION, String POST_AMOUNT, String CHARGE_CODE);
	
	public Integer dbSwap();
}
