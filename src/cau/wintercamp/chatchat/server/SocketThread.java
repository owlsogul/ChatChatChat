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

		// 클라이언트로부터 데이터가 들어오면 
		// ChatManager에 saveData() -> 데이터 저장, whereToGo() -> 데이터 처리해서 다시 어디로 보내야할 지 판별
		
		// ChatManager로 부터 데이터 전달받으면
		// sendData() -> 클라이언트들에게 데이터 재전송
		
		//receiveData();
		
		
	}
	
	public void receiveData() {
			
		// 클라이언트로부터 JSON문자열 전송받으면 ChatManager로 전달
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

