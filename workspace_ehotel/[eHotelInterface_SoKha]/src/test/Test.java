package test;

import java.util.Date;
import java.util.List;

import com.elcom.ehotel.variables.DataHelper;
import com.elcom.eodapp.ehotel.common.xsd.OBill;
import com.elcom.eodapp.ehotel.common.xsd.OPost;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterface;
import com.elcom.eodapp.ehotel.sokha.CoreSoKhaInterfacePortType;


public class Test {
	public static void main(String[] args) {
		/*String ITEM_DATE = "0000-00-00";
		String ITEM_TIME = "16:05:57";
		String strDateTime = ITEM_DATE + " " + ITEM_TIME;
		Date rsDate = DataHelper.parseDate(strDateTime, "yyyy-MM-dd HH:mm:ss");
		if(rsDate == null) {
			rsDate = new Date();
		}
		ITEM_DATE = DataHelper.format(rsDate, "yyyy-MM-dd");
		ITEM_TIME = DataHelper.format(rsDate, "HH:mm:ss");
		return;*/
		
		CoreSoKhaInterface core = new CoreSoKhaInterface();
		CoreSoKhaInterfacePortType port = core.getCoreSoKhaInterfaceHttpSoap11Endpoint();
		List<OPost> listBill = port.guestPostReq("");
		
		for(OPost bill : listBill) {
			System.out.println(bill.getROOMNUMBER().getValue() + "-" + bill.getDURATION().getValue());
		}
		
		//XL | 2000 | 123456 | 791 | Please call back Mr James Bond to arrange for meeting. Thank you. | NOTHING | NOTHING | WF: NOTHING
		boolean mess=  port.guestMessageTextOnline("2000", "123456", "791", "Please call back Mr James Bond to arrange for meeting. Thank you.", "");
		System.out.println(mess);
		boolean r=  port.guestBillItem("2000", "123456", "Room Charge (ROM)", "1.00", "1", "2015-07-05", "11:18:00");
		System.out.println(r);
	}
}
