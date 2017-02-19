package cau.wintercamp.chatchat.client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Translator {

	public Data tranlateJSONToData(String json_str){

		JSONParser parser = new JSONParser();

		try {

			JSONObject jObject = (JSONObject) parser.parse(json_str);

			int type = ((Long) jObject.get("type")).intValue();
			switch (type){
			
			default: return null;
			case Data.DATATYPE_REGISTER:
				String id = (String) jObject.get("user_id");
				String pw = (String) jObject.get("user_pw");
				boolean same = ((Long) jObject.get("bool")).intValue() == 1 ? true : false;
				DataRegister dataRegister = new DataRegister(id, pw, same);
				return dataRegister;
				
			case Data.DATATYPE_LOGIN:
				id = (String) jObject.get("user_id");
				pw = (String) jObject.get("user_pw");
				same = ((Long) jObject.get("bool")).intValue() == 1 ? true : false;
				DataLogin dataLogin = new DataLogin(id, pw, same);
				return dataLogin;
				
			case Data.DATATYPE_CHAT:
				String roomId = (String) jObject.get("room_id");
				String message = (String) jObject.get("message");
				String userId = (String) jObject.get("user_id");
				DataChat dataChat = new DataChat(roomId, message, userId);
				return dataChat;
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String translateDataToJSONString(Data data){
		JSONObject jObject = new JSONObject();
		
		int type = data.getDataType();
		jObject.put("type", (int)type);
		switch (type){
		default: return null;
		
		case Data.DATATYPE_REGISTER:
			DataRegister dataRegister = (DataRegister) data;
			jObject.put("user_id", (String)dataRegister.getUserId());
			jObject.put("user_pw", (String)dataRegister.getUserPw());
			jObject.put("bool", dataRegister.getBool() ? 1 : 0);
			break;
			
		case Data.DATATYPE_LOGIN:
			DataLogin dataLogin = (DataLogin) data;
			jObject.put("user_id", dataLogin.getUserId());
			jObject.put("user_pw", dataLogin.getUserPw());
			jObject.put("bool", dataLogin.getBool() ? 1 : 0);
			break;
			
		case Data.DATATYPE_CHAT:
			DataChat dataChat = (DataChat) data;
			jObject.put("room_id", dataChat.getRoomId());
			jObject.put("message", dataChat.getMessage());
			jObject.put("user_id", dataChat.getUserId());
			break;
		}
		
		return jObject.toJSONString();
	}



}
