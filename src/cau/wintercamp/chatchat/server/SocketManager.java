package cau.wintercamp.chatchat.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class SocketManager{
	
	// ������ ���� ���Ͼ�������� ���ͷ� �����ϰ� �� ���͵��� Map���� �����Ѵ�.
	//HashMap<String, Vector<Socket> > map = new HashMap<String , Vector<Socket> >();

	// �켱�� vector �ϳ����� ����ؼ� �� �ϳ��� �����غ���
	
	
	
	
	Vector<SocketThread> roomA = new Vector<>();
	
	public void addSocketThread(SocketThread socTh){
		
		Thread thread = new Thread(socTh);
		thread.start();
		
	}
	

	public void chatDataToUsers(Data data, String strData) {
		
		// ���� �����Ѵٸ� map���� �ش� ���� vector���� �������ش�
	
		// for socTh in roomA
		for(SocketThread socTh : roomA) {
			socTh.sendData(strData);
		}
		
	}
	
}
