package cau.wintercamp.chatchat.server;

public class ChatManager {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;
	  
	String strData;

	Data data = new Data();
	
	
	public ChatManager(String strData) {
		
		Translator trans = new Translator();
		data = trans.JSONstrToObj(strData);
		
		switch(data.dataType) {
		case DATATYPE_CHAT:
			chat();
			break;
		case DATATYPE_REGISTER:
			Account ac = new Account();
			ac.register();
			break;
		case DATATYPE_LOGIN:
			ac = new Account();
			ac.login();
		
		}
	}
	
	
	public void chat() {
		
		
	}

}
