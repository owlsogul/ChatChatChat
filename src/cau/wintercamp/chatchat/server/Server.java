package cau.wintercamp.chatchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(7777);
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				if (socket !=null) {
					
					// 클라이언트로 부터 접속 요청이 들어오면 소켓을 생성 후 SocketManager로 socket전달
					SocketManager s_manager = new SocketManager(socket);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}



}
