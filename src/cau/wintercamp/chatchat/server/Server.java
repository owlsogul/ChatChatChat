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
					
					// Ŭ���̾�Ʈ�� ���� ���� ��û�� ������ ������ ���� �� SocketManager�� socket����
					SocketManager s_manager = new SocketManager(socket);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}



}
