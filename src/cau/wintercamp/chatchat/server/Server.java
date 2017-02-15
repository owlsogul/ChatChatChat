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
					// Ŭ���̾�Ʈ�� ���� ���� ��û�� ������ ������ ���� �� SocketManager�� socket����
					socketManager = new SocketManager();
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
