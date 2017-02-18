package cau.wintercamp.chatchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void print(String prefix, String msg){
		System.out.println(prefix + ":" + msg);
	}
	
	public static Server mainServer;
	public static void main(String[] args) {

		mainServer = new Server();

	}


	
	public ServerManager serverManager;
	public ServerSocket serverSocket;
	public Server(){
		try {

			serverSocket = new ServerSocket(7777);
			serverManager = new ServerManager(this);
			Server.print("server", "open server");

			while(true) {
				Socket socket = serverSocket.accept();

				if (socket !=null) {
					serverManager.registerSocketToAccount(socket);
					Server.print("server", "send socket to socekt manager");
					socket = null;
					Server.print("server", "wait to connect client");
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
