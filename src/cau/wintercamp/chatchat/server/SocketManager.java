package cau.wintercamp.chatchat.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class SocketManager{
	
	// 각각의 방의 소켓쓰레드들을 벡터로 관리하고 그 벡터들을 Map으로 관리한다.
	//HashMap<String, Vector<Socket> > map = new HashMap<String , Vector<Socket> >();

	// 우선은 vector 하나만을 사용해서 방 하나만 구현해보자
	
	
	
	
	Vector<SocketThread> roomA = new Vector<>();
	
	public void addSocketThread(SocketThread socTh){
		
		Thread thread = new Thread(socTh);
		thread.start();
		
	}
	

	public void chatDataToUsers(Data data, String strData) {
		
		// 방을 구분한다면 map에서 해당 방의 vector만을 전달해준다
	
		// for socTh in roomA
		for(SocketThread socTh : roomA) {
			socTh.sendData(strData);
		}
		
	}
	
}
