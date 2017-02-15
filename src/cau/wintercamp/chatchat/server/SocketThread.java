package cau.wintercamp.chatchat.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.simple.JSONObject;

public class SocketThread implements Runnable{

	public Socket socket;
	public BufferedReader br;
	public PrintWriter pw;
	
	
	public SocketThread(Socket socket) {
		
		this.socket = socket;
	}
	
	public void run() {

		// Ŭ���̾�Ʈ�κ��� �����Ͱ� ������ 
		// ChatManager�� saveData() -> ������ ����, whereToGo() -> ������ ó���ؼ� �ٽ� ���� �������� �� �Ǻ�
		
		// ChatManager�� ���� ������ ���޹�����
		// sendData() -> Ŭ���̾�Ʈ�鿡�� ������ ������
		
		//receiveData();
		
		
	}
	
	public void receiveData() {
			
		// Ŭ���̾�Ʈ�κ��� JSON���ڿ� ���۹����� ChatManager�� ����
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data = br.readLine();
			if (data != null) {
				
				ChatManager c_manager = new ChatManager(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendData(String toClient) {
		
		 try {
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			pw.println(toClient);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

