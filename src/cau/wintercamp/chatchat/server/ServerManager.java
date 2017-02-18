package cau.wintercamp.chatchat.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerManager{
	
	/** 관리자용 방이름 */
	
	public static final String ADMINROOM_ACCOUNT = "ADIMROOM_ACCOUNT";
	public static final String ADMINROOM_MAIN = "ADMINROOM_MAIN";
	
	public Server server;
	public DataManager dataManager;
	public AccountManager accountManager;
	public ServerManager(Server server){
		this.server = server;
		init();
	}
	
	
	public HashMap<String, ArrayList<SocketThread>> connectedSocket;
	public void init(){
		
		this.dataManager = new DataManager(this);
		this.accountManager = new AccountManager(this);
		
		connectedSocket = new HashMap<>();
		connectedSocket.put(ADMINROOM_ACCOUNT, new ArrayList<SocketThread>());
		connectedSocket.put(ADMINROOM_MAIN, new ArrayList<SocketThread>());
		Server.print("server manager", "init server manger");
	}
	
	/**
	 * 데이터가 서버메니저를 거쳐 지나게 하는 브릿지 함수<br>
	 * 데이터메니저로 전송이 된다.
	 * */
	public void handlingData(SocketThread socketThread, String data){
		Server.print("server manager", "handling data of " + socketThread.socket.getInetAddress().toString());
		this.dataManager.handlingData(socketThread, data);
	}
	
	/**
	 * 로그인/회원가입 관련 방에 소켓을 등록하는 함수. 여기서 방은 단순히 채팅방만을 의미하는 것이 아니라 기능에 따라 나뉘기도한다.
	 * */
	public void registerSocketToAccount(Socket socket){
		SocketThread socketThread = new SocketThread(socket, this);
		connectedSocket.get(ADMINROOM_ACCOUNT).add(socketThread);
		Server.print("server manager", "register socket to account of " + socketThread.socket.getInetAddress().toString());
		return;
	}

	/**
	 * 데이터메니저로부터 호출되는 함수. 로그인 기능을 한다<br>
	 * 이미 로그인이 되었는데 또다시 호출이 된다면 로그를 띄운다
	 * */
	public void successLogin(SocketThread socketThread, String toClient) {
		ArrayList<SocketThread> list = connectedSocket.get(ADMINROOM_ACCOUNT);
		if (list.contains(socketThread)){
			list.remove(list.indexOf(socketThread));
			list = connectedSocket.get(ADMINROOM_MAIN);
			list.add(socketThread);
			socketThread.sendData(toClient);
			Server.print("server manager", "success login client of " +  socketThread.socket.getInetAddress().toString());
		}
		else {
			System.out.println("ERROR - ALREADY LOGIN");
		}
	}
	
	public void failLogin(SocketThread socketThread, String objToJSONStr) {
		socketThread.sendData(objToJSONStr);
		Server.print("server manager", "fail login client of " +  socketThread.socket.getInetAddress().toString());
	}
	
	
	
	public synchronized void sendChatMessage(String roomId, String toClient) {
		for (SocketThread soc : connectedSocket.get(roomId)){
			soc.sendData(toClient);
		}
		Server.print("server manager", "chat message sended "+ toClient);
	}

	public void sendRegisterResult(SocketThread socketThread, String objToJSONStr) {
		socketThread.sendData(objToJSONStr);
		Server.print("server manager", "register message sended " + objToJSONStr);
	}
	
	
	
	
}
