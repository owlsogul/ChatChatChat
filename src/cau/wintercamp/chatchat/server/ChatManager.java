package cau.wintercamp.chatchat.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChatManager {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;
	  
	String strData;
	SocketManager s_manager;
	Data data = new Data();
	
	
	public ChatManager(String strData, SocketManager s_manager) {
		
		this.strData = strData;
		this.s_manager = s_manager;
		
		Translator trans = new Translator();
		data = trans.JSONstrToObj(strData);
		
		switch(data.dataType) {
		case DATATYPE_CHAT:
			saveChatData();
			resendChatData();
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


	public void saveChatData() {
		
		// 방Key 이름의 파일에 데이터저장
		try {
			String fileName = data.roomId + ".txt";
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.write(strData);
		
			out.close();
		} catch(IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	
	public void resendChatData() {
		
		s_manager.chatDataToUsers(data, strData);
		 
	}
}