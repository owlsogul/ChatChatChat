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

	public SocketManager s_manager;

	public SocketThread(Socket socket, SocketManager s_manager) {

		this.socket = socket;
		this.s_manager = s_manager;
	}

	public void run() {

		// Ŭ���̾�Ʈ�κ��� �����Ͱ� ������ 
		// ChatManager�� saveData() -> ������ ����, whereToGo() -> ������ ó���ؼ� �ٽ� ���� �������� �� �Ǻ�

		// ChatManager�� ���� ������ ���޹�����
		// sendData() -> Ŭ���̾�Ʈ�鿡�� ������ ������
		while (true){
			try {
				receiveData();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		

	}

	public void receiveData() throws IOException {

		// Ŭ���̾�Ʈ�κ��� JSON���ڿ� ���۹����� ChatManager�� ����
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data = br.readLine();
		if (data != null) {

			ChatManager c_manager = new ChatManager(data, s_manager);
		}
	}

	public void sendData(String toClient) {

		try {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					pw.println(toClient);
				}
			});
			thread.start();
		} catch (Exception e) {
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

