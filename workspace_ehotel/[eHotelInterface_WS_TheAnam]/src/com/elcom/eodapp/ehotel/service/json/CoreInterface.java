package com.elcom.eodapp.ehotel.service.json;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam.Mode;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle=ParameterStyle.WRAPPED, use=Use.LITERAL)

public interface CoreInterface {
	
	@WebMethod(operationName="checkIn") 
	@WebResult(name="OperationResult", partName="checkInAck")
	public Integer GuestCheckin(@WebParam(name="MessageBody", mode=Mode.IN) String message);
	
	@WebMethod(operationName="checkOut") 
	@WebResult(name="OperationResult", partName="checkOutAck")
	public Integer GuestCheckout(@WebParam(name="MessageBody", mode=Mode.IN) String message);
	
	@WebMethod(operationName="guestMove") 
	@WebResult(name="OperationResult", partName="guestMoveAck")
	public Integer GuestRoomMove(@WebParam(name="MessageBody", mode=Mode.IN) String message);
	
	@WebMethod(operationName="guestMessageWaiting") 
	@WebResult(name="OperationResult", partName="guestMessageWaitingAck")
	public Integer GuestMessageWaiting(@WebParam(name="MessageBody", mode=Mode.IN) String message);
}
