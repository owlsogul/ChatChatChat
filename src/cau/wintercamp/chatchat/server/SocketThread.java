package cau.wintercamp.chatchat.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketThread implements Runnable{


	/**
	 * 클라이언트와 직접적으로 연결되어있는 객체<br>
	 * 생성자에서 가져와진다
	 * */
	public Socket socket;

	public Thread receiverThread;

	/**
	 * IO를 관리함 
	 * */
	public BufferedReader br;
	public PrintWriter pw;

	public ServerManager serverManager;

	public String userId = null;

	public SocketThread(Socket socket, ServerManager serverManager) {

		this.socket = socket;
		this.serverManager = serverManager;
		this.receiverThread = new Thread(this);
		

		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.receiverThread.start();

	}

	public void run() {

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

		String data = br.readLine();
		Server.print("socket", "received " + br.ready() + data);
		if (data != null) {
			serverManager.handlingData(this, data);
		}
		return;
	}

	// 굳이 여기서도 쓰레드를 만들어야하는지 의문이 들어 쓰레드를 만들지 않아보겠다.
	public void sendData(String toClient) {

		pw.println(toClient);
		Server.print("socket", "send " + toClient);
		pw.flush();
		
		return;
	}

	public void closeSocket() {
		try {
			br.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

}

