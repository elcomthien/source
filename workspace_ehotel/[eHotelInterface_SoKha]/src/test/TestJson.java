package test;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestJson {
	public static void main(String[] args) {
		/*JSONObject obj = new JSONObject();		
		obj.put("Message-DateTime", "2010-06-02T11:59:11");
		obj.put("CallBack-Flag", true);
		List l = new ArrayList<JSONObject>(); 
		l.add(obj);
		
		JSONObject parentObj = new JSONObject();
		parentObj.put("Guest-Msgs", l);		
		String s = parentObj.toJSONString();
		System.out.println(s);
		*/
		String message = "{'0' : {'roomno' : '1020','guestno' : '13155','message_id' : '630','message_text' : 'Please call back Mr James Bond to arrange for meeting. Thank you.','last_update' : ''}, " 
						+ "'1' : {'roomno' : '1020','guestno' : '13155','message_id' : '631','message_text' : 'Please call back Mr James Bond to arrange for meeting. Thank you.','last_update' : ''}, "
						+ "'2' : {'roomno' : '1020','guestno' : '13155','message_id' : '632','message_text' : 'Please call back Mr James Bond to arrange for meeting. Thank you.','last_update' : ''}}";
		message = message.replace("'", "\"");
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message);
			Set<String> ks = jsonObject.keySet();
			for(String k : ks) {				
				System.out.println((JSONObject)jsonObject.get(k));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
