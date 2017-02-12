package cau.wintercamp.chatchat.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.JSONObject;

public class SocketThread implements Runnable{

	private Socket socket;
	BufferedReader br;
	
	public SocketThread(Socket socket) {
		
		this.socket = socket;
	}
	
	public void run() {

		// Ŭ���̾�Ʈ�κ��� �����Ͱ� ������ 
		// ChatManager�� saveData() -> ������ ����, dataHandler() -> ������ ó���ؼ� �ٽ� ���� �������� �� �Ǻ�
		// sendData() -> Ŭ���̾�Ʈ�鿡�� ������ ������
		
		receiveData();
		
	}
	
	public void receiveData() {
			
		// Ŭ���̾�Ʈ�κ��� JSON���ڿ� ���۹���
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data = br.readLine();
		
		if (data != null) {
			ChatManager c_manager = new ChatManager(data);
		}
	}
	
	public void sendData() {
		
	}
	
	public void closeSocket() {
		try {
			br.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

