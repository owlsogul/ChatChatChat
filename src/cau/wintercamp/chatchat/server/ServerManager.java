package cau.wintercamp.chatchat.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ServerManager{
	
	/** �����ڿ� ���̸� */
	
	/** �α���/ȸ������ ������ ��� �ִ� �������� �ִ� �� */
	public static final String ADMINROOM_ACCOUNT = "ADIMROOM_ACCOUNT";
	/** ����� �׽�Ʈ��. ���� ��*/
	public static final String ADMINROOM_MAIN = "ADMINROOM_MAIN";
	
	public Server server;
	/** ������ ����, �м��� ���� ��ü */
	public DataManager dataManager;
	/** ���� ���� �����͸� ó���ϱ� ���� ��ü  */
	public AccountManager accountManager;
	public ServerManager(Server server){
		this.server = server;
		init();
	}
	
	/**
	 * ���ϵ��� ����Ǿ��ִ� �ؽ���. key ������ ���̸�, value�δ� ����� ��� ���ϵ��� ����Ʈ�� ������.
	 * */
	public HashMap<String, ArrayList<SocketThread>> connectedSocket;
	public void init(){
		
		// ���� �޴����� �ʱ�ȭ
		this.dataManager = new DataManager(this);
		this.accountManager = new AccountManager(this);
		
		// Ŀ��Ƽ����� �ʱ�ȭ
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
	
	/**
	 * �α����� �����Ͽ��� �� ȣ��Ǵ� �Լ�
	 * */
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

	public void disconnectSocket(SocketThread socketThread) {
		Iterator<String> iterator = connectedSocket.keySet().iterator();
		while (iterator.hasNext()){
			String key = iterator.next();
			ArrayList<SocketThread> list = connectedSocket.get(key);
			for (int i = 0;i < list.size(); i++){
				if (list.get(i) == socketThread){
					Server.print("server manager", socketThread.socket.getInetAddress().toString() + " �� ������ ���������ϴ�");
					list.remove(i);
					System.out.println(connectedSocket.get(key).size());
					return;
				}
			}
		}
	}

	public void logout(SocketThread socketThread, String userId, String objToJSONStr) {
		Iterator<String> iterator = connectedSocket.keySet().iterator();
		
		while (iterator.hasNext()){
			String key = iterator.next();
			if (key.equals(ADMINROOM_ACCOUNT)){
				continue;
			}
			ArrayList<SocketThread> list = connectedSocket.get(key);
			for (int i = 0;i < list.size(); i++){
				if (list.get(i) == socketThread){
					Server.print("server manager", userId + " �� �α׾ƿ� �߾��");
					connectedSocket.get(ADMINROOM_ACCOUNT).add(list.get(i));
					list.remove(i);
					System.out.println(connectedSocket.get(key).size());
					socketThread.sendData(objToJSONStr);
					return;
				}
			}
		}
	}
	
	
	
	
}
