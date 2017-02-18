package cau.wintercamp.chatchat.server;

public class DataManager {

	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;

	public ServerManager serverManager;
	public Translator translator;
	public DataManager(ServerManager serverManager){
		this.serverManager = serverManager;
		this.translator = new Translator();
		Server.print("data manager", "init");
	}


	//	public void saveChatData() {
	//		
	//		// 방Key 이름의 파일에 데이터저장
	//		try {
	//			String fileName = data.roomId + ".txt";
	//			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
	//			out.write(strData);
	//		
	//			out.close();
	//		} catch(IOException e) {
	//			System.err.println(e);
	//			System.exit(1);
	//		}
	//	}
	//	
	//	
	//	public void resendChatData() {
	//		
	//		s_manager.chatDataToUsers(data, strData);
	//		 
	//	}


	public void handlingData(SocketThread socketThread, String strData) {
		
		Server.print("data manager", "translate string to data " + strData);
		Data data = translator.JSONstrToObj(strData);
		
		switch (data.dataType){
		case Translator.DATATYPE_REGISTER:
			Server.print("data manager", "register");
			data.same = serverManager.accountManager.createAccount(data.id, data.pw);
			serverManager.sendRegisterResult(socketThread, translator.ObjToJSONStr(data));
			return;
		case Translator.DATATYPE_LOGIN:
			Server.print("data manager", "login");
			if (serverManager.accountManager.isLoginSucceed(data.id, data.pw)){
				data.same = true;
				serverManager.successLogin(socketThread, translator.ObjToJSONStr(data));
			}
			else {
				data.same = false;
				serverManager.failLogin(socketThread, translator.ObjToJSONStr(data));
			}
			return;
		case Translator.DATATYPE_CHAT:
			Server.print("data manager", "chat");
			// 채팅메세지를 보냄
			// 일단 메인 채팅룸으로 보낸다
			serverManager.sendChatMessage(ServerManager.ADMINROOM_MAIN, translator.ObjToJSONStr(data));
			return;
		}

	}
}