package cau.wintercamp.chatchat.client;

import java.io.IOException;
import java.net.Socket;

/**
 * 서버로부터 Raw 데이터를 가져오는 클래스
 * @author jo_mingyu
 * */
public class Client {

	public static Client client;
	public static void main(String[] args) {
		client = new Client();
	}
	
	public Socket soc;
	public SocketIO socIo;
	
	public String ip = "localhost";
	public int port = 7777;
	
	public Client(){
		
		try {
			soc = new Socket(ip, port);
			socIo = new SocketIO(soc);
			if (socIo.init()) {
				System.out.println("Sucess Load Socket IO");
			}
			else {
				System.out.println("Failed Load Socket IO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
