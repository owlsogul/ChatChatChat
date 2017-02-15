package cau.wintercamp.chatchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static Server mainServer;
	public static void main(String[] args) {

		mainServer = new Server();

	}


	
	public SocketManager socketManager;
	public ServerSocket serverSocket;
	public Server(){
		try {

			serverSocket = new ServerSocket(7777);

			while(true) {
				Socket socket = serverSocket.accept();

				if (socket !=null) {
					// 클라이언트로 부터 접속 요청이 들어오면 소켓을 생성 후 SocketManager로 socket전달
					socketManager = new SocketManager();
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
