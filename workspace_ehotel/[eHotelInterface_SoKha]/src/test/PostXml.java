package test;
import java.net.*;
import java.io.*;
public class PostXml {
	public static void main(String[] args) {		
	    try {
	     /*String xmldata = "<?xml version='1.0' encoding='UTF-8' ?>" + 	    	
	    		  			"<S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'>" + 
	    		  			"<S:Header/> " + 
 							"<S:Body> " + 
 							"<bill soapenv:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'> "+
 							"<sRoomNo xsi:type='xsd:string'>2000</sRoomNo>" +
 							"<sGuestNo xsi:type='xsd:string'>123456</sGuestNo>" +
 							"</bill> "+
 							"</S:Body> " +
 							"</S:Envelope> ";
 			*/
	    	/*String xmldata = "<?xml version='1.0' encoding='UTF-8' ?>" + 
		  			//"<S:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/'>"+ 
		  			"<S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'>" + 
		  			"<S:Header/> " + 
						"<S:Body> " + 
						"<ns2:guestMessageWaiting xmlns:ns2='http://json.service.ehotel.eodapp.elcom.com/'> " +
						//"<arg0 xsi:type='xsd:string'>111</arg0> " + 
						"<MessageBody>{'Guest-Reservation': '123456','Guest-Room': '2000'}</MessageBody>" + 
						"</ns2:guestMessageWaiting> "+
						"</S:Body> " +
						"</S:Envelope> ";
	    	*/
	    	String xmldata = "<?xml version='1.0' ?>" + 
					 "<S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'>" + 
			         "<S:Body> " + 
					 "<ns2:checkIn xmlns:ns2='http://json.service.ehotel.eodapp.elcom.com/'>" + 
					 "<MessageBody>{'Guest-ArrivalDate': '2011-12-13','Guest-DepartureDate': '2018-12-13','Guest-FirstName': 'Esperanza','Guest-LastName': 'Dionisio','Guest-Name': 'Esperanza Dionisio','Guest-Reservation': '1924459','Guest-Room': '2200','Guest-Language': 'en_US','Guest-Group': '718787','Guest-Title': 'Ms','Guest-VIPStatus': '','Guest-TVRights': '','Room-ShareFlag': '0'}</MessageBody>" +
					 "</ns2:checkIn>" + 
			         "</S:Body>" + 
					 "</S:Envelope>";
	    	
	    	//String xmldata = "<?xml version='1.0' ?><S:Envelope xmlns:S='http://schemas.xmlsoap.org/soap/envelope/'><S:Body><ns2:getHelloWorldAsString xmlns:ns2='http://ws.mkyong.com/'><arg0>mkyong</arg0></ns2:getHelloWorldAsString></S:Body></S:Envelope>";
				
	      //Create socket for local
	      String hostname = "192.168.99.2";
	      int port = 9999;
	      String path = "/ws/CoreSoKha"; 
	      
	    //Create socket for sokha
	      /*String hostname = "203.155.100.231";
	      int port = 80;
	      String path = "/ifc/WebServiceServer.php";
	      */
	      InetAddress addr = InetAddress.getByName(hostname);
	      Socket sock = new Socket(addr, port);				
	      //Send header
	      
	      BufferedWriter  wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(),"UTF-8"));
	      // You can use "UTF8" for compatibility with the Microsoft virtual machine.
	      wr.write("POST " + path + " HTTP/1.0\r\n");
	      wr.write("Host: localhost\r\n");
	      wr.write("Content-Length: " + xmldata.length() + "\r\n");
	      wr.write("Content-Type: text/xml; charset=\"utf-8\"\r\n");
	      wr.write("\r\n");
				
	      //Send data
	      wr.write(xmldata);
	      wr.flush();
				
	      // Response
	      BufferedReader rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	      String line;
	      while((line = rd.readLine()) != null)	    	  
	    	  System.out.println(line);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
