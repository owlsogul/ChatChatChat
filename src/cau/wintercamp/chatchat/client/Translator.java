package cau.wintercamp.chatchat.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Translator {

	public Data tranlateJSONToData(String json_str){

		JSONParser parser = new JSONParser();

		try {

			JSONObject jObject = (JSONObject) parser.parse(json_str);

			int type = (int) jObject.get("type");
			switch (type){
			
			default: return null;
			case Data.DATATYPE_REGISTER:
				String id = (String) jObject.get("id");
				String pw = (String) jObject.get("pw");
				boolean same = (int) jObject.get("bool") == 1 ? true : false;
				DataRegister dataRegister = new DataRegister(id, pw, same);
				return dataRegister;
				
			case Data.DATATYPE_LOGIN:
				id = (String) jObject.get("id");
				pw = (String) jObject.get("pw");
				same = (int) jObject.get("bool") == 1 ? true : false;
				DataLogin dataLogin = new DataLogin(id, pw, same);
				return dataLogin;
				
			case Data.DATATYPE_CHAT:
				String roomId = (String) jObject.get("room_id");
				String message = (String) jObject.get("message");
				DataChat dataChat = new DataChat(roomId, message);
				return dataChat;
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String translateDataToJSONString(Data data){
		JSONObject jObject = new JSONObject();
		
		int type = (int) jObject.get("type");
		switch (type){
		default: return null;
		case Data.DATATYPE_REGISTER:
			jObject.put("", value)
		}
		
		return jObject.toJSONString();
	}



}
