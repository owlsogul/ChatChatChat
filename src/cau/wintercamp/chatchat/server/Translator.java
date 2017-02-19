package cau.wintercamp.chatchat.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Translator {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2, DATATYPE_LOGOUT = 3;
	
	public Data JSONstrToObj(String strData) {
		
		JSONParser parser = new JSONParser();
		Data data = new Data();
		
		try {
			// JSON 데이터를 넣어 JSON Object로 만들어줌
			JSONObject objData = (JSONObject) parser.parse(strData);
			
			// type정보 추출
			data.dataType = ((Long)objData.get("type")).intValue();
			Server.print("translator", "test  "+data.dataType);
			
			switch (data.dataType) {
			case DATATYPE_CHAT:
				String roomId = (String) objData.get("room_id");
				String message = (String) objData.get("message");
				String userId = (String) objData.get("user_id");
				data.dataChat(roomId, message, userId);
				break;

			case DATATYPE_REGISTER:
				String pw = objData.get("user_pw").toString();
				String id = objData.get("user_id").toString();
				boolean same = ((Long)objData.get("bool")).intValue() == 1 ? true : false;
				data.dataRegister(id, pw, same);
				break;
				
			case DATATYPE_LOGIN:
				id = (String) objData.get("user_id");
				pw = (String) objData.get("user_pw");
				same = ((Long)objData.get("bool")).intValue() == 1 ? true : false;
				data.dataLogin(id, pw, same);
				break;
			
			case DATATYPE_LOGOUT:
				id = (String) objData.get("user_id");
				data.dataLogout(id);
				break;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public String ObjToJSONStr(Data data) {
		JSONObject json = new JSONObject();
		json.put("type", data.dataType);
		json.put("user_id", data.id);
		json.put("user_pw", data.pw);
		json.put("bool", data.same ? 1 : 0);
		json.put("room_id", data.roomId);
		json.put("message", data.message);
		return json.toJSONString();
	}
	
}
