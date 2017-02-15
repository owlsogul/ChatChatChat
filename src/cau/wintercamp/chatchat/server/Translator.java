package cau.wintercamp.chatchat.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Translator {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;
	
	public Data JSONstrToObj(String strData) {
		
		JSONParser parser = new JSONParser();
		int type = 0;
		
		Data data = new Data();
		
		try {
			// JSON 데이터를 넣어 JSON Object로 만들어줌
			JSONObject objData = (JSONObject) parser.parse(strData);
			
			// type정보 추출
			data.dataType = (int) objData.get("type");
			
			switch (type) {
			case DATATYPE_CHAT:
				String roomId = (String) objData.get("room_id");
				String message = (String) objData.get("message");
				String userId = (String) objData.get("user_id");
				data.dataChat(roomId, message, userId);
				break;

			case DATATYPE_REGISTER:
				String id = (String) objData.get("user_id");
				String pw = (String) objData.get("user_pw");
				boolean same = (boolean) objData.get("bool");
				data = new Data();
				data.dataRegister(id, pw, same);
				break;
				
			case DATATYPE_LOGIN:
				id = (String) objData.get("user_id");
				pw = (String) objData.get("user_pw");
				same = (boolean) objData.get("bool");
				data = new Data();
				data.dataLogin(id, pw, same);
				break;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public void JSONbojToStr() {
		
	}
	
	//	public String DataToJSON()
}
