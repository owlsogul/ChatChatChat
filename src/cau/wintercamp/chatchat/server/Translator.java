package cau.wintercamp.chatchat.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import cau.wintercamp.chatchat.client.Data;
import cau.wintercamp.chatchat.client.DataChat;
import cau.wintercamp.chatchat.client.DataLogin;
import cau.wintercamp.chatchat.client.DataRegister;

public class Translator {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;
	
	public void JSONstrToObj(String strData) {
		
		JSONParser parser = new JSONParser();
		
		try {
			// JSON 데이터를 넣어 JSON Object로 만들어줌
			JSONObject objData = (JSONObject) parser.parse(strData);
			
			// type정보 추출
			int type = (int) objData.get("type");
			
			switch (type) {
			case DATATYPE_CHAT:
				String roomId = (String) objData.get("room_id");
				String message = (String) objData.get("message");
				String userId = (String) objData.get("user_id");
				DataChat dataChat = new DataChat(roomId, message, userId);
				break;

			case Data.DATATYPE_REGISTER:
				String id = (String) objData.get("user_id");
				String pw = (String) objData.get("user_pw");
				boolean same = (int) objData.get("bool") == 1 ? true : false;
				Account ac = new Account();
				ac.register(id, pw, same);
				break;
				
			case Data.DATATYPE_LOGIN:
				id = (String) objData.get("user_id");
				pw = (String) objData.get("user_pw");
				same = (int) objData.get("bool") == 1 ? true : false;
				ac = new Account();
				ac.login(id, pw, same);
				break;
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void JSONbojToStr() {
		
	}
	
	//	public String DataToJSON()
}
