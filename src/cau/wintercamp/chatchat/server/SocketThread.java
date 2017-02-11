package cau.wintercamp.chatchat.server;

import java.io.OutputStream;
import java.net.Socket;

public class SocketThread implements Runnable{

	private Socket socket;
	
	public SocketThread(Socket socket) {
		
		this.socket = socket;
	}
	
	public void run() {

		
		//JSONObject json = new JSONObject();
	}
}
