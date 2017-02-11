package cau.wintercamp.chatchat.server;

import java.net.Socket;

public class SocketManager{


	public SocketManager(Socket socket) {
		
		// 넘겨받은 socket을 SocketThread로 전달하면서 쓰레드 생성
		Thread thread = new Thread(new SocketThread(socket));
		thread.start();
	}
	
	// 소켓들 관리하는거 짜기
}
