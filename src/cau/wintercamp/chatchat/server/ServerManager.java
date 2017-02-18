package cau.wintercamp.chatchat.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerManager{
	
	/** �����ڿ� ���̸� */
	
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
	 * �����Ͱ� �����޴����� ���� ������ �ϴ� �긴�� �Լ�<br>
	 * �����͸޴����� ������ �ȴ�.
	 * */
	public void handlingData(SocketThread socketThread, String data){
		Server.print("server manager", "handling data of " + socketThread.socket.getInetAddress().toString());
		this.dataManager.handlingData(socketThread, data);
	}
	
	/**
	 * �α���/ȸ������ ���� �濡 ������ ����ϴ� �Լ�. ���⼭ ���� �ܼ��� ä�ù游�� �ǹ��ϴ� ���� �ƴ϶� ��ɿ� ���� �����⵵�Ѵ�.
	 * */
	public void registerSocketToAccount(Socket socket){
		SocketThread socketThread = new SocketThread(socket, this);
		connectedSocket.get(ADMINROOM_ACCOUNT).add(socketThread);
		Server.print("server manager", "register socket to account of " + socketThread.socket.getInetAddress().toString());
		return;
	}

	/**
	 * �����͸޴����κ��� ȣ��Ǵ� �Լ�. �α��� ����� �Ѵ�<br>
	 * �̹� �α����� �Ǿ��µ� �Ǵٽ� ȣ���� �ȴٸ� �α׸� ����
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
